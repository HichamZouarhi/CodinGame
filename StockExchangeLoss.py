import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(input())
vals = []
for i in input().split():
    v = int(i)
    vals.append(v)

peak = vals[0]
result = 0
max_result = 0
for val in vals[1:]:
    if val > peak:
        peak = val
        continue
    else:
        result = peak - val
    
    if result > max_result:
        max_result = result


print(-max_result)
