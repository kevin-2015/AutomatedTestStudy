package cn.slimsmart.fitnesse.feature.http;

import org.junit.Test;

import cn.slimsmart.fitnesse.feature.db.DbSlimSelectQuery;
import cn.slimsmart.fitnesse.feature.db.DbSlimSetup;

public class DBTest {
	@Test
	public void testQuery() throws Exception {
		new DbSlimSetup("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/test", "root", "123456");
		DbSlimSelectQuery query = new DbSlimSelectQuery("select * from user");
		System.out.println(query.query());
	}
}
