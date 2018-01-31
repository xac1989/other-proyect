package com.scanandgo.middleware.batch.product.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.scanandgo.middleware.batch.product.configuration.ApplicationConfig;
import com.scanandgo.middleware.batch.product.configuration.ApplicationConfig.SFTPProperties;
import com.scanandgo.middleware.batch.product.exception.ProductServiceException;
import com.scanandgo.middleware.batch.product.service.SftpService;
import com.scanandgo.middleware.batch.product.util.Processor;
import com.scanandgo.middleware.batch.product.util.SftpErrorLog;

/**
 * 
 * 
 * <h1>SftpClient</h1>
 * <p>
 * </p>
 * 
 * @author fernando.castro
 * @version 1.0
 * @since Mar 30, 2017
 */
@Service
@Scope("prototype")
public class SftpServiceImpl implements SftpService {

	private final static int LINES_BY_PRODUCT = 7;

	private static final Logger logger = LoggerFactory.getLogger(SftpServiceImpl.class);

	private Session session = null;
	private Channel channel = null;
	private ChannelSftp downloadChannelSftp = null;

	@Autowired
	private ApplicationConfig applicationConfig;

	@Autowired
	SftpErrorLog sftpErrorLog;

	@Autowired
	Processor processor;

	@Override
	public List<String> getFileNames() throws SftpException, JSchException, ProductServiceException {
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
			downloadChannelSftp.cd(downloadChannelSftp.getHome() + "/" + sftpProperties.getPathEntrada());
			// Print the current path on console
			logger.info("Home = " + downloadChannelSftp.getHome());
			logger.info("Path = " + downloadChannelSftp.pwd());

			@SuppressWarnings("unchecked")
			Vector<LsEntry> files = downloadChannelSftp
					.ls(downloadChannelSftp.getHome() + "/" + sftpProperties.getPathEntrada() + "*.DAT");
			List<String> fileNames = new ArrayList<>(files.size());

			for (LsEntry tempFile : files) {
				fileNames.add(tempFile.getFilename());
			}
			Collections.sort(fileNames);
			
			for(String tmp:fileNames)
				System.out.println(tmp);
			return fileNames;

		} catch (JSchException | SftpException exc) {
			exc.printStackTrace();
			throw new ProductServiceException("Poblemas en la conexión con SFTP.", exc);
		} finally {
			this.closeConnection();
		}
	}

	@Override
	public InputStream getFileStreamByName(String fileName) throws ProductServiceException {
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
			downloadChannelSftp.cd(downloadChannelSftp.getHome() + "/" + sftpProperties.getPathEntrada());

			InputStream inputStream = downloadChannelSftp.get(fileName);
			return inputStream;
		} catch (JSchException | SftpException exc) {
			exc.printStackTrace();
			throw new ProductServiceException("Poblemas en la conexión con SFTP.", exc);
		}
	}
	
	private void moveFile(String fileName) throws ProductServiceException {
		SFTPProperties sftpProperties = applicationConfig.getSftpConfig();
		ChannelSftp uploadChannelSftp = null;
		Channel upChannel = null;
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
			upChannel = session.openChannel("sftp");
			// connecting channel to SFTP Server
			channel.connect();
			upChannel.connect();
			logger.info("sftp channel opened and connected.");
			// Type Caste a Channel object to ChannelSftp Object
			downloadChannelSftp = (ChannelSftp) channel;
			uploadChannelSftp = (ChannelSftp) upChannel;
			// Go to particular directory using SftpChannel
			downloadChannelSftp.cd(downloadChannelSftp.getHome() + "/" + sftpProperties.getPathEntrada());

			InputStream inputStream = downloadChannelSftp.get(fileName);
			
			uploadChannelSftp.put(inputStream, uploadChannelSftp.getHome() + "/" + sftpProperties.getPathSalida()+fileName);
			
			downloadChannelSftp.rm(fileName);
			
		} catch (JSchException | SftpException exc) {
			exc.printStackTrace();
			throw new ProductServiceException("Poblemas en la conexión con SFTP.", exc);
		}finally {
			uploadChannelSftp.exit();
			upChannel.disconnect();
			this.closeConnection();
		}
	}

	@Override
	public void read() {
		try {
			List<String> fileNames = this.getFileNames();
			for (String tmpFileName : fileNames) {
				InputStream inputStream = this.getFileStreamByName(tmpFileName);
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				String line = br.readLine();
				while (line != null) {
					List<String> tmpLines = new ArrayList<>(LINES_BY_PRODUCT);
					String ean = line.substring(3, 16);
					tmpLines.add("store" + tmpFileName.substring(5, 9));
					while ((line != null && !line.equals(""))
							&& (ean.equals(line.substring(3, 16)) || ean.equals(line.substring(43, 56)))) {
						tmpLines.add(line);
						line = br.readLine();
					}
					try {
						logger.info("Nombre de archivo: "+tmpFileName);
						processor.process(tmpLines);
					} catch (Exception ex) {
						ex.printStackTrace();
						sftpErrorLog.logError(ean, tmpFileName, ex.getMessage());
					}
				}
				this.closeConnection();
				this.moveFile(tmpFileName);
			}
		} catch (ProductServiceException | SftpException | JSchException | IOException ex) {
			ex.printStackTrace();
		} finally {
			this.closeConnection();
		}
	}

	@Override
	public void closeConnection() {
		downloadChannelSftp.exit();
		channel.disconnect();
		session.disconnect();
	}

	@Override
	public void run() {
		this.read();
	}

}
