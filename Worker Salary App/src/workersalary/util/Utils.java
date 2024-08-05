package workersalary.util;

import java.sql.Date;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

public class Utils {

    public static String dateToString(Date date) {
        return String.format("%d/%d/%d",
                date.getDate(), date.getMonth() + 1, date.getYear() + 1900);
    }

    public static String doubleToString(double num) {
        return String.format("%.2f", num);
    }

    public static String timeToTimeToString(Timestamp startTime, Timestamp endTime) {
        return String.format("%d:%02d-%d:%02d", startTime.getHours(), startTime.getMinutes(),
                endTime.getHours(), endTime.getMinutes());
    }

    public static Date getCurDate() {
        return new java.sql.Date(System.currentTimeMillis());
    }

    public static double checkNumDouble(String text, String title, double minNum) {
        double value = minNum;
        if (text == null || text.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô " + title + " không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return value;
        }
        double result = -1;
        try {
            result = Double.parseDouble(text);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ô " + title + " chỉ có thể nhập số!", "Warning", JOptionPane.WARNING_MESSAGE);
            return value;
        }
        if (result < minNum) {
            value = minNum;
        } else {
            value = result;
        }
        return value;
    }

    public static int checkNumInt(String text, String title, int minNum) {
        return checkNumInt(text, title, minNum, Integer.MAX_VALUE);
    }

    public static int checkNumInt(String text, String title, int minNum, int maxNum) {
        int value = minNum;
        if (text == null || text.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ô " + title + " không được trống!", "Warning", JOptionPane.WARNING_MESSAGE);
            return value;
        }
        int result = -1;
        try {
            result = Integer.parseInt(text);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ô " + title + " chỉ có thể nhập số!", "Warning", JOptionPane.WARNING_MESSAGE);
            return value;
        }
        if (result < minNum) {
            value = minNum;
        } else if (result > maxNum) {
            value = maxNum;
        } else {
            value = result;
        }
        return value;
    }

    

}
