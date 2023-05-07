package ex02;

import java.io.*;

public class StartApplication {
    commandActions action = new commandActions();

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

    void collCommand(String[] command) {
        switch (command[0]) {
            case "ls":
                action.lsCommand(command);
                break;
            case "mv":
                action.mvCommand(command);
                break;
            case "cd":
                action.cdCommand(command);
                break;
            default:
                throw  new RuntimeException(command[0] + " command undetected");
        }
    }
}
