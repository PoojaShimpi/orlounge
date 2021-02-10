package org.orlounge.common.util;

import org.orlounge.common.AppConstants;
import org.orlounge.common.UserToken;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Satyam Soni on 9/16/2015.
 */
public class ProcessData {

    public static Date cloneDateObject(final Date date) {
        Date locDate = null;
        if (ProcessData.isValid(date)) {
            locDate = (Date) date.clone();
        }
        return locDate;
    }


    public static void setValueInMap(final Map map, final Object key, final Object value, final Object notValidValue) {
        if (isValid(value)) {
            map.put(key, value);
        } else {
            if (notValidValue == null) {
                return;
            }
            map.put(key, notValidValue);
        }
    }

    public static void setValueInMap(final Map map, final Object key, final Object value) {
        setValueInMap(map, key, value, "");
    }

    public static void setValueInJson(final Map map, final Object key, final Object value, final Object notValidValue) {

        if (isValid(value)) {
            map.put(key, value);
        } else {
            if (notValidValue == null) {
                return;
            }
            map.put(key, notValidValue);
        }
    }

    public static void setValueInJson(final Map map, final Object key, final Object value) {
        setValueInJson(map, key, value, "");
    }

    public static Object joinTwoFields(final Object firstFiled, final Object secondField, final String saparator) {
        Object obj;
        if (isValid(firstFiled) && isValid(secondField)) {
            obj = firstFiled + saparator + secondField;
        } else if (isValid(firstFiled)) {
            obj = firstFiled;
        } else if (isValid(secondField)) {
            obj = secondField;
        } else {
            obj = null;
        }
        return obj;
    }

    public static String joinFields(final String saparator, final Object... fields) {
        String result = "";
        final StringBuffer resultBuffer = new StringBuffer();
        for (final Object field : fields) {
            if (isValid(field)) {
                if(field instanceof  Collection){
                    resultBuffer.append(StringUtils.collectionToDelimitedString((Collection) field, saparator));
                } else {
                    resultBuffer.append(field);
                }

                resultBuffer.append(saparator);
            }
        }
        result = resultBuffer.toString();
        if (!result.isEmpty()) {
            result = result.substring(0, result.length() - saparator.length());
        }
        return result;
    }

    public static String joinTwoFields(final String firstFiled, final String secondField, final String saparator) {
        String str;
        if (isValid(firstFiled) && isValid(secondField)) {
            str = firstFiled + saparator + secondField;
        } else if (isValid(firstFiled)) {
            str = firstFiled;
        } else if (isValid(secondField)) {
            str = secondField;
        } else {
            str = null;
        }
        return str;
    }

    /**
     * This method joins first name and last name for users using list which contains map as first name and last name.
     *
     * @param separator
     * @param list
     * @return
     */
    public static StringBuffer joinFields(final String separator, final List<Map<String, Object>> list) {
        StringBuffer result = new StringBuffer();
        Iterator<String> itr;
        for (final Map<String, Object> map : list) {
            if (isValid(map)) {
                result.append(separator);
                itr = map.keySet().iterator();
                while (itr.hasNext()) {
                    result.append(' ').append(map.get(itr.next()));
                }
            }
        }
        if (result.length() > 0) {
            result = result.replace(0, separator.length(), "");
        }
        return result;
    }

    public static StringBuffer joinFields(final String separator, final Set<String> set) {
        StringBuffer result = new StringBuffer();
        for (final String value : set) {
            if (isValid(value)) {
                result.append(separator);
                result.append(' ').append(value);
            }
        }
        if (result.length() > 0) {
            result = result.replace(0, separator.length(), "");
        }
        return result;
    }

    public static Map getSuccessJson(final boolean successFlag) {
        final Map jsonObject = new HashMap();
        jsonObject.put("success", successFlag);
        return jsonObject;
    }

    public static Map<String, Boolean> getSuccessMap(final boolean successFlag) {
        final Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put("success", successFlag);
        return map;
    }

    public static Map<String, Object> getSuccessMap(final boolean successFlag, final String message) {
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", successFlag);
        map.put("message", message);
        return map;
    }

    public static Integer getOriginalIntegerId(final String idStr) {
        if (isValid(idStr)) {
            final String[] idArray = idStr.split("_");
            return Integer.parseInt(idArray[idArray.length - 1]);
        }
        return null;
    }

    /**
     * get codeType from id with prefix.
     * <p/>
     * example:: if I pass DS_1921 then I will get Diagnosis because DS is indication of Diagnosis.
     */


    public static Long getOriginalLongId(final String idStr) {
        Long locLong = null;
        if (idStr != null) {
            final String[] idArray = idStr.split("_");
            locLong = Long.parseLong(idArray[idArray.length - 1]);
        }
        return locLong;

    }


    public static String getIdKeyChar(final Object anyId) {
        return getIdKeyChar(getString(anyId));
    }

    public static String getIdKeyChar(final String anyId) {
        return anyId.split("_")[0];
    }

    public static String generateIdFromKey(final Object idObj, final String codeKeyChar) {
        return codeKeyChar + "_" + getString(idObj);
    }

    public static boolean isCode(final String code) {
        final Pattern pattern = Pattern.compile("[A-Z]{0,1}\\d");
        final Matcher matcher = pattern.matcher(code);
        return matcher.find();
    }

    public static String getTxtFileNameFromXml(String fileName) {
        String fName;
        if (isValid(fileName) && fileName.length() >= 5) {
            if (".xml".equals(fileName.substring(fileName.length() - 4, fileName.length()))) {
                fileName = fileName.substring(0, fileName.length() - 4);
            }

            fName = fileName;
        } else {
            fName = null;
        }

        return fName;
    }

	/*public static String getFileContent(final ServletContext contex, String fileName)
    {
		String fName;
		try
		{
			fileName = getTxtFileNameFromXml(fileName);
			final String docContent = EzCacUtils.getFullFileText(getFile(contex, fileName));
			//TODO uncomment to get all files from Amazone.done by charvee
//			AmazonS3Service.getInstance(true, EzCacConstants.AMAZON_CLOUD_SERVICE_S3_ACCESS_KEY_VALUE, EzCacConstants.AMAZON_CLOUD_SERVICE_S3_SECRET_KEY_VALUE, EzCacConstants.AMAZON_CLOUD_SERVICE_S3_REGION);
//			final String docContent = AmazonS3Service.getTextFile(EzCacConstants.AWS_FOLDER_PATH, fileName);
			fName = docContent;
		}
		catch (Exception exception)
		{
			LOG.error( exception);
			fName = null;
		}
		return fName;
	}*/

/*

    public static String getFileContent(String fileName, Map masterConfig, Map tenantConfig) {
        String fName;
        try {
            fileName = getTxtFileNameFromXml(fileName);
            final String docContent = EzCacUtils.getFullFileText(getFile(fileName, masterConfig, tenantConfig));
            //TODO uncomment to get all files from Amazone.done by charvee
//			AmazonS3Service.getInstance(true, EzCacConstants.AMAZON_CLOUD_SERVICE_S3_ACCESS_KEY_VALUE, EzCacConstants.AMAZON_CLOUD_SERVICE_S3_SECRET_KEY_VALUE, EzCacConstants.AMAZON_CLOUD_SERVICE_S3_REGION);
//			final String docContent = AmazonS3Service.getTextFile(EzCacConstants.AWS_FOLDER_PATH, fileName);
            fName = docContent;
        } catch (Exception exception) {
            exception.printStackTrace();
            fName = null;
        }
        return fName;
    }

    public static String getFileContent(final File file) {
        String docCon = null;
        try {
            final String docContent = EzCacUtils.getFullFileText(file);
            docCon = docContent;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return docCon;
    }
*/

   /* public static String getFileContent(final String file) {
        return getFileContent(new File(file));
    }*/



    /*
    // TODO : Remove this method from here.. Make a separate Service for this.
        /** Replaced for AWS files done by charvee
         * @throws InterruptedException
         * @throws AmazonClientException
         * @throws AmazonServiceException */
/*
    public static File getFile(final String fileName, Map masterConfig, Map<String, String> tenantConfig) throws InterruptedException {
        final String filePath = getFilePath(fileName, tenantConfig);

        final File file = new File(filePath);

        try {
            if (!file.exists() || file.length() == 0) {
                final String isAWSPath = ProcessData.getString(tenantConfig.get(EzCacConstants.AWS_PATH_KEY));
                if ("true".equalsIgnoreCase(isAWSPath)) {
                    String accessKey = ProcessData.getString(masterConfig.get(EzCacConstants.AMAZON_CLOUD_SERVICE_S3_ACCESS_KEY));
                    String secretKey = ProcessData.getString(masterConfig.get(EzCacConstants.AMAZON_CLOUD_SERVICE_S3_SECRET_KEY));
                    String regionKey = ProcessData.getString(masterConfig.get(EzCacConstants.AMAZON_CLOUD_SERVICE_S3_REGION_KEY));
                    AmazonS3Service amazon = AmazonS3Service.getInstance(true, accessKey, secretKey, regionKey);

                    String awsFolderPath = ProcessData.getString(tenantConfig.get(EzCacConstants.AWS_FOLDER_PATH_KEY));

                    amazon.downloadFile(awsFolderPath, fileName, filePath);
                }
            }
            return file;
        } catch (Exception ex) {
            ex.printStackTrace();
            return file;
        }

    }
*/

    /*
 /* // TODO : Remove this method from here.. Make a separate Service for this.
      *//** Replaced for AWS files done by charvee
       * @throws InterruptedException
       * @throws AmazonClientException
       * @throws AmazonServiceException *//*
    public static File getScannedDocFile(final String fileName, Map masterConfig, Map<String, String> tenantConfig) throws InterruptedException {
        final String filePath = getPDFFilePath(fileName, tenantConfig);

        final File file = new File(filePath);

        try {
            if (!file.exists() || file.length() == 0) {
                final String isAWSPath = ProcessData.getString(tenantConfig.get(EzCacConstants.AWS_PATH_KEY));
                if ("true".equalsIgnoreCase(isAWSPath)) {
                    String accessKey = ProcessData.getString(masterConfig.get(EzCacConstants.AMAZON_CLOUD_SERVICE_S3_ACCESS_KEY));
                    String secretKey = ProcessData.getString(masterConfig.get(EzCacConstants.AMAZON_CLOUD_SERVICE_S3_SECRET_KEY));
                    String regionKey = ProcessData.getString(masterConfig.get(EzCacConstants.AMAZON_CLOUD_SERVICE_S3_REGION_KEY));
                    AmazonS3Service amazon = AmazonS3Service.getInstance(true, accessKey, secretKey, regionKey);

                    String awsFolderPath = ProcessData.getString(tenantConfig.get(EzCacConstants.AWS_SCAN_DOC_FOLDER_PATH_KEY));

                    amazon.downloadFile(awsFolderPath, fileName, filePath);
                }
            }
            return file;
        } catch (Exception ex) {
            ex.printStackTraace();
            return file;
        }

    }
*/
 /*   public static File getScannedImageFile(final String fileName, Map masterConfig, Map<String, String> tenantConfig) throws InterruptedException {
        final String filePath = getImageFilePath(fileName, tenantConfig);

        final File file = new File(filePath);

        try {
            if (!file.exists() || file.length() == 0) {
                final String isAWSPath = ProcessData.getString(tenantConfig.get(EzCacConstants.AWS_PATH_KEY));
                if ("true".equalsIgnoreCase(isAWSPath)) {
                    String accessKey = ProcessData.getString(masterConfig.get(EzCacConstants.AMAZON_CLOUD_SERVICE_S3_ACCESS_KEY));
                    String secretKey = ProcessData.getString(masterConfig.get(EzCacConstants.AMAZON_CLOUD_SERVICE_S3_SECRET_KEY));
                    String regionKey = ProcessData.getString(masterConfig.get(EzCacConstants.AMAZON_CLOUD_SERVICE_S3_REGION_KEY));
                    AmazonS3Service amazon = AmazonS3Service.getInstance(true, accessKey, secretKey, regionKey);

                    String awsFolderPath = ProcessData.getString(tenantConfig.get(EzCacConstants.AWS_SCAN_IMG_FOLDER_PATH_KEY));

                    amazon.downloadFile(awsFolderPath, fileName, filePath);
                }
            }
            return file;
        } catch (Exception ex) {
            ex.printStackTraace();
            return file;
        }

    }

*/
    /**
     * Get boolean from object.
     *
     * @param object
     * @return
     */
    public static Boolean getBoolean(final Object object) {
        if (object == null) {
            return null;
        } else if (object instanceof Boolean) {
            return (Boolean) object;
        } else if (object instanceof String) {
            return Boolean.valueOf((String) object);
        } else {
            return null;
        }
    }

    /**
     * get integer from object
     */
    public static Integer getInteger(final Object obj) {
        if (obj == null) {
            return null;
        } else {
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
            if (obj instanceof Double) {
                return (int) Math.round(getDouble(obj));
            }
            if (obj instanceof Float) {
                return Math.round(getFloat(obj));
            }
            if (obj instanceof String) {
                try {
                    if (isValid(obj)) {
                        return Integer.parseInt((String) obj);
                    } else {
                        return null;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return null;
                }
            } else {
                try {
                    return Integer.parseInt(obj.toString());
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return null;
                }
            }
        }
    }

    public static Float getFloat(final Object obj) {

        if (obj == null) {
            return null;
        } else {
            if (obj instanceof Float) {
                return (Float) obj;
            }
            if (obj instanceof String) {
                try {
                    return Float.parseFloat((String) obj);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return null;
                }
            } else {
                try {
                    return Float.parseFloat(obj.toString());
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return null;
                }
            }
        }
    }

    /**
     * get integer from object
     */
    public static Long getLong(final Object obj) {
        if (isValid(obj)) {
            if (obj instanceof Long) {
                return (Long) obj;
            }
            if (obj instanceof String) {
                try {
                    return Long.parseLong((String) obj);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return null;
                }
            } else {
                try {
                    return Long.parseLong(obj.toString());
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return null;
                }
            }

        } else {
            return null;
        }
    }

    /**
     * get double from object
     */
    public static Double getDouble(final Object obj) {
        if (isValid(obj)) {
            if (obj instanceof Double) {
                return (Double) obj;
            }
            if (obj instanceof String) {
                try {
                    return Double.parseDouble((String) obj);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return null;
                }
            } else {
                try {
                    return Double.parseDouble(obj.toString());
                } catch (Exception exception) {
                    exception.printStackTrace();
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    /**
     * get string from object
     */
    public static String getString(final Object obj) {

        return getString(obj, true);
    }

    /**
     * get string from object
     */
    public static String nullToEmpty(final Object obj) {

        return Objects.isNull(obj) ? ""  : getString(obj) ;
    }

    /**
     * get string from object
     */
    public static String getString(final Object obj, final boolean isEmptyStringAccepted) {
        String str;
        if ((isEmptyStringAccepted && obj == null) || !isEmptyStringAccepted && !isValid(obj)) {
            str = null;
        } else {
            if (obj instanceof String) {
                str = (String) obj;
            } else {
                str = obj.toString();
            }
        }
        return str;
    }

    /**
     * to check whether this object is valid or not.
     */
    public static boolean isValid(final Object obj) {
        if (obj == null) {
            return false;
        } else {
            if (obj instanceof String) {
                final String str = (String) obj;
                return !(str.isEmpty() || "null".equals(str));
            } else if (obj instanceof Map) {
                final HashMap json = (HashMap) obj;
                if (json == null || json.isEmpty()) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * check whether map is null or empty or has elements.
     *
     * @param map
     * @return true if map has elements and false if map is null or empty.
     */
    public static boolean isValidMap(final Map<?, ?> map) {
        boolean booleanValue;
        if (null == map || map.isEmpty()) {
            booleanValue = false;
        } else {
            booleanValue = true;
        }
        return booleanValue;
    }

    /**
     * check whether set is null or empty or has elements.
     *
     * @param set
     * @return true if set has elements and false if set is null or empty.
     */
    public static boolean isValidSet(final Set<?> set) {
        boolean boolValue;
        if (null == set || set.isEmpty()) {
            boolValue = false;
        } else {
            boolValue = true;
        }
        return boolValue;
    }

    /**
     * Check weather String array is neither null nor empty.
     *
     * @param array
     * @return
     */
    public static boolean isValidStringArray(final String[] array) {
        boolean isValid = true;
        if (array == null || array.length == 0) {
            isValid = false;
        }
        return isValid;
    }

    public static String getRequestParamsString(final Map<String, String> requestKeyValueMap) {
        final StringBuilder finalParams = new StringBuilder();
        final Set<Map.Entry<String, String>> entrySet = requestKeyValueMap.entrySet();
        for (final Map.Entry<String, String> key : entrySet) {
            finalParams.append(key.getKey() + "=" + key.getValue() + "&");
        }


        finalParams.replace(finalParams.length() - 1, finalParams.length(), "");
        return finalParams.toString();
    }

    public static Date getDate(final Object object) {
        if (!isValid(object)) {
            return null;
        }
        if (object instanceof Timestamp) {
            return new Date(((Timestamp) object).getTime());
        }
        if (object instanceof Date) {
            return ((Date) object);
        }
        if (object instanceof String) {
            try {
                return new SimpleDateFormat(DateContent.YYYY_MM_DD_HH_MM_SS).parse(((String) object));
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        }
        return null;
    }

    public static Date getDate(final Object object, final String dateFormat) {
        if (!isValid(object)) {
            return null;
        }
        if (object instanceof Timestamp) {
            return new Date(((Timestamp) object).getTime());
        }
        if (object instanceof Date) {
            return ((Date) object);
        }
        if (object instanceof String) {
            try {
                return new SimpleDateFormat(dateFormat).parse(((String) object));
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        }
        return null;
    }


    public static Integer getIntegerValueOfBooleanString(final String value) {
        Integer integer = 0;
        if (!StringUtil.isEmptyString(value) && "true".equals(value)) {
            integer = 1;
        }
        return integer;
    }

    public static void incrementAndSetMapValue(final Map<String, Integer> map, final String key) {
        incrementAndSetMapValue(map, key, 1);
    }

    public static void incrementAndSetMapValue(final Map<String, Integer> map, final String key, final Integer incrementValue) {
        Integer value = map.get(key);
        if (value == null) {
            value = 0;
        }
        value = value + incrementValue;
        map.put(key, value);
    }

    /**
     * Convert value upto 2 decimal
     *
     * @param value
     * @return Double if value is valid, else 0D
     */
    public static Double convertIntoTwoPointDecimal(final Object value) {
        return convertIntoDecimal(value, 2);
    }

    /**
     * Convert value upto specified number of decimals
     *
     * @param value
     * @param noOfDecimal
     * @return Double if value is valid, else 0D
     */
    public static Double convertIntoDecimal(final Object value, final int noOfDecimal) {
        Double decimal = 0D;
        final Double val = ProcessData.getDouble(value);
        if (ProcessData.isValid(val)) {
            decimal = ProcessData.division(Math.round(val * Math.pow(10, noOfDecimal)), Math.pow(10, noOfDecimal));
        }
        return decimal;
    }

    /**
     * @param milliSeconds
     * @return
     */

    public static Long convertMilliSecondsIntoDays(final Long milliSeconds) {
        Long noOfDays = 0L;
        final Double miliSeconds = ProcessData.getDouble(milliSeconds);
        if (ProcessData.isValid(miliSeconds)) {
            noOfDays = Math.round(miliSeconds / (1000 * 60 * 60 * 24));
        }
        return noOfDays;
    }

    /**
     * get division
     *
     * @param numerator   - Long
     * @param denominator - Long
     * @return quotient - Double
     */
    public static Double division(final Object numerator, final Object denominator) {
        Double divisionDouble = 0D;
        final Double numeratorDouble = ProcessData.getDouble(numerator);
        final Double denominatorDouble = ProcessData.getDouble(denominator);
        if (ProcessData.isValid(numeratorDouble) && ProcessData.isValid(denominatorDouble) && denominatorDouble != 0D) {
            divisionDouble = new Double(numeratorDouble / denominatorDouble);
        }
        return divisionDouble;
    }

    /**
     * This method return given integer number into given numOfDigits with 0 as prefix. (ex. Integer 5 for 4 digits result into 0005)
     *
     * @param number
     * @param numOfDigits
     * @return
     */
    public static String getIntegerInDesireDigits(final Integer number, final Integer numOfDigits) {
        String result = "";
        if (isValid(number) && isValid(numOfDigits)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("%0");
            stringBuffer.append(numOfDigits);
            stringBuffer.append("d");
            result = String.format(stringBuffer.toString(), number);
        }

        return result;
    }

    public static boolean isValidCollection(final Collection<?> collection) {
        return null != collection && 0 < collection.size() && !collection.isEmpty();
    }

    /**
     * Calculate percentage if both are valid and denominator is not 0
     *
     * @param value      - object
     * @param totalCount - object
     * @return Percentage - Double
     */
    public static Double calculatePercentage(final Object value, final Object totalCount) {
        Double percentage = 0D;
        final Double val = getDouble(value);
        final Double totalCnt = getDouble(totalCount);
        if (ProcessData.isValid(val) && ProcessData.isValid(totalCnt) && totalCnt != 0D) {
            percentage = new Double((val * 100) / totalCnt);
        }
        return percentage;
    }

    public static Integer calculatePercentageInteger(final Long value, final Long totalCount) {
        Integer percentage = 0;
        if (ProcessData.isValid(totalCount) && isValid(value) && totalCount != 0) {
            final Double division = calculatePercentage(value, totalCount);
            percentage = (int) Math.round(division);
        }
        return percentage;
    }

    /**
     * validate json array. If it is null or empty return false.
     *
     * @param jsonArray
     * @return true/false
     */
    public static boolean isValidJSonArray(final List jsonArray) {
        Boolean isValidJsonArray = true;
        if (jsonArray == null) {
            isValidJsonArray = false;
        } else {
            if (jsonArray.isEmpty()) {
                isValidJsonArray = false;
            }
        }
        return isValidJsonArray;
    }

    /**
     *
     * @param filter
     * @return
     */

	/*public static Boolean checkCodingSystemFilter(final ReportFilterCommonBean filter)
    {
		Boolean isICD9CodingSystem;
		if (ProcessData.isValid(filter) && ProcessData.isValidCollection(filter.getCodingSystem()))
		{
			if (filter.getCodingSystem().contains(CodeConstants.ICD9_CODING_SYSTEM))
			{
				isICD9CodingSystem = true;
			}
			else if (filter.getCodingSystem().contains(CodeConstants.ICD10_CODING_SYSTEM))
			{
				isICD9CodingSystem = false;
			}
			else
			{
				throw new ReportNotExistException(900002, new Object[] { "" });
			}
		}
		else
		{
			throw new ReportNotExistException(900002, new Object[] { "" });
		}
		return isICD9CodingSystem;
	}*/

    /**
     * retrieve redirect url from error message written in properties file and return it
     */
    /*public static Map<String, String> getRedirectStringAndMessage(final String message) {
        final Map<String, String> map = new HashMap<String, String>();
        String messageClone = message;
        final StringBuffer buffer = new StringBuffer();
        if (StringUtil.containsSubString(message, EzCacConstants.REDIRECT_KEY_IN_ERROR_MSG)) {
            int redirectIndex = message.indexOf(EzCacConstants.REDIRECT_KEY_IN_ERROR_MSG);
            if (redirectIndex - 1 >= EzCacConstants.ONE) {
                messageClone = message.substring(0, redirectIndex - 1);
            }
            redirectIndex += 3;
            redirectIndex += EzCacConstants.REDIRECT_KEY_IN_ERROR_MSG.length();
            while (redirectIndex < message.length() && message.charAt(redirectIndex) != ' ') {
                buffer.append(message.charAt(redirectIndex++));
            }
            map.put(EzCacConstants.REDIRECT_KEY_IN_ERROR_MSG, buffer.toString());
        }
        if (message != null && message.trim().startsWith(EzCacConstants.REDIRECT_KEY_IN_ERROR_MSG)) {
            map.put("message", "");
        } else {
            map.put("message", messageClone);
        }
        return map;
    }*/

    public static Set<Integer> convertStringArraytoIntegerSet(final String[] stringArray) {
        final Set<Integer> convertedSet = new HashSet<Integer>();
        if (stringArray != null && stringArray.length > 0) {
            for (final String value : stringArray) {
                convertedSet.add(ProcessData.getInteger(value));
            }
        }
        return convertedSet;
    }


    public static List<Integer> convertStringArraytoIntList(String[] sarray) {
        List<Integer> intList = null;
        if (sarray != null) {
            intList = new ArrayList<Integer>();
            for (int i = 0; i < sarray.length; i++) {
                intList.add(Integer.parseInt(sarray[i]));
            }
        }
        return intList;
    }

    /**
     * Union of two arraylist
     *
     * @param list1 first list
     * @param list2 second list
     * @return union list of first and second list
     */
    public static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();
        set.addAll(list1);
        set.addAll(list2);
        return new ArrayList<T>(set);
    }

    /**
     * Inter section of two list
     *
     * @param list1 first list
     * @param list2 second list
     * @return inter section of first and second list
     */
    public static <T> List<T> interSection(List<T> list1, List<T> list2) {
        if (!ProcessData.isValidCollection(list1) || !ProcessData.isValidCollection(list2)) {
            return new ArrayList<T>();
        } else {
            List<T> temp = (List<T>) ((ArrayList<T>) list1).clone();
            temp.retainAll(list2);
            return temp;
        }
    }

    /**
     * Get Deleted row from passed old and new list
     *
     * @param list1 old list
     * @param list2 new list
     * @return list of deleted elements
     */
    public static <T> List<T> getDelRowIds(List<T> list1, List<T> list2) {
        if (!ProcessData.isValidCollection(list1)) {
            return new ArrayList<T>();
        } else {
            List<T> temp = (List<T>) ((ArrayList<T>) list1).clone();
            if (ProcessData.isValidCollection(list2)) {
                temp.removeAll(list2);
            } else {
                return temp;
            }
            return removeDuplicateListelements(temp);
        }
    }

    /**
     * Remove duplicate elements from list
     *
     * @param list1 list
     * @return processed list
     */
    public static <T> List<T> removeDuplicateListelements(List<T> list1) {
        Set<T> set1 = new LinkedHashSet<T>();
        set1.addAll(list1);
        list1.clear();
        list1.addAll(set1);
        return list1;
    }

    /**
     * Replace char in string
     *
     * @param text            text
     * @param replaceChar     replace Chara
     * @param replaceWithChar replace With Character
     * @return newString text with replaced character
     */
    public static String replaceString(String text, String replaceChar, String replaceWithChar) {
        String newString = text.replaceAll(replaceChar, replaceWithChar);
        return newString;
    }

    public static boolean checkUserPermission(UserToken token, Integer inputId) {
        if(token != null) {
            if(token.isAdmin()) {
                return token.isAdmin();
            } else if (token.isLSA()) {
                return token.isLSA();
            } else if(inputId != null) {
                return (Objects.equals(inputId, token.getUserId()));
            }
        }
        return false;
    }

    public static String getAction(boolean isView){
        return isView ? AppConstants.ACTION_UPDATE : AppConstants.ACTION_VIEW;
    }

}
