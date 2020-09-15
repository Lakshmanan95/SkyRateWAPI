package com.skyrate.clib.service.email;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.stereotype.Service;

import com.skyrate.clib.util.SKYRATEUtil;

@Service
public class EmailTemplateService {

	//get email
	public String getEmailTemplate(String templateName, Map<String,Object> modelMap){
	      String result = null;
	      Properties props = new Properties();
	     	props.put("file.resource.loader.path", SKYRATEUtil.getConfigDIR()+"/emailTemplate");
	        VelocityEngine velocity = new VelocityEngine();
	        velocity.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,"org.apache.velocity.runtime.log.Log4JLogChute");

	        velocity.setProperty("runtime.log.logsystem.log4j.logger","com.mindtree.igg.website.email.TemplateMergeUtilVelocityImpl");

	        velocity.init(props);
	        System.out.println(SKYRATEUtil.getConfigDIR()+"/emailTemplate/"+templateName);
	        Template template = velocity.getTemplate(templateName);
	        
	        VelocityContext context = new VelocityContext();
	        
	        Iterator it = modelMap.entrySet().iterator();
	        while (it.hasNext()) {
	            Map.Entry<String,Object> pair = (Map.Entry<String,Object>)it.next();
	            context.put( pair.getKey(), pair.getValue());
	        }
	        
	      //  context.put("title", "Apache Velocity");
	     //   context.put("body", "Raj Velocity");
	        StringWriter writer = new StringWriter();
	        template.merge(context, writer);
	        result = writer.toString();

	        return result;
	}
}
