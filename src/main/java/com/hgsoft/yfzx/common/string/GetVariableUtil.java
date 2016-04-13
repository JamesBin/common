package com.hgsoft.yfzx.common.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述:根据模板获得对应的变量的组合字符串,以逗号分割
 * @Author: PanNaiZhao
 * @Date: 2016/1/5.
 * @File: EmailTemplateVariableUtil
 */
    public class GetVariableUtil {
    /**
     * 功能描述：根据模板获得对应的变量的组合字符串,以“,”分割
     * @param templateStr
     * @return
     */
    public static String getVariableStr(String templateStr){
        StringBuilder customVariableStr = new StringBuilder();
        String regex = "\\$\\{[^}]*\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(templateStr);
        while (matcher.find()){
            String variable= matcher.group(0).split("[\\{|\\}]")[1];
            customVariableStr.append(variable+",");
        }
        return customVariableStr.toString();
    }
}
