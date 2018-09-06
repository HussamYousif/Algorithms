import sys

class Movie:

    def __init__(self):
        parameter = sys.stdin.readline().split()
        self.movies = int(parameter[0])
        self.requests = int(parameter[1])
        self.size = self.generateSize(self.movies+self.requests)
        self.tree = [0]*(self.size*2)
        self.alias = [0] * (self.movies+1)
        # inserting the starting movies into the tree.
        for i in range(0, self.movies):
            self.tree[i+self.size+self.requests] = 1
            self.alias[i+1] = i+self.size+self.requests

        # Building the branches.
        for i in range(self.size-1, 0, -1):
            self.tree[i] = self.tree[i*2] + self.tree[(i*2)+1]

        self.maxTaken = self.alias[1]
        self.requestList = map(int, raw_input().split())

    def generateSize(self, minimumLength):
        s = 1
        while s <= minimumLength:
            s *= 2
        return s

    def sum(self, movieRequest):
        left = self.maxTaken
        right = self.alias[movieRequest]

        ret = self.tree[left]

        while(left/2 != right/2):
            if left %2 == 0:
                ret += self.tree[left+1]
            if right %2 == 1:
                ret += self.tree[right-1]

            left /= 2
            right /= 2
        return ret

    def update(self, movieName):
        # TODO: I may have to subtract this with one:
        previousPosition = self.alias[movieName]

        while previousPosition != 0:
            self.tree[previousPosition] -= 1
            previousPosition /= 2

        self.maxTaken -= 1

        self.alias[movieName] = self.maxTaken
        position = self.maxTaken

        while position != 0:
            self.tree[position] += 1
            position /= 2

    def computeRequest(self):
        resultList = [0] * len(self.requestList)
        i = 0
        for movieNumber in self.requestList:
            if self.alias[movieNumber] == self.maxTaken:
                resultList[i] = 0
                i += 1
            else:
                resultList[i] = self.sum(movieNumber)
                self.update( movieNumber)
                i += 1
        for result in resultList:
            print result,

    def showTree(self):
        for i in range(0,len(self.tree)):
            print "tree[%d] = %d" %(i,self.tree[i])

def main():
    testCases = int(raw_input())
    for i in range(0, testCases):
        movie = Movie()
        movie.computeRequest()
        print

if __name__ == "__main__":
    main()
