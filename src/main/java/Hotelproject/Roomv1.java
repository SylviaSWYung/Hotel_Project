package Hotelproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * Status: under arbeid
 */

public class Roomv1 {

    public Roomv1(){
    }

    /*Først hente ut informasjon fra HotelRoom.java
     * @return skal ikke returnere noe. 
     */

    public void roomInfo(String chain, String destination, String roomNr) throws IOException{    //Kaster fileNotFoundException for å benytte scanner
        File file = new File("HotelRoom.csv");
        Scanner scanner = new Scanner(file);                                    //Henter info fra HotelRoom.csv, vha Scanner 
        StringBuilder content = new StringBuilder();
        boolean roomFound = false; 

        while(scanner.hasNextLine()){                                           //Benytter en while loop for å hente informasjon fra hver linje
            String[] newRoomListe = scanner.nextLine().split(";");        //Linjen i filen, splitter dette med ; 

            String hotelchain = newRoomListe[0].trim();                         //ny variabel, fra nyRoomListe hent hotelchain. Trim whitespace
            String place = newRoomListe[1].trim();
            String room = newRoomListe[2].trim();
            Boolean availability = Boolean.parseBoolean(newRoomListe[3].trim()); //Endrer til newRoomListe til en boolean
            String price = newRoomListe[4].trim();

            //Dersom hotelchian = chain, place = destination, room = roomNr. 
            if(hotelchain.equalsIgnoreCase(chain) && place.equalsIgnoreCase(destination) && room.equalsIgnoreCase(roomNr)){
                roomFound = true;
                System.out.println("Valgt hotell: " + chain + " sted: "+ destination + " og rom nr: " + roomNr);
                if (availability) {
                    System.out.println("Rommet er tilgjengelig");
                    availability = false;
                }else{
                    System.out.println("Rommet er utilgjengelig");
                }
            }

            String updatedLine = String.join(";", hotelchain, place, room, String.valueOf(availability), price);
            content.append(updatedLine).append("\n");
        }
        if(!roomFound){
            System.out.println("Rommet ble ikke funnet!");
        }


        FileWriter writer = new FileWriter(file);
        writer.write(content.toString());
        writer.close();
        scanner.close();
        
    }


    public static void main(String[] args) throws IOException{
        Roomv1 room1 = new Roomv1();
        Roomv1 room2 = new Roomv1();
        room1.roomInfo("Strawberry", "Trondheim", "2");
        room2.roomInfo("Strawberry", "Trondheim", "2");

    }

   
}
