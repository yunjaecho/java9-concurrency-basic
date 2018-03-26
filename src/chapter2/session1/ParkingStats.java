package chapter2.session1;

public class ParkingStats {

    private long numberCars;
    private long numberMotorcycles;

    private ParkingCash cash;

    private final Object controlCars, controlMotorcycles;

    public ParkingStats(ParkingCash cash) {
        this.numberCars = 0;
        this.numberMotorcycles = 0;
        this.cash = cash;
        controlCars = new Object();
        controlMotorcycles = new Object();
    }

    public void carComeIn() {
        synchronized (controlCars) {
            numberCars++;
        }
    }

    public void carGoOut() {
        synchronized (controlCars) {
            numberCars--;
        }
        cash.vehiclePay();
    }

    public void motoComeIn() {
        synchronized (controlMotorcycles) {
            numberMotorcycles++;
        }
    }

    public void motoGoOut() {
        synchronized (controlMotorcycles) {
            numberMotorcycles--;
        }
        cash.vehiclePay();
    }

    public long getNumberCars() {
        synchronized (controlCars) {
            return numberCars;
        }

    }

    public long getNumberMotorcycles() {
        synchronized (controlMotorcycles) {
            return numberMotorcycles;
        }

    }
}
