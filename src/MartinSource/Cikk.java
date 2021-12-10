/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MartinSource;

import java.util.Date;
import java.util.List;

/**
 *
 * @author MARtin
 */
public class Cikk {
    // (5 string 1 integer 2 date 1 string 1 bool)
    String articleNum;
    String shelf;
    String box;
    String category;
    String description;
    int quantity;
    Date placed;
    Date modified;
    String operator;
    boolean needsReorder;
    
    public Cikk()
    {
        this.articleNum = this.shelf = this.box = this.category = 
                this.description = this.operator = "";
        this.quantity = 0;
        this.needsReorder = false;
    }
    //Csak ha jó sorrendben benne van mindegyik tag és hibátlanul tudom konvertálni
    //Sztem nem kell am xd
    public Cikk(List<Object> egysor)
    {
        if(egysor.size() == 10)
        {
            try{
                this.articleNum = (String)egysor.get(0);
                this.shelf = (String)egysor.get(1);
                this.box = (String)egysor.get(2);
                this.category = (String)egysor.get(3);
                this.description = (String)egysor.get(4);
                this.quantity = (int)egysor.get(5);
                this.placed = (Date)egysor.get(6);
                this.modified = (Date)egysor.get(7);
                this.operator = (String)egysor.get(8);
                this.needsReorder = (boolean)egysor.get(9);
            } 
            catch(Exception e) {
                
            }
        }
    }
    
    public Cikk(String articleNum, String shelf, String box, String category,
            String description, int quantity, Date placed, Date modified, 
            String operator, boolean needs)
    {
        this.articleNum = articleNum;
        this.shelf = shelf;
        this.box = box;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.placed = placed;
        this.modified = modified;
        this.operator = operator;
        this.needsReorder = needs;
    }
    
    //kopi kopi kopipészt lesz
    public Cikk(Cikk c)
    {
        this.articleNum = c.articleNum;
        this.shelf = c.shelf;
        this.box = c.box;
        this.category = c.category;
        this.description = c.description;
        this.quantity = c.quantity;
        this.placed = c.placed;
        this.modified = c.modified;
        this.operator = c.operator;
        this.needsReorder = c.needsReorder;
    }
    
    
}
