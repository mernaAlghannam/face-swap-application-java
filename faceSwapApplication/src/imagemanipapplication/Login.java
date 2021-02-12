/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagemanipapplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *  
 * @author merna
 */
public class Login {
    private TextField name; // text field where they type the name
    private TextField password; // text field for password
    private VBox page;  // the vbox of the page you can hide
    
    /**
     * login constructor
     */
    Login(){
        
    }
    /**
     * this shows the password and name text field and only disspaears if you pressed submit
     * @return the page vbox
     */
    public VBox getLoginPage(){
        VBox loginInfo = new VBox();
        
        Text nameTitle = new Text("Name: ");    // title of the name text field
        nameTitle.setFill(Color.WHITE);
        name = new TextField();
        Text passTitle = new Text("Password: ");    // title of the password textfield
        passTitle.setFill(Color.WHITE);
        password = new TextField();
        
        Button submit = new Button("Submit");   // the submit button
        
        // if you clicked submit, it would save the name and password into a string and calls another fuction to save 
        // data of the object you added teh information to
        submit.setOnAction(e -> {
            String names = String.valueOf(name.getText()); // add whatever data added to name field in names
            String passwords = String.valueOf(password.getText());  // add whatever data added to password field in password
            
            //saved it to the person object
            Person data = new Person(names, passwords);
            
            //saves the object in a text feidl
            try {
                saveToFile(data);   // calls the function to save in a text field
            } catch (IOException ex) {
                System.out.println("try again");
            }
            finally{
                System.out.println("done");
            }
            
            //hides the login vbox
            loginInfo.setVisible(false);
        });
        
        loginInfo.getChildren().addAll(nameTitle, name, passTitle, password, submit);
        
        return loginInfo;
    }
    /**
     * save the person object into a text file - demonstrates serializable interface capabilities
     * @param info
     * @throws IOException 
     */
    public void saveToFile(Person info) throws IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("person.txt"));
        objectOutputStream.writeObject(info);
    }
}
