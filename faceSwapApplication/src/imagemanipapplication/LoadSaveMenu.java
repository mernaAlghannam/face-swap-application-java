/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagemanipapplication;

import java.io.IOException;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javax.imageio.IIOException;

/**
 *
 * @author merna
 */
public class LoadSaveMenu {
    private String input1;  // resized image
    private String outImg1; //manipulated image
    private GridPane imagesPane = new GridPane();
    
    LoadSaveMenu(String input1, String outImg1, GridPane imagesPane){
        this.input1 = input1;
        this.outImg1 = outImg1;
        this.imagesPane = imagesPane;
    }
    
    /**
     * retrieves the load and save menu
     * @return main
     */
    public MenuBar getMenu(){
        //the menu with its menu items - load and save image
        MenuBar main = new MenuBar();
        Menu saveLoad = new Menu("Save or Load");
        main.getMenus().add(saveLoad);

        MenuItem load = new MenuItem("Load Image");
        MenuItem save = new MenuItem("Save Image");

        saveLoad.getItems().addAll(load, save);
        
        // if load, it calls the image chooser function with the desired width and height of the image to resize it
        load.setOnAction(e ->{
            // resize to a fixed width (not proportional)
            int scaledWidth = 350;
            int scaledHeight = 400;
            
            //this process is to demonstate the comparable interfaces
            ImageChoosing choose1 = new ImageChoosing(scaledWidth, scaledHeight, input1, outImg1 );           
            ImageChoosing choose2 = new ImageChoosing(300, scaledHeight, input1, outImg1 );
            
            int value = choose1.compareTo(choose2);
            if(value > 0){
                imagesPane.add(choose1.getImage(), 3, 3);  
            }else{
                imagesPane.add(choose2.getImage(), 3, 3);
            }
        }); 
        
        //saves the manipulated image to any file you want by calling the filechooser class
        save.setOnAction(e ->{
            ImageChoosing choose; 
            try {
                choose = new ImageChoosing(outImg1);
            }catch(IIOException ex){
                System.out.println("the file does not exist");
            } catch (IOException ex) {
                System.out.println("the file does not exist");

            }finally{
                System.out.println("done");
            }           
        });
        
        return main;
    }

    
}
