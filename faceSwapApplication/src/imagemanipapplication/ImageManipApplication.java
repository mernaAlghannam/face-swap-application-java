/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagemanipapplication;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.opencv.core.Core;
import javafx.scene.shape.*;
//import org.opencv.face.*;

/**
 *
 * @author merna
 */
public class ImageManipApplication extends Application {
    
    // calls the opencv library
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
   static GridPane processPane = new GridPane();
   static GridPane imagesPane = new GridPane();
    @Override
    public void start(Stage primaryStage) {

        BorderPane main = new BorderPane(); //the pane of all the pages
        
        //strings of the file areas the final photo is going to be saved in
        // you might need to change the uri
        String outputImagePath1 = "C:\\Users\\merna\\Desktop\\Merna Alghannam Final project\\faceSwapApplication\\src\\image\\photo_fixed.jpg";
        String newImg1 = "C:\\Users\\merna\\Desktop\\Merna Alghannam Final project\\faceSwapApplication\\src\\image\\photo_output.jpg";
        
        //the pane of the top border pane - set to a color
        GridPane titlePane = new GridPane();
        titlePane.setAlignment(Pos.CENTER);
        main.setTop(titlePane);
        titlePane.setBackground(new Background(new BackgroundFill(Color.rgb(57, 115, 172), CornerRadii.EMPTY, Insets.EMPTY)));
       
        // add the two input and output images       
        imagesPane.setAlignment(Pos.CENTER);
        main.setCenter(imagesPane);
        imagesPane.setBackground(new Background(new BackgroundFill(Color.rgb(112, 128, 144), CornerRadii.EMPTY, Insets.EMPTY)));
        
        
        // add the buttons for the process
        main.setLeft(processPane);
        processPane.setAlignment(Pos.CENTER);
        processPane.setBackground(new Background(new BackgroundFill(Color.rgb(40, 60, 80), CornerRadii.EMPTY, Insets.EMPTY)));
        
        // sets the backgrounds of the images - black
        Rectangle backGround1 = new Rectangle(350, 400);
        backGround1.setFill(Color.BLACK);
        backGround1.setStroke(Color.rgb(112, 128, 144));
        imagesPane.add(backGround1, 3, 3);
        Rectangle backGround2 = new Rectangle(350, 400);
        backGround2.setFill(Color.BLACK);
        backGround2.setStroke(Color.rgb(112, 128, 144));
        imagesPane.add(backGround2, 5, 3);
        
        // Calls the image process page - add another vbox with an extra menu and hides it
        ImageMorphingPage imageProcess = new ImageMorphingPage(outputImagePath1, newImg1, imagesPane);
        
        // adds the boxes for the titles and description of the image process page - it is hidden for now
        HBox introImg = new HBox();
        introImg.getChildren().addAll(imageProcess.getTitle(), imageProcess.getDiscription());
        introImg.setVisible(false);
        titlePane.add(introImg, 2, 1);
                    
        //calls the login class to add your info and then adds it to the left pane
        Login login = new Login();
        processPane.add(login.getLoginPage(), 1, 7);

        //create a dropdown menu to choose either image manip or face detection
        MenuBar saveMenu = new MenuBar();
        Menu choosePage = new Menu("Choose Page");
        saveMenu.getMenus().add(choosePage);
        titlePane.add(saveMenu, 7, 1);
        
        //sets teh menu choices - face detection page and image process page
        MenuItem detection = new MenuItem("Face Detection");
        MenuItem imageProcessing = new MenuItem("Image Processing");
        
        choosePage.getItems().addAll(detection, imageProcessing);
        
        // this is only for when you first ente the application, you would see the face detection page first 
        // unless chosen otherwise
        FaceDetectionPage detectPage = new FaceDetectionPage(outputImagePath1, newImg1, imagesPane);
        HBox introFace = new HBox();
        introFace.getChildren().addAll(detectPage.getTitle(), detectPage.getDiscription());
        introFace.setVisible(true);
        titlePane.add(introFace, 2, 1);

        processPane.add(detectPage.getButton1(), 1, 2);
        processPane.add(detectPage.getButton2(), 1, 4);
        
        // if you chose the facet dection page it woud=ld set the title info to visible and shows all the other
        // things in it
        detection.setOnAction(e -> {
            introFace.setVisible(true);
            introImg.setVisible(false);
            
            showPage(detectPage);
        });
        
        // if you chose the image processing page it woud=ld set the title info to visible and shows all the other
        // things in it
        imageProcessing.setOnAction(e -> {
            introImg.setVisible(true);
            introFace.setVisible(false);
            
            showPage(imageProcess);
        });
        
        // the menu taht would allow you to load and save an image
        LoadSaveMenu slmenu = new LoadSaveMenu(outputImagePath1,newImg1, imagesPane);
        titlePane.add(slmenu.getMenu(), 8, 1);

        Scene scene = new Scene(main, 900, 500, Color.BLUE);

        primaryStage.setTitle("Image Manipulation App"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage 
        primaryStage.show(); // Display the stage 
    }
    /**
     * shows an example of polymorphism by printing the pages
     * @param page 
     */
    public static void showPage(Pages page){
        processPane.add(page.getButton1(), 1, 2);
        processPane.add(page.getButton2(), 1, 4);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
