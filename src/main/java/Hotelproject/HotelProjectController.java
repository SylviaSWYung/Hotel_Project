package Hotelproject;

import java.io.IOException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class HotelProjectController {
    private Room room;

    @FXML
    private ChoiceBox<String> hotelchainChoiceBox;

    @FXML
    private ChoiceBox<String> destinationChoiceBox;

    @FXML
    private ChoiceBox<String> roomNumberChoiceBox;

    @FXML
    private TextField guestTextField;

    @FXML
    private TextArea bookingInformationTextArea;

    @FXML   
    private void initialize() throws IOException{
        this.room = new Room();
        
        this.hotelchainChoiceBox.getItems().addAll(Room.HOTELCHAIN);
        
        this.destinationChoiceBox.getItems().addAll(Room.DESTINATION);

        this.roomNumberChoiceBox.getItems().addAll(Room.ROOMNUMBER);

        this.guestTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    
    }

    private void refreshBookingInfo() {
        String hotelchain = hotelchainChoiceBox.getValue();
        String destination = destinationChoiceBox.getValue();
        String roomNumber = roomNumberChoiceBox.getValue();
        int guestCount = Integer.parseInt(guestTextField.getText());
        LocalDate date = LocalDate.now();
        //int totalprice = 
    
        StringBuilder builder = new StringBuilder("Booking Information:\n");
        builder.append("Date: ").append(date).append("\n");
        builder.append("Hotel Chain: ").append(hotelchain).append("\n");
        builder.append("Destination: ").append(destination).append("\n");
        builder.append("Room Number: ").append(roomNumber).append("\n");
        builder.append("Amount of Guests: ").append(guestCount).append("\n");
        //builder.append("\n Total price: ").append(totalprice).append("\n");

        this.bookingInformationTextArea.setText(builder.toString());
    }

    @FXML
    private void handleBook() throws IOException{
        String chain = this.hotelchainChoiceBox.getValue();
        String destination = this.destinationChoiceBox.getValue();
        String roomNr = this.roomNumberChoiceBox.getValue();
        String guestInteger = this.guestTextField.getText();
        //Integer guestInput = Integer.parseInt(guestTextField.getText()); gjør den om til string istedet for å sjekke om det er noe der i det hele tatt, så int etterpå fir å sjekke om det er valid tall

        String wrong = null; 
        if(chain == null){
            wrong = "You have to choose a hotelchain";
        }else if(destination == null){
            wrong = "You have to choose a destination";
        }else if(roomNr == null){
            wrong = "You have to choose a room number";
        }else if(guestInteger.isEmpty()){
            wrong = "You have to choose the amount of guest!";
        }else {
            try {
                int guestInput = Integer.parseInt(guestTextField.getText());
                if (guestInput <= 0) {
                    wrong = "Invalid number of guests.";
                }
            } catch (NumberFormatException e) {
                wrong = "Invalid number of guests";
            }
        }

        try {
            if(wrong != null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Couldn't book");
                alert.setContentText(wrong);
                alert.showAndWait();
                return;
            }
            this.room.booking(chain, destination, roomNr);
            this.refreshBookingInfo();
    
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("You have successfully booked!");
            alert.setContentText("Booking information is updated.");
            
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not book room, the room is already booked.");
            alert.setContentText("A problem occurred while trying to book: " + e.getMessage() + " Try to book another room or cancel booking.");
            
            alert.showAndWait();
        }
        this.room.booking(chain, destination, roomNr);
        //this.refreshRoomInfo();

    }


    @FXML
    private void handleCancelBook() throws IOException{
        String chain = this.hotelchainChoiceBox.getValue();
        String destination = this.destinationChoiceBox.getValue();
        String roomNr = this.roomNumberChoiceBox.getValue();
        System.out.println(chain);

        this.room.cancelBooking(chain, destination, roomNr);
    }

    @FXML 
    private void handleVippsPayment(){
        gjennomforbetalingvipps(price, guestInput, totalPrice);
    }
    
    @FXML
    private void handleCardPayment(){
        gjennomforbetalingcard(price, guestInput, totalPrice);
    }

    
}
