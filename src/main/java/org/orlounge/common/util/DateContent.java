package org.orlounge.common.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.orlounge.exception.ORException;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
public abstract class DateContent extends Date implements Serializable {

    public final static String HOUR_DIFF_TYPE = "HOURS";
    public final static String DAY_DIFF_TYPE = "DAY";
    public final static String MINUTE_DIFF_TYPE = "MINUTE";
    public final static String SECOND_DIFF_TYPE = "SECOND";


    /**
     * Date Formats
     */

    public static final String MM_DD_YYYY = "MM/dd/yyyy";
    public static final String DD_MM_YYYY = "dd/mm/yyyy";
    public static final String MM_DD_YY = "MM/dd/yy";
    public static final String YYYY_MM_DD = "yyyy/MM/dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String MMM_YY_WITH_DASH = "MMM-yy";
    public static final String MM_DD_YY_HH_MM_AM_PM = "MM/dd/yy HH:mm a";
    public static final String MM_DD_YYYY_HH_MM_AM_PM = "MM/dd/yyyy HH:mm a";
    public static final String MM_DD_YYYY_H_MM_AM_PM = "MM/dd/yyyy, HH:mm a";
    public static final String YYYY_MM_DD_DASH = "yyyy-MM-dd";

    public static final String DD_MMM_YYYY = "dd MMM yyyy";
    public static final String HH_MM_AM_PM_DD_MMM_YYYY = "HH:mm a dd MMM yyyy";
    public static final String DD_MMM_YYYY_HH_MM_AM_PM = " dd MMM yyyy, HH:mm a";

    public static final String HH_MM = "HH:mm a";

    public static final String DATA_BASE_DATE_FORMAT = "yyyy-MM-dd";

    public static  final String HH_MM_AM_PM_DD_MMM_YY="HH:mm a dd/MM/yyyy";

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static Date formatStringIntoDate(final String date, final String dateFormat) {
        DateFormat formatter;
        Date formattedDate = null;
        if (dateFormat != null ) {
            formatter = new SimpleDateFormat(dateFormat, Locale.getDefault());
        } else {
            formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.getDefault());
        }
        try {
            if (date != null && !date.isEmpty()) {
                formattedDate = formatter.parse(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    public static Date getGMTDateFromMilliSeconds(final long milliSeconds) {
        try {
            final DateTimeZone dateTimeZone = DateTimeZone.getDefault();
            return new Date(dateTimeZone.convertLocalToUTC(milliSeconds, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatDateIntoString(final Date date, final String dateFormat) {
        DateFormat formatter;
        String formattedString = null;
        if (dateFormat != null && !dateFormat.isEmpty()) {
            formatter = new SimpleDateFormat(dateFormat, Locale.getDefault());
        } else {
            formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.getDefault());
        }
        try {
            if (date != null) {
                formattedString = formatter.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return formattedString;
    }

    public static Date formatDateIntoDate(final Date date, String dateFormat) {
        String formattedString = null;
        Date formattedDate = null;
        if (dateFormat == null || dateFormat.isEmpty()) {
            dateFormat = "MM-dd-yyyy HH:mm:ss";
        }

        try {
            if (date != null ) {
                formattedString = formatDateIntoString(date, dateFormat);
                formattedDate = formatStringIntoDate(formattedString, dateFormat);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    public static String formatStringIntoString(final String date, String dateFormat) {
        String formattedString = null;
        Date formattedDate = null;
        if (dateFormat == null || dateFormat.isEmpty()) {
            dateFormat = "MM-dd-yyyy HH:mm:ss";
        }

        try {
            if (date != null && !date.isEmpty()) {
                formattedDate = formatStringIntoDate(date, dateFormat);
                formattedString = formatDateIntoString(formattedDate, dateFormat);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return formattedString;
    }

    /**
     * This will convert DB Date to local date and then convert it to milliseconds.
     *
     * @param date
     */
    public static long convertUTCDateIntoLocalDateMilli(final Date date) {
        final DateTime dateTime = new DateTime(date, DateTimeZone.UTC);

        final DateTimeZone dateTimeZone1 = DateTimeZone.getDefault();
        final DateTime dateTime1 = new DateTime(date, dateTimeZone1);

        final Long offSetDiff = (long) (dateTimeZone1.getOffset(dateTime1.getMillis()));

        return dateTime.getMillis() + offSetDiff;

    }

    /**
     * This will convert local date to time zone date and then returned millisecond of that date.
     *
     * @param timeZone
     * @param date
     */
    public static long convertLocalDateIntoTmizoneDateMilli(final String timeZone, final Date date) {
        final DateTimeZone dateTimeZone = DateTimeZone.forID(timeZone);
        final DateTime dateTime = new DateTime(date, dateTimeZone);

        final DateTimeZone dateTimeZone1 = DateTimeZone.getDefault();
        final DateTime dateTime1 = new DateTime(date, dateTimeZone1);

        final Long offSetDiff = (long) (dateTimeZone1.getOffset(dateTime1.getMillis()) - dateTimeZone.getOffset(dateTime.getMillis()));

        return dateTime.getMillis() + offSetDiff;

    }

    /**
     * @param timeZone
     * @return
     */

    public static Date convertCurrentLocalDateIntoTmizoneDate(final String timeZone) {
        final Date currentDate = new Date();
        final DateTimeZone dateTimeZone = DateTimeZone.forID(timeZone);
        final DateTime dateTime = new DateTime(currentDate, dateTimeZone);

        final DateTimeZone dateTimeZone1 = DateTimeZone.getDefault();
        final DateTime dateTime1 = new DateTime(currentDate, dateTimeZone1);

        final Long offSetDiff = (long) (dateTimeZone1.getOffset(dateTime1.getMillis()) - dateTimeZone.getOffset(dateTime.getMillis()));

        final long finalTime = dateTime.getMillis() + offSetDiff;

        return new Date(finalTime);
    }

    public static String convertCurrentLoalDateIntoTimeZoneDateString(final String timeZone, final String returnedDateFormat) {
        final Date currentDate = convertCurrentLocalDateIntoTmizoneDate(timeZone);
        return formatDateIntoString(currentDate, returnedDateFormat);
    }

    public static Date formatClientStringDateIntoSystemDate(final String date, final String dateFormat) {
        if (date != null && !date.isEmpty()) {
            final Date stringDate = formatStringIntoDate(date, dateFormat);
            final String gmtStringDate = DateUtility.getTimeZoneSpecificDate(null, dateFormat, stringDate);
            final Date gmtDate = formatStringIntoDate(gmtStringDate, dateFormat);

            return gmtDate;
        } else {
            return null;
        }

    }

    public static String formatClientStringIntoSystemDateString(final String date, final String dateFormat) {
        if (ProcessData.isValid(date)) {
            final Date stringDate = formatStringIntoDate(date, dateFormat);
            final String gmtStringDate = DateUtility.getTimeZoneSpecificDate(null, dateFormat, stringDate);

            return gmtStringDate;
        } else {
            return null;
        }

    }

    public static Date convertObjectIntoDate(final Object date) {
        if (!StringUtil.isEmptyString(date) && date instanceof Date) {
            return (Date) date;
        }
        return null;
    }

    public static Date convertDashboardDateToString(final String date) {
        if (!StringUtil.isEmptyString(date)) {
            final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyyMMdd");
            final DateTimeFormatter df1 = DateTimeFormat.forPattern("yyyy/MM/dd");
            final DateTime formattedDateTime = dateFormatter.parseDateTime(date);
            final String dateStirng = formattedDateTime.toString(df1);

            return formatClientStringDateIntoSystemDate(dateStirng, "yyyy/MM/dd");
        }
        return null;
    }

    public static Integer getDifferenceBetweenDays(final Date startDate, final Date endDate) {
        if (startDate != null && endDate != null) {
            return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
        }
        return null;

    }

    public static Integer getDifferenceBetweenDaysWithTime(final Date startDate, final Date endDate) {
        if (startDate != null && endDate != null) {
            return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
        }
        return null;

    }

    public static Integer getDifferenceBetweenDate(final Date startDate, final Date endDate, final String type) {
        if (startDate != null && endDate != null) {
            if (type.equals(DAY_DIFF_TYPE)) {
                return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
            } else if (type.equals(MINUTE_DIFF_TYPE)) {
                return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60));
            } else if (type.equals(SECOND_DIFF_TYPE)) {
                return (int) ((endDate.getTime() - startDate.getTime()) / 1000);
            } else if (type.equals(HOUR_DIFF_TYPE)) {
                return (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60));
            }
        }
        return null;

    }

    /**
     * This method compare two date without considering time.
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Integer getDifferenceBetweenDatesWithoutTime(final Date startDate, final Date endDate) {
        return DateTimeComparator.getDateOnlyInstance().compare(startDate, endDate);
    }

    public static String formatDateForMessages(final Date date, final String timeZone) {
        if (!StringUtil.isEmptyString(date)) {
            final Date formattedDate = formatDateIntoDate(date, "yyyy-MM-dd");
            final Date currentDate = formatDateIntoDate(getCurrentGMTDate(), "yyyy-MM-dd");
            final Integer diffInDays = getDifferenceBetweenDays(formattedDate, currentDate);
            String dateFormat = "";
            if (diffInDays == 0) {
                dateFormat = "HH:mm a";
            }
            else{
                dateFormat = DateContent.HH_MM_AM_PM_DD_MMM_YY;
            }
            /* else if (diffInDays <= EzCacConstants.SEVEN) {
                dateFormat = "MMM dd";
            } else {
                dateFormat = EzCacConstants.MM_DD_YY;
            }*/

            return convertDBDateToClientDateString(date, timeZone, dateFormat);
        }
        return null;
    }

    public static boolean validateDates(final Object fromDate, final Object toDate, final String dateFormat) {
        if (ProcessData.isValid(fromDate) && ProcessData.isValid(toDate)) {
            long fromDateInMilli = 0;
            long toDateInMilli = 0;
            if (fromDate instanceof Date) {
                fromDateInMilli = ProcessData.getDate(fromDate).getTime();
                toDateInMilli = ProcessData.getDate(toDate).getTime();
            } else {
                fromDateInMilli = formatStringIntoDate(ProcessData.getString(fromDate), dateFormat).getTime();
                toDateInMilli = formatStringIntoDate(ProcessData.getString(toDate), dateFormat).getTime();
            }
            if (fromDateInMilli > toDateInMilli) {
                throw new ORException(700008);
            }
            return true;
        } else if (ProcessData.isValid(fromDate) && !ProcessData.isValid(toDate)) {
            throw new ORException(700007);
        } else if (!ProcessData.isValid(fromDate) && ProcessData.isValid(toDate)) {
            throw new ORException(700007);
        }
        return true;
    }


    public static List<Map<String, Object>> getStartEndDateOfLast12Months(final String timeZone) {

        final Calendar startDateCalendar = Calendar.getInstance();

        Date startDateTime = null;

        final String dateStr = DateUtility.getTimeZoneSpecificDate(timeZone, "yyyy-MM-dd HH:mm:ss", startDateCalendar.getTime());

        final Date date = formatStringIntoDate(dateStr, "yyyy-MM-dd HH:mm:ss");

        final List<Map<String, Object>> dateList = new ArrayList<Map<String, Object>>();
        Map<String, Object> dateMap;
        for (int i = 12; i > 0; i--) {
            dateMap = new HashMap<String, Object>();
            startDateCalendar.setTime(date);
            startDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            startDateCalendar.set(Calendar.MINUTE, 59);
            startDateCalendar.set(Calendar.SECOND, 59);
            startDateCalendar.add(Calendar.MONTH, -i);
            startDateCalendar.set(Calendar.DATE, startDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            startDateCalendar.set(Calendar.MILLISECOND, 0);
            startDateTime = startDateCalendar.getTime();

            long startDateTimeMilli = convertLocalDateIntoTmizoneDateMilli(timeZone, startDateTime);
            dateMap.put("endDate", startDateTimeMilli);

            startDateCalendar.set(Calendar.DATE, startDateCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            startDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
            startDateCalendar.set(Calendar.MINUTE, 0);
            startDateCalendar.set(Calendar.SECOND, 0);
            startDateCalendar.set(Calendar.MILLISECOND, 0);
            startDateTime = startDateCalendar.getTime();

            startDateTimeMilli = convertLocalDateIntoTmizoneDateMilli(timeZone, startDateTime);
            dateMap.put("startDate", startDateTimeMilli);

            final String formattedDate = formatDateIntoString(startDateTime, DateContent.MMM_YY_WITH_DASH);
            dateMap.put("displayDate", formattedDate);

            dateList.add(dateMap);
        }

        return dateList;

    }

    public static List<Map<String, Object>> getStartEndDateOfLast7Days(final String timeZone) {

        final Calendar startDateCalendar = Calendar.getInstance();

        Date startDateTime = null;

        final String dateStr = DateUtility.getTimeZoneSpecificDate(timeZone, "yyyy-MM-dd HH:mm:ss", startDateCalendar.getTime());

        final Date date = formatStringIntoDate(dateStr, "yyyy-MM-dd HH:mm:ss");

        final List<Map<String, Object>> dateList = new ArrayList<Map<String, Object>>();
        Map<String, Object> dateMap;
        for (int i = 7; i > 0; i--) {
            dateMap = new HashMap<String, Object>();
            startDateCalendar.setTime(date);
            startDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            startDateCalendar.set(Calendar.MINUTE, 59);
            startDateCalendar.set(Calendar.SECOND, 59);
            startDateCalendar.add(Calendar.DATE, -i);
            startDateCalendar.set(Calendar.MILLISECOND, 0);
            startDateTime = startDateCalendar.getTime();

            long startDateTimeMilli = convertLocalDateIntoTmizoneDateMilli(timeZone, startDateTime);
            dateMap.put("endDate", startDateTimeMilli);

            startDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
            startDateCalendar.set(Calendar.MINUTE, 0);
            startDateCalendar.set(Calendar.SECOND, 0);
            startDateCalendar.set(Calendar.MILLISECOND, 0);
            startDateTime = startDateCalendar.getTime();

            startDateTimeMilli = convertLocalDateIntoTmizoneDateMilli(timeZone, startDateTime);
            dateMap.put("startDate", startDateTimeMilli);

            final String formattedDate = formatDateIntoString(startDateTime, "MMM-dd");
            dateMap.put("displayDate", formattedDate);

            dateList.add(dateMap);
        }

        return dateList;

    }

    /**
     * return start and end date of every week from last specified week.
     */

    public static List<Map<String, Object>> getAllStartEndDateOfEveryWeekFromLastSpecifiedWeeks(final int num, final String timeZone) {

        final Calendar startDateCalendar = Calendar.getInstance();

        Date startDateTime = null;
        Date endDateTime = null;

        final String dateStr = DateUtility.getTimeZoneSpecificDate(timeZone, "yyyy-MM-dd HH:mm:ss", startDateCalendar.getTime());

        final Date date = formatStringIntoDate(dateStr, "yyyy-MM-dd HH:mm:ss");

        final List<Map<String, Object>> dateList = new ArrayList<Map<String, Object>>();
        Map<String, Object> dateMap;
        for (int i = num; i > 0; i--) {
            dateMap = new HashMap<String, Object>();
            startDateCalendar.setTime(date);
            startDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            startDateCalendar.set(Calendar.MINUTE, 59);
            startDateCalendar.set(Calendar.SECOND, 59);
            startDateCalendar.setFirstDayOfWeek(Calendar.MONDAY);
            startDateCalendar.add(Calendar.WEEK_OF_YEAR, -i);
            startDateCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            startDateCalendar.set(Calendar.MILLISECOND, 0);
            endDateTime = startDateCalendar.getTime();

            long startDateTimeMilli = convertLocalDateIntoTmizoneDateMilli(timeZone, endDateTime);
            dateMap.put("endDate", startDateTimeMilli);

            startDateCalendar.set(Calendar.DATE, startDateCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            startDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
            startDateCalendar.set(Calendar.MINUTE, 0);
            startDateCalendar.set(Calendar.SECOND, 0);
            startDateCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            startDateCalendar.set(Calendar.MILLISECOND, 0);
            startDateTime = startDateCalendar.getTime();

            startDateTimeMilli = convertLocalDateIntoTmizoneDateMilli(timeZone, startDateTime);
            dateMap.put("startDate", startDateTimeMilli);

            final String formattedDate = formatDateIntoString(startDateTime, "MMM-dd") + " - " + formatDateIntoString(endDateTime, "MMM-dd");
            dateMap.put("displayDate", formattedDate);
            dateList.add(dateMap);
        }

        return dateList;

    }



    /**
     * to check whether inDate is between start and end date or not.
     *
     * @param startDate
     * @param endDate
     * @param comparedDate
     * @return true if comparedDate is between start and end date, else false
     */
    public static boolean isDateExistsBetweenTwoDates(final Date startDate, final Date endDate, final Date comparedDate) {
        boolean isDateExists = false;
        if (ProcessData.isValid(startDate) && ProcessData.isValid(endDate) && ProcessData.isValid(comparedDate)) {

            final Long inDateMilliSeconds = comparedDate.getTime();
            if (inDateMilliSeconds >= startDate.getTime() && inDateMilliSeconds <= endDate.getTime()) {
                isDateExists = true;
            }

        } else {
            isDateExists = false;
        }
        return isDateExists;
    }

    /**
     * to check whether inDate is between start and end date or not. <br>
     * Inputs are date-time in milli seconds.
     *
     * @param startDateTime
     * @param endDateTime
     * @param comparedDateTime
     * @return true if comparedDateTime is between start and end date, else false
     */
    public static Boolean isDateExistsBetweenTwoDates(final Long startDateTime, final Long endDateTime, final Long comparedDateTime) {
        boolean isDateExists = false;
        if (ProcessData.isValid(startDateTime) && ProcessData.isValid(endDateTime) && ProcessData.isValid(comparedDateTime)) {
            if (comparedDateTime >= startDateTime && comparedDateTime <= endDateTime) {
                isDateExists = true;
            }
        } else {
            isDateExists = false;
        }
        return isDateExists;
    }

    /**
     * Retrieve all start and end date of last 12 months and display name of month (MMM-yy) for CDI Reports.<br>
     * TODO TimeZone changes will be applicable here.
     *
     * @return List<Map<String, Object>><br>
     * startDate - start date of month <br>
     * endDate - end date of month <br>
     * displayDate - display name of month
     */

    public static List<Map<String, String>> getStartEndDateMonthNameOfLast12Months(final String timeZone) {

        final Calendar startDateCalendar = Calendar.getInstance();

        Date startDateTime = null;

        final String dateStr = DateUtility.getTimeZoneSpecificDate(timeZone, "yyyy-MM-dd HH:mm:ss", startDateCalendar.getTime());

        final Date date = formatStringIntoDate(dateStr, "yyyy-MM-dd HH:mm:ss");

        final List<Map<String, String>> dateList = new ArrayList<Map<String, String>>();
        Map<String, String> dateMap;
        for (int i = 12; i > 0; i--) {
            dateMap = new HashMap<String, String>();
            startDateCalendar.setTime(date);
            startDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
            startDateCalendar.set(Calendar.MINUTE, 59);
            startDateCalendar.set(Calendar.SECOND, 59);
            startDateCalendar.add(Calendar.MONTH, -i);
            startDateCalendar.set(Calendar.DATE, startDateCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            startDateCalendar.set(Calendar.MILLISECOND, 0);
            startDateTime = startDateCalendar.getTime();

            dateMap.put("endDate", DateContent.convertClientDateIntoUTCString(startDateTime, timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, DateContent.YYYY_MM_DD_HH_MM_SS));

            startDateCalendar.set(Calendar.DATE, startDateCalendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            startDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
            startDateCalendar.set(Calendar.MINUTE, 0);
            startDateCalendar.set(Calendar.SECOND, 0);
            startDateCalendar.set(Calendar.MILLISECOND, 0);
            startDateTime = startDateCalendar.getTime();
            dateMap.put("startDate", DateContent.convertClientDateIntoUTCString(startDateTime, timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, DateContent.YYYY_MM_DD_HH_MM_SS));

            final String formattedDate = formatDateIntoString(startDateTime, DateContent.MMM_YY_WITH_DASH);
            dateMap.put("displayDate", formattedDate);

            dateList.add(dateMap);
        }

        return dateList;

    }

    /**
     * This method converts date retrieved from Database to the date of client time zone.dateFormat is the format of date that is passed.
     *
     * @param dateStr
     * @param timeZone
     * @param dateFormat
     * @return Date
     */
    public static Date convertDBDateToClientDate(final String dateStr, final String timeZone, final String dateFormat) {
        if (ProcessData.isValid(dateStr) && ProcessData.isValid(timeZone) && ProcessData.isValid(dateFormat)) {
            Date date = DateContent.formatStringIntoDate(dateStr, dateFormat);

            date = convertDBDateToClientDate(date, timeZone);

            return date;
        }
        return null;
    }

    /**
     * This method converts date retrieved from Database to the date of client time zone.
     *
     * @param date
     * @param timeZone
     * @return Date
     */
    public static Date convertDBDateToClientDate(final Date date, final String timeZone) {
        if (ProcessData.isValid(date) && ProcessData.isValid(timeZone)) {
            final DateTime dateTime = new DateTime(date, DateTimeZone.UTC);

            final DateTimeZone dateTimeZone1 = DateTimeZone.forID(timeZone);
            final DateTime dateTime1 = new DateTime(date, dateTimeZone1);

            final long finalTime = dateTime.getMillis() + dateTimeZone1.getOffset(dateTime1.getMillis());

            return new Date(finalTime);
        }
        return null;
    }

    public static long convertDBDateToClientDateAndGetTimeInMillSec(final Date date, final String timeZone) {
        if (ProcessData.isValid(date) && ProcessData.isValid(timeZone)) {
            final DateTime dateTime = new DateTime(date, DateTimeZone.UTC);

            final DateTimeZone dateTimeZone1 = DateTimeZone.forID(timeZone);
            final DateTime dateTime1 = new DateTime(date, dateTimeZone1);


            return dateTime.getMillis() + dateTimeZone1.getOffset(dateTime1.getMillis());
        }
        return 0;
    }

    /**
     * @param dateStr
     * @param timeZone
     * @param dateFormat
     * @param returnDateFormat
     * @return
     */

    public static String convertDBDateToClientDateString(final Object dateStr, final String timeZone, final String dateFormat, final String returnDateFormat) {
        if (ProcessData.isValid(dateStr) && ProcessData.isValid(timeZone) && ProcessData.isValid(dateFormat) && ProcessData.isValid(returnDateFormat)) {
            Date date = null;
            if (dateStr instanceof Date) {
                date = ProcessData.getDate(dateStr);

            } else {
                date = DateContent.formatStringIntoDate(dateStr.toString(), dateFormat);
            }

            date = convertDBDateToClientDate(date, timeZone);

            return DateContent.formatDateIntoString(date, returnDateFormat);
        }
        return null;
    }

    public static String convertDBDateToClientDateString(final Object dateStr, final String timeZone, final String returnDateFormat) {
        return convertDBDateToClientDateString(dateStr, timeZone, DateContent.YYYY_MM_DD_HH_MM_SS, returnDateFormat);
    }

    /**
     * This method is used to get current GMT Date.
     *
     * @return Date
     */
    public static Date getCurrentGMTDate() {
        final Date date = new Date();
        final DateTimeZone dateTimeZone = DateTimeZone.getDefault();
        final DateTime dateTime = new DateTime(date, dateTimeZone);

        return new Date(dateTimeZone.convertLocalToUTC(dateTime.getMillis(), true));

    }

    public static String getCurrentGMTDateString(final String dateFormat) {
        Date date = new Date();
        final DateTimeZone dateTimeZone = DateTimeZone.getDefault();

        date = new Date(dateTimeZone.convertLocalToUTC(date.getTime(), true));

        return formatDateIntoString(date, dateFormat);

    }

    public static String getCurrentGMTDateString() {

        return getCurrentGMTDateString(DateContent.YYYY_MM_DD_HH_MM_SS);

    }

    /**
     * This method converts date that are coming from client side to the date of UTC time zone.
     *
     * @param date
     * @param timeZone
     * @return Date
     */
    public static Date convertClientDateIntoUTCDate(final Date date, final String timeZone) {
        if (ProcessData.isValid(date) && ProcessData.isValid(timeZone)) {
            final DateTimeZone dateTimeZone = DateTimeZone.forID(timeZone);
            final DateTime dateTime = new DateTime(date, dateTimeZone);

            final DateTimeZone dateTimeZone1 = DateTimeZone.getDefault();
            final DateTime dateTime1 = new DateTime(date, dateTimeZone1);

            final Long offSetDiff = (long) (dateTimeZone1.getOffset(dateTime1.getMillis()) - dateTimeZone.getOffset(dateTime.getMillis()));

            final long finalTime = dateTime.getMillis() + offSetDiff;

            return new Date(dateTimeZone1.convertLocalToUTC(finalTime, true));
        }
        return null;
    }


    public static String getOffset(final Date date, final String timeZone) {
        if (ProcessData.isValid(date) && ProcessData.isValid(timeZone)) {
            final DateTimeZone dateTimeZone = DateTimeZone.forID(timeZone);
            final DateTime dateTime = new DateTime(date, dateTimeZone);

            //final DateTimeZone dateTimeZone1 = DateTimeZone.getDefault();
            //final DateTime dateTime1 = new DateTime(date, dateTimeZone1);
            final int t = 60*60*1000;

            final int offSetDiff =  dateTimeZone.getOffset(dateTime.getMillis());

            int hr = offSetDiff / t;
            int min = (offSetDiff % t)/(1000*60);
            boolean pos = (hr>=0);

            hr = hr<0 ? hr*-1 : hr;
            min = min<0 ? min*-1 : min;
            // final long finalTime = dateTime.getMillis() + offSetDiff;

            return ((pos) ? "+": "-" ) + String.format("%02d", hr) +":"+ String.format("%02d", min);
        }
        return null;
    }



    /**
     * This method converts date that are coming from client side to the date of UTC time zone.dateFormat is the format of date that is passed.
     *
     * @param dateStr
     * @param timeZone
     * @param dateFormat
     * @return Date
     */
    public static Date convertClientDateIntoUTCDate(final String dateStr, final String timeZone, final String dateFormat) {
        if (ProcessData.isValid(dateStr) && ProcessData.isValid(timeZone) && ProcessData.isValid(dateFormat)) {
            Date date = DateContent.formatStringIntoDate(dateStr, dateFormat);

            date = convertClientDateIntoUTCDate(date, timeZone);

            return date;
        }
        return null;
    }

    /**
     * This method converts date that are coming from client side to the date of UTC time zone.dateFormat is the format of date that is passed.
     *
     * @param dateStr
     * @param timeZone
     * @param dateFormat
     * @param returnDateFormat
     * @return String
     */
    public static String convertClientDateIntoUTCString(final Object dateStr, final String timeZone, final String dateFormat, final String returnDateFormat) {
        if (ProcessData.isValid(dateStr) && ProcessData.isValid(timeZone) && ProcessData.isValid(dateFormat) && ProcessData.isValid(returnDateFormat)) {
            Date date = null;

            if (dateStr instanceof Date) {
                date = ProcessData.getDate(dateStr);

            } else {
                date = DateContent.formatStringIntoDate(dateStr.toString(), dateFormat);
            }
            date = convertClientDateIntoUTCDate(date, timeZone);
            return DateContent.formatDateIntoString(date, returnDateFormat);
        }
        return null;
    }

    /**
     * This method converts date that are coming from client side from search panel to the date of UTC time zone to compare it with database date.dateFormat is
     * the format of date that is passed.
     *
     * @param fromDateStr
     * @param toDateStr
     * @param timeZone
     * @param dateFormat
     * @return String[]
     */
    public static String[] convertClientSearchDateIntoUTCString(final String fromDateStr, final String toDateStr, final String timeZone, final String dateFormat) {
        if (ProcessData.isValid(fromDateStr) && ProcessData.isValid(toDateStr) && ProcessData.isValid(timeZone) && ProcessData.isValid(dateFormat)) {
            String[] finalDate = new String[2];
            Date fromDate = DateContent.formatStringIntoDate(fromDateStr, dateFormat);

            Date toDate = DateContent.formatStringIntoDate(toDateStr, dateFormat);
            toDate = DateUtility.getEndTimeToDate(toDate);

            fromDate = convertClientDateIntoUTCDate(fromDate, timeZone);
            finalDate[0] = DateContent.formatDateIntoString(fromDate, DateContent.YYYY_MM_DD_HH_MM_SS);

            toDate = convertClientDateIntoUTCDate(toDate, timeZone);
            finalDate[1] = DateContent.formatDateIntoString(toDate, DateContent.YYYY_MM_DD_HH_MM_SS);
            return finalDate;
        }

        return new String[0];
    }

    /**
     * This method converts date that are coming from client side from search panel to the date of UTC time zone to compare it with database date.dateFormat is
     * the format of date that is passed.
     *
     * @param fromDateStr
     * @param toDateStr
     * @param timeZone
     * @param dateFormat
     * @return Date[]
     */
    public static Date[] convertClientSearchDateIntoUTCDate(final String fromDateStr, final String toDateStr, final String timeZone, final String dateFormat) {
        if (ProcessData.isValid(fromDateStr) && ProcessData.isValid(toDateStr) && ProcessData.isValid(timeZone) && ProcessData.isValid(dateFormat)) {
            Date[] finalDate = new Date[2];
            final Date fromDate = DateContent.formatStringIntoDate(fromDateStr, dateFormat);

            Date toDate = DateContent.formatStringIntoDate(toDateStr, dateFormat);
            toDate = DateUtility.getEndTimeToDate(toDate);

            finalDate[0] = convertClientDateIntoUTCDate(fromDate, timeZone);
            finalDate[1] = convertClientDateIntoUTCDate(toDate, timeZone);

            return finalDate;
        }

        return new Date[0];
    }

    /* this function return time String in HH:mm AM  12 hours format
     *
     * */
    public static String getTime(Date date) {

        String time;
        SimpleDateFormat sdf = new SimpleDateFormat(DateContent.HH_MM );
        time = sdf.format(date);

        return time;
    }

    public static String convertDateformatToAnother(String currentDate,String existingFormat,String convertToFormat){
        String convertedDate = null;
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(existingFormat);
            Date tempDate= simpleDateFormat.parse(currentDate);
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(convertToFormat);
            convertedDate= outputDateFormat.format(tempDate).toString();
        } catch (ParseException ex){
            ex.printStackTrace();
        }
        return  convertedDate;
    }


}
