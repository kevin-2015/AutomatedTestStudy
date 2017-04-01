package cn.slimsmart.fitnesse.demo;

import fit.Fixture;

/**
 * 累计学生个数
 * @author zhutw
 *
 */
public class StudentActionFixture extends Fixture{
	
	private int count;
	
	public void init(){
		count=1;
	}
	
	public void addStudent(int count){
		this.count += count;
	}
	
	public int count(){
		return count;
	}
}
