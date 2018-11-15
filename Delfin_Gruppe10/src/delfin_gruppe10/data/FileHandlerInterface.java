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
public interface FileHandlerInterface {
    
    public void writeMemberToFile(Member member);
    
    public ArrayList<Member> readMembersFromFile();
    
    public void editMemberInFile(Member who, Member updated);
    
    public void writeCompetetiveToFile(CompetetiveSwimmer swimmer);
    
    public ArrayList<CompetetiveSwimmer> readCompetetivesFromFile();
    
    public void editCompetetiveInFile(CompetetiveSwimmer swimmer);
    
}
