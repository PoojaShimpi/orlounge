package org.orlounge.service;

import lombok.Getter;
import org.orlounge.factory.DAOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Satyam Soni on 9/15/2015.
 */
@Component
@Getter
public  class AbstractBaseService {

    @Autowired
    private DAOFactory daoFactory;

}
