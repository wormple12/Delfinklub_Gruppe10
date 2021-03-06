
package delfin_gruppe10.domainlogic;

import java.util.Objects;

/**
 * 
 * 
 * @author Simon Asholt Norup, Lukas Bjørnvad
 * @version 1.00, 22 Nov 2018
 */
public class TrainingResult {

    private final Discipline discipline;
    private final String time;
    private final String date;

    public TrainingResult(Discipline discipline, String time, String date) { // time should be formatted like this: String str = String.format("%d:%02d", minutes, seconds);
        if (date == null || time == null || discipline == null){
            throw new IllegalArgumentException("Fejl: Et felt er tomt.");
        } else if (!date.matches("\\d{2}-\\d{2}-\\d{4}")){ // should have other possible formats?
            throw new IllegalArgumentException("Fejl: Dato skal formateres DD-MM-ÅÅÅÅ.");
        } else if (!time.matches("\\d{2}:\\d{2}:\\d{2}")){ // should have other possible formats?
            throw new IllegalArgumentException("Fejl: Tid skal formateres MM:SS:mm.");
        } 
        this.discipline = discipline;
        this.time = time;
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    @Override
    public String toString() {
        return "(time=" + time + ", date=" + date + ')';
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
        final TrainingResult other = (TrainingResult) obj;
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (this.discipline != other.discipline) {
            return false;
        }
        return true;
    }

}
