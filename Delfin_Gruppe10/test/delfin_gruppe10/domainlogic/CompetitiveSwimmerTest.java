/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import static delfin_gruppe10.domainlogic.Discipline.BUTTERFLY;
import static delfin_gruppe10.domainlogic.Discipline.CRAWL;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ludvig
 */
public class CompetitiveSwimmerTest {
    
    public CompetitiveSwimmerTest() {
    }
    
    CompetetiveSwimmer compSwi = new CompetetiveSwimmer("Jack McDonalds",
                "01-01-1955",
                "Very Derp Street 333", "1111", "Long Way From Here",
                "11112222", "derp@isDerp.derp");
    
    TrainingResult traiResBut1 = new TrainingResult(BUTTERFLY, "01:01:01", "04-05-1997");
    TrainingResult traiResBut2 = new TrainingResult(BUTTERFLY, "02:02:02", "04-05-1997");
    TrainingResult traiResCra = new TrainingResult(CRAWL, "02:02:02", "04-05-1997");
    
    CompetetiveResult compRes1 = new CompetetiveResult(BUTTERFLY, "03:03:03", "07-08-09", "Lyngby", 1);
    CompetetiveResult compRes2 = new CompetetiveResult(CRAWL, "03:03:03", "07-08-09", "Lyngby", 2);
    CompetetiveResult compRes3 = new CompetetiveResult(BUTTERFLY, "01:02:03", "07-08-11", "Holte", 3);
    
    ArrayList<CompetetiveResult> cRList = new ArrayList();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        compSwi.setBestTrainingResult(traiResBut2);
        compSwi.setBestTrainingResult(traiResCra);
        compSwi.addCompetetiveResult(compRes1);
        compSwi.addCompetetiveResult(compRes2);
        compSwi.addCompetetiveResult(compRes3);
        
        cRList.add(compRes1);
        cRList.add(compRes2);
        cRList.add(compRes3);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void getBestTrainingResultTest(){      
        assertEquals(compSwi.getBestTrainingResult(BUTTERFLY), traiResBut2);
    } 
    
//    @Test
//    public void getCompetetiveResultsTest(){
//        assertEquals(compSwi.getCompetetiveResults(), cRList);
//    }
}
