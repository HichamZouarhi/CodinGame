import sys
import math
 
w, h = [int(i) for i in input().split()]
n = input()

x0, y0 = [int(i) for i in input().split()]
x, y = x0, y0
x_search, y_search = range(w), range(h)

def narrow_search(previous_position, next_position, search_range):
    if info == "UNKNOWN":
        pass
    elif info == "SAME":
        search_range = [i for i in search_range if abs(previous_position - i) == abs(next_position - i)]
    elif info == "WARMER":
        search_range = [i for i in search_range if abs(previous_position - i) > abs(next_position - i)]
    elif info == "COLDER":
        search_range = [i for i in search_range if abs(previous_position - i) < abs(next_position - i)]
    
    return search_range

def lookup(x0, y0, x, y, x_search, y_search, info):
    # xaxis dichotomy
    if len(x_search) != 1:
        x_search = narrow_search(x0, x, x_search)
    #yaxis dichotomy
    else:
        y_search = narrow_search(y0, y, y_search)
    
    return x_search, y_search
 
 
while 1:
    info = input()
    x_search, y_search = lookup(x0, y0, x, y, x_search, y_search, info)

    x0,y0 = x,y
    # dichotomy along x axis
    if len(x_search) != 1:
        # the bisection between x0 and x should cut the area in 2 so:
        # (x + x0)/2 = (x_search[0] + x_search[-1])/2
        # the 2 first cases narrow the search for test cases 6 and 8
        # credit to ethiery for this one
        
        if (x0 == 0 and len(x_search) != w):
            x = (3 * x_search[0] + x_search[-1]) // 2 
        elif (x0 == w - 1 and len(x_search) != w):
            x = (x_search[0] + 3 * x_search[-1]) // 2 - x0
        else:
            x = x_search[0] + x_search[-1] - x0
        
        # to avoid fixed points
        if x == x0:
            x += 1
        x = min(max(x, 0), w-1)
 
    else:
    # transition to second dichotomy
        if x != x_search[0]:
            x = x0 = x_search[0]
            print(x, y)
            info = input()
        # finishing
        if len(y_search) == 1:
            y = y_search[0]
        # dichotomy along y axis
        else:
            if (y0 == 0 and len(y_search) != h):
                y = (3 * y_search[0] + y_search[-1]) // 2
            elif (y0 == h - 1 and len(y_search) != h):
                y = (y_search[0] + 3 * y_search[-1]) // 2 - y0
            else:
                y = y_search[0] + y_search[-1] - y0
            y = min(max(y, 0), h-1)
    
    print(x, y)