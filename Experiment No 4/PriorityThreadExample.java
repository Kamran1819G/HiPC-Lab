// Main class to demonstrate thread priorities with a real task
class PriorityThreadExample extends Thread {

    // Constructor to set thread name
    public PriorityThreadExample(String name) {
        super(name);
    }

    // run() method for the thread that is called when start() is invoked
    public void run() {
        // Print the current thread executing the run method
        System.out.println("Executing run method of thread: " + Thread.currentThread().getName());

        // Simulating a time-consuming task (e.g., loop over a large range)
        for (int i = 1; i <= 1000000; i++) {
            if (i % 100000 == 0) {
                System.out.println(getName() + " has completed " + (i / 10000) + "% of the task");
            }
        }

        // Indicating that the thread has completed its task
        System.out.println(getName() + " has finished execution.");
    }

    // Main driver method
    public static void main(String[] args) {
        // Creating threads with meaningful names
        PriorityThreadExample thread1 = new PriorityThreadExample("Thread-1");
        PriorityThreadExample thread2 = new PriorityThreadExample("Thread-2");
        PriorityThreadExample thread3 = new PriorityThreadExample("Thread-3");

        // Displaying the default priority of threads
        System.out.println("Thread 1 default priority: " + thread1.getPriority());
        System.out.println("Thread 2 default priority: " + thread2.getPriority());
        System.out.println("Thread 3 default priority: " + thread3.getPriority());

        // Setting custom priorities for threads
        thread1.setPriority(2); // Lower priority
        thread2.setPriority(5); // Normal priority
        thread3.setPriority(8); // Higher priority

        // Display the updated priority of threads
        System.out.println("Updated Thread 1 priority: " + thread1.getPriority());
        System.out.println("Updated Thread 2 priority: " + thread2.getPriority());
        System.out.println("Updated Thread 3 priority: " + thread3.getPriority());

        // Start the threads to observe their execution behavior
        thread1.start();
        thread2.start();
        thread3.start();

        // Display main thread's current state
        System.out.println("Currently executing thread: " + Thread.currentThread().getName());
        System.out.println("Main thread default priority: " + Thread.currentThread().getPriority());

        // Set and display updated main thread priority
        Thread.currentThread().setPriority(10);
        System.out.println("Updated Main thread priority: " + Thread.currentThread().getPriority());

        // Main thread doing some work too
        for (int i = 1; i <= 500000; i++) {
            if (i % 100000 == 0) {
                System.out.println("Main thread has completed " + (i / 5000) + "% of its task");
            }
        }

        System.out.println("Main thread has finished execution.");
    }
}
