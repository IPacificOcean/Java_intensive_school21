package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;


public class ConvertImgToConsoleView {

    private String  white_;

    private String  black_;
    private final String  pathToImg_;

    public ConvertImgToConsoleView(Validation v, String pathToImg) {
        this.white_ = v.getWhite_();
        this.black_ = v.getBlack_();
        this.pathToImg_ = Paths.get(pathToImg).toAbsolutePath().normalize().toString();
    }

     public void convertAndOutputInConsole() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File((pathToImg_)));
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();
        printImg(height, width, bufferedImage);
    }

    /* setting for java.bmp
    *   for (int i = 0; i < height; i+=5) {
            for (int j = 0; j < width; j+=3) {
    * */
    private void  printImg(int height, int width, BufferedImage bi) {
        int blackColor = 0xFF000000;

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int color = bi.getRGB(j, i);
                if (color == blackColor) {
                    System.out.print('#');
                } else {
                    System.out.print('_');
                }
            }
            System.out.println();
        }
    }
}
