package com.mycompany.busticketing_system;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Booking {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // Object declaration
        Ticket[] ticket = new Ticket[100];
        BusFees[] busfees = new BusFees[100];
        Payment[] payments = new Payment[100];

        int ticketCount = 0;

        //reservation
        int[] seatCount = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};

        int resumeBook;


        //payment
  
        int[] payIndex = new int[100];
        int choice = 0;
        Welcome welcome = new Welcome();
        
        //main program
        welcome.message(); // program start here with a welcome message
        Login.displayMainMenu(); //display login and registration selection
        do {

            CustomerFile chkfile = new CustomerFile();
            chkfile.chkfile();
            chkfile.getUser();
            int userCount = 0;
            int counter = 0; //counter to break loop

            userCount = Login.getUserCount();//get customer index 
            Customer customer = new Customer();

            if (!chkfile.fileEmpty()) { //if file exists
                if (chkfile.getCustomer().get(userCount).getCustType() == 'M') {
                    customer = new Member(chkfile.getCustomer().get(userCount).getIc(), chkfile.getCustomer().get(userCount).getPassword(), chkfile.getCustomer().get(userCount).getName(), chkfile.getCustomer().get(userCount).getContactNo(), chkfile.getCustomer().get(userCount).getEmail(), chkfile.getCustomer().get(userCount).getDob(), chkfile.getCustomer().get(userCount).getGender(), chkfile.getCustomer().get(userCount).getCustType());
                } else {
                    customer = new Normal(chkfile.getCustomer().get(userCount).getIc(), chkfile.getCustomer().get(userCount).getPassword(), chkfile.getCustomer().get(userCount).getName(), chkfile.getCustomer().get(userCount).getContactNo(), chkfile.getCustomer().get(userCount).getEmail(), chkfile.getCustomer().get(userCount).getDob(), chkfile.getCustomer().get(userCount).getGender(), chkfile.getCustomer().get(userCount).getCustType());

                }
            } else { // if file corrupted
                System.out.println("File corrupted. Redirecting to the main menu.");
                Login.displayMainMenu();
            }

            welcome.message();
            System.out.println("\n                          Welcome to Sani Express Ticket Reservation System!!! \n");
            System.out.println("**************************************");
            System.out.println("     Sani Express Main Menu         ");
            System.out.println("**************************************");
            System.out.println("      1)Account Details             ");
            System.out.println("      2)Reservation                 ");
            System.out.println("      3)Payment                     ");
            System.out.println("      4)Back                        ");
            System.out.println("**************************************");

            while (counter == 0) {
                try {
                    System.out.print("\nPlease Enter Your Choice :");
                    choice = input.nextInt();
                    if (choice < 1 || choice > 4) {
                        System.out.print("Invalid, Please enter again!! : \n");
                    } else {
                        counter++;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Type of Input, Please Try Again.");
                    input.next(); // to consume the input token (prevent infinite loops) 

                }
            }

            switch (choice) {
                case 1 ->
                    customer.custDetails();
                case 2 -> {
                    do {
                        reservation(seatCount, ticket, ticketCount, busfees, payments);
                        ticketCount++;
                        do {
                            System.out.print("Do you want to book another seat reservation? (1 = Yes / 2 = No): ");
                            resumeBook = input.nextInt();
                            if (resumeBook != 1 && resumeBook != 2) {
                                System.out.print("Invalid choice! Please select 1 or 2 only.\n");
                            }
                        } while (resumeBook != 1 && resumeBook != 2);
                    } while (resumeBook == 1);
                }
                case 3 ->
                    payment(ticket, busfees, payments, ticketCount, payIndex, customer);
                case 4 ->
                    Booking.main(args);
                default -> {
                }
            }

        } while (choice != 4);
    }

    public static void reservation(int[] seatCount, Ticket[] ticket, int ticketCount, BusFees[] busfees, Payment[] payments) {
        int destinationChoice = 0;
        int seatTypeChoice = 0;
        int scheduleChoice = 0;
        int seatQty = 0;
        int counter = 0;
        int counter2 = 0;
        int i;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n*************************************************************************************");       
        System.out.println("                                  RESERVATION                                          ");
        System.out.println("*************************************************************************************");

        BusDisplay.destinations();

        //validate destination choice
        while (counter == 0) {
            try {
                destinationChoice = sc.nextInt();
                if (destinationChoice != 1 && destinationChoice != 2 && destinationChoice != 3) {
                    System.out.println("\nInvalid input.\n");
                    System.out.print("Select a destination (1/2/3): ");
                } else {
                    counter++;
                }
            } catch (Exception e) {
                System.out.println("\nInvalid input.\n");
                System.out.print("Select a destination (1/2/3): ");
                sc.next();
            }
        }

        // switching destination choice
        switch (destinationChoice) {
            case 1:
                BusDisplay.alorSetarSchedule(seatCount);
                break;
            case 2:
                BusDisplay.georgeTownSchedule(seatCount);
                break;
            default:
                BusDisplay.kuantanSchedule(seatCount);
                break;
        }

        counter = 0;
        System.out.print("\nSelect a schedule (1-6): ");

        //validate schedule choice
        while (counter == 0) {
            try {
                scheduleChoice = sc.nextInt();
                if (scheduleChoice < 1 || scheduleChoice > 6) {
                    System.out.println("\nInvalid input.\n");
                    System.out.print("Select a schedule (1-6): ");

                } else {
                    counter++;
                }
            } catch (Exception e) {
                System.out.println("\nInvalid input.\n");
                System.out.print("Select a schedule (1-6): ");
                sc.next();
            }
        }

        BusDisplay.seatClassDetails();

        //validate seat choice
        counter = 0;
        System.out.print("\nSelect a seat type (1/2/3): ");
        while (counter == 0) {
            try {
                seatTypeChoice = sc.nextInt();
                if (seatTypeChoice != 1 && seatTypeChoice != 2 && seatTypeChoice != 3) {
                    System.out.println("\nInvalid input.\n");
                    System.out.print("\nSelect a seat type (1/2/3): ");
                    counter = 0;
                } else {
                    counter++;
                }
            } catch (Exception e) {
                System.out.println("\nInvalid input.\n");
                System.out.print("\nSelect a seat type (1/2/3): ");
                sc.next();
            }
        }


        counter = 0;
        System.out.print("\nEnter seat quantity: ");

        //validate seat quantity 
        while (counter == 0) {
            while (counter2 == 0) {
                try {
                    seatQty = sc.nextInt();
                    counter2++;
                } catch (Exception e) {
                    System.out.println("\nInvalid input.\n");
                    System.out.print("\nSelect seat quantity: ");
                    sc.next();
                }
            }

            switch (destinationChoice) {
                case 1:

                    switch (scheduleChoice) {
                        case 1:
                            i = 0;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 2:
                            i = 1;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 3:
                            i = 2;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 4:
                            i = 3;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 5:
                            i = 4;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 6:
                            i = 5;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;
                    }
                    break;

                case 2:
                    switch (scheduleChoice) {
                        case 1:
                            i = 6;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 2:
                            i = 7;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 3:
                            i = 8;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 4:
                            i = 9;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 5:
                            i = 10;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 6:
                            i = 11;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                    }
                    break;

                case 3:
                    switch (scheduleChoice) {
                        case 1:
                            i = 12;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 2:
                            i = 13;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 3:
                            i = 14;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 4:
                            i = 15;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 5:
                            i = 16;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                        case 6:
                            i = 17;
                            seatQty = BusDisplayValidate.seatQuantityVal(seatQty, seatCount, i);
                            break;

                    }
            }
            counter++;
        }

     
        // save user input into constructor
        ticket[ticketCount] = new Ticket(destinationChoice, scheduleChoice, seatTypeChoice);
        busfees[ticketCount] = new BusFees(destinationChoice, scheduleChoice, seatTypeChoice, seatQty);

        // set the user choice to show their payment details
        ticket[ticketCount].setdestinationChoiceS(ticket[ticketCount].getDestinationChoice());
        ticket[ticketCount].setscheduleChoiceS(ticket[ticketCount].getScheduleChoice(), ticket[ticketCount].getDestinationChoice());
        ticket[ticketCount].setSeatClassChoiceS(ticket[ticketCount].getSeatTypeChoice());
 

        busfees[ticketCount].setTicketFees(busfees[ticketCount].getDestinationChoice(), busfees[ticketCount].getScheduleChoice());
        busfees[ticketCount].setSeatClassFees(busfees[ticketCount].getSeatTypeChoice());

        String id;
        double fees;

        id = "Z" + (ticketCount + 1);
        fees = ((busfees[ticketCount].getSeatClassFees() + busfees[ticketCount].getBusTicketFees()) * seatQty);
        payments[ticketCount] = new Payment(id, fees);

        // print out seat reservation details
        System.out.println("\nReservation Details :");
        System.out.println(ticket[ticketCount].toString());
        System.out.println(busfees[ticketCount].toString());

    }

    // payment method
    public static void payment(Ticket[] ticket, BusFees[] busfees, Payment[] payments, int ticketCount, int[] payIndex, Customer customer) {

        Scanner sc = new Scanner(System.in);
        int select;
        int payChoice;
        String payID;
        boolean confirm = false;
        double totalFees = 0;
        LocalDate date = LocalDate.now();
        char again = ' ';
        double discountRate;
        double discount;
        double totalAmount;

        OUTER_1:
        do {
            PaymentMenu();
            payChoice = sc.nextInt();
            int checkMethod = 0;
            //payment
            switch (payChoice) {
                case 1 -> {
                    for (int i = 0; i < ticketCount; i++) {
                        if (payments[i].getPaymentMethod().equals(" ")) { // checking the paymentmethod to check the payment is 
                            checkMethod++;                                //already paid or not if user click 1 again
                        }
                    }
                    if (checkMethod > 0) {                        
                        System.out.println("\n************************************************************************");
                        System.out.println("Payment ID|Payment Amount|Destination  |Departure Date|   Seat Class  ");
                        System.out.println("************************************************************************");
                        

                        for (int i = 0; i < ticketCount; i++) {
                            if (payments[i].getPaymentMethod().equals(" ")) {
                                System.out.printf("%-10s|RM%10.2f%2s|", payments[i].getPaymentID(), payments[i].getPaymentAmount(), "");
                                System.out.printf("%-14s|%-13s|%-16s \n", ticket[i].getdestinationChoiceS(), ticket[i].getDepartureDate(), ticket[i].getSeatClassChoiceS());
                                System.out.println("************************************************************************");
                            }
                        }
                    } else {
                        System.out.println("\nNo Payment Left.\n");
                        confirm = false;
                    }
                    int count = 0;
                    if (checkMethod > 0) {

                        do {
                            confirm = false;
                            System.out.print("Enter Payment ID :");
                            payID = sc.nextLine() + sc.nextLine();
                            for (int i = 0; i < ticketCount; i++) {
                                if (payID.equals(payments[i].getPaymentID()) && payments[i].getPaymentMethod().equals(" ")) {
                                    confirm = true;
                                    payIndex[count] = i;
                                    count++;
                                    checkMethod--;
                                    break;
                                }
                            }
                            if (confirm == false) {
                                System.out.println("\nInvalid Value! Try Again\n");
                                System.out.print("Try again(Y/N) :");
                                again = sc.next().charAt(0);
                            } else if (confirm == true && checkMethod > 0) {
                                System.out.print("Do you want to pay other payments(Y/N) :");
                                again = sc.next().charAt(0);
                            } else {
                                break;
                            }
                            while (Character.toUpperCase(again) != 'Y' && Character.toUpperCase(again) != 'N') {
                                System.out.println("\nInvalid Value!Try Again : \n");
                                again = sc.next().charAt(0);
                            }
                        } while (Character.toUpperCase(again) == 'Y');
                    }
                    if (confirm == true) {

                        System.out.println("\n                 Payment Details");
                        System.out.println("********************************************************");
                        for (int x = 0; x < count; x++) {
                            System.out.print("Payment ID                 = ");
                            System.out.println(payments[payIndex[x]].getPaymentID());
                            System.out.print("SeatClassFees              = ");
                            System.out.println(busfees[payIndex[x]].getSeatClassFees());
                            System.out.print("Schedule                   = ");
                            System.out.println(busfees[payIndex[x]].getBusTicketFees());
                            System.out.print("Pay Amount                 = ");
                            System.out.println("RM" + payments[payIndex[x]].getPaymentAmount());
                            totalFees += payments[payIndex[x]].getPaymentAmount();
                        }

                        discountRate = customer.getDiscountRate();
                        discount = totalFees * discountRate;
                        totalAmount = totalFees - discount;
                        System.out.print("Total                      = ");
                        System.out.println("RM" + totalFees);
                        System.out.printf("Discount(%2d%%)              = ", (int)(discountRate * 100));
                        System.out.printf("RM%.2f" , discount);
                        System.out.print("\nTotal need to pay          = ");
                        System.out.println("RM" + totalAmount);
                        totalFees = 0;
                        OUTER:
                        do {
                            PaymentChoice();
                            select = sc.nextInt();
                            switch (select) {
                                case 1 -> {
                                    for (int x = 0; x < payIndex.length; x++) {
                                        payments[payIndex[x]].setpaymentMethod("Credit Card");//set the paymethod so that if user want 
                                    }                                                         //to pay again it will minus the payment that been paid
                                    credit();
                                }
                                case 2 -> {
                                    for (int x = 0; x < payIndex.length; x++) {
                                        payments[payIndex[x]].setpaymentMethod("E Wallet");//set the paymethod so that if user want 
                                    }                                                     //to pay again it will minus the payment that been paid
                                    ewallet();
                                }
                                case 3 -> {
                                    break OUTER;
                                }
                                default ->
                                    System.out.println("\nInvalid input. Please Try Again.\n");
                            }
                        } while (select < 1 || select > 3);
                        if (select != 3) {
                            System.out.println("Successfully paid. \n");
                            for (int x = 0; x < payIndex.length; x++) {
                                payments[payIndex[x]].setpaymentDate(date.toString());//to store the latest date for receipt purpose
                            }
                            payments[0].receipt(ticket, count, busfees, payments, payIndex, customer);//for receipt
                        }
                    }
                }
                case 2 -> {
                    break OUTER_1;
                }
                default ->
                    System.out.println("\nInvalid Input, Please Try Again.\n");
            }
            //back
        } while (payChoice != 2);

    }

    public static void PaymentMenu() {
        System.out.println("\n*************************************************************************************");
        System.out.println("                                   PAYMENT                                             ");
        System.out.println("*************************************************************************************\n");
       

        
        System.out.println("\n***************************");
        System.out.println("      Payment Options      ");
        System.out.println("***************************");
        System.out.println("    1. Pay                 ");
        System.out.println("    2. Back                ");
         System.out.println("***************************");
        System.out.print("\nEnter your selection :");
    }

    public static void PaymentChoice() {
        System.out.println("\n*******************************");
        System.out.println("        Payment Mode           ");
        System.out.println("*******************************");
        System.out.println("   1. Credit Card(Visa)        ");
        System.out.println("   2. E Wallet                 ");
        System.out.println("   3. Back                     ");
        System.out.println("********************************");
        System.out.print("\nEnter Payment Mode :");
    }

    public static void credit() {
        String creditCardNum;
        CreditCard credit = new CreditCard();
        Scanner sc = new Scanner(System.in);
        int cvv;
        int expMonth;
        int expYear;
        int bank;

        do {
            System.out.print("1.MAYBANK\n2.CIMB\n3.RHB\n4.PUBLIC BANK\n ");
            System.out.print("Choose your Credit Card bank: ");
            bank = sc.nextInt();
            credit.validBank(bank);
            if (credit.validBank(bank) == false) {
                System.out.println("\nInvalid Input. Please Try Again.\n");
            }
        } while (credit.validBank(bank) == false);

        System.out.println("*************************************************************************************");

        do {
            System.out.print("Enter Credit Card Number : ");
            creditCardNum = sc.next();
            credit.setCreditCardNum(creditCardNum);
            if (credit.validCreditCardNum() == false) {
                System.out.println("\nInvalid Card Number.Please Try Again. \n");
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
            }
        } while (credit.validDate(expMonth, expYear) == false);

    }

    public static void ewallet() {
        String phone;
        String password;
        Ewallet ew = new Ewallet();
        Scanner sc = new Scanner(System.in);
        int type;

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
            }
        } while (ew.validPassword() == false);

    }

}
