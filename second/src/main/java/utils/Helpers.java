package utils;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;

import java.util.Random;

/**
 * A class with auxiliary methods
 */
@Slf4j
public class Helpers {

    /**
     * A method that compares numeric values
     *
     * @return are the values equal or not
     */
    public static boolean checkIntParams(int origin, int bounds, int keyValue, String check) {
        Random random = new Random();
        int randomInt = random.nextInt(origin, bounds);

        if (keyValue != randomInt) {
            log.warn(check + " parameter doesn't match");
            return false;
        }
        return true;
    }

    /**
     * A method that checks whether the array is empty or not
     *
     * @return is the array empty or not
     */
    public static boolean checkArray(JSONArray arr, String check) {
        if (arr.length() != 0) {
            return true;
        } else {
            log.warn(check + " array is empty");
            return false;
        }
    }

    /**
     * Method that checks the isDay parameter
     *
     * @return is the parameter correct or not
     */
    public static boolean checkIsDay(String firstParam) {
        if (!(firstParam.equals("yes") || firstParam.equals("no"))) {
            log.warn("invalid day value");
            return false;
        }
        return true;
    }
}
