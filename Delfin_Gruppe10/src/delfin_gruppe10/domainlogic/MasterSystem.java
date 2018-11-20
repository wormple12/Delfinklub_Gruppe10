/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import delfin_gruppe10.data.*;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class MasterSystem implements MasterInterface {

    private static final String MEMBER_PATH = "members.txt";
    private static final String COMPETETIVE_PATH = "competetives.txt";

    // == DO NOT CHANGE ==
    private static final String TEST_MEMBER_PATH = "testMembers.txt";
    private static final String TEST_COMPETETIVE_PATH = "testCompetetives.txt";
    // ===================

    private final FileHandler dataAccessor;

    public MasterSystem() {
        this(false);
    }

    public MasterSystem(boolean test) {
        if (test) {
            dataAccessor = new FileHandler(TEST_MEMBER_PATH, TEST_COMPETETIVE_PATH);
        } else {
            dataAccessor = new FileHandler(MEMBER_PATH, COMPETETIVE_PATH);
        }
    }

    @Override
    public ArrayList<Member> getAllMembers() {
        return dataAccessor.readMembersFromFile();
    }

    @Override
    public Member getMember(String name) {
        ArrayList<Member> members = dataAccessor.readMembersFromFile();
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        throw new IllegalArgumentException("No members exists with that name.");
    }

    @Override
    public void addMember(String name, String birthdate, String address, String postnr, String city, String phone, String mail, boolean active) {
        ArrayList<Member> members = dataAccessor.readMembersFromFile();
        for (Member member : members) {
            if (member.getName().equals(name)) {
                throw new IllegalArgumentException("Name already exists in database.");
            } else if (member.getMail().equals(mail)) {
                throw new IllegalArgumentException("Email already exists in database.");
            }
        }
        Member member = new Member(name, birthdate, address, postnr, city, phone, mail, active);
        dataAccessor.writeMemberToFile(member);
    }

    @Override
    public void editMember(String originalName, String name, String birthdate, String address, String postnr, String city, String phone, String mail, boolean active) {
        Member originalMember = getMember(originalName);
        double arrears = originalMember.getArrears();
        Member updatedMember = new Member(name, birthdate, address, postnr, city, phone, mail, active);
        double originalC = originalMember.getYearlyContingent();
        double updatedC = updatedMember.getYearlyContingent();
        if (originalC < updatedC){
            double diff = updatedC - originalC;
            arrears += diff;
        }
        updatedMember.setArrears(arrears);
        dataAccessor.editMemberInFile(originalMember, updatedMember);
    }

    @Override
    public void deleteMember(String name) {
        Member member = getMember(name);
        dataAccessor.deleteMemberInFile(member);
    }

    @Override
    public void registerPayment(String name, double amount) {
        try {
            Member original = getMember(name);
            Member updated = original.clone();
            updated.payArrears(amount);
            dataAccessor.editMemberInFile(original, updated);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Member> getMembersInArrears() {
        return dataAccessor.readMembersInArrearsFromFile();
    }

    // ===========================
    @Override
    public ArrayList<CompetetiveSwimmer> getCompetetiveSwimmers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addToCompetetiveTeam(Member member, boolean add) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTrainingResult(CompetetiveSwimmer member, Discipline discipline, String time, String date) {
    TrainingResult og = member.getBestTrainingResult(discipline);
    TrainingResult nw = new TrainingResult(discipline, time, date);
    }

    @Override
    public void addCompetetiveResult(CompetetiveSwimmer member, Discipline discipline, String time, String date, String competition, int ranking) {
       try{
        CompetetiveSwimmer updated = member.clone();
        CompetetiveResult r = new CompetetiveResult(discipline,time,date,competition,ranking); 
        updated.addCompetetiveResult(r);
        dataAccessor.editCompetetiveInFile(member, updated);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<CompetetiveSwimmer> getTop5(Discipline d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private boolean isFaster(String og, String nw){
     boolean faster = false;
     int minutes,seconds,nanoseconds;
     minutes = Integer.parseInt(og.substring(0, 2));
     seconds = Integer.parseInt(og.substring(3,5));
     nanoseconds = Integer.parseInt(og.substring(6, 8));
     int nminutes, nseconds, nnanoseconds;
     nminutes = Integer.parseInt(nw.substring(0, 2));
     nseconds = Integer.parseInt(nw.substring(3,5));
     nnanoseconds = Integer.parseInt(nw.substring(6, 8));
     
     if(minutes > nminutes){
       faster = true;  
     }else if(minutes == nminutes && seconds>nseconds){
         faster = true;
     }else if(minutes == nminutes && seconds==nseconds && nanoseconds > nnanoseconds){
         faster = true;
     }
     return faster;
    } 

}
