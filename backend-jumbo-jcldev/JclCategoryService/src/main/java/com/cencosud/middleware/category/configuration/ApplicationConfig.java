package com.cencosud.middleware.category.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class ApplicationConfig {
	
    private VTexProperties vtex;
    private MongoProperties mongoConfig;
    private BusinessProperties business;
    private ServerProperties server;

    public VTexProperties getVtex() {
        return vtex;
    }

    public void setVtex(VTexProperties vtex) {
        this.vtex = vtex;
    }
        
    public MongoProperties getMongoConfig() {
		return mongoConfig;
	}

	public void setMongoConfig(MongoProperties mongoConfig) {
		this.mongoConfig = mongoConfig;
	}

	public BusinessProperties getBusiness() {
		return business;
	}

	public void setBusiness(BusinessProperties business) {
		this.business = business;
	}

	public static class VTexProperties {
        private String url;
        private String schema;
        private int port;
        private String apiKey;
        private String apiSecret;
        private String env;
        private String urlImages;

        
		public String getUrlImages() {
			return urlImages;
		}
		public void setUrlImages(String urlImages) {
			this.urlImages = urlImages;
		}
		public String getEnv() {
			return env;
		}
		public void setEnv(String env) {
			this.env = env;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getSchema() {
			return schema;
		}
		public void setSchema(String schema) {
			this.schema = schema;
		}
		public int getPort() {
			return port;
		}
		public void setPort(int port) {
			this.port = port;
		}
		public String getApiKey() {
			return apiKey;
		}
		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}
		public String getApiSecret() {
			return apiSecret;
		}
		public void setApiSecret(String apiSecret) {
			this.apiSecret = apiSecret;
		}
    }
	
    public static class MongoProperties {
        private String database;

		public String getDatabase() {
			return database;
		}

		public void setDatabase(String database) {
			this.database = database;
		}
    }

    public static class BusinessProperties {
        private Map<String ,DepartmentProperties> departments;
        private List<String> departmentsIds;
        private List<String> specsRemove;
		private String departmentDeals;
        private String dealClusterId;

        /**
		 * @return the specsRemove
		 */
		public List<String> getSpecsRemove() {
			return specsRemove;
		}

		/**
		 * @param specsRemove the specsRemove to set
		 */
		public void setSpecsRemove(List<String> specsRemove) {
			this.specsRemove = specsRemove;
		}
		
		public Map<String, DepartmentProperties> getDepartments() {
			return departments;
		}

		public void setDepartments(Map<String, DepartmentProperties> departments) {
			this.departments = departments;
		}

		public List<String> getDepartmentsIds() {
			return departmentsIds;
		}

		public void setDepartmentsIds(List<String> departmentsIds) {
			this.departmentsIds = departmentsIds;
		}
		
		public String getDepartmentDeals() {
			return departmentDeals;
		}

		public void setDepartmentDeals(String departmentDeals) {
			this.departmentDeals = departmentDeals;
		}

        public String getDealClusterId() {
            return dealClusterId;
        }

        public void setDealClusterId(String dealClusterId) {
            this.dealClusterId = dealClusterId;
        }
    }
    
    public static class DepartmentProperties {
        private int id;
        private String name;
        private String basicPath;
        private String order;
        private boolean food;
        
		public String getOrder() {
			return order;
		}
		public void setOrder(String order) {
			this.order = order;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getBasicPath() {
			return basicPath;
		}
		public void setBasicPath(String basicPath) {
			this.basicPath = basicPath;
		}
		public boolean isFood() {
			return food;
		}
		public void setFood(boolean food) {
			this.food = food;
		}
		
    }
    
    public static class ServerProperties {
        private String path;
        
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
    }

	public ServerProperties getServer() {
		return server;
	}

	public void setServer(ServerProperties server) {
		this.server = server;
	}
}