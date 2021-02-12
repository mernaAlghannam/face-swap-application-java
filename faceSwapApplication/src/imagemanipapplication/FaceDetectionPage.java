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
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author merna
 */
public class FaceDetectionPage extends Pages {
    private final Font font = new Font(20);
    
    /**
     * 
     * @param inImg
     * @param outImg
     * @param imagesPane 
     */
    FaceDetectionPage(String inImg, String outImg, GridPane imagesPane){
        super(inImg, outImg, imagesPane); // calls the super class
    }
    /**
     * 
     * @return the face detection button
     */
    @Override
    public Button getButton1(){
        Button detect = new Button("     Detect    ");
        detect.setFont(font);
        detect.setStyle("-fx-background-color: Yellow");
        //calls the face detection function to detect the faces
        detect.setOnAction(e -> {
            // the file with the pretrained face detection model
            String xmlFile = 
                    "C:\\Users\\merna\\Desktop\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml";
            CascadeClassifier classifier = new CascadeClassifier(xmlFile);
            
            //calls it here
            FaceDetector face1 = new FaceDetector(inImg, outImg, classifier);
            face1.detectAndDrawFace();
        });
        
        return detect;
    }
    
    /**
     * 
     * @return the show function that woudl show the manipulated image
     */
    @Override
    public Button getButton2(){
        Button show = new Button("      show     ");
        show.setFont(font);
        show.setStyle("-fx-background-color: Yellow");
        
        
        // when you click on the button, it would create an 
        //imageview of the specified file area and shows the image
        show.setOnAction(e -> {
            try {
                File img1 = new File(outImg);
                ImageView newStuff1 = new ImageView(img1.toURI().toString());
                imagesPane.add(newStuff1, 5, 3);

            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        });
        
        return show;
    }
    
    /**
     * 
     * @return the tile of the face detection page
     */
    @Override
    public Text getTitle(){
        Text title = new Text("Face Detection");
        title.setFont(Font.font("Chiller", FontWeight.BOLD, FontPosture.ITALIC, 30));
        title.setFill(Color.DARKGREY);
        
        return title;
    }
    
    /**
     * 
     * @return the description of the face detection page
     */
    @Override
    public Text getDiscription(){
        Text discription = new Text("Choose one photos from file and detect the faces in i. "
                + "\nYou must click detect then show to show the image with the detected faces.");
        discription.setFont(Font.font("New Time Roman", FontPosture.ITALIC, 10));
        discription.setFill(Color.DARKGREY);
        
        return discription;
    } 
}
