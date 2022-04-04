/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;


import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

/**
 * FXML Controller class
 * @author Shannon 
 */

/**
* RUNTIME ERROR OCCURED (SAVE BUTTON):
* Caused by: javalangNumberFormatException For input string: ""
* It was SOLVED by implementing a try/catch method and notifies the user of incorrect values
*/

/** Controller class for the applications FXML modify part screen. */
public class ModifyPartMenuController implements Initializable {
    
    Stage stage;
    Parent scene;
    private Part selectedPart;
    public Part radioPart;
    
   
    @FXML
    private Label labelMachineID;
    
    @FXML
    private TextField modMachineIDTxt;

    @FXML
    private TextField modPartIDTxt;
    
    @FXML
    private TextField modPartNameTxt;
    
    @FXML
    private TextField modPartInvTxt;
    
    @FXML
    private TextField modPartPriceTxt;
    
    @FXML
    private TextField modPartMaxTxt;
    
    @FXML
    private TextField modPartMinTxt;
    
    @FXML
    private RadioButton partInHouseRadio;
    
    @FXML
    private RadioButton partOutsourceRadio;
    
    @FXML
    private ToggleGroup modsourceTG;
    
    /** Changes the label depending on radio button selection. 
     * 
     * @param event 
     */
    @FXML
    public void onActionRadioBtnChg(ActionEvent event) {
        
        if(partInHouseRadio.isSelected()){
            labelMachineID.setText("Machine ID");

        } else if (partOutsourceRadio.isSelected()) {
            labelMachineID.setText("Company Name");
            
        }
        
    }
    
    /** Sends the user a confirmation popup before returning the main menu. */
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

    /**
     * RUNTIME ERROR OCCURED:
     * Caused by: javalangNumberFormatException For input string: ""
     * It was SOLVED by implementing a try/catch method and notifies the user of incorrect values.
     * checks for valid fields then saves the modified part to the inventory
     * and takes the user back the main menu. */
    @FXML
    public void onActionModPartSave(ActionEvent event) throws IOException {
      
    /** Uses a wrapper class to convert between data types. */    
    try {        
            int id = Integer.parseInt(modPartIDTxt.getText());
            String name = modPartNameTxt.getText();
            int stock = Integer.parseInt(modPartInvTxt.getText());
            double price = Double.parseDouble(modPartPriceTxt.getText());
            int min = Integer.parseInt(modPartMinTxt.getText());
            int max = Integer.parseInt(modPartMaxTxt.getText());
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
                
                if(modsourceTG.getSelectedToggle() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please select InHouse or Outsourced");
                    alert.showAndWait();
                    return;
                }
                
                Part newPart;
                if(partInHouseRadio.isSelected()) {
                    newPart = new InHouse(id, "", 0.0, 0, 0, 0, 0);
                    newPart.setName(modPartNameTxt.getText());
                    newPart.setPrice(Double.parseDouble(modPartPriceTxt.getText()));
                    newPart.setStock(Integer.parseInt(modPartInvTxt.getText()));
                    newPart.setMin(Integer.parseInt(modPartMinTxt.getText()));
                    newPart.setMax(Integer.parseInt(modPartMaxTxt.getText()));
                    ((InHouse) newPart).setMachineID(Integer.parseInt(modMachineIDTxt.getText()));

                    Inventory.deletePart(selectedPart);
                    Inventory.addPart(newPart);

            }
                else if(partOutsourceRadio.isSelected()) {
                    newPart = new Outsourced(id, "", 0.0, 0, 0, 0, "");
                    newPart.setName(modPartNameTxt.getText());
                    newPart.setPrice(Double.parseDouble(modPartPriceTxt.getText()));
                    newPart.setStock(Integer.parseInt(modPartInvTxt.getText()));
                    newPart.setMin(Integer.parseInt(modPartMinTxt.getText()));
                    newPart.setMax(Integer.parseInt(modPartMaxTxt.getText()));
                    ((Outsourced) newPart).setCompanyName(modMachineIDTxt.getText());
                    
                    Inventory.deletePart(selectedPart);
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
   
    /** Transfers the data about the selected part to this controller. */
    public void sendPart(Part part)
    {      
        selectedPart = part;

        modPartIDTxt.setText(String.valueOf(selectedPart.getId()));
        modPartNameTxt.setText(selectedPart.getName());
        modPartInvTxt.setText(String.valueOf(selectedPart.getStock()));
        modPartPriceTxt.setText(String.valueOf(selectedPart.getPrice()));
        modPartMinTxt.setText(String.valueOf(selectedPart.getMin()));
        modPartMaxTxt.setText(String.valueOf(selectedPart.getMax()));
              
        if(selectedPart instanceof InHouse) {
            modMachineIDTxt.setText(String.valueOf(((InHouse) part).getMachineID()));
            labelMachineID.setText("Machine ID");
            partInHouseRadio.setSelected(true);
        }
        else if(selectedPart instanceof Outsourced) {
            modMachineIDTxt.setText(((Outsourced) part).getCompanyName());
            labelMachineID.setText("Company Name");
            partOutsourceRadio.setSelected(true); 
        } 
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        /** Disables the user from entering data to the part ID text field. */  
        modPartIDTxt.setDisable(true);
        
        /** Increments the part ID field starting from 1. */
        modPartIDTxt.setText(String.valueOf(Inventory.getAllParts().size() + 1));
    }    
    

    
}
