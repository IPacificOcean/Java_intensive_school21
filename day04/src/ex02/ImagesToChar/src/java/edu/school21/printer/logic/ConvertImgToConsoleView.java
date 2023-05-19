package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.FColor;


public class ConvertImgToConsoleView {

    private final String  white_;

    private final String  black_;
    private final String  pathToImg_;

    public ConvertImgToConsoleView(ParserCL pcl, String pathToImg) {
        this.white_ = pcl.getWhite_();
        this.black_ = pcl.getBlack_();
        this.pathToImg_ = Paths.get(pathToImg).toAbsolutePath().normalize().toString();
    }

    public void convertAndOutputInConsole() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File((pathToImg_)));

        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();

        printImg(height, width, bufferedImage);
    }

    private void  printImg(int height, int width, BufferedImage bi) {
        ColoredPrinter foreground =
                new ColoredPrinter.Builder(1, false).foreground(FColor.valueOf(black_)).build();

        ColoredPrinter background =
                new ColoredPrinter.Builder(1, false).foreground(FColor.valueOf(white_)).build();
        int blackColor = 0xFF000000;
        System.out.println(white_ + " " + black_);

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int color = bi.getRGB(j, i);
                if (color == blackColor) {
                    foreground.print('\u2588');
                } else {
                    background.print('\u2588');
                }
            }
            System.out.println();
        }
    }
}
