/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.data;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import delfin_gruppe10.domainlogic.CompetetiveSwimmer;
import delfin_gruppe10.domainlogic.Member;
import java.util.ArrayList;

/**
 *
 * @author PC 2 2016 SDC-privat
 */
public class FileHandler implements FileHandlerInterface {
    private final String memberPath;
    private final String competetivePath;
    
    public FileHandler(String memberPath, String competetivePath){
        this.memberPath = memberPath; 
        this.competetivePath = competetivePath;
    }

    @Override
    public void writeMemberToFile(Member member) {
    }

    @Override
    public ArrayList<Member> readMembersFromFile() {        
        try{
            FileInputStream fileIn = new FileInputStream(memberPath); 
            ObjectInputStream objIn = new ObjectInputStream(fileIn);       
            
            Member member = null;
            ArrayList<Member> memberList = null;

            try {
                while (true) {
                    member = (Member) objIn.readObject();
                    memberList.add(member);
                }
            } catch (EOFException e) {}
            return memberList;
        } catch(Exception ex){
            ex.printStackTrace();
            return null;
        } 
    }

    @Override
    public void editMemberInFile(String originalName, Member updated) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void deleteMemberInFile(Member member) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Member> readMembersInArrearsFromFile() {
        try{
            FileInputStream fileIn = new FileInputStream(memberPath); 
            ObjectInputStream objIn = new ObjectInputStream(fileIn);       
            
            Member member = null;
            ArrayList<Member> memberList = null;

            try {
                while (true) {
                    member = (Member) objIn.readObject();
                    if(member.getArrears() < member.getYearlyContingent()){
                        memberList.add(member);
                    }
                }
            } catch (EOFException e) {}
            return memberList;
        } catch(Exception ex){
            ex.printStackTrace();
            return null;
        } 
    }

    // ==========================================
    
    
    
    
    
    
    
    
    
    // ARE THESE NECESSARY?
    @Override
    public void writeCompetetiveToFile(CompetetiveSwimmer swimmer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CompetetiveSwimmer> readCompetetivesFromFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editCompetetiveInFile(CompetetiveSwimmer swimmer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
