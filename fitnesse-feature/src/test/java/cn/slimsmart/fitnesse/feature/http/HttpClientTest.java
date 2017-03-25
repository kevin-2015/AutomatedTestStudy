package cn.slimsmart.fitnesse.feature.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class HttpClientTest{
	
	@Test
	public void testGet(){
		new HttpClient().get("http://www.baidu.com");
	}

	@Test
	public void testGetResponseCode(){
		Assert.assertEquals(200, new HttpClient().getResponseCode("http://www.baidu.com"));;
	}
	
	@Test
	public void testGetParams(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("wd", "自動化測試");
		System.out.println(new HttpClient().get("http://www.baidu.com",params));
	}
	
	@Test
	public void testPost(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("query", "test");
		System.out.println(new HttpClient().post("https://fanyi.baidu.com/langdetect",params));
	}
	
	@Test
	public void testPostResponseCode(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("query", "test");
		Assert.assertEquals(200, new HttpClient().postResponseCode("https://fanyi.baidu.com/langdetect",params));;
	}
	 
}
