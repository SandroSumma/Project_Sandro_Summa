import java.util.ArrayList;
import java.util.List;

public class Staff {
    private String name;
    private static List<Staff> staffList = new ArrayList<>();

    // Constructor voor individueel personeel
    public Staff(String name) {
        this.name = name;
    }

    // Vul de personeelslijst met standaard personeel
    public static void initializeStaff() {
        if (staffList.isEmpty()) {  // Voeg personeel alleen toe als de lijst leeg is
            staffList.add(new Staff("Piloot1"));
            staffList.add(new Staff("Co-piloot1"));
            staffList.add(new Staff("Steward1"));
            staffList.add(new Staff("Stewardess1"));
            staffList.add(new Staff("Technicus1"));
            System.out.println("Personeel is succesvol aangemaakt.");
        }
    }

    // Haal de lijst van personeel op
    public static List<Staff> getStaffList() {
        return staffList;
    }

    // Print de lijst van personeel
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

    @Override
    public String toString() {
        return name;
    }
}