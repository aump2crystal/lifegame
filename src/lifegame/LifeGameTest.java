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
		
		//测试（1,1）细胞周围的活细胞，有1个，之后测试边界值与错误值直接修改坐标
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
		//测试状态改变，之后测试边界值与错误值直接修改坐标
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
		assertEquals(1,nextStatus[2][2]);//根据规则可知细胞为活；
		
	}
	@Test
	public void testChangeStatusmap() {
		//fail("Not yet implemented");
		//设置错误的测试用例，考虑细胞可能出现生与死（0/1）之外的状态
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
		assertEquals(1,nextStatus[2][2]);//当前细胞状态出错；
		gameMap.changeStatus(nextStatus, tempStatus);
		assertEquals(1,nextStatus[1][1]);//周围细胞状态出错，即邻居计数的函数输入出错；
	}
}