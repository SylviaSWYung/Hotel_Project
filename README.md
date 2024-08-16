# Project Overview 

This project is part of the course "Object-Oriented Programming" for first-year students at NTNU. The course covers fundamental algorithms and data structures, constructs and control flow in object-oriented languages, modularization and reuse, standard software libraries, unit testing, debugging tools, object-oriented design, and the use of UML diagrams (class, object, sequence, and interaction). Additionally, it includes design patterns and basic app architecture using modern GUI toolkits, with Java as the implementation language. 

## App Description
HotelProjectApp is a hotel booking application that allows users to select a hotel chain, location, room, and specify the number of guests before booking. The app calculates the total cost by multiplying the room price by the number of guests and then offers payment options via Vipps or credit card. The system checks room availability by scanning a CSV file and updates the file to prevent double bookings. CSV files are being used because we have yet learned about databases. Users can also cancel bookings, which updates the room's status in the file. Error handling ensures users receive appropriate messages for invalid inputs or actions.

## Project Requirements
The final product must meet these key requirements:

- **Interacting Classes:** The app must include at least two interacting classes. One class should perform some form of calculation or processing, and at least one class must implement an interface.
- **Encapsulation and Validation:** All classes must encapsulate their state with appropriate validation where needed.
- **JavaFX User Interface:** The app should have a JavaFX-based user interface, with corresponding Controller and App classes.
- **File Handling:** The app must read from and write to a file, storing relevant data.
- **Error Handling:** Implement error handling at key points in the app.
- **Unit Testing:** Include JUnit tests to verify the app's functionality.
- **FXML and SceneBuilder:** Used for designing the app's UI, with integration into the app's logic through Controller.java.

## Image of the application 
![Image of the hotel application](/src/pictures/Hotel_project.png)