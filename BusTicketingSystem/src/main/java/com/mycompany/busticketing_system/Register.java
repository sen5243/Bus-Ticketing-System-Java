package com.mycompany.busticketing_system;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class Register {

    Scanner sc = new Scanner(System.in);
    CustomerFile filechk = new CustomerFile();

    // store data for registration
    private String ic;
    private String password;
    private String name;
    private String contNo;
    private String email;
    private String dob;
    private char custType;
    private char gender;
    int[] date = new int[3]; // store day, month , year of a date
    private char custTypeOption; // input for member registration confirmation
    private int existCount; // + 1 if found user if same ic
    int paymentChoice; // choice for payment

    Payment payMethod = new Payment();

    public Register() {
    }

    public Register(String ic, String password, String name, String contNo, String email, String dob, char gender, char custType) {
        this.ic = ic;
        this.password = password;
        this.name = name;
        this.contNo = contNo;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.custType = custType;
    }

    // getter and setter
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

    public String getContNo() {
        return contNo;
    }

    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getCustType() {
        return custType;
    }

    public void setCustType(char custType) {
        this.custType = custType;
    }

    // registration menu
    public void register() {
        filechk.chkfile();
        boolean memberpayResult;
        System.out.println("\n*************************************************************************************");
        System.out.println("                                  ACCOUNT REGISTER                                      ");
        System.out.println("*************************************************************************************\n");
        System.out.println("-----------------------PLEASE FILL IN YOUR PERSONAL INFORMATION----------------------");

        do {
            System.out.print("Enter name: ");
            name = sc.nextLine().trim();
            
            System.out.print("Enter ic (12 digits without dashes as stated in IC): ");
            ic = sc.nextLine().trim();
            if (!validateIc(ic)) {
                if (existCount > 0) {
                    System.out.println("This IC Number has been used, Please Try Again");

                } else {
                    System.out.println("Invalid IC Number Format, Please Try Again");
                }
            }
        } while (!validateIc(ic));

        do {
            System.out.print("Enter password (7 character include digit and letter): ");
            password = sc.next().trim();
            if (!validatePassword(password)) {
                System.out.println("Invalid Password.");
                System.out.println("Please enter password with 7 character include digits and letters \n");
            }

        } while (!validatePassword(password));

        do {
            System.out.print("Enter contact number (10-12 digit without '-') : ");
            contNo = sc.next().trim();
            if (!validateContNo(contNo)) {
                System.out.println("Invalid Contact Number");
                System.out.println("Please Enter contact number with 10-12 digit without dashes \n");
            }
        } while (!validateContNo(contNo));

        do {
            System.out.print("Enter email address : ");
            email = sc.next().trim();
            if (!validateEmail(email)) {
                System.out.println("Invalid Email, Please Try Again.");
            }
        } while (!validateEmail(email));

        do {
            System.out.print("Enter gender (M/F) : ");
            gender = Character.toUpperCase(sc.next().charAt(0));
            if (!validateGender(gender)) {
                System.out.println("Invalid Input, Please Try Again~\n");
            }
        } while (!validateGender(gender));

        do {
            System.out.print("Do you want to register as a member? (Y/N) ");
            custTypeOption = Character.toUpperCase(sc.next().charAt(0));
            if (!validateOption(custTypeOption)) {
                System.out.println("Please Enter a Valid Option.\n");
            }
        } while (!validateOption(custTypeOption));

        if (custTypeOption == 'Y') {

            do {
                System.out.println("\n*************************************************************************************");
                System.out.println("                                MEMBERSHIP SELECTION                                     ");
                System.out.println("*************************************************************************************\n");

                System.out.println("\n*********************************************************************");
                System.out.println("|            Payment Details                  |  PROMOTION PRICE(RM)|");
                System.out.println("*********************************************************************");
                System.out.println("|   1. 1 Month Membership                     |        30.00        |");
                System.out.println("|      (Monthly)                              |                     |");
                System.out.println("*********************************************************************");
                System.out.println("|   2. 1 Year Membership                      |        200.00       |");
                System.out.println("|      (Yearly)                               |                     |");
                System.out.println("*********************************************************************");
                System.out.println("|   3. Continue As Normal Customer            |        0.00         |");
                System.out.println("|      (FREE OF CHARGE)                       |                     |");
                System.out.println("*********************************************************************");

                int counter = 0;
                while (counter == 0) {
                    try {
                        System.out.print("\nEnter your choice (1-3): ");
                        paymentChoice = sc.nextInt();
                        counter++;
                    } catch (InputMismatchException ex) {
                        System.out.println("Invalid Input. Please Try Again");
                        sc.next(); // to consume the input token (prevent infinite loops
                    }
                }

                if (paymentChoice < 1 || paymentChoice > 3) {
                    System.out.println("Invalid Input. Please Try Again.\n");
                }

            } while (paymentChoice < 1 || paymentChoice > 3);

            if (paymentChoice != 3) {
                memberpayResult = payMethod.memberPay();
            } else {
                memberpayResult = false;
            }

            if (memberpayResult) {
                custType = 'M';
            } else {
                custType = 'N';
            }

        } else {
            custType = 'N';
        }

        addCustomer();
        System.out.println("Account Registered Sucessfully\n");
        Login.displayMainMenu();

    }

    // IC validation
    public boolean validateIc(String ic) {

        boolean validation;
        validation = ic.matches("(([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01]))([0-9]{2})([0-9]{4})"); //format for phone num

        if (validation) {
            dob = convertDate(ic);
            String dateArr[] = dob.split("-");

            for (int i = 0; i < dateArr.length; i++) {
                date[i] = Integer.parseInt(dateArr[i]);

            }
            if (!validateDob(date[2], date[1], date[0])) { //mainly used to check date of Feb
                validation = false;
                System.out.println("Invalid date format for IC, Please Try Again");
            }

            if (filechk.getUser()) {
                for (int i = 0; i < filechk.getCustomer().size(); i++) {
                    if (filechk.getCustomer().get(i).getIc().equals(ic)) { // check if the inputted IC exists in file
                        validation = false;
                        existCount = 1;
                    }
                }
            }

        }

        return validation;
    }

    public String convertDate(String ic) {
        String year = ic.substring(0, 2);
        String month = ic.substring(2, 4);
        String day = ic.substring(4, 6);

        if (Integer.parseInt(year) <= Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yy")))) {
            if (Integer.parseInt(year) < 10) {
                return "20" + year + '-' + month + '-' + day;

            } else {
                return "20" + year + '-' + month + '-' + day;
            }
        } else {
            return "19" + year + '-' + month + '-' + day;
        }
    }

    // password validation
    public boolean validatePassword(String password) {
        boolean validation;
        int digitCount = 0, letterCount = 0, invalidCount = 0;

        if (password.length() >= 7) {
            for (int i = 0; i < password.length(); i++) {
                if (Character.isDigit(password.charAt(i))) {
                    digitCount++;
                } else if (Character.isLetter(password.charAt(i))) {
                    letterCount++;
                } else {
                    invalidCount++;
                }
            }
        }

        if (invalidCount > 0) {
            validation = false;
        } else {
            validation = digitCount > 0 && letterCount > 0;
        }

        return validation;
    }

    // contact number validation
    public boolean validateContNo(String contNo) {
        boolean validation = true;

        if (contNo.length() > 12 || contNo.length() < 10) {
            validation = false;
        } else {
            for (int i = 0; i < contNo.length(); i++) {
                if (Character.isDigit(contNo.charAt(i))) {
                    validation = true;
                } else {
                    validation = false;
                    break;
                }
            }
        }

        return validation;
    }

    // email validation
    public boolean validateEmail(String email) {

        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    // gender validation
    public boolean validateGender(char gender) {

        return gender == 'F' || gender == 'M';
    }

    //option validation
    public boolean validateOption(char option) {

        return option == 'N' || option == 'Y';
    }

    // method to validate Date of brith
    public boolean validateDob(int day, int month, int year) {
        if (year == LocalDate.now().getYear()) {                        //year = current year
            if (month == LocalDate.now().getMonthValue()) {             // month = current month
                if (day < LocalDate.now().getDayOfMonth() && day >= 1) {
                    return true;
                } else {
                    return false;
                }
            } else if (month < LocalDate.now().getMonthValue() && month >= 1 && day >= 1 && day <= 31) {    // month < current month
                if ((month == 2 && year % 4 == 0 && day >= 1 && day <= 29) || (month == 2 && year % 4 != 0 && day >= 1 && day <= 28)) {
                    return true;
                } else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31) {
                    return true;
                } else if ((month == 4 || month == 6 || month == 9 || month == 11) && (day <= 30 && day > 0)) {
                    return true;
                } else {
                    return false;
                }
            } else {

                return false;
            }
        } else if (year >= 1000 && year < LocalDate.now().getYear() && month >= 1 && month <= 12 && day >= 1 && day <= 31) {
            if ((month == 2 && year % 4 == 0 && day >= 1 && day <= 29) || (month == 2 && year % 4 != 0 && day >= 1 && day <= 28)) {
                return true;
            } else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day <= 31) {
                return true;
            } else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 30) {
                return true;
            } else {

                return false;

            }
        } else {

            return false;
        }

    }

    // add customer details to the text file
    public void addCustomer() {

        try {
            FileWriter inputFile = new FileWriter("customer.txt", true);
            inputFile.write(ic + ',' + password + ',' + name + ',' + contNo + ',' + email + ',' + dob + ',' + gender + ',' + custType + '\n');
            inputFile.close();
            System.out.println("File Saved Sucessfully.\n");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
        }
    }

}
