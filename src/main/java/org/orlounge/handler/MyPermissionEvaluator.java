package org.orlounge.handler;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Satyam Soni on 8/18/2015.
 */
@Component
public class MyPermissionEvaluator implements PermissionEvaluator {


    @Override
    public boolean hasPermission(Authentication authentication, Object domain, Object per) {
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return true;
    }

}
