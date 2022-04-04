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
public class Outsourced extends Part{
    /** Company name is declared as a string type. */
    String companyName;

    /** Constructor for Outsourced. */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** Company name setter. */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /** Company name getter. */
    public String getCompanyName() {
        return companyName;
    }
}