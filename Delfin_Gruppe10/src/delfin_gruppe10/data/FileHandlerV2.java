/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.data;

import delfin_gruppe10.domainlogic.CompetetiveSwimmer;
import delfin_gruppe10.domainlogic.Member;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author PC 2 2016 SDC-privat
 */
public class FileHandlerV2 implements FileHandlerInterface {

    private final String memberPath;
    private final String competetivePath;

    private static Path FILE;

    public FileHandlerV2(String memberPath, String competetivePath) {
        this.memberPath = memberPath;
        this.competetivePath = competetivePath;
        FILE = Paths.get(memberPath);
    }

    private List<String> readFile() {
        // read file and place lines in list
        try {
            return Files.readAllLines(FILE);
        } catch (IOException ex) {
            try {
                Files.createFile(FILE);
                return readFile();
            } catch (IOException e) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void writeMemberToFile(Member member) {
        try {
            List<String> strings = readFile();
            strings.add(member.toString());
            Files.write(FILE, strings);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    @Override
    public ArrayList<Member> readMembersFromFile() {
        ArrayList<Member> members = new ArrayList<>();
        List<String> strings = readFile();
        try {
            for (String string : strings) {
                String[] vars = new String[8];
                int endIndex = 0;
                int startIndex;
                for (int i = 0; i < vars.length; i++) {
                    startIndex = string.indexOf("=", endIndex);
                    endIndex = string.indexOf(",", startIndex);
                    vars[i] = string.substring(startIndex + 1, endIndex);
                }
                LocalDate date = LocalDate.parse(vars[1]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
                vars[1] = formatter.format(date);

                members.add(new Member(vars[0], vars[1], vars[2], vars[3], vars[4], vars[5], vars[6], Boolean.parseBoolean(vars[7])));
            }
            return members;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Animals.txt is not formatted properly.");
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
        ArrayList<Member> allMembers = readMembersFromFile();
        ArrayList<Member> membersNotPaid = new ArrayList();
        
        for(int i = 0; i<allMembers.size(); i++){
            if(allMembers.get(i).getArrears() < allMembers.get(i).getYearlyContingent()){
                membersNotPaid.add(allMembers.get(i));
            }
        }
        return membersNotPaid;
    }

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
