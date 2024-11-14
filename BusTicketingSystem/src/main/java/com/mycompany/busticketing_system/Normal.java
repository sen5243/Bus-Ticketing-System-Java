package com.mycompany.busticketing_system;

import java.time.LocalDate;

public class Normal extends Customer {

     private final double DISCOUNT_RATE;
    private final char CUST_TYPE = 'N';

    public Normal() {
        this.DISCOUNT_RATE = 0;
    }

    public Normal(String ic, String password, String name, String contactNo, String email, LocalDate dob, char gender, char custType) {
        super(ic, password, name, contactNo, email, dob, gender, custType);
        this.DISCOUNT_RATE = 0;
    }

    /**
     *
     * @return
     */
    @Override
    public char getCustType() {
        return CUST_TYPE;
    }

    @Override
    public double getDiscountRate() {
        return DISCOUNT_RATE;
    }

    @Override
    public String toString() { //polymorphism
        return super.toString()
                + "\n  Customer Type     = Normal";
    }
}
