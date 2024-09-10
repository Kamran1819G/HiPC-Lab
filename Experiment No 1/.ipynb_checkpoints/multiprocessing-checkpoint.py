import multiprocessing


def task(i):
    # Do some computation here
    return i * 2


if __name__ == '__main__':
    with multiprocessing.Pool() as pool:
        results = pool.map(task, range(10))
        print(results)
