__author__ = 'hussam'
import cProfile
import itertools
import math

# THE SIEVE OF ERATOSTHENES
def generating_primes():
    primes = [True] * (int(math.ceil(math.sqrt(9999999))))

    primes[0] = False
    primes[1] = False

    for i in range(0, len(primes)):
        if primes[i]:
            for j in range(2,len(primes)):
                if i*j >= len(primes):
                    break
                else:
                    primes[i*j] = False
    return primes


def gen_primes(up_to):
    primes = []
    for number in range(2, up_to):
        if is_prime(number):
            primes.append(number)
    return primes

# find prime through trial by division.
def is_prime(number):

    # For 0's and 1's.
    if number < 2:
        return False

    if number == 2:
        return True

    # This right here is genius. We can remove all the multiples of two by checking if numberis divisible by 2.
    if number % 2 == 0:
        return False

    for i in range(3, int(math.sqrt(number))+1, 2):
        if number % i == 0:
            return False
    return True


def check_primes(primes, number):
    num = int(number)
    sqrt = math.ceil(math.sqrt(num))
    if num < 2:
        return False

    if num == 2:
        return True

    if num % 2 == 0:
        return False


    for prime in primes:
        if prime > sqrt:
            return True
        if num % prime == 0 and prime != num:
            return False
    return True



def main():
    primes = gen_primes(int(math.ceil(math.sqrt(9999999))))

    tests = int(raw_input())
    for i in range(tests):
        input = raw_input()

        #perms = set([p for p in itertools.combinations(input,len(input))])
        perms = set([])
        #print perms

        #for index in range(0, len(input)):
            #for permutaion in itertools.combinations(input, index):
                #perms.add(permutaion)


        for index in range(1, len(input)+1):
            for permutaion in itertools.permutations(input, index):
                p = ("".join(permutaion).lstrip("0"))
                if p:
                    perms.add(p)

        #print perms

        #perms = filter(None, perms)
        #perms = map("".join, perms)

        results = 0

        for per in perms:
            if check_primes(primes, per):
                results += 1


        print results


if __name__ == "__main__":
    main()