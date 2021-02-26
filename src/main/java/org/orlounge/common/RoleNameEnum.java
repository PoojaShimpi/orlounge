package org.orlounge.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Satyam Soni on 1/24/2016.
 */
public abstract class RoleNameEnum {
    public static final Map<String, String> ROLE_NAME_MAP = new HashMap<>();

    static {
        ROLE_NAME_MAP.put(AppConstants.ADMIN_ROLE, "Administrator");
        ROLE_NAME_MAP.put(AppConstants.ORM_ROLE, "OR Manager");
        ROLE_NAME_MAP.put(AppConstants.NURSE_ROLE, "Nurse");
        ROLE_NAME_MAP.put(AppConstants.SURGEON_ROLE, "Surgeon");
        ROLE_NAME_MAP.put(AppConstants.ANESTHESIOLOGIST_ROLE, "Anesthesiologist");
        ROLE_NAME_MAP.put(AppConstants.GASTROENTERLOGIST_ROLE, "Gastroenterologist");
        ROLE_NAME_MAP.put(AppConstants.PULMONOLOGIST_ROLE, "Pulmonologist");
        ROLE_NAME_MAP.put(AppConstants.GYNECOLOGIST_ROLE, "Gynecologist");
        ROLE_NAME_MAP.put(AppConstants.OBSTERTRICIAN_ROLE, "Obstetricians");
        ROLE_NAME_MAP.put(AppConstants.RADIOLOGIST_ROLE, "radiologist");
        ROLE_NAME_MAP.put(AppConstants.RESIDENT_ROLE, "Resident");
        ROLE_NAME_MAP.put(AppConstants.ASSISTANT_ROLE, "Surgical Assistant");
        ROLE_NAME_MAP.put(AppConstants.DIRECTOR_OF_SURGERY, "Director of Surgery");
    }

}
