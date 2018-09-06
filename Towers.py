import sys


def update(size,floors, position, value):
    position += size

    while (position != 0):
        floors[position] += value
        position /=2

def sum(size, floors, start, end):
    left = start + size
    right = end + size+1

    ret = floors[left]
    while ((left/2) != (right/2)):
        if (left%2 == 0):
            ret += floors[left+1]
        if(right%2 == 1):
            ret += floors[right-1]
        left /=2
        right /=2
    return ret
def showTree(floors):
    for i in floors:
        print floors[i]

while (True):
    numberOfFloors = int(raw_input())

    if (numberOfFloors == 0 ):
        sys.exit()

    size = 1

    while (size <= numberOfFloors):
        size *=2

    floors = [0] * (size*2)

    for i in range(0, numberOfFloors):
        floors[i+size] = int(raw_input())
    for i in range(size-1, 0, -1):
        floors[i] = floors[i*2] + floors[(i*2)+1]
        
    while(True):
        userCommand = sys.stdin.readline().split()

        command = userCommand[0]
        if (command is "S"):
            print "Slutt for i dag."
            break

        a = int(userCommand[1])
        b = int(userCommand[2])
 
        if (command is "I"):
            update(size,floors,a-1,b)
        if (command is "U"):
            update(size,floors,a-1,-b)
        

        if(command is "T"):
            inhabitants = sum(size,floors,a-1,b-1)
            gjest = "gjester"
            if (inhabitants == 1):
                gjest = "gjest"

            if(a == b ):
	        print "Det er %d %s som bor i etasje %d." % (inhabitants,gjest,a)
            elif (a == 1 and  b == numberOfFloors):
	        print "Det er %d %s som bor i hotellet." % (inhabitants,gjest)
            else:
	        print "Det er %d %s som bor mellom etasje %d og etasje %d." % (inhabitants, gjest, a, b)



