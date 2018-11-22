
package delfin_gruppe10.domainlogic;

import delfin_gruppe10.data.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * MasterSystem
 * The class acts as a controller class that communicates between a dataAccessor and the UI.
 * It contains the logic, necessary for taking data from the dataAccessor and transforming it
 * into what the UI needs.
 * Also, this class implements an interface that makes sure the contract is unbroken,
 * that is to say, that it follows the specifications given in the use-case diagram.
 * 
 * The two constant String variables in the beginning can be changed if necessary
 * to use different paths for the data files
 *
 * @author Simon Asholt Norup, Lukas Bjørnvad
 * @version 1.00, 22 Nov 2018
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
    
    /**
     * Initializes the MasterSystem by creating a dataAccessor to read from
     *
     * @param test Boolean only used to tell the program which path to use - for junit and other testing purposes, it must be true - otherwise, it must always be false
     */
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

    
    /**
     * Gets all members in the club, both active and passive
     *
     * @return ArrayList containing all members
     * @throws RuntimeException if something in dataAccessor goes wrong
     */
    @Override
    public ArrayList<Member> getAllMembers() {
        return dataAccessor.readMembersFromFile();
    }

    /**
     * Attempts to retrieve a member in the club with a given name
     *
     * @param name the name of the member to search for
     * @return the first possible member to be found with the given name
     * @throws IllegalArgumentException if no members are found with the given name
     */
    @Override
    public Member getMember(String name) {
        ArrayList<Member> members = getAllMembers();
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        throw new IllegalArgumentException("Ingen medlemmer eksisterer med det navn.");
    }

    /**
     * Creates a member with the given basic information and adds it to the list
     *
     * @param name the name of the member to create
     * @param birthdate the birthdate of the member to create (as a String)
     * @param address the street name and number of the member to create
     * @param postnr the zipcode of the member to create
     * @param city the city of the member to create
     * @param phone the phone number of the member to create (without +45) - in the current version, only Danish phone numbers are allowed
     * @param mail the email of the member to create
     * @param active whether the member to create has an active or a passive membership at the club
     * 
     * @throws IllegalArgumentException if @Member throws it. Also if a member already has the given name or the given email.
     */
    @Override
    public void addMember(String name, String birthdate, String address, String postnr, String city, String phone, String mail, boolean active) {
        ArrayList<Member> members = dataAccessor.readMembersFromFile();
        for (Member member : members) {
            if (member.getName().equals(name)) {
                throw new IllegalArgumentException("Navn eksisterer allerede i databasen.");
            } else if (member.getMail().equals(mail)) {
                throw new IllegalArgumentException("Email eksisterer allerede i databasen.");
            }
        }
        Member member = new Member(name, birthdate, address, postnr, city, phone, mail, active);
        dataAccessor.writeMemberToFile(member);
    }

    /**
     * Edits an already existing member. Replaces the member's basic information with the given information.
     * If the yearly contingent rises because of a raise in age or because of the member becoming active,
     * the arrears of the member will be updated as well.
     * If the name is changed, the CompetetiveSwimmer equivalent of the member will have its name updated too.
     *
     * @param originalName the original name of the member to update
     * @param name the updated name of the member
     * @param birthdate the updated birthdate of the member (as a String)
     * @param address the updated street name and number of the member
     * @param postnr the updated zipcode of the member
     * @param city the updated city of the member
     * @param phone the updated phone number of the member (without +45) - in the current version, only Danish phone numbers are allowed
     * @param mail the updated email of the member
     * @param active whether the member to update now has an active or a passive membership at the club
     * 
     * @throws IllegalArgumentException if @Member throws it. Also if a member already has the given name or the given email.
     */
    @Override
    public void editMember(String originalName, String name, String birthdate, String address, String postnr, String city, String phone, String mail, boolean active) {
        Member originalMember = getMember(originalName); // initiate original member and replacement
        Member updatedMember = new Member(name, birthdate, address, postnr, city, phone, mail, active);
        
        // change arrears if necessary
        double arrears = originalMember.getArrears();
        double originalC = originalMember.getYearlyContingent();
        double updatedC = updatedMember.getYearlyContingent();
        if (originalC < updatedC){
            double diff = updatedC - originalC;
            arrears += diff;
        }
        updatedMember.setArrears(arrears);
        
        if (!originalName.equals(name)){ // if name has been changed, also change it in competetive file
            try {
            CompetetiveSwimmer swimmer = getCompSwim(originalName);
            CompetetiveSwimmer updatedSwimmer = swimmer.copy();
            updatedSwimmer.setName(name);
            dataAccessor.editCompetetiveInFile(swimmer, updatedSwimmer);
            } catch (IllegalArgumentException e){
                // member is not competetive, so continue without changing competetive file
            }
        }
        
        dataAccessor.editMemberInFile(originalMember, updatedMember); // replace
    }

    /**
     * Attempts to remove a person from the member list.
     * All information about this former member, including results from training and competition, is deleted permanently.
     *
     * @param name the name of the member to delete
     */
    @Override
    public void deleteMember(String name) {
        try {
            CompetetiveSwimmer swimmer = getCompSwim(name);
            dataAccessor.deleteMemberInFile(swimmer);
        } catch (IllegalArgumentException e){
            // there's no swimmer by that name, so continue without deleting from competetives.txt
        }
        Member member = getMember(name);
        dataAccessor.deleteMemberInFile(member);
    }

    /**
     * Register a payment to pay for a part of the arrears for a given member.
     *
     * @param name the name of the member to register payment for
     * @param amount the amount of DKK to be paid
     * 
     * @throws RuntimeException if cloning is unsuccesful, so that the member's arrears information can't be updated.
     * @throws IllegalArgumentException if a member doesn't exist with the given name, or if the given amount is negative or more than what the member owes
     */
    @Override
    public void registerPayment(String name, double amount) {
        try {
            Member original = getMember(name);
            Member updated = original.clone();
            updated.payArrears(amount);
            dataAccessor.editMemberInFile(original, updated);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Gets all members in the club who are in arrears (meaning they have yet to pay a part of their yearly contingent)
     *
     * @return ArrayList containing all members in arrears
     * @throws RuntimeException if something in dataAccessor goes wrong
     */
    @Override
    public ArrayList<Member> getMembersInArrears() {
        return dataAccessor.readMembersInArrearsFromFile();
    }

    // ===============================================
    
    /**
     * Gets all members who are active on a competetive team, regardless of which team
     *
     * @return ArrayList containing all active competetive swimmers
     * @throws RuntimeException if something in dataAccessor goes wrong
     */
    @Override
    public ArrayList<CompetetiveSwimmer> getCompetetiveSwimmers() {
        ArrayList<CompetetiveSwimmer> swimmers = dataAccessor.readCompetetivesFromFile();
        int count = 0;
        while (count < swimmers.size()) {
            CompetetiveSwimmer swimmer = swimmers.get(count);
            if (!swimmer.isActive()) {
                swimmers.remove(count);
            } else {
                count++;
            }
        }
        return swimmers;
    }
    
    /**
     * Attempts to retrieve a swimmer on any competetive team with the given name
     *
     * @param name the name of the member to search for
     * @return the first possible competetive swimmer to be found with the given name
     * @throws IllegalArgumentException if no competetive swimmers are found with the given name
     */
    @Override
    public CompetetiveSwimmer getCompSwim(String name) {
        ArrayList<CompetetiveSwimmer> members = dataAccessor.readCompetetivesFromFile();
        for (CompetetiveSwimmer member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        throw new IllegalArgumentException("Ingen konkurrencesvømmere findes med det navn.");
    }

    /**
     * Adds the given member to a competetive team, changing his/her membership to active if necessary
     * If the member is already on a competetive team, this method only changes the membership status
     *
     * @param member the member to make active on a competetive team
     * @throws RuntimeException if something in dataAccessor goes wrong
     */
    @Override
    public void addToCompetetiveTeam(Member member) {
        if (!member.isActive()) {
            editMember(member.getName(), member.getName(), member.getBirthdate(), member.getAddress(), member.getPostnr(), member.getCity(), member.getPhone(), member.getMail(), true);
        }
        try {
            CompetetiveSwimmer swimmer = getCompSwim(member.getName()); 
        } catch (IllegalArgumentException e){
            CompetetiveSwimmer swimmer = new CompetetiveSwimmer(member.getName(), member.getBirthdate(), member.getAddress(), member.getPostnr(), member.getCity(), member.getPhone(), member.getMail(), true);
            dataAccessor.writeCompetetiveToFile(swimmer);
        }
    }
    
    /**
     * Removes the given member from his/her competetive team, changing his/her membership to passive if necessary
     *
     * @param swimmer the member to remove from competetive status
     * @throws IllegalArgumentException if the given member already has passive membership
     */
    @Override
    public void removeFromCompetetiveTeam(CompetetiveSwimmer swimmer){
        if (swimmer.isActive()) {
            editMember(swimmer.getName(), swimmer.getName(), swimmer.getBirthdate(), swimmer.getAddress(), swimmer.getPostnr(), swimmer.getCity(), swimmer.getPhone(), swimmer.getMail(), false);
        } else {
            throw new IllegalArgumentException("Den valgte svømmer er ikke på holdet.");
        }
    }

    /**
     * Registers a result from a training session if it is the best result of that swimmer in the given discipline.
     * Only one such result per discipline can be registered at a time
     *
     * @param original the member who swam
     * @param discipline the discipline, the member exercised
     * @param time the result time - how fast did the member complete the training (formatted MM:SS:mm)
     * @param date the date of the training session (formatted DD-MM-YYYY)
     * @throws IllegalArgumentException if the time or date is formatted wrongly
     */
    @Override
    public void addTrainingResult(CompetetiveSwimmer original, Discipline discipline, String time, String date) {
        TrainingResult originalR = original.getBestTrainingResult(discipline);
        TrainingResult newR = new TrainingResult(discipline, time, date);
        CompetetiveSwimmer copy = original.copy();
        if (isFaster(originalR.getTime(), newR.getTime())) {
            copy.setBestTrainingResult(newR);
            dataAccessor.editCompetetiveInFile(original, copy);
        }
    }

    /**
     * Registers a result in a given discipline from a competition. All competetive results are saved.
     * If it is the best result of that swimmer in the given discipline (including all his/her results from other competitions as well as training sessions)
     * it will also be registered as so.
     *
     * @param original the member who swam
     * @param discipline the discipline, the member exercised
     * @param time the result time - how fast did the member complete the competition (formatted MM:SS:mm)
     * @param date the date of the competition (formatted DD-MM-YYYY)
     * @param competition the official name of the competition
     * @param ranking integer representing the official ranking in the given discipline in the given competition
     * @throws IllegalArgumentException if the time or date is formatted wrongly, or if ranking is less than 1
     */
    @Override
    public void addCompetetiveResult(CompetetiveSwimmer original, Discipline discipline, String time, String date, String competition, int ranking) {
        CompetetiveSwimmer updated = original.copy();
        CompetetiveResult r = new CompetetiveResult(discipline,time,date,competition,ranking); 
        updated.addCompetetiveResult(r);
        dataAccessor.editCompetetiveInFile(original, updated);
        addTrainingResult(updated, discipline, time, date);
    }

    /**
     * Gets the 5 swimmers in the club with the best personal results in the given discipline (including both training and competetive results)
     * Orders the swimmers by result times in the given discipline, with the fastest swimmers placed highest in the list
     *
     * @param d the discipline to get top 5 swimmers for
     * @return ArrayList containing the top 5 swimmers in the given discipline, ordered by result time
     */
    @Override
    public ArrayList<CompetetiveSwimmer> getTop5(Discipline d) {
        ArrayList<CompetetiveSwimmer> swimmers = getCompetetiveSwimmers();
        ArrayList<CompetetiveSwimmer> top5 = new ArrayList<>();
        for (CompetetiveSwimmer swimmer : swimmers){
            TrainingResult result = swimmer.getBestTrainingResult(d);
            if (!result.getTime().equals("59:59:00")){ //default value for training results - swimmers with no registered results are never added to the list
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
        
        if (top5.size() > 5){
            top5.subList(5, top5.size()).clear(); // trims list to a size of 5
        }
        
        return top5;
    }
    
    //===========================================
    
    private boolean isFaster(String original, String updated) {
        boolean faster = false;
        int minutes, seconds, nanoseconds;
        minutes = Integer.parseInt(original.substring(0, 2));
        seconds = Integer.parseInt(original.substring(3, 5));
        nanoseconds = Integer.parseInt(original.substring(6, 8));
        int nminutes, nseconds, nnanoseconds;
        nminutes = Integer.parseInt(updated.substring(0, 2));
        nseconds = Integer.parseInt(updated.substring(3, 5));
        nnanoseconds = Integer.parseInt(updated.substring(6, 8));

        if (minutes > nminutes) {
            faster = true;
        } else if (minutes == nminutes && seconds > nseconds) {
            faster = true;
        } else if (minutes == nminutes && seconds == nseconds && nanoseconds > nnanoseconds) {
            faster = true;
        }
        return faster;
    }
}
