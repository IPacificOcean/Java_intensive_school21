package ex03;

public class Program {
public static void main(String[] args) throws Exception {
    Validation v = new Validation();
    v.check(args);
    int threadCount = v.getThreadCount_();

    Downloader d = new Downloader();
    d.download();

}

}
