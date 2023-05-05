package ex01;


public class Program {
    public static void main(String[] args) {
        String dictPath = "day02/src/ex01/Dictionary.txt";
        if (args.length < 2) {
            System.err.println("too few arguments");
            System.exit(-1);
        }
        CosineSimilarityOfTexts cosineSimilarityOfTexts = new CosineSimilarityOfTexts(args[0], args[1], dictPath);
        cosineSimilarityOfTexts.startApp();
    }
}
