package com.dianping.customer.report.biz.exceptions;


import java.util.ArrayList;
import java.util.List;

public class OperationResult {

    private static ThreadLocal<OperationResult> OPERATION_RESULT = new ThreadLocal<OperationResult>();

    public static OperationResult getInstance() {
        if (OPERATION_RESULT.get() == null) {
            OPERATION_RESULT.set(new OperationResult());
        }
        return OPERATION_RESULT.get();
    }

    private OperationResult() {

    }

    private List<KeyValuePair> errors = new ArrayList<KeyValuePair>();

    private List<KeyValuePair> informations = new ArrayList<KeyValuePair>();


    public boolean isSuccess() {
        return this.errors.size() == 0;
    }


    public List<KeyValuePair> getErrors() {
        return errors;
    }

    public List<KeyValuePair> getInformations() {
        return informations;
    }

    public String getFormatedErrorMessages() {
        return buildFormatedMessages(this.errors);
    }

    public String getFormatedInformationMessages() {
        return buildFormatedMessages(this.informations);
    }

    private String buildFormatedMessages(List<KeyValuePair> pairs) {
        StringBuilder messageBuilder = new StringBuilder();
        for (KeyValuePair keyValuePair : pairs) {
            messageBuilder.append(keyValuePair.getValue());
            messageBuilder.append(HTMLConstants.SEPERATOR);
        }
        return messageBuilder.toString();
    }

    public boolean containsErrorKey(String key) {

        for (KeyValuePair keyValuePair : this.errors) {

            if (keyValuePair.getKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    public void clearAllMessages() {
        getErrors().clear();
        getInformations().clear();
    }

    public void addErrorMessage(String errorKey) {
        addErrorMessage(errorKey, null, null);
    }

    public void addErrorMessage(String errorKey, String defaultMessage) {
        addErrorMessage(errorKey, null, defaultMessage);
    }

    public void addErrorMessage(String errorKey, List<String> args) {
        addErrorMessage(errorKey, args, null);
    }

    public void addErrorMessage(String errorKey, List<String> args, String defaultMessage) {
        addMessageToPairs(this.errors, errorKey, args, defaultMessage);
    }

    public void addInformationMessage(String key) {
        addInformationMessage(key, null);
    }

    public void addInformationMessage(String key, List<String> args) {
        addMessageToPairs(this.informations, key, args, null);
    }

    private static void addMessageToPairs(List<KeyValuePair> pairs, String key, List<String> args, String defaultMessage) {
        KeyValuePair keyValuePair = null;
        keyValuePair = new KeyValuePair(key, args, defaultMessage);

        if (!containsKey(pairs, key)) {
            pairs.add(keyValuePair);
        }
    }


    private static boolean containsKey(List<KeyValuePair> pairs, String key) {
        for (KeyValuePair pair : pairs) {
            if (pair.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

}
