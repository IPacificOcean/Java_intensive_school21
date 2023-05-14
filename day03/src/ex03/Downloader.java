package ex03;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Downloader {

    void download() throws IOException {
        URL fLoad = new URL("https://www.sli.komi.com/files/stipendii/instruction_word_to_pdf.pdf");
        String fNameUrl = fLoad.getFile();
        Path p = Paths.get(fNameUrl);
        String fName = p.getFileName().toString();
        String result = "day03/src/ex03/" + fName;

        try {
//            downloadUsingStream(fLoad, result); // Variant N1 (option)
            downloadUsingNIO(fLoad, result); // Variant N2 (option)
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
// Variant N1
    private void downloadUsingStream(URL fLoad, String result) throws IOException {
        BufferedInputStream in = new BufferedInputStream(fLoad.openStream());
        FileOutputStream fout = new FileOutputStream(result);
        byte[] buffer = new byte[1024];
        int count;
        while ((count = in.read(buffer, 0, 1024)) != -1) {
            fout.write(buffer, 0, count);
        }
        in.close();
        fout.close();
    }

    // Variant N2
   private void downloadUsingNIO(URL fLoad, String result) throws IOException {
        ReadableByteChannel rbc = Channels.newChannel(fLoad.openStream());
        FileOutputStream fos = new FileOutputStream(result);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        rbc.close();
        fos.close();
    }
}



