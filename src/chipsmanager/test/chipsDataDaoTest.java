package chipsmanager.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import chipsmanager.dao.chipsDataDao;
import chipsmanager.javabean.chips;

public class chipsDataDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetChipId() {
		int id1=new chipsDataDao().getChipId("54/7400");
		assertEquals(id1,1);
		
	}

	@Test
	public void testGetChipName() {
		String name1=new chipsDataDao().getChipName("54/7400");
		assertEquals(name1, "四2输入与非门");
		String name2=new chipsDataDao().getChipName("54/7401");
		assertEquals(name2, "四2输入与非门（OC）");
	}

	@Test
	public void testGetFunctions() {
		String function1=new chipsDataDao().getFunctions("54/7400");
		assertEquals(function1, "与非门");
	}

	@Test
	public void testGetPinNumber() {
		int pin1=new chipsDataDao().getPinNumber("54/7400");
		assertEquals(pin1, 14);
	}

	@Test
	public void testGetDefination() {
		String def1=new chipsDataDao().getDefination("54/7400");
		assertEquals(def1, "1A－4A，1B－4B 输入端 1Y－4Y 输出端");
	}

	@Test
	public void testSearchChipsByMoreCondtion() {
//		chips chips=new chips(1, "54/7400", "四2输入与非门", "与非门", 14, "1A－4A，1B－4B 输入端 1Y－4Y 输出端", "00 为四组2 输入端与非门（正逻辑），共有54/7400、54/74H00、54/74S00、54/74LS00四种线路结构形式");
//		chips tmp=new chipsDataDao().searchChipsByModelId("54/7400");
//		assertEquals(chips, tmp);
	}
}
