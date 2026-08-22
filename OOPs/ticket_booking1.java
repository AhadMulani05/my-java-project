class Ticket {
    int id;
    String mName;
    String cName;
    double price;
    boolean isBooked;

    Ticket(int id, String mName, String cName, double price) {
        this.id = id;
        this.mName = mName;
        this.cName = cName;
        this.price = price;
        isBooked = false;
    }

    void bookTicket() {
        if(!isBooked) {
            isBooked = true;
            System.out.println("Ticket boooked successfully");
        }
        else System.out.println("Ticket is already boooked...");
    }

    void cancelTicket() {
        if(isBooked) {
            isBooked = false;
            System.out.println("Ticket cancelled successfullly");
        }
        else {
            System.out.println("Ticket is not booked");
        }
    }

    void displayTicket() {
        System.out.println("---------------");
        System.out.println("Ticket ID      : " + id);
        System.out.println("Movie Name     : " + mName);
        System.out.println("Customer Name  : " + cName);
        System.out.println("Price          : " + price);
        System.out.println("Booked         : " + isBooked);
        System.out.println("---------------");
    }
}

public class ticket_booking1 {
    public static void main(String[] args) {
        Ticket t1 = new Ticket(101, "Dhurander", "Ahad", 250);

        t1.displayTicket();
        t1.bookTicket();
        t1.bookTicket();
    }
}
 

//AHAD RIHAJ MULANI OK SEE YOU IN MY COUNTRY OK SEE YOU IN MY HOME SWEET HOME.....