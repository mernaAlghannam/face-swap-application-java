/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagemanipapplication;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author merna
 */
public abstract class Pages {

    String inImg; // resized image
    String outImg;  // manipulated image
    GridPane imagesPane = new GridPane();
    
    /**
     * 
     * @param inImg
     * @param outImg
     * @param imagesPane 
     */
    Pages(String inImg, String outImg, GridPane imagesPane) {
        this.inImg = inImg;
        this.outImg = outImg;
        this.imagesPane = imagesPane;
    }

    //the abstract function that would be used in other classes inheriting from pages
    abstract public Button getButton1();

    abstract public Button getButton2();

    abstract public Text getTitle();

    abstract public Text getDiscription();
}
