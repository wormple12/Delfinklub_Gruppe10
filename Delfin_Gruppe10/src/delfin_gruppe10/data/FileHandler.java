
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
 * This class handles communication with the .txt files. 
 * It is capable of writing to, reading and editing objects stored in .txt files.
 * It can handle both member and competetive swimmer objects. 
 * @author Simon Asholt Norup, Ludvig Bramsen, Damjan Djurisic
 * @version 1.00, 22 Nov 2018
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

  /**
   * Reads .txt files
   * Is used directly or indirectly by every other method in this class
   * 
   * @param FILE must be a path 
   * @return an arraylist of strings of everything in the file it was made to read
   * @throws IOException if it fails to read the desired file.
  */   
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

    /**
     * Writes member objects to .txt file
     * 
     * @param member to convert to text
     * @throws RuntimeException if it encounters any kind of exception
     */   
    @Override
    public void writeMemberToFile(Member member) {
        try {
            List<String> strings = readFile(FILE);
            strings.add(member.toString());
            Files.write(FILE, strings);
        } catch (Exception ex) {
            throw new RuntimeException("Fejl. Kontakt programmøren.");
        }
    }

    /**
     * Reads member objects from a .txt file
     * 
     * @return an arraylist of members
     * @throws RuntimeException if the .txt file it writes to isn't formatted properly 
     */   
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

    /**
     * Takes two member objects and replaces the first one with the second
     * 
     * @param original the member object that is going to be replaced
     * @param updated the member object that is going to replace the original
     * @throws RuntimeException if the .txt file it writes to isn't formatted properly
     */    
    @Override
    public void editMemberInFile(Member original, Member updated) {
        try {
            List<String> strings = readFile(FILE);
            strings.remove(original.toString());
            strings.add(updated.toString());
            Files.write(FILE, strings);
        } catch (Exception ex) {
            throw new RuntimeException("members.txt is not formatted properly.");
        }
    }

    /**
     * The method deletes a specified member object
     * 
     * @param member to delete from file
     * @throws RuntimeException if the .txt file it writes to isn't formatted properly
     */   
    @Override
    public void deleteMemberInFile(Member member) {
        try {
            if (member instanceof CompetetiveSwimmer) {
                List<String> strings = readFile(competetiveFILE);
                CompetetiveSwimmer swimmer = (CompetetiveSwimmer)member;
                strings.remove(swimmer.toString());
                Files.write(competetiveFILE, strings);
            }
            List<String> strings = readFile(FILE);
            strings.remove(((Member)member).toString());
            Files.write(FILE, strings);
        } catch (IOException ex) {
            throw new RuntimeException("members.txt is not formatted properly.");
        }
    }

    /**
     * This method reads members in arrears
     * 
     * @return arraylist
     */   
    @Override
    public ArrayList<Member> readMembersInArrearsFromFile() {
        ArrayList<Member> allMembers = readMembersFromFile();
        ArrayList<Member> membersNotPaid = new ArrayList();

        for (int i = 0; i < allMembers.size(); i++) {
            if (allMembers.get(i).getArrears() > 0) {
                membersNotPaid.add(allMembers.get(i));
            }
        }
        return membersNotPaid;
    }
    
    // ===================================================

    
    /**
     * Writes competetive swimmer objects to .txt file
     * 
     * @param swimmer to convert to text
     * @throws RuntimeException if it fails to read the desired file. 
     */    
    @Override
    public void writeCompetetiveToFile(CompetetiveSwimmer swimmer) {
        try {
            List<String> strings = readFile(competetiveFILE);
            strings.add(swimmer.toString());
            Files.write(competetiveFILE, strings);
        } catch (IOException ex) {
            throw new RuntimeException("Fejl. Kontakt programmøren.");

        }
    }

    /**
     * Reads CompetetiveSwimmer objects from .txt file
     * 
     * @return arraylist of CompetetiveSwimmer
     * @throws StringIndexOutOfBoundsException if the index is out of bound
     * @throws RuntimeException if the .txt file is not formatted properly
     */    
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
                    if (member.getName().equals(name) ){
                        m = member;
                        break;
                    }
                }

                competitiveMembers.add(new CompetetiveSwimmer(m.getName(), m.getBirthdate(), m.getAddress(), m.getPostnr(), m.getCity(), m.getPhone(), m.getMail(), m.isActive()));
                
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
            throw new RuntimeException("competetives.txt is not formatted properly.");
        }
    }

    /**
     * Replaces CompetetiveSwimmer object with another one
     * 
     * @param original CompetetiveSwimmer object, the one to be replaced
     * @param updated CompetetiveSwimmer object, the one that shall take its place
     * @throws RuntimeException if the file is not formatted properly 
     */   
    @Override
    public void editCompetetiveInFile(CompetetiveSwimmer original, CompetetiveSwimmer updated) {
        try {
            List<String> strings = readFile(competetiveFILE);
            strings.remove(original.toString());
            strings.add(updated.toString());
            Files.write(competetiveFILE, strings);
        } catch (Exception ex) {
            throw new RuntimeException("competetives.txt is not formatted properly.");
        }
    }

}
