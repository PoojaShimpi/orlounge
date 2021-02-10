package org.orlounge.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Satyam Soni on 9/18/2015.
 */
@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    private static void setContext(ApplicationContext context) {
        AppListener.context = context;
        //AppListener.setContext(context);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        setContext(event.getApplicationContext());
    }
}
