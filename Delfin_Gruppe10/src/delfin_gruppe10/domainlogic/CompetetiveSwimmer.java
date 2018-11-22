
package delfin_gruppe10.domainlogic;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 
 * 
 * @author Simon Asholt Norup, Lukas Bjørnvad
 * @version 1.00, 22 Nov 2018
 */
public class CompetetiveSwimmer extends Member {

    private TrainingResult bestButterflyResult;
    private TrainingResult bestCrawlResult;
    private TrainingResult bestBackstrokeResult;
    private TrainingResult bestBreaststrokeResult;
    private ArrayList<CompetetiveResult> competetiveResults;

    public CompetetiveSwimmer(String name, String birthdate, String address, String postnr, String city, String phone, String mail, boolean active) {
        super(name, birthdate, address, postnr, city, phone, mail, active);
        bestButterflyResult = new TrainingResult(Discipline.BUTTERFLY, "59:59:00", "01-01-2000");
        bestCrawlResult = new TrainingResult(Discipline.CRAWL, "59:59:00", "01-01-2000");
        bestBackstrokeResult = new TrainingResult(Discipline.BACKSTROKE, "59:59:00", "01-01-2000");
        bestBreaststrokeResult = new TrainingResult(Discipline.BREASTSTROKE, "59:59:00", "01-01-2000");
        competetiveResults = new ArrayList<>();
    }

    /**
     * Gives the best TrainingResult object in a specific discipline for a CompetetiveSwimmer object
     * 
     * @param d is an enum and the specified discipline 
     * @return the best TrainingResult object
     */
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
    
    /**
     * Manually sets the best training result for a CompetetiveSwimmer object
     * 
     * @param result is a TrainingResult object
     */
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
        str.append(", competetiveResults:");
        for (CompetetiveResult result : competetiveResults){
            str.append(result+"AND");
        }
        if (competetiveResults.isEmpty()){
            str.append("null");
        } else {
            str.delete(str.lastIndexOf("AND"), str.length());
        }
        str.append("}");
        return str.toString();
    }
    
    /**
     * Makes a deep copy of a CompetetiveSwimmer object that copies all values of fields and all its results 
     * 
     * @return a CompetetiveSwimmer object
     */
    public CompetetiveSwimmer copy() {
        CompetetiveSwimmer swimmer = new CompetetiveSwimmer(getName(), getBirthdate(), getAddress(), getPostnr(), getCity(), getPhone(), getMail(), isActive());
        TrainingResult originalTR = getBestTrainingResult(Discipline.BUTTERFLY);
        TrainingResult newTR = new TrainingResult(Discipline.BUTTERFLY, originalTR.getTime(), originalTR.getDate());
        swimmer.setBestTrainingResult(newTR);
        originalTR = getBestTrainingResult(Discipline.CRAWL);
        newTR = new TrainingResult(Discipline.CRAWL, originalTR.getTime(), originalTR.getDate());
        swimmer.setBestTrainingResult(newTR);
        originalTR = getBestTrainingResult(Discipline.BACKSTROKE);
        newTR = new TrainingResult(Discipline.BACKSTROKE, originalTR.getTime(), originalTR.getDate());
        swimmer.setBestTrainingResult(newTR);
        originalTR = getBestTrainingResult(Discipline.BREASTSTROKE);
        newTR = new TrainingResult(Discipline.BREASTSTROKE, originalTR.getTime(), originalTR.getDate());
        swimmer.setBestTrainingResult(newTR);
        for (CompetetiveResult originalCR : competetiveResults) {
            CompetetiveResult newCR = new CompetetiveResult(originalCR.getDiscipline(), originalCR.getTime(), originalCR.getDate(), originalCR.getCompetition(), originalCR.getRanking());
            swimmer.addCompetetiveResult(newCR);
        }
        return swimmer;
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
        final CompetetiveSwimmer other = (CompetetiveSwimmer) obj;
        if (!Objects.equals(this.bestButterflyResult, other.bestButterflyResult)) {
            return false;
        }
        if (!Objects.equals(this.bestCrawlResult, other.bestCrawlResult)) {
            return false;
        }
        if (!Objects.equals(this.bestBackstrokeResult, other.bestBackstrokeResult)) {
            return false;
        }
        if (!Objects.equals(this.bestBreaststrokeResult, other.bestBreaststrokeResult)) {
            return false;
        }
        if (!Objects.equals(this.competetiveResults, other.competetiveResults)) {
            return false;
        }
        return true;
    }
    
    
}
