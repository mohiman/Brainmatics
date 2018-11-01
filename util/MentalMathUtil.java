package brainmatics.manan.com.brainmatics.util;

/**
 * Created by mohiman on 10/16/2018.
 */

import android.util.Log;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mohiman on 9/3/2018.
 */

public class MentalMathUtil {


    /**
     * Random number generator, Signature would be modified to pass the diffculty, and min max should
     * be managed internally
     * @return
     */
//    public static int randInt() {
//
//
//        // nextInt is normally exclusive of the top value,
//        // so add 1 to make it inclusive
//        int randomNum = RAND.nextInt((MAX_INT - MIN_INT) + 1) + MIN_INT;
//
//        return randomNum;
//    }

    /**
     * Generates NumbersInfo Class, which has both the numbers, operation, difficulty and the answer
     * @param operation
     * @param difficulty
     * @return
     */
    public static NumbersInfo getNumbersInformation(int operation, int difficulty){
        NumbersInfo numbersInfo = new NumbersInfo();
        numbersInfo.setDifficulty(difficulty);
        numbersInfo.setOperation(operation);
        if (operation == MentalMathUtil.ADDITION){
            numbersInfo = getAdditionNumbersInfo(numbersInfo);
        }
        if (operation == MentalMathUtil.MULTIPLICATION){
            numbersInfo = getMultiplicationNumbersInfo(numbersInfo);
        }
        if (operation == MentalMathUtil.SUBTRACTION){
            numbersInfo = getSubtractionNumbersInfo(numbersInfo);
        }
        if (operation == MentalMathUtil.DIVISION){
            numbersInfo = getDivisionNumbersInfo(numbersInfo);
        }
        if (operation == MentalMathUtil.OOP){
            numbersInfo = getOOPNumbers(numbersInfo);
        }

        return numbersInfo;
    }
    private static String getNextDistinctOperator(ArrayList<String> currentOperatorList) {
        Random rand = new Random();
        String opr =  OPERATORS[rand.nextInt(OPERATORS.length)];

        while(currentOperatorList.contains(opr)){
            opr =  OPERATORS[rand.nextInt(OPERATORS.length)];
        }
        return opr;
    }
    private static NumbersInfo getOOPNumbers(NumbersInfo numbersInfo) {

        StringBuffer oopStr = new StringBuffer();
        String operator = "";
        String space = " ";
        ArrayList<String> operatorsList = new ArrayList<String>();

        if (numbersInfo.getDifficulty() == MentalMathUtil.DIFFICULTY_EASY){

            oopStr.append(MentalMathUtil.randInt(1, MentalMathUtil.MAX_1_DIGIT));
            oopStr.append( space );

            operator=getNextDistinctOperator(operatorsList);

            operatorsList.add(operator);

            oopStr.append( operator);
            oopStr.append( space );

            oopStr.append(MentalMathUtil.randInt(1, MentalMathUtil.MAX_1_DIGIT));
            oopStr.append( space );

            operator=getNextDistinctOperator(operatorsList);

            operatorsList.add(operator);
            oopStr.append( operator);

            oopStr.append(MentalMathUtil.randInt(1, MentalMathUtil.MAX_1_DIGIT));
            numbersInfo.setOopString(oopStr.toString());

        }
        if (numbersInfo.getDifficulty() == MentalMathUtil.DIFFICULTY_MEDIUM){

            oopStr.append(MentalMathUtil.randInt(1, MentalMathUtil.MAX_1_DIGIT));
            oopStr.append( space );

            operator=getNextDistinctOperator(operatorsList);
            operatorsList.add(operator);
            oopStr.append( operator);
            oopStr.append( space );

            oopStr.append(MentalMathUtil.randInt(1, MentalMathUtil.MAX_1_DIGIT));
            oopStr.append( space );

            operator=getNextDistinctOperator(operatorsList);
            operatorsList.add(operator);
            oopStr.append( operator);
            oopStr.append( space );

            oopStr.append(MentalMathUtil.randInt(1, MentalMathUtil.MAX_1_DIGIT));
            numbersInfo.setOopString(oopStr.toString());

            operator=getNextDistinctOperator(operatorsList);
            operatorsList.add(operator);
            oopStr.append( operator);
            oopStr.append( space );

            oopStr.append(MentalMathUtil.randInt(1, MentalMathUtil.MAX_1_DIGIT));
            numbersInfo.setOopString(oopStr.toString());

        }
        if (numbersInfo.getDifficulty() == MentalMathUtil.DIFFICULTY_HARD){

            oopStr.append(MentalMathUtil.randInt(1, MentalMathUtil.MAX_1_DIGIT));
            oopStr.append( space );

            operator=getNextDistinctOperator(operatorsList);
            operatorsList.add(operator);
            oopStr.append( operator);
            oopStr.append( space );

            oopStr.append(MentalMathUtil.randInt(1, MentalMathUtil.MAX_1_DIGIT));
            oopStr.append( space );

            operator=getNextDistinctOperator(operatorsList);
            operatorsList.add(operator);
            oopStr.append( operator);
            oopStr.append( space );

            oopStr.append(MentalMathUtil.randInt(1, MentalMathUtil.MAX_1_DIGIT));
            oopStr.append( space );

            numbersInfo.setOopString(oopStr.toString());

            operator=getNextDistinctOperator(operatorsList);
            operatorsList.add(operator);
            oopStr.append( operator);
            oopStr.append( space );

            oopStr.append(MentalMathUtil.randInt(1, MentalMathUtil.MAX_1_DIGIT));
            oopStr.append( space );
            numbersInfo.setOopString(oopStr.toString());

            operator=getNextDistinctOperator(operatorsList);
            operatorsList.add(operator);
            oopStr.append( operator);
            oopStr.append( space );

            oopStr.append(MentalMathUtil.randInt(1, MentalMathUtil.MAX_1_DIGIT));
            numbersInfo.setOopString(oopStr.toString());

        }


        Expression e = new ExpressionBuilder(numbersInfo.getOopString()).build();

        double result = e.evaluate();
        numbersInfo.setOopResult(result);
        numbersInfo.setAnswer((long)result);

        Log.d("MentalMathUtil", "This is numbersInfo in MentalMathUtil " + numbersInfo.toString());
        return numbersInfo;
    }

    /**
     * logic for division
     * @param numbersInfo
     * @return
     */
    private static NumbersInfo getDivisionNumbersInfo(NumbersInfo numbersInfo) {
        if (numbersInfo.getDifficulty()== MentalMathUtil.DIFFICULTY_EASY){
            numbersInfo.setNum1(randInt(MentalMathUtil.MIN_2_DIGIT,MentalMathUtil.MAX_2_DIGIT));
            numbersInfo.setNum2(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_1_DIGIT));
            while(numbersInfo.getNum1() % numbersInfo.getNum2() != 0  ){
                numbersInfo.setNum1(randInt(MentalMathUtil.MIN_2_DIGIT,MentalMathUtil.MAX_2_DIGIT));
                numbersInfo.setNum2(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_1_DIGIT));
            }
        }
        else if (numbersInfo.getDifficulty()== MentalMathUtil.DIFFICULTY_MEDIUM){
            numbersInfo.setNum1(randInt(MentalMathUtil.MIN_3_DIGIT,MentalMathUtil.MAX_3_DIGIT));
            numbersInfo.setNum2(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_2_DIGIT));
            while(numbersInfo.getNum1() % numbersInfo.getNum2() != 0  ) {
                numbersInfo.setNum1(randInt(MentalMathUtil.MIN_3_DIGIT,MentalMathUtil.MAX_3_DIGIT));
                numbersInfo.setNum2(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_2_DIGIT));
            }
        }
        else if (numbersInfo.getDifficulty() == MentalMathUtil.DIFFICULTY_HARD){
            numbersInfo.setNum1(randInt(MentalMathUtil.MIN_4_DIGIT,MentalMathUtil.MAX_4_DIGIT));
            numbersInfo.setNum2(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_2_DIGIT));
            while(numbersInfo.getNum1() % numbersInfo.getNum2() != 0  ) {
                numbersInfo.setNum1(randInt(MentalMathUtil.MIN_4_DIGIT,MentalMathUtil.MAX_4_DIGIT));
                numbersInfo.setNum2(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_2_DIGIT));
            }
        }
        numbersInfo.setAnswer( numbersInfo.getNum1()/numbersInfo.getNum2());
        return numbersInfo;
    }

    /**
     * Logic for Subtraction
     * @param numbersInfo
     * @return
     */
    private static NumbersInfo getSubtractionNumbersInfo(NumbersInfo numbersInfo) {
        if (numbersInfo.getDifficulty() == MentalMathUtil.DIFFICULTY_EASY){
            numbersInfo.setNum1(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_1_DIGIT));
            numbersInfo.setNum2(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_1_DIGIT));
            while(numbersInfo.getNum2() > numbersInfo.getNum1()){
                numbersInfo.setNum2(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_1_DIGIT));
            }
        }
        if (numbersInfo.getDifficulty()== MentalMathUtil.DIFFICULTY_MEDIUM){
            numbersInfo.setNum1(randInt(MentalMathUtil.MIN_2_DIGIT,MentalMathUtil.MAX_2_DIGIT));
            numbersInfo.setNum2(randInt(MentalMathUtil.MIN_2_DIGIT,MentalMathUtil.MAX_2_DIGIT));
            while(numbersInfo.getNum2() > numbersInfo.getNum1()){
                numbersInfo.setNum2(randInt(MentalMathUtil.MIN_2_DIGIT,MentalMathUtil.MAX_2_DIGIT));
            }
        }
        if (numbersInfo.getDifficulty() == MentalMathUtil.DIFFICULTY_HARD){
            numbersInfo.setNum1(randInt(MentalMathUtil.MIN_3_DIGIT,MentalMathUtil.MAX_3_DIGIT));
            numbersInfo.setNum2(randInt(MentalMathUtil.MIN_3_DIGIT,MentalMathUtil.MAX_3_DIGIT));
            while(numbersInfo.getNum2() > numbersInfo.getNum1()){
                numbersInfo.setNum2(randInt(MentalMathUtil.MIN_3_DIGIT,MentalMathUtil.MAX_3_DIGIT));
            }
        }

        numbersInfo.setAnswer( numbersInfo.getNum1()-numbersInfo.getNum2());

        return  numbersInfo;
    }

    /**
     * Breaking the logic for multiplication
     * @param numbersInfo
     * @return
     */
    private static NumbersInfo getMultiplicationNumbersInfo(NumbersInfo numbersInfo) {
        if (numbersInfo.getDifficulty()== MentalMathUtil.DIFFICULTY_EASY){
            numbersInfo.setNum1(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_1_DIGIT));
            numbersInfo.setNum2(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_1_DIGIT));
        }
        if (numbersInfo.getDifficulty()== MentalMathUtil.DIFFICULTY_MEDIUM){
            numbersInfo.setNum1(randInt(MentalMathUtil.MIN_2_DIGIT,MentalMathUtil.MAX_2_DIGIT));
            numbersInfo.setNum2(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_1_DIGIT));
        }
        if (numbersInfo.getDifficulty() == MentalMathUtil.DIFFICULTY_HARD){
            numbersInfo.setNum1(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_2_DIGIT));
            numbersInfo.setNum2(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_2_DIGIT));
        }
        numbersInfo.setAnswer( numbersInfo.getNum1()*numbersInfo.getNum2());
        return numbersInfo;
    }

    /**
     * breaking the logic for addition numbers flow
     * @param numbersInfo
     * @return
     */
    private static NumbersInfo getAdditionNumbersInfo(NumbersInfo numbersInfo) {
        if (numbersInfo.getDifficulty()== MentalMathUtil.DIFFICULTY_EASY){
            numbersInfo.setNum1(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_1_DIGIT));
            numbersInfo.setNum2(randInt(MentalMathUtil.MIN_1_DIGIT,MentalMathUtil.MAX_1_DIGIT));
        }
        if (numbersInfo.getDifficulty()== MentalMathUtil.DIFFICULTY_MEDIUM){
            numbersInfo.setNum1(randInt(MentalMathUtil.MIN_2_DIGIT,MentalMathUtil.MAX_2_DIGIT));
            numbersInfo.setNum2(randInt(MentalMathUtil.MIN_2_DIGIT,MentalMathUtil.MAX_2_DIGIT));
        }
        if (numbersInfo.getDifficulty() == MentalMathUtil.DIFFICULTY_HARD){
            numbersInfo.setNum1(randInt(MentalMathUtil.MIN_3_DIGIT,MentalMathUtil.MAX_3_DIGIT));
            numbersInfo.setNum2(randInt(MentalMathUtil.MIN_3_DIGIT,MentalMathUtil.MAX_3_DIGIT));
        }
        numbersInfo.setAnswer( numbersInfo.getNum1()+numbersInfo.getNum2());
        return numbersInfo;
    }

//
//    public static int randInt(int min) {
//
//
//        // nextInt is normally exclusive of the top value,
//        // so add 1 to make it inclusive
//        int randomNum = RAND.nextInt((MAX_INT - min) + 1) + min;
//
//        return randomNum;
//    }

    /**
     * Core logic of generating random Numbers.
     * @param min
     * @param max
     * @return
     */
    public static int randInt(int min,int max) {


        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = RAND.nextInt((max - min) + 1) + min;

        return randomNum;
    }


    private static final int MAX_INT = 99;
    private static final int MIN_INT = 1;
    private static final Random RAND =new Random();


    public static final int ADDITION = 1;
    public static final int SUBTRACTION = 2;
    public static final int MULTIPLICATION=3;
    public static final int DIVISION=4;
    public static final int OOP=5;
    public static final String OPERATION = "Operation" ;
    public static final String DIFFICULTY = "Difficulty";
    public static  final int DIFFICULTY_EASY = 1;
    public static  final int DIFFICULTY_MEDIUM = 2;
    public static  final int DIFFICULTY_HARD = 3;
    private static final int MAX_1_DIGIT = 9;
    private static final int MIN_1_DIGIT = 2;

    private static final int MIN_2_DIGIT = 10;
    private static final int MAX_2_DIGIT = 99;

    private static final int MIN_3_DIGIT = 100;
    private static final int MAX_3_DIGIT = 999;

    private static final int MIN_4_DIGIT = 1000;
    private static final int MAX_4_DIGIT = 9999;
    public static final String FA_FONT = "fa-solid-900.ttf";
    public static final String DATA_FILE_NAME = "results.obj";
    public static final int MAX_CHALLANGE_QUESTIONS = 10;

    public static final int TIMED_CHALLANGE = 1;
    public static final int PRACTICE_MODE = 2;
    public static final String MODE = "AppMode";
    private static final String[] OPERATORS = new String[]{"+","-","/","*"};

}
