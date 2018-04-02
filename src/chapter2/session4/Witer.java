package chapter2.session4;

import java.util.Date;

public class Witer implements Runnable {

    private PricesInfo pricesInfo;

    public Witer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i=0; i<3; i++) {
            System.out.printf("%s: Writer Attempt to modify the prices.\n", new Date());
            pricesInfo.setPrices(Math.random()*10, Math.random() * 8);
            System.out.printf("%s: Writer: Prices have bean modified\n", new Date());
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
