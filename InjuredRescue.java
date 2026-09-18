
// 
public class InjuredRescue extends RescueCase {
    private String injuryDescription;
    private double vetCost;
    private boolean surgeryRequired;

    // passes shared case details to the base class constructor and initializes injury-specific details
    public InjuredRescue(String caseId, String animalName, String species, String rescueLocation,
                         String assignedRanger, int rescueDays, double dailyCareCost,
                         String injuryDescription, double vetCost, boolean surgeryRequired) {
        super(caseId, animalName, species, rescueLocation, assignedRanger, rescueDays, dailyCareCost);
        this.injuryDescription = injuryDescription;
        this.vetCost = vetCost;
        this.surgeryRequired = surgeryRequired;
    }

    // calculates total cost
    @Override
    public double calculateTotalCost() {
        return getBaseCareCost() + vetCost + (surgeryRequired ? 5000 : 0);
    }

    // priority is Critical if surgery is required, else High
    @Override
    public String determinePriority() {
        return surgeryRequired ? "Critical" : "High";
    }

    @Override
    public String getRescueType() { 
        return "Injured Animal Rescue"; 
    }
}



// endangered species rescue class
public class EndangeredRescue extends RescueCase {
    private String conservationClassification;
    private double securityCost;
    private boolean specialistTeamRequired;

    // Constructor initializing endangered species rescue details
    public EndangeredRescue(String caseId, String animalName, String species, String rescueLocation,
                            String assignedRanger, int rescueDays, double dailyCareCost,
                            String conservationClassification, double securityCost, boolean specialistTeamRequired) {
        super(caseId, animalName, species, rescueLocation, assignedRanger, rescueDays, dailyCareCost);
        this.conservationClassification = conservationClassification;
        this.securityCost = securityCost;
        this.specialistTeamRequired = specialistTeamRequired;
    }

    // Calculates base care + security costs + R8000 if specialist team is needed
    @Override
    public double calculateTotalCost() {
        return getBaseCareCost() + securityCost + (specialistTeamRequired ? 8000 : 0);
    }

    // Endangered species rescues always carry Critical priority
    @Override
    public String determinePriority() {
        return "Critical";
    }

    @Override
    public String getRescueType() { 
        return "Endangered Species Rescue"; 
    }
}