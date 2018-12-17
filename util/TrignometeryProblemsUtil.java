package brainmatics.manan.com.brainmatics.util;

import java.util.HashMap;

/**
 * Created by mohiman on 12/17/2018.
 */

public class TrignometeryProblemsUtil {

    public static final HashMap<String, String> EASY_TRIG_PROBLEMS_MAP = new HashMap<String, String>();
    public static final HashMap<String, String> MEDIUM_TRIG_PROBLEMS_MAP = new HashMap<String, String>();
    public static final HashMap<String, String> HARD_TRIG_PROBLEMS_MAP = new HashMap<String, String>();

    static
    {
        EASY_TRIG_PROBLEMS_MAP.put("sin(0)","0");
        EASY_TRIG_PROBLEMS_MAP.put("sin(30)","1/2");
        EASY_TRIG_PROBLEMS_MAP.put("sin(45)","1/s2");
        EASY_TRIG_PROBLEMS_MAP.put("sin(60)","s3/2");
        EASY_TRIG_PROBLEMS_MAP.put("sin(90)","1");

        EASY_TRIG_PROBLEMS_MAP.put("cos(0)","1");
        EASY_TRIG_PROBLEMS_MAP.put("cos(30)","s3/2");
        EASY_TRIG_PROBLEMS_MAP.put("cos(45)","1/s2");
        EASY_TRIG_PROBLEMS_MAP.put("cos(60)","1/2");
        EASY_TRIG_PROBLEMS_MAP.put("cos(90)","0");

        EASY_TRIG_PROBLEMS_MAP.put("tan(0)","0");
        EASY_TRIG_PROBLEMS_MAP.put("tan(30)","1/s3");
        EASY_TRIG_PROBLEMS_MAP.put("tan(45)","1");
        EASY_TRIG_PROBLEMS_MAP.put("tan(60)","s3");
        EASY_TRIG_PROBLEMS_MAP.put("tan(90)","ND");




    }

}



