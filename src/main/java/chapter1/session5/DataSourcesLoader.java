package chapter1.session5;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSourcesLoader implements Runnable {
    @Override
    public void run() {
        System.out.println("DataSourcesLoader Beginning data sources loading :" + new Date());

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("DataSourcesLoader Data sources loading has finished : " + new Date());
    }
}
