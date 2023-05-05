package ex01;


public class Program {
    public static void main(String[] args) {
    String textA = "day02/src/ex01/textA.txt";
    String textB = "day02/src/ex01/textB.txt";

    CosineSimilarityOfTexts cosineSimilarityOfTexts = new CosineSimilarityOfTexts(textA, textB);
    cosineSimilarityOfTexts.startApp();

//        try (BufferedReader reader = new BufferedReader(new FileReader("day02/src/ex01/textA.txt"))) {
//            List<String> chunks = new ArrayList<>();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                for (String chunk : line.split("\\W+")) {
//                    chunks.add(chunk);
//                }
//            }
//            reader.close();
//            System.out.println(chunks);
//        } catch (IOException e) {
//        }
    }



}
