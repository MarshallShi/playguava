package com.dianping.customer.report.biz.exceptions;

import com.dianping.customer.report.biz.utils.Beans;
import org.springframework.context.MessageSource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class KeyValuePair implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 455659471372390409L;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    private String key;

    public List<String> getArgs() {
        return args;
    }

    private List<String> args = new ArrayList<String>();

    private String defaultMessage;

    public KeyValuePair(String key) {
        this(key, null, null);
    }

    public KeyValuePair(String key, String defaultMessage) {
        this(key, null, defaultMessage);
    }

    public KeyValuePair(String key, List<String> args) {
        this(key, args, null);
    }

    public KeyValuePair(String key, List<String> args, String defaultMessage) {
        this.key = key;

        if (args != null) {
            this.args.addAll(args);
        }
        this.args.remove(null);

        this.defaultMessage = defaultMessage;
    }

    public String getValue() {
        MessageSource messageSource = Beans.getBean(MessageSource.class);
        return messageSource.getMessage(this.key, this.args.toArray(), this.defaultMessage, Locale.SIMPLIFIED_CHINESE);
    }
}
