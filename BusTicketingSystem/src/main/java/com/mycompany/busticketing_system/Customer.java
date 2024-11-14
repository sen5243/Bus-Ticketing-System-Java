package com.mycompany.busticketing_system;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class Customer {

    private String ic;
    private String password;
    private String name;
    private String contactNo;
    private String email;
    private LocalDate dob;
    private char gender;
    private char custType;
    private double discountRate;
    private ArrayList<Customer> customers = new ArrayList<>();

    File file = new File("customer.txt");
    CustomerFile chkfile = new CustomerFile();
    Register register = new Register(); // for input validation purpose (reuse)

    public Customer() {
    }

    public Customer(String ic, String password, String name, String contactNo, String email, LocalDate dob, char gender, char custType) {
        this.ic = ic;
        this.password = password;
        this.name = name;
        this.contactNo = contactNo;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.custType = custType;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public char getCustType() {
        return custType;
    }

    // method to display the personal details
    @Override
    public String toString() {
        return """            
               Personal Details
               **********************************************\n"""+
                  "  IC                = " + ic
                + "\n  Password          = " + password
                + "\n  Name              = " + name
                + "\n  Contact Number    = " + contactNo
                + "\n  Email             = " + email
                + "\n  DOB               = " + dob
                + "\n  Gender            = " + gender;

    }


    public void custDetails() { // method to display and modify customer details
        char confirmation; //prompt confirmation           
        int option = 0;   //which info to change 
        String temp; //tempararily store the changed value
        Scanner sc = new Scanner(System.in);

        if (chkfile.chkfile() == 1) { // if file exists
            if (file.length() == 0) { // if file empty
                System.out.println("Customer File is empty");
            } else {// if file is not empty
                chkfile.getUser(); // get all the customer info
                customers.addAll(chkfile.getCustomer()); // add all customer info to arrayList

            }
        }
        int userCount;
        userCount = Login.getUserCount(); //get customer index from file via login

        do {
            int counter = 0; //counter to break loop
            System.out.println("\n*************************************************************************************");
            System.out.println("                                  ACCOUNT DETAILS                                      ");
            System.out.println("*************************************************************************************\n");
            
            System.out.println(toString() + "\n"); // display accuont details

            System.out.print("Do You Want To Edit Or Update Your Personal Details (Y/N) : ");
            confirmation = sc.next().charAt(0); // get confirmation input
            while (Character.toUpperCase(confirmation) != 'Y' && Character.toUpperCase(confirmation) != 'N') {
                System.out.print("Invalid Input, Please Try Again : ");
                confirmation = sc.next().charAt(0);
            }

            if (Character.toUpperCase(confirmation) == 'Y') {
                
                
                System.out.println("****************************");
                System.out.println("       Edit Menu            ");
                System.out.println("****************************");
                System.out.println("  1) Password               ");
                System.out.println("  2) Contact Number         ");
                System.out.println("  3) Email                  ");
                System.out.println("****************************");

                while (counter == 0) {
                    try {
                        System.out.print("Choose the option to edit : ");
                        option = sc.nextInt();
                        if (option < 1 || option > 3) {
                            System.out.print("Invalid Input, Please enter again.");
                        } else {
                            counter++;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input Type. Please Try Again.");
                        sc.next(); //consume the input token to prevent infinite loop
                    }
                }

                switch (option) {
                    case 1 -> {
                        do {
                            System.out.print("Enter Your New Password : ");
                            temp = sc.next();
                            if (!register.validatePassword(temp)) {
                                System.out.println("Invalid Password");
                                System.out.println("Please Enter A Password With 7 Characters Including Digits and Letters \n");
                            }
                        } while (!register.validatePassword(temp));
                        System.out.print("\nAre you sure you want to change from " + getPassword() + " to " + temp + "\n (Y/N)--> ");
                        confirmation = sc.next().charAt(0);
                        while (Character.toUpperCase(confirmation) != 'Y' && Character.toUpperCase(confirmation) != 'N') {
                            System.out.print("Invalid Input. Please Try Again.\n");
                            confirmation = sc.next().charAt(0);
                        }
                        if (Character.toUpperCase(confirmation) == 'Y') {
                            setPassword(temp);
                            if (!chkfile.fileEmpty()) {
                                customers.get(userCount).setPassword(temp);
                            }

                        }
                    }

                    case 2 -> {
                        do {
                            System.out.print("Enter Your New Contact Number : ");
                            temp = sc.next();
                            if (!register.validateContNo(temp)) {
                                System.out.println("Invalid Contact Number");
                                System.out.println("Please Enter Your Contact Number with 10-12 digit without dashes : \n");
                            }
                        } while (!register.validateContNo(temp));
                        System.out.print("\nAre you sure you want to change from " + getContactNo() + " to " + temp + " (Y/N)--> ");
                        confirmation = sc.next().charAt(0);
                        while (Character.toUpperCase(confirmation) != 'Y' && Character.toUpperCase(confirmation) != 'N') {
                            System.out.print("Invalid Input, Please Try Again. ");
                            confirmation = sc.next().charAt(0);
                        }
                        if (Character.toUpperCase(confirmation) == 'Y') {
                            setContactNo(temp);
                            if (!chkfile.fileEmpty()) {
                                customers.get(userCount).setContactNo(temp);
                            }

                        }
                    }
                    case 3 -> {
                        do {
                            System.out.print("Enter your new EMAIL--> ");
                            temp = sc.next();
                            if (!register.validateEmail(temp)) {
                                System.out.println("Invalid Email");
                                System.out.println("Please Enter Your Email");
                            }
                        } while (!register.validateEmail(temp));
                        System.out.print("\nAre you sure you want to change from " + getEmail() + " to " + temp + " (Y/N)--> ");
                        confirmation = sc.next().charAt(0);
                        while (Character.toUpperCase(confirmation) != 'Y' && Character.toUpperCase(confirmation) != 'N') {
                            System.out.print("Invalid Input, Please Enter Again : ");
                            confirmation = sc.next().charAt(0);
                        }
                        if (Character.toUpperCase(confirmation) == 'Y') {
                            setEmail(temp);

                            if (!chkfile.fileEmpty()) {
                                customers.get(userCount).setEmail(temp);
                            }

                        }
                    }

                }
                if (Character.toUpperCase(confirmation) == 'Y') {
                    chkfile.updateCustFile();
                }
            } else {
                break;
            }
        } while (Character.toUpperCase(confirmation) == 'Y');

    } 


}
