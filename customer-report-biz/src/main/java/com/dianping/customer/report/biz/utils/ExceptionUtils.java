package com.dianping.customer.report.biz.utils;

public class ExceptionUtils {
    public static String getExceptionMessage(Throwable e) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ExceptionMessage is: " + e.getMessage() + ";");
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            stringBuilder.append(stackTraceElement.toString() + "<br>");
        }
        Throwable causedException = e.getCause();
        if (causedException != null) {
            stringBuilder.append("caused:");
            for (int i = 0; i < 10 && i < causedException.getStackTrace().length; i++) {
                stringBuilder.append(causedException.getStackTrace()[i].toString() + "<br>");
            }
        }
        return stringBuilder.toString();
    }
}
