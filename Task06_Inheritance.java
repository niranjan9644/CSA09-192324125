
class BaseVehicle {
    BaseVehicle() {
        System.out.println("Vehicle constructor");
    }
}

class BaseCar extends BaseVehicle {
    BaseCar() {
        super();
        System.out.println("Car constructor");
    }
}

class BaseElectricCar extends BaseCar {
    BaseElectricCar() {
        super();
        System.out.println("ElectricCar constructor");
    }
}

public class Task06_Inheritance {
    public static void main(String[] args) {
        new BaseElectricCar();
    }
}
