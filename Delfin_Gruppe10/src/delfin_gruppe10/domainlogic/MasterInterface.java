
package delfin_gruppe10.domainlogic;

import java.util.ArrayList;

/**
 * 
 * 
 * @author Simon Asholt Norup
 * @version 1.00, 22 Nov 2018
 */
public interface MasterInterface {
    
    public ArrayList<Member> getAllMembers();
    
    public Member getMember(String name);
    
    public void addMember(String name, String birthdate, String address, String postnr, String city, String phone, String mail, boolean active);
    
    public void editMember(String originalName, String name, String birthdate, String address, String postnr, String city, String phone, String mail, boolean active);
    
    public void deleteMember(String name);
    
    public ArrayList<CompetetiveSwimmer> getCompetetiveSwimmers();
    
    public void addToCompetetiveTeam(Member member);
    
    public void removeFromCompetetiveTeam(CompetetiveSwimmer swimmer);
    
    public void addTrainingResult(CompetetiveSwimmer  member, Discipline discipline, String time, String date);
    
    public void addCompetetiveResult(CompetetiveSwimmer member, Discipline discipline, String time, String date, String competition, int ranking);
    
    public ArrayList<CompetetiveSwimmer> getTop5(Discipline d);
    
    public void registerPayment(String name, double amount);
    
    public ArrayList<Member> getMembersInArrears();
    
    public CompetetiveSwimmer getCompSwim(String name);
            
}
