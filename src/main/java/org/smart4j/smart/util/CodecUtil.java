package org.smart4j.smart.util;

/**
 * Created by liangyue on 2018/1/3.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码与解码工具类
 */
public final class CodecUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将URL编码
     * @param source
     * @return
     */
    public static String encodeURL(String source){
        String target;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("encode url failure");
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将URL解码
     * @param source
     * @return
     */
    public static String decodeURL(String source){
        String target;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("decode url failure");
            throw new RuntimeException(e);
        }
        return target;
    }
}
