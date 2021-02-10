package org.orlounge.common;

public enum HospitalType {

    HOSPITAL("Hospital"), AMBULATORY_CENTER("Ambulatory Center");
    private String dispName;

    HospitalType(String dispName) {
        this.dispName = dispName;
    }

    public String getDispName() {
        return dispName;
    }
}
