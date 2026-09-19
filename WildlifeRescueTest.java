import org.junit.Test;
import static org.junit.Assert.*;

public class WildlifeRescueTest {

    // test 1 - verify InjuredRescue cost calculation (5 days * R200/day = R1000 + R1500 vet + R5000 surgery = R7500)
    @Test
    public void testInjuredRescueCalculateTotalCost() {
        InjuredRescue injured = new InjuredRescue("IR001", "Leo", "Lion", "Kruger", "Ranger John", 5, 200.0, "Leg Fracture", 1500.0, true);
        assertEquals(7500.0, injured.calculateTotalCost(), 0.001);
    }

    // Test 2: Verify InjuredRescue priority logic (Surgery = Critical, No Surgery = High)
    @Test
    public void testInjuredRescueDeterminePriority() {
        InjuredRescue withSurgery = new InjuredRescue("IR001", "Leo", "Lion", "Kruger", "Ranger John", 5, 200.0, "Leg Fracture", 1500.0, true);
        InjuredRescue withoutSurgery = new InjuredRescue("IR002", "Maya", "Cheetah", "Kruger", "Ranger Jane", 3, 150.0, "Minor Scratch", 300.0, false);
        
        assertEquals("Critical", withSurgery.determinePriority());
        assertEquals("High", withoutSurgery.determinePriority());
    }

    // Test 3: Verify OrphanedRescue cost calculation (10 days * R100/day = R1000 + R800 feeding + R2500 foster = R4300)
    @Test
    public void testOrphanedRescueCalculateTotalCost() {
        OrphanedRescue orphan = new OrphanedRescue("OR001", "Bambino", "Antelope@", "Addo", "Ranger Sam", 10, 100.0, 2, 800.0, true);
        assertEquals(4300.0, orphan.calculateTotalCost(), 0.001);
    }

    // Test 4: Verify OrphanedRescue priority based on age (< 3 months = High, >= 3 months = Medium)
    @Test
    public void testOrphanedRescueDeterminePriority() {
        OrphanedRescue youngOrphan = new OrphanedRescue("OR001", "Bambino", "Antelope", "Addo", "Ranger Sam", 10, 100.0, 2, 800.0, true);
        OrphanedRescue olderOrphan = new OrphanedRescue("OR002", "Kip", "Zebra", "Addo", "Ranger Sam", 10, 100.0, 5, 800.0, false);
        
        assertEquals("High", youngOrphan.determinePriority());
        assertEquals("Medium", olderOrphan.determinePriority());
    }

    // Test 5: Verify EndangeredRescue cost calculation (15 days * R300/day = R4500 + R5000 security + R8000 specialist = R17500)
    @Test
    public void testEndangeredRescueCalculateTotalCost() {
        EndangeredRescue endangered = new EndangeredRescue("ER001", "Rhino1", "Black Rhino", "Hluhluwe", "Ranger Alex", 15, 300.0, "CR", 5000.0, true);
        assertEquals(17500.0, endangered.calculateTotalCost(), 0.001);
    }

    // Test 6: Verify status changes when starting and completing a rescue
    @Test
    public void testRescueCaseStatusUpdates() {
        InjuredRescue case1 = new InjuredRescue("IR001", "Leo", "Lion", "Kruger", "Ranger John", 5, 200.0, "Cut", 500.0, false);
        
        assertEquals("Pending", case1.getStatus());
        
        case1.startRescue();
        assertEquals("Rescue in Progress", case1.getStatus());
        
        case1.completeRescue();
        assertEquals("Completed", case1.getStatus());
    }
}