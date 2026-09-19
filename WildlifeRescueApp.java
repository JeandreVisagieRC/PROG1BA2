import java.util.ArrayList;
import java.util.Scanner;

public class WildlifeRescueApp {// main application class for wildlife rescue operations
    private static ArrayList<RescueCase> rescueList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {// main menu loop
            System.out.println("\n==========================================");
            System.out.println("   WILDLIFE RESCUE OPERATIONS SYSTEM");
            System.out.println("==========================================");
            System.out.println("1. Create Rescue Case");
            System.out.println("2. Search Rescue Case");
            System.out.println("3. Update Rescue Status");
            System.out.println("4. Display All Rescue Cases");
            System.out.println("5. Rescue Report");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();
            switch (choice) {// handles user menu selection
                case "1": createCase(); break;
                case "2": searchCase(); break;
                case "3": updateStatus(); break;
                case "4": displayAll(); break;
                case "5": generateReport(); break;
                case "6": System.exit(0);
                default: System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static RescueCase findCase(String id) {// searches for a rescue case by ID
        for (RescueCase rc : rescueList) {
            if (rc.getCaseId().equalsIgnoreCase(id)) return rc;
        }
        return null;
    }

    private static void createCase() {// creates a new rescue case based on user input
        String id;
        while (true) {
            System.out.print("Enter Case ID: ");
            id = scanner.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("ID cannot be blank.");
            } else if (findCase(id) != null) {
                System.out.println("ID must be unique.");
            } else break;
        }
//user inputs
        System.out.print("Enter Animal Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Species: ");
        String species = scanner.nextLine();
        
        System.out.print("Enter Rescue Location: ");
        String location = scanner.nextLine();
        
        System.out.print("Enter Assigned Ranger: ");
        String ranger = scanner.nextLine();

        System.out.print("Enter Rescue Type (1-Injured, 2-Orphaned, 3-Endangered): ");
        String type = scanner.nextLine();

        if (type.equals("1")) {// creates an injured rescue case
            rescueList.add(new InjuredRescue(id, name, species, location, ranger, 5, 200, "Fracture", 1500, true));
        } else if (type.equals("2")) {
            rescueList.add(new OrphanedRescue(id, name, species, location, ranger, 10, 100, 2, 800, true));
        } else if (type.equals("3")) {
            rescueList.add(new EndangeredRescue(id, name, species, location, ranger, 15, 300, "CR", 5000, true));
        }
        System.out.println("Case recorded successfully.");
    }

    private static void searchCase() {// searches for a rescue case by ID and displays its summary
        System.out.print("Enter Case ID to search: ");
        String id = scanner.nextLine();
        RescueCase c = findCase(id);
        if (c != null) {
            System.out.println(c.generateSummary());
        } else {
            System.out.println("Case not found.");
        }
    }

    private static void updateStatus() {// updates the status of a rescue case based on user input
        System.out.print("Enter Case ID: ");
        String id = scanner.nextLine();
        RescueCase c = findCase(id);
        if (c != null) {
            System.out.print("Enter Status (1-Start, 2-Complete): ");
            String op = scanner.nextLine();
            if (op.equals("1")) c.startRescue();
            else if (op.equals("2")) c.completeRescue();
            System.out.println("Status updated.");
        } else {
            System.out.println("Case not found.");
        }
    }

    private static void displayAll() {// displays all rescue cases
        for (RescueCase c : rescueList) {
            System.out.println(c.generateSummary());
        }
    }

    private static void generateReport() {// generates a summary report of all rescue cases with total cost
        double totalCost = 0;
        System.out.println("\n==========================================");
        System.out.println("WILDLIFE RESCUE REPORT");
        System.out.println("==========================================");
        for (RescueCase c : rescueList) {
            System.out.printf("Case ID : %s\nType : %s\nSpecies : %s\nPriority : %s\nStatus : %s\nTotal Cost : R%.2f\n------------------------------------------\n",
                    c.getCaseId(), c.getRescueType(), c.getSpecies(), c.determinePriority(), c.getStatus(), c.calculateTotalCost());
            totalCost += c.calculateTotalCost();
        }
        System.out.println("Total Rescue Cases : " + rescueList.size());
        System.out.printf("Total Rescue Cost  : R%.2f\n", totalCost);
    }
}