/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FelixSource;

import java.io.Serializable;

/**
 *  This is one of our entity class this has all the annotation to save to the database and also to serilazie it to JSON to send out with our API
 *  You can see the databse realtion in our documentation (page 7)
 * @author Desmor
 */


public class Container implements Serializable {

    public String getShelf() {
        return Shelf;
    }

    public void setShelf(String Shelf) {
        this.Shelf = Shelf;
    }

    public String getBox() {
        return Box;
    }

    public void setBox(String Box) {
        this.Box = Box;
    }
    //@JsonBackReference
    public Item getItem() {
        return Item;
    }

    public void setItem(Item Item) {
        this.Item = Item;
    }
    
    //Composite key
    String Shelf;
    String Box;
    
    //Foregin Key to Item
    private Item Item;

    public Container() {}
    
    public Container(String Shelf, String Box, Item Item) {
        this.Shelf = Shelf;
        this.Box = Box;
        this.Item = Item;
    }
    
    public Container(String Shelf, String Box) {
        this.Shelf = Shelf;
        this.Box = Box;
    }
    
    
    
}

