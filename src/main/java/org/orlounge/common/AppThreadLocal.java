package org.orlounge.common;

/**
 * Created by Satyam Soni on 9/19/2015.
 */
public class AppThreadLocal {
    private static ThreadLocal<UserToken> tokenLocal = new ThreadLocal<UserToken>();
    private static ThreadLocal<UserToken> temp = new ThreadLocal<UserToken>();


    public static void setTokenLocal(UserToken  token){
        tokenLocal.set(token);
    }

    public static UserToken getTokenLocal(){
        return tokenLocal.get();
    }

    public static void  clear(){
        tokenLocal.remove();
        temp.remove();
    }

    public static void setTemp(UserToken  token){
        temp.set(token);
    }

    public static UserToken getTemp(){
        return temp.get();
    }
}
