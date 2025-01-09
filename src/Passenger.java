public class Passenger {
    private String name;
    private int age;
    private String address;
    private int bagage;

    public Passenger(String name, int age, String address , int bagage) {
        this.name = name;
        this.age = 0;
        this.address = address;
        this.bagage = bagage;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public int getBagage() {
        return bagage;
    }

}

