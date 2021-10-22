package tuition;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Class that conducts JUnit tests on add() and remove() from Roster class.
 *
 * @author Mervin James, Akshar Patel
 */
public class RosterTest {

    /**
     * Conducts a JUnit test on add() from the Roster class.
     */
    @org.junit.Test
    public void add() {
        Student[] sList = new Student[4];
        Roster roster = new Roster(sList);

        //test case #1: adding mode students than the initial roster
        // capacity should be valid.
        Student student1 = new Student("Jane Doe", Major.BA);
        assertTrue(roster.add(student1));
        Student student2 = new Student("Joshua Patel", Major.BA);
        assertTrue(roster.add(student2));
        Student student3 = new Student("Sunny Lin", Major.BA);
        assertTrue(roster.add(student3));
        Student student4 = new Student("Barry Young", Major.BA);
        assertTrue(roster.add(student4));
        Student student5 = new Student("Mary Johnson", Major.BA);
        assertTrue(roster.add(student5));

        //test case #2: adding student not in the roster should be valid.
        Student student = new Student("Robert Johnson", Major.CS);
        assertTrue(roster.add(student));

        //test case #3: adding student already in the roster should be
        // invalid.
        assertFalse(roster.add(student));

        //test case #4: adding Resident student not in the roster should be
        // valid.
        Student rStudent = new Resident("Jessica Smith", Major.CS, 15);
        assertTrue(roster.add(rStudent));

        //test case #5: adding Resident student already in the roster should
        // be invalid.
        assertFalse(roster.add(rStudent));

        //test case #6: adding Nonresident student not in the roster should
        // be valid.
        Student nrStudent = new NonResident("Kevin Ford", Major.CS, 15);
        assertTrue(roster.add(nrStudent));

        //test case #7: adding Nonresident student already in the roster
        // should be invalid.
        assertFalse(roster.add(nrStudent));

        //test case #8: adding Tristate student not in the roster should
        // be valid.
        Student tStudent = new TriState("Nate Reyes", Major.CS, 15, 4000,
                State.NY);
        assertTrue(roster.add(tStudent));

        //test case #9: adding Tristate student already in the roster should
        // be invalid.
        assertFalse(roster.add(tStudent));

        //test case #10: adding International student not in the roster should
        // be valid.
        Student iStudent = new International("Alex Jones", Major.CS, 15,
                false);
        assertTrue(roster.add(iStudent));

        //test case #11: adding International student already in the roster
        // should be invalid.
        assertFalse(roster.add(iStudent));
    }

    /**
     * Conducts a JUnit test on remove() from the Roster class.
     */
    @org.junit.Test
    public void remove() {
        Student[] sList = new Student[4];
        Roster roster = new Roster(sList);

        //test case #1: removing student in the roster should be valid.
        Student student = new Student("Robert Johnson", Major.CS);
        assertTrue(roster.add(student));
        assertTrue(roster.remove(student));

        //test case #2: removing student not in the roster should be invalid.
        assertFalse(roster.remove(student));

        //test case #3: removing Resident student in the roster should be
        // valid.
        Student rStudent = new Resident("Jessica Smith", Major.CS, 15);
        assertTrue(roster.add(rStudent));
        assertTrue(roster.remove(rStudent));

        //test case #4: removing Resident student not in the roster should be
        // invalid.
        assertFalse(roster.remove(rStudent));

        //test case #5: removing Nonresident student in the roster should be
        // valid.
        Student nrStudent = new NonResident("Kevin Ford", Major.CS, 15);
        assertTrue(roster.add(nrStudent));
        assertTrue(roster.remove(nrStudent));

        //test case #6: removing Nonresident student not in the roster should
        // be invalid.
        assertFalse(roster.remove(nrStudent));

        //test case #7: removing Tristate student in the roster should be
        // valid.
        Student tStudent = new TriState("Nate Reyes", Major.CS, 15, 4000,
                State.NY);
        assertTrue(roster.add(tStudent));
        assertTrue(roster.remove(tStudent));

        //test case #8: removing Tristate student not in the roster should be
        // invalid.
        assertFalse(roster.remove(tStudent));

        //test case #9: removing International student in the roster should
        // be valid.
        Student iStudent = new International("Alex Jones", Major.CS, 15,
                false);
        assertTrue(roster.add(iStudent));
        assertTrue(roster.remove(iStudent));

        //test case #10: removing International student not in the roster
        // should be invalid.
        assertFalse(roster.remove(iStudent));
    }
}