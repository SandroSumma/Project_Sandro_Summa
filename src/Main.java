import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.awt.Desktop;
import java.util.Scanner;

class Main {
    static ArrayList<Passenger> passengers = new ArrayList<>();
    static ArrayList<Flight> flights = new ArrayList<>();


    public static void main(String[] args) {
        Staff.initializeStaff();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nKeuzemenu:");
            System.out.println("1. Aanmaken nieuwe passagier");
            System.out.println("2. Aanmaken vlucht");
            System.out.println("3. Aanmaken ticket");
            System.out.println("4. Toewijzen personeel");
            System.out.println("5. Print vlucht info");
            System.out.println("0. Afsluiten");
            System.out.print("Maak een keuze: ");
            int keuze = scanner.nextInt();
            scanner.nextLine();

            switch (keuze) {
                case 1 -> createPassenger(scanner);
                case 2 -> createFlight(scanner);
                case 3 -> createTicket(scanner);
                case 4 -> assignStaff(scanner);
                case 5 -> printFlightInfo(scanner);
                case 0 -> {
                    System.out.println("Programma afgesloten.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
    }

    private static void createPassenger(Scanner scanner) {
        System.out.print("Voer de naam van de passagier in: ");
        String name = scanner.nextLine();
        System.out.print("Voer de leeftijd in: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Voer het adres in: ");
        String adres = scanner.nextLine();
        passengers.add(new Passenger(name, age, adres));
        System.out.println("Passagier aangemaakt.");
    }

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

    private static void createTicket(Scanner scanner) {
        if (passengers.isEmpty() || flights.isEmpty()) {
            System.out.println("Er zijn geen passagiers of vluchten beschikbaar.");
            return;
        }
        System.out.println("Beschikbare passagiers:");
        for (int i = 0; i < passengers.size(); i++) {
            System.out.println((i + 1) + ". " + passengers.get(i).getName());
        }
        System.out.print("Kies een passagier: ");
        int passengerIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.println("Beschikbare vluchten:");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i).getFlightCode() + " naar " + flights.get(i).getDestination());
        }
        System.out.print("Kies een vlucht: ");
        int flightIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Kies klasse (economy/business): ");
        String ticketClass = scanner.nextLine();

        if (!ticketClass.equalsIgnoreCase("economy") && !ticketClass.equalsIgnoreCase("business")) {
            System.out.println("Ongeldige klasse.");
            return;
        }

        Flight flight = flights.get(flightIndex);
        Passenger passenger = passengers.get(passengerIndex);
        if (flight.addPassenger(passenger, ticketClass)) {
            System.out.println("Ticket aangemaakt.");
        } else {
            System.out.println("Geen beschikbare plaatsen in deze klasse.");
        }
    }

    private static void assignStaff(Scanner scanner) {
        if (flights.isEmpty() || Staff.getStaffList().isEmpty()) {
            System.out.println("Geen vluchten of personeel beschikbaar.");
            return;
        }

        // Toon beschikbare vluchten
        System.out.println("Beschikbare vluchten:");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i).getFlightCode() + " naar " + flights.get(i).getDestination());
        }
        System.out.print("Kies een vlucht: ");
        int flightIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Flight selectedFlight = flights.get(flightIndex);

        // Toon beschikbaar personeel
        System.out.println("Beschikbaar personeel:");
        Staff.printStaff();  // Toon personeel uit Staff-klasse
        System.out.print("Voer de naam van het personeel in dat je wilt toewijzen: ");
        String staffName = scanner.nextLine();

        // Zoek het personeel en wijs het toe aan de vlucht
        for (Staff s : Staff.getStaffList()) {
            if (s.toString().equalsIgnoreCase(staffName)) {
                selectedFlight.assignStaff(s);  // Wijs personeel toe aan de vlucht
                System.out.println(s + " is toegewezen aan vlucht " + selectedFlight.getFlightCode());
                return;
            }
        }
        System.out.println("Personeel niet gevonden.");
    }

    private static void printFlightInfo(Scanner scanner) {
        System.out.println("Voer de vluchtcode in: ");
        String flightCode = scanner.nextLine();

        for (Flight flight : flights) {
            if (flight.getFlightCode().equals(flightCode)) {
                File file = new File(flightCode + "_info.txt");

                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("Vlucht: " + flight.getFlightCode() + " naar " + flight.getDestination() + "\n");
                    writer.write("Passagiers: \n");
                    for (Passenger p : flight.getPassengers()) {
                        writer.write("- " + p.getName() + "\n");
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