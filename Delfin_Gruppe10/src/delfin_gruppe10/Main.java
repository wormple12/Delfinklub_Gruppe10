/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10;

import delfin_gruppe10.data.FileHandler;
import delfin_gruppe10.domainlogic.Member;
import java.util.ArrayList;

/**
 *
 * @author PC 2 2016 SDC-privat
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileHandler fH = new FileHandler("member.txt", "competitive.txt");
        Member member = new Member("Jack McDonalds",
                "01-01-1955",
                "Very Derp Street 333", "1111", "Long Way From Here",
                "11112222", "derp@isDerp.derp", true);
        
        Member member3 = new Member("Simon Norup", "20-12-1995", "Lyngevej 39", "3660", "Stenløse", "60893899", "wormple12@hotmail.com", false);
        
        Member member2 = null;
        
        fH.writeMemberToFile(member3);
        //fH.writeMemberToFile(member3);
        //member2 = 
        //ArrayList<Member> members = fH.readMembersFromFile();
        //System.out.println(fH.readMembersFromFile().get(1).getName());
        System.out.println(fH.readMembersFromFile().get(0).getName());
        //System.out.println(member2.getName());
        //System.out.println(fH.readMembersFromFile().get(0).getName());
    }   
}
