package com.dianping.customer.report.biz.exceptions;

import com.dianping.customer.report.biz.enums.ApplicationExceptionEnum;

/**
 * author: feipeng
 * 一个应用级别的异常机制，所有本应用的产生的异常都可以用该异常来包装，以便更容易理解和处理
 * 该异常接受两个参数,ApplicationExceptionEnum和产生异常的上下文数据
 * 所有使用ApplicationException的地方都需要在ApplicationExceptionEnum定义，该枚举主要是定义异常的处理器
 * 该项目是web项目，在各层抛出该异常时，有2种结果
 * 1.最终由web层的一个切面做统一处理，通过枚举的异常处理器友好的将异常信息流给用户,记录日志等
 * 2.被其它层catch住，需要自行处理。
 */
public class ApplicationException extends RuntimeException{

    private final ApplicationExceptionEnum applicationExceptionEnum;
    private final Object[] args;



    public ApplicationException(Throwable t,ApplicationExceptionEnum applicationExceptionEnum,Object ... args){
        super(t);
        this.applicationExceptionEnum = applicationExceptionEnum;
        this.args = args;
    }

    public ApplicationException(ApplicationExceptionEnum applicationExceptionEnum,Object ... args){
        this(null,applicationExceptionEnum,args);
    }

    public ApplicationExceptionEnum getApplicationExceptionEnum() {
        return applicationExceptionEnum;
    }

    public Object[] getArgs() {
        return args;
    }

}
