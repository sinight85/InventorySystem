/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;


import javafx.scene.control.TextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.Optional;
import javafx.collections.ObservableList;


import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;


/**
 * FXML Controller class
 *
 * @author Shannon
 */

/** Controller class for the applications FXML modify product screen. */
public class ModifyProductMenuController implements Initializable {
     
    Stage stage;
    Parent scene;
    Product newProduct;
    Product selectedProduct;
    int selectedIndex;
    Part selectedPart;

    public ObservableList<Part>associatedParts = FXCollections.observableArrayList();

    
    @FXML
    private Button modProductSaveBtn;
    
    @FXML
    private Button modProductCancelBtn;
    
    @FXML
    private Label modProductSearchLbl; 
    
    @FXML
    private Label modProductIDLabel;
    
    @FXML
    private Label modNameLabel;
    
    @FXML
    private Label modInvLabel;
    
    @FXML
    private Label modPriceLabel;
    
    @FXML
    private Label modMaxLabel;
    
    @FXML
    private Label modMinLabel;
                   
    @FXML
    private TableColumn<Part, Integer> partIDACol;

    @FXML
    private TableColumn<Part, Integer> partIDBCol;

    @FXML
    private TableColumn<Part, Integer> partInvACol;

    @FXML
    private TableColumn<Part, Integer> partInvBCol;

    @FXML
    private TableColumn<Part, String> partNameACol;

    @FXML
    private TableColumn<Part, String> partNameBCol;

    @FXML
    private TableColumn<Part, Double> partPriceACol;

    @FXML
    private TableColumn<Part, Double> partPriceBCol;

    @FXML
    private TextField productIDTxt;

    @FXML
    private TextField productInvTxt;

    @FXML
    private TextField productMaxTxt;
    
    @FXML
    private TextField productMinTxt;

    @FXML
    private TextField productNameTxt;

    @FXML
    private TextField productPriceTxt;

    @FXML
    private TextField productTableSearchTxt;

    @FXML
    private TableView<Part> productTableViewA;

    @FXML
    private TableView<Part> productTableViewB;   
    
            
    @FXML
    public void onActionReturnMain(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to cancel?");
        alert.showAndWait();

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }
    @FXML
    public void onActionModRemove(ActionEvent event) {

        Part selectedPart = productTableViewB.getSelectionModel().getSelectedItem();

        if(selectedPart == null){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must select an Associated Part first!");
            alert.showAndWait();

            return;
            
        } else {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the part, do you want to continue?");
        alert.setTitle("Confirm Deletion");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            
            associatedParts.remove(selectedPart);
            productTableViewB.setItems(associatedParts);
            
            }
        
        }

    }

    @FXML
    public void onActionModAdd (ActionEvent event) {
        
        Part selectedPart = productTableViewA.getSelectionModel().getSelectedItem();
        
        if (selectedPart == null) {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a part to add");
            alert.showAndWait();

            return;
            
        } else {
            
        associatedParts.add(selectedPart);
        productTableViewB.setItems(associatedParts);
        
        }
                
    }
    
    /** Checks for valid fields then saves the modified product to the inventory
     * and takes the user back the main menu. */
    @FXML
    public void onActionModSave(ActionEvent event) throws IOException {
        
        try {
            int id = Integer.parseInt(productIDTxt.getText());
            String name = productNameTxt.getText();
            int stock = Integer.parseInt(productInvTxt.getText());
            double price = Double.parseDouble(productPriceTxt.getText());
            int min = Integer.parseInt(productMinTxt.getText());
            int max = Integer.parseInt(productMaxTxt.getText()); 
            
            if (name.isEmpty()) {
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setTitle("No name error");
                alert.setContentText("Please name the product");
                alert.showAndWait();

            return;
            } else {
            
            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Minimum must have a value less than maximum");
                alert.showAndWait();
                return;
            }

            if (stock > max || stock < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Data Entry Error");
                alert.setContentText("Inv value must be between min and max");
                alert.showAndWait();
                return;
            }

            Product modProduct = new Product(id,name,price,stock,min,max);
            for (Part part : associatedParts) {
                modProduct.addAssociatedParts(part);

            }
            
            Inventory.deleteProduct(selectedProduct);
            Inventory.addProduct(modProduct);
            }


        } catch (NumberFormatException e) {

            Alert alert = new Alert((Alert.AlertType.ERROR));
            alert.setTitle("Data Entry Error");
            alert.setContentText("A field has been left blank or is invalid. " +
                    "Please check all fields.");
            alert.showAndWait();
            return;
        }
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }  
    
    /** Transfers the data about the selected product to this controller. */
    public void sendProduct(Product product)
    {
        selectedProduct = product;
        
        productIDTxt.setText(String.valueOf(product.getId()));
        productNameTxt.setText(product.getName());
        productInvTxt.setText(String.valueOf(product.getStock()));
        productPriceTxt.setText(String.valueOf(product.getPrice()));
        productMaxTxt.setText(String.valueOf(product.getMax()));
        productMinTxt.setText(String.valueOf(product.getMin()));
        productTableViewB.setItems(selectedProduct.getAllAssociatedParts());
    } 
    
    /** Searches parts to associate based on ID or String data types for both partial and full names
     * and alerts the user if no part is found. */
    @FXML
    public void onActionModProductSearch (ActionEvent event){
        
        String search = productTableSearchTxt.getText();

        ObservableList<Part> parts = searchParts(search);

        productTableViewA.setItems(parts);
        }      
        private ObservableList<Part> searchParts(String partialPart) {
            ObservableList<Part> partsName = FXCollections.observableArrayList();
            ObservableList<Part> allParts = Inventory.getAllParts();

            String searchPart = productTableSearchTxt.getText();
            
            for (Part part : allParts) 
                    if (String.valueOf(part.getId()).contains(searchPart) || part.getName().contains(partialPart)) {
                        partsName.add(part);
                    } 
                    else if (searchPart.isBlank() || searchPart.isEmpty()) {

                productTableViewA.setItems(Inventory.getAllParts());
            }
            
            if (partsName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Search Error");
            alert.setContentText("Part not found!");
            alert.showAndWait();
        }
            
            return partsName;
    }



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
               
        /** Displays the parts to associate with product. */
        partIDACol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameACol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvACol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceACol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTableViewA.setItems(Inventory.getAllParts());   

        /** Displays the associated parts table. */
        partIDBCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameBCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvBCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceBCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        /** Disables the user from entering data to the part ID text field. */ 
        productIDTxt.setDisable(true);
        
        /** Increments the product ID field starting from 1000. */
        productIDTxt.setText(String.valueOf(Inventory.getAllProducts().size() + 1000));

    }   
        
    
}
