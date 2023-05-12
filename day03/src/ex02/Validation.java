package ex02;

public class Validation {
    int size_ = 0;
    int threadCount_ = 0;

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
