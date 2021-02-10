package org.orlounge.common.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
public class DateUtility {

    /**
     * Method added by DS
     */
    public static String getTimeZoneSpecificDate(final String timeZone, final String dateFormat, final Date date) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(dateFormat);
        DateTimeZone dateTimeZone = null;
        if (ProcessData.isValid(timeZone)) {
            dateTimeZone = DateTimeZone.forID(timeZone);
        } else {
            dateTimeZone = DateTimeZone.getDefault();
        }
        final DateTime dateTime = new DateTime(date, dateTimeZone);
        return dateTime.toString(dateTimeFormatter);
    }

    public static Date getStartDateOfCurrentMonth() {
        final Calendar aCalendar = Calendar.getInstance();
        aCalendar.set(Calendar.DATE, 1);
        aCalendar.set(Calendar.HOUR_OF_DAY, 0);
        aCalendar.set(Calendar.MINUTE, 0);
        aCalendar.set(Calendar.SECOND, 0);
        final Date fromDate = aCalendar.getTime();

        return fromDate;
    }

    public static Date getStartDateOfCurrentMonth(final String timeZone) {
        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startDate = calendar.getTime();
        startDate = DateContent.convertClientDateIntoUTCDate(startDate, timeZone);

        return startDate;

    }

    public static Date getEndDateOfCurrentMonth(final String timeZone) {
        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endDate = calendar.getTime();
        endDate = DateContent.convertClientDateIntoUTCDate(endDate, timeZone);

        return endDate;

    }

    public static Date[] getStartEndDateOfCurrentMonth(final String timeZone) {

        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);
        Date[] startEndDate = new Date[2];

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endDate = calendar.getTime();
        endDate = DateContent.convertClientDateIntoUTCDate(endDate, timeZone);
        startEndDate[1] = endDate;

        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        endDate = calendar.getTime();
        endDate = DateContent.convertClientDateIntoUTCDate(endDate, timeZone);
        startEndDate[0] = endDate;

        return startEndDate;

    }

    public static String[] getClientStartEndDateOfCurrentMonth(final String timeZone, final String returnDateFormat) {

        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);
        String[] startEndDate = new String[2];

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endDate = calendar.getTime();
        startEndDate[1] = DateContent.formatDateIntoString(endDate, returnDateFormat);

        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        endDate = calendar.getTime();
        startEndDate[0] = DateContent.formatDateIntoString(endDate, returnDateFormat);

        return startEndDate;

    }

    public static Date[] getStartEndDateOfPreviousMonth(final String timeZone) {
        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);
        Date[] startEndDate = new Date[2];

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endDate = calendar.getTime();
        endDate = DateContent.convertClientDateIntoUTCDate(endDate, timeZone);
        startEndDate[1] = endDate;

        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        endDate = calendar.getTime();
        endDate = DateContent.convertClientDateIntoUTCDate(endDate, timeZone);
        startEndDate[0] = endDate;

        return startEndDate;

    }

    public static String[] getClientStartEndDateOfPreviousMonth(final String timeZone, final String returnDateFormat) {
        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);
        String[] startEndDate = new String[2];

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endDate = calendar.getTime();
        startEndDate[1] = DateContent.formatDateIntoString(endDate, returnDateFormat);

        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        endDate = calendar.getTime();
        startEndDate[0] = DateContent.formatDateIntoString(endDate, returnDateFormat);

        return startEndDate;

    }

    public static Date[] getStartEndDateOfPreviousWeek(final String timeZone) {
        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);
        Date[] startEndDate = new Date[2];

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endDate = calendar.getTime();
        endDate = DateContent.convertClientDateIntoUTCDate(endDate, timeZone);
        startEndDate[1] = endDate;

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        endDate = calendar.getTime();
        endDate = DateContent.convertClientDateIntoUTCDate(endDate, timeZone);
        startEndDate[0] = endDate;

        return startEndDate;

    }

    public static String[] getClientStartEndDateOfPreviousWeek(final String timeZone, final String returnDateFormat) {
        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);
        String[] startEndDate = new String[2];

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        final Date endDate = calendar.getTime();
        startEndDate[1] = DateContent.formatDateIntoString(endDate, returnDateFormat);

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        startEndDate[0] = DateContent.formatDateIntoString(endDate, returnDateFormat);

        return startEndDate;

    }

    public static Date getEndDateOfPreviousMonth(final String timeZone) {
        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endDate = calendar.getTime();
        endDate = DateContent.convertClientDateIntoUTCDate(endDate, timeZone);

        return endDate;

    }

    public static Date getStartDateOfPreviousMonth(final String timeZone) {
        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startDate = calendar.getTime();
        startDate = DateContent.convertClientDateIntoUTCDate(startDate, timeZone);

        return startDate;

    }

    public static Date getEndDateOfCurrentMonth() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    public static Date getStartDateOfPreviousMonth() {
        final Calendar aCalendar = Calendar.getInstance();
        aCalendar.add(Calendar.MONTH, -1);
        aCalendar.set(Calendar.DATE, 1);
        aCalendar.set(Calendar.HOUR, 0);
        aCalendar.set(Calendar.MINUTE, 0);
        aCalendar.set(Calendar.SECOND, 0);
        return aCalendar.getTime();
    }

    /**
     * get start and end date of last specified weeks.<br>
     * suppose num : 2 then it gives start and end date of last 2 weeks.
     *
     * @return dateArray - start date, end date
     */

    public static Date[] getStartEndDateOfLastSpecifiedWeeks(final int num, final String timeZone) {
        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endDateTime = calendar.getTime();

        calendar.add(Calendar.WEEK_OF_YEAR, -(num - 1));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDateTime = calendar.getTime();

        endDateTime = DateContent.convertClientDateIntoUTCDate(endDateTime, timeZone);
        startDateTime = DateContent.convertClientDateIntoUTCDate(startDateTime, timeZone);
        return new Date[]{startDateTime, endDateTime};
    }

    public static Date[] getStartEndDateOfLast12Months(final String timeZone) {
        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endDateTime = calendar.getTime();

        calendar.add(Calendar.MONTH, -11);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDateTime = calendar.getTime();

        endDateTime = DateContent.convertClientDateIntoUTCDate(endDateTime, timeZone);
        startDateTime = DateContent.convertClientDateIntoUTCDate(startDateTime, timeZone);
        return new Date[]{startDateTime, endDateTime};
    }

    public static Date[] getStartEndDateOfLast7Days(final String timeZone) {
        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        final Date currentDate = DateContent.formatStringIntoDate(currentDateStr, DateContent.YYYY_MM_DD_HH_MM_SS);

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endDateTime = calendar.getTime();

        calendar.add(Calendar.DATE, -6);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDateTime = calendar.getTime();
        endDateTime = DateContent.convertClientDateIntoUTCDate(endDateTime, timeZone);
        startDateTime = DateContent.convertClientDateIntoUTCDate(startDateTime, timeZone);
        return new Date[]{startDateTime, endDateTime};
    }

    public static Date getEndDateOfPreviousMonth() {
        final Calendar aCalendar = Calendar.getInstance();
        aCalendar.add(Calendar.MONTH, -1);
        aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        aCalendar.set(Calendar.HOUR_OF_DAY, 23);
        aCalendar.set(Calendar.MINUTE, 59);
        aCalendar.set(Calendar.SECOND, 59);
        return aCalendar.getTime();
    }

    public static Date getStartDateOfPreviousWeek() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndDateOfPreviousWeek() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * set 23:59:59 time to date object
     *
     * @param date
     * @return date
     * @author Gautam Joshi
     */
    public static Date getEndTimeToDate(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * set 23:59:59 time to date object
     *
     * @param date
     * @return date
     * @author Gautam Joshi
     */
    public static Date getStartTimeToDate(final Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * Get last one year start and end date from current date (month wise)<br>
     * e.g. current Date is 13 FEB 2013 Then it will return End Date = Fri Jan 31 23:59:59 IST 2014 IST 2014 Start Date = Fri Feb 01 00:00:00 IST 2013
     *
     * @return dateArray
     * @author Gautam Joshi
     */
    public static Date[] getLastYearDateRange(String timeZone) {

        final String currentDateStr = DateUtility.getTimeZoneSpecificDate(timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        final Date endDateTime = calendar.getTime();

        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        final Date startDateTime = calendar.getTime();

        return new Date[]{DateContent.convertClientDateIntoUTCDate(startDateTime, timeZone), DateContent.convertClientDateIntoUTCDate(endDateTime, timeZone)};
    }


    /**
     * This method converts seconds to minutes.
     *
     * @param seconds as Object
     * @return 0D if seconds -object is null or invalid, and minutes if seconds - object is valid.
     */
    public static Double convertSecondToMinute(final Object seconds) {
        final Double sec = ProcessData.getDouble(seconds);
        if (ProcessData.isValid(sec)) {
            return new Double(sec / 60);
        }
        return 0D;
    }

    /**
     * Check date is between specified two date
     * @param date date
     * @param from from date
     * @param to to date
     * @return true/false
     */
    public static boolean isDateBetweenSpecifiedDate(Date date, Date from, Date to) {
        return date.compareTo(from) >= 0 && date.compareTo(to) <= 0;
    }

}
