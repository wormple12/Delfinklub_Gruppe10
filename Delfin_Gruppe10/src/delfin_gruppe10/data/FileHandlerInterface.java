package delfin_gruppe10.data;

import delfin_gruppe10.domainlogic.CompetetiveSwimmer;
import delfin_gruppe10.domainlogic.Member;
import java.util.ArrayList;

/**
 * Interface for the FileHandler class
 * 
 * @author Simon Asholt Norup
 * @version 1.00, 22 Nov 2018
 */
public interface FileHandlerInterface {

    public void writeMemberToFile(Member member);

    public ArrayList<Member> readMembersFromFile();

    public void editMemberInFile(Member original, Member updated);

    public void deleteMemberInFile(Member member);

    public ArrayList<Member> readMembersInArrearsFromFile();

    public void writeCompetetiveToFile(CompetetiveSwimmer swimmer);

    public ArrayList<CompetetiveSwimmer> readCompetetivesFromFile();

    public void editCompetetiveInFile(CompetetiveSwimmer original, CompetetiveSwimmer updated);

}
