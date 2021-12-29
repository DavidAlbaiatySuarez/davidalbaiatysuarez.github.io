import numpy as np  
import matplotlib.pyplot as plt

# The x-axis (values incremented by 200)
x = []
n = 0
for i in range(0,100):
    n+=200
    x.append(n)

# np_array = np.array(x)

# Values for the y-axis
# (copy here the array from the Tests.killerArrayAlgorithm())
# The following array is from a previous run of the aforementioned method
y = np.array([1, 1, 1, 2, 3, 5, 2, 2, 1, 2, 2,
              2, 4, 6, 4, 5, 7, 11, 7, 13, 14,
              10, 10, 10, 12, 12, 17, 14, 15, 23,
              18, 19, 21, 26, 25, 27, 27, 30, 27,
              32, 49, 49, 49, 52, 44, 37, 62, 42,
              42, 42, 44, 46, 63, 50, 51, 54, 57,
              61, 75, 75, 83, 77, 68, 89, 85, 83,
              114, 95, 85, 108, 94, 114, 96, 115,
              132, 125, 108, 137, 143, 120, 154,
              139, 137, 166, 133, 136,154, 188, 151,
              158, 177, 169, 168, 162, 170, 174, 198, 175, 171, 178])


# plotting 
plt.title("Line graph")  
plt.xlabel("X axis")  
plt.ylabel("Y axis")  
plt.plot(x, y, color ="red")


plt.show()

