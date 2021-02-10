package org.orlounge.business;

import org.orlounge.bean.ConfigBean;
import org.orlounge.factory.BusinessFactory;
import org.orlounge.factory.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Satyam Soni on 9/15/2015.
 */
public class AbstractBaseBusiness {
    private static ConcurrentHashMap<String, String> configMap = null;


    public Map<String,String> getConfigMap(){
        configMap = null;
        if(configMap != null){
            return configMap;
        } else {

            List<ConfigBean> bean = getServiceFactory().getLoginService().getConfig();
            configMap = new ConcurrentHashMap<>();
            for(ConfigBean c : bean){
                configMap.putIfAbsent(c.getKey(), c.getValue());
            }
            return configMap;
        }

    }

    @Autowired
    private BusinessFactory businessFactory;

    public BusinessFactory getBusinessFactory() {
        return businessFactory;
    }

    public void setBusinessFactory(BusinessFactory businessFactory) {
        this.businessFactory = businessFactory;
    }

    @Autowired
    private ServiceFactory serviceFactory;

    public ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }
}
