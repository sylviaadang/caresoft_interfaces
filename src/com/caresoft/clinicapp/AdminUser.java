package com.caresoft.clinicapp;

import java.util.ArrayList;
import java.util.Date;

public class AdminUser extends User implements HIPAACompliantUser, HIPAACompliantAdmin {
	private Integer employeeID;
    private String role;
//    don't forget to initialize
    private ArrayList<String> securityIncidents = new ArrayList<String>();
    
    // TO DO: Implement a constructor that takes an ID and a role
    
    public AdminUser(Integer id, String role) {
    	this.id = id;
    	this.role = role;
    	this.employeeID =id;
    }
    
    
    
    public void newIncident(String notes) {
        String report = String.format(
            "Datetime Submitted: %s \n,  Reported By ID: %s\n Notes: %s \n", 
            new Date(), this.id, notes
        );
        securityIncidents.add(report);
    }
    public void authIncident() {
        String report = String.format(
            "Datetime Submitted: %s \n,  ID: %s\n Notes: %s \n", 
            new Date(), this.id, "AUTHORIZATION ATTEMPT FAILED FOR THIS USER"
        );
        securityIncidents.add(report);
    }
    
    // TO DO: Implement HIPAACompliantUser!
    // TO DO: Implement HIPAACompliantAdmin!
    
    @Override
    public boolean assignPin(int pin) {
    	if (pin < 99999 || pin > 999999) {
    		return false;
    	}
    	this.pin = pin;
    	return true;
    }
    
    @Override
    public ArrayList<String> reportSecurityIncidents() {
    	return this.securityIncidents;
    }
    
    @Override
    public boolean accessAuthorized(Integer confirmedAuthID) {
    	if (this.id != confirmedAuthID) {
    		this.authIncident();
    		return false;
    	}
    	return true;
    }
    
    
    // TO DO: Setters & Getters
}
