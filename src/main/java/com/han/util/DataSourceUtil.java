package com.han.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 	数据库连接池工具类
 * @author junki
 * @date 2020年1月16日
 */
public final class DataSourceUtil {

	private DataSourceUtil() {};
	
	private static Properties pro = new Properties();
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	private static DataSource dataSource = null;
	
	static {
		// 1.将配置文件加载到properties集合内 
		InputStream is = DataSourceUtil.class.getClassLoader().getResourceAsStream("druid.properties");
		try {
			pro.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 2.使用properties集合内的配置参数初始化一个连接池
		try {
			dataSource = DruidDataSourceFactory.createDataSource(pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 获取数据库连接池
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	// 获取数据库连接
	public static Connection getConnection() {
		if (threadLocal.get() == null) {
			try {
				threadLocal.set(dataSource.getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return threadLocal.get();
	}
	
	// 关闭资源
	public static void closeAll(Connection con, Statement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
				// 销毁threadLocal中的连接对象
				threadLocal.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
