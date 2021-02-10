package org.orlounge.common.util;


import javax.servlet.http.HttpSession;


public class MessageUtils {
    private String message;
    private boolean isSuccess;

    private MessageUtils(String message, boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public static void success(HttpSession session, String message) {
        dumpMessage(session, message, true);
    }

    public static void failure(HttpSession session, String message) {
        dumpMessage(session, message, false);
    }

    public static void dumpMessage(HttpSession session, String message, boolean isSuccess) {
        session.setAttribute("message", new MessageUtils(message, isSuccess));
    }

    public static String getMessage(HttpSession session) {
        MessageUtils messageUtils = (MessageUtils) session.getAttribute("message");
        if (messageUtils != null) {
            return messageUtils.message;
        } else {
            return null;
        }
    }

    public static boolean isSuccess(HttpSession session) {
        MessageUtils messageUtils = (MessageUtils) session.getAttribute("message");
        if (messageUtils != null) {
            return messageUtils.isSuccess;
        } else {
            return true;
        }
    }
}
