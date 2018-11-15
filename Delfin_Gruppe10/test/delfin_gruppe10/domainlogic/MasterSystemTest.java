/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import delfin_gruppe10.data.CompetetiveSwimmer;
import delfin_gruppe10.data.Discipline;
import delfin_gruppe10.data.Member;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PC 2 2016 SDC-privat
 */
public class MasterSystemTest {
    
    public MasterSystemTest() {
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

    /**
     * Test of getAllMembers method, of class MasterSystem.
     */
    @Test
    public void testGetAllMembers() {
        System.out.println("getAllMembers");
        MasterSystem instance = new MasterSystem(true);
        ArrayList<Member> expResult = null;
        ArrayList<Member> result = instance.getAllMembers();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMember method, of class MasterSystem.
     */
    @Test
    public void testGetMember() {
        System.out.println("getMember");
        String name = "";
        MasterSystem instance = new MasterSystem(true);
        Member expResult = null;
        Member result = instance.getMember(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMember method, of class MasterSystem.
     */
    @Test
    public void testAddMember() {
        System.out.println("addMember");
        String name = "";
        String birthdate = "";
        String address = "";
        String postnr = "";
        String city = "";
        String phone = "";
        String mail = "";
        boolean active = false;
        MasterSystem instance = new MasterSystem(true);
        instance.addMember(name, birthdate, address, postnr, city, phone, mail, active);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editMember method, of class MasterSystem.
     */
    @Test
    public void testEditMember() {
        System.out.println("editMember");
        String originalName = "";
        String name = "";
        String birthdate = "";
        String address = "";
        String postnr = "";
        String city = "";
        String phone = "";
        String mail = "";
        boolean active = false;
        MasterSystem instance = new MasterSystem(true);
        instance.editMember(originalName, name, birthdate, address, postnr, city, phone, mail, active);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMember method, of class MasterSystem.
     */
    @Test
    public void testDeleteMember() {
        System.out.println("deleteMember");
        Member member = null;
        MasterSystem instance = new MasterSystem(true);
        instance.deleteMember(member);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompetetiveSwimmers method, of class MasterSystem.
     */
    @Test
    public void testGetCompetetiveSwimmers() {
        System.out.println("getCompetetiveSwimmers");
        MasterSystem instance = new MasterSystem(true);
        ArrayList<CompetetiveSwimmer> expResult = null;
        ArrayList<CompetetiveSwimmer> result = instance.getCompetetiveSwimmers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToCompetetiveTeam method, of class MasterSystem.
     */
    @Test
    public void testAddToCompetetiveTeam() {
        System.out.println("addToCompetetiveTeam");
        Member member = null;
        boolean add = false;
        MasterSystem instance = new MasterSystem(true);
        instance.addToCompetetiveTeam(member, add);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTrainingResult method, of class MasterSystem.
     */
    @Test
    public void testAddTrainingResult() {
        System.out.println("addTrainingResult");
        Member member = null;
        Discipline discipline = null;
        String time = "";
        String date = "";
        MasterSystem instance = new MasterSystem(true);
        instance.addTrainingResult(member, discipline, time, date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCompetetiveResult method, of class MasterSystem.
     */
    @Test
    public void testAddCompetetiveResult() {
        System.out.println("addCompetetiveResult");
        Member member = null;
        Discipline discipline = null;
        String time = "";
        String date = "";
        String competition = "";
        int ranking = 0;
        MasterSystem instance = new MasterSystem(true);
        instance.addCompetetiveResult(member, discipline, time, date, competition, ranking);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTop5 method, of class MasterSystem.
     */
    @Test
    public void testGetTop5() {
        System.out.println("getTop5");
        Discipline d = null;
        MasterSystem instance = new MasterSystem(true);
        ArrayList<CompetetiveSwimmer> expResult = null;
        ArrayList<CompetetiveSwimmer> result = instance.getTop5(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerPayment method, of class MasterSystem.
     */
    @Test
    public void testRegisterPayment() {
        System.out.println("registerPayment");
        Member member = null;
        double amount = 0.0;
        MasterSystem instance = new MasterSystem(true);
        instance.registerPayment(member, amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembersInArrears method, of class MasterSystem.
     */
    @Test
    public void testGetMembersInArrears() {
        System.out.println("getMembersInArrears");
        MasterSystem instance = new MasterSystem(true);
        ArrayList<Member> expResult = null;
        ArrayList<Member> result = instance.getMembersInArrears();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
