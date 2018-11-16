/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

public class Member implements Serializable {

    private final String name;
    private final LocalDate birthdate;
    private final String address;
    private final String postnr;
    private final String city;
    private final String phone;
    private final String mail;
    private boolean active;
    private double arrears;

    public Member(String name, String birthdate, String address, String postnr, String city, String phone, String mail, boolean active) {
        String cause = "Fejl: "; boolean error = false;
        if (name == null || birthdate == null || address == null || postnr == null || city == null || phone == null || mail == null){
            cause += "Et felt er tomt.\n"; error = true;
        } else if (!name.matches(".* .*")){
            cause += "Skriv venligst det fulde navn.\n"; error = true;
        } else if (!birthdate.matches("\\d{2}-\\d{2}-\\d{4}")){ // birthdate should have other possible formats
            cause += "Fødselsdato skal formateres DD-MM-ÅÅÅÅ.\n"; error = true; 
        } else if (!address.matches(".* \\d*")){  // should also have other possible formats for people living in apartments for instance
            cause += "Skriv venligst vej og nummer.\n"; error = true;
        } else if (!postnr.matches("\\d{4}")){
            cause += "Skriv venligst et dansk postnummer.\n"; error = true;
        } else if (!phone.matches("\\d{8}")){
            cause += "Skriv venligst et telefonnummer på 8 cifre.\n"; error = true;
        } else if (!mail.matches("\\S*@\\S*\\.\\S*")){
            cause += "Skriv venligst en gyldig email."; error = true;
        }
        if (error){
            throw new IllegalArgumentException(cause);
        }

        this.birthdate = convertToDate(birthdate);
        this.name = name; // we could choose to capitalize first letter in all words, but isn't important
        this.address = address; // same as above
        this.postnr = postnr;
        this.city = city; // same as above
        this.phone = phone;
        this.mail = mail;
        this.active = active;
        this.arrears = getYearlyContingent();
    }
    
    private LocalDate convertToDate(String date){
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6));
        return LocalDate.of(year, month, day);
    }

    public int getAge() { // be private?
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

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
    
    public void payArrears(double amount){
        if (amount < 0 || amount > arrears){
            throw new IllegalArgumentException();
        }
        arrears -= amount;
    }

    public void setActive(boolean b){
        active = b;
    }

    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdate() {
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

//    @Override
//    public String toString() {
//        return "Member{" + "name=" + name + ", address=" + address + ", postnr=" + postnr + ", city=" + city + ", phone=" + phone + ", mail=" + mail + ", active=" + active + ", arrears=" + arrears + '}';
//    }
    
}
