###################################################################################################

#Function to store the author´s name with its respective book in the dictionary: books
def booksDictionary(file):
    try:
        books = {}
        with open(file,"r") as book_file:
            for line in book_file:
                line = line.strip().split(",")
                books[line[0]] = line[1] #Format {"author":"book"}
        return books
    except FileNotFoundError:
        print("**FileNotFoundError: File with name %s not found" % (file))
#---------------------------------------------------------------------------------
#Function to store all user ratings in the dictionary: ratingsUser
def ratingsDictionary(file):
    try:
        ratingsUser = {}
        line = 0 #Keeps track of the current line in the txt file
        last_user = None
        with open(file,"r") as ratings:
            for i in ratings:
                i = i.strip()
                if (line % 2 != 0): #If line is not divisble by 2 then we must have the ratings
                    i = list(map(int,i.split(" "))) #Converts the ratings into a list of integers
                    ratingsUser[last_user] = i
                else: #Store the username to variable last_user
                    last_user = i
                line += 1
        return ratingsUser
    except FileNotFoundError:
        print("**FileNotFoundError: File with name %s not found" % (file))
###################################################################################################

#Calculates the dot product for the similarity algorithm and stores in a list of lists the result as {"userX":dot_product}
def dotProductSorted(ratingsUser,user):
    dotProductList = [] 
    username_ratings = ratingsUser[user]
    
    with open("output.txt","a") as output_file:
        for username in ratingsUser:
            if username != user:
                #Dot product of username ratings with all other users
                dot_product = sum(list(map(lambda x:x[0]*x[1], zip(username_ratings, ratingsUser[username]))))
                dotProductList.append([username, dot_product])
    
        dotProductSortedList = sorted(dotProductList,key=lambda x: x[1]) #Dot product list of list sorted in ascending order
        for i in dotProductSortedList:
            output_file.write("%s similarity with %s: %s " % (i[0],user,i[1])) #username,user,dot product
            output_file.write("\n")

    return dotProductSortedList #Dot product list of list sorted in ascending order

#Creates a sorted list of most_similar_user ratings
def booksRatingsUser(user,books):
    userBooksRatings = []
    
    for x,i in enumerate(books):
        userBooksRatings.append((i,books[i],user[x])) # [[author,book,rating]]
    
    return sorted(userBooksRatings, key=lambda x: x[2], reverse=True)
        
#Finding similar books
def recommendedBooksUser(userRatingsDict,mostSimilarUserRatings,books_recommended,expectedRecommendations):
    global recommendedBooks #we make this list global so that its contents keep updating when function called several times
    for i in mostSimilarUserRatings:
        if books_recommended < expectedRecommendations: #do this if number_of_recommendations not reached
            
            if userRatingsDict[i[0:2]] == 0 and (i[2] == 3 or i[2] == 5) \
            and (i[0:2] not in recommendedBooks): #If book not read and rating 3 or 5 from similar user and book not recommended
                
                recommendedBooks.append((i[0:2]))
                books_recommended += 1
    return recommendedBooks,books_recommended #Return the list of books and the updated number of books in list 
    
#Similarity algorithm to find common likes between users
def similarityAlgorithm(user,expectedRecommendations=10):
    global recommendedBooks
    ratingsUser = ratingsDictionary("ratings.txt")
    
    #Dictionary in the form {(author,book):rating} for the user inputed
    username_ratings = ratingsUser[user]
    books = booksDictionary("books.txt")
    userRatingsDict = {}
    for x,i in enumerate(books):
        userRatingsDict[(i,books[i])] = username_ratings[x]
        
    dotProductList = dotProductSorted(ratingsUser,user) #Returns a list of lists with the corresponding dot product per user
    
    booksRecommended,user_position = 0,-1
    
    while booksRecommended < expectedRecommendations:
        mostSimilarRatings = ratingsUser[dotProductList[user_position][0]]
        recommendedBooks.append((dotProductList[user_position][0])) #appends the username to list
        mostSimilarUserRatings = booksRatingsUser(mostSimilarRatings,books)

        recommendedBooks,booksRecommended = recommendedBooksUser(userRatingsDict,mostSimilarUserRatings,
                                                                 booksRecommended,expectedRecommendations)
        user_position -= 1 #look at the next element from the list in reverse order
    
    with open("output.txt","a") as output_file:
        output_file.write("\n")
        output_file.write("Recommending based on similarity algorithm")
        output_file.write("\n")
        output_file.write("+++++++++++++++++++++++++++++++++++")
        output_file.write("\n")
        output_file.write("\n")
        for i in recommendedBooks:
            if len(i) > 2: #If there is only one element in i: i.e.: username
                output_file.write("Recommended by user: %s " % (i))
                output_file.write("\n")
            else:
                output_file.write("       " + "%s by %s" % (i[1],i[0]))
                output_file.write("\n")
    return recommendedBooks

##################################################################################################
import random

#Each integer rating should be interpreted as follows:
def ratingsMeaningTable():
    print("############################################")
    print("Rating          Meaning")
    print("-----------------------------------")
    print(" -5             Hated it!")
    print(" -3             Didn't like it ")
    print("  0             Haven´t read it")
    print("  1             OK             ")
    print("  3             Liked it!")
    print("  5             Really liked it!")
    print("#############################################")


def ratingBooks(file):
    booksAsked = []
    for i in range(20): 
        book = random.choice(open(file).readlines())
        book = book.strip()
        if book not in booksAsked:
            booksAsked.append(book)
    
    #Rating books
    booksAndRatings = []
    ratingsMeaningTable()
    for book in booksAsked:
        rating = False
        while rating != True: #While input is invalid ask for a rating
            rating_input = input("Rate the book %s: " % (book))
            if rating_input in ["-5","-3","0","1","3","5"]:
                booksAndRatings.append((book,rating_input))
                rating = True
            else:
                print(Exception("**InvalidRating**")) 
    
    ratingsForAllBooks = []
    with open(file,"r") as books_file:
        for book in books_file:
            book = book.strip()
            if book in [element[0] for element in booksAndRatings]: #iterates over the first element of the list
                for i in booksAndRatings: 
                    if i[0] == book:
                        ratingsForAllBooks.append(str(i[1]))
            else:
                ratingsForAllBooks.append("0")
                
    return " ".join(ratingsForAllBooks) #leaves the ratings in the required format X X X X X X X


def main():
    try:
        with open("output.txt","a") as output_file:
            input_user = input("Please enter your username: ")
            output_file.write("What is your name?: %s " % (input_user))
            output_file.write("\n")
            input_recommendations = int(input("Please enter the number of recommendations you would like to receive: "))
            output_file.write("How many recommendations: %s " % (input_recommendations))
            output_file.write("\n")
            output_file.write("\n")
        
        if input_user in ratingsDictionary("ratings.txt"):
            return similarityAlgorithm(input_user,input_recommendations)
        else:
            #If user not in file ratings.txt then do the following
            with open("ratings.txt","a") as ratingsFile:
                ratingsFile.write(input_user)
                ratingsFile.write("\n")
        
                books_ratings = ratingBooks("books.txt") #function called to ask user the ratings of 20% of books
                ratingsFile.write(books_ratings)
                ratingsFile.write("\n")
            return similarityAlgorithm(input_user,input_recommendations)

    except ValueError:
        open("output.txt", "w").close() #Deletes all contents of the output.txt if this stage reached
        print("**ValueError: Please only use integers when you input the number of recommendations**")
    except FileNotFoundError:
        print("**FileNotFoundError; File name not found**")

recommendedBooks = [] #Initialize a list of books as a global variable
main()  
