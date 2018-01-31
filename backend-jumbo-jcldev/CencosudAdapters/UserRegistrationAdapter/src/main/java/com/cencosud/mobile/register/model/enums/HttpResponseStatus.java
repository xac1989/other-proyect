package com.cencosud.mobile.register.model.enums;

public enum HttpResponseStatus {
	HTTP_400("1,2,3,6",400),
	HTTP_409("5,7",409),
	HTTP_500("4",500);
	
	private String ids;
	int httpCode;
	
	private HttpResponseStatus(String ids, int httpCode) {
		this.ids = ids;
		this.httpCode = httpCode;
	}

	public String getIds() {
		return ids;
	}

	public int getHttpCode() {
		return httpCode;
	}	
	
	public static int findCodeHttp(Integer code) {
		for(HttpResponseStatus tmpEnum:HttpResponseStatus.values()) {
			for(String tmpCode:tmpEnum.getIds().split(",")) {
				if(tmpCode.equals(code.toString())) {
					return tmpEnum.getHttpCode();
				}
			}
		}
		return 0;
	}
}
