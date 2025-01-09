import Classes.Flight;
import Classes.Passenger;
import Classes.Staff;
import Classes.Ticket;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.awt.Desktop;
import java.util.Scanner;

/**
 * De hoofdklaas van het programma. Deze bevat het keuzemenu en de methoden om passagiers, vluchten, tickets en personeel te beheren.
 */
class Main {
    static ArrayList<Passenger> passengers = new ArrayList<>();
    static ArrayList<Flight> flights = new ArrayList<>();
    static List<Ticket> tickets = new ArrayList<>();

    /**
     * De hoofdmethoden die de gebruikersinterface aanstuurt. Het toont een menu voor de gebruiker om keuzes te maken.
     * Het behandelt de interactie met de gebruiker voor passagiers, vluchten, tickets, boarding, personeel, en vluchtinformatie.
     *
     * @param args command-line argumenten (niet gebruikt).
     */
    public static void main(String[] args) {
        Staff.initializeStaff();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nKeuzemenu:");
            System.out.println("1. Aanmaken nieuwe passagier");
            System.out.println("2. Aanmaken vlucht");
            System.out.println("3. Aanmaken ticket");
            System.out.println("4. Boarding");
            System.out.println("5. Toewijzen personeel");
            System.out.println("6. Print vlucht info");
            System.out.println("0. Afsluiten");
            System.out.print("Maak een keuze: ");
            int keuze = scanner.nextInt();
            scanner.nextLine();

            switch (keuze) {
                case 1 -> createPassenger(scanner);
                case 2 -> createFlight(scanner);
                case 3 -> createTicket(scanner);
                case 4 -> boardPassenger(scanner);
                case 5 -> assignStaff(scanner);
                case 6 -> printFlightInfo(scanner);
                case 0 -> {
                    System.out.println("Programma afgesloten.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
    }

    /**
     * Maakt een nieuwe passagier aan door naam, leeftijd, adres en bagagegewicht in te voeren.
     * De passagier wordt alleen toegevoegd als deze nog niet bestaat in de lijst van passagiers.
     *
     * @param scanner De scanner waarmee de invoer van de gebruiker wordt gelezen.
     */
    private static void createPassenger(Scanner scanner) {
        String name = "";
        while (name.trim().isEmpty()) {
            System.out.print("Voer de naam van de passagier in: ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Naam mag niet leeg zijn!");
            }
        }

        int age = -1;
        while (age <= 0) {
            System.out.print("Voer de leeftijd in: ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                scanner.nextLine();
                if (age <= 0) {
                    System.out.println("Leeftijd moet een positief getal zijn.");
                }
            } else {
                System.out.println("Voer een geldige leeftijd in.");
                scanner.nextLine();
            }
        }

        String address = "";
        while (address.trim().isEmpty()) {
            System.out.print("Voer het adres in: ");
            address = scanner.nextLine();
            if (address.trim().isEmpty()) {
                System.out.println("Adres mag niet leeg zijn!");
            }
        }

        double bagageWeight = -1;
        while (bagageWeight <= 0 || bagageWeight > 35) {
            System.out.print("Voer het gewicht van de bagage in (kg): ");
            if (scanner.hasNextDouble()) {
                bagageWeight = scanner.nextDouble();
                scanner.nextLine();
                if (bagageWeight > 35) {
                    System.out.println("De bagage weegt te veel! Maximum toegestaan gewicht is 35 kg.");
                }
            } else {
                System.out.println("Voer een geldig gewicht in.");
                scanner.nextLine();
            }
        }

        for (Passenger p : passengers) {
            if (p.getName().equalsIgnoreCase(name) && p.getAge() == age && p.getAddress().equalsIgnoreCase(address)) {
                System.out.println("Deze passagier bestaat al!");
                return;
            }
        }

        passengers.add(new Passenger(name, age, address, (int) bagageWeight));
        System.out.println("Passagier aangemaakt.");
    }

    /**
     * Maakt een nieuwe vlucht aan door een vluchtcode, bestemming, en het aantal beschikbare stoelen in verschillende klassen in te voeren.
     *
     * @param scanner De scanner waarmee de invoer van de gebruiker wordt gelezen.
     */
    private static void createFlight(Scanner scanner) {
        System.out.print("Voer de vluchtcode in: ");
        String flightCode = scanner.nextLine();
        System.out.print("Voer de eindbestemming in: ");
        String destination = scanner.nextLine();
        System.out.print("Voer het aantal plaatsen economy in: ");
        int economySeats = scanner.nextInt();
        System.out.print("Voer het aantal plaatsen business in: ");
        int businessSeats = scanner.nextInt();
        scanner.nextLine();
        flights.add(new Flight(flightCode, destination, economySeats, businessSeats));
        System.out.println("Vlucht aangemaakt.");
    }

    /**
     * Maakt een ticket aan voor een passagier op een vlucht in een bepaalde klasse.
     * Controleert of de opgegeven vlucht en klasse geldig zijn.
     *
     * @param scanner De scanner waarmee de invoer van de gebruiker wordt gelezen.
     */
    public static void createTicket(Scanner scanner) {
        System.out.print("Voer de naam van de passagier in: ");
        String name = scanner.nextLine();

        System.out.print("Voer de vluchtcode in: ");
        String flightCode = scanner.nextLine();

        System.out.print("Kies klasse (economy/business): ");
        String ticketClass = scanner.nextLine();

        if (!ticketClass.equalsIgnoreCase("economy") && !ticketClass.equalsIgnoreCase("business")) {
            System.out.println("Ongeldige klasse.");
            return;
        }

        tickets.add(new Ticket(name, flightCode, ticketClass));
        System.out.println("Classes.Ticket aangemaakt voor " + name + " op vlucht " + flightCode + " in " + ticketClass + " klasse.");
    }

    /**
     * Boekt een passagier in voor een vlucht. Controleert of de passagier en het ticket geldig zijn,
     * en of er nog beschikbare plaatsen in de geselecteerde klasse zijn.
     *
     * @param scanner De scanner waarmee de invoer van de gebruiker wordt gelezen.
     */
    public static void boardPassenger(Scanner scanner) {
        System.out.println("Beschikbare vluchten:");
        for (Flight f : flights) {
            System.out.println(f.getFlightCode() + " naar " + f.getDestination());
        }

        System.out.print("Voer de naam van de passagier in: ");
        String name = scanner.nextLine();

        Ticket ticket = null;
        for (Ticket t : tickets) {
            if (t.getPassengerName().equalsIgnoreCase(name)) {
                ticket = t;
                break;
            }
        }

        if (ticket == null) {
            System.out.println("Geen geldig ticket gevonden voor deze passagier.");
            return;
        }

        Flight flight = null;
        for (Flight f : flights) {
            if (f.getFlightCode().equalsIgnoreCase(ticket.getFlightCode())) {
                flight = f;
                break;
            }
        }

        if (flight == null) {
            System.out.println("Vlucht niet gevonden.");
            return;
        }

        Passenger passenger = null;
        for (Passenger p : passengers) {
            if (p.getName().equalsIgnoreCase(name)) {
                passenger = p;
                break;
            }
        }

        if (passenger == null) {
            System.out.println("Passagier niet gevonden.");
            return;
        }

        if (flight.addPassenger(passenger, ticket.getTicketClass())) {
            System.out.println(name + " is succesvol geboekt op vlucht " + flight.getFlightCode());
            tickets.remove(ticket);
        } else {
            System.out.println("Geen beschikbare plaatsen in deze klasse.");
        }
    }

    /**
     * Wijs personeel toe aan een vlucht.
     * Controleert of er personeel en vluchten beschikbaar zijn.
     *
     * @param scanner De scanner waarmee de invoer van de gebruiker wordt gelezen.
     */
    private static void assignStaff(Scanner scanner) {
        if (flights.isEmpty() || Staff.getStaffList().isEmpty()) {
            System.out.println("Geen vluchten of personeel beschikbaar.");
            return;
        }

        System.out.println("Beschikbare vluchten:");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i).getFlightCode() + " naar " + flights.get(i).getDestination());
        }
        System.out.print("Kies een vlucht: ");
        int flightIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Flight selectedFlight = flights.get(flightIndex);

        System.out.println("Beschikbaar personeel:");
        Staff.printStaff();
        System.out.print("Voer de naam van het personeel in dat je wilt toewijzen: ");
        String staffName = scanner.nextLine();

        for (Staff s : Staff.getStaffList()) {
            if (s.toString().equalsIgnoreCase(staffName)) {
                selectedFlight.assignStaff(s);
                System.out.println(s + " is toegewezen aan vlucht " + selectedFlight.getFlightCode());
                return;
            }
        }
        System.out.println("Personeel niet gevonden.");
    }

    /**
     * Print de informatie van een vlucht naar een tekstbestand en opent dit bestand, indien mogelijk.
     *
     * @param scanner De scanner waarmee de invoer van de gebruiker wordt gelezen.
     */
    private static void printFlightInfo(Scanner scanner) {
        System.out.println("Voer de vluchtcode in: ");
        String flightCode = scanner.nextLine();

        for (Flight flight : flights) {
            if (flight.getFlightCode().equals(flightCode)) {
                File file = new File(flightCode + "_info.txt");

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("Vlucht: " + flight.getFlightCode() + " naar " + flight.getDestination() + "\n");
                    writer.write("Economy plaatsen: " + flight.getEconomySeats() + "\n");
                    writer.write("Business plaatsen: " + flight.getBusinessSeats() + "\n");
                    writer.write("Personeel" + flight.getStaff() + "\n");
                    writer.write("Passagiers: \n");
                    for (Passenger p : flight.getPassengers()) {
                        writer.write("- " + p.getName() + " " + p.getBagage() + "kg" + "\n");
                    }
                    System.out.println("Vluchtinformatie opgeslagen in " + file.getName());

                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(file);
                    } else {
                        System.out.println("De file kan niet geopend worden");
                    }

                } catch (IOException e) {
                    System.out.println("Fout bij opslaan of openen van vluchtinformatie.");
                }
                return;
            }
        }
        System.out.println("Vlucht niet gevonden.");
    }
}
