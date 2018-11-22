
package delfin_gruppe10.domainlogic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Damjan
 */
public class TrainingResultTest {

    public TrainingResultTest() {
    }
    
    CompetetiveSwimmer compSwi = new CompetetiveSwimmer("Jack McDonalds",
            "01-01-1955",
            "Very Derp Street 333", "1111", "Long Way From Here",
            "11112222", "derp@isDerp.derp", true);

    TrainingResult traiResTime = new TrainingResult(Discipline.BUTTERFLY,
            "00:08:55",
            "06-06-1997");

    TrainingResult traiResBut1 = new TrainingResult(Discipline.BUTTERFLY, "01:01:01", "04-05-1997");
    TrainingResult traiResBac2 = new TrainingResult(Discipline.BACKSTROKE, "02:02:02", "04-05-1997");
    TrainingResult traiResCra = new TrainingResult(Discipline.CRAWL, "02:02:02", "04-05-1997");

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        compSwi.setBestTrainingResult(traiResBut1);
        compSwi.setBestTrainingResult(traiResBac2);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTime method, of class TrainingResult.
     */
    @Test
    public void testGetTime() {


        assertEquals(compSwi.getBestTrainingResult(Discipline.BACKSTROKE), traiResBac2);

    }

    /**
     * Test of getDate method, of class TrainingResult.
     */
    @Test
    public void testGetDate() {

        assertEquals(compSwi.getBestTrainingResult(Discipline.BUTTERFLY), traiResBut1);
    }

    /**
     * Test of getDiscipline method, of class TrainingResult.
     */
    @Test
    public void testGetDiscipline() {

        assertEquals(compSwi.getBestTrainingResult(Discipline.BACKSTROKE), traiResBac2);
    }

}
