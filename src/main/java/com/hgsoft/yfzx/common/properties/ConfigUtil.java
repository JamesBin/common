package com.hgsoft.yfzx.common.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 功能描述：操作properties文件
 * @author   zengtao
 * @version  1.0.0
 */
public class ConfigUtil {
    private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
	static Properties properties=new Properties();

	private static void load(){
	  InputStream is= ConfigUtil.class.getResourceAsStream("/config.properties");
	  try {
		  properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 功能描述：properties文件读取之后存储到map对象中并返回。
     * @param filename
     * @return
     * @throws IOException
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Map<String, String> loadClassPathConfig(String filename) throws IOException {
        InputStream ins = null;
        Map params = new HashMap();
        logger.info("start load {}....", filename);
        try {
            ins = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            properties.load(ins);
            Properties prop = new Properties();
            prop.load(ins);
            params.putAll(prop);
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    logger.error("关闭文件", e);
                }
                ins = null;
            }
        }
        logger.info("end load {}.", filename);
        return params;
    }

    /**
     * 功能描述：获得properties中的某个key的value
     * @param key
     * @return
     */
	public static  String getProperty(String key){
		String value= properties.getProperty(key);
		return value;
	}
    /**
     * 功能描述：获得properties中的某个key的value，value为int类型
     * @param key
     * @return
     */
	public static int getIntProperty(String key){
		String value=getProperty(key);
		if(value==null)return 0;
		return Integer.parseInt(value.trim());
	}
    /**
     * 功能描述：查询properties中是否包含key
     * @param key
     * @return
     */
    protected boolean check(String key, Properties properties) {
        if (properties.containsKey(key)) {
            return true;
        } else {
            throw new IllegalArgumentException("the property do not have the key:" + key);
        }
    }

    /**
     * test
     * @param args
     */
	public static void main(String[] args) {
		System.out.println(ConfigUtil.getProperty("bank.host"));
	}
}

