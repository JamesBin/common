package com.hgsoft.yfzx.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 功能描述：关于异常的工具类.
 * 参考了guava的Throwables。
 *
 * @author calvin
 */
public class Exceptions {

    /**
     * 功能描述：将CheckedException转换为UncheckedException.
     *
     * @param ex
     * @return
     */
    public static RuntimeException unchecked(Throwable ex) {
        if (ex instanceof RuntimeException) {
            return (RuntimeException) ex;
        } else {
            return new RuntimeException(ex);
        }
    }

    /**
     * 功能描述：将ErrorStack转化为String.
     *
     * @param ex
     * @return
     */
    public static String getStackTraceAsString(Throwable ex) {
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /**
     * 功能描述：获取组合本异常信息与底层异常信息的异常描述, 适用于本异常为统一包装异常类，底层异常才是根本原因的情况。
     *
     * @param ex
     * @return
     */
    public static String getErrorMessageWithNestedException(Throwable ex) {
        Throwable nestedException = ex.getCause();
        return new StringBuilder().append(ex.getMessage()).append(" nested exception is ")
                .append(nestedException.getClass().getName()).append(":").append(nestedException.getMessage())
                .toString();
    }

    /**
     * 功能描述：获取异常的Root Cause.
     *
     * @param ex
     * @return
     */
    public static Throwable getRootCause(Throwable ex) {
        Throwable cause;
        while ((cause = ex.getCause()) != null) {
            ex = cause;
        }
        return ex;
    }

    /**
     * 功能描述：判断异常是否由某些底层的异常引起.
     *
     * @param ex
     * @param causeExceptionClasses
     * @return
     */
    public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
        Throwable cause = ex;
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }
}
