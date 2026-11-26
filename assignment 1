
class ParkingVehicle {
    private final String registrationNumber;
    private final String type;

    public ParkingVehicle(String registrationNumber, String type) {
        this.registrationNumber = registrationNumber;
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getType() {
        return type;
    }
}

class ParkingSlot {
    private final int floor;
    private final int slotNumber;
    private ParkingVehicle vehicle;

    public ParkingSlot(int floor, int slotNumber) {
        this.floor = floor;
        this.slotNumber = slotNumber;
    }

    public boolean isFree() {
        return vehicle == null;
    }

    public boolean park(ParkingVehicle vehicle) {
        if (vehicle == null || !isFree()) {
            return false;
        }
        this.vehicle = vehicle;
        return true;
    }

    public ParkingVehicle removeVehicle() {
        ParkingVehicle removed = vehicle;
        vehicle = null;
        return removed;
    }

    public int getFloor() {
        return floor;
    }

    public int getSlotNumber() {
        return slotNumber;
    }
}

class ParkingTicket {
    private static int nextTicketNumber = 1000;

    private final int ticketNumber;
    private final ParkingVehicle vehicle;

    public ParkingTicket(ParkingVehicle vehicle) {
        this.ticketNumber = ++nextTicketNumber;
        this.vehicle = vehicle;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public ParkingVehicle getVehicle() {
        return vehicle;
    }
}

public class Task01_ProblemModel {
    public static void main(String[] args) {
        ParkingVehicle v =
                new ParkingVehicle("TN01AB1234", "Car");

        ParkingSlot slot = new ParkingSlot(0, 1);
        ParkingTicket ticket = new ParkingTicket(v);

        System.out.println("Parked: " + slot.park(v));
        System.out.println("Ticket: " + ticket.getTicketNumber());
        System.out.println("Slot free: " + slot.isFree());

        slot.removeVehicle();
        System.out.println("Slot free after exit: " + slot.isFree());
    }
}
