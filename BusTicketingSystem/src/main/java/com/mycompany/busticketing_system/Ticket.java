package com.mycompany.busticketing_system;

public class Ticket {
    //this class is used to print the bus detail in the payment function

    private int destinationChoice;
    private int scheduleChoice;
    private int seatTypeChoice;
    private String destinationChoiceS;
    private String departureDate;
    private String seatClassChoiceS;
 
    //constructor
    public Ticket() {
    }
 
 
    public Ticket(int destinationChoice, int scheduleChoice, int seatTypeChoice) {
        this.destinationChoice = destinationChoice;
        this.scheduleChoice = scheduleChoice;
        this.seatTypeChoice = seatTypeChoice;
    }
 
    

    public int getDestinationChoice() {
        return destinationChoice;
    }

    public void setDestinationChoice(int destinationChoice) {
        this.destinationChoice = destinationChoice;
    }

    public int getScheduleChoice() {
        return scheduleChoice;
    }

    public void setScheduleChoice(int scheduleChoice) {
        this.scheduleChoice = scheduleChoice;
    }

    public int getSeatTypeChoice() {
        return seatTypeChoice;
    }

    public void setSeatTypeChoice(int seatTypeChoice) {
        this.seatTypeChoice = seatTypeChoice;
    }
    
 
  
    
    // return destination as string
    public String getdestinationChoiceS() {
        return destinationChoiceS;
    }
 
    // return type of seat class as string
    public String getSeatClassChoiceS() {
        return seatClassChoiceS;
    }
 
    public String getDepartureDate() {
        return departureDate;
    }
 
    // set the destination choice
    public void setdestinationChoiceS(int destinationChoice) {
        switch (destinationChoice) {
            case 1:
                destinationChoiceS = "Alor Setar";
                break;
            case 2:
                destinationChoiceS = "George Town";
                break;

            case 3:
                destinationChoiceS = "Kuantan";
                break;
        }
    }
 
    // set the type of seat class 
    public void setSeatClassChoiceS(int seatTypeChoice) {
        switch (seatTypeChoice) {
            case 1:
                seatClassChoiceS="Premium";
                break;
            case 2:
                seatClassChoiceS= "Deluxe";
                break;
            default:
                seatClassChoiceS="Economy";
        }
    }
 
    // set the departure date for each schedule that selected
    public void setscheduleChoiceS(int scheduleChoice, int destinationChoice) {
        switch (destinationChoice) {
            case 1:
                if (scheduleChoice == 1) {
                    departureDate = "01/10/2022";

                } else if (scheduleChoice == 2) {
                    departureDate = "01/10/2022";

                } else if (scheduleChoice == 3) {
                    departureDate = "01/10/2022";

                } else if (scheduleChoice == 4) {
                    departureDate = "02/10/2022";

                } else if (scheduleChoice == 5) {
                    departureDate = "02/10/2022";

                } else if (scheduleChoice == 6) {
                    departureDate = "02/10/2022";

                }
                break;
                case 2:
                if (scheduleChoice == 1) {
                    departureDate = "01/10/2022";

                } else if (scheduleChoice == 2) {
                    departureDate = "01/10/2022";

                } else if (scheduleChoice == 3) {
                    departureDate = "01/10/2022";

                } else if (scheduleChoice == 4) {
                    departureDate = "02/10/2022";

                } else if (scheduleChoice == 5) {
                    departureDate = "02/10/2022";

                } else if (scheduleChoice == 6) {
                    departureDate = "02/10/2022";

                }
                break;
                case 3:
                if (scheduleChoice == 1) {
                    departureDate = "01/10/2022";

                } else if (scheduleChoice == 2) {
                    departureDate = "01/10/2022";

                } else if (scheduleChoice == 3) {
                    departureDate = "01/10/2022";

                } else if (scheduleChoice == 4) {
                    departureDate = "02/10/2022";

                } else if (scheduleChoice == 5) {
                    departureDate = "02/10/2022";
                 
                } else if (scheduleChoice == 6) {
                    departureDate = "02/10/2022";
                    
                }
                break;
            
        }
      
    }
 
    @Override
    // method to display the bus details
    public String toString() {
        return """
               ******************************************************************
               Ticket Details : \n""" +
               "Destination       = " + destinationChoiceS
                + "\nDeparture Date    = " + departureDate              
                + "\nSeat Class        = " + seatClassChoiceS;
    }
 
   
 

}
