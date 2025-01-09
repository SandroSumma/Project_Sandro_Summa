/**
 * De Ticket klasse vertegenwoordigt een ticket dat aan een passagier wordt toegewezen voor een specifieke vlucht.
 * Het bevat informatie over de passagier, de vlucht en de klasse van het ticket.
 */
public class Ticket {
    private String passengerName;
    private String flightCode;
    private String ticketClass;

    /**
     * Constructor voor het aanmaken van een ticket.
     *
     * @param passengerName De naam van de passagier voor wie het ticket is.
     * @param flightCode De code van de vlucht waarvoor het ticket is.
     * @param ticketClass De klasse van het ticket (bijvoorbeeld "economy" of "business").
     */
    public Ticket(String passengerName, String flightCode, String ticketClass) {
        this.passengerName = passengerName;
        this.flightCode = flightCode;
        this.ticketClass = ticketClass;
    }

    /**
     * Haalt de naam van de passagier op.
     *
     * @return De naam van de passagier die het ticket heeft.
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * Haalt de vluchtcode op waarvoor het ticket is.
     *
     * @return De vluchtcode die aan dit ticket is gekoppeld.
     */
    public String getFlightCode() {
        return flightCode;
    }

    /**
     * Haalt de klasse van het ticket op.
     *
     * @return De klasse van het ticket (bijvoorbeeld "economy" of "business").
     */
    public String getTicketClass() {
        return ticketClass;
    }
}
