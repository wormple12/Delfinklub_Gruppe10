
package delfin_gruppe10;

import delfin_gruppe10.data.FileHandler;
import delfin_gruppe10.domainlogic.*;
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
        MasterSystem mS = new MasterSystem();
        
        CompetetiveSwimmer comp1 = new CompetetiveSwimmer("Jack McDonalds",
                "01-01-1955",
                "Very Derp Street 333", "1111", "Long Way From Here",
                "11112222", "derp@isDerp.derp");
        Member member = new Member("Jack McDonalds",
                "01-01-1955",
                "Very Derp Street 333", "1111", "Long Way From Here",
                "11112222", "derp@isDerp.derp", true);
        
        CompetetiveSwimmer comp2 = new CompetetiveSwimmer("John Johnson",
                "01-01-1955",
                "Very Derp Street 333", "1111", "Long Way From Here",
                "11112222", "derp@isDerp.derp");
        Member member2 = new Member("John Johnson",
                "01-01-1955",
                "Very Derp Street 333", "1111", "Long Way From Here",
                "11112222", "derp@isDerp.derp", true);

        Member member4 = new Member("a a", "20-12-1995", "Lyngevej 39", "3660", "Stenløse", "60893899", "wormple12@hotmail.com", true);
        CompetetiveSwimmer comp4 = new CompetetiveSwimmer("a a", "20-12-1995", "Lyngevej 39", "3660", "Stenløse", "60893899", "wormple12@hotmail.com");
        
        TrainingResult result = new TrainingResult(Discipline.BUTTERFLY, "01:22:22", "01-01-2011");
        comp4.setBestTrainingResult(result);
        
        CompetetiveResult result2 = new CompetetiveResult(Discipline.CRAWL, "12:07:58", "26-12-2017", "The Easter Parade", 10);
        CompetetiveResult result3 = new CompetetiveResult(Discipline.BREASTSTROKE, "57:29:99", "31-05-2018", "The Easter Parade", 19);
        comp2.addCompetetiveResult(result2);
        comp2.addCompetetiveResult(result3);
        
//        System.out.println(member);
//        System.out.println(member2);
//        System.out.println(member4);
//        System.out.println(comp1);
//        System.out.println(comp2);
//        System.out.println(comp4);
//        System.out.println("");
        
//        fH.writeMemberToFile(member);
//        fH.writeMemberToFile(member2);
//        fH.writeMemberToFile(member4);
//        fH.writeCompetetiveToFile(comp1);
//        fH.writeCompetetiveToFile(comp2);
//        fH.writeCompetetiveToFile(comp4);
        
        ArrayList<Member> members = fH.readMembersFromFile();
        ArrayList<CompetetiveSwimmer> swimmers = fH.readCompetetivesFromFile();
        for (Member myMember : members){
            System.out.println(myMember);
        }
        for (CompetetiveSwimmer swimmer : swimmers){
            System.out.println(swimmer);
        }
        
    }
}
