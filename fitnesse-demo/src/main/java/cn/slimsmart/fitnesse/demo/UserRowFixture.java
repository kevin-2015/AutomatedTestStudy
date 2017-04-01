package cn.slimsmart.fitnesse.demo;

import fit.RowFixture;
/**
 * @author zhutw
 *
 */
public class UserRowFixture extends RowFixture{

	@Override
	public Object[] query() throws Exception {
		return new Object[]{new User("1","jack",23),new User("2","tom",34)};
	}

	@Override
	public Class<?> getTargetClass() {
		return User.class;
	}

}
