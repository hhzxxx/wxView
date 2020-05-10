package com.qxt.bysj.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;


@Component
public class UpFileUtil {


	public String saveOssFile(MultipartFile file,String objType) {
		String ossUrl = "";
		String fileName = file.getOriginalFilename();
		//处理IE上传带路径的问题
		int iPos = fileName.lastIndexOf("\\");
		if (iPos>0) fileName = fileName.substring(iPos+1);
		
		String ext = fileName.substring(fileName.lastIndexOf("."));// jpg
        UUID uuid = UUID.randomUUID();
        String randName= uuid.toString();
		String newfileName = randName + ext;

        //OSS上传
        String objectKey = objType+"/"+newfileName;
        try {
        	if (!OssUpFile.uploadFile(objectKey, file)){
				throw new Exception("上传文件（oss）出错");
			}else {
				ossUrl = OssUpFile.getUrl(objectKey);
			}
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	file = null;
        }
		return ossUrl;
	}

}
