public class MultiThreadedSum {
    private static int sum = 0; // Shared resource

    // Synchronized method to update the sum
    public synchronized void addToSum(int value) {
        sum += value;
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadedSum multiThreadedSum = new MultiThreadedSum();
        int numThreads = 10; // Number of threads
        int range = 10000; // Range of integers to sum
        Thread[] threads = new Thread[numThreads];

        // Create threads
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(
                    new SumTask(multiThreadedSum, i * (range / numThreads), (i + 1) * (range / numThreads)));
            threads[i].start(); // Start the thread
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            thread.join();
        }

        // Print the final sum
        System.out.println("The sum of the first " + range + " integers is: " + sum);
    }

    // Runnable task that sums a range of integers
    static class SumTask implements Runnable {
        private final MultiThreadedSum multiThreadedSum;
        private final int start;
        private final int end;

        public SumTask(MultiThreadedSum multiThreadedSum, int start, int end) {
            this.multiThreadedSum = multiThreadedSum;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                multiThreadedSum.addToSum(i);
            }
        }
    }
}
