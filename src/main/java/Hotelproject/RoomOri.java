package Hotelproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*Originalt, room.java. Her implementeres ikke endring på csv filen. Tanken er å benytte en annen metode... Men er i tenke fasen. 
 * 
 * Status: Skal ikke endres. Går tilbake til denne dersom andre filer har fått større endringer.
 */

public class RoomOri {

    public RoomOri(){
    }

    /*Først hente ut informasjon fra HotelRoom.java
     * @return skal ikke returnere noe. 
     */

    public void roomInfo(String chain, String destination, String roomNr) throws FileNotFoundException{    //Kaster fileNotFoundException for å benytte scanner
        File file = new File("HotelRoom1.txt");
        Scanner scanner = new Scanner(file);                                    //Henter info fra HotelRoom.csv, vha Scanner 
        while(scanner.hasNextLine()){                                           //Benytter en while loop for å hente informasjon fra hver linje
            String[] newRoomListe = scanner.nextLine().split(";");        //Linjen i filen, splitter dette med ; 

            String hotelchain = newRoomListe[0].trim();                         //ny variabel, fra nyRoomListe hent hotelchain. Trim whitespace
            String place = newRoomListe[1].trim();
            String room = newRoomListe[2].trim();
            Boolean availability = Boolean.parseBoolean(newRoomListe[3].trim()); //Endrer til newRoomListe til en boolean

            for(int i = 0; i <= newRoomListe.length; i++){
                if((hotelchain.toLowerCase().equals(chain.toLowerCase())) && 
                    place.toLowerCase().equals(destination.toLowerCase()) && 
                    room.toLowerCase().equals(roomNr.toLowerCase())){
                    System.out.println("Valgt hotell: " + chain + " sted: "+ destination + " og rom nr: " + roomNr);
                    if(availability){       //Ta inn isAvailable her? Altså sjekke om d r tilgjengelig, deretter returnere en statement til book()?
                        System.out.println("Rommet er tilgjengelig");
                        break;
                    }else{
                        System.out.println("Rommet er utilgjengelig");
                        break;}
                }
            }
            //System.out.println(hotelchain + place + room + availability);        //Tester for å sjekke om info fra csv dukker opp
        }
        scanner.close();
        //return true; //Midlertidig, må fikses eller fjernes 
    }

    public boolean isAvailable(){   

        return true; 
    }

    public void addBooking(){
        
    }

    public void book(){

    }

    public static void main(String[] args) throws FileNotFoundException{
        RoomOri room1 = new RoomOri();
        RoomOri room2 = new RoomOri();
        room1.roomInfo("Strawberry", "Trondheim", "2");
        room2.roomInfo("Strawberry", "Trondheim", "2");

    }

   
}
