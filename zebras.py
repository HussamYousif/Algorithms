import sys

def myRead():
    x = raw_input()
    if x=="Z":
        return "1"
    elif x=="O":
        return "0"
    else:
        return "Unexpected input."

n = int(raw_input())

l = [myRead() for x in range(n)]

l = "".join(l)
l = int(l,2)

print ((2**n)-1 - l)



