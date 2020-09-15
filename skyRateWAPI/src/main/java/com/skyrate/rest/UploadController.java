package com.skyrate.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.annotation.MultipartConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.skyrate.clib.util.SKYRATEUtil;
import com.skyrate.clib.util.UploadUtil;

@RestController
@RequestMapping("/upload")
@CrossOrigin( maxAge = 3600)
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50) 
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	private static Properties properties = new Properties();
	
	String webrootLocation = SKYRATEUtil.getConfigDIR() + "/webrootLocation.properties";

	 @RequestMapping(method = RequestMethod.POST, value="/uploader")
    @ResponseBody
    public String fileUploader(@RequestParam("file") MultipartFile uploader, 
    		@RequestParam(value="id", required=false) String fileKey) throws Exception {
    		
    		String uploadedDir = uploadFile(uploader,UploadUtil.getBaseUploadDirectory(),0, ""+fileKey);
    		properties.load(new FileReader(webrootLocation));
    		String location =  properties.getProperty("DEFAULT_IMG_URL");
      		return location+"/"+uploadedDir;
    		
    }
	 @RequestMapping(method = RequestMethod.POST, value="/productImageUploader")
	    @ResponseBody
	    public String productImageUploader(@RequestParam("file") MultipartFile uploader) throws Exception {
	    		
	    		String uploadedDir = uploadFile(uploader,UploadUtil.getProductImageUploadDIR(),0,"");
	    		properties.load(new FileReader(webrootLocation));
	    		String location =  properties.getProperty("PRODUCT_IMAGES_WEB_ROOT");
	      		return location+"/"+uploadedDir;
	    		
	    }
	 @RequestMapping(method = RequestMethod.POST, value="/profileImageUploader")
	    public String profileImageUploader(@RequestParam("file") MultipartFile uploader) throws Exception {
	    		
	    		String uploadedDir = uploadFile(uploader,UploadUtil.getUserProfileImageUploadDIR(),0,"");
	    		properties.load(new FileReader(webrootLocation));
	    		String location =  properties.getProperty("UserProfileImageDIR");
	      		return location+"/"+uploadedDir;
	    		
	    }
	 @RequestMapping(method = RequestMethod.POST, value="/adImageUploader")
	    public String adImageUploader(@RequestParam("file") MultipartFile uploader) throws Exception {
	    		
	    		String uploadedDir = uploadFile(uploader,UploadUtil.getAdImageUploadDIR(),0,"");
	    		properties.load(new FileReader(webrootLocation));
	    		String location =  properties.getProperty("AD_IMAGE_WEB_ROOT");
	      		return location+"/"+uploadedDir;
	    		
	    }
	 
	
	
	 private String uploadFile( MultipartFile uploader, String uploadDIR, int uploadedFile, String fileKey) throws Exception{
	   		
    		
    		String fileName = uploader.getOriginalFilename();
    		String fileWithTimeStamp =  fileKey+"_"+uploader.getOriginalFilename();
    		String todayDir="";
   
    		UploadUtil cd = new UploadUtil();
    		if(uploadedFile == 0)
    		todayDir = cd.createDirectory(uploadDIR);
    		else{
    			 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    	   		  System.out.println(timestamp.getTime());
    	    		 fileWithTimeStamp = timestamp.getTime()+"-"+fileName;
    			todayDir = cd.getFileUploadDir(uploadDIR);
    			
    		}
    		InputStream inputStream = uploader.getInputStream();	    		
			 
			File file = new File(uploadDIR+"/"+todayDir+"/"+fileWithTimeStamp);
    		OutputStream outputStream = null;
    		
    		try{

    			// write the inputStream to a FileOutputStream
    			outputStream =
    	                    new FileOutputStream(file);

    			int read = 0;
    			byte[] bytes = new byte[1024];

    			while ((read = inputStream.read(bytes)) != -1) {
    				outputStream.write(bytes, 0, read);
    			}

    			

    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			if (inputStream != null) {
    				try {
    					inputStream.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
    			if (outputStream != null) {
    				try {
    					// outputStream.flush();
    					outputStream.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}

    			}
    		}
   			 File file2 = new File(file.getAbsolutePath());
   			 if(file2.exists()){
   				 file2.setExecutable(true,false);
   				 file2.setReadable(true,false);
   				 file2.setWritable(true,false);
   				 
   			 }
    	    

    		return todayDir+"/"+fileWithTimeStamp;
	 }

	 
	 
	
	 
	
	    
}
