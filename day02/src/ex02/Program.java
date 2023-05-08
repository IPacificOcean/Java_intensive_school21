package ex02;

public class Program {
    public static void main(String[] args) {
        if((args.length >= 1) && args[0].startsWith("--current-folder=")) {
            String workdir = args[0].substring("--current-folder=".length());
            StartApplication startApp = new StartApplication(workdir);
            startApp.start();
        } else {
            System.err.println("too few arguments or error of input");
        }
    }
}
