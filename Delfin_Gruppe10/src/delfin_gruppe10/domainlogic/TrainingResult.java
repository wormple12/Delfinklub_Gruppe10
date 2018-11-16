/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author HP
 */
public class TrainingResult {

    private final Discipline discipline;
    private final LocalTime time;
    private final LocalDate date;

    public TrainingResult(Discipline discipline, String time, String date) { // time should be formatted like this: String str = String.format("%d:%02d", minutes, seconds);
        if (date == null || time == null || discipline == null
                || !date.matches("\\d{2}-\\d{2}-\\d{4}") || !time.matches("\\d{2}:\\d{2}:\\d{2}")) // should have other possible formats?
        {
            throw new IllegalArgumentException();
        }
        this.discipline = discipline;

        this.time = LocalTime.parse(time);

        int day = Integer.parseInt(date.substring(0, 2)); // these lines convert birthdate from String to LocalDate
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6));
        this.date = LocalDate.of(year, month, day);
    }
    
    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

}
