/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busticketing_system;

/**
 *
 * @author Human
 */
public class BusDisplay {
        public static void destinations() {
        System.out.println("Destinations Available:\n ");
        System.out.println("1. Alor Setar");
        System.out.println("2. George Town");
        System.out.println("3. Kuantan");
        System.out.print("\nSelect a destination (1/2/3): ");
    }

    // method to display seat class detail
    public static void seatClassDetails() {
        System.out.print("\n\t\t\t\t\t\tSeat Class");
        System.out.print("\n*******************************************************************************************************\n");
        System.out.print("|   Type of Seat     | Price(RM) |                             Description                            |\n");
        System.out.print("*******************************************************************************************************\n");
        System.out.print("| 1. Premium         | 100.00    | Complimentary food and drinks                                      |\n");
        System.out.print("|                                |                                                                    |\n");
        System.out.print("*******************************************************************************************************\n");
        System.out.print("| 2. Deluxe          |  75.00    | Complimentary drinks                                               |\n");
        System.out.print("|                                |                                                                    |\n");
        System.out.print("*******************************************************************************************************\n");
        System.out.print("| 3. Economy         |  30.00    | Classic seat with no additional features                           |\n");
        System.out.print("|                                |                                                                    |\n");
        System.out.print("*******************************************************************************************************\n");
    }

    // method to display bus schedules
    public static void alorSetarSchedule(int[] seat) {
        System.out.print("\n\t\t\t\t\t\t\tAlor Setar Bus Schedule \n");
        System.out.print("**************************************************************************************************************************************\n");
        System.out.print("  No |    Date    |         Destination          | Departure Time | Arrival Time |      Duration      | Price(RM)| Seat(s) Available \n");
        System.out.print("**************************************************************************************************************************************\n");
        System.out.printf("  1. | 01/10/2022 | Shahab Perdana Bus Terminal  |  4:00 a.m.     |  9:25 a.m.   | 5 hours 25 minutes | 23.00    |        %d         \n", seat[0]);
        System.out.printf("  2. | 01/10/2022 | Shahab Perdana Bus Terminal  |  6:00 a.m.     | 11:40 p.m.   | 5 hours 40 minutes | 23.00    |        %d         \n", seat[1]);
        System.out.printf("  3. | 01/10/2022 | Shahab Perdana Bus Terminal  |  8:00 a.m.     |  1:55 p.m    | 5 hours 55 minutes | 23.00    |        %d         \n", seat[2]);
        System.out.printf("  4. | 02/10/2022 | Shahab Perdana Bus Terminal  |  3:00 a.m.     |  8:10 a.m.   | 5 hours 10 minutes | 23.00    |        %d         \n", seat[3]);
        System.out.printf("  5. | 02/10/2022 | Shahab Perdana Bus Terminal  |  8:00 p.m.     |  1:30 p.m.   | 5 hours 30 minutes | 26.00    |        %d         \n", seat[4]);
        System.out.printf("  6. | 02/10/2022 | Shahab Perdana Bus Terminal  |  9:00 p.m.     |  2:50 p.m.   | 5 hours 50 minutes | 26.00    |        %d         \n", seat[5]);
        System.out.print("**************************************************************************************************************************************\n");
    }

    public static void georgeTownSchedule(int[] seat) {
        System.out.print("\n\t\t\t\t\t\t\tGeorge Town Bus Schedule \n");
        System.out.print("*************************************************************************************************************************************\n");
        System.out.print("  No |    Date    |         Destination         | Departure Time | Arrival Time |      Duration      | Price(RM)| Seat(s) Available  \n");
        System.out.print("*************************************************************************************************************************************\n");
        System.out.printf("  1. | 01/10/2022 | Sungai Nibong Bus Terminal  |  4:00 a.m.     |  8:20 a.m.   | 4 hours 20 minutes | 20.00    |        %d         \n", seat[6]);
        System.out.printf("  2. | 01/10/2022 | Sungai Nibong Bus Terminal  |  6:00 a.m.     | 10:25 a.m.   | 4 hours 25 minutes | 20.00    |        %d         \n", seat[7]);
        System.out.printf("  3. | 01/10/2022 | Sungai Nibong Bus Terminal  |  8:00 a.m.     | 12:45 p.m    | 4 hours 45 minutes | 20.00    |        %d         \n", seat[8]);
        System.out.printf("  4. | 02/10/2022 | Sungai Nibong Bus Terminal  | 10:00 a.m.     |  2:55 p.m.   | 4 hours 55 minutes | 23.00    |        %d         \n", seat[9]);
        System.out.printf("  5. | 02/10/2022 | Sungai Nibong Bus Terminal  |  8:00 p.m.     | 12:45 a.m.   | 4 hours 45 minutes | 23.00    |        %d         \n", seat[10]);
        System.out.printf("  6. | 02/10/2022 | Sungai Nibong Bus Terminal  |  9:00 p.m.     |  2:15 a.m.   | 5 hours 15 minutes | 23.00    |        %d         \n", seat[11]);
        System.out.print("*************************************************************************************************************************************\n");
    }

    public static void kuantanSchedule(int[] seats) {
        System.out.print("\n\t\t\t\t\t\t\tKuantan Bus Schedule \n");
        System.out.print("************************************************************************************************************************************\n");
        System.out.print("  No |    Date    |        Destination        | Departure Time | Arrival Time  |      Duration      | Price(RM)| Seat(s) Available  \n");
        System.out.print("************************************************************************************************************************************\n");
        System.out.printf("  1. | 01/10/2022 | Terminal Kuantan Sentral  |  4:00 a.m.     |  8:00 a.m.    | 4 hours            | 20.00    |        %d         \n", seats[12]);
        System.out.printf("  2. | 01/10/2022 | Terminal Kuantan Sentral  |  6:00 a.m.     | 10:00 a.m.    | 4 hours            | 20.00    |        %d         \n", seats[13]);
        System.out.printf("  3. | 01/10/2022 | Terminal Kuantan Sentral  |  8:00 a.m.     |  1:00 p.m     | 5 hours            | 20.00    |        %d         \n", seats[14]);
        System.out.printf("  4. | 02/10/2022 | Terminal Kuantan Sentral  | 10:00 a.m.     |  3:00 p.m.    | 5 hours            | 23.00    |        %d         \n", seats[15]);
        System.out.printf("  5. | 02/10/2022 | Terminal Kuantan Sentral  |  8:00 p.m.     | 12:30 a.m.    | 4 hours 30 minutes | 23.00    |        %d         \n", seats[16]);
        System.out.printf("  6. | 02/10/2022 | Terminal Kuantan Sentral  | 10:00 p.m.     |  2:00 a.m.    | 4 hours            | 23.00    |        %d         \n", seats[17]);
        System.out.print("************************************************************************************************************************************\n");
    }
}
