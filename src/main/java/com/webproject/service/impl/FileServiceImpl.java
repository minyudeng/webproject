package com.webproject.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.webproject.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@Service
public class FileServiceImpl implements FileService {
    private String endpoint = "oss-cn-wuhan-lr.aliyuncs.com";

    private String accessKeyId = "LTAI5tMVDTf6v89ptFG7jKHn";

    private String accessKeySecret = "r3mOpMd5NSoGAuxAqfgu15456SgLPT";

    private String bucketName = "dmyweb";

    private String urlPrefix = "http://dmyweb.oss-cn-wuhan-lr.aliyuncs.com/";

    @Override
    public String upload(String objectName, MultipartFile file, String oldName) {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if (oldName != null){
            oldName = oldName.substring(urlPrefix.length());
            delFile(oldName);
        }
        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(file.getBytes()));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            String ret = urlPrefix + objectName;
            System.out.println("新文件: "+ret);
            return ret;
    }
}

    @Override
    public Boolean delFile(String objectName) {
        if (isEmpty(objectName)){
            System.out.println("文件存在");
        }else {
            System.out.println("文件不存在");
            return true;
        }
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 删除文件。
            ossClient.deleteObject(bucketName, objectName);
            System.out.println("删除成功:"+objectName);
            return true;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return false;
    }
    public boolean isEmpty(String objectName){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        boolean ret = ossClient.doesObjectExist(bucketName,objectName);
        System.out.println("isEmpty= "+ret);
        ossClient.shutdown();
        return ret;
    }
}
