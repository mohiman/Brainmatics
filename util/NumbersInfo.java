package brainmatics.manan.com.brainmatics.util;

/**
 * Created by mohiman on 9/18/2018.
 */

public class NumbersInfo {

    private long num1;
    private long num2;
    private long answer;
    private int operation;
    private int difficulty;
    private String oopString;
    private double oopResult;

    public double getOopResult() {
        return oopResult;
    }

    public void setOopResult(double oopResult) {
        this.oopResult = oopResult;
    }

    @Override
    public String toString() {
        return "NumbersInfo{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                ", answer=" + answer +
                ", operation=" + operation +
                ", difficulty=" + difficulty +
                ", oopString='" + oopString + '\'' +
                ", oopResult=" + oopResult +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumbersInfo that = (NumbersInfo) o;

        if (num1 != that.num1) return false;
        if (num2 != that.num2) return false;
        if (answer != that.answer) return false;
        if (operation != that.operation) return false;
        if (difficulty != that.difficulty) return false;
        if (Double.compare(that.oopResult, oopResult) != 0) return false;
        return oopString != null ? oopString.equals(that.oopString) : that.oopString == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (num1 ^ (num1 >>> 32));
        result = 31 * result + (int) (num2 ^ (num2 >>> 32));
        result = 31 * result + (int) (answer ^ (answer >>> 32));
        result = 31 * result + operation;
        result = 31 * result + difficulty;
        result = 31 * result + (oopString != null ? oopString.hashCode() : 0);
        temp = Double.doubleToLongBits(oopResult);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String getOopString() {

        return oopString;
    }

    public void setOopString(String oopString) {
        this.oopString = oopString;
    }

    public long getNum1() {
        return num1;
    }

    public void setNum1(long num1) {
        this.num1 = num1;
    }

    public long getNum2() {
        return num2;
    }

    public void setNum2(long num2) {
        this.num2 = num2;
    }

    public long getAnswer() {
        return answer;
    }

    public void setAnswer(long answer) {
        this.answer = answer;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
