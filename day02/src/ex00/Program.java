package ex00;

public class Program {
    public static void main(String[] args) {
        String in = "day02/src/ex00/signatures.txt";
        String result = "day02/src/ex00/result.txt";

        CheckExtension checkExtension = new CheckExtension(in, result);
        checkExtension.startApp();

    }
}

//        day02/src/ex00/pay.pdf
//        day02/src/ex00/image.png
//        day02/src/ex00/bubl.gif