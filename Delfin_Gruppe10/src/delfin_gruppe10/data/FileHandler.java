/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.data;

import delfin_gruppe10.domainlogic.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC 2 2016 SDC-privat
 */
public class FileHandler implements FileHandlerInterface {

    private static Path FILE;
    private static Path competetiveFILE;

    public FileHandler(String memberPath, String competetivePath) {
        try {
            FILE = Paths.get(memberPath);
            competetiveFILE = Paths.get(competetivePath);
        } catch (InvalidPathException e) {
            throw new RuntimeException();
        }
    }

    private List<String> readFile(Path FILE) throws IOException {
        // read file and place lines in list
        try {
            return Files.readAllLines(FILE);
        } catch (IOException ex) {
            try {
                Files.createFile(FILE);
                return readFile(FILE);
            } catch (IOException e) {
                throw new IOException();
            }
        }
    }

    @Override
    public void writeMemberToFile(Member member) {
        try {
            List<String> strings = readFile(FILE);
            strings.add(member.toString());
            Files.write(FILE, strings);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public ArrayList<Member> readMembersFromFile() {
        ArrayList<Member> members = new ArrayList<>();
        try {
            List<String> strings = readFile(FILE);
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
                String name = vars[0]; String birthdate = vars[1];
                String address = vars[2]; String postNr = vars[3]; String city = vars[4];
                String phone = vars[5]; String mail = vars[6];
                String active = vars[7]; String arrears = vars[8];

                members.add(new Member(name, birthdate, address, postNr, city, phone, mail, Boolean.parseBoolean(active), Double.parseDouble(arrears)));
            }
            return members;
        } catch (Exception e) {
            throw new RuntimeException("members.txt is not formatted properly.");
        }
    }

    @Override
    public void editMemberInFile(Member original, Member updated) {
        try {
            List<String> strings = readFile(FILE);
            strings.remove(original.toString());
            strings.add(updated.toString());
            Files.write(FILE, strings);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteMemberInFile(Member member) {
        try {
            List<String> strings = readFile(FILE);
            strings.remove(member.toString());
            Files.write(FILE, strings);
        } catch (IOException ex) {
            throw new RuntimeException();
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
            List<String> strings = readFile(competetiveFILE);
            strings.add(swimmer.toString());
            Files.write(competetiveFILE, strings);
        } catch (IOException ex) {
            throw new RuntimeException();

        }
    }

    @Override
    public ArrayList<CompetetiveSwimmer> readCompetetivesFromFile() {
        ArrayList<CompetetiveSwimmer> competitiveMembers = new ArrayList<>();
        try {
            List<String> strings = readFile(competetiveFILE);
            int count = 0;
            for (String string : strings) {
                int startIndex = string.indexOf("=");
                int endIndex = string.indexOf(",", startIndex);
                String name = string.substring(startIndex+1, endIndex);
                
                ArrayList<Member> members = readMembersFromFile();
                Member m = null;
                for (Member member : members){
                    if (member.getName().equals(name)){
                        m = member;
                        break;
                    }
                }

                competitiveMembers.add(new CompetetiveSwimmer(m.getName(), m.getBirthdate(), m.getAddress(), m.getPostnr(), m.getCity(), m.getPhone(), m.getMail()));
                
                for (int i = 0; i < 4; i++) {
                    startIndex = string.indexOf("=", endIndex);
                    endIndex = string.indexOf(",", startIndex);
                    String time = string.substring(startIndex + 1, endIndex);
                    startIndex = string.indexOf("=", endIndex);
                    endIndex = string.indexOf(")", startIndex);
                    String date = string.substring(startIndex + 1, endIndex);
                    
                    TrainingResult result = new TrainingResult(Discipline.values()[i], time, date);
                    competitiveMembers.get(count).setBestTrainingResult(result);
                }
                
                while (true) {
                    try {
                        String[] vars = new String[5];
                        for (int i = 0; i < vars.length; i++) {
                            startIndex = string.indexOf("=", endIndex);
                            endIndex = string.indexOf(",", startIndex);
                            if (i == 4) {
                                endIndex = string.indexOf(")", startIndex);
                            }
                            if (startIndex == -1) {
                                throw new StringIndexOutOfBoundsException();
                            }
                            vars[i] = string.substring(startIndex + 1, endIndex);
                        }
                        Discipline discipline = Discipline.valueOf(vars[0]);
                        String time = vars[1];
                        String date = vars[2];
                        String competition = vars[3];
                        int ranking = Integer.parseInt(vars[4]);

                        CompetetiveResult result = new CompetetiveResult(discipline, time, date, competition, ranking);
                        competitiveMembers.get(count).addCompetetiveResult(result);
                    } catch (StringIndexOutOfBoundsException e) {
                        break;
                    }
                }
                
                count++;
            }
            
            return competitiveMembers;
            
        } catch (Exception e) {
            throw new RuntimeException("Competetives.txt is not formatted properly.");
        }
    }

    @Override
    public void editCompetetiveInFile(CompetetiveSwimmer original, CompetetiveSwimmer updated) {
        try {
            List<String> strings = readFile(competetiveFILE);
            strings.remove(original.toString());
            strings.add(updated.toString());
            Files.write(competetiveFILE, strings);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

}
