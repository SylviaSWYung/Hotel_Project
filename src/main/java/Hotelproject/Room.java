package Hotelproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*Room versjon 1: Tar inn HotelRoom.csv som en fil, og lese inn hver setning, for så å finne om kjede, destination
 * og roomnr er i csv. Deretter dersom dette er true, redigere den til false. 
 * 
 * Q: Kanskje lage dette på to ulike metoder og ikke en... Evt ikke benytte csv da det kan være vanskelig å redigere. 
 * Men da vil ikke de andre objektene vite at en endring har blitt gjennomført på csv filen. 
 * 
 * Status: under arbeid
 */

public class Room {

    public Room(){
    }

    /*Først hente ut informasjon fra HotelRoom.java
     * @return skal ikke returnere noe. 
     */

    public void roomInfo(String chain, String destination, String roomNr) throws IOException{    //Kaster fileNotFoundException for å benytte scanner
        File file = new File("HotelRoom.csv");
        Scanner scanner = new Scanner(file);                                    //Henter info fra HotelRoom.csv, vha Scanner 
        StringBuilder content = new StringBuilder();
        while(scanner.hasNextLine()){                                           //Benytter en while loop for å hente informasjon fra hver linje
            String[] newRoomListe = scanner.nextLine().split(";");        //Linjen i filen, splitter dette med ; 

            String hotelchain = newRoomListe[0].trim();                         //ny variabel, fra nyRoomListe hent hotelchain. Trim whitespace
            String place = newRoomListe[1].trim();
            String room = newRoomListe[2].trim();
            Boolean availability = Boolean.parseBoolean(newRoomListe[3].trim()); //Endrer til newRoomListe til en boolean

            String updatedLine = String.join(";", newRoomListe);
            content.append(updatedLine).append("\n");

            for(int i = 0; i <= updatedLine.length(); i++){
                if((hotelchain.toLowerCase().equals(chain.toLowerCase())) && 
                    place.toLowerCase().equals(destination.toLowerCase()) && 
                    room.toLowerCase().equals(roomNr.toLowerCase())){
                    System.out.println("Valgt hotell: " + chain + " sted: "+ destination + " og rom nr: " + roomNr);
                    if(availability){
                        System.out.println("Rommet er tilgjengelig");
                        //content.delete(16, 21);
                        //content.insert(16, "false");
                        break;
                    }else{
                        System.out.println("Rommet er utilgjengelig");
                        break;
                    }
                }
            }
            
        }
        FileWriter writer = new FileWriter(file);
        writer.write(content.toString());
        writer.close();
        scanner.close();
    }

    public boolean isAvailable(){   

        return true; 
    }

    public void addBooking(){
        
    }

    public void book(){

    }

    public static void main(String[] args) throws IOException{
        Room room1 = new Room();
        Room room2 = new Room();
        room1.roomInfo("Strawberry", "Trondheim", "2");
        room2.roomInfo("Strawberry", "Trondheim", "2");

    }

   
}
