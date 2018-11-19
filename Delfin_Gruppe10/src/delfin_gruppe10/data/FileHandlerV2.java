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

    private static Path FILE;
    private static Path competitiveFILE;

    public FileHandlerV2(String memberPath, String competetivePath) {
        FILE = Paths.get(memberPath);
        competitiveFILE = Paths.get(competetivePath);
    }

    private List<String> readFile(Path FILE) {
        // read file and place lines in list
        try {
            return Files.readAllLines(FILE);
        } catch (IOException ex) {
            try {
                Files.createFile(FILE);
                return readFile(FILE);
            } catch (IOException e) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void writeMemberToFile(Member member) {
        try {
            List<String> strings = readFile(FILE);
            strings.add(member.toString());
            Files.write(FILE, strings);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Member> readMembersFromFile() {
        ArrayList<Member> members = new ArrayList<>();
        List<String> strings = readFile(FILE);
        try {
            for (String string : strings) {
                String[] vars = new String[9];
                int endIndex = 0;
                int startIndex;
                for (int i = 0; i < vars.length; i++) {
                    startIndex = string.indexOf("=", endIndex);
                    endIndex = string.indexOf(",", startIndex);
                    if (endIndex == -1){
                        endIndex = string.indexOf("}", startIndex);
                    }
                    vars[i] = string.substring(startIndex + 1, endIndex);
                }
                LocalDate date = LocalDate.parse(vars[1]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
                vars[1] = formatter.format(date);

                members.add(new Member(vars[0], vars[1], vars[2], vars[3], vars[4], vars[5], vars[6], Boolean.parseBoolean(vars[7]), Double.parseDouble(vars[8])));
            }
            return members;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Animals.txt is not formatted properly.");
            return null;
        }
    }

    @Override
    public void editMemberInFile(Member original, Member updated) {
        try {
            List<String> strings = readFile(FILE);
            strings.remove(original.toString());
            strings.add(updated.toString());
            Files.write(FILE, strings);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    @Override
    public void deleteMemberInFile(Member member) {
        try {
            List<String> strings = readFile(FILE);
            strings.remove(member.toString());
            Files.write(FILE, strings);
        } catch (IOException ex) {
            ex.printStackTrace();
        }   
    }

    @Override
    public ArrayList<Member> readMembersInArrearsFromFile() {
        ArrayList<Member> allMembers = readMembersFromFile();
        ArrayList<Member> membersNotPaid = new ArrayList();
        
        for(int i = 0; i<allMembers.size(); i++){
            if(allMembers.get(i).getArrears() > 0){
                membersNotPaid.add(allMembers.get(i));
            }
        }
        return membersNotPaid;
    }
    
    // ===================================================

    @Override
    public void writeCompetetiveToFile(CompetetiveSwimmer swimmer) {
        try {
            List<String> strings = readFile(competitiveFILE);
            strings.add(swimmer.toString());
            Files.write(competitiveFILE, strings);
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }
    //læs resultater from competitive
    @Override
    public ArrayList<CompetetiveSwimmer> readCompetetivesFromFile() {
        ArrayList<CompetetiveSwimmer> competitiveMembers = new ArrayList<>();
        List<String> strings = readFile(competitiveFILE);
        try {
            for (String string : strings) {
                String[] vars = new String[9];
                int endIndex = 0;
                int startIndex;
                for (int i = 0; i < vars.length; i++) {
                    startIndex = string.indexOf("=", endIndex);
                    endIndex = string.indexOf(",", startIndex);
                    if (endIndex == -1){
                        endIndex = string.indexOf("}", startIndex);
                    }
                    vars[i] = string.substring(startIndex + 1, endIndex);
                }
                LocalDate date = LocalDate.parse(vars[1]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
                vars[1] = formatter.format(date);

                competitiveMembers.add(new CompetetiveSwimmer(vars[0], vars[1], vars[2], vars[3], vars[4], vars[5], vars[6]));
            }
            return competitiveMembers;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Animals.txt is not formatted properly.");
            return null;
        }
    }

    @Override
    public void editCompetetiveInFile(CompetetiveSwimmer swimmer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
