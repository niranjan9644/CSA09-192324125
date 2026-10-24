
class ParkingTicket {
    private final int ticketNumber;
    private final String registrationNumber;
    private final int floor;
    private final int slot;

    public ParkingTicket() {
        this(0, "UNKNOWN", 0, 0);
    }

    public ParkingTicket(int ticketNumber,
                         String registrationNumber) {
        this(ticketNumber, registrationNumber, 0, 0);
    }

    public ParkingTicket(int ticketNumber,
                         String registrationNumber,
                         int floor, int slot) {
        if (ticketNumber < 0 || floor < 0 || slot < 0) {
            throw new IllegalArgumentException(
                    "Invalid ticket or slot details");
        }
        if (registrationNumber == null
                || registrationNumber.isBlank()) {
            throw new IllegalArgumentException(
                    "Registration number is required");
        }

        this.ticketNumber = ticketNumber;
        this.registrationNumber = registrationNumber;
        this.floor = floor;
        this.slot = slot;
    }

    // Copy constructor
    public ParkingTicket(ParkingTicket other) {
        this(other.ticketNumber, other.registrationNumber,
                other.floor, other.slot);
    }

    public void display() {
        System.out.println("Ticket: " + ticketNumber);
        System.out.println("Vehicle: " + registrationNumber);
        System.out.println("Floor: " + floor);
        System.out.println("Slot: " + slot);
    }
}

public class Task04_Constructors {
    public static void main(String[] args) {
        ParkingTicket original =
                new ParkingTicket(1001, "TN01AB1234", 1, 2);

        ParkingTicket copy = new ParkingTicket(original);

        original.display();
        System.out.println("Copied ticket:");
        copy.display();
    }
}
