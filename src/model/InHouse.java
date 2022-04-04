/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Shannon
 */

/** Extends (inherits) from the Part class and contains the setters and getters. */
public class InHouse extends Part {

    /** Declares machineID as an integer type. */
    private int machineID;

    /** Constructor for the inhouse object. */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /** The setter for machineID. */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    /**The getter for machineID. */
    public int getMachineID() {
        return machineID;
    }
    
}
