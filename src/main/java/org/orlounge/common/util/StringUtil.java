package org.orlounge.common.util;

import com.amazonaws.util.CollectionUtils;

import java.util.*;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
public class StringUtil {

    public final static int RANDOM_NUMERICONLY = 0;
    public final static int RANDOM_ALPHAONLY = 1;
    //public final static int RANDOM_ALPHANUMERIC = 2;

    /**
     * This method was created in VisualAge.
     *
     * @param string1 java.lang.String
     * @param string2 java.lang.String
     * @return boolean
     */
    public static boolean compare(final Object string1, final Object string2) {
        boolean isSame;
        if (string1 == null && string2 == null) {
            isSame = true;
        } else if (string1 == null || string2 == null) {
            isSame = false;
        } else if (string1.toString() == null || string2.toString() == null) {
            isSame = false;
        } else {
            isSame = string1.toString().equals(string2.toString());
        }
        return isSame;
    }

    /**
     * @param original
     * @param sub
     * @return
     */
    public static boolean containsSubString(final Object original, final Object sub) {
        boolean isContainsSubString;
        final String originalString = ProcessData.getString(original);
        final String subString = ProcessData.getString(sub);
        if (originalString == null || subString == null || originalString.indexOf(subString) == -1) {
            isContainsSubString = false;
        } else {
            isContainsSubString = true;
        }
        return isContainsSubString;
    }

    /**
     * This method was created in VisualAge.
     *
     * @return java.lang.String
     * @param obj java.lang.Object
     */

    /**
     * This will generate a random string of a specified length. It will generate either a numeric string, alpha string, or alphanumeric string depending on the
     * type specified
     */
    public static String createRandomString(final int length, final int stringType) {
        final StringBuffer randomStringBuffer = new StringBuffer("");
        String[] lookup = null;
        int upperBound = 0;
        final Random random = new Random();

        switch (stringType) {
            case RANDOM_ALPHAONLY: {
                lookup = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
                upperBound = 26;
                break;
            }
            case RANDOM_NUMERICONLY: {
                lookup = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
                upperBound = 10;
                break;
            }
            default: {
                lookup = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
                upperBound = 36;
                break;
            }
        }

        for (int i = 0; i < length; i++) {
            randomStringBuffer.append(lookup[random.nextInt(upperBound)]);
        }

        return randomStringBuffer.toString();
    }

    /**
     * @param obj
     * @return
     */
    public static boolean isEmptyString(final Object obj) {
        return isEmptyString(obj, true);
    }

    public static boolean isEmptyString(final Object obj, final boolean doTrim) {
        boolean isEmpty;
        if (obj == null) {
            isEmpty = true;
        } else {
            if (doTrim) {
                isEmpty = ProcessData.getString(obj).trim().length() == 0;
            } else {
                isEmpty = ProcessData.getString(obj).length() == 0;
            }
        }
        return isEmpty;
    }

    public static String toAgeInUTC(final Date birthDate) {

        if (birthDate != null) {
            final Date dob = (Date) birthDate;
            /** Birth day */
            final Calendar caldob = Calendar.getInstance();
            caldob.setTime(dob);
            /** Today */
            final Calendar calnow = Calendar.getInstance();
            calnow.setTime(DateContent.getCurrentGMTDate());

            /** case date in the future */
            if (caldob.after(calnow)) {
                return "invalid";
            }

            int ageYears = calnow.get(Calendar.YEAR) - caldob.get(Calendar.YEAR);
            int ageMonths = calnow.get(Calendar.MONTH) - caldob.get(Calendar.MONTH);
            final int ageWeeks = calnow.get(Calendar.WEEK_OF_YEAR) - caldob.get(Calendar.WEEK_OF_YEAR);
            final int daysInMonth = calnow.get(Calendar.DAY_OF_MONTH) - caldob.get(Calendar.DAY_OF_MONTH);

            /** if birth day is after current day in month -> need to remove a month */
            if (daysInMonth < 1) {
                ageMonths--;
            }

            /** if birth month is after current month -> need to remove a year */
            if (ageMonths < 1) {
                ageYears--;
                ageMonths = 12 + ageMonths;
            }

            /** less than a month */
            if (ageYears == 0 && ageMonths <= 1 && ageWeeks >= 0 && ageWeeks <= 4) {
                if (ageWeeks <= 1) {
                    return ageWeeks + " week";
                }
                return ageWeeks + " weeks";
            }
            /** from 0 years to 2 years -> age in months */
            else if (ageYears >= 0 && ageYears < 2) {
                final int totalMonths = ageYears * 12 + ageMonths;
                if (totalMonths == 1) {
                    return "1 month";
                }
                return totalMonths + " months";
            }
            /** age in years */
            else {
                /** no need to precise 'years' here */
                return Integer.toString(ageYears);
            }
        }
        return null;
    }

    public static Integer toAgeUsingGMT(final Date birthDate) {
        /** in case invalid function returns -1; */

        if (birthDate != null) {
            final Date dob = (Date) birthDate;
            /** Birth day */
            final Calendar caldob = Calendar.getInstance();
            caldob.setTime(dob);
            /** Today */
            final Calendar calnow = Calendar.getInstance();
            calnow.setTime(DateContent.getCurrentGMTDate());

            /** case date in the future */
            if (caldob.after(calnow)) {
                return -1;
            }

            int ageYears = calnow.get(Calendar.YEAR) - caldob.get(Calendar.YEAR);
            int ageMonths = calnow.get(Calendar.MONTH) - caldob.get(Calendar.MONTH);
            final int ageWeeks = calnow.get(Calendar.WEEK_OF_YEAR) - caldob.get(Calendar.WEEK_OF_YEAR);
            final int daysInMonth = calnow.get(Calendar.DAY_OF_MONTH) - caldob.get(Calendar.DAY_OF_MONTH);

            /** if birth day is after current day in month -> need to remove a month */
            if (daysInMonth < 0) {
                ageMonths--;
            }

            /** if birth month is after current month -> need to remove a year */
            if (ageMonths < 0) {
                ageYears--;
                ageMonths += 12;
            }

            /** less than a month */
            if (ageYears == 0 && ageMonths <= 1 && ageWeeks >= 0 && ageWeeks <= 4) {
                if (ageWeeks <= 0) {
                    return 0;
                }
                return 0;
            } else {
                /** no need to precise 'years' here */
                return ageYears;
            }
        }
        return null;
    }

    /**
     * This method was created in VisualAge.
     *
     * @param obj java.lang.Object
     * @return double
     */
    public static int toInteger(final Object obj) {
        int returnInt = 0;
        if (obj != null) {
            if (obj instanceof Boolean) {
                returnInt = toInteger((Boolean) (obj));
            }
            try {
                returnInt = Integer.parseInt(obj.toString());
            } catch (Exception e) {
                returnInt = 0;
            }
        }
        return returnInt;
    }

    public static int toInteger(final Boolean value) {
        int returnInt = 0;
        if (value != null) {
            try {
                returnInt = value ? 1 : 0;
            } catch (Exception e) {
                returnInt = 0;
            }
        }
        return returnInt;
    }

    public static String replace(String str, final String pattern, final String replace) {
        if (str == null || pattern == null || replace == null) {
            return str;
        }

        final StringBuffer result = new StringBuffer();

        int index = str.indexOf(pattern);
        while (index >= 0) {
            result.append(str.substring(0, index));
            result.append(replace);
            str = str.substring(index + pattern.length());
            index = str.indexOf(pattern);
        }

        if (str != null && str.length() > 0) {
            result.append(str);
        }

        return result.toString();
    }

    public static String quote(final Object value) {
        String quote = null;
        if (value != null && ProcessData.isValid(value)) {
            final StringBuffer outString = new StringBuffer();

            /**
             * if the string contains any single quotes, replace them with 2 single quotes for proper insertion...
             */
            if (value.toString().indexOf('\'') == -1) {
                outString.append(value);

            } else {
                int count;
                for (count = 0; count < value.toString().length(); ++count) {
                    outString.append(value.toString().charAt(count));
                    if (value.toString().charAt(count) == '\'') {
                        outString.append('\'');
                    }
                }
            }
            quote = "'" + outString + "'";
        }

        return quote;
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
    }

    public static String join(String s, List<String> approversBCC) {
        return CollectionUtils.join(approversBCC, s);
    }
}
