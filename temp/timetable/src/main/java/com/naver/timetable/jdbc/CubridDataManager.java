/*
 * @(#)DataManager.java $version 2014. 8. 5.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NAVER Corp. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.naver.timetable.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp.AbandonedConfig;
import org.apache.commons.dbcp.AbandonedObjectPool;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;

/**
 * 스프링용 싱글톤 빈으로 만들것.
 * 사용 하려면 init 으로 사용
 * @author younghan
 */
public class CubridDataManager {
	private volatile boolean defaultAutoCommit = true;
	private volatile boolean defaultReadOnly = false;
	
	private volatile DataSource dataSource = null;
	
	private String strDriver = "cubrid.jdbc.driver.CUBRIDDriver";
	private String strDBConn = "jdbc:cubrid:10.99.204.57:30102:nstore:::?althosts=10.99.204.57:30102&charset=utf-8&connectTimeout=5&queryTimeout=5";
	private String strUserID = "nstore";
	private String strUserPW = "nstore";
	private int maxActive;
	private int maxIdle;
	private int minIdle;
	private long timeBetweenEvictionRunsMillis;
//	private long minEvictableIdleTimeMillis;
	private int initialSize;
	
	public CubridDataManager()	{
	}
	
	public CubridDataManager(String strDriver, String strDBConn, String strUserID, String strUserPW)	{
		this.strDriver = strDriver;
		this.strDBConn = strDBConn;
		this.strUserID = strUserID;
		this.strUserPW = strUserPW;
	}
	
	
	public String getStrDriver() {
		return strDriver;
	}

	public void setStrDriver(String strDriver) {
		this.strDriver = strDriver;
	}

	public String getStrDBConn() {
		return strDBConn;
	}

	public void setStrDBConn(String strDBConn) {
		this.strDBConn = strDBConn;
	}

	public String getStrUserID() {
		return strUserID;
	}

	public void setStrUserID(String strUserID) {
		this.strUserID = strUserID;
	}

	public String getStrUserPW() {
		return strUserPW;
	}

	public void setStrUserPW(String strUserPW) {
		this.strUserPW = strUserPW;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	/**
	 * jdbc에 사용할 드라이버를 로딩하기 위함.
	 * @param strDriver
	 * @param strDBConn
	 * @param strUserID
	 * @param strUserPW
	 */
	public void initDriver(){
		try {
			Class.forName(strDriver);
			Connection objConn = DriverManager.getConnection(strDBConn, strUserID, strUserPW);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//타임아웃 되면 처리를 할것인지 설정.
		AbandonedConfig abandonedConfig = new AbandonedConfig();
		abandonedConfig.setRemoveAbandoned(true);
		
		
		AbandonedObjectPool connectionPool = new AbandonedObjectPool(null, abandonedConfig);
		
		connectionPool.setMaxActive(maxActive);
		connectionPool.setMaxIdle(maxIdle);
		connectionPool.setMinIdle(minIdle);
		connectionPool.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//		connectionPool.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		
		ConnectionFactory driverConnFactory = new DriverManagerConnectionFactory(strDBConn, strUserID, strUserPW);
		PoolableConnectionFactory connFactory = 
                new PoolableConnectionFactory(driverConnFactory,
                                              connectionPool,
                                              null,
                                              null,
                                              defaultReadOnly,
                                              defaultAutoCommit);
		
		PoolingDataSource pds = new PoolingDataSource(connectionPool);
        dataSource = pds;

        try {
            for (int i = 0 ; i < initialSize ; i++) {
                connectionPool.addObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public Connection getConnection()	{
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void close(ResultSet rs)	{
		try	{
			if(rs != null)	{
				rs.close();
			}
		} catch(Exception e)	{
			e.printStackTrace();
		}
	}
	
	public void close(Statement stmt)	{
		try	{
			if(stmt != null)	{
				stmt.close();
			}
		} catch(Exception e)	{
			e.printStackTrace();
		}
	}

	
}
