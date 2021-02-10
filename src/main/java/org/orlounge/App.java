package org.orlounge;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@Slf4j
public class App {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView errorPage(Exception e){
        log.error("Error", e);
        return new ModelAndView("jsp/error-main");
    }


}
