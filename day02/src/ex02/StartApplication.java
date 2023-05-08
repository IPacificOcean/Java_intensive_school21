package ex02;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StartApplication {

    Path path_;
    private final commandActions action_ = new commandActions();

    public StartApplication(String workdir) {
        path_ = Paths.get(workdir).toAbsolutePath().normalize();
    }

    void start() {
        System.out.println(path_);
        action_.setPath_(path_);
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

    void collCommand(String[] command) throws IOException {
        switch (command[0]) {
            case "ls":
                action_.lsCommand(command);
                break;
            case "mv":
                action_.mvCommand(command);
                break;
            case "cd":
                action_.cdCommand(command);
                break;
            default:
                throw  new RuntimeException(command[0] + " command undetected");
        }
    }
}
