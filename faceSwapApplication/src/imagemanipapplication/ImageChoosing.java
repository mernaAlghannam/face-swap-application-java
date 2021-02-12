/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagemanipapplication;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;

/**
 * javadoc ink -> file:///C:/Users/merna/Desktop/JAVA%20School/faceSwapApplication/dist/javadoc/index.html
 * @author merna
 */
public class ImageChoosing implements Comparable<ImageChoosing> {

    private int scaledWidth;    // the chose width of image
    private int scaledHeight;   // the chsoen height of image
    private String outputImage; // the resized image file
    private String savedImage;  // the manipulated image files
    private FileChooser open = new FileChooser();   // file chooser to load image
    private FileChooser save = new FileChooser();   // file chooser to save image
    private BufferedImage image;

    ImageChoosing(String savedImage) throws IIOException, IOException {
        this.savedImage = savedImage;
        //Set extension filter for the saved file chooser to only jpg
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        save.getExtensionFilters().addAll(extFilterJPG);
        saveImage();
    }

    ImageChoosing(int scaledWidth, int scaledHeight, String outputImage, String savedImage) {
        this.scaledWidth = scaledWidth;
        this.scaledHeight = scaledHeight;
        this.outputImage = outputImage;
        this.savedImage = savedImage;
        //Set extension filter for the loaded image to only jpg
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        open.getExtensionFilters().addAll(extFilterJPG);
    }
    /**
     * 
     * @return width
     */
    public int getWidth() {
        return scaledWidth;
    }
    
    /**
     * 
     * @return height
     */
    public int getHeight() {
        return scaledHeight;
    }
    
    /**
     * 
     * @return image name
     */
    public String getImageName() {
        return outputImage;
    }

    /**
     * this retrieves an image from the computer using file chooser
     * @return the chosen image
     */
    public ImageView getImage() {
        ImageView old1 = new ImageView();
        try {
            File file1 = open.showOpenDialog(null);
            
            //resizes the image
            resize(file1.toString(), outputImage, scaledWidth, scaledHeight);
            
            //displays teh image in teh image pane 
            File newOut1 = new File(outputImage);
            Image imageTwo = new Image(newOut1.toURI().toString());
            old1.setImage(imageTwo);
        } catch (IOException ex) {
            System.out.println("Error resizing the image.");
            ex.printStackTrace();
        }

        return old1;
    }

    /**
     *  resizes the image with the desired width and height
     * @param inputImagePath
     * @param outputImagePath
     * @param scaledWidth
     * @param scaledHeight
     * @throws IOException
     */
    public void resize(String inputImagePath,
            String outputImagePath, int scaledWidth, int scaledHeight) throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outImage, formatName, new File(outputImagePath));
    }
    /**
     *  saves the manipulated image to any file of your desire using file chooser
     * @throws IOException 
     */
    public void saveImage() throws IOException {

        File file1 = save.showSaveDialog(null);
        File old1 = new File(savedImage);
        image = ImageIO.read(old1);
        ImageIO.write(image, "jpg", file1);

    }
    /**
     *  a demonstration of comparable interfaces
     * @param t
     * @return the comparison between to object of type image chooser by comparing its width
     */
    @Override
    public int compareTo(ImageChoosing t) {
        ImageChoosing other = (ImageChoosing) t;
        return scaledWidth > other.scaledWidth ? 1 : -1;
    }
}
