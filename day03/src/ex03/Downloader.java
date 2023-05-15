package ex03;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Downloader {
    private static final int BUFFSIZE = 1024;
    private final String fileOfUrls_ = "day03/src/ex03/files_urls2.txt";
    private final String pathResult_ = "day03/src/ex03/download/";

    private final List<String> listUrls_ = new ArrayList<>();

    void txtToList() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileOfUrls_))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Collections.addAll(listUrls_, line.substring(2));
            }
//                for (String s : listUrls_)
//                System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void download(int i) throws IOException {
        URL fLoad = new URL(listUrls_.get(i));
        Path p = Paths.get(fLoad.getFile());
        String fName = p.getFileName().toString();
        String nameResult_ = pathResult_ + fName;

        try {
            downloadUsingStream(fLoad, nameResult_); // Variant N1 (option)
//            downloadUsingNIO(fLoad, result); // Variant N2 (option)
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Variant N1
    private void downloadUsingStream(URL fLoad, String result) throws IOException {
        BufferedInputStream in = new BufferedInputStream(fLoad.openStream(), BUFFSIZE);
        BufferedOutputStream fout = new BufferedOutputStream(new FileOutputStream(result));
        byte[] buffer = new byte[BUFFSIZE];
        int count;
        while ((count = in.read(buffer)) != -1) {
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



