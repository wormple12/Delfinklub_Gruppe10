/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import delfin_gruppe10.data.FileHandler;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class FileHandlerTest {
    
    public Path FILE;
    public Path competetiveFILE;
    
    FileHandler fH = new FileHandler("memberTest.txt", "competetiveTest.txt");
    
    Member member1 = new Member("Jack McDonalds", "01-01-1955", "Very Derp Street 333", "1111", "Long Way From Here", "11112222", "derp@isDerp.derp", true);
    Member member2 = new Member("John Johnson", "01-01-1972", "Very Derp Street 456", "1195", "Long Hay From There", "10312972", "herp@isBerp.derp", false);
    Member member3 = new Member("a a", "20-12-1995", "Lyngevej 39", "3660", "Stenløse", "60893899", "wormple12@hotmail.com", true);
    
    CompetetiveSwimmer comp1 = new CompetetiveSwimmer("Jack McDonalds", "01-01-1955", "Very Derp Street 333", "1111", "Long Way From Here", "11112222", "derp@isDerp.derp");
    CompetetiveSwimmer comp2 = new CompetetiveSwimmer("John Johnson", "01-01-1955", "Very Derp Street 333", "1111", "Long Way From Here", "11112222", "derp@isDerp.derp");
    CompetetiveSwimmer comp3 = new CompetetiveSwimmer("a a", "20-12-1995", "Lyngevej 39", "3660", "Stenløse", "60893899", "wormple12@hotmail.com");
    
    TrainingResult traiResBut1 = new TrainingResult(Discipline.BUTTERFLY, "01:01:01", "04-05-1997");
    TrainingResult traiResBut2 = new TrainingResult(Discipline.BUTTERFLY, "02:02:02", "04-05-1997");
    TrainingResult traiResCra = new TrainingResult(Discipline.CRAWL, "02:02:02", "04-05-1997");

    CompetetiveResult compRes1 = new CompetetiveResult(Discipline.BUTTERFLY, "03:03:03", "07-08-2009", "Lyngby", 1);
    CompetetiveResult compRes2 = new CompetetiveResult(Discipline.CRAWL, "03:03:03", "07-08-2009", "Lyngby", 2);
    CompetetiveResult compRes3 = new CompetetiveResult(Discipline.BUTTERFLY, "01:02:03", "07-08-2011", "Holte", 3);

    ArrayList<CompetetiveResult> cRList = new ArrayList();
    
    public FileHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void readMembersFromFile() throws FileNotFoundException{
        fH.writeMemberToFile(member1);
        fH.writeMemberToFile(member2);
        fH.writeMemberToFile(member3);
        
        assertEquals(fH.readMembersFromFile().get(0), member1); 
        assertEquals(fH.readMembersFromFile().get(1), member2);
        assertEquals(fH.readMembersFromFile().get(2), member3);
        
        PrintWriter pw = new PrintWriter("memberTest.txt");
        pw.close();
    }
    
    @Test 
    public void editMemberInFileTest() throws FileNotFoundException{
        fH.writeMemberToFile(member1);
        assertEquals(fH.readMembersFromFile().get(0), member1);
        fH.editMemberInFile(member1, member2);
        assertEquals(fH.readMembersFromFile().get(0), member2);
        
        PrintWriter pw = new PrintWriter("memberTest.txt");
        pw.close();
    }
    
    @Test
    public void deleteMemberInFileTest() throws FileNotFoundException{
        fH.writeMemberToFile(member1);
        fH.writeMemberToFile(member2);
        fH.writeMemberToFile(member3);
        
        fH.deleteMemberInFile(member1);
        
        assertNotEquals(fH.readMembersFromFile().get(0), member1);
        assertEquals(fH.readMembersFromFile().get(1), member3);
        
        PrintWriter pw = new PrintWriter("memberTest.txt");
        pw.close();
    }
    
    //er den her rigtigt lavet?
    @Test
    public void readMembersInArrearsFromFile() throws FileNotFoundException{
        fH.writeMemberToFile(member1);
        fH.writeMemberToFile(member2);
        fH.writeMemberToFile(member3);
        //overvej at ændre arrears og se hvad der sker
        
        assertEquals(fH.readMembersInArrearsFromFile(), fH.readMembersFromFile());
        
        PrintWriter pw = new PrintWriter("memberTest.txt");
        pw.close();
    }
    
    @Test
    public void readCompetetiveToFileTest() throws FileNotFoundException{
        fH.writeCompetetiveToFile(comp1);
        fH.writeCompetetiveToFile(comp2);
        fH.writeCompetetiveToFile(comp3);
        
        assertEquals(fH.readCompetetivesFromFile().get(0), comp1); 
        assertEquals(fH.readCompetetivesFromFile().get(1), comp2);
        assertEquals(fH.readCompetetivesFromFile().get(2), comp3);
        
        PrintWriter pw = new PrintWriter("competetiveTest.txt");
        pw.close();
    }
}
