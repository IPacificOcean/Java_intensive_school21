package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConvertImgToConsoleView {
    private final char  white_;
    private final char  black_;
    private final String  pathToImg_;

    public ConvertImgToConsoleView(String[] args, String pathToImg) {
        this.white_ = args[0].charAt(0);
        this.black_ = args[1].charAt(0);
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
                    System.out.print(black_);
                } else {
                    System.out.print(white_);
                }
            }
            System.out.println();
        }
    }
}
