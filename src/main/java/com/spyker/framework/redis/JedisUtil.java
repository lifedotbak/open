package com.spyker.framework.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class JedisUtil {


	@Autowired private JedisPool jedisPool;

	private Jedis jedis;

	public Jedis getJedis() {

		if (null == jedis) {
			return jedisPool.getResource();
		} else {
			return jedis;
		}
	}

	public void release(Jedis jedis) {

		if (null != jedis) {
			try {
				jedis.close();
			} catch (Exception e) {
				log.error("close jedis error->{}", e);
			}

		}
	}

	/**
	 * 将给定的空间元素（纬度、经度、名字）添加到指定的键里面。 这些数据会以有序集合的形式被储存在键里面， 从而使得像 GEORADIUS 和
	 * GEORADIUSBYMEMBER 这样的命令可以在之后通过位置查询取得这些元素。
	 * <p>
	 * GEOADD 命令以标准的 x,y 格式接受参数， 所以用户必须先输入经度， 然后再输入纬度。 GEOADD 能够记录的坐标是有限的：
	 * 非常接近两极的区域是无法被索引的。 精确的坐标限制由 EPSG:900913 / EPSG:3785 / OSGEO:41001 等坐标系统定义，
	 * 具体如下：
	 * <p>
	 * 有效的经度介于 -180 度至 180 度之间。 有效的纬度介于 -85.05112878 度至 85.05112878 度之间。
	 * <p>
	 * 当用户尝试输入一个超出范围的经度或者纬度时， GEOADD 命令将返回一个错误。
	 *
	 * @param tableName
	 * @param userId
	 * @param longitude
	 * @param latitude
	 */
	public void geoadd(String tableName, String userId, double longitude, double latitude) {
		Jedis jedis = jedisPool.getResource();

		try {

			jedis.geoadd(tableName, longitude, latitude, userId);
		} catch (Exception e) {
			log.error("geoadd error->{}", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
	}


	/**
	 * 批量添加地理位置
	 *
	 * @param key
	 * @param memberCoordinateMap
	 * @return
	 */
	public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			return jedis.geoadd(key, memberCoordinateMap);
		} catch (Exception e) {
			log.error("geoadd error->{}", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return null;
	}

	/**
	 * 获取地理位置的坐标
	 *
	 * @param key
	 * @param members
	 * @return
	 */
	public List<GeoCoordinate> geopos(String key, String... members) {
		Jedis jedis = null;

		try {
			jedis = jedisPool.getResource();
			return jedis.geopos(key, members);
		} catch (Exception e) {
			log.error("geoadd error->{}", e);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}

		return null;
	}


}