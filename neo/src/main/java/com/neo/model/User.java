package com.neo.model;

import java.io.Serializable;
public class User implements Serializable {
    public String name;
    public String message;
  
    public User(User newUser) {
        this.name = newUser.name ;
        this.message= newUser.message;
     
    }
    public User() {
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                "message='"+message+'\''+
                '}';
    }

    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
 
 
}