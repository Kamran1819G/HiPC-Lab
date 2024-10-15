class PrimePalindromeFinder implements Runnable {
    private int startRange, endRange;

    // Constructor to initialize the range for this thread
    public PrimePalindromeFinder(int start, int end) {
        this.startRange = start;
        this.endRange = end;
    }

    // Run method to check numbers in the assigned range
    @Override
    public void run() {
        for (int num = startRange; num <= endRange; num++) {
            if (isPrime(num) && isPalindrome(num)) {
                // Synchronized block to ensure only one thread prints at a time
                synchronized (System.out) {
                    System.out.println(Thread.currentThread().getName() + ": " + num + " is Prime and Palindrome");
                }
            }
        }
    }

    // Method to check if a number is prime
    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // Method to check if a number is a palindrome
    private boolean isPalindrome(int num) {
        int original = num, reversed = 0;
        while (num > 0) {
            int digit = num % 10;
            reversed = reversed * 10 + digit;
            num /= 10;
        }
        return original == reversed;
    }
}

public class MultithreadedPrimePalindrome {
    public static void main(String[] args) {
        int start = 1, end = 100000; // Range of numbers to check
        int numThreads = 4; // Number of threads

        // Divide the range among the threads
        int rangePerThread = (end - start + 1) / numThreads;

        // Create and start threads
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            int threadStart = start + (i * rangePerThread);
            int threadEnd = (i == numThreads - 1) ? end : threadStart + rangePerThread - 1;
            threads[i] = new Thread(new PrimePalindromeFinder(threadStart, threadEnd));
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Prime and Palindrome search completed.");
    }
}
