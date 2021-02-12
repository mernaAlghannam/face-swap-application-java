/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagemanipapplication;

import java.io.Serializable;

/**
 *
 * @author merna
 */
public class Person implements Serializable{
    private String name;    // name of person
    private String password;    // password of person
    
    /**
     * person constructor
     * @param name
     * @param password 
     */
    Person(String name, String password){
        this.name = name;
        this.password = password;
    }
    
    /**
     * 
     * @return the name
     */
    public String getName(){
        return name;
    }
    
    /**
     * 
     * @return the password
     */
    public String getPassword(){
        return password;
    }
}
