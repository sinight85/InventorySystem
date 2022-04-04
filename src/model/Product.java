/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Shannon
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Creates the class product which can contain associated parts. */
public class Product {
    
    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    String name;
    double price;
    int stock;
    int min;
    int max;
    
    /** Constructor for Product. */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    /** Product setter for ID. */
    public void setId(int id) {
        this.id = id;
    }
    /** Product setter for name. */
    public void setName(String name) {
        this.name = name;
    }
    /** Product setter for price. */
    public void setPrice(double price) {
        this.price = price;
    }
    /** Product setter for stock. */
    public void setStock(int stock) {
        this.stock = stock;
    }
    /** Product setter for min. */
    public void setMin(int min) {
        this.min = min;
    }
    /** Product setter for max. */
    public void setMax(int max) {
        this.max = max;
    }
    
    /** Product getter for ID. */    
    public int getId() {
        return id;
    }
    /** Product getter for name. */  
    public String getName() {
        return name;
    }
    /** Product getter for price. */  
    public double getPrice() {
        return price;
    }
    /** Product getter for stock. */  
    public int getStock() {
        return stock;
    }
    /** Product getter for min. */  
    public int getMin() {
        return min;
    }
    /** Product getter for max. */  
    public int getMax() {
        return max;
    }

    /** Gets associated parts of product. */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
    /** Adds associated part to product. */
    public void addAssociatedParts(Part part) {
        this.associatedParts.add(part);
    }

    /** Deletes associated part from product. */
    public boolean deleteAssociatedPart(Part part){
        if(associatedParts.contains(part)){
            associatedParts.remove(part);
            return true;
        }
        return false;
    }
    
    


    
}
