package vttp.batchb.ssf.practice_test.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoUtility {

    //update time everytime an entry has been manipulated
    

    //date -> long
    //to ephochmilliseconds
    public static Long dateToEphoch(Date date) {
        return date.getTime();
    }

    //obj -> date
    public static Date ephocToDate(Object ephoch) {
        Long toDate = ((Long)ephoch);
        Date date = new Date(toDate);
        return date;
    }

    public static Date stringToDate(Date date, String string) {
        // date = Sun Mar 10 00:00:00 GMT+08:00 2024 (in seconds)
        DateFormat df = new SimpleDateFormat("EEE, mm/DD/yyyy");

        try {
            date = df.parse(string);
        } catch (ParseException e) {
            //e.printStackTrace();
        }        
        return date;
    }
    
}
