import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(input())
usages = []
for i in range(n):
    j, d = [int(j) for j in input().split()]
    usages.append([j, j + d - 1])

usages.sort(key = lambda x: x[1], reverse = False)
print(usages, file = sys.stderr)

S = set([0])
k = 0

for i in range(1, len(usages)):
    if usages[i][0] > usages[k][1]:
        S.add(i)
        k = i


print(len(S))