import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Base class Reservation
// Represents a generic reservation with common attributes such as ID, customer name, and reservation date
class Reservation {
    private int reservationID;
    private String customerName;
    private Date reservationDate;

    // Constructor to initialize a reservation
    public Reservation(int reservationID, String customerName, Date reservationDate) {
        this.reservationID = reservationID;
        this.customerName = customerName;
        this.reservationDate = reservationDate;
    }

    // Getter and setter for reservation ID
    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    // Getter and setter for customer name
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Getter and setter for reservation date
    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    // Method to check reservation status
    public void checkReservationStatus() {
        System.out.println("Reservation ID: " + reservationID + " is confirmed for " + reservationDate + ".");
    }

    // Method to modify reservation details
    public void modifyReservationDetails(String customerName, Date reservationDate) {
        setCustomerName(customerName);
        setReservationDate(reservationDate);
        System.out.println("Reservation details updated successfully.");
    }
}

// Subclass ResortReservation
// Extends Reservation to include specific attributes for resort bookings such as room number
class ResortReservation extends Reservation {
    private int roomNumber;

    // Constructor to initialize a resort reservation
    public ResortReservation(int reservationID, String customerName, Date reservationDate, int roomNumber) {
        super(reservationID, customerName, reservationDate);
        this.roomNumber = roomNumber;
    }

    // Getter and setter for room number
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    // Override method to check reservation status for resorts
    @Override
    public void checkReservationStatus() {
        System.out.println("Resort Reservation ID: " + getReservationID() + " for room number " + roomNumber + " is confirmed for " + getReservationDate() + ".");
    }

    // Method to obtain room number
    public void obtainRoomNumber() {
        System.out.println("Room Number: " + roomNumber);
    }
}

// Subclass RailwayReservation
// Extends Reservation to include specific attributes for railway bookings such as seat number
class RailwayReservation extends Reservation {
    private String seatNumber;

    // Constructor to initialize a railway reservation
    public RailwayReservation(int reservationID, String customerName, Date reservationDate, String seatNumber) {
        super(reservationID, customerName, reservationDate);
        this.seatNumber = seatNumber;
    }

    // Getter and setter for seat number
    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    // Override method to check reservation status for railways
    @Override
    public void checkReservationStatus() {
        System.out.println("Railway Reservation ID: " + getReservationID() + " for seat number " + seatNumber + " is confirmed for " + getReservationDate() + ".");
    }

    // Method to obtain seat number
    public void obtainSeatNumber() {
        System.out.println("Seat Number: " + seatNumber);
    }
}

// Main class to test the program
public class Main {
    public static void main(String[] args) throws ParseException {
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        // List to store all reservations
        List<Reservation> reservations = new ArrayList<>();
        // Date formatter for parsing input dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Reservation ID counter
        int reservationCounter = 1;

        System.out.println("Enter the number of reservations you want to create: ");
        int numberOfReservations = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Loop to create reservations based on user input
        for (int i = 0; i < numberOfReservations; i++) {
            System.out.println("\nChoose reservation type (1 for Resort, 2 for Railway):");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Generate automatic reservation ID
            int reservationID = reservationCounter++;
            System.out.println("Generated Reservation ID: " + reservationID);

            // Gather common reservation details
            System.out.print("Customer Name: ");
            String customerName = scanner.nextLine();
            System.out.print("Reservation Date (dd/MM/yyyy): ");
            String dateInput = scanner.nextLine();
            Date reservationDate = dateFormat.parse(dateInput);

            // Create specific reservation based on user choice
            if (choice == 1) {
                // Automatically assign a room number
                int roomNumber = 1 + i;
                System.out.println("Assigned Room Number: " + roomNumber);
                reservations.add(new ResortReservation(reservationID, customerName, reservationDate, roomNumber));
            } else if (choice == 2) {
                // Automatically assign a seat number
                String seatNumber = "S" + (100 + i);
                System.out.println("Assigned Seat Number: " + seatNumber);
                reservations.add(new RailwayReservation(reservationID, customerName, reservationDate, seatNumber));
            } else {
                System.out.println("Invalid choice. Skipping this reservation.");
            }
        }

        // Menu-driven loop for user interaction
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Check Reservation Status");
            System.out.println("2. Modify Reservation Details");
            System.out.println("3. Obtain Room/Seat Number");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int menuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Handle menu choices
            if (menuChoice == 1) {
                // Check reservation status
                System.out.print("Enter Reservation ID to check status: ");
                int checkID = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                boolean found = false;
                for (Reservation reservation : reservations) {
                    if (reservation.getReservationID() == checkID) {
                        reservation.checkReservationStatus();
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Reservation ID not found.");
                }
            } else if (menuChoice == 2) {
                // Modify reservation details
                System.out.print("Enter Reservation ID to modify: ");
                int modifyID = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                boolean found = false;
                for (Reservation reservation : reservations) {
                    if (reservation.getReservationID() == modifyID) {
                        System.out.print("Enter new Customer Name: ");
                        String newCustomerName = scanner.nextLine();
                        System.out.print("Enter new Reservation Date (dd/MM/yyyy): ");
                        String newDateInput = scanner.nextLine();
                        Date newReservationDate = dateFormat.parse(newDateInput);
                        reservation.modifyReservationDetails(newCustomerName, newReservationDate);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Reservation ID not found.");
                }
            } else if (menuChoice == 3) {
                // Obtain room or seat number
                System.out.print("Enter Reservation ID to obtain details: ");
                int obtainID = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                boolean found = false;
                for (Reservation reservation : reservations) {
                    if (reservation.getReservationID() == obtainID) {
                        if (reservation instanceof ResortReservation) {
                            ((ResortReservation) reservation).obtainRoomNumber();
                        } else if (reservation instanceof RailwayReservation) {
                            ((RailwayReservation) reservation).obtainSeatNumber();
                        }
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Reservation ID not found.");
                }
            } else if (menuChoice == 4) {
                // Exit the program
                System.out.println("Exiting the program.");
                break;
            } else {
                // Handle invalid menu choices
                System.out.println("Invalid choice. Please try again.");
            }
        }

        // Close the scanner to release resources
        scanner.close();
    }
}

