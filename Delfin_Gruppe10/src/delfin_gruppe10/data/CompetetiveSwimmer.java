/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.data;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class CompetetiveSwimmer extends Member {

    private TrainingResult bestButterflyResult;
    private TrainingResult bestCrawlResult;
    private TrainingResult bestBackstrokeResult;
    private TrainingResult bestBreaststrokeResult;
    private ArrayList<CompetetiveResult> competetiveResults;

    public CompetetiveSwimmer(String name, String birthdate, String address, String postnr, String city, String phone, String mail) {
        super(name, birthdate, address, postnr, city, phone, mail, true);
    }

    public TrainingResult getBestTrainingResult(Discipline d) {
        switch (d) {
            case BUTTERFLY:
                return bestButterflyResult;
            case CRAWL:
                return bestCrawlResult;
            case BACKSTROKE:
                return bestBackstrokeResult;
            case BREASTSTROKE:
                return bestBreaststrokeResult;
            default:
                throw new AssertionError();
        }
    }

    public ArrayList<CompetetiveResult> getCompetetiveResults() {
        return competetiveResults;
    }
    
    public void setBestTrainingResult(TrainingResult result) {
        Discipline d = result.getDiscipline();
        switch (d) {
            case BUTTERFLY:
                bestButterflyResult = result;
            case CRAWL:
                bestCrawlResult = result;
            case BACKSTROKE:
                bestBackstrokeResult = result;
            case BREASTSTROKE:
                bestBreaststrokeResult = result;
            default:
                throw new AssertionError();
        }
    }
    
    public void addCompetetiveResult(CompetetiveResult result){
        competetiveResults.add(result);
    }

}
