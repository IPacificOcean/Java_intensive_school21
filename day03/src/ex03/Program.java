package ex03;

import java.util.concurrent.TimeUnit;

public class Program {
    public static void main(String[] args) throws Exception {
        Validation v = new Validation();
        v.check(args);
        int threadCount = v.getThreadCount_();

        Downloader d = new Downloader();
        d.txtToList();
        for (int i = 0; i < 8; ++i) {
//            TimeUnit.SECONDS.sleep(5);
            d.download(i);
        }

    }

}
