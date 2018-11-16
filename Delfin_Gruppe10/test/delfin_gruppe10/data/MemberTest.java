/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.data;

import delfin_gruppe10.domainlogic.Member;
import java.time.LocalDate;
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
public class MemberTest {

    public MemberTest() {
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

    @Test
    public void testCreateNewMember() {
        Member m = new Member(
                "Simon Norup",
                "20-12-1995",
                "Lyngevej 39", "3660", "Stenløse",
                "60893899", "wormple12@hotmail.com", false);
        assertNotNull(m);
        assertEquals(LocalDate.of(1995, 12, 20), m.getBirthdate());
    }
    
    @Test
    public void testPassiveMember() {
        Member m = new Member(
                "Simon Norup",
                "20-12-1995",
                "Lyngevej 39", "3660", "Stenløse",
                "60893899", "wormple12@hotmail.com", false);
        assertEquals(22, m.getAge());
        assertEquals(500., m.getYearlyContingent(), 0.);
    }
    
    @Test
    public void testSeniorMembers() {
        Member m = new Member(
                "Jack McDonalds",
                "01-01-1955",
                "Very Derp Street 333", "1111", "Long Way From Here",
                "11112222", "derp@isDerp.derp", true);
        assertEquals(63, m.getAge());
        assertEquals(1600.*0.75, m.getYearlyContingent(), 0.00001);
        
        Member m2 = new Member(
                "Donald McJackson",
                "01-01-1985",
                "Very Derp Street 333", "1111", "Long Way From Here",
                "11112223", "son@isDerp.derp", true);
        assertEquals(33, m2.getAge());
        assertEquals(1600., m2.getYearlyContingent(), 0.00001);
    }
    
    @Test
    public void testJuniorMember() {
        Member m = new Member(
                "Your New Neighbor",
                "06-06-2001",
                "Elm Street 6", "6666", "Realm of Nightmares",
                "66666666", "S6C6A6R6yyy@yes.net", true);
        assertEquals(17, m.getAge());
        assertEquals(1000., m.getYearlyContingent(), 0.);
    }
    
    @Test (expected=IllegalArgumentException.class)
    public void testIllegalName() {
        Member m = new Member(
                "YourNewNeighbor",
                "06-06-2001",
                "Elm Street 6", "6666", "Realm of Nightmares",
                "66666666", "S6C6A6R6yyy@yes.net", true);
    }
    @Test (expected=IllegalArgumentException.class)
    public void testIllegalBirthdate() {
        Member m = new Member(
                "Your New Neighbor",
                "06-06-01",
                "Elm Street 6", "6666", "Realm of Nightmares",
                "66666666", "S6C6A6R6yyy@yes.net", true);
    }
    @Test (expected=IllegalArgumentException.class)
    public void testIllegalAddress() {
        Member m = new Member(
                "Your New Neighbor",
                "06-06-2001",
                "6 Elm Street", "6666", "Realm of Nightmares",
                "66666666", "S6C6A6R6yyy@yes.net", true);
    }
    @Test (expected=IllegalArgumentException.class)
    public void testIllegalPostNr() {
        Member m = new Member(
                "Your New Neighbor",
                "06-06-2001",
                "Elm Street 6", null, "Realm of Nightmares",
                "66666666", "S6C6A6R6yyy@yes.net", true);
    }
    @Test (expected=IllegalArgumentException.class)
    public void testIllegalCity() {
        Member m = new Member(
                "Your New Neighbor",
                "06-06-2001",
                "Elm Street 6", "6666", "N0WH?R6",
                "66666666", "S6C6A6R6yyy@yes.net", true);
    }
    @Test (expected=IllegalArgumentException.class)
    public void testIllegalPhone() {
        Member m = new Member(
                "Your New Neighbor",
                "06-06-2001",
                "Elm Street 6", "6666", "Realm of Nightmares",
                "123456789", "S6C6A6R6yyy@yes.net", true);
    }
    @Test (expected=IllegalArgumentException.class)
    public void testIllegalMail() {
        Member m = new Member(
                "Your New Neighbor",
                "06-06-2001",
                "Elm Street 6", "6666", "Realm of Nightmares",
                "66666666", "S6C6A6R 6yyyyes.net", true);
    }

}
