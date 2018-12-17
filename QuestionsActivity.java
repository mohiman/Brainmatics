package brainmatics.manan.com.brainmatics;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import brainmatics.manan.com.brainmatics.util.MentalMathUtil;
import brainmatics.manan.com.brainmatics.util.NumbersInfo;
import brainmatics.manan.com.brainmatics.util.ResultsInfo;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener{
//    private TextView txtViewFinalStatus=null;
    private EditText value = null;
    private int programsAnswer = 0;
    //    private ImageView imgCorrect, imgWrong;
    private ImageView imgQuestionStat;

    private int currentQuestion = 0;
//    private static final int TOTAL_QUESTIONS = 10;
    private TextView tv;
    private int seconds = 00;
    private int minutes = 00;
    private StringBuffer display;
    private Timer t = new Timer();
    private int pass=0,fail=0;
    int operation=0;
    int mode = 1;
    private int difficulty=0;

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnClear, btnEnter, btnExit, btnHome, btnSqRoot, btnFraction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        value = findViewById(R.id.editTextValue);
        value.setEnabled(false);
        Typeface font = Typeface.createFromAsset( getAssets(), MentalMathUtil.FA_FONT );

        imgQuestionStat = findViewById(R.id.imageViewQuesStatus);
        tv = (TextView)findViewById(R.id.textViewTimer);
        getNextSetOfNumbersBasedOnOperation();
        ((LinearLayout)findViewById(R.id.linearLayoutMain)).setVisibility(View.VISIBLE);
        Intent i = getIntent();

        operation = (int)i.getSerializableExtra(MentalMathUtil.OPERATION);
        difficulty = (int)i.getSerializableExtra(MentalMathUtil.DIFFICULTY);
        mode = (int)i.getSerializableExtra(MentalMathUtil.MODE);

        updateTitleAndOperation(operation,mode);
        tv.setText("");
        if (mode == 1) {
            startTimer(); //Starts the timer
        }
        btn0 = (Button)findViewById(R.id.btn_0);
        btn1 = (Button)findViewById(R.id.btn_1);
        btn2 = (Button)findViewById(R.id.btn_2);
        btn3 = (Button)findViewById(R.id.btn_3);
        btn4 = (Button)findViewById(R.id.btn_4);
        btn5 = (Button)findViewById(R.id.btn_5);
        btn6 = (Button)findViewById(R.id.btn_6);
        btn7 = (Button)findViewById(R.id.btn_7);
        btn8 = (Button)findViewById(R.id.btn_8);
        btn9 = (Button)findViewById(R.id.btn_9);
        btnClear = (Button)findViewById(R.id.btn_clear);
        btnEnter = (Button)findViewById(R.id.btn_Enter);
        btnHome = (Button)findViewById(R.id.btnQuestionsHome);
        btnExit = (Button)findViewById(R.id.btnQuestionsClose);
        btnSqRoot=(Button)findViewById(R.id.btn_sqroot);
        btnFraction=(Button)findViewById(R.id.btn_fraction);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnEnter.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        btnSqRoot.setOnClickListener(this);
        btnFraction.setOnClickListener(this);
        btnHome.setTypeface(font);
        btnExit.setTypeface(font);
        btnSqRoot.setTypeface(font);
        btnFraction.setTypeface(font);

    }

    /**
     * Updates the image to be displayed based on the operation Selected
     * @param operation
     */
    private void updateTitleAndOperation(int operation, int mode){
        TextView displayOperationTxtVw = (TextView)findViewById(R.id.textGrid);
        ImageView imageViewOperation = (ImageView)findViewById(R.id.imageViewOperation);
        getNextSetOfNumbersBasedOnOperation( );
        switch(operation){
            case MentalMathUtil.ADDITION:
                displayOperationTxtVw.setText("Addition");
                imageViewOperation.setImageResource(R.drawable.newaddition);
                break;
            case MentalMathUtil.SUBTRACTION:
                displayOperationTxtVw.setText("Subtraction");
                imageViewOperation.setImageResource(R.drawable.newminus);
                break;
            case MentalMathUtil.MULTIPLICATION:
                displayOperationTxtVw.setText("Multiply");
                imageViewOperation.setImageResource(R.drawable.newmultiply);
                break;
            case MentalMathUtil.DIVISION:
                displayOperationTxtVw.setText("Divide");
                imageViewOperation.setImageResource(R.drawable.newdivide);
                break;
            case MentalMathUtil.OOP:
                displayOperationTxtVw.setText("Order of Operations");
                imageViewOperation.setVisibility(View.INVISIBLE);  //setImageResource(R.drawable.newdivide);
                break;
            case MentalMathUtil.TRIGNOMETRY:
                displayOperationTxtVw.setText("Trignometery");
                imageViewOperation.setVisibility(View.INVISIBLE);  //setImageResource(R.drawable.newdivide);
                break;
            default:
                displayOperationTxtVw.setText("Not Found");
                break;

        }
        if (mode == 2){
            displayOperationTxtVw.setText(displayOperationTxtVw.getText() + " (Practice)");
        }
    }

    /**
     * Dies three things
     * 1. checks the answer
     * 2. Displays Toast for correct/incorrect and also displays the icon for correct incorrect. The icon disappears after 1 second
     * 3. Gets next set of values for the questions, If this is a timed test then it stops after 10 questions
     * @param editTextValue
     */
    private void updateQuestionsAndOtherText(String editTextValue){
        AutoHideImage autoHideImage = null;

        int answer = 0;
        try {
            answer = Integer.parseInt(editTextValue);
        } catch (NumberFormatException nfe) {
            answer = 0;
        }

        if (answer != programsAnswer){
            Toast.makeText(QuestionsActivity.this, "WRONG", Toast.LENGTH_SHORT).show();
            imgQuestionStat.setImageResource(R.drawable.x_mark);
            fail++;

        }
        else{
            pass++;
            Toast.makeText(QuestionsActivity.this, "Correct", Toast.LENGTH_SHORT).show();
            imgQuestionStat.setImageResource(R.drawable.check_mark);
        }


        if (mode == 2 || currentQuestion <= MentalMathUtil.MAX_CHALLANGE_QUESTIONS) {
            imgQuestionStat.setVisibility(View.VISIBLE);
            /**
             * This code crashes in API 26
             */
            Handler mHandler=new Handler();
            autoHideImage = new AutoHideImage(imgQuestionStat);

            mHandler.postDelayed(autoHideImage,1000);
            value.setText("");
            getNextSetOfNumbersBasedOnOperation();
        }
        else
        {
            String status = "Good Job!!!\nCorrect: " + pass + "\nIncorrect: " + fail;
            Intent finalStatus = new Intent(this, DisplayStatus.class);
            finalStatus.putExtra("status",status);

            /**
             * Add Code here to Read Write the Results!!!
             */
            writeResults(operation, difficulty, pass, MentalMathUtil.MAX_CHALLANGE_QUESTIONS);
            startActivity(finalStatus);
            t.cancel();
        }

    }

    /**
     * Write the results to local storage, before doing that read the existing data and update it with these results
     * @param operation
     * @param difficulty
     * @param pass
     * @param totalQuestions
     */
    private void writeResults(int operation, int difficulty, int pass, int totalQuestions) {
        ResultsInfo resultsInfo = new ResultsInfo(operation,difficulty,pass,totalQuestions);

        HashMap<Integer, ArrayList<ResultsInfo>> existingData = null;

        //Read the Data
        try {
            ObjectInput oin = new ObjectInputStream(openFileInput(MentalMathUtil.DATA_FILE_NAME));
            existingData = (HashMap<Integer,ArrayList<ResultsInfo>>) oin .readObject();
        }
        catch(Exception e){
        }

        if (existingData == null){
            existingData = new HashMap<Integer,ArrayList<ResultsInfo>> ();
        }
        else {

            ArrayList<ResultsInfo> operationDataList = existingData.get(operation);

            if (operationDataList == null) {
                operationDataList = new ArrayList<ResultsInfo>();
            }

            operationDataList.add(resultsInfo);

            existingData.put(operation, operationDataList);

        }

        Toast.makeText(QuestionsActivity.this, "Writing this data + " + resultsInfo.toString() + ", Size of Hashmap " + existingData.size(), Toast.LENGTH_SHORT).show();


        //Write the data
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream (getApplicationContext().openFileOutput(MentalMathUtil.DATA_FILE_NAME, Context.MODE_PRIVATE));
            outputStream.writeObject(existingData);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Standard Timer task implementation to display clock on top right corner of the app
     */
    private void startTimer(){
        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        seconds++;
                        if (seconds == 60) {
                            seconds = 0;
                            minutes++;
                        }
                        display = new StringBuffer();
                        if(minutes <= 0) display.append("0");
                        display.append(minutes);
                        display.append(":");
                        if (seconds<=9) display.append("0");
                        display.append(seconds);
                        tv.setText(display);
                    }
                });
            }
        }, 0, 1000);
    }

    /**
     * Gets the next set of numbers
     */
    private void getNextSetOfNumbersBasedOnOperation(){
        NumbersInfo numbersInfo = MentalMathUtil.getNumbersInformation(operation, difficulty);


        TextView txtViewnum1 = (TextView)findViewById(R.id.textViewNum1);
        TextView txtViewnum2 = (TextView)findViewById(R.id.textViewNum2);

        programsAnswer= (int)numbersInfo.getAnswer();
        if(numbersInfo.getOperation()== MentalMathUtil.TRIGNOMETRY){
            //Trignometry Code
            txtViewnum1.setText("Calculate : " );
            txtViewnum2.setText("Sin(30)");
        }
        else if (numbersInfo.getOperation() == MentalMathUtil.OOP){

            Log.i(getClass().getName(), "First Data " + numbersInfo.toString() + "(numbersInfo.getOopResult() % 1 == 0) == " + ((numbersInfo.getOopResult() % 1)) );
            while((numbersInfo.getOopResult() % 1 != 0) ||  (numbersInfo.getOopResult() < 0)  ){
                numbersInfo = MentalMathUtil.getNumbersInformation(operation, difficulty);
                Log.i(getClass().getName(), "Inside the while Loop " + numbersInfo.toString() + "(numbersInfo.getOopResult() % 1 == 0) == " + ((numbersInfo.getOopResult() % 1)) );
                programsAnswer= (int)numbersInfo.getAnswer();
            }
            Log.i(getClass().getName(), "Final NumbersInfo outside the while Loop" + numbersInfo.toString());


            txtViewnum1.setText(numbersInfo.getOopString() );
            txtViewnum2.setText(null);
            txtViewnum2.setVisibility(View.GONE);
            ((LinearLayout)findViewById(R.id.LineyarLayout3)).setVisibility(View.GONE);
//            value.setText(""+numbersInfo.getAnswer()); //Uncomment this line to start printing answer...
        }
        else {
            txtViewnum1.setText(""+ numbersInfo.getNum1());
            txtViewnum2.setText(""+ + numbersInfo.getNum2());

        }

        if (mode == 1){
            ((TextView)findViewById(R.id.textViewQuestion)).setText("Q "+ (currentQuestion++) +" of " + MentalMathUtil.MAX_CHALLANGE_QUESTIONS);
        }
        else{
            ((TextView)findViewById(R.id.textViewQuestion)).setText("Q "+ (currentQuestion++) );
        }

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_Enter:
                updateQuestionsAndOtherText(value.getText().toString());
                break;
            case R.id.btn_clear:
                value.setText("");
                break;
            case R.id.btnQuestionsClose:
                finishAffinity();
                System.exit(0);
            case R.id.btnQuestionsHome:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                updateEditText((Button)view);
                break;
        }

    }
    private void updateEditText(Button digits) {

        value.setText(value.getText()==null ? digits.getText() :  value.getText().toString() + digits.getText());
    }
}

/**
 * Separate Thread implementation to hide the imageView after 1 second
 */
class AutoHideImage extends Thread
{
    private ImageView img;
    public AutoHideImage(ImageView img){
        this.img = img;
    }
    @Override
    public void run() {
//        System.out.println("I am in the thread");
//        try{
//            Thread.sleep(1000);
            img.setVisibility(View.INVISIBLE);
//        }
//        catch (InterruptedException ue){
//            ue.printStackTrace();
//        }
    }
}
