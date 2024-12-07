package vttp.batchb.ssf.practice_test.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpSession;
import vttp.batchb.ssf.practice_test.models.Login;

public class TodoUtility {

    public static final String LOGIN = "login";

    //get sess
    public static Login getLoginInfo(HttpSession sess) {
         //get session
       Login loginInfo = (Login)sess.getAttribute(LOGIN);
       // Initialize the session if it is a new session
       if (null == loginInfo) {

           loginInfo = new Login();
           System.out.println("check loginInfo Obj because null "+ loginInfo);
           sess.setAttribute(LOGIN, loginInfo);
       }
        System.out.println("session is not null anymore " + sess.toString());

        return loginInfo;
    }
    
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
