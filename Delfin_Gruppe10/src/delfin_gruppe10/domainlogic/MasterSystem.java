/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import delfin_gruppe10.data.*;
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

    private final FileHandlerV2 dataAccessor;

    public MasterSystem() {
        this(false);
    }

    public MasterSystem(boolean test) {
        if (test) {
            dataAccessor = new FileHandlerV2(TEST_MEMBER_PATH, TEST_COMPETETIVE_PATH);
        } else {
            dataAccessor = new FileHandlerV2(MEMBER_PATH, COMPETETIVE_PATH);
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
        Member updatedMember = new Member(name, birthdate, address, postnr, city, phone, mail, active);
        dataAccessor.editMemberInFile(originalMember, updatedMember);
    }

    @Override
    public void deleteMember(String name) {
        Member member = getMember(name);
        dataAccessor.deleteMemberInFile(member);
    }

    @Override
    public void registerPayment(String name, double amount) {
        Member member = getMember(name);
        Member updated = member;
        updated.payArrears(amount);
        dataAccessor.editMemberInFile(member, updated);
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
    public void addTrainingResult(Member member, Discipline discipline, String time, String date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCompetetiveResult(Member member, Discipline discipline, String time, String date, String competition, int ranking) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CompetetiveSwimmer> getTop5(Discipline d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
