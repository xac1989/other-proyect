package com.cencosud.middleware.monitor.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController	
@RequestMapping("/status")
public class MonitorMSController {
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public Map<String,String> getStatusOk()  {
		
		Map<String,String> result = new HashMap<String,String>();

		result.put("message", "Verification service status...");
		result.put("status", "OK");
		
		return result;
	}
	
}
