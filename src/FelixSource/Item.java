/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FelixSource;

import java.time.LocalDateTime;

/**
 *
 * @author 0.5X
 */
public class Item {

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String ItemID) {
        this.ItemID = ItemID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    public String getOperatorName() {
        return OperatorName;
    }

    public void setOperatorName(String OperatorName) {
        this.OperatorName = OperatorName;
    }

    public LocalDateTime getTimePlaced() {
        return TimePlaced;
    }

    public void setTimePlaced(LocalDateTime TimePlaced) {
        this.TimePlaced = TimePlaced;
    }

    public LocalDateTime getTimeModified() {
        return TimeModified;
    }

    public void setTimeModified(LocalDateTime TimeModified) {
        this.TimeModified = TimeModified;
    }

    public Boolean getNeedsReorder() {
        return NeedsReorder;
    }

    public void setNeedsReorder(Boolean NeedsReorder) {
        this.NeedsReorder = NeedsReorder;
    }
    
    String ItemID;
    
    String Category;
    String Name;
    Integer Quantity;
    String OperatorName;
    LocalDateTime TimePlaced;
    LocalDateTime TimeModified;
    Boolean NeedsReorder;

    public Item() {}
      
    public Item(String ItemID, String Category, String Name, int Quantity, String OperatorName, LocalDateTime TimePlaced, LocalDateTime TimeModified, Boolean NeedsReorder) {
        this.ItemID = ItemID;
        this.Category = Category;
        this.Name = Name;
        this.Quantity = Quantity;
        this.OperatorName = OperatorName;
        this.TimePlaced = TimePlaced;
        this.TimeModified = TimeModified;
        this.NeedsReorder = NeedsReorder;
    }
    
    
}
