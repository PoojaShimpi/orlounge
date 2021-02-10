package org.orlounge.config;

import org.orlounge.bean.ConfigBean;
import org.orlounge.dao.LoginDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class DynamicConfig {

    @Bean
    public Map<String, String> getConfigMap(LoginDAO loginDAO) {
        return loginDAO.getConfigs().stream().collect(Collectors.toMap(ConfigBean::getKey, ConfigBean::getValue));
    }
}
