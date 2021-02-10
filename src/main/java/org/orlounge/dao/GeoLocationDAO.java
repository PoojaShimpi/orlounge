package org.orlounge.dao;

import org.orlounge.bean.GeoLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Satyam Soni on 10/5/2015.
 */
@Component
public interface GeoLocationDAO extends JpaRepository<GeoLocation, Integer> {

    @Query("from GeoLocation where active = 1 and groupId = :groupId order by id desc")
    public List<GeoLocation> getGeoActiveLocationsForGroup(Integer groupId);


    @Query("from GeoLocation order by id desc")
    public List<GeoLocation> getGeoAllLocations();

    @Query("from GeoLocation where id = :id")
    public GeoLocation getGeoLocation(Integer id);


    default GeoLocation deleteGeoLocation(GeoLocation bean) {
        bean.setActive(0);
        return save(bean);
    }
}
