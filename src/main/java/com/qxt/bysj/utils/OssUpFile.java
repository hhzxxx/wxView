package com.qxt.bysj.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import com.linkallcloud.log.Log;
//import com.linkallcloud.log.Logs;

@Component
public class OssUpFile {

    //protected Log log = Logs.get(); 无日志打印
	private static Log log = LogFactory.getLog(OssUpFile.class);

    private static String endpoint = "";
    @Value("${endpoint}")
    public void setEndpoint(String endpoint1) {
        endpoint = endpoint1;
    }

    private static String accessKeyId = "";
    @Value("${accessKeyId}")
    public void setAccessKeyId(String accessKeyId1) {
        accessKeyId = accessKeyId1;
    }

    private static String accessKeySecret = "";
    @Value("${accessKeySecret}")
    public void setAccessKeySecret(String accessKeySecret1) {
        accessKeySecret = accessKeySecret1;
    }

    private static String bucketName = "";
    @Value("${bucketName}")
    public void setBucketName(String bucketName1) {
        bucketName = bucketName1;
    }

    /**
     * 功能描述:
     *
     * @param:[objectKey, multipartFile 文件的新名称]
     * @return:java.lang.String
     * @date:2018/10/14 15:46
     **/
    public static boolean uploadFile(String objectKey, MultipartFile multipartFile)
            throws FileNotFoundException {
        boolean flag = false;
        // 创建OSSClient的实例
        OSSClient ossClient  = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        //StringBuffer sb = new StringBuffer();
        // 上传的文件不是空，并且文件的名字不是空字符串
        if (multipartFile.getSize() != 0 && !"".equals(multipartFile.getName())) {
            ObjectMetadata om = new ObjectMetadata();
            om.setContentLength(multipartFile.getSize());
            // 设置文件上传到服务器的名称
            om.addUserMetadata("filename", objectKey);
            
            ByteArrayInputStream is = null;
            try {
            	is = new ByteArrayInputStream(multipartFile.getBytes());
                ossClient.putObject(bucketName, objectKey, is, om);
                
                String ossUrl = getUrl(objectKey);
                log.info("ossUrl: "+ossUrl);
                flag = true;
                
            } catch (OSSException oe) {
                oe.printStackTrace();
            } catch (ClientException ce) {
                ce.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                
            } finally {
                ossClient.shutdown();
                
				try {
					if (is!=null) is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
        
        return flag;
    }

    private static long OssUrlTimeout = 3600L * 1000 * 24 * 365 * 20; //20年有效
    public static String getUrl(String objectKey){
        OSSClient ossClient  = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        // 设置这个文件地址的有效时间
        Date expiration = new Date(new Date().getTime() + OssUrlTimeout);
        String url = ossClient.generatePresignedUrl(bucketName, objectKey, expiration).toString();
        log.info("oss file: "+url);
        ossClient.shutdown();
        return url;
    }


    public static synchronized OSSClient getInstance() {
        OSSClient ossClient = null;
        //OSSClient ossClient = connInstances.get("oss");
        //if (ossClient == null) {
            synchronized (OssUpFile.class) {
                if (ossClient == null) {
                    ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                }
            }
        //}
        return ossClient;
    }

    /** 获取OSS文件  */
    private static OSSObject downFile(String fileName) {

        OSSClient ossClient = getInstance();
        Date expiration = new Date(new Date().getTime() + OssUrlTimeout); //3600 * 1000
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, fileName, HttpMethod.GET);
        // 设置过期时间。
        request.setExpiration(expiration);
        // 生成签名URL（HTTP GET请求）。
        URL signedUrl = ossClient.generatePresignedUrl(request);
        // 使用签名URL发送请求。
        Map<String, String> customHeaders = new HashMap<String, String>();
        // 添加GetObject请求头。
        //customHeaders.put("Range", "bytes=100-1000");
        OSSObject object = ossClient.getObject(signedUrl, customHeaders);
        log.info("oss file: "+signedUrl.toString());
        
        return object;
    }

    public static boolean changeObjectKey(String oldObjectKey,String newObjectKey){
        OSSClient ossClient = getInstance();
        Boolean flag = true;
        try {
            ossClient.copyObject(bucketName, oldObjectKey, bucketName, newObjectKey);
        }catch (OSSException e){
            flag = false;
        }catch (ClientException e){
            flag = false;
        }finally {
            if(flag){
                ossClient.deleteObject(bucketName, oldObjectKey);
            }
        }
        return flag;
    }

    public static boolean downOssFile(String objectKey, HttpServletResponse response, String fileName) throws IOException {
        // 创建OSSClient实例。
        //OSSClient ossClient  = new OSSClient(endpoint,accessKeyId,accessKeySecret);

        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        //OSSObject ossObject = ossClient.getObject(bucketName, objectKey);
        OSSObject ossObject = downFile(objectKey);

        //ossClient.getObject(new GetObjectRequest(bucketName,objectKey),new File("C:\\Users\\Administrator\\Downloads\\"+objectKey));
        boolean flag = false;
        if (ossObject != null) {
            InputStream inputStream = ossObject.getObjectContent();
            int buffer = 1024 * 10;
            byte[] data = new byte[buffer];
            try {
                response.setContentType("application/octet-stream");
                // 文件名可以任意指定
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));//将文件名转为ASCLL编码
                int read;
                while ((read = inputStream.read(data)) != -1) {
                    response.getOutputStream().write(data, 0, read);
                }
                response.getOutputStream().flush();
                response.getOutputStream().close();
                ossObject.close();
                flag = true;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            	if (inputStream!=null) inputStream.close();
            }
        }

        // 关闭OSSClient。
        //ossClient.shutdown();
        return flag;
    }

}
