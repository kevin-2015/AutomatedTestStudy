package cn.slimsmart.fitnesse.feature.db;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

public class DbConnectionFactory {

	private static Map<String, MyDataSource> connectionDetailsMap = new ConcurrentHashMap<String, MyDataSource>();

	public static synchronized DataSource getDataSource(String connectionPoolName) {
		MyDataSource myDataSource = connectionDetailsMap.get(connectionPoolName);
		if (null == myDataSource) {
			return null;
		} else {
			return myDataSource.getDataSource();
		}
	}

	public static synchronized DataSource getDataSource(String connectionPoolName, String jdbcDriver, String connectUrl, String username, String password,
			int minIdle, int maxActive) throws Exception {
		if (null == connectionDetailsMap.get(connectionPoolName)) {
			connectionDetailsMap.put(connectionPoolName, new MyDataSource());
		}
		MyDataSource myDataSource = connectionDetailsMap.get(connectionPoolName);
		if (null != myDataSource.getDataSource() && String.valueOf(jdbcDriver).equals(myDataSource.getJdbcDriver())
				&& String.valueOf(connectUrl).equals(myDataSource.getConnectUrl()) && String.valueOf(username).equals(myDataSource.getUsername())
				&& String.valueOf(password).equals(myDataSource.getPassword()) && minIdle == myDataSource.getMinIdle()
				&& maxActive == myDataSource.getMaxActive()) {
			return myDataSource.getDataSource();
		}
		DruidDataSource druidDatasource = new DruidDataSource();
		druidDatasource.setDriverClassName(jdbcDriver);
		druidDatasource.setUrl(connectUrl);
		druidDatasource.setUsername(username);
		druidDatasource.setPassword(password);
		druidDatasource.setMinIdle(minIdle);
		druidDatasource.setMaxActive(maxActive);
		druidDatasource.setInitialSize(minIdle);
		myDataSource.setDataSource(druidDatasource);
		myDataSource.setConnectUrl(connectUrl);
		myDataSource.setJdbcDriver(jdbcDriver);
		myDataSource.setMaxActive(maxActive);
		myDataSource.setMinIdle(minIdle);
		myDataSource.setPassword(password);
		myDataSource.setUsername(username);
		return druidDatasource;
	}

}
