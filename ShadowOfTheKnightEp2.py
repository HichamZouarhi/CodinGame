import sys
import math
import random
from copy import deepcopy

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

# w: width of the building.
# h: height of the building.
w, h = [int(i) for i in input().split()]
n = int(input())  # maximum number of turns before game over.

x0, y0 = [int(i) for i in input().split()]
curr_pos = [x0, y0]

prev_grid = [[0 for i in range(w)] for j in range(h)]
curr_grid = [[0 for i in range(w)] for j in range(h)]
# print(str(w) + " " + str(h), file = sys.stderr)
# print(prev_grid, file = sys.stderr)

def distance(ax, ay, bx, by):
    return math.sqrt((bx - ax) ** 2 + (by - ay) ** 2)

def get_next_pos(grid, curr_pos):
    pos_seq = []
    for i in range(h):
        for j in range(w):    
            if grid[i][j] != -1:
                pos_seq.append([j, i])
            
    # pos_seq.remove(curr_pos)
    return random.choice(pos_seq)

def distance_grid(grid, pos):
    for i in range(h):
        for j in range(w):
            if grid[i][j] != -1:
                grid[i][j] = distance(j, i, pos[0], pos[1])
    
    return deepcopy(grid)

def compare_grids(grid1, grid2, eq, inversed = False):
    for i in range(h):
        for j in range(w):
            if grid1[i][j] != -1 and grid2[i][j] != -1:
                if eq:
                    if grid1[i][j] != grid2[i][j]:
                        grid2[i][j] = -1
                elif inversed:
                    if grid1[i][j] > grid2[i][j]:
                        grid2[i][j] = -1
                else:
                    if grid1[i][j] < grid2[i][j]:
                        grid2[i][j] = -1
    return deepcopy(grid2)

# game loop
prev_pos = deepcopy(curr_pos)
while True:
    bomb_dir = input()  # Current distance to the bomb compared to previous distance (COLDER, WARMER, SAME or UNKNOWN)
    
    if bomb_dir == "UNKNOWN":
        prev_pos = deepcopy(curr_pos)
        next_pos = get_next_pos(curr_grid, prev_pos)
        # curr_pos = deepcopy(next_pos)
        # print(str(next_pos[0]) + " " + str(next_pos[1]))
        # curr_pos = next_pos
    elif bomb_dir == "SAME":
        prev_pos = deepcopy(curr_pos)
        curr_pos = deepcopy(next_pos)
        print(str(prev_pos) + " -> " + str(curr_pos), file = sys.stderr)
        prev_grid = distance_grid(prev_grid, prev_pos)
        curr_grid = distance_grid(curr_grid, curr_pos)
        print("PREV GRID --------" + str(prev_pos), file = sys.stderr)
        for row in prev_grid:
            print([int(col) for col in row], file = sys.stderr)
        
        print("CURR GRID --------" + str(curr_pos), file = sys.stderr)
        for row in curr_grid:
            print([int(col) for col in row], file = sys.stderr)
        curr_grid = compare_grids(prev_grid, curr_grid, eq = True)
        print("COMPARE GRID ------------", file = sys.stderr)
        for row in curr_grid:
            print([int(col) for col in row], file = sys.stderr)
        # print(str(curr_pos[0]) + " " + str(curr_pos[1]))
        next_pos = get_next_pos(curr_grid, curr_pos)
    elif bomb_dir == "WARMER":
        prev_pos = deepcopy(curr_pos)
        curr_pos = deepcopy(next_pos)
        print(str(prev_pos) + " -> " + str(curr_pos), file = sys.stderr)
        prev_grid = distance_grid(prev_grid, prev_pos)
        curr_grid = distance_grid(curr_grid, curr_pos)
        print("PREV GRID --------" + str(prev_pos), file = sys.stderr)
        for row in prev_grid:
            print([int(col) for col in row], file = sys.stderr)
        
        print("CURR GRID --------" + str(curr_pos), file = sys.stderr)
        for row in curr_grid:
            print([int(col) for col in row], file = sys.stderr)
        curr_grid = compare_grids(prev_grid, curr_grid, eq = False, inversed = False)
        print("COMPARE GRID ------------", file = sys.stderr)
        for row in curr_grid:
            print([int(col) for col in row], file = sys.stderr)
        # print(str(curr_pos[0]) + " " + str(curr_pos[1]))
        next_pos = get_next_pos(curr_grid, curr_pos)
    elif bomb_dir == "COLDER":
        prev_pos = deepcopy(curr_pos)
        curr_pos = deepcopy(next_pos)
        print(str(prev_pos) + " -> " + str(curr_pos), file = sys.stderr)
        prev_grid = distance_grid(prev_grid, prev_pos)
        curr_grid = distance_grid(curr_grid, curr_pos)
        print("PREV GRID --------" + str(prev_pos), file = sys.stderr)
        for row in prev_grid:
            print([int(col) for col in row], file = sys.stderr)
        
        print("CURR GRID --------" + str(curr_pos), file = sys.stderr)
        for row in curr_grid:
            print([int(col) for col in row], file = sys.stderr)
        
        curr_grid = compare_grids(prev_grid, curr_grid, eq = False, inversed = True)
        print("COMPARE GRID ------------", file = sys.stderr)
        for row in curr_grid:
            print([int(col) for col in row], file = sys.stderr)
        # print(str(curr_pos[0]) + " " + str(curr_pos[1]))
        next_pos = get_next_pos(curr_grid, curr_pos)
    
    print(str(next_pos[0]) + " " + str(next_pos[1]))
    prev_grid = deepcopy(curr_grid)

    
