/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author PC 2 2016 SDC-privat
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MasterSystemTest {
    
    private static ArrayList<Member> members = new ArrayList();
    
    public MasterSystemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        members.add(new Member(
                "Simon Norup",
                "20-12-1995",
                "Lyngevej 39", "3660", "Stenløse",
                "48183899", "wormple12@hotmail.com", false));
        members.add(new Member(
                "Jack McDonalds",
                "01-01-1955",
                "Very Derp Street 333", "1111", "Long Way From Here",
                "11112222", "derp@isDerp.derp", true));
        members.add(new Member(
                "Your New Neighbor",
                "06-06-2001",
                "Elm Street 6", "6666", "Realm of Nightmares",
                "66666666", "S6C6A6R6yyy@yes.net", true));
        MasterSystem instance = new MasterSystem(true);
        instance.addMember(members.get(0).getName(), members.get(0).getBirthdate(), members.get(0).getAddress(), members.get(0).getPostnr(), members.get(0).getCity(), members.get(0).getPhone(), members.get(0).getMail(), members.get(0).isActive());
        instance.addMember(members.get(1).getName(), members.get(1).getBirthdate(), members.get(1).getAddress(), members.get(1).getPostnr(), members.get(1).getCity(), members.get(1).getPhone(), members.get(1).getMail(), members.get(1).isActive());
        instance.addMember(members.get(2).getName(), members.get(2).getBirthdate(), members.get(2).getAddress(), members.get(2).getPostnr(), members.get(2).getCity(), members.get(2).getPhone(), members.get(2).getMail(), members.get(2).isActive());
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
    public void test01GetAllMembers() {
        System.out.println("getAllMembers");
        MasterSystem instance = new MasterSystem(true);
        ArrayList<Member> expResult = members;
        ArrayList<Member> result = instance.getAllMembers();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMember method, of class MasterSystem.
     */
    @Test
    public void test02GetMember() {
        System.out.println("getMember");
        String name = "Jack McDonalds";
        MasterSystem instance = new MasterSystem(true);
        Member expResult = members.get(1);
        Member result = instance.getMember(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of addMember method, of class MasterSystem.
     */
    @Test
    public void test03AddMember() {
        System.out.println("addMember");
        String name = "Ariel The Mermaid";
        String birthdate = "04-11-1822";
        String address = "The Sea 1";
        String postnr = "1234";
        String city = "Very Sea";
        String phone = "12345678";
        String mail = "ariel@seamail.sea";
        boolean active = false;
        MasterSystem instance = new MasterSystem(true);
        
        instance.addMember(name, birthdate, address, postnr, city, phone, mail, active);
        members.add(new Member(name, birthdate, address, postnr, city, phone, mail, active));
        
        String actualName = instance.getMember(name).getName();
        assertEquals(name, actualName);
    }

    /**
     * Test of editMember method, of class MasterSystem.
     */
    @Test
    public void test04EditMember() {
        System.out.println("editMember");
        MasterSystem instance = new MasterSystem(true);
        String originalName = "Simon Norup";
        Member originalMember = instance.getMember(originalName);
        String name = "Simon Asholt Norup";
        String birthdate = "20-12-1995";
        String address = originalMember.getAddress();
        String postnr = originalMember.getPostnr();
        String city = originalMember.getCity();
        String phone = "60893899";
        String mail = originalMember.getMail();
        boolean active = true;
        
        instance.editMember(originalName, name, birthdate, address, postnr, city, phone, mail, active);
        members.set(0, new Member(name, birthdate, address, postnr, city, phone, mail, active));
        
        String actualName = instance.getMember(name).getName();
        assertEquals(name, actualName);
    }

    /**
     * Test of deleteMember method, of class MasterSystem.
     */
    @Test
    public void test05DeleteMember() {
        System.out.println("deleteMember");
        MasterSystem instance = new MasterSystem(true);
        String name = "Your New Neighbor";
        instance.deleteMember(name);
        members.remove(2);
        
        ArrayList<Member> actualMembers = instance.getAllMembers();
        for (Member member : actualMembers){
            if (member.getName().equals(name)){
                fail();
            } else if ("Elm Street 6".equals(member.getAddress())){
                fail();
            }
        }
    }
    
    /**
     * Test of registerPayment method, of class MasterSystem.
     */
    @Test
    public void test06RegisterPayment() {
        System.out.println("registerPayment");
        MasterSystem instance = new MasterSystem(true);
        String name = "Simon Asholt Norup";
        double amount = 600.;
        
        instance.registerPayment(name, amount);
        
        double exp = 1000.;
        Member member = instance.getMember(name);
        assertEquals(exp, member.getArrears(), 0.);
    }

    /**
     * Test of getMembersInArrears method, of class MasterSystem.
     */
    @Test
    public void test07GetMembersInArrears() {
        System.out.println("getMembersInArrears");
        MasterSystem instance = new MasterSystem(true);
        String name = "Simon Asholt Norup";
        instance.registerPayment(name, 1000.);
        members.remove(0);
        ArrayList<Member> expResult = members;
        ArrayList<Member> result = instance.getMembersInArrears();
        assertEquals(expResult, result);
    }
    
    // ==========================0
    
    
    
    

    /**
     * Test of getCompetetiveSwimmers method, of class MasterSystem.
     */
    @Test
    public void test08GetCompetetiveSwimmers() {
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
    public void test09AddToCompetetiveTeam() {
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
    public void test10AddTrainingResult() {
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
    public void test11AddCompetetiveResult() {
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
    public void test12GetTop5() {
        System.out.println("getTop5");
        Discipline d = null;
        MasterSystem instance = new MasterSystem(true);
        ArrayList<CompetetiveSwimmer> expResult = null;
        ArrayList<CompetetiveSwimmer> result = instance.getTop5(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
