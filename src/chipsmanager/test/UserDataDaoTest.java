package chipsmanager.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chipsmanager.dao.UserDataDao;

public class UserDataDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testValidUser() {
		boolean user_1=new UserDataDao().validUser("2015210983","mkn210983");
		assertEquals(user_1, true);
		
		boolean user_2=new UserDataDao().validUser("2015210968", "123456");
		assertEquals(user_2, false);
		
		boolean user_3=new UserDataDao().validUser("2015210982", "");
		assertEquals(user_3, false);
		
		boolean user_4=new UserDataDao().validUser("", "'\\,./#$532");
		assertEquals(user_4, false);
	}

}
