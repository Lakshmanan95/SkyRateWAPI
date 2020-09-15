package com.skyrate.clib.util;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;


public class UploadUtil {
	
//	private static Properties properties = new Properties();
	//String Directory;
//	public static String DEFAULT_IMG_URL="image_uploads/product_images/drugrx.png";
//	public static String USER_UPLOAD_WEB_ROOT="assets/img/image_uploads/product_images";
//	public static String PRODUCT_IMAGES_WEB_ROOT="assets/img/image_uploads/product_images";
//	public static String USER_PROFILE_IMAGES_WEB_ROOT="assets/img/image_uploads/user_profile_images";
	
	public static String getTodayDir(String baseDir){
		
	      Calendar c = Calendar.getInstance();
	      int year = Calendar.getInstance().get(Calendar.YEAR);
	      String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
	      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY");
	      LocalDate localDate = LocalDate.now();
	      
	      String Year = Integer.toString(year);
	      String date = dtf.format(localDate);
	      String uuid = UUID.randomUUID().toString().replace("-", "");
	      
	      cretaeDirWithPermission(baseDir+"/"+Year);
	      cretaeDirWithPermission(baseDir+"/"+Year+"/"+month);
	      cretaeDirWithPermission(baseDir+"/"+Year+"/"+month+"/"+date);
	      
	      return Year+"/"+month+"/"+date;
	}
	public static String getFileUploadDir(String baseDir){
		Calendar c = Calendar.getInstance();
	      int year = Calendar.getInstance().get(Calendar.YEAR);
	      String month = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
	      String Year = Integer.toString(year);
//	      String businessId = Integer.toString(id);
	      
	      cretaeDirWithPermission(baseDir+"/"+Year);
	      cretaeDirWithPermission(baseDir+"/"+Year);
	      
	      return Year;
	      
	}
	private static boolean cretaeDirWithPermission(String path){
		 File file = new File(path);
		 if(!file.exists()){
			  file.mkdirs();
	    	  file.setExecutable(true,false);
	    	  file.setReadable(true,false);
	    	  file.setWritable(true,false);
		 }
		 return true;
	}
	public static String createDirectory(String baseDir){
		  String todayDir = getTodayDir(baseDir);
	  /*    File file = new File(baseDir+"/"+todayDir);
	      if(!file.exists()){
	   	  file.mkdirs();
	    	  file.setExecutable(true,false);
	    	  file.setReadable(true,false);
	    	  file.setWritable(true,false);
	    	  try{
	    	  Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");
	    	  Files.createDirectories(Paths.get(file.getAbsolutePath()), PosixFilePermissions.asFileAttribute(perms));
	    	  System.out.println("Dir permissions done");
	    	  }catch(Exception e){
	    		  e.printStackTrace();
	    	  }
	    	  
	      }*/
		return todayDir;
	}
	 public static String getBaseUploadDirectory(){
		 String baseUploadDIR="";
		 Properties properties = new Properties();

			String locationProperties;
			try{
			locationProperties = SKYRATEUtil.getConfigDIR() + "/location.properties";
			properties.load(new FileReader(locationProperties));
			baseUploadDIR = properties.getProperty("MainDirectory");
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return baseUploadDIR;
 		
	 }
	 public static String getProductImageUploadDIR(){
		 String baseUploadDIR="";
		 Properties properties = new Properties();

			String locationProperties;
			try{
			locationProperties = SKYRATEUtil.getConfigDIR() + "/location.properties";
			properties.load(new FileReader(locationProperties));
			baseUploadDIR = properties.getProperty("ProductImageDIR");
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return baseUploadDIR;
 		
	 }	 
	 public static String getUserProfileImageUploadDIR(){
		 String baseUploadDIR="";
		 Properties properties = new Properties();

			String locationProperties;
			try{
			locationProperties = SKYRATEUtil.getConfigDIR() + "/location.properties";
			properties.load(new FileReader(locationProperties));
			baseUploadDIR = properties.getProperty("UserProfileImageDIR");
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return baseUploadDIR;
 		
	 }	
	 
	 public static String getAdImageUploadDIR(){
		 String baseUploadDIR="";
		 Properties properties = new Properties();

			String locationProperties;
			try{
			locationProperties = SKYRATEUtil.getConfigDIR() + "/location.properties";
			properties.load(new FileReader(locationProperties));
			baseUploadDIR = properties.getProperty("AdImageDIR");
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return baseUploadDIR;
 		
	 }
	 
}