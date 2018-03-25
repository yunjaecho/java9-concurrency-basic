package chapter1.session7;

public class Task implements Runnable {

    /**
     * UnsafeMain method of the class
     */
    @Override
    public void run() {
        // The next instruction always throws and exception
        int number = Integer.parseInt("TTT");
        // This sentence will never be executed
        System.out.println("number : " + number);
    }
}
