
public class Task02_DataRepresentation {
    public static void main(String[] args) {
        // Three floors with different capacities
        String[][] slots = {
            new String[2],
            new String[3],
            new String[1]
        };

        int floor = 1;
        int slot = 2;
        long entryTimeMillis = System.currentTimeMillis();
        double hourlyRate = 30.0;
        int durationMinutes = 125;

        // Ceiling division: 125 minutes = 3 billable hours
        int hours = durationMinutes / 60;
        if (durationMinutes % 60 != 0) {
            hours++;
        }

        double fee = hours * hourlyRate;

        slots[floor][slot] = "TN02AB1234";

        System.out.println("Vehicle: " + slots[floor][slot]);
        System.out.println("Floor: " + floor);
        System.out.println("Slot: " + slot);
        System.out.println("Entry time: " + entryTimeMillis);
        System.out.println("Billable hours: " + hours);
        System.out.println("Parking fee: Rs " + fee);
    }
}
