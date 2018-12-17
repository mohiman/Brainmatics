package brainmatics.manan.com.brainmatics;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import brainmatics.manan.com.brainmatics.util.MentalMathUtil;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    final Context context = this;
    private CardView cardViewAdd, cardViewMinus, cardViewMultiply, cardViewDivide, cardOop, cardTrig;
    private Button buttonHighScore, buttonExit, buttonSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface font = Typeface.createFromAsset( getAssets(), MentalMathUtil.FA_FONT );


        buttonHighScore = (Button)findViewById(R.id.button_highscore);
        buttonHighScore.setTypeface(font);
        buttonHighScore.setOnClickListener(this);

        buttonExit = (Button)findViewById(R.id.button_exit);
        buttonExit.setTypeface(font);
        buttonExit.setOnClickListener(this);

        buttonSetting = (Button)findViewById(R.id.button_setting);
        buttonSetting.setTypeface(font);
        buttonSetting.setOnClickListener(this);

        cardViewAdd = (CardView)findViewById(R.id.card_add);
        cardViewMinus=(CardView)findViewById(R.id.card_subtract);
        cardViewMultiply=(CardView)findViewById(R.id.card_multiply);
        cardViewDivide=(CardView)findViewById(R.id.card_divide);
        cardOop=(CardView)findViewById(R.id.card_oop);
        cardTrig=(CardView)findViewById(R.id.card_trig);
        cardViewAdd.setOnClickListener(this);
        cardViewMinus.setOnClickListener(this);
        cardViewMultiply.setOnClickListener(this);
        cardViewDivide.setOnClickListener(this);
        cardOop.setOnClickListener(this);
        cardTrig.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, ProblemAndChallangeType.class);
        switch (view.getId()) {
            case R.id.card_add:
                intent.putExtra(MentalMathUtil.OPERATION,MentalMathUtil.ADDITION);
                startActivity(intent);
                break;
            case R.id.card_subtract:
                intent.putExtra(MentalMathUtil.OPERATION,MentalMathUtil.SUBTRACTION);
                startActivity(intent);
                break;
            case R.id.card_multiply:
                intent.putExtra(MentalMathUtil.OPERATION,MentalMathUtil.MULTIPLICATION);
                startActivity(intent);
                break;
            case R.id.card_divide:
                intent.putExtra(MentalMathUtil.OPERATION,MentalMathUtil.DIVISION);
                startActivity(intent);
                break;
            case R.id.card_oop:
                intent.putExtra(MentalMathUtil.OPERATION,MentalMathUtil.OOP);
                startActivity(intent);
                break;
            case R.id.card_trig:
                intent.putExtra(MentalMathUtil.OPERATION,MentalMathUtil.TRIGNOMETRY);
                startActivity(intent);
                break;
            case R.id.button_exit:
                finishAffinity();
                System.exit(0);
            case R.id.button_highscore:
//Nothing here yet
                break;
            case R.id.button_setting:
//Nothing here yet
                break;
        }

    }
}
