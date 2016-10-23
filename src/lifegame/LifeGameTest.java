package lifegame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LifeGameTest {

	private GameMap gameMap;
	int[][] map;
	@Before
	public void setUp() throws Exception {
		gameMap=new GameMap();
		gameMap.initMap();
	}

	@After
	public void tearDown() throws Exception {
		
	}


	@Test
	public void testGetNearCellStatusCount() {
		//fail("Not yet implemented");
		
		//���ԣ�1,1��ϸ����Χ�Ļ�ϸ������1����֮����Ա߽�ֵ�����ֱֵ���޸�����
		int[][] map = {
				{0,0,0,0,0},
				{0,1,0,0,0},
				{0,0,1,1,0},
				{0,0,0,0,0},
				{0,0,0,1,1},
		
			};
		int count=gameMap.getNearCellStatusCount(1,1, map);
		assertEquals(count,1);
		int count1=gameMap.getNearCellStatusCount(2,2, map);
		assertEquals(count1,2); 
	}

	@Test
	public void testChangeStatus() {
		//fail("Not yet implemented");
		//����״̬�ı䣬֮����Ա߽�ֵ�����ֱֵ���޸�����
		int[][] tempStatus={
				{0,0,1,0,0},
				{0,1,0,0,0},
				{0,0,1,0,0},
				{0,0,1,1,0},
		};
		int[][] nextStatus={
				{0,0,1,0,0},
				{0,1,0,0,0},
				{0,0,1,0,0},
				{0,0,1,1,0},
		};
		gameMap.changeStatus(nextStatus, tempStatus);
		assertEquals(1,nextStatus[2][2]);//���ݹ����֪ϸ��Ϊ�
		
	}
	@Test
	public void testChangeStatusmap() {
		//fail("Not yet implemented");
		//���ô���Ĳ�������������ϸ�����ܳ�����������0/1��֮���״̬
		int[][] tempStatus={
				{0,0,2,0,0},
				{0,2,0,0,0},
				{0,0,1,0,0},
				{0,0,1,1,0},
		};
		int[][] nextStatus={
				{0,0,1,0,0},
				{0,1,0,0,0},
				{0,0,1,0,0},
				{0,0,1,1,0},
		};
		gameMap.changeStatus(nextStatus, tempStatus);
		assertEquals(1,nextStatus[2][2]);//��ǰϸ��״̬����
		gameMap.changeStatus(nextStatus, tempStatus);
		assertEquals(1,nextStatus[1][1]);//��Χϸ��״̬�������ھӼ����ĺ����������
	}
}