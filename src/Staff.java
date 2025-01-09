import java.util.ArrayList;
import java.util.List;

public class Staff {
    private String name;
    private static List<Staff> staffList = new ArrayList<>();

    public Staff(String name) {
        this.name = name;
    }

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

    public static List<Staff> getStaffList() {
        return staffList;
    }

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