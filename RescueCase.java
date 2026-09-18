public abstract class RescueCase implements RescueOperations {//base model for all rescue cases
    private String caseId;
    private String animalName;
    private String species;
    private String rescueLocation;
    private String assignedRanger;
    private int rescueDays;
    private double dailyCareCost;
    private String status;

    public RescueCase(String caseId, String animalName, String species, String rescueLocation, //shared case details initialization
                      String assignedRanger, int rescueDays, double dailyCareCost) {
        this.caseId = caseId;
        this.animalName = animalName;
        this.species = species;
        this.rescueLocation = rescueLocation;
        this.assignedRanger = assignedRanger;
        this.rescueDays = rescueDays;
        this.dailyCareCost = dailyCareCost;
        this.status = "Pending";//default status for new cases
    }

    public String getCaseId() { return caseId; }//encapsulation: getters and setters for private fields
    public String getAnimalName() { return animalName; }
    public String getSpecies() { return species; }
    public String getRescueLocation() { return rescueLocation; }
    public String getAssignedRanger() { return assignedRanger; }
    public int getRescueDays() { return rescueDays; }
    public double getDailyCareCost() { return dailyCareCost; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getBaseCareCost() {//calculate base care cost based on days and daily cost
        return rescueDays * dailyCareCost;
    }

    public abstract double calculateTotalCost();//abstract method for subclasses to implement specific cost calculations
    public abstract String determinePriority();
    public abstract String getRescueType();

    @Override//interface method implementations
    public void startRescue() { this.status = "Rescue in Progress"; }

    @Override
    public void completeRescue() { this.status = "Completed"; }

    @Override
    public String generateSummary() {//generates a formatted summary of the rescue case
        return String.format("ID: %s | Type: %s | Species: %s | Ranger: %s | Priority: %s | Status: %s | Total Cost: R%.2f",
                caseId, getRescueType(), species, assignedRanger, determinePriority(), status, calculateTotalCost());
    }
}