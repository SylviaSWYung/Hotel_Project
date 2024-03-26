package Hotelproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Room {
    private int amountofDays;
    /*Liste av rom nr
     * addbooking()
     * book()
     * isAvaiable()
     */

    public Room(){
        
    }

    /*Først hente ut informasjon fra HotelRoom.java
     * @return skal ikke returnere noe. 
     */
    public void roomInfo() throws FileNotFoundException{
        File file = new File("HotelRoom.csv");
        Scanner scanner = new Scanner(file);                 //Henter info fra HotelRoom.csv, vha Scanner 
        while(scanner.hasNextLine()){                                          //Benytter en while loop for å hente informasjon fra hver linje
            String[] newRoomListe = scanner.nextLine().split(";");        //Linjen i filen, splitter dette med ; 

            String hotelchain = newRoomListe[0].trim();                         //ny variabel, fra nyRoomListe hent hotelchain. Trim whitespace
            String place = newRoomListe[1].trim();
            int room1 = Integer.parseInt(newRoomListe[2].trim());
            Boolean availability = Boolean.parseBoolean(newRoomListe[3].trim());

            System.out.println(hotelchain);
            System.out.println(place);
            System.out.println(room1);
            System.out.println(availability);
            System.out.println();
        }
        scanner.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Room room = new Room();
        room.roomInfo();
    }

   
}
