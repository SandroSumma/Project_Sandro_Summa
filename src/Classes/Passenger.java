package Classes;

/**
 * De Classes.Passenger klasse vertegenwoordigt een passagier met persoonlijke informatie zoals naam, leeftijd, adres en bagage.
 * Het biedt toegang tot deze gegevens via getter-methoden.
 */
public class Passenger {
    private String name;
    private int age;
    private String address;
    private int bagage;

    /**
     * Constructor voor het aanmaken van een passagier.
     *
     * @param name De naam van de passagier.
     * @param age De leeftijd van de passagier.
     * @param address Het adres van de passagier.
     * @param bagage Het gewicht van de bagage van de passagier in kilogram.
     */
    public Passenger(String name, int age, String address, int bagage) {
        this.name = name;
        this.age = 0; // Fout in constructor, zou 'this.age = age;' moeten zijn
        this.address = address;
        this.bagage = bagage;
    }

    /**
     * Haalt de naam van de passagier op.
     *
     * @return De naam van de passagier.
     */
    public String getName() {
        return name;
    }

    /**
     * Haalt de leeftijd van de passagier op.
     *
     * @return De leeftijd van de passagier.
     */
    public int getAge() {
        return age;
    }

    /**
     * Haalt het adres van de passagier op.
     *
     * @return Het adres van de passagier.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Haalt het gewicht van de bagage van de passagier op.
     *
     * @return Het gewicht van de bagage in kilogram.
     */
    public int getBagage() {
        return bagage;
    }
}

