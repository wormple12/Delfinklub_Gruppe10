/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import java.util.Objects;

/**
 *
 * @author HP
 */
public class CompetetiveResult extends TrainingResult {

    private final String competition;
    private final int ranking;
    
    public CompetetiveResult(Discipline discipline, String time, String date, String competition, int ranking) {
        super(discipline, time, date);
        if (competition == null || competition.isEmpty()){
            throw new IllegalArgumentException("Fejl: Angiv venligst stævne.");
        } else if (ranking < 1){
            throw new IllegalArgumentException("Fejl: Angiv venligst en placering over 0.");
        }
        this.competition = competition;
        this.ranking = ranking;
    }

    public String getCompetition() {
        return competition;
    }

    public int getRanking() {
        return ranking;
    }
    
    @Override
    public String toString() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY");
//        String formattedDate = formatter.format(date);
//        formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        String formattedTime = formatter.format(time);
        return "(discipline=" + getDiscipline() + ", time=" + getTime() + ", date=" + getDate() + ", competition=" + competition + ", ranking=" + ranking + ')';
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
        final CompetetiveResult other = (CompetetiveResult) obj;
        if (this.ranking != other.ranking) {
            return false;
        }
        if (!Objects.equals(this.competition, other.competition)) {
            return false;
        }
        return true;
    }
    
    
    
}
