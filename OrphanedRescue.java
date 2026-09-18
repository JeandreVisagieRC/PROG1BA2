// orphaned animal rescue class
public class OrphanedRescue extends RescueCase {
    private int estimatedAgeMonths;
    private double feedingCost;
    private boolean fosterCareRequired;

    // orphan details
    public OrphanedRescue(String caseId, String animalName, String species, String rescueLocation,
                          String assignedRanger, int rescueDays, double dailyCareCost,
                          int estimatedAgeMonths, double feedingCost, boolean fosterCareRequired) {
        super(caseId, animalName, species, rescueLocation, assignedRanger, rescueDays, dailyCareCost);
        this.estimatedAgeMonths = estimatedAgeMonths;
        this.feedingCost = feedingCost;
        this.fosterCareRequired = fosterCareRequired;
    }

    // care cost calculation
    @Override
    public double calculateTotalCost() {
        return getBaseCareCost() + feedingCost + (fosterCareRequired ? 2500 : 0);
    }

    // assigns priority based on age: High for under 3 months, Medium otherwise
    @Override
    public String determinePriority() {
        return estimatedAgeMonths < 3 ? "High" : "Medium";
    }

    @Override
    public String getRescueType() { 
        return "Orphaned Animal Rescue"; 
    }
}