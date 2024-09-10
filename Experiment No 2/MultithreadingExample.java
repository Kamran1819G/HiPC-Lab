class WorkerThread extends Thread {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is running");
        } catch (Exception e) {
            System.out.println("Exception is caught");
        }
        System.out.println(Thread.currentThread().getName() + " is finished");
    }
}

public class MultithreadingExample {
    public static void main(String[] args) {
        int numberOfThreads = 5000;
        for (int i = 0; i < numberOfThreads; i++) {
            WorkerThread thread = new WorkerThread();
            thread.start();
        }
    }
}
