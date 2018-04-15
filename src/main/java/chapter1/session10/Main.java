package chapter1.session10;

public class Main {
    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("My ThreadFactory");
        Task task = new Task();

        Thread thread;

        System.out.println("Starting the Threads");
        for (int i=0; i<10; i++) {
            thread = factory.newThread(task);
            thread.start();
        }

        System.out.println("Factory stats");
        System.out.println(factory.getStats());
    }
}
