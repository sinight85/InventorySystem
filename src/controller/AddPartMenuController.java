 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.Optional;

import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import javafx.scene.control.ButtonType;
import model.Part;

/**
 * FXML Controller class
 *
 * @author Shannon 
 */

/** Controller class for the applications FXML part screen. */
public class AddPartMenuController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    @FXML
    private Label labelMachineID;
    
    @FXML
    private Label partIDLabel;
    
    @FXML
    private Label partInvLabel;
    
    @FXML
    private Label partMaxLabel;
    
    @FXML
    private Label partMinLabel;
    
    @FXML
    private Label partNameLabel;
    
    @FXML
    private Label partPriceLabel;
    
    @FXML
    private Button addPartCancelBtn;
        
    @FXML
    private TextField partidentField;
    
    @FXML
    private TextField partNameField;
    
    @FXML
    private TextField partInvField;
    
    @FXML
    private TextField partPriceField;
    
    @FXML
    private TextField partMaxField;
    
    @FXML
    private TextField partMinField;
    
    @FXML
    private TextField partMachineField;
    
    @FXML
    private RadioButton partInHouseRadio;
    
    @FXML
    private RadioButton partOutsourceRadio; 
    
    @FXML
    private ToggleGroup sourceTG;
    
    /** Sets the label as Machine ID or Company Name depending on the radio button selection. 
     * 
     */
    @FXML
    public void onActionRadioBtnChg(ActionEvent event) {
        
        if(partInHouseRadio.isSelected()){
            labelMachineID.setText("MachineID");

        } else if (partOutsourceRadio.isSelected()) {
            labelMachineID.setText("Company Name");
            
        }
        
    }
    
    /** Sends the user a confirmation popup before returning the main menu. 
     */
    @FXML
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

    /** Checks for valid fields then creates the new part object to the observable list
     * and takes the user back the main menu. 
     */
    @FXML
    public void onActionSavePart(ActionEvent event) throws IOException {
        
    /** Uses a wrapper class to convert between data types. */    
        try {        
            int id = Integer.parseInt(partidentField.getText());
            String name = partNameField.getText();
            int stock = Integer.parseInt(partInvField.getText());
            double price = Double.parseDouble(partPriceField.getText());
            int max = Integer.parseInt(partMaxField.getText());
            int min = Integer.parseInt(partMinField.getText());
            boolean isInhouse;
            String companyName = null; 
            
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
                
                if(sourceTG.getSelectedToggle() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please select InHouse or Outsourced");
                    alert.showAndWait();
                    return;
                }
                
                Part newPart;
                if(partInHouseRadio.isSelected()) {
                    newPart = new InHouse(id, "", 0.0, 0, 0, 0, 0);
                    newPart.setName(partNameField.getText());
                    newPart.setPrice(Double.parseDouble(partPriceField.getText()));
                    newPart.setStock(Integer.parseInt(partInvField.getText()));
                    newPart.setMin(Integer.parseInt(partMinField.getText()));
                    newPart.setMax(Integer.parseInt(partMaxField.getText()));
                    ((InHouse) newPart).setMachineID(Integer.parseInt(partMachineField.getText()));

                    Inventory.addPart(newPart);

            }
                else if(partOutsourceRadio.isSelected()) {

                    newPart = new Outsourced(id, "", 0.0, 0, 0, 0, "");
                    newPart.setName(partNameField.getText());
                    newPart.setPrice(Double.parseDouble(partPriceField.getText()));
                    newPart.setStock(Integer.parseInt(partInvField.getText()));
                    newPart.setMin(Integer.parseInt(partMinField.getText()));
                    newPart.setMax(Integer.parseInt(partMaxField.getText()));
                    ((Outsourced) newPart).setCompanyName(partMachineField.getText());

                    Inventory.addPart(newPart);
                    
                }
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            }         
        
        catch (NumberFormatException e){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Data Entry Error");
            alert.setContentText("A field has been left blank or is invalid. " +
                    "Please check all fields.");
            alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        /** Disables the user from entering data to the part ID text field. */     
        partidentField.setDisable(true);
        
        /** Increments the part ID field starting from 1. */
        partidentField.setText(String.valueOf(Inventory.getAllParts().size() + 1));

    }
    
        

        
  
}
    
 

