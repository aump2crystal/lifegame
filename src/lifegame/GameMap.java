package lifegame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameMap extends JPanel{
	private final int ALIVE=1;
	private final int DEAD=0;
	private final int ROWS=20;
	private final int COLUMNS=20;
	private final int WIDTH=20;
	private final int HEIGHT=20;
	private int map[][];
	private int nextStatus[][];
	private int tempStatus[][];//定义当前状态与下一状态
	
	/**
	 * 刷新时间
	 */
	private Timer timer;
	private final int INTERVAL=1000;
	
	
	//地图边界的外一层都设置为0
	public void initMap()
	{
		map=new int[ROWS+2][COLUMNS+2];
		nextStatus=new int[ROWS+2][COLUMNS+2];
		tempStatus=new int[ROWS+2][COLUMNS+2];
	}
	
	/**
	 * 细胞的状态随机生成,地图边界的外一层都设置为0
	 */
	public void cellProducedByRandomly(int[][] map)
	{
		for(int i=1;i<map.length-1;i++)
			for(int j=1;j<map[0].length-1;j++)
				map[i][j]=((int)(Math.random()*10))%2;
		

		
	}
	
	public void cellProducedByMouse()
	{
		this.addMouseListener(new MouseHandler());
	}
	
	private class MouseHandler extends MouseAdapter{
		public void mousePressed(MouseEvent event)
		{
			
		/*	if(gameOver()){
				timer.stop();
				initMap();
				for(int i=0;i<ROWS+2;i++)
					for(int j=0;j<COLUMNS+2;j++)
					{
						nextStatus[i][j]=map[i][j];
						tempStatus[i][j]=map[i][j];
					}
			}*/
			Point point=event.getPoint();
			int row=0;
			int column=0;
			row=(int)(point.getX()/HEIGHT);
			column=(int)(point.getY()/WIDTH);
			map[row+1][column+1]=(map[row+1][column+1]+1)%2;
			for(int i=0;i<ROWS+2;i++)
				for(int j=0;j<COLUMNS+2;j++)
				{
					nextStatus[i][j]=map[i][j];
					tempStatus[i][j]=map[i][j];
				}
			repaint();
		}
	}
	
	public GameMap()
	{
		initMap();
	}
	
	//获得当前细胞周围的活细胞细胞的数量
	public int getNearCellStatusCount(int row,int column,int[][] tempStatus)
	{
	
		int aliveCellCount=0;
	
		for(int i=row-1;i<=row+1;i++)
			for(int j=column-1;j<=column+1;j++)
			{
				if(i==row&&j==column)
					continue;
				
				aliveCellCount+=tempStatus[i][j];
			}
		return aliveCellCount;
	}
	
	public void changeStatus(int[][] nextStatus,int[][] tempStatus)
	{
		for(int i=1;i<tempStatus.length-1;i++)
			for(int j=1;j<tempStatus[0].length-1;j++)
			{
				switch(getNearCellStatusCount(i,j,tempStatus))
				{
				case 0:
				case 1:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
					nextStatus[i][j]=DEAD;
					break;
				case 2:nextStatus[i][j]=tempStatus[i][j];
					break;
				case 3:nextStatus[i][j]=ALIVE;
					break;
				default:break;
				}
			}
		
		changeToNext(tempStatus,nextStatus);
	}
	
	/**
	 * 开始游戏
	 */
	
	public boolean startGame()
	{
		for(int i=0;i<ROWS+2;i++)
			for(int j=0;j<COLUMNS+2;j++)
			{
				nextStatus[i][j]=map[i][j];
				tempStatus[i][j]=map[i][j];
			}
		
		timer=new Timer(INTERVAL,new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						changeStatus(nextStatus,tempStatus);
						repaint();
					}
			
				});
		timer.start();
		return true;
	}
	
	/**
	 * 跳转到下一状态
	 */
	
	public void changeToNext(int[][] tempStatus,int[][] nextStatus)
	{
		for(int i=1;i<tempStatus.length;i++)
			for(int j=1;j<tempStatus[0].length;j++)
				tempStatus[i][j]=nextStatus[i][j];
	}
	
	/**
	 * 绘图
	 */
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(int i=1;i<ROWS+1;i++)
			for(int j=1;j<COLUMNS+1;j++)
			{
				if(nextStatus[i][j]==ALIVE)
					g.fillRect(WIDTH*(i-1), HEIGHT*(j-1), WIDTH, HEIGHT);
				else g.drawRect(WIDTH*(i-1), HEIGHT*(j-1), WIDTH, HEIGHT);
			}
	}
	
	//结束游戏
	public void endGame()
	{
		timer.stop();
		initMap();
		repaint();
	}
	
	public int[][] getMap()
	{
		return map.clone();
	}

}