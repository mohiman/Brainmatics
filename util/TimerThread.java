package brainmatics.manan.com.brainmatics.util;

import android.widget.TextView;

/**
 * Created by mohiman on 9/4/2018.
 */

public class TimerThread extends Thread {
    private int seconds = 00;
    private int minutes = 00;
    private StringBuffer display;
    private TextView timerTxt;
    public TimerThread(TextView textview){
        this.timerTxt = textview;
    }
    @Override
    public void run() {
        try{
            Thread.sleep(1000);
            if (minutes < 9)
                display.append("0");

            display.append(minutes);
            display.append(":");
            if (seconds < 9)
                display.append("0");
            display.append(seconds);
            this.timerTxt.setText(display.toString());
            seconds ++ ;

            if(seconds == 60)
            {
                seconds=00;
                minutes++;
            }
        }
        catch (InterruptedException ie){
            ie.printStackTrace();
        }
    }
}
