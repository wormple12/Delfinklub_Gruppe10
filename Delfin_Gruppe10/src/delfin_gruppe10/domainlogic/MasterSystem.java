/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import delfin_gruppe10.data.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

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
            try {
                Path testPath1 = Paths.get(TEST_MEMBER_PATH);
                Path testPath2 = Paths.get(TEST_COMPETETIVE_PATH);
                Files.deleteIfExists(testPath1);
                Files.deleteIfExists(testPath2);
            } catch (IOException e) {
                // do nuthin' about it
            } finally {
                dataAccessor = new FileHandler(TEST_MEMBER_PATH, TEST_COMPETETIVE_PATH);
            }
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
        return dataAccessor.readCompetetivesFromFile();
    }

    @Override
    public void addToCompetetiveTeam(Member member) {
        CompetetiveSwimmer swimmer = new CompetetiveSwimmer(member.getName(), member.getBirthdate(), member.getAddress(), member.getPostnr(), member.getCity(), member.getPhone(), member.getMail());
        dataAccessor.writeCompetetiveToFile(swimmer);
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
        // should only be active ones?
        ArrayList<CompetetiveSwimmer> swimmers = getCompetetiveSwimmers();
        ArrayList<CompetetiveSwimmer> top5 = new ArrayList<>();
        for (CompetetiveSwimmer swimmer : swimmers){
            TrainingResult result = swimmer.getBestTrainingResult(d);
            if (result != null){ // never null! there's a default value -so change this
                top5.add(swimmer);
            }
        }
        
        top5.sort(new Comparator<CompetetiveSwimmer>() {
            @Override
            public int compare(CompetetiveSwimmer c1, CompetetiveSwimmer c2) {
                String result1 = c1.getBestTrainingResult(d).getTime();
                String result2 = c2.getBestTrainingResult(d).getTime();
                if (!isFaster(result1, result2) == !isFaster(result2, result1)) {
                    return 0;
                }
                return isFaster(result2, result1) ? -1 : 1;
            }
        });
        
        top5.subList(5, top5.size()).clear(); // trims list to a size of 5
        return top5;
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
