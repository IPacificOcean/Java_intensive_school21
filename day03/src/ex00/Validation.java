package ex00;

public class Validation {
    int check (String[] args) {
     int n = 0;
    if((args.length >= 1) && args[0].startsWith("--count=")) {
        try {
          n = Integer.parseInt(args[0].substring("--count=".length()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        System.err.println("too few arguments or error of input");
        System.exit(-1);
    }
          return n;
    }
}
