package cn.slimsmart.fitnesse.demo;

import fitnesse.fixtures.RowEntryFixture;

public class UserRowEntryFixture extends RowEntryFixture{

	private int age;
	
	@Override
	public void enterRow() throws Exception {
		if(age<18){
			throw new Exception("未满18岁");
		}
	}

}
