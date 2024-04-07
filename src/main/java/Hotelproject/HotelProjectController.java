package Hotelproject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private TextArea bookingConfirmationTextArea;

    @FXML   
    private void initialize() throws IOException{
        this.room = new Room();
        
        this.hotelchainChoiceBox.getItems().addAll(Room.HOTELCHAIN);
        
        this.destinationChoiceBox.getItems().addAll(Room.DESTINATION);

        this.roomNumberChoiceBox.getItems().addAll(Room.ROOMNUMBER);

        this.guestTextField.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    
    }

    @FXML
    private void handleBook() throws IOException{
        String chain = this.hotelchainChoiceBox.getValue();
        String destination = this.destinationChoiceBox.getValue();
        String roomNr = this.roomNumberChoiceBox.getValue();

        String wrong = null; 
        if(chain == null){
            wrong = "You have to choose a hotelchain";
        }else if(destination == null){
            wrong = "You have to choose a destination";
        }else if(roomNr == null){
            wrong = "You have to choose a room number";
        }

        if(wrong != null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Couldn't book");
            alert.setContentText(wrong);
            alert.showAndWait();
            return;
        }
        this.room.booking(chain, destination, roomNr);
        }


    @FXML
    private void handleCancelBooking() throws IOException{
        String chain = this.hotelchainChoiceBox.getValue();
        String destination = this.destinationChoiceBox.getValue();
        String roomNr = this.roomNumberChoiceBox.getValue();

        this.room.cancelBooking(chain, destination, roomNr);
    }

    @FXML 
    private void handleVippsPayment(){

    }
    
    @FXML
    private void handleCardPayment(){

    }

    


    

}
