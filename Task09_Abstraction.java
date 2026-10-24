
interface Payable {
    double payment();
}

interface ChargeableVehicle {
    double chargingFee(int kWh);
}

abstract class AbstractVehicle implements Payable {
    abstract double parkingFee(int minutes);
}

class EV extends AbstractVehicle
        implements ChargeableVehicle {

    @Override
    double parkingFee(int minutes) {
        int hours = minutes / 60;
        if (minutes % 60 != 0) hours++;
        return hours * 30.0;
    }

    @Override
    public double chargingFee(int kWh) {
        return kWh * 12.0;
    }

    @Override
    public double payment() {
        return parkingFee(130) + chargingFee(10);
    }
}

public class Task09_Abstraction {
    public static void main(String[] args) {
        AbstractVehicle vehicle = new EV();

        System.out.println(
                "Parking fee: Rs " + vehicle.parkingFee(130));
        System.out.println(
                "Total payment: Rs " + vehicle.payment());
    }
}
