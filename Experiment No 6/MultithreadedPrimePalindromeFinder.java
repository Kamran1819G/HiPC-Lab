class PrimeFinder implements Runnable {
    private int startRange, endRange;

    public PrimeFinder(int start, int end) {
        this.startRange = start;
        this.endRange = end;
    }

    @Override
    public void run() {
        StringBuilder primes = new StringBuilder();
        primes.append("Prime numbers from ").append(startRange).append(" to ").append(endRange).append(": \n");
        for (int num = startRange; num <= endRange; num++) {
            if (isPrime(num)) {
                primes.append(num).append(" ");
            }
        }
        synchronized (System.out) {
            System.out.println(primes.toString());
        }
    }

    private boolean isPrime(int num) {
        if (num <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}

class PalindromeFinder implements Runnable {
    private int startRange, endRange;

    public PalindromeFinder(int start, int end) {
        this.startRange = start;
        this.endRange = end;
    }

    @Override
    public void run() {
        StringBuilder palindromes = new StringBuilder();
        palindromes.append("Palindrome numbers from ").append(startRange).append(" to ").append(endRange)
                .append(": \n");
        for (int num = startRange; num <= endRange; num++) {
            if (isPalindrome(num)) {
                palindromes.append(num).append(" ");
            }
        }
        synchronized (System.out) {
            System.out.println(palindromes.toString());
        }
    }

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

public class MultithreadedPrimePalindromeFinder {
    public static void main(String[] args) {
        int start = 1, end = 1000; // Range of numbers to check
        int numThreads = 4; // Number of threads

        // Divide the range for both prime and palindrome finders
        int rangePerThread = (end - start + 1) / numThreads;

        // Prime Finder Threads
        Thread[] primeThreads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            int threadStart = start + (i * rangePerThread);
            int threadEnd = (i == numThreads - 1) ? end : threadStart + rangePerThread - 1;
            primeThreads[i] = new Thread(new PrimeFinder(threadStart, threadEnd));
            primeThreads[i].start();
        }

        // Palindrome Finder Threads
        Thread[] palindromeThreads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            int threadStart = start + (i * rangePerThread);
            int threadEnd = (i == numThreads - 1) ? end : threadStart + rangePerThread - 1;
            palindromeThreads[i] = new Thread(new PalindromeFinder(threadStart, threadEnd));
            palindromeThreads[i].start();
        }

        // Wait for all prime threads to finish
        for (Thread thread : primeThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Wait for all palindrome threads to finish
        for (Thread thread : palindromeThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Prime and Palindrome search completed.");
    }
}
