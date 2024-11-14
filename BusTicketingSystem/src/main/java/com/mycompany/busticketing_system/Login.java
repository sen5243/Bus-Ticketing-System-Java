package com.mycompany.busticketing_system;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {

    private static int userCount;

    public Login() {
    }

    // getter and setter
    public static int getUserCount() {
        return userCount;
    }

    public static void setUserCount(int userCount) {
        Login.userCount = userCount;
    }

    // method to display main menu and user choice
    public static void displayMainMenu() {

        int option = 0;
        int counter = 0; // to exit try loop
        Scanner input = new Scanner(System.in);

        Login userLogin = new Login();
        Register userRegister = new Register();
        CustomerFile chkfile = new CustomerFile();

        do {
            chkfile.chkfile();

            System.out.println("\n\n************************************************");
            System.out.println("       1.Login                                  ");
            System.out.println("       2.Register                               ");
            System.out.println("       3.Exit                                   ");
            System.out.println("************************************************");

            while (counter == 0) {
                try {
                    System.out.print("  Enter your selection : ");
                    option = input.nextInt();
                    counter++;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Type of Input, Please Try Again.");
                    input.next(); // to consume the input token (prevent infinite loops) 

                }

            }

            switch (option) {
                case 1 -> {
                    if (!chkfile.fileEmpty()) { //check is file empty (means no customer recorded)
                        userLogin.login();
                    } else {
                        System.out.println("The File is Empty. Please Register First Before Login.");
                        System.out.println("Redirecting to Registration Session ...");
                        userRegister.register(); // redirect to customer regstration
                    }
                }
                case 2 ->
                    userRegister.register();
                case 3 -> {
                    Welcome welcome = new Welcome();
                    Closure closure = new Closure();
                    welcome.message();
                    closure.message();
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid Option.Please Enter A Valid Option Between 1 to 3");
                    counter = 0;
                }
            }

        } while (option < 1 || option > 3);

    }

    // login method
    public void login() {
        Scanner sc = new Scanner(System.in);
        CustomerFile chkfile = new CustomerFile();
        int found = 0;
        System.out.println("\n*************************************************************************************");
        System.out.println("                                  USER LOGIN                                          ");
        System.out.println("*************************************************************************************\n");
        System.out.print("Please Enter Your IC: ");
        String ic = sc.nextLine().trim(); // Read user IC

        System.out.print("Password: ");
        String password = sc.nextLine().trim();// Read user Password

        chkfile.chkfile(); //check if file exists
        chkfile.getUser();//get customers infomation in file

        ArrayList<String> txtIC = new ArrayList<>();
        ArrayList<String> txtPass = new ArrayList<>();

        if (chkfile.chkfile() == 1 && !chkfile.fileEmpty()) { // if file exists and not empty
            for (int i = 0; i < chkfile.getCustomer().size(); i++) { //insert customer info to txtIC and txtPass
                txtIC.add(chkfile.getCustomer().get(i).getIc());
                txtPass.add(chkfile.getCustomer().get(i).getPassword());
            }
        }
        for (int i = 0; i < chkfile.getCustomer().size(); i++) {
            if (txtIC.get(i).equals(ic) && txtPass.get(i).equals(password)) { //check if both IC and Password are correct
                found++;
                setUserCount(i); //set index of that customer if found
            }
        }

        if (found > 0) { // if user is found
            System.out.println("Login Sucessfully.");
        } else {
            System.out.println("User not found");
            displayMainMenu();
        }
    }

}
