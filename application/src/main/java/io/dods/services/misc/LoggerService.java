package io.dods.services.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Richard Gottschalk
 */
@Service
public class LoggerService {

    private String getFileName(Class aClass) {
        if (aClass.getPackage().getName().contains(".parser")) {
            return "parser";
        } else if (aClass.getPackage().getName().contains(".api")) {
            return "api";
        }
        return aClass.getName();
    }

    public void info(Class aClass, String text) {
        Logger logger = LoggerFactory.getLogger(getFileName(aClass));
        logger.info(text);
    }

    public void debug(Class aClass, String text) {
        Logger logger = LoggerFactory.getLogger(getFileName(aClass));
        logger.debug(text);
    }

    public void error(Class aClass, String text) {
        Logger logger = LoggerFactory.getLogger(getFileName(aClass));
        logger.error(text);
    }

    public void warn(Class aClass, String text) {
        Logger logger = LoggerFactory.getLogger(getFileName(aClass));
        logger.warn(text);
    }

    public void error(Class aClass, Throwable throwable) {
        StringBuilder shortStackTrace = new StringBuilder();
        for (int i = 0; i < 5 && i < throwable.getStackTrace().length; i++) {
            if (i > 0) shortStackTrace.append('\n');
            shortStackTrace.append(throwable.getStackTrace()[i]);
        }

        String msg = String.format("%s: %s\n%s",
                throwable.getClass().getName(),
                throwable.getMessage(),
                shortStackTrace.toString());

        error(aClass, msg);
    }
}
