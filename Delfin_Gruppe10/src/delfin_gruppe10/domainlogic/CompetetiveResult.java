/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import delfin_gruppe10.domainlogic.Discipline;
import delfin_gruppe10.domainlogic.TrainingResult;

/**
 *
 * @author HP
 */
public class CompetetiveResult extends TrainingResult {

    private final String competition;
    private final int ranking;
    
    public CompetetiveResult(Discipline discipline, String time, String date, String competition, int ranking) {
        super(discipline, time, date);
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
    
}
