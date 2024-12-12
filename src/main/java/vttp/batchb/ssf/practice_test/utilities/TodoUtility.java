package vttp.batchb.ssf.practice_test.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpSession;
import vttp.batchb.ssf.practice_test.models.Login;

public class TodoUtility {

    public static final String LOGIN = "login";

    //casting stored date back to date field in html
    public static String dateToString(Date date) {
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateForHtml = df.format(date);
        
        return dateForHtml;
    }

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
        //System.out.println("session is not null anymore " + sess.toString());

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
    //for html date
    public static Date stringToDateHtml(String string) {
        //date = 2024-12-20
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            Date date = df.parse(string);
            return date;
        } catch (Exception x) {
            System.out.println("html date parsing error");
        }
        return null;
    }

    //for json only
    public static Date stringToDateJson(String string) {
        // date = Sun, 10/15/2023
        DateFormat df = new SimpleDateFormat("EEE, MM/dd/yyyy");
        try {
            Date date = df.parse(string);
            return date;
        } catch (Exception ex) {
            System.out.println("json date parsing error");
        }
        return null;
    }
    
}
