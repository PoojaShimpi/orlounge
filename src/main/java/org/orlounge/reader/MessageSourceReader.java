package org.orlounge.reader;

import org.springframework.context.MessageSource;

/**
 * Created by Satyam Soni on 9/20/2015.
 */
public class MessageSourceReader {


    /**
     * Message Source that would be single point access.
     */
    private static MessageSource source;

    /**
     * Default constructor
     */
    public MessageSourceReader() {

    }

    public static MessageSource getSource() {
        return source;
    }

    /**
     * @param messageSource the messageSource to set
     */
    public void setMessageSource(final MessageSource messageSource) {
        source = messageSource;
    }
}
