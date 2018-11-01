package brainmatics.manan.com.brainmatics;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import brainmatics.manan.com.brainmatics.util.MentalMathUtil;

public class ProblemAndChallangeType extends AppCompatActivity implements View.OnClickListener{
    int operation = 0; // difficulty=0;
    String operationText = "Not Defined";
    private Button buttonCancel, buttonContinue;
    RadioButton rbTimed, rbPractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Typeface font = Typeface.createFromAsset( getAssets(), MentalMathUtil.FA_FONT );
        setContentView(R.layout.activity_problem_and_challange_type);

        buttonCancel = (Button)findViewById(R.id.button_cancel);
        buttonContinue = (Button)findViewById(R.id.button_ok);
        buttonContinue.setTypeface(font);
        buttonCancel.setTypeface(font);

        buttonCancel.setOnClickListener(this);
        buttonContinue.setOnClickListener(this);

        rbPractice= (RadioButton)findViewById(R.id.rb_practice);
        rbTimed= (RadioButton)findViewById(R.id.rb_timed);

        operation = (int)getIntent().getSerializableExtra(MentalMathUtil.OPERATION);
        setOperationDisplayText();
    }

    private void setOperationDisplayText(){
        TextView displayOperationTxtVw = (TextView)findViewById(R.id.textViewOperation);

        switch (operation){
            case MentalMathUtil.ADDITION:
                displayOperationTxtVw.setText("Addition");
                break;
            case MentalMathUtil.SUBTRACTION:
                displayOperationTxtVw.setText("Subtraction");
                break;
            case MentalMathUtil.MULTIPLICATION:
                displayOperationTxtVw.setText("Multiplication");
                break;
            case MentalMathUtil.DIVISION:
                displayOperationTxtVw.setText("Division");
                break;
            case MentalMathUtil.OOP:
                displayOperationTxtVw.setText("Order of Operations");
                break;
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, QuestionsActivity.class);
        switch (view.getId()){
            case R.id.button_ok:
//                Toast.makeText(ProblemAndChallangeType.this, "Difficulty: " + getDifficulty() + ", Operation: "+ operation, Toast.LENGTH_SHORT).show();

                intent.putExtra(MentalMathUtil.DIFFICULTY,getDifficulty());
                intent.putExtra(MentalMathUtil.OPERATION,operation);
                int type = 1;
                if (rbPractice.isChecked()){
                    type = MentalMathUtil.PRACTICE_MODE;
                }
                intent.putExtra(MentalMathUtil.MODE, type);
                startActivity(intent);
                break;
            case R.id.button_cancel:
                this.finish();
                break;
        }
    }

    private int getDifficulty(){
        int difficulty = 0;
        RadioButton rbEasy, rbMedium, rbHard;

        rbEasy = (RadioButton)findViewById(R.id.rb_easy);
        rbMedium = (RadioButton)findViewById(R.id.rb_medium);
        rbHard= (RadioButton)findViewById(R.id.rb_hard);

        if (rbEasy.isChecked()){
            difficulty=MentalMathUtil.DIFFICULTY_EASY;
        }
        else if(rbMedium.isChecked()){
            difficulty=MentalMathUtil.DIFFICULTY_MEDIUM;
        }
        else if(rbHard.isChecked()){
            difficulty=MentalMathUtil.DIFFICULTY_HARD;
        }
        return difficulty;

    }
}
