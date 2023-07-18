package Day2;


enum Destination {
    ISTANBUL,
    ADANA,
    ANKARA
}

class Bus {
    private Destination destination;
    private Passenger[] passengers;
    private int size;
    private int lp = 0;

    Bus(Destination destination, int size) {
        this.destination = destination;
        this.size = size;
        this.passengers = new Passenger[size];

    }

    public void insertPassenger(Passenger passenger) {
        if (passenger.destination != this.destination) {
            System.out.println("Destinations do not match");
            return;
        }
        if (this.lp < this.size) {
            this.passengers[lp] = passenger;
            System.out.println("Passenger inserted");
            this.lp++;
        } else {
            System.out.println("Bus is full");
        }
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public void setPassengers(Passenger[] passengers) {
        this.passengers = passengers;
    }
}

class Passenger {
    String name;
    Destination destination;

    Passenger(String name, Destination destination) {
        this.name = name;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }
}


public class BusReservation {

    public static void main(String args[]) {
        Bus bus1 = new Bus(Destination.ADANA, 1);
        bus1.insertPassenger(new Passenger("Ali", Destination.ADANA));
        bus1.insertPassenger(new Passenger("Veli", Destination.ANKARA));
        bus1.insertPassenger(new Passenger("Mehmet", Destination.ADANA));
    }
}
