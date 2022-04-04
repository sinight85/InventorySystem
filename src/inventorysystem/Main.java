/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventorysystem;

import controller.AddPartMenuController;
import controller.AddProductMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Controller class
 *
 * @author Shannon 
 */

// Javadoc folder is located in the InventorySystem > dist folder

/** Class that creates an application that displays messages. */
public class Main extends Application{

    static int idPart = 0;
    static int idProduct = 999;

    /** Loads the initial scene and creates the FXML stage. 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inventory Management System");
        stage.show();
    }
    /** Main method is the first method called to run the java program and also creates the sample data. 
     */
        public static void main(String[] args) {
            
            //Populates the Table with sample parts and products
            
            Part Brakes = new Part(idPart += 1, "Brakes", 15.00, 10, 0, 100) {};
            
            Part Wheel = new Part(idPart += 1, "Wheel", 11.00, 16, 0, 100) {};
            
            Part Seat = new Part(idPart += 1, "Seat", 15.00, 10, 0, 100) {};
            
            Part Handles = new Part(idPart += 1, "Handles", 25.00, 11, 0, 100) {};
            
            Inventory.getAllParts().addAll(Brakes,Wheel,Seat,Handles);
            
          
            Product Bike = new Product(idProduct +=1, "Giant Bike", 299.99, 5, 0, 100) {};
            
            Product Tricycle = new Product(idProduct +=1, "Tricycle", 99.99, 3, 0, 100) {};
            
            Product Tandem = new Product(idProduct +=1, "Tandem Bike", 299.99, 7, 0, 100) {};
            
            Product Speed = new Product(idProduct +=1, "Speed Bike", 399.99, 6, 0, 100) {};
                                 
            Inventory.getAllProducts().addAll(Bike,Tricycle,Tandem,Speed);
                

        launch(args);
           
    }
    
}