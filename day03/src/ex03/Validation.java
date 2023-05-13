package ex03;

public class Validation {
    private int size_;
    private int threadCount_;

    public int getSize_() {
        return size_;
    }

    public int getThreadCount_() {
        return threadCount_;
    }

    void check(String[] args) {
        if ((args.length >= 1) && args[0].startsWith("--arraySize=") && args[1].startsWith("--threadCount=")) {
            try {
                size_ = Integer.parseInt(args[0].substring("--arraySize=".length()));
                threadCount_ = Integer.parseInt(args[1].substring("--threadCount=".length()));
                if (size_ > 2000000 || size_ < 0 || threadCount_ > size_ || threadCount_ < 0) {
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
