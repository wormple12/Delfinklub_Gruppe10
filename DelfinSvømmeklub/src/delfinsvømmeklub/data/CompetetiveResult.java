/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfinsvømmeklub.data;

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
    
}
