import sys
import math
__author__ = 'hussam'

"""
The Idea is simple:
1. Find convex hull.
2. Find the maximum distance for each point in convex hull.

So we basically ignore all the points in the middle of the convex hull and only care for the distance
of the points in the bounds.

run time = sorting O(n log n), graham scan = O(n log n), comparing each point in convex =(m^2).
n for all points.
M for the points in graham scan only.
"""


def ccw(p1, p2, p3):
    if (p2[0] - p1[0])*(p3[1] - p1[1]) - (p2[1] - p1[1])*(p3[0] - p1[0]) < 0:
        return -1
    if (p2[0] - p1[0])*(p3[1] - p1[1]) - (p2[1] - p1[1])*(p3[0] - p1[0]) > 0:
        return 1
    else:
        return 0


def left(convex, p):
    while len(convex) > 1 and ccw(convex[-2], convex[-1], p) != 1:
            convex.pop()
    if not len(convex) or convex[-1] != p:
        convex.append(p)

    return convex


def get_upper_lower_hull(coordinates):
    """
Sort the coordinates by x and y coords ( NOT BY POLAR ANGLE).
Then we find the lower and upper hull and then we put them together.
"""
    # n Log n
    coordinates = sorted(coordinates)

    lower = reduce(left, coordinates, [])
    upper = reversed(coordinates)
    upper = reduce(left, upper, [])

    return (upper, lower)


def distance(x, y, x2, y2):
    return math.sqrt(math.pow(x2 - x, 2) + math.pow(y2 - y, 2))


def main():
    shots = int(raw_input())

    coords = []
    for i in range(shots):
        x, y = map(int, sys.stdin.readline().split())
        coords.append((x, y))

    upper = []
    lower = []
    upper, lower = get_upper_lower_hull(coords)

    dis = 0

    for x, y in upper:
        for x2, y2 in lower:
            tmp = distance(x, y, x2, y2)
            if tmp > dis:
                dis = tmp
    print dis


if __name__ == "__main__":
    main()