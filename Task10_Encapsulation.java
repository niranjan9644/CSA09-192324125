
class VehicleData {
    private String registrationNumber;
    private int durationMinutes;

    public VehicleData(String registrationNumber) {
        setRegistrationNumber(registrationNumber);
    }

    public void setRegistrationNumber(String registrationNumber) {
        if (registrationNumber == null
                || registrationNumber.isBlank()) {
            throw new IllegalArgumentException(
                    "Registration number is required");
        }
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setDurationMinutes(int durationMinutes) {
        if (durationMinutes < 0) {
            throw new IllegalArgumentException(
                    "Duration cannot be negative");
        }
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}

public class Task10_Encapsulation {
    public static void main(String[] args) {
        VehicleData v = new VehicleData("TN01AB1234");
        v.setDurationMinutes(125);

        System.out.println(v.getRegistrationNumber());
        System.out.println(v.getDurationMinutes());

        try {
            v.setDurationMinutes(-5);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
