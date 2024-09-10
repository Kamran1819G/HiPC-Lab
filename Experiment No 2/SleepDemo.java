class SleepDemo extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                // Pause the current thread for 1000 milliseconds (1 second)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        SleepDemo t1 = new SleepDemo();
        t1.start(); // Start the thread
    }
}
