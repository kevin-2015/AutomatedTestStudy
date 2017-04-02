package cn.slimsmart.fitnesse.feature.db;

import javax.sql.DataSource;

public class MyDataSource {

	private DataSource dataSource = null;

	private String jdbcDriver = null;
	private String connectUrl = null;
	private String username = null;
	private String password = null;
	private int minIdle = 1;
	private int maxActive = 5;

	public MyDataSource() {
	}

	public MyDataSource(String jdbcDriver, String connectUrl, String username, String password) {
		this.jdbcDriver = jdbcDriver;
		this.connectUrl = connectUrl;
		this.username = username;
		this.password = password;
	}

	public MyDataSource(String jdbcDriver, String connectUrl, String username, String password, int minIdle, int maxActive) {
		this.jdbcDriver = jdbcDriver;
		this.connectUrl = connectUrl;
		this.username = username;
		this.password = password;
		this.minIdle = minIdle;
		this.maxActive = maxActive;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public String getConnectUrl() {
		return connectUrl;
	}

	public void setConnectUrl(String connectUrl) {
		this.connectUrl = connectUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
}
