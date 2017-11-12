package com.flikster.Util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

    public static String dateToEpoch(String enteredDate) {
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch + "";
    }

    public static String dateOnlyAccess(String enteredDate) {
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch + "";
    }


    public static long dob(String enteredDate) {
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch;
    }


    public static String ageCal(String enteredDate) {
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch + "";
    }


    public static String epochToDate(long epoch) {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date(epoch));
    }

    public static String fetchGraphDate(long epoch) {
        return new SimpleDateFormat("MMM yy").format(new Date(epoch));
    }


    public static String colepochToDate(long epoch) {
        return new SimpleDateFormat("dd-MMM-yyyy HH:mm").format(new Date(epoch));
    }

    public static String dateTimeToEpoch(String enteredDate) {
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch + "";
    }


    public static long colTime(String enteredDate) {

        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch;
    }


    public static long timeequlality(String enteredDate) {
        enteredDate.replace("AM", "am");
        enteredDate.replace("PM", "pm");
        Log.e("entereddate", enteredDate + "");
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch;
    }

    public static String coldateC(String enteredDate) {
        long epoch = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        try {
            Date date = format.parse(enteredDate);
            System.out.println(date);
            epoch = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return epoch + "";
    }

    public static String currentDate() {
        String updateDate = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat simDf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            updateDate = simDf.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateDate;
    }

    public static double mintsTohoursCovert(String minutes) {
        return Double.parseDouble(minutes) / 60.0;
    }

    public static String mintsToCoverthoursMin(String minutes) {
        double hoursAndMin = Double.parseDouble(minutes) / 60.0;
        String[] strarray = String.format("%.2f", hoursAndMin).split("\\.");
        return String.valueOf(Integer.parseInt(strarray[0]) + " Hrs " + Integer.parseInt(strarray[1]) + " Min").toString();
    }


    public static String currentTime() {
        String currentTime = "";
        Calendar c = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
        try {
            currentTime = simpleDateFormat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentTime;
    }

    public static String serverSentDateChange(String serverDate) {
        Log.e("serverDate", serverDate);
        String changeFormatDate = "";
        try {
            SimpleDateFormat simDf = new SimpleDateFormat("yyyy-MM-dd");
            Date yourDate = simDf.parse(serverDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(yourDate);
            calendar.get(Calendar.YEAR); //Day of the Year :)
            calendar.get(Calendar.DATE); //Day of the day :)
            int orderMonth = calendar.get(Calendar.MONTH) + 1;
            changeFormatDate = calendar.get(Calendar.DATE) + "-" + orderMonth + "-" + calendar.get(Calendar.YEAR);
            Log.e("changedate", changeFormatDate + "");

        } catch (Exception e) {

        }
        return changeFormatDate;
    }


}
