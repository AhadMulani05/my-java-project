class Ticket {
    int ticketId;
    String movieName;
    String customerName;
    double price;
    boolean isBooked;

    // Constructor
    Ticket(int ticketId, String movieName, String customerName, double price) {
        this.ticketId = ticketId;
        this.movieName = movieName;
        this.customerName = customerName;
        this.price = price;
        this.isBooked = false;
    }

    // Book Ticket
    void bookTicket() {
        if (!isBooked) {
            isBooked = true;
            System.out.println("Ticket Booked Successfully");
        } else {
            System.out.println("Ticket Already Booked");
        }
    }

    // Cancel Ticket
    void cancelTicket() {
        if (isBooked) {
            isBooked = false;
            System.out.println("Ticket Cancelled Successfully");
        } else {
            System.out.println("Ticket is not booked");
        }
    }

    // Display Ticket Details
    void displayTicket() {
        System.out.println("---------------");
        System.out.println("Ticket ID      : " + ticketId);
        System.out.println("Movie Name     : " + movieName);
        System.out.println("Customer Name  : " + customerName);
        System.out.println("Price          : " + price);
        System.out.println("Booked         : " + isBooked);
        System.out.println("---------------");
    }
}

public class ticket_booking {
    public static void main(String[] args) {

        Ticket t1 = new Ticket(101, "Avengers", "Ahad", 250);
        Ticket t2 = new Ticket(102, "KGF 2", "Ali", 200);
        Ticket t3 = new Ticket(103, "Pushpa", "Aman", 180);

        // Display all tickets
        t1.displayTicket();
        t2.displayTicket();
        t3.displayTicket();

        // Book Ticket 1
        t1.bookTicket();

        // Try booking again
        t1.bookTicket();

        // Cancel Ticket
        t1.cancelTicket();

        // Try cancelling again
        t1.cancelTicket();

        // Display Ticket 1 again
        t1.displayTicket();
    }
}
