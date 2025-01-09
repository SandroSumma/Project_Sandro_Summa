public class Ticket {
    private String passengerName;
    private String flightCode;
    private String ticketClass;

    public Ticket(String passengerName, String flightCode, String ticketClass) {
        this.passengerName = passengerName;
        this.flightCode = flightCode;
        this.ticketClass = ticketClass;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public String getTicketClass() {
        return ticketClass;
    }
}