package chapter1.session5;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetworkConnectionLoader implements Runnable {
    @Override
    public void run() {
        System.out.println("NetworkConnectionLoader Beginning data sources loading : " + new Date());

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("NetworkConnectionLoader Data sources loading has finished : " + new Date());
    }
}
