/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busticketing_system;

/**
 *
 * @author Human
 */
public class BusFees {

    private int destinationChoice;
    private int scheduleChoice;
    private int seatTypeChoice;
    private int seatQty;
    private double seatClassFees;
    private double busTicketFees;

    public BusFees() {
    }

    public BusFees(int destinationChoice, int scheduleChoice, int seatTypeChoice, int seatQty) {
        this.destinationChoice = destinationChoice;
        this.scheduleChoice = scheduleChoice;
        this.seatTypeChoice = seatTypeChoice;
        this.seatQty = seatQty;
    }

    public int getSeatQty() {
        return seatQty;
    }

    public void setSeatQty(int seatQty) {
        this.seatQty = seatQty;
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

    public double getSeatClassFees() {
        return seatClassFees;
    }

    public double getBusTicketFees() {
        return busTicketFees;
    }

    // method to set seat class fees
    public void setSeatClassFees(int seatTypeChoice) {
        switch (seatTypeChoice) {
            case 1:
                seatClassFees = 100.0;
                break;
            case 2:
                seatClassFees = 75.0;
                break;
            default:
                seatClassFees = 30.0;
        }
    }

    // method to set ticket price
    public void setTicketFees(int destinationChoice, int scheduleChoice) {
        switch (destinationChoice) {
            case 1:
                switch (scheduleChoice) {
                    case 1,2,3,4:
                        busTicketFees = 23.0;             
                        break;
                    case 5,6:
                        busTicketFees = 26.0;
                        break;
                }
                break;

            case 2:
                switch (scheduleChoice) {
                    case 1,2,3:
                        busTicketFees = 20.0;
                        break;
                    case 4,5,6:
                        busTicketFees = 23.0;
                        break;
                }
                break;

            case 3:
                switch (scheduleChoice) {
                    case 1,2,3:
                        busTicketFees = 20.0;
                        break;
                    default:
                        busTicketFees = 23.0;
                }
        }
    }
    
    @Override
    // method to display the reservation fees details
    public String toString() {
        return "\nBus Fees : "
                + "\nSeat Quantity      = " + seatQty
                + "\nBus Ticket Fees    = RM" + busTicketFees
                + "\nSeat Class Fees    = RM" + seatClassFees
                + "\nTotal Fees         = RM" + (busTicketFees+seatClassFees)*seatQty              
                + "\n******************************************************************";
    }

}
