package com.chenhj.spider.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.baidu.aip.ocr.AipOcr;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;


/**
 * @author chenhj
 */
public class OrcUtil {

	private static final Log log = LogFactory.getLog(OrcUtil.class);
    private static final String IN_PATH = "";
    private static final String OUT_PATH = "";

    /**
     * baidu orc 帐号资源枚举
     * @author xd
     */
    private enum ApiAccount {
        /**
         * 石总
         */
        SHIBIN("", "", ""),

        /**
         * 波总
         */
        HEZHENBO("", "", "");

    	private final String appId;
    	private final String apiKey;
    	private final String secretKey;
    	ApiAccount(String appId, String apiKey, String secretKey) {
    		this.appId = appId;
    		this.apiKey = apiKey;
    		this.secretKey = secretKey;
    	}
		public String getAppId() {
			return appId;
		}
		public String getApiKey() {
			return apiKey;
		}
		public String getSecretKey() {
			return secretKey;
		}
    }

    /**
     * 获取验证码
     * @param result base64图片数据
     * @return 验证码
     */
    public String getVerifyCode(String result) throws IOException {
        String code = "";
        String s = result.split("\"image\":\"")[1].split("\"")[0];
              
        URL base = Thread.currentThread().getContextClassLoader().getResource("");
        String basePath = new File(base.getFile(), "../../").getCanonicalPath();
        
        String in = basePath + IN_PATH + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmssSSS") + ".jpg";
        String out = basePath + OUT_PATH + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmssSSS") + ".jpg";
//        Boolean b = generateImage(s, in);
        
//        if (b) {
//            code = orcResult(in, out, Boolean.TRUE);
//        }
        return code;
    }

    /**
     * 获取验证码
     * @param bytes  图片字节数组
     * @param suffix 图片后缀
     * @return 验证码
     */
    public String getVerifyCode(byte[] bytes, String suffix) throws IOException, Exception {
    	URL base = Thread.currentThread().getContextClassLoader().getResource("");
        String basePath = new File(base.getFile(), "../../").getCanonicalPath();
        basePath = basePath.replaceAll("%20", " ");
        String in = basePath + IN_PATH + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmssSSS") + "." + suffix;
        String out = basePath + OUT_PATH + DateUtil.format(DateUtil.date(), "yyyyMMddHHmmssSSS") + "." + suffix;

        this.byteToFile(bytes, in);
        String code = orcResult(in, out, Boolean.FALSE);
        FileUtil.del(new File(in));
        FileUtil.del(new File(out));
        return code;
    }
    
    private void byteToFile(byte[] bytes, String fileName) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);   
        BufferedImage bi1 =ImageIO.read(bais); 
        try {   
            File w2 = new File(fileName);//可以是jpg,png,gif格式   
            ImageIO.write(bi1, "gif", w2);//不管输出什么格式图片，此处不需改动   
        } catch (IOException e) {   
            e.printStackTrace();   
        } finally {
        	bais.close();
        }
    }

    public String orcResult(String originalImg, String outImg, Boolean cuttingImg) {
        //去噪点
//        ImgUtils.removeBackground(OriginalImg, outImg);
//        if(cuttingImg) {
        	 //裁剪边角
//            ImgUtils.cuttingImg(outImg);
//        }
        //调用api
        String result = orcApiResult(outImg);
        return result;
    }

    private String orcApiResult(String img) {
    	try {
    		for (ApiAccount o : ApiAccount.values()) {
        		AipOcr client = new AipOcr(o.getAppId(), o.getApiKey(), o.getSecretKey());
        		HashMap<String, String> options = new HashMap<String, String>();
        		JSONObject res = client.basicGeneral(img, options);
        		// qps受限，切换帐号资源
        		if (res.toString().contains("error_code")) {
        			log.error("ORC 图片识别失败，response=" + res.toString() + "，帐号信息：" + JSONUtil.toJsonStr(o));
        			continue;
        		}
        		return res.toString().split("\"words\":\"")[1].split("\"}],")[0];
        	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return "";
    }
}
