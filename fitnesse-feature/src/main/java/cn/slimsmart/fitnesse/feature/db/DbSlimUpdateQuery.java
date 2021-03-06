package cn.slimsmart.fitnesse.feature.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DbSlimUpdateQuery {

	private String connectionPoolName;
	private String sql;

	public DbSlimUpdateQuery(String sql) {
		this(DbSlimSetup.DEFAULT_CONNECTION_POOL_NAME, sql);
	}

	public DbSlimUpdateQuery(String connectionPoolName, String sql) {
		this.connectionPoolName = connectionPoolName;
		sql = sql.replaceAll("\\n", " ");
		sql = sql.replaceAll("\\t", " ");
		sql = sql.replaceAll("<br/>", " ");
		sql = sql.trim();
		this.sql = sql;
	}

	public void table(List<List<String>> table) {
	}

	public List<Object> query() {
		List<List<List<String>>> dataTable = getDataTable();
		return new ArrayList<Object>(dataTable);
	}

	public String rowsUpdated() {
		List<List<List<String>>> dataTable = getDataTable();
		return dataTable.get(0).get(0).get(1);
	}

	protected List<List<List<String>>> getDataTable() {
		DataSource dataSource = DbConnectionFactory.getDataSource(connectionPoolName);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<List<List<String>>> dataTable = new ArrayList<List<List<String>>>();
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(true);
			stmt = conn.createStatement();
			int rowsUpdated = stmt.executeUpdate(sql);

			ArrayList<List<String>> dataRow = new ArrayList<List<String>>();
			ArrayList<String> dataItem = new ArrayList<String>();
			dataItem.add(String.valueOf("rowsUpdated"));
			dataItem.add(String.valueOf(rowsUpdated));
			dataRow.add(dataItem);
			dataTable.add(dataRow);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (rset != null)
					rset.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return dataTable;
	}
}
