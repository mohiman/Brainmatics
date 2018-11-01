package brainmatics.manan.com.brainmatics;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import brainmatics.manan.com.brainmatics.util.MentalMathUtil;
import brainmatics.manan.com.brainmatics.util.ResultsInfo;

public class DisplayStatus extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_status);
        Typeface font = Typeface.createFromAsset( getAssets(), MentalMathUtil.FA_FONT );

        Intent i = getIntent();

        TextView txtStatus = (TextView)findViewById(R.id.txtFinalStatus);


        Button btn_home, btn_exit;
        btn_home = (Button)findViewById(R.id.btnsts_home);
        btn_exit=(Button)findViewById(R.id.btnsts_exit);
        btn_home.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        btn_exit.setTypeface(font);
        btn_home.setTypeface(font);

        HashMap<Integer, ArrayList<ResultsInfo>> existingData = null;

        //Read the Data
        try {
            ObjectInput oin = new ObjectInputStream(openFileInput(MentalMathUtil.DATA_FILE_NAME));
            existingData = (HashMap<Integer,ArrayList<ResultsInfo>>) oin .readObject();
        }
        catch(Exception e){
        }


        txtStatus.setText((String)i.getSerializableExtra("status") +"\nSize of existing data "  + (existingData != null ? ""+existingData.size() : " no data found" ));

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnsts_home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnsts_exit:
                finishAffinity();
//                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
        }

    }
}
