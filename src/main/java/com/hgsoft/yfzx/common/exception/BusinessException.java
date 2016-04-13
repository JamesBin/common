package com.hgsoft.yfzx.common.exception;

/**
 * 功能描述：自定义的运行时异常类,基础框架开发平台用到的就是此自定义异常，在业务的逻辑开发中抛出此异常，就可以在后续进行处理。
 *
 * @Author: 吴锡霖
 * @File: BusinessException.java
 * @DATE: 2015/6/4
 * @TIME: 14:39
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String msg) {
        super(msg);
    }
}
