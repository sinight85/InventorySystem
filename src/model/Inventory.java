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

/** Class that contains the inventory with getters and setters. */
public class Inventory {
    
 private static ObservableList<Part> allParts = FXCollections.observableArrayList();
 private static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
 private static ObservableList<Part> allAssociatedParts = FXCollections.observableArrayList();

 
 private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
 private static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

 private static int productID = 0;
 private static int partID = 0;



public Inventory() {

}
/** Adds part to the observable array list of part objects. */
public static void addPart(Part part){
    allParts.add(part);
}

/** Adds product to the observable array list of part objects. */
public static void addProduct(Product product) {
    allProducts.add(product);
}

/** Returns an Observable List of Part objects. */
public static ObservableList<Part> getAllParts() {
        return allParts;
    }

/** Returns an Observable List of all associated Part objects. */
public static ObservableList<Part> getAllAssociatedParts() {
        return allAssociatedParts;
    }

/** Returns an Observable List of all filtered Part objects. */
public static ObservableList<Part> getAllFilteredParts() {
        return filteredParts;
    }

/** Returns an Observable List of Product objects. */
public static ObservableList<Product>  getAllProducts() {
        return allProducts;
    }

/** Returns an Observable List of all filtered Products objects. */
public static ObservableList<Product> getAllFilteredProducts() {
        return filteredProducts;
    }

/** Creates a unique Product ID. */
public static int getNewProductId() {
        return ++productID;
    }

/** Creates a unique Part ID. */
public static int getNewPartId() {
        return ++partID;
    }

    /** Lookup parts by ID and returns the list. */
        public static Part lookupPart(int partID) {
            for (Part part : allParts) {
                if (part.getId() == partID) {
                    return part;
                }
            }
            return null;
        }

    /** Lookup parts by string and returns the list. */
        public static ObservableList<Part> lookupPart(String partName){
            for (Part part : allParts) {
                if(part.getName().equals(partName)){
                    ObservableList<Part> filteredPartList = FXCollections.observableArrayList();
                    filteredPartList.add(part);
                    return filteredPartList;
                }
            }
            return null;
        }

    /** Lookup products by ID and returns the list. */
        public static Product lookupProduct(int productID) {
            for (Product product : allProducts) {
                if (product.getId() == productID) {
                    return product;
                }
            }
            return null;
        }
        

    /** Lookup products by string and returns the list. */
        public static ObservableList<Product> lookupProduct(String productName){
            for (Product product : allProducts) {
                if(product.getName().equals(productName)){
                    ObservableList<Product> filteredPartList = FXCollections.observableArrayList();
                    filteredPartList.add(product);
                    return filteredPartList;
                }
            }
            return null;
        }

/** Updates selected part in the list. */
    public static void updatePart(int id, Part selectedPart){
        int index = allParts.indexOf(selectedPart);
        allParts.set(index, selectedPart);
    }

/** Updates selected part in the list. */
    public static void updateProduct(int id, Product selectedProduct){
        int index = allProducts.indexOf(selectedProduct);
        allProducts.set(index, selectedProduct);
    }

/** Deletes selected part from the list. */
    public static boolean deletePart(Part partSelected){
        for(Part part : allParts) {
            allParts.remove(partSelected);
            return true;
        }
        return false;
    }

/** Deletes selected product from the list. */
public static boolean deleteProduct(Product productSelected){
    for(Product product : allProducts) {
        allProducts.remove(productSelected);
        return true;
    }
    return false;
}

}
