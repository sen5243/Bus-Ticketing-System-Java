package com.mycompany.busticketing_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerFile { //store and modify customer details

    private Scanner fileInput;
    private ArrayList<Customer> customer = new ArrayList<>(); //aggregation

    public CustomerFile() {
        
    }

    public ArrayList<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(ArrayList<Customer> customer) {
        this.customer = customer;
    }

    public boolean fileEmpty(){ //check if file is empty
         File file = new File("customer.txt");
         return file.length() == 0;
    }
    public int chkfile() { // check if file exsits
        File file = new File("customer.txt");
        if (!file.exists()) {
            System.out.println("File Not Found. Creating new file...");
            try {
                if (file.createNewFile()) {
                    System.out.println("File Created");
                    return 0;
                } else {
                    return 1;
                }

            } catch (IOException ex) {
                System.out.println("An error occured while creating the file. Continue to the next process without saving file...");
                return 0;
            }
        }
        return 1;
    }

    public boolean getUser() { //get user info from file to array list
        int i = 0; // counter
        try {
            File file = new File("customer.txt");
            fileInput = new Scanner(file);

            if (file.length() != 0) {
                while (fileInput.hasNextLine()) { // loop through the file and store customers info 
                    String[] split = fileInput.nextLine().split(",");
                    customer.add(new Customer(split[0], split[1], split[2], split[3], split[4], LocalDate.parse(split[5]), split[6].charAt(0),split[7].charAt(0)));
                    i++;
                }
                return true;
            } else {
                return false;
            }

        } catch (FileNotFoundException | NullPointerException error) {
            System.out.println("File not found.");

        } finally {
            fileInput.close();
        }

        return true;
    }
        public void updateCustFile() {// overwrite file while customer details  changed

        try {
            FileWriter inputFile = new FileWriter("customer.txt");
            for (int i = 0; i < getCustomer().size(); i++) {
                inputFile.write(getCustomer().get(i).getIc() + ',' + getCustomer().get(i).getPassword() + ',' + getCustomer().get(i).getName() + ',' + getCustomer().get(i).getContactNo() + ',' + getCustomer().get(i).getEmail() + ',' + getCustomer().get(i).getDob() + ',' + getCustomer().get(i).getGender() + ',' + getCustomer().get(i).getCustType() + '\n');
            }
            inputFile.close();
            System.out.println("File Updated.");

        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
        }

    }

}
