/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;


import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
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

import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Main Menu Controller class
 *
 * @author Shannon 
 */


/** Controller class for the applications FXML main menu. */
public class MainMenuController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    Product selectedProduct;
    Part selectedPart;
    
    @FXML
    private Button addPartButton;
    @FXML
    private Button addProductBtn;
    @FXML
    private Button delPartBtn;
    @FXML
    private Button delProductBtn;
    @FXML
    private TextField searchPartsField;
    @FXML
    private TextField searchProductsField;
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    @FXML
    private TableColumn<Part, Integer> partInvCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableColumn<Product, Integer> productIDCol;
    @FXML
    private TableColumn<Product, Integer> productInvCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;
    @FXML
    private TableView<Product> productTableView;  
    @FXML
    private Label searchLabelL;
    @FXML
    private Label searchLabelLR;
    

        
/** search parts
 * 
 * @param id
 * @return if searched ID has a match
 */
    public boolean searchPart(int id)
    {  
        for(Part part : Inventory.getAllParts())
        {
            if(part.getId() == id)
                return true;
        }
        return false;
           
    }
    
/** search products
 * 
 * @param id
 * @return if searched ID has a match
 */
    public boolean searchProduct(int id)
    {
        for(Product product : Inventory.getAllProducts())
        {
            if(product.getId() == id)
                return true;
        }
        return false;  
    }
    
    
/** update parts
 * 
 * @param id
 * @param selectedPart 
 */
    public boolean updatePart(int id, Part selectedPart)
    {
            int index = -1;
        
        for(Part part : Inventory.getAllParts())
        {
            index++;
                        
            if(part.getId() == id)
            {
                Inventory.getAllParts().set(index, selectedPart);
                return true;
            }
        }
        return false;   
    }
    
/** update products
 * 
 * @param id
 * @param selectedProduct
 */
    public boolean updateProduct(int id, Product selectedProduct)
    {
            int index = -1;
        
        for(Product product : Inventory.getAllProducts())
        {
            index++;
                        
            if(product.getId() == id)
            {
                Inventory.getAllProducts().set(index, selectedProduct);
                return true;
            }
        }
        
        return false;
    }
           
/** delete part
 * 
 * @param partSelected
 */
    public boolean deletePart(Part partSelected)
    {
        for(Part part : Inventory.getAllParts()) {
            return Inventory.getAllParts().remove(part);
        }
        return false;        
    }

/** delete product
 * 
 * @param productSelected
 */
    public boolean deleteProduct(Product productSelected)
    {
        for(Product product : Inventory.getAllProducts()) {
            return Inventory.getAllProducts().remove(product);
        }
        
        return false;        
    }

    
/** select part
 * 
 * @param id
 * @return selected part or null if nothing is selected
 */
    public Part selectPart(int id)
    {
        for(Part part : Inventory.getAllParts())
        {
            if(part.getId() == id)
                return part;
        }
        
        return null;
    }
    
/** select product
 * 
 * @param id
 * @return selected product or null if nothing is selected
 */
    public Product selectProduct(int id)
    {
        for(Product product : Inventory.getAllProducts())
        {
            if(product.getId() == id)
                return product;
        }
        
        return null;
    }
    

/** filter part
 * 
 * @param name
 */    
    public ObservableList<Part> filterPart(String name)
    {
        if(!(Inventory.getAllFilteredParts().isEmpty()))
            Inventory.getAllFilteredParts().clear();
        
       for(Part part : Inventory.getAllParts()) 
       {
           if(part.getName().contains(name))
               Inventory.getAllFilteredParts().add(part);
       }
       
       if(Inventory.getAllFilteredParts().isEmpty())
           return Inventory.getAllParts();
       else
           return Inventory.getAllFilteredParts();

    }

/** filter product
 * 
 * @param name
 */
    public ObservableList<Product> filterProduct(String name)
    {
        for(Product product : Inventory.getAllProducts())
       {
           if(product.getName().contains(name))
               Inventory.getAllFilteredProducts().add(product);
       }
       
        if(Inventory.getAllFilteredParts().isEmpty())
           return Inventory.getAllProducts();
       else
           return Inventory.getAllFilteredProducts();

    }
    
    /** 
     * When clicked, takes the user to the Add Part screen.
     * @throws IOException 
     */
    @FXML
    public void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPartMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();  
    } 
    
    /** When clicked, takes the user to the Add Product screen.
     * 
     * 
     * @throws IOException 
     */
    @FXML
    public void onActionAddProduct(ActionEvent event) throws IOException {
    
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProductMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
        
    }
    
    /** RUN TIME ERROR would occur when the the modify and/or delete button was pressed with out any part or product 
     * selected due to the selectedPart/selectProduct being null SOLUTION was to use an if statement to apply different 
     * functions depending on the users actions. 
     * 
     * 
     * @throws IOException 
     */
    @FXML
    public void onActionDelPart(ActionEvent event) throws IOException {  

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if(selectedPart == null){
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a part to delete");
            alert.showAndWait();

            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();
        
            if (result.isPresent() && result.get() == ButtonType.OK) {
                
                Inventory.deletePart(selectedPart);
            }

    }
    
    /** If a product is selected, sends a confirmation pop up and if "ok" is clicked, deletes the
     * product from inventory. 
     * 
     */
    @FXML
    public void onActionDelProduct(ActionEvent event) {

        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please selected a product to delete");
            alert.showAndWait();
            return;
            
        }                
                ObservableList<Part> assocProducts = selectedProduct.getAllAssociatedParts();

                if (assocProducts.size() >= 1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Associated Parts");
                    alert.setContentText("You can not delete products with associated parts");
                    alert.showAndWait();
                    return;
                    
                } else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Alert");
                    alert.setContentText("Are you sure you want to delete this product?");
                    Optional<ButtonType> result = alert.showAndWait();

                        if (result.isPresent() && result.get() == ButtonType.OK) {

                            Inventory.deleteProduct(selectedProduct);
                        }
                    }     
    }
    
    /** Exits the user from the application. */
    @FXML
    public void onActionExitMain(ActionEvent event) {
        
        System.exit(0);    
        
    }
    
    /** As long as a product is selected, opens the modify product screen. */
    @FXML
    public void onActionModifyProduct(ActionEvent event) throws IOException {  
        
        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please selected a product to modify");
            alert.showAndWait();
            return;
            
        } else {
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProductMenu.fxml"));
            loader.load();

            ModifyProductMenuController ModProductController = loader.getController();
            ModProductController.sendProduct(productTableView.getSelectionModel().getSelectedItem());       

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }  
    
    /** As long as a part is selected, opens the modify part screen. */
    @FXML
    public void onActionModifyPart(ActionEvent event) throws IOException {         
           
        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please selected a part to modify");
            alert.showAndWait();
            return;
            
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPartMenu.fxml"));
            loader.load();

            ModifyPartMenuController ModPartController = loader.getController();
            ModPartController.sendPart(partTableView.getSelectionModel().getSelectedItem());

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        
    }

//Initialize the controller class
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        //POPULATE PRODUCT TABLE
        productTableView.setItems(Inventory.getAllProducts());
        
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
        // POPULATE PART TABLE
        partTableView.setItems(Inventory.getAllParts());
        
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }
    
    /** Searches products based on ID or String data types for both partial and full names
     * and alerts the user if no product is found. */
    @FXML
    public void onActionMainProductSearch(ActionEvent event) {
        
        String search = searchProductsField.getText();
                
        ObservableList<Product> product = searchProduct(search);

        productTableView.setItems(product);
    }
        
    private ObservableList<Product> searchProduct(String partialProduct) {
        
        ObservableList<Product> nameProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        String searchProduct = searchProductsField.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchProduct)|| product.getName().contains(partialProduct)) {
                nameProducts.add(product);
            }
        }
        if (searchProduct.isEmpty() || searchProduct.isBlank()) {
            
            productTableView.setItems(Inventory.getAllProducts());
            
        }
        
        if (nameProducts.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Search Error");
            alert.setContentText("Product not found!");
            alert.showAndWait();
        }
        
        return nameProducts;
    
    }

   /** Searches parts based on ID or String data types for both partial and full names
     * and alerts the user if no part is found. */
    @FXML
    public void onActionMainPartSearch(ActionEvent event)  {
        
        String search = searchPartsField.getText();

        ObservableList<Part> parts = searchParts(search);

        partTableView.setItems(parts);
        }      
        private ObservableList<Part> searchParts(String partialPart) {
            ObservableList<Part> partsName = FXCollections.observableArrayList();
            ObservableList<Part> allParts = Inventory.getAllParts();

            String searchPart = searchPartsField.getText();
            
            for (Part part : allParts) 
                    if (String.valueOf(part.getId()).contains(searchPart) || part.getName().contains(partialPart)) {
                        partsName.add(part);
                    } 
                    else if (searchPart.isBlank() || searchPart.isEmpty()) {

                partTableView.setItems(Inventory.getAllParts());
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