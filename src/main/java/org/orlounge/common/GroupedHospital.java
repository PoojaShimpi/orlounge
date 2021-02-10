package org.orlounge.common;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
@Getter
@Setter
public class GroupedHospital {
    private String stateName;
    private Integer stateId;
    private String stateCode;

    private List<HospitalInfo> list = new ArrayList<HospitalInfo>();


    public void addHospital(String hospitalName, Integer hospitalId, HospitalType type) {
        list.add(new HospitalInfo(hospitalId, hospitalName, type.getDispName()));
    }

    @Getter
    @Setter
    public static class HospitalInfo {
        private Integer hospitalId;
        private String hospitalName;
        private String hospitalType;
        public HospitalInfo(Integer hospitalId, String hospitalName, String hospitalType) {
            this.hospitalId = hospitalId;
            this.hospitalName = hospitalName;
            this.hospitalType = hospitalType;
        }

    }
}
