/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.data;

import java.time.LocalDate;
import java.time.Period;

public class Member {

    private final String name;
    private final LocalDate birthdate;
    private final String address;
    private final String postnr;
    private final String city;
    private final String phone;
    private final String mail;
    private boolean active;

    public Member(String name, String birthdate, String address, String postnr, String city, String phone, String mail, boolean active) {
        if (name == null || birthdate == null || address == null || postnr == null || city == null || phone == null || mail == null
                || !name.matches(".* .*") || !birthdate.matches("\\d{2}-\\d{2}-\\d{4}") || !address.matches(".* \\d*") // birthdate should have other possible formats
                || !postnr.matches("\\d{4}") || !phone.matches("\\d{8}") || !mail.matches("\\S*@\\S*\\.\\S*")) {

            throw new IllegalArgumentException();
        }

        int day = Integer.parseInt(birthdate.substring(0, 2)); // these lines convert birthdate from String to LocalDate
        int month = Integer.parseInt(birthdate.substring(3, 5));
        int year = Integer.parseInt(birthdate.substring(6));
        LocalDate date = LocalDate.of(year, month, day);
        this.birthdate = date;

        this.name = name; // we could choose to capitalize first letter in all words, but isn't important
        this.address = address; // same as above
        this.postnr = postnr;
        this.city = city; // same as above
        this.phone = phone;
        this.mail = mail;
        this.active = active;
    }

    public int getAge() {
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

}
