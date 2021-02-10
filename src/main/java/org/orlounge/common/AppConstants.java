package org.orlounge.common;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by Satyam Soni on 9/26/2015.
 */
public interface AppConstants {

    String ALERT_SITE_STATUS = "siteStatusAlert";


    String BASE_URL ="http://orlounge.com";
    String STATIC_CONTENT_BASE_URL =  BASE_URL + "/resources";

    String ORM_ROLE = "orm";
    String NURSE_ROLE = "nurse";
    String ADMIN_ROLE = "admin";
    String SURGEON_ROLE = "surgeon";
    String ANESTHESIOLOGIST_ROLE = "anesthesiologist";
    String GASTROENTERLOGIST_ROLE = "gastroenterologist";
    String PULMONOLOGIST_ROLE = "pulmonologist";
    String GYNECOLOGIST_ROLE = "gynecologist";
    String OBSTERTRICIAN_ROLE = "obstetrician";
    String RADIOLOGIST_ROLE = "radiologist";
    String RESIDENT_ROLE = "resident";
    String ASSISTANT_ROLE = "assistant";
    String GUEST_ROLE = "guest";
    String STAFF_ROLE = "staff";


    String PROJECT_ACCESS_TYPE_PRIVATE = "PRIVATE";
    String PROJECT_ACCESS_TYPE_PUBLIC = "PUBLIC";
    String PROJECT_ACCESS_TYPE_ROLE = "ROLE";
    String PROJECT_ACCESS_TYPE_USER = "USER";



    String CALL_SCHEDULE_BUCKET_NAME= "CALL_SCHEDULE_BUCKET_NAME";
    String CALL_SCHEDULE_TEMP_DOWNLOAD= "CALL_SCHEDULE_TEMP_DOWNLOAD";
    String S3_ACCESS_KEY_PROP= "S3_ACCESS_KEY";
    String SNS_ACCESS_KEY_PROP= "SNS_ACCESS_KEY";
    String SES_ACCESS_KEY_PROP= "SES_ACCESS_KEY";
    String S3_SECRET_KEY_PROP= "S3_SECRET_KEY";
    String SNS_SECRET_KEY_PROP= "SNS_SECRET_KEY";
    String SES_SECRET_KEY_PROP= "SES_SECRET_KEY";
    String S3_REGION_PROP= "S3_REGION";


    String LOGIN_EVENT = "LOGIN";
    String ATTEMPT_LOGIN_EVENT = "ATTEMPT_LOGIN";
    String LOGOUT_EVENT = "LOGOUT";
    String REGISTER_USER_EVENT = "REGISTER_USER";
    String APPROVED_USER_EVENT = "USER_APPROVED";


    String SMTP_HOST = "SMTP_HOST";
    String SMTP_PORT = "SMTP_PORT";
    String FROM_MAIL = "FROM_MAIL";
    String FROM_PASS = "FROM_MAIL_PASS";


    public static final String JAVA_TEMP_PATH = System.getProperty("java.io.tmpdir");
    public static final String CAC_ENVIRONMENT_VARIABLE = "cacfilespath";
    public static final String DATABASE_PROPERTIES_FILE_NAME = "database.properties";
    public static final String ERROR_MESSAGES_PROPERTIES_FILE_NAME = "errorMessages_en.properties";
    /**
     * Constants for files upload
     */

    public static final String ENCODER_INFO_PROPERTIES_FILE_NAME = "encoderInformation.properties";
    public static final String CODE_ATTRIBUTE_MAPPING_FILE_NAME = "enm/codeAttributeMapping.txt";
    public static final String EXAMINATION_FILE_NAME = "enm/examination";
    public static final String GROUP_ID_MAP_FILE_NAME = "enm/Group_Id_Map";
    public static final String HISTORY_FILE_NAME = "enm/history";
    public static final String MEDICAL_DECISION_MAKING_FILE_NAME = "enm/medicalDecisionMaking";
    public static final String PLACE_OF_SERVIE_MAPPING_FILE_NAME = "enm/PlaceOfServieMapping";
    public static final String TEXT_FILES_FOLDER_PATH_KEY = "MED_DOC_FILE_PATH";
    public static final String PDF_FILES_FOLDER_PATH_KEY = "MED_DOC_PDF_PATH";
    public static final String IMAGE_FILES_FOLDER_PATH_KEY = "MED_DOC_IMG_PATH";
    public static final String LOGO_FILES_FOLDER_PATH_KEY = "UPLOADFILE_LOGO_FOLDER_PATH";
    public static final String PROFILE_FILES_FOLDER_PATH_KEY = "UPLOADFILE_PROFILE_FOLDER_PATH";
    public static final String UPLOADFILE_LOGO_DEFAULT_IMAGE_KEY = "UPLOADFILE_LOGO_DEFAULT_IMAGE";
    public static final String AWS_PATH_KEY = "AWS_PATH";
    public static final String AWS_FOLDER_PATH_KEY = "AWS_FOLDER_PATH";
    public static final String AWS_SCAN_DOC_FOLDER_PATH_KEY = "AWS_SCAN_DOC_FOLDER_PATH";
    public static final String AWS_SCAN_IMG_FOLDER_PATH_KEY = "AWS_SCAN_IMG_FOLDER_PATH";
    public static final String AMAZON_CLOUD_SERVICE_S3_ACCESS_KEY = "AWS_CLOUD_SERVICE_S3_ACCESS_KEY";
    public static final String AMAZON_CLOUD_SERVICE_S3_SECRET_KEY = "AWS_CLOUD_SERVICE_S3_SECRET_KEY";
    public static final String AMAZON_CLOUD_SERVICE_S3_REGION_KEY = "AWS_CLOUD_SERVICE_S3_REGION";
    public static final String SEARCH_SERVICE_URL = "SEARCH_SERVICE_URL";
    public static final String SEARCH_CDI_SERVICE_URL = "SEARCH_CDI_SERVICE_URL";
    public static final String SEARCH_SERVICE_MODULE = "SEARCH_SERVICE_MODULE";
    public static final String SEARCH_CDI_SERVICE_MODULE = "SEARCH_CDI_SERVICE_MODULE";
    public static final String SEARCH_SERVICE_AUTH_TOKEN = "SEARCH_SERVICE_AUTH_TOKEN";
    public static final String SEARCH_CDI_SERVICE_AUTH_TOKEN = "SEARCH_CDI_SERVICE_AUTH_TOKEN";
    public static final String SEARCH_SERVICE_CORE = "SEARCH_SERVICE_CORE";
    public static final String SEARCH_CDI_SERVICE_CORE = "SEARCH_CDI_SERVICE_CORE";
    public static final String SEARCH_DB_RESULT_SIZE = "SEARCH_DB_RESULT_SIZE";
    public static final Boolean LOG_UNSUCCESS = true;
    public static final String USER_SETTING_COMPONENT_NAME_LANDINGPAGE_INSIGHTS = "LANDINGPAGE_INSIGHTS";
    public static final String TOTAL_ENCOUNTER_COUNT = "TOTAL_ENCOUNTER_COUNT";
    public static final String SORT_FIELD = "sortField";
    public static final String SORT_ORDER = "sortOrder";
    public static final String SORT_ORDER_ASCENDING = "asc";
    public static final String SORT_ORDER_DESCENDING = "desc";
    public static final String ROLE_CATEGORY_NAME = "role";
    public static final String ENCOUNTER_STATUS_CATEGORY_NAME = "encounterStatus";
    public static final String UPLOADFILE_LOGO_PURPOSE = "UPLOADFILE_LOGO_PURPOSE";
    public static final String UPLOADFILE_PROFILE_PURPOSE = "UPLOADFILE_PROFILE_PURPOSE";


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

    public static final String DATA_BASE_DATE_FORMAT_WITHTIME = "yyyy-MM-dd HH:mm:ss";

    public static final String DATA_BASE_DATE_FORMAT_WITHOUTTIME = "yyyy-MM-dd";

    public static  final String HH_MM_AM_PM_DD_MMM_YY="HH:mm a dd/MM/yyyy";

    /**
     * Generate outbound
     *
     */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmss.SSS";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";


    /**
     * while downloading file
     */
    public static final String EXCEL_FILE_CONTENT_TYPE = "application/excel";
    public static final String DOC_FILE_CONTENT_TYPE = "application/msword";
    public static final String HTML_FILE_CONTENT_TYPE = "application/html";
    public static final String PDF_FILE_CONTENT_TYPE = "application/pdf";
    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final DecimalFormat FORMAT_AMOUNT_NUMBER = new DecimalFormat("#,###,##0.00");
    public static final DecimalFormat FORMAT_UP_TO_TWO_DECIMAL = new DecimalFormat("0.00");
    public static final DecimalFormat FORMAT_UP_TO_FOUR_DECIMAL = new DecimalFormat("0.0000");
    public static final int VARIANCE_FOR_IN_PROGRESS_TIME_IN_MIN = 10;
    public static final long IN_PROGRESS_SCHEDULER_EXPRESSION = (VARIANCE_FOR_IN_PROGRESS_TIME_IN_MIN * 60 * 1000) / 2L;
    /**
     * password constants
     */
    public static final Random RANDOM = new Random();
    public static final Integer USER_LOCK_MAXIMUM_ATTEMPT = 3;
    public static final Integer USER_LOCK_TIME_IN_MINUTE = 10;
    public static final Integer USER_LOGIN_FAILURE__CONSIDERATION_TIME_IN_MINUTE = 30;
    public static final String USERNAME_SESSION_ATTR = "username_session";
    public static final String PASS_WARNING_SESSION_ATTR = "pass_warning_session";
    public static final String ALREADY_IN_PROGRESS_CASE_WARNING_SESSION_ATTR = "already_inprogress_case_warning_session";
    public static final Integer PASS_WARNING_DISPLAY_INDICATOR = -100;
    public static final Integer PASS_WARNING_DISPLAY_BEFORE_DAYS = 1;
    public static final Integer PASS_MAX_LENGTH = 100;
    public static final Integer PASS_MIN_LENGTH = 8;
    public static final Integer PASS_MAX_OTP_LENGTH = 30;
    public static final String PASS_SPECIAL_CHARCTERS = "!@#$%^&*-_=+?.";
    public static final String LOWER_CASE_CHARS_STRING = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPER_CASE_CHARS_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERIC_CHARS_STRING = "0123456789";
    public static final String ENCOUNTER_SESSION_NAME = "currentEnounterInSession";
    public static final String REDIRECT_KEY_IN_ERROR_MSG = "redirectTo";
    //[a-zA-Z0-9!@#\$%\^\&*?+=._-]{8,}$
    //^(?=.*[!@#\$%\^\&*?+=._-])(?=.*[A-Z]).{8,}$
    public static final String SESSION_LAST_ACCESS_IGNORE_URL = "captureLastActiveForParticularCase";
    public static final int ZERO = 0;
    public static final int ONE = 1;

    //public static final String				CASE_ALLOCATION_MESSAGE								= "cases are allocated successfully.";
    public static final int SEVEN = 7;
    public static final String LIMIT_CONST = "limit";
    public static final String START_CONST = "start";
    public static final String EDIT = "edit";
    public static final String LOGIN_CONST = "login";
    public static final String ADD = "add";
    public static final String ONSITE = "Onsite";
    public static final String OFFSITE = "Offsite";
    public static final String BOTH = "Both";
    public static final String LOGIN_PAGE_REDIRECT = "loginPageRedirect";
    public static final String FILE_ALLOCATION_PAGE_REDIRECT = "fileAllocationRedirect";
    public static final String CODER_HOME_PAGE_REDIRECT = "coderHome";
    public static final String MESSAGE_CENTER_PAGE_REDIRECT = "messageCenter";
    public static final String SUCCESS_MSG = "success";
    public static final String TOTAL_CONST = "total";
    public static final String MALE_GENDER_CONST = "MALE";
    public static final String FEMALE_GENDER_CONST = "FEMALE";
    public static final String UNKNOWN_GENDER_CONST = "UNKNOWN";
    public static final String MALE_GENDER_ABBR_CONST = "M";
    public static final String FEMALE_GENDER_ABBR_CONST = "F";
    public static final String UNKNOWN_GENDER_ABBR_CONST = "U";
    public static final String TIMEZONE_KEY = "HOSPITAL_TimeZone";
    public static final String HOSPITAL_ID = "HOSPITAL_Id";
    public static final String HOSPITAL_NAME = "HOSPITAL_Name";
    public static final String MAX_DNFC_DAYS = "HOSPITAL_MaximumDNFCDays";
    public static final String TARGET_DNFC_DAYS = "HOSPITAL_TargetDNFCDays";
    public static final String DISCHARGE_LABEL = "The patient has been <b>DISCHARGED</b> on <b>%s</b>";
    public static final String SELECTION_LABEL_ALL = "All";
    public static final String SELECTION_LABEL_NONE = "None";
    public static final String SELECTION_LABEL_QUERY_OPPORTUNITY = "Query Opportunity";
    public static final String SELECTION_LABEL_QO_IMPACTING_DRG = "QO + Impacting DRG";
    public static final String SELECTION_FIELD_KEY_IS_SELECTABLE = "is_selectable";
    public static final String SELECTION_FIELD_KEY_HAS_QUERY_OPPORTUNITY = "has_query_opportunity";
    public static final String SELECTION_FIELD_KEY_IS_IMPACTING_DRG = "is_impacting_drg";
    public static final String ACTION_ELEMENT_ASSIGN = "assign";
    public static final String ACTION_ELEMENT_LABEL = "Assign";
    public static final String ACTION_ELEMENT_MARKASIMPORTANT = "markAsImportant";
    public static final String ACTION_ELEMENT_MARKASIMPORTANT_LABEL = "Mark as Important";

    public static final String WORKLIST_CODING_CASES_ALIAS = "Coding Cases";
    public static final String WORKLIST_REVIEW_CASES_ALIAS = "Review Cases";
    public static final String WORKLIST_URL_FOR_START_BUTTON = "checkEncounter";
    public static final String WORKLIST_START_BUTTON_LABEL = "Start Coding";
    //public static final String INSIGHT_CODING_CASES_ALIAS = "CODING";
    //public static final String INSIGHT_REVIEW_CASES_ALIAS = "REVIEW";

    public static final String ENCOUNTER_PRIORITY_HIGH = "High";
    public static final String ENCOUNTER_PRIORITY_LOW = "Low";

    public static final String CASE_ASSIGNED_SUCCESSFULLY= "case(s) assigned successfully.";
    public static final String CASE_ALREADY_ASSIGNED="The cases are already assigned to this user. Please select another";
    public static final String CASE_ASSIGN_ANOTHER_USER="case(s) Assign them to another user.";



    public static final String ITEM_LIST_PAGE_SIZE = "ITEM_LIST_PAGE_SIZE";
    public static final String SORT_BY_CASE_VALUE = "SORT_BY_CASE_VALUE";
    public static final String SORT_BY_LIFO_FIFO = "SORT_BY_LIFO_FIFO";
    public static final String LIFO_SORT = "LIFO";
    public static final String FIFO_SORT = "FIFO";

    public static final String CODE_CATEGORY_ADMITTING = "admitting";
    public static final String CODE_CATEGORY_PRINCIPAL = "principal";
    public static final String CODE_CATEGORY_SECONDARY = "secondary";

    public static final String ACCESS_RIGHT_FULL = "Full";
    public static final String ACCESS_RIGHT_Restricted = "Restricted";
    public static final String ROLE = "role";

    public static final String TYPE_METHOD_GET = "GET";
    public static final String TYPE_METHOD_POST = "POST";
    public static final String KEY_CONTENT_TYPE = "Content-Type";

    public static final String OUTBOUND_URL = "OUTBOUND_HL7_SENDER_URL";

    public static final String DEFAULT_REVENUE_CODE = "0001";

    public static final String POA_YES = "Y";

    static final String STATUS_CODE = "status_code";
    static final String RESPONSE = "response";

    public static final String ACTION = "action";
    public static final String ACTION_CREATE = "create";
    public static final String ACTION_UPDATE = "update";
    public static final String ACTION_VIEW = "view";


    public static final String GLOBAL_FAILURE_MSG = "Seems something went wrong. Please try again.";






}