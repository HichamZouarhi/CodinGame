import sys
import math
import json
import re

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.

n = int(input())
cgx_text = ""
for i in range(n):
    cgxline = input()
    print(cgxline, file = sys.stderr)
    cgxline = re.sub(r"\((?=(?:[^\']*\'[^\']*\')*[^\']*\Z)", '\n(\n', cgxline)
    cgxline = re.sub(r"\)(?=(?:[^\']*\'[^\']*\')*[^\']*\Z)", '\n)\n', cgxline)
    cgxline = cgxline.replace(')\n;', ');')
    cgxline = re.sub(r"\;(?=(?:[^\']*\'[^\']*\')*[^\']*\Z)", ";\n", cgxline)
    cgxline = re.sub(r"\= (?=(?:[^\']*\'[^\']*\')*[^\']*\Z)", '=', cgxline)
    cgxline = re.sub(r"\ (?=(?:[^\']*\'[^\']*\')*[^\']*\Z)", '', cgxline)
    cgxline = re.sub(r"\t(?=(?:[^\']*\'[^\']*\')*[^\']*\Z)", '', cgxline)
    cgx_text += cgxline


level = 0
new_cgx = ""
for line in cgx_text.split('\n'):
    new_line = line
    for i in range(level):
        new_line = "    " + new_line
    if line.startswith('('):
        level += 1
    
    if line.endswith(')') or line.endswith(');'):
        level -= 1
        new_line = new_line[4:]
   
    if new_line.replace(' ', '') != '':
        new_cgx += new_line + '\n'
    
print(new_cgx.strip())
