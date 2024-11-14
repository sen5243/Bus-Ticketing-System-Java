package com.mycompany.busticketing_system;

import java.util.regex.*;


public class Ewallet extends Payment {

    private String phoneNumber;
    private String password;
    boolean valid;

    // constructor
    public Ewallet() {
    }

    public Ewallet(String paymentID, double paymentAmount, String paymentMethod,String phoneNumber, String password) {
        super(paymentID, paymentAmount);
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    //getter and setter
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
     public boolean validType(int type){
        valid=false;
        
        if (type ==1 || type==2) 
                valid=true;
            else
                valid=false;
        
        return valid;
    }

    // method to validate phone number 
    public boolean validPhoneNumber() {
        String regex="^(01)[2-46-9][0-9]{7}$|^(01)[1][0-9]{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }

    // method to validate password
    public boolean validPassword() {
        boolean valid = false;
        if (password.length() == 6) {
            for (int i = 0; i < password.length(); i++) {
                if (Character.isDigit(password.charAt(i))) {
                    valid = true;
                } else {
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }
    @Override
    public String toString() {
        return "phoneNumber=" + phoneNumber + "\npassword=" + password;
    }
}
