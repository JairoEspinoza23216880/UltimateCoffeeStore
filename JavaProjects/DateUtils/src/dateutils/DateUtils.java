package dateutils;

import java.util.GregorianCalendar;

public class DateUtils {

    public static boolean isSameDay(GregorianCalendar date1, GregorianCalendar date2) {
        return date1.get(GregorianCalendar.YEAR) == date2.get(GregorianCalendar.YEAR)
                && date1.get(GregorianCalendar.MONTH) == date2.get(GregorianCalendar.MONTH)
                && date1.get(GregorianCalendar.DAY_OF_MONTH) == date2.get(GregorianCalendar.DAY_OF_MONTH);
    }

    public static boolean isSameWeek(GregorianCalendar date1, GregorianCalendar date2) {
        return date1.get(GregorianCalendar.YEAR) == date2.get(GregorianCalendar.YEAR)
                && date1.get(GregorianCalendar.WEEK_OF_YEAR) == date2.get(GregorianCalendar.WEEK_OF_YEAR);
    }

    public static boolean isSameMonth(GregorianCalendar date1, GregorianCalendar date2) {
        return date1.get(GregorianCalendar.YEAR) == date2.get(GregorianCalendar.YEAR)
                && date1.get(GregorianCalendar.MONTH) == date2.get(GregorianCalendar.MONTH);
    }

    public static boolean isSamePeriod(GregorianCalendar date1, GregorianCalendar date2, int period) {
        switch (period) {
            case 0: // Same day
                return isSameDay(date1, date2);
            case 1: // Same week
                return isSameWeek(date1, date2);
            case 2: // Same month
                return isSameMonth(date1, date2);
            default:
                throw new IllegalArgumentException("Invalid period. Use 0 for day, 1 for week, 2 for month.");
        }
    }
}