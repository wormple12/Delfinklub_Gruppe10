/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delfin_gruppe10.domainlogic;

import delfin_gruppe10.domainlogic.Discipline;
import delfin_gruppe10.domainlogic.TrainingResult;
import delfin_gruppe10.domainlogic.Member;
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
        bestButterflyResult = new TrainingResult(Discipline.BUTTERFLY, "00:00:00", "01-01-2000");
        bestCrawlResult = new TrainingResult(Discipline.CRAWL, "00:00:00", "01-01-2000");
        bestBackstrokeResult = new TrainingResult(Discipline.BACKSTROKE, "00:00:00", "01-01-2000");
        bestBreaststrokeResult = new TrainingResult(Discipline.BREASTSTROKE, "00:00:00", "01-01-2000");
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
                break;
            case CRAWL:
                bestCrawlResult = result;
                break;
            case BACKSTROKE:
                bestBackstrokeResult = result;
                break;
            case BREASTSTROKE:
                bestBreaststrokeResult = result;
                break;
            default:
                throw new AssertionError();
        }
    }
    
    public void addCompetetiveResult(CompetetiveResult result){
        competetiveResults.add(result);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("CompetetiveSwimmer{" + "name=" + getName() + ", bestButterflyResult:" + bestButterflyResult + ", bestCrawlResult:" + bestCrawlResult + ", bestBackstrokeResult:" + bestBackstrokeResult + ", bestBreaststrokeResult:" + bestBreaststrokeResult);
        str.append(str.append(", competetiveResults:"));
        for (CompetetiveResult result : competetiveResults){
            str.append(result+"AND");
        }
        str.delete(str.lastIndexOf("AND"), str.length());
        str.append("}");
        return str.toString();
    }

}
