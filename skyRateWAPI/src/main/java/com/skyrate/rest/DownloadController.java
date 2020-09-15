package com.skyrate.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skyrate.clib.util.SKYRATEUtil;
import com.skyrate.model.business.FileRequest;
import com.skyrate.model.dbentity.Capabilities;
import com.skyrate.model.dbentity.Parts;
import com.skyrate.service.BusinessService;

@RestController
@RequestMapping("download")
@CrossOrigin(maxAge = 3600)
public class DownloadController {

	@Autowired
	BusinessService businessService;
	
	private static final Logger logger =  LoggerFactory.getLogger(DownloadController.class);
	private static Properties properties = new Properties();
	private static final String APPLICATION_PDF = "application/pdf";
	
	@RequestMapping(method = RequestMethod.GET, value = "/downloadFile")
	public void getDownloadCapabilities(HttpServletResponse response, Model model,
			 @RequestParam(value="filePath", required=false) String filePath,
			 @RequestParam(value="fileChoosen", required=false) String fileChoosen) throws ClassNotFoundException ,Exception{
		
		Capabilities capabilities = new Capabilities();
		String downloadLocation = null;
		String webrootLocation = SKYRATEUtil.getConfigDIR() + "/location.properties";
		properties.load(new FileReader(webrootLocation));
		String baseLocation = properties.getProperty("DefaultBaseDir");
		Parts parts = new Parts();
		if(fileChoosen.equalsIgnoreCase("Capabilities")){
			capabilities = businessService.findById(Integer.parseInt(filePath));
			 downloadLocation = baseLocation+capabilities.getCapabilitiesLocation();
		}
		if(fileChoosen.equalsIgnoreCase("Parts")){
			parts = businessService.findPartsById(Integer.parseInt(filePath));
			downloadLocation = baseLocation+parts.getPartsLocation();
		}
		
//		baseLocation+request.getFileName();
		
		
		String filename = filePath;
		String fileSplit[] = filename.split("/");
		String fileToDownload = fileSplit[fileSplit.length -1];
		
		
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment;filename="+fileToDownload);
        
        File file = new File(downloadLocation);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + downloadLocation + " was not found.");
        }
        InputStream in = new FileInputStream(file);

        response.setContentType(APPLICATION_PDF);
        response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));
        FileCopyUtils.copy(in, response.getOutputStream());
        
//        InputStream inputstream = new FileInputStream("/C:/WS/SkyRateWeb/SkyRate/src/assets/img/image_uploads/default_uploads/2018/September/19-09-2018/feb-08.txt");
//        IOUtils.copy(inputstream, response.getOutputStream());
//        response.flushBuffer();
//        File file = new File(downloadLocation);
//        FileInputStream fileIn = new FileInputStream(file);
//        ServletOutputStream out = response.getOutputStream();
//
//        byte[] outputByte = new byte[(int)file.length()];
//        //copy binary contect to output stream
//        while(fileIn.read(outputByte, 0, (int)file.length()) != -1)
//        {
//        out.write(outputByte, 0, (int)file.length());
//        }
	
	}
	
	}

