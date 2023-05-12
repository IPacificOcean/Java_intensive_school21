package ex01;

public class Validation {
    public int check(String[] args) {
     int n = 0;
    if((args.length >= 1) && args[0].startsWith("--count=")) {
        try {
          n = Integer.parseInt(args[0].substring("--count=".length()));
        } catch (NumberFormatException x) {
            System.err.println("illegal argument, must be an integer. " + x);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        System.err.println("too few arguments or error of input");
        System.exit(-1);
    }
          return n;
    }
}
