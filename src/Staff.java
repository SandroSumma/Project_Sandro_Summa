import java.util.ArrayList;
import java.util.List;

/**
 * De Staff klasse vertegenwoordigt een personeelslid van de luchtvaartmaatschappij.
 * Het biedt methoden om personeel te initialiseren, op te halen en weer te geven.
 */
public class Staff {
    private String name;
    private static List<Staff> staffList = new ArrayList<>();

    /**
     * Constructor voor het aanmaken van een nieuw personeelslid.
     *
     * @param name De naam van het personeel.
     */
    public Staff(String name) {
        this.name = name;
    }

    /**
     * Initialiseert het personeel door een lijst van vooraf gedefinieerde personeelsleden toe te voegen.
     * Dit gebeurt alleen als de lijst nog leeg is.
     */
    public static void initializeStaff() {
        if (staffList.isEmpty()) {
            staffList.add(new Staff("Piloot1"));
            staffList.add(new Staff("Co-piloot1"));
            staffList.add(new Staff("Steward1"));
            staffList.add(new Staff("Stewardess1"));
            staffList.add(new Staff("Technicus1"));
            System.out.println("Personeel is succesvol aangemaakt.");
        }
    }

    /**
     * Haalt de lijst van alle personeelsleden op.
     *
     * @return Een lijst van alle personeelsleden.
     */
    public static List<Staff> getStaffList() {
        return staffList;
    }

    /**
     * Print de lijst van beschikbaar personeel naar de console.
     * Als er geen personeel beschikbaar is, wordt een melding weergegeven.
     */
    public static void printStaff() {
        if (staffList.isEmpty()) {
            System.out.println("Er is geen personeel beschikbaar.");
        } else {
            System.out.println("Beschikbaar personeel:");
            for (Staff s : staffList) {
                System.out.println(s);
            }
        }
    }

    /**
     * Geeft de naam van het personeel weer.
     *
     * @return De naam van het personeelslid.
     */
    @Override
    public String toString() {
        return name;
    }
}
