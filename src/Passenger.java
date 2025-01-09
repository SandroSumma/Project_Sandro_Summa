public class Passenger {
    private String name;
    private int age;
    private String adres;

    public Passenger(String name, int age, String adres) {
        this.name = name;
        this.age = 0;
        this.adres = adres;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAdres() {
        return adres;
    }
}

