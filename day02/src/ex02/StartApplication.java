package ex02;

import java.io.*;

public class StartApplication {

    void start(String workdir) {
        System.out.println(workdir);
        input();
    }

    void input() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line;
            String string;
            while (((string = br.readLine()) != null)) {
                if (string.equals("exit")) break;
                line = string.trim().split("\\s");
                try {
                    collCommand(line);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void collCommand(String[] comand) {
        switch (comand[0]) {
            case "ls":
                System.out.println("ls");
                break;
            case "mv":
                System.out.println("mv");
                break;
            case "cd":
                System.out.println("cd");
                break;
            default:
                throw  new RuntimeException(comand[0] + " command undetected");
        }
    }
}
