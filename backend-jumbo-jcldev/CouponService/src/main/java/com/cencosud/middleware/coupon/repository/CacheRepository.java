package com.cencosud.middleware.coupon.repository;

public interface CacheRepository {

	<T> void put(String cacheName, Class<T> valueClass, String key, T value);

	<T> T get(String cacheName, Class<T> valueClass, String key);

	<T> void createCache(String cacheName, Class<T> valueClass);

}