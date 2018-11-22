
package delfin_gruppe10.domainlogic;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Member implements Serializable, Cloneable {

    private String name;
    private final String birthdate;
    private final String address;
    private final String postnr;
    private final String city;
    private final String phone;
    private final String mail;
    private boolean active;
    private double arrears;
    
    public Member(String name, String birthdate, String address, String postnr, String city, String phone, String mail, boolean active, double arrears) {
        this(name, birthdate, address, postnr, city, phone, mail, active);
        this.arrears = arrears;
    }

    public Member(String name, String birthdate, String address, String postnr, String city, String phone, String mail, boolean active) {
        String cause = "Fejl: "; boolean error = false;
        if (name == null || birthdate == null || address == null || postnr == null || city == null || phone == null || mail == null){
            cause += "Et felt er tomt.\n"; error = true;
        } else {
            if (!name.matches("\\D+ \\D+")){
                cause += "Skriv venligst det fulde navn.\n"; error = true;
            } if (!birthdate.matches("\\d{2}-\\d{2}-\\d{4}")){ // birthdate should have other possible formats
              cause += "Fødselsdato skal formateres DD-MM-ÅÅÅÅ.\n"; error = true; 
            } if (!address.matches("\\D+ \\d+")){  // should also have other possible formats for people living in apartments for instance
               cause += "Skriv venligst vej og nummer.\n"; error = true;
            } if (!postnr.matches("\\d{4}")){
               cause += "Skriv venligst et dansk postnummer.\n"; error = true;
            } if (!city.matches("\\D+")){
                cause += "Skriv venligst en gyldig by.\n"; error = true;
            } if (!phone.matches("\\d{8}")){
                cause += "Skriv venligst et telefonnummer på 8 cifre.\n"; error = true;
            } if (!mail.matches("\\S+@\\S+\\.\\S+")){
                cause += "Skriv venligst en gyldig email."; error = true;
            }
        }
        if (error){
            throw new IllegalArgumentException(cause);
        }

        this.birthdate = birthdate;
        this.name = name; // we could choose to capitalize first letter in all words, but isn't important
        this.address = address; // same as above
        this.postnr = postnr;
        this.city = city; // same as above
        this.phone = phone;
        this.mail = mail;
        this.active = active;
        
        try {
            this.arrears = getYearlyContingent();
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("Fejl: Den angivne dato eksisterer ikke.");
        }
    }

    /**
     * This method returns the age of a member object based on the birthdate 
     * 
     * @return an int which is the age in years
     */    
    public int getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(birthdate, formatter);
        return Period.between(date, LocalDate.now()).getYears();
    }

    /**
     * The method returns the yearly contigent for a member object based on the member's age in years
     * 
     * @return a double which is the contigent
     */   
    public double getYearlyContingent() {
        int age = getAge();
        if (isActive()) {
            if (age < 18) {
                return 1000.;
            } else if (age > 60) {
                return 1600.*0.75;
            } else {
                return 1600.;
            }
        } else {
            return 500.;
        }
    }

    public double getArrears() {
        return arrears;
    }
    
    /**
     * This method takes a double and substracts a member object's arrear 
     * 
     * @param amount is the amount of money that is payed
     * @throws IllegalArgumentException if amount is negative or higher than the money required 
     */   
    public void payArrears(double amount){
        if (amount < 0 || amount > arrears){
            throw new IllegalArgumentException();
        }
        arrears -= amount;
    }
    
    /**
     * This method manually defines arrears for a member object
     * 
     * @param amount is the amount of money that is to be payed
     * @throws IllegalArgumentException if amount is negative
     */  
    public void setArrears(double amount){
        if (amount < 0){
            throw new IllegalArgumentException();
        }
        arrears = amount;
    }

    public void setActive(boolean b){
        active = b;
    }

    public boolean isActive() {
        return active;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getAddress() {
        return address;
    }

    public String getPostnr() {
        return postnr;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public String toString() {
        return "Member{" + "name=" + name + ", birthdate=" + birthdate + ", address=" + address + ", postnr=" + postnr + ", city=" + city + ", phone=" + phone + ", mail=" + mail + ", active=" + active + ", arrears=" + arrears + '}';
    }
    
    @Override
    public Member clone() throws CloneNotSupportedException {
        return (Member) super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Member other = (Member) obj;
        if (this.active != other.active) {
            return false;
        }
        if (Double.doubleToLongBits(this.arrears) != Double.doubleToLongBits(other.arrears)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.birthdate, other.birthdate)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.postnr, other.postnr)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.mail, other.mail)) {
            return false;
        }
        return true;
    }
    
}
