package org.orlounge.exception;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Satyam Soni on 9/15/2015.
 */
public class ORException extends RuntimeException {
    private Throwable ex;
    private int errorCode = 0;
    private static Properties prop;
    static {
        try{
            InputStream is = ORException.class.getClassLoader().getResourceAsStream("application.properties");
            prop = new Properties();
            prop.load(is);
            is.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public ORException(Throwable ex, int code){
        super();
        this.ex = ex;
        this.errorCode = code;
    }
    public ORException(Throwable ex){
        super();
        this.ex = ex;
    }

    public ORException( int code){
        super();
        this.errorCode = code;
    }

    public String getMessage(){
        return prop.getProperty(""+errorCode);
    }

    public StackTraceElement[] getStackTrace() {
        if(ex!=null){
          return  ex.getStackTrace();
        } else {
            return super.getStackTrace();
        }
    }


    public Throwable getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
