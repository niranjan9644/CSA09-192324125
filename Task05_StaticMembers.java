
class TicketCounter {
    private static int nextNumber = 1000;

    private final int ticketNumber;

    public TicketCounter() {
        ticketNumber = ++nextNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public static int getIssuedCount() {
        return nextNumber - 1000;
    }
}

public class Task05_StaticMembers {
    public static void main(String[] args) {
        TicketCounter t1 = new TicketCounter();
        TicketCounter t2 = new TicketCounter();

        System.out.println(t1.getTicketNumber());
        System.out.println(t2.getTicketNumber());
        System.out.println(
                "Issued: " + TicketCounter.getIssuedCount());
    }
}
