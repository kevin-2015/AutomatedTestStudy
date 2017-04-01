package cn.slimsmart.fitnesse.demo;

import java.util.List;

public class AddCalculator {
	
	private int a;
	private int b;
	public void setA(int a) {
		this.a = a;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int add(){
		return a+b;
	}
	 public void execute() {  } 
	 public void reset() {  }  
	 public void table(List<List<String>> table) {}
}
