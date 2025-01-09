import java.util.ArrayList;
import java.util.List;

/**
 * De Flight klasse vertegenwoordigt een vlucht met een vluchtcode, bestemming, beschikbare stoelen,
 * passagiers en personeel.
 * Het biedt methoden om passagiers toe te voegen, personeel toe te wijzen en vluchtinformatie op te halen.
 */
public class Flight {
    private String flightCode;
    private String destination;
    private int economySeats;
    private int businessSeats;
    private List<Passenger> passengers;
    private List<Staff> staff;

    /**
     * Constructor voor het aanmaken van een vlucht.
     *
     * @param flightCode De unieke code van de vlucht.
     * @param destination De bestemming van de vlucht.
     * @param economySeats Het aantal beschikbare economy-stoelen.
     * @param businessSeats Het aantal beschikbare business-stoelen.
     */
    public Flight(String flightCode, String destination, int economySeats, int businessSeats) {
        this.flightCode = flightCode;
        this.destination = destination;
        this.economySeats = economySeats;
        this.businessSeats = businessSeats;
        this.passengers = new ArrayList<>();
        this.staff = new ArrayList<>();
    }

    /**
     * Probeert een passagier toe te voegen aan de vlucht, afhankelijk van de gekozen ticketklasse.
     * Als er voldoende plaatsen zijn in de geselecteerde klasse, wordt de passagier toegevoegd.
     *
     * @param passenger De passagier die toegevoegd moet worden.
     * @param ticketClass De klasse van het ticket (economy/business).
     * @return true als de passagier succesvol is toegevoegd, anders false.
     */
    public boolean addPassenger(Passenger passenger, String ticketClass) {
        if (ticketClass.equalsIgnoreCase("economy") && economySeats > passengers.size()) {
            passengers.add(passenger);
            return true;
        } else if (ticketClass.equalsIgnoreCase("business") && businessSeats > passengers.size()) {
            passengers.add(passenger);
            return true;
        }
        return false;
    }

    /**
     * Wijst een personeelslid toe aan de vlucht.
     *
     * @param staffMember Het personeel dat toegewezen moet worden.
     */
    public void assignStaff(Staff staffMember) {
        staff.add(staffMember);
    }

    /**
     * Haalt het aantal beschikbare economy-stoelen op voor de vlucht.
     *
     * @return Het aantal economy-stoelen.
     */
    public int getEconomySeats() {
        return economySeats;
    }

    /**
     * Haalt het aantal beschikbare business-stoelen op voor de vlucht.
     *
     * @return Het aantal business-stoelen.
     */
    public int getBusinessSeats() {
        return businessSeats;
    }

    /**
     * Haalt de vluchtcode op van de vlucht.
     *
     * @return De vluchtcode.
     */
    public String getFlightCode() {
        return flightCode;
    }

    /**
     * Haalt de bestemming op van de vlucht.
     *
     * @return De bestemming van de vlucht.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Haalt de lijst van passagiers op die aan de vlucht zijn toegevoegd.
     *
     * @return De lijst van passagiers.
     */
    public List<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * Haalt de lijst van personeel op dat aan de vlucht is toegewezen.
     *
     * @return De lijst van personeel.
     */
    public List<Staff> getStaff() {
        return staff;
    }
}
