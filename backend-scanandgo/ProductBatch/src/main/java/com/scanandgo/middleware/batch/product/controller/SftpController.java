package com.scanandgo.middleware.batch.product.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scanandgo.middleware.batch.product.service.SelloService;
import com.scanandgo.middleware.batch.product.service.SftpService;

@RestController
public class SftpController {

	@Autowired
	SftpService sftpService;

	@Autowired
	SelloService selloService;

	@RequestMapping("/launchjob")
	public String handle() throws Exception {

		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			if (!isRuning(Thread.currentThread().getThreadGroup())) {
				Thread thread = new Thread(sftpService, "sftp-service-thread");
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}

		return "Done";
	}

	@RequestMapping("/launchSellos")
	public String handleSellos() throws Exception {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			Thread thread = new Thread(selloService);
			thread.start();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return "Done";
	}

	private boolean isRuning(ThreadGroup group) {
		if (group == null)
			return false;
		int num_threads = group.activeCount();
		Thread[] threads = new Thread[num_threads];

		group.enumerate(threads, false);
		List<Thread> threadList = Arrays.asList(threads);
		for (Thread tmp : threadList) {
			if (tmp!=null && tmp.getName().equals("sftp-service-thread"))
				return true;
		}

		return false;
	}
}