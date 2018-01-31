package com.cencosud.middleware.coupon.repository.impl;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.cencosud.middleware.coupon.repository.CacheRepository;

@Repository
public class CacheRepositoryImpl implements CacheRepository {
	
	Logger logger = LoggerFactory.getLogger(CacheRepositoryImpl.class);

	@Override
	public <T> void createCache(String cacheName, Class<T> valueClass) {

		CachingProvider cachingProvider = Caching.getCachingProvider();
		CacheManager cacheManager = cachingProvider.getCacheManager();

		// configure the cache
		cacheManager.createCache(cacheName, new MutableConfiguration<String, T>()
				.setTypes(String.class, valueClass)
				.setStoreByValue(false)
				.setExpiryPolicyFactory(null)
				.setStatisticsEnabled(false));
		logger.info("Cache created");
	}

	private <T> Cache<String, T> getCache(String cacheName, Class<T> valueClass) {
		return Caching.getCache(cacheName, String.class, valueClass);
	}

	/*
	 * (non-Javadoc)
	 * @see edu.globant.ltmj.dao.CacheRepository#put(java.lang.String,
	 * java.lang.Class, java.lang.String, T)
	 */

	@Override
	public <T> void put(String cacheName, Class<T> valueClass, String key, T value) {
		Cache<String, T> cache = getCache(cacheName, valueClass);
		logger.info("Puttin into cache: {} - k:{} v:{}",cacheName,key,value);
		cache.put(key, value);
	}

	/*
	 * (non-Javadoc)
	 * @see edu.globant.ltmj.dao.CacheRepository#get(java.lang.String,
	 * java.lang.Class, java.lang.String)
	 */

	@Override
	public <T> T get(String cacheName, Class<T> valueClass, String key) {
		Cache<String, T> cache = getCache(cacheName, valueClass);
		T t = cache.get(key);
		logger.info("Getting from cache: {} - k:{} v:{}",cacheName,key,t);
		return t;

	}

}