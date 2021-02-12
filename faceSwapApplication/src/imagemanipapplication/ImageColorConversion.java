/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagemanipapplication;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author merna
 */
public class ImageColorConversion{

    private String edited1;     // resized image
    private String outImg1; // manipulated image
    private BufferedImage img = null;
    private File f = null;
    
    //this would add the image that will be mainpulated to bufferedImage img
    ImageColorConversion(String edited1, String outImg1) {
        this.edited1 = edited1;
        this.outImg1 = outImg1;

        //read image
        try {
            f = new File(edited1);
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /**
     * this would change the pixel colors in the image to a shade of blue manually
     */
    public void loadBlueImage() {

        //get image width and height
        int width = img.getWidth();
        int height = img.getHeight();
        
        //loops through the pixels and changes the pixel color to a shade of blue
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int b = p & 0xff;

                //set new RGB
                p = (a << 24) | (0 << 16) | (0 << 8) | b;

                img.setRGB(x, y, p);
            }
        }
        //this writes the pixels into the manipulated image
        try {
            f = new File(outImg1);
            ImageIO.write(img, "jpg", f);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
