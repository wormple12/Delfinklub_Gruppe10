/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.data;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Member> readMembersFromFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
