package com.mycompany.busticketing_system;

import java.util.Scanner;

public class Payment {

    private String paymentID;
    private double paymentAmount;
    private String paymentMethod = " ";
    private String paymentDate;
    String comp = "Sani Express";
    String addres = "Petronas Twin Tower\n Kuala Lumpur, Malaysia";
    private static int receiptNo = 0;

    // constructor
    Payment() {

    }

    Payment(String paymentID, double paymentAmount) {
        this.paymentID = paymentID;
        this.paymentAmount = paymentAmount;
    }

    public void setpaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public void setpaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setpaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setpaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentDate() {

        return paymentDate;
    }

    //payment method menu
    public boolean memberPay() {
        Scanner sc = new Scanner(System.in);
        String creditCardNum;
        CreditCard credit = new CreditCard();
        int cvv;
        int expMonth;
        int expYear;
        int bank;
        String phone;
        String password;
        Ewallet ew = new Ewallet();

        int type;
        int select;

        boolean payValue = false;

        do {
            System.out.println("\n*******************************");
            System.out.println("        Payment Mode           ");
            System.out.println("*******************************");
            System.out.println("   1. Credit Card(Visa)        ");
            System.out.println("   2. E Wallet                 ");
            System.out.println("   3. Back                     ");
            System.out.println("********************************");
            System.out.print("\nEnter Payment Mode :");
            select = sc.nextInt();
            //input.next();
            if (select == 1) {
                do {
                    System.out.print("1.MAYBANK\n2.CIMB\n3.RHB\n4.PUBLIC BANK\n ");
                    System.out.print("Choose your Credit Card bank: ");
                    bank = sc.nextInt();
                    credit.validBank(bank);
                    if (credit.validBank(bank) == false) {
                        System.out.println("\nInvalid Input! Try Again!! \n");
                    }
                } while (credit.validBank(bank) == false);

                System.out.println("*************************************************************************************");

                do {

                    System.out.print("Enter Credit Card Number : ");
                    creditCardNum = sc.next();
                    credit.setCreditCardNum(creditCardNum);
                    if (credit.validCreditCardNum() == false) {
                        System.out.println("\nInvalid Card Number! Try Again!!\n");
                    }
                } while (credit.validCreditCardNum() == false);
                do {
                    System.out.print("Enter cvv(3 digits) : ");
                    cvv = sc.nextInt();
                    credit.setCCV(cvv);
                    if (credit.validCvv() == false) {
                        System.out.println("\nInvalid Cvv! Try Again!! \n");
                    }
                } while (credit.validCvv() == false);
                do {
                    System.out.println("Enter expired date of Visa Card :");
                    System.out.print("Year(YYYY)  : ");
                    expYear = sc.nextInt();
                    System.out.print("Month(1-12) : ");
                    expMonth = sc.nextInt();
                    if (credit.validDate(expMonth, expYear) == false) {
                        System.out.println("\nInvalid Month Or Year! Try Again!! \n");
                    } else {
                        payValue = true;
                    }
                } while (credit.validDate(expMonth, expYear) == false);

            } else if (select == 2) {
                do {
                    System.out.print("1.TouchnGo\n2.Boost\n");
                    System.out.print("Choose your E wallet preference: ");
                    type = sc.nextInt();
                    ew.validType(type);
                    if (ew.validType(type) == false) {
                        System.out.println("\nInvalid Input! Try Again!! \n");
                    }
                } while (ew.validType(type) == false);

                System.out.println("*************************************************************************************");
                do {
                    System.out.print("Enter phone number that registered with your E Wallet : ");
                    phone = sc.next();
                    ew.setPhoneNumber(phone);
                    if (ew.validPhoneNumber() == false) {
                        System.out.println("\nInvalid Phone Number! Try Again!! \n");
                    }
                } while (ew.validPhoneNumber() == false);
                do {
                    System.out.print("Enter password(6 digits) : ");
                    password = sc.next();
                    ew.setPassword(password);
                    if (ew.validPassword() == false) {
                        System.out.println("\nInvalid Password! Try Again!!\n");
                    } else {
                        payValue = true;
                    }
                } while (ew.validPassword() == false);

            } else if (select == 3) {
                break;
            } else {
                System.out.println("\nInvalid Value! Please Try Again!! \n");
            }
        } while (select < 1 || select > 3);
        return payValue;
    }

    //Receipt
    public void receipt(Ticket[] ticket, int count, BusFees[] busfees, Payment[] payments, int[] paymentIndex, Customer customer) {
        double total = 0;
        receiptNo++;

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("*************************************************************************************");
        System.out.printf("%-25s%50s%10s" + '\n', comp, "", "No:A" + receiptNo);
        System.out.printf("%-23s" + '\n', addres);
        System.out.printf("%45s" + '\n', "Receipt");
        System.out.println(paymentDate);

        System.out.println("*************************************************************************************");
        System.out.printf("%-5s%12s%25s%20s" + '\n', "No", "Item", "Price(RM)", "Subtotal(RM)");
        System.out.println("*************************************************************************************");

        for (int i = 0; i < count; i++) {

            System.out.printf("%-5s%15s%20.2f%20.2f" + '\n', payments[paymentIndex[i]].paymentID, "Bus Ticket", payments[paymentIndex[i]].paymentAmount, payments[paymentIndex[i]].paymentAmount * 1);
            total += payments[paymentIndex[i]].getPaymentAmount();

        }
        System.out.println("");
        System.out.println("");

        System.out.println("*************************************************************************************");

        System.out.printf("%-5s%26.2f" + '\n', "Total", total);
        System.out.printf("%-5s%23.2f" + '\n', "Discount", total * customer.getDiscountRate());
        System.out.println("*************************************************************************************");

        System.out.printf("%-5s%20.2f" + '\n', "Amount paid", total - (total * customer.getDiscountRate()));
        System.out.println("*************************************************************************************");
        System.out.printf("%45s" + '\n', "THANK YOU!!");
    }

    @Override
    public String toString() {
        return String.format("|%s%2s|%-10s|RM%12.2f|%-14s|", paymentDate, " ", paymentID, paymentAmount, paymentMethod);
    }

}
