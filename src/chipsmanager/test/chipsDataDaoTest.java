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
		assertEquals(name1, "��2���������");
		String name2=new chipsDataDao().getChipName("54/7401");
		assertEquals(name2, "��2��������ţ�OC��");
	}

	@Test
	public void testGetFunctions() {
		String function1=new chipsDataDao().getFunctions("54/7400");
		assertEquals(function1, "�����");
	}

	@Test
	public void testGetPinNumber() {
		int pin1=new chipsDataDao().getPinNumber("54/7400");
		assertEquals(pin1, 14);
	}

	@Test
	public void testGetDefination() {
		String def1=new chipsDataDao().getDefination("54/7400");
		assertEquals(def1, "1A��4A��1B��4B ����� 1Y��4Y �����");
	}

	@Test
	public void testSearchChipsByMoreCondtion() {
//		chips chips=new chips(1, "54/7400", "��2���������", "�����", 14, "1A��4A��1B��4B ����� 1Y��4Y �����", "00 Ϊ����2 ���������ţ����߼���������54/7400��54/74H00��54/74S00��54/74LS00������·�ṹ��ʽ");
//		chips tmp=new chipsDataDao().searchChipsByModelId("54/7400");
//		assertEquals(chips, tmp);
	}
}
