import sys
import collections
import cProfile


class Puzzle():

    """puzzle task, I will create a bfs which will get all permutations of a
    puzzle such that database[server_input] = moveis"""

    def __init__(self):
        self.database = {}
        self.bfs()

    def bfs(self):
        """The actual bfs after completion it will return all
        the permutations of a complete state.
        """
        queue = collections.deque()
        completed = ('1', '2', '3', '4', '5', '6', '7', '8', '#')
        self.database[completed] = 0
        queue.appendleft(list(completed))
        visited = {}
        visited[completed] = True

        while (queue):
            puzzle = queue.popleft()
            free = self.get_free_space(puzzle)
            neighbour_list = self.get_new_permutation(puzzle, free)
            depth = self.database[tuple(puzzle)]+1

            for neighbour in neighbour_list:
                key = tuple(neighbour)
                if key not in visited:
                    queue.append(neighbour)
                    self.database[key] = depth
                    visited[key] = True

    def get_free_space(self, puzzle):
        """TODO: Returns the free_space of the puzzle.

        :puzzle: the current puzzle we want to find free_space of.
        :returns: free_space.
        """
        for i in range(len(puzzle)):
            if puzzle[i] == '#':
                return i


    def get_new_permutation(self, puzzle, free_space):
        """ Slides b into freespace.

        :returns: a new list of puzzles with b and free_space swapping place

        """
        permutations = []

        free_space_neighbours = self.get_neighbours(free_space)

        for neighbour in free_space_neighbours:
            tmp_list = list(puzzle)
            tmp = tmp_list[free_space]
            tmp_list[free_space] = tmp_list[neighbour]
            tmp_list[neighbour] = tmp
            permutations.append(tmp_list)

        return permutations

    def get_neighbours(self, free_space):
        if free_space == 0:
            return (1,3)
        elif free_space == 1:
            return (0,2,4)
        elif free_space == 2:
            return (1,5)
        elif free_space == 3:
            return (0, 4, 6)
        elif free_space == 4:
            return (1, 3, 5, 7)
        elif free_space == 5:
            return (2, 4, 8)
        elif free_space == 6:
            return (3, 7)
        elif free_space == 7:
            return (4, 6, 8)
        elif free_space == 8:
            return (5, 7)


def get_input():
    puzzle = []

    for i in range(0,4):
        puzzle += sys.stdin.readline().strip()
    return tuple(puzzle)

def main():
    test_cases = int(raw_input())
    puzzle = Puzzle()

    for test in range(0,test_cases):
        key = get_input()
        if (key not in puzzle.database):
            print "impossible"
        else:
            print puzzle.database[key]

if __name__ == '__main__':
    main()
