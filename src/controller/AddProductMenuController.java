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

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import model.Product;

/**
 * 
 *
 * @author Shannon
 */

/**
 * Controller class for the applications FXML product screen. 
 */
public class AddProductMenuController implements Initializable {
    
    Product newProduct = new Product(0, "name", 0.0,0,0,0);
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    Stage stage;
    Parent scene;
    private Product selectedProduct;

    @FXML
    private Button addProductBtn;
    @FXML
    private Label addProductIDLabel; 
    @FXML
    private Label addNameLabel;  
    @FXML
    private Label addInvLabel;   
    @FXML
    private Label addPriceLabel;  
    @FXML
    private Label addMaxLabel;  
    @FXML
    private Label addMinLabel;
    @FXML
    private Label addProductSearchLbl;
    @FXML
    private TableColumn<Part, Integer> partIDACol;
    @FXML
    private TableColumn<Product, Integer> partIDBCol;
    @FXML
    private TableColumn<Part, Integer> partInvACol;
    @FXML
    private TableColumn<Product, Integer> partInvBCol;
    @FXML
    private TableColumn<Part, String> partNameACol;
    @FXML
    private TableColumn<Product, String> partNameBCol;
    @FXML
    private TableColumn<Part, Double> partPriceACol;
    @FXML
    private TableColumn<Product, Double> partPriceBCol;  
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
    private TableView<Part> partTableViewTop;
    @FXML
    private TableView<Part> partTableViewBot;   
    @FXML
    private TableColumn<Product, Integer> partIDCol;
    @FXML
    private TableColumn<Product, Integer> partInvCol;
    @FXML
    private TableColumn<Product, String> partNameCol;
    @FXML
    private TableColumn<Product, Double> partPriceCol;
    @FXML
    private TableColumn<Product, Integer> productIDColB;
    @FXML
    private TableColumn<Product, Integer> productInvColB;
    @FXML
    private TableColumn<Product, String> productNameColB;
    @FXML
    private TableColumn<Product, Double> productPriceColB;
    
    
    /** Transfers the data about the selected product to this controller TEST. 
     */
    @FXML
    public void onActionAddProduct(ActionEvent event) {

        Part singlePart = partTableViewTop.getSelectionModel().getSelectedItem();
        newProduct.addAssociatedParts(singlePart);
        partTableViewBot.setItems(newProduct.getAllAssociatedParts()); 
    }
    
    /** Transfers the data about the selected product to this controller. 
     */
    @FXML
    public void sendProduct(Product product)
    {      
        selectedProduct = product;

        productIDTxt.setText(String.valueOf(selectedProduct.getId()));
        productNameTxt.setText(selectedProduct.getName());
        productInvTxt.setText(String.valueOf(selectedProduct.getStock()));
        productPriceTxt.setText(String.valueOf(selectedProduct.getPrice()));
        productMinTxt.setText(String.valueOf(selectedProduct.getMin()));
        productMaxTxt.setText(String.valueOf(selectedProduct.getMax()));
        
        partTableViewBot.setItems(selectedProduct.getAllAssociatedParts());
    }
    
    /** Sends the user a confirmation popup before returning the main menu. 
     */
    public void onActionReturnMain(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values. " + 
                "Do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK)           
        {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    
    /** First sends the user a confirmation pop up before removing the product from inventory. 
     */
    @FXML
    public void onActionRemoveProduct(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setContentText("Are you sure you want to delete this part?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Part selected = partTableViewBot.getSelectionModel().getSelectedItem();
                Inventory.deletePart(selected);
                partTableViewBot.setItems(Inventory.getAllParts());
            }
        });

    }
    
    /** Checks for valid fields then creates the new product object to the observable list
     * and takes the user back the main menu FUTURE ENCHANCEMENT would be to add multiple parts and/or products; This way the user does not have 
     * open the form and save for each and every part/product; The add forms could allow the user to simply save the 
     * part/product WITHOUT returning them to the main menu and the form would be cleared upon saving for reuse.
     */
    @FXML
    public void onActionSaveProduct(ActionEvent event) throws IOException {
       
    // Uses a wrapper class to convert between data types.   
        try{ 
            int id = Integer.parseInt(productIDTxt.getText());
            String name = productNameTxt.getText();
            int stock = Integer.parseInt(productInvTxt.getText());
            double price = Double.parseDouble(productPriceTxt.getText());
            int min = Integer.parseInt(productMinTxt.getText());
            int max = Integer.parseInt(productMaxTxt.getText());
            
            if (max < min) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Minimum value must be less than the maximum value");
                    alert.showAndWait();
                    return;
                }

                if(stock > max || stock < min) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Inv value must be between min and max");
                    alert.showAndWait();
                    return;
                }
                
            newProduct.setId(Integer.parseInt(productIDTxt.getText()));
            newProduct.setName(productNameTxt.getText());
            newProduct.setPrice(Double.parseDouble(productPriceTxt.getText()));
            newProduct.setStock(Integer.parseInt(productInvTxt.getText()));
            newProduct.setMin(Integer.parseInt(productMinTxt.getText()));
            newProduct.setMax(Integer.parseInt(productMaxTxt.getText()));

            Inventory.addProduct(newProduct);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
                
        }
        
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Data Entry Error");
            alert.setContentText("A field has been left blank or is invalid. " +
                    "Please check all fields.");
            alert.showAndWait();
        }
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        partTableViewTop.setItems(Inventory.getAllParts());

        // POPULATES TOP TABLE
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        

        // POPULATES BOTTOM TABLE
        productIDColB.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColB.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvColB.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColB.setCellValueFactory(new PropertyValueFactory<>("price"));
       
        
        /** Disables the user from entering data to the product ID text field. */
        TextField partIDfield = productIDTxt;
        partIDfield.setDisable(true);
        
        /** Increments the product ID field starting from 1000. */
        productIDTxt.setText(String.valueOf(Inventory.getAllProducts().size() + 1000));
       
    }

    
    /** Searches parts to associate based on ID or String data types for both partial and full names
     * and alerts the user if no part is found. 
     */
    @FXML
    public void onActionProductSearch(ActionEvent event) {
        
        String search = productTableSearchTxt.getText();

        ObservableList<Part> parts = searchParts(search);

        partTableViewTop.setItems(parts);
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

                partTableViewTop.setItems(Inventory.getAllParts());
            }
            
            if (partsName.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Search Error");
            alert.setContentText("Part not found!");
            alert.showAndWait();
        }
            
            return partsName;

        }
        
    
    
}

