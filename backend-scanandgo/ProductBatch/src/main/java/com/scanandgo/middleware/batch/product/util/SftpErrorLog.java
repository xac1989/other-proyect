package com.scanandgo.middleware.batch.product.util;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.scanandgo.middleware.batch.product.configuration.ApplicationConfig;
import com.scanandgo.middleware.batch.product.configuration.ApplicationConfig.SFTPProperties;

@Component
@Scope("prototype")
public class SftpErrorLog {

	private static final Logger logger = LoggerFactory.getLogger(SftpErrorLog.class);
	private static final String ERROR_FILE_NAME = "Error";
	private Session session = null;
	private Channel channel = null;
	private ChannelSftp downloadChannelSftp = null;

	@Autowired
	private ApplicationConfig applicationConfig;

	public void logError(String ean, String fileName, String message) {
		SFTPProperties sftpProperties = applicationConfig.getSftpConfig();
		try {
			JSch jsch = new JSch();

			// creating a session object
			session = jsch.getSession(sftpProperties.getUsername(), sftpProperties.getUrl(), sftpProperties.getPort());
			// setting a password
			session.setPassword(sftpProperties.getPassword());
			// Create a Properties object of Java Util
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			// connecting to SFTP server
			session.connect();
			logger.info("Host connected");
			// creating a channel object for SFTP
			channel = session.openChannel("sftp");
			// connecting channel to SFTP Server
			channel.connect();
			logger.info("sftp channel opened and connected.");
			// Type Caste a Channel object to ChannelSftp Object
			downloadChannelSftp = (ChannelSftp) channel;
			// Go to particular directory using SftpChannel
			downloadChannelSftp.cd(downloadChannelSftp.getHome() + "/" + sftpProperties.getPathError());
			Calendar calendarToDay = Calendar.getInstance();
			StringBuffer sbFile = new StringBuffer(ERROR_FILE_NAME);
			sbFile.append("_").append(fileName.replaceAll(".DAT", "")).append(".log");

			OutputStream outPutStream = null;
			StringBuffer strLineLog = new StringBuffer();
			String pattern = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat format = new SimpleDateFormat(pattern);

			outPutStream = downloadChannelSftp.put(sbFile.toString(), ChannelSftp.APPEND);
			strLineLog.append(format.format(calendarToDay.getTime())).append("\tEAN: ").append(ean)
					.append("\tMessage: ").append(message).append("\n");
			IOUtils.write(strLineLog, outPutStream, StandardCharsets.UTF_8);

			outPutStream.close();

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	public void closeConnection() {
		downloadChannelSftp.exit();
		channel.disconnect();
		session.disconnect();
	}
}
