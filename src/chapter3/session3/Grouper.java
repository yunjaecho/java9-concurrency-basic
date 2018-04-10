package chapter3.session3;

public class Grouper implements Runnable {

    private final Results results;

    public Grouper(Results results) {
        this.results = results;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.println("Grouper: processing results");
        int data[] = results.getData();

        for (int number: data) {
            finalResult+=number;
        }
        System.out.println("Grouper : Total result : " + finalResult);
    }
}
