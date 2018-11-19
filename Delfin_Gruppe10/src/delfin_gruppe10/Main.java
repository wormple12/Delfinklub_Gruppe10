
package delfin_gruppe10;

import delfin_gruppe10.data.FileHandler;
import delfin_gruppe10.data.FileHandlerV2;
import delfin_gruppe10.domainlogic.CompetetiveSwimmer;
import delfin_gruppe10.domainlogic.Discipline;
import delfin_gruppe10.domainlogic.MasterSystem;
import delfin_gruppe10.domainlogic.Member;
import delfin_gruppe10.domainlogic.TrainingResult;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author PC 2 2016 SDC-privat
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileHandlerV2 fH = new FileHandlerV2("member.txt", "competitive.txt");
        MasterSystem mS = new MasterSystem();
        
        CompetetiveSwimmer member = new CompetetiveSwimmer("Jack McDonalds",
                "01-01-1955",
                "Very Derp Street 333", "1111", "Long Way From Here",
                "11112222", "derp@isDerp.derp");

        CompetetiveSwimmer member4 = new CompetetiveSwimmer("a a", "20-12-1995", "Lyngevej 39", "3660", "Stenløse", "60893899", "wormple12@hotmail.com");
        
        TrainingResult result = new TrainingResult(Discipline.BUTTERFLY, "01:22:22", "01-01-2011");
        member4.setBestTrainingResult(result);
        //System.out.println(mS.getMembersInArrears());
        //mS.registerPayment("Jack McDonalds", 1200);
        
        fH.writeCompetetiveToFile(member);
        fH.writeCompetetiveToFile(member4);
        
        //System.out.println(fH.readMembersFromFile().get(0).getYearlyContingent());
        //System.out.println(fH.readMembersFromFile().get(0).getArrears());
        
        //System.out.println(fH.readMembersFromFile().get(2).getName());
        //System.out.println(fH.readMembersFromFile().get(2).getYearlyContingent());
        //System.out.println(fH.readMembersFromFile().get(2).getArrears());
        //System.out.println(fH.readMembersInArrearsFromFile());
        
        //member2 = fH.readMembersFromFile().get(1);
        //System.out.println(member2.getName());
    }
}
