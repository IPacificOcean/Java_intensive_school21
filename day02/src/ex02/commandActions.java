package ex02;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class commandActions {
    private Path path_;

    public void setPath_(Path path) {
        path_ = path;
    }

    void lsCommand(String[] actions) {
        if (actions.length != 1) {
            throw  new RuntimeException(actions[0] + ": incorrect number of arguments");
        }
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path_)) {
            stream.forEach((f) -> {
                try {
                    long size;
                    if (Files.isDirectory(f)) {
                        size = directorySize(f);
                    } else {
                        size = Files.size(f);
                    }
                    System.out.printf("%s %d KB\n", f.getFileName(), size);
                } catch (Exception e) {
                    System.err.println("it is not possible to get the size");
                }
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    void cdCommand(String[] actions) {
        if (actions.length != 2) {
            throw  new RuntimeException(actions[0] + ": incorrect number of arguments");
        }
        Path toDirectory = path_.resolve(Paths.get(actions[1]));
        if (Files.isDirectory(toDirectory)) {
            path_ = toDirectory.normalize();
            System.out.println(path_);
        } else {
            throw  new RuntimeException(actions[0] + ": no such directory");
        }
    }

    void mvCommand(String[] actions) throws IOException {
        if (actions.length != 3) {
            throw new RuntimeException(actions[0] + ": incorrect number of arguments");
        }
        Path fileName = path_.resolve(Paths.get(actions[1]));
        Path dest = path_.resolve(Paths.get(actions[2]));
        if (!Files.exists(fileName) || !Files.isDirectory(dest.getParent())) throw new RuntimeException(actions[0] + ": No such file or directory");
        Files.move(fileName, dest, REPLACE_EXISTING);
    }

    private long directorySize(Path folder) {
        long size = 0L;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
            for (Path p : stream) {
                if (Files.isDirectory(p)) {
                    size += directorySize(p);
                } else {
                    size += Files.size(p);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return size;
    }
}
