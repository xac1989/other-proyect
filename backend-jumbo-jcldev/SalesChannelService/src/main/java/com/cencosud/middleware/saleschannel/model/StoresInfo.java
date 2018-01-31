package com.cencosud.middleware.saleschannel.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * 
 * <h1>StoresInfo</h1>
 * <p>
 * Entidad StoresInfo
 * </p>
 * @author engelbert.quiroz
 * @version 1.0
 * @since Oct 09, 2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoresInfo {

	private Map<String, List<Commune>> stores;
	private String data;

	/**
	 * 
	 * @return stores 
	 */
	public Map<String, List<Commune>> getStores() {
		return stores;
	}

	/**
	 * 
	 * @return data Información de Stores como String
	 */
	public String getData() {
		return data;
	}

	/**
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void setData(String data) throws Exception {

		this.data = data.replaceAll("[\r\n]", "");
		ObjectMapper mapper = new ObjectMapper();
		stores = mapper.readValue(this.data, new TypeReference<Map<String, List<Commune>>>() {
		});
	}

}
