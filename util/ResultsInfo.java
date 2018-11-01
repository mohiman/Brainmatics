package brainmatics.manan.com.brainmatics.util;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by mohiman on 10/24/2018.
 */

public class ResultsInfo implements Serializable, Comparable<ResultsInfo >{
    public ResultsInfo(int operation, int difficulty, int noOfCorrectAnswers, int totalQuestions) {
        this.operation = operation;
        this.difficulty = difficulty;
        this.noOfCorrectAnswers = noOfCorrectAnswers;
        this.totalQuestions = totalQuestions;
    }

    private int operation;
    private int difficulty;
    private int noOfCorrectAnswers;
    private int totalQuestions;

    @Override
    public String toString() {
        return "ResultsInfo{" +
                "operation=" + operation +
                ", difficulty=" + difficulty +
                ", noOfCorrectAnswers=" + noOfCorrectAnswers +
                ", totalQuestions=" + totalQuestions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultsInfo that = (ResultsInfo) o;

        if (operation != that.operation) return false;
        if (difficulty != that.difficulty) return false;
        if (noOfCorrectAnswers != that.noOfCorrectAnswers) return false;
        return totalQuestions == that.totalQuestions;
    }

    @Override
    public int hashCode() {
        int result = operation;
        result = 31 * result + difficulty;
        result = 31 * result + noOfCorrectAnswers;
        result = 31 * result + totalQuestions;
        return result;
    }

    public int getOperation() {

        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    @Override
    public int compareTo(@NonNull ResultsInfo resultsInfo) {
        return 0;
    }
}
