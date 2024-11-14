package com.mycompany.busticketing_system;

import java.time.LocalDate;
import java.util.regex.*;

public class CreditCard extends Payment {

    private String creditCardNum;
    private int cvv;
    boolean valid;

    //construtor
    CreditCard() {
    }

    CreditCard(String paymentID, double paymentAmount,String creditCardNum, int cvv) {
        super(paymentID, paymentAmount);
        this.creditCardNum = creditCardNum;
        this.cvv = cvv;
        
    }

    // getter and setter
    public void setCreditCardNum(String creditCardNum) {
        this.creditCardNum = creditCardNum;
    }

    public void setCCV(int cvv) {
        this.cvv = cvv;
    }

    public long getCvv() {
        return cvv;
    }

    public String getCreditCardNum() {
        return creditCardNum;
    }

    public boolean validBank(int bank) {
        valid = false;

        if (bank == 1 || bank == 2 || bank == 3 || bank == 4) {
            valid = true;
        } else {
            valid = false;
        }

        return valid;
    }

    // Valiate credit card number
    public boolean validCreditCardNum() {
        String regex = "^4[0-9]{12}(?:[0-9]{3})?$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(creditCardNum);
        return m.matches();

    }

    //Validate month and year input
    public boolean validDate(int expMonth, int expYear) {
        valid = true;
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int year = date.getYear();

        if (expYear >= year) {
            if (expMonth >= 1 && expMonth <= 12) {
                if (expYear == year) {
                    if (expMonth < month) {
                        valid = false;
                    } else {
                        valid = true;
                    }

                } else {
                    valid = true;
                }

            } else {
                valid = false;
            }

        } else {
            valid = false;
        }

        return valid;
    }

    // Validate cvv 
    public boolean validCvv() {
        valid = false;
        int length = String.valueOf(cvv).length();
        if (length == 3) {
            valid = true;
        } else {
            valid = false;
        }
        return valid;
    }

    @Override
    public String toString() {
        return "CreditCardNum= " + creditCardNum + "\n cvv= " + cvv;
    }
    
    

}
