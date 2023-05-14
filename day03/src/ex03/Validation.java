package ex03;

public class Validation {
    private int threadCount_;


    public int getThreadCount_() {
        return threadCount_;
    }

    void check(String[] args) {
        if ((args.length >= 1) && args[0].startsWith("--threadCount=")) {
            try {
                threadCount_ = Integer.parseInt(args[0].substring("--threadCount=".length()));
                if (threadCount_ <= 0) {
                    System.err.println("incorrect arguments");
                    System.exit(-1);
                }
            } catch (NumberFormatException x) {
                System.err.println("illegal argument, must be an integer. " + x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("too few arguments or error of input");
            System.exit(-1);
        }
    }
}
