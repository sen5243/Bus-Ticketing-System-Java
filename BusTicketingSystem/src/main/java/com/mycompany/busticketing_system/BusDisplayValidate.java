/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busticketing_system;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Human
 */
public class BusDisplayValidate {

    public static int seatQuantityVal(int seatQty, int[] seatCount, int i) {
        Scanner sc = new Scanner(System.in);    
        while (seatQty < 0 || seatQty > seatCount[i]) {
            int counter = 0;
            System.out.println("\nInvalid input.\n");
            while (counter == 0) {
                try {
                    System.out.print("\nEnter seat quantity: ");
                    seatQty = sc.nextInt();
                    counter++;
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid input.\n");
                    sc.next();
                }

            }

        }
        
        seatCount[i] -= seatQty;
        return seatQty;
    }

}
