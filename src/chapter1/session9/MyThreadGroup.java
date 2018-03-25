package chapter1.session9;

public class MyThreadGroup extends ThreadGroup {

    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("The thread " + t.getId() + " has throw an Exception");
        e.printStackTrace(System.out);
        System.out.println("Terminating the rest of the Threads");
        interrupt();
    }
}
