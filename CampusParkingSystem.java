
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

abstract class Vehicle {
    private final String regNo;

    protected Vehicle(String regNo) {
        if (regNo == null || regNo.isBlank()) {
            throw new IllegalArgumentException(
                    "Invalid registration number");
        }
        this.regNo = regNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public abstract double parkingFee(
            long minutes, LocalDateTime entry);

    public double chargingFee(int kWh) {
        return 0.0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Vehicle
                && regNo.equals(((Vehicle) obj).regNo);
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }
}

class TwoWheeler extends Vehicle {
    TwoWheeler(String regNo) {
        super(regNo);
    }

    @Override
    public double parkingFee(long minutes, LocalDateTime entry) {
        return billableHours(minutes) * 10.0;
    }

    static long billableHours(long minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException(
                    "Negative duration");
        }
        return minutes / 60 + (minutes % 60 == 0 ? 0 : 1);
    }
}

class Car extends Vehicle {
    Car(String regNo) {
        super(regNo);
    }

    @Override
    public double parkingFee(long minutes, LocalDateTime entry) {
        return TwoWheeler.billableHours(minutes) * 30.0;
    }
}

class ElectricCar extends Car {
    ElectricCar(String regNo) {
        super(regNo);
    }

    @Override
    public double chargingFee(int kWh) {
        if (kWh < 0) throw new IllegalArgumentException(
                "Invalid energy");
        return kWh * 12.0;
    }
}

class EBike extends Vehicle {
    EBike(String regNo) {
        super(regNo);
    }

    @Override
    public double parkingFee(long minutes, LocalDateTime entry) {
        return 20.0; // Flat parking fee
    }
}

class DeliveryVan extends Vehicle {
    DeliveryVan(String regNo) {
        super(regNo);
    }

    @Override
    public double parkingFee(long minutes, LocalDateTime entry) {
        long hours = TwoWheeler.billableHours(minutes);
        boolean peak = entry.getHour() >= 9
                && entry.getHour() < 17;

        return hours * 30.0 * (peak ? 2.0 : 1.0);
    }
}

class ParkingTicket {
    private static int nextId = 1000;

    final int id;
    final Vehicle vehicle;
    final int floor;
    final int slot;
    final LocalDateTime entry;
    final boolean freePass;
    final int chargingKWh;

    ParkingTicket(Vehicle vehicle, int floor, int slot,
                  LocalDateTime entry, boolean freePass,
                  int chargingKWh) {
        this.id = ++nextId;
        this.vehicle = vehicle;
        this.floor = floor;
        this.slot = slot;
        this.entry = entry;
        this.freePass = freePass;
        this.chargingKWh = chargingKWh;
    }
}

class ParkingService {
    private final Vehicle[][] slots;
    private final Map<String, ParkingTicket> active =
            new HashMap<>();

    ParkingService(int[] capacities) {
        slots = new Vehicle[capacities.length][];
        for (int f = 0; f < capacities.length; f++) {
            if (capacities[f] < 0) {
                throw new IllegalArgumentException(
                        "Invalid floor capacity");
            }
            slots[f] = new Vehicle[capacities[f]];
        }
    }

    public ParkingTicket enter(
            Vehicle vehicle,
            boolean monthlyPass,
            boolean visitorDayPass,
            int chargingKWh,
            LocalDateTime entry) {

        if (vehicle == null || entry == null
                || chargingKWh < 0) {
            throw new IllegalArgumentException(
                    "Invalid entry details");
        }

        String reg = vehicle.getRegNo();

        if (active.containsKey(reg)) {
            throw new IllegalArgumentException(
                    "Vehicle already parked");
        }

        for (int f = 0; f < slots.length; f++) {
            for (int s = 0; s < slots[f].length; s++) {
                if (slots[f][s] == null) {
                    slots[f][s] = vehicle;

                    ParkingTicket ticket = new ParkingTicket(
                            vehicle, f, s, entry,
                            monthlyPass || visitorDayPass,
                            chargingKWh);

                    active.put(reg, ticket);
                    return ticket;
                }
            }
        }

        throw new IllegalStateException("Parking is full");
    }

    public double exit(String regNo, LocalDateTime exit) {
        ParkingTicket ticket = active.get(regNo);

        if (ticket == null) {
            throw new IllegalArgumentException(
                    "No active ticket");
        }

        long minutes = Duration.between(
                ticket.entry, exit).toMinutes();

        if (minutes < 0) {
            throw new IllegalArgumentException(
                    "Exit precedes entry");
        }

        double fee = ticket.freePass ? 0.0
                : ticket.vehicle.parkingFee(
                        minutes, ticket.entry);

        fee += ticket.vehicle.chargingFee(
                ticket.chargingKWh);

        slots[ticket.floor][ticket.slot] = null;
        active.remove(regNo);

        System.out.println("Ticket: " + ticket.id);
        System.out.println("Vehicle: " + regNo);
        System.out.println("Slot: " + ticket.floor
                + "-" + ticket.slot);

        return fee;
    }
}

public class CampusParkingSystem {
    public static void main(String[] args) {
        ParkingService parking =
                new ParkingService(new int[]{2, 1, 3, 2, 4});

        LocalDateTime entry =
                LocalDateTime.of(2026, 10, 14, 10, 0);

        ParkingTicket t1 = parking.enter(
                new TwoWheeler("TN01A1111"),
                false, false, 0, entry);

        ParkingTicket t2 = parking.enter(
                new Car("TN02B2222"),
                true, false, 0, entry);

        ParkingTicket t3 = parking.enter(
                new ElectricCar("TN03C3333"),
                false, false, 10, entry);

        ParkingTicket t4 = parking.enter(
                new EBike("TN04D4444"),
                false, false, 0, entry);

        ParkingTicket t5 = parking.enter(
                new DeliveryVan("TN05E5555"),
                false, false, 0, entry);

        LocalDateTime exit = entry.plusMinutes(130);

        System.out.println("Two-wheeler fee: Rs "
                + parking.exit("TN01A1111", exit));

        System.out.println("Monthly-pass car fee: Rs "
                + parking.exit("TN02B2222", exit));

        System.out.println("Electric-car fee: Rs "
                + parking.exit("TN03C3333", exit));

        System.out.println("E-bike fee: Rs "
                + parking.exit("TN04D4444", exit));

        System.out.println("Delivery-van fee: Rs "
                + parking.exit("TN05E5555", exit));
    }
}
