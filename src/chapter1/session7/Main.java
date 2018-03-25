package chapter1.session7;

/**
 * Processing Uncontrolled Exceptions in a Thread
 * Unchecked exception mechanism
 *   - catch
 *   - Treat
 */
public class Main {
    public static void main(String[] args) {
        // Create the task
        Task task = new Task();
        // Create new Thread(task)
        Thread thread = new Thread(task);
        // Sets de uncaught exception handler
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        // Starts the Thread
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread has finished");
    }
}
