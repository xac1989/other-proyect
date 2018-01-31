package com.cencosud.middleware.rewards.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.cencosud.middleware.rewards.client.impl.RewardsClientImpl;

@Configuration
public class RewardsClientConfig {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.cencosud.middleware.rewards.client.wsdl");
		return marshaller;
	}

	@Bean
	public RewardsClientImpl rewardsClient(Jaxb2Marshaller marshaller) {
		RewardsClientImpl client = new RewardsClientImpl();
		return client;
	}

}