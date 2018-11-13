/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import delfin_gruppe10.data.Member;
import delfin_gruppe10.data.Discipline;
import delfin_gruppe10.data.CompetetiveSwimmer;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public interface MasterInterface {
    
    public ArrayList<Member> getAllMembers();
    
    public Member getMember(String name);
    
    public void addMemberDetails(String name, String birthdate, String address, String postnr, String city, String phone, String mail);
    
    public void deleteMember(Member member);
    
    public ArrayList<CompetetiveSwimmer> getCompetetiveSwimmers();
    
    public void addToCompetetiveTeam(Member member, boolean add);
    
    public void addTrainingResult(Member member, Discipline discipline, String time, String date);
    
    public void addCompetetiveResult(Member member, Discipline discipline, String time, String date, String competition, int ranking);
    
    public ArrayList<CompetetiveSwimmer> getTop5(Discipline d);
    
    public void registerPayment(Member member, double amount);
    
    public ArrayList<Member> getMembersInArrears();
            
}