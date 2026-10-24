
import java.util.Objects;

class VehicleRecord {
    private final String regNo;

    VehicleRecord(String regNo) {
        this.regNo = Objects.requireNonNull(regNo);
    }

    public double calculateFee(int hours) {
        return hours * 20.0;
    }

    @Override
    public String toString() {
        return "VehicleRecord{regNo='" + regNo + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof VehicleRecord)) return false;

        VehicleRecord other = (VehicleRecord) obj;
        return regNo.equals(other.regNo);
    }

    @Override
    public int hashCode() {
        return regNo.hashCode();
    }
}

class CarRecord extends VehicleRecord {
    CarRecord(String regNo) {
        super(regNo);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 30.0;
    }
}

public class Task07_Overriding {
    public static void main(String[] args) {
        VehicleRecord v = new CarRecord("TN01AB1234");

        System.out.println(v.calculateFee(3));
        System.out.println(v);
        System.out.println(
                v.equals(new VehicleRecord("TN01AB1234")));
        System.out.println(v.hashCode()
                == new VehicleRecord("TN01AB1234").hashCode());
    }
}
