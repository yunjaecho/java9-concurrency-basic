package chapter1.session7;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    /**
     * UnsafeMain method of the class.
     * It process the uncaught exceptions throwed in a Thread
     * @param t
     *          The Thread than throws the Exception
     * @param e
     *          The Exception throwed
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("An exception has been captured");
        System.out.println("Thread :" + t.getId());
        System.out.println("Exception : " + e.getClass().getName() + " : " + e.getMessage());
        System.out.println("Stack Trace : ");
        e.printStackTrace(System.out);
        System.out.println("Thread status : " + t.getState());
    }
}
