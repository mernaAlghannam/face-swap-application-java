/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagemanipapplication;

import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author merna
 */
public class ImageMorphingPage extends Pages {

    private final Font font = new Font(20); // font used later
    
    // constructor for image morphing pages
    ImageMorphingPage(String inImg, String outImg, GridPane imagesPane) {
        super(inImg, outImg, imagesPane);
    }
    /**
     * shows the image processed image in the image pane
     * @return shows the image processed image
     */
    @Override
    public Button getButton1() {
        Button show = new Button("    Show       ");

        show.setFont(font);
        show.setStyle("-fx-background-color: Yellow");
        show.setOnAction(e -> {
            try {
                File img1 = new File(outImg);
                ImageView newStuff1 = new ImageView(img1.toURI().toString());   //creates an image view of the manip image
                imagesPane.add(newStuff1, 5, 3);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        });
        return show;
    }
    
    /**
     * 
     * @return the pixel manip button to begin to manip function
     */
    @Override
    public Button getButton2() {
        Button pixelManip = new Button(" Blue Color ");
        pixelManip.setFont(font);
        pixelManip.setStyle("-fx-background-color: Yellow");
        
        // when pressed it called the function that chnages all the pixels to a blue shade
        pixelManip.setOnAction(e -> {
            ImageColorConversion sortImg = new ImageColorConversion(inImg, outImg);
            sortImg.loadBlueImage();
        });

        return pixelManip;
    }

    /**
     *
     * @return tile of image sort page
     */
    @Override
    public Text getTitle() {
        Text title = new Text("Image Processing");
        title.setFont(Font.font("Chiller", FontWeight.BOLD, FontPosture.ITALIC, 30));
        title.setFill(Color.DARKGREY);
        return title;
    }

    /**
     *
     * @return description of the image processing page
     */
    @Override
    public Text getDiscription() {
        Text discription = new Text("Choose one photo from file and change"
                + " its pixels shade to blue. ");
        discription.setFont(Font.font("New Time Roman", FontPosture.ITALIC, 10));
        discription.setFill(Color.DARKGREY);
        return discription;
    }
}
