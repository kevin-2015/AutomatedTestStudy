package cn.slimsmart.fitnesse.feature.db;

public class DbSlimSetup {

	public static String DEFAULT_CONNECTION_POOL_NAME = "_default_pool";
	public static int DEFAULT_CONNECTION_POOL_MIN_IDLE = 1;
	public static int DEFAULT_CONNECTION_POOL_MAX_AXTIVE = 5;
	public static int DEFAULT_WAIT_TIMEOUT = 30000;

	public DbSlimSetup(String jdbcDriver, String jdbcUrl, String username, String password) throws Exception {
		DbConnectionFactory.getDataSource(DEFAULT_CONNECTION_POOL_NAME, jdbcDriver, jdbcUrl, username, password, DEFAULT_CONNECTION_POOL_MIN_IDLE,
				DEFAULT_CONNECTION_POOL_MAX_AXTIVE);
	}
	public DbSlimSetup(String jdbcDriver, String jdbcUrl, String username, String password, int minIdle, int maxActive) throws Exception {
		DbConnectionFactory.getDataSource(DEFAULT_CONNECTION_POOL_NAME, jdbcDriver, jdbcUrl, username, password, minIdle, maxActive);

	}
	public DbSlimSetup(String connectionPoolName, String jdbcDriver, String jdbcUrl, String username, String password, int minIdle, int maxActive)
			throws Exception {
		DbConnectionFactory.getDataSource(connectionPoolName, jdbcDriver, jdbcUrl, username, password, minIdle, maxActive);
	}
}
