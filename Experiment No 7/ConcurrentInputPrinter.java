import java.util.Scanner;

class InputHandler extends Thread {
    private volatile boolean running = true;

    // Method to stop the printing thread
    public void stopPrinting() {
        running = false;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("InputHandler: Waiting for user input...");
        scanner.nextLine(); // Waits for the user to press Enter
        stopPrinting(); // Stops the printing thread once input is given
        System.out.println("InputHandler: User input received. Stopping the printing thread.");
        scanner.close();
    }

    public boolean isRunning() {
        return running;
    }
}

class ConsolePrinter extends Thread {
    private InputHandler inputHandler;

    public ConsolePrinter(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public void run() {
        while (inputHandler.isRunning()) {
            System.out.println("ConsolePrinter: Printing...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ConsolePrinter: Stopped printing as user input is received.");
    }
}

public class ConcurrentInputPrinter {
    public static void main(String[] args) {
        // Create the InputHandler object
        InputHandler inputHandler = new InputHandler();

        // Create the ConsolePrinter object and pass the InputHandler reference
        ConsolePrinter consolePrinter = new ConsolePrinter(inputHandler);

        // Start both threads
        inputHandler.start();
        consolePrinter.start();
    }
}
