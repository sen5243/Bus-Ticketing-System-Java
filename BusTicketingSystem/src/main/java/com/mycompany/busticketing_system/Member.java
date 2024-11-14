package com.mycompany.busticketing_system;

import java.time.LocalDate;

public class Member extends Customer {

    private final double DISCOUNT_RATE = 0.2;
    private final char CUST_TYPE = 'M';

    public Member() {
    }

    public Member(String ic, String password, String name, String contactNo, String email, LocalDate dob, char gender, char custType) {
        super(ic, password, name, contactNo, email, dob, gender, custType);
    }


    @Override
    public double getDiscountRate() {
        return DISCOUNT_RATE;
    }

    @Override
    public char getCustType() {
        return CUST_TYPE;
    }
    
    /**
     *
     * @return
     */
    @Override //polymophism
    public String toString() {
        return super.toString()
                + "\n  Customer Type     = Member";
    }
}
