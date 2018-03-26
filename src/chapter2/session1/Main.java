package chapter2.session1;

public class Main {
    public static void main(String[] args) {
        ParkingCash cash = new ParkingCash();
        ParkingStats stats = new ParkingStats(cash);

        System.out.println("Parking simulator");

        int numberSensors = 2* Runtime.getRuntime().availableProcessors();
        Thread threads[] = new Thread[numberSensors];

        for (int i=0; i<numberSensors;i++) {
            Sensor sensor = new Sensor(stats);
            Thread thread = new Thread(sensor);
            thread.start();
            threads[i] = thread;
        }

        for (int i=0; i<numberSensors;i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Number of cars: " + stats.getNumberCars());
        System.out.println("Number of motorcycles: " + stats.getNumberMotorcycles());
        cash.close();
    }
}
