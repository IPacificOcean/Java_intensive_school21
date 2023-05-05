package ex01;


public class Program {
    public static void main(String[] args) {
        String textA;
        String textB;
        String dictPath = "day02/src/ex01/Dictionary.txt";
//        String textA = "day02/src/ex01/textA.txt";
//        String textB = "day02/src/ex01/textB.txt";
        if (args.length < 2) {
            System.out.println("too few arguments");
            System.exit(-1);
        } else {
            textA = args[0];
            textB = args[1];
            CosineSimilarityOfTexts cosineSimilarityOfTexts = new CosineSimilarityOfTexts(textA, textB, dictPath);
            cosineSimilarityOfTexts.startApp();
        }

    }
}
