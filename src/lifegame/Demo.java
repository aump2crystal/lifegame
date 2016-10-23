package lifegame;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Demo extends JFrame{
	
	private JButton button;
	
	public Demo()
	{
		GameMap gameMap=new GameMap();
		this.setSize(410,500);
		this.setTitle("生命游戏");
		this.add(gameMap);
		MenuItem menuItem1=new MenuItem("随机生成");
		
		MenuItem menuItem2=new MenuItem("手点生成");
		
		Menu gameControl=new Menu("游戏");
		gameControl.setEnabled(false);
		MenuItem startGame=new MenuItem("开始游戏");
		startGame.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						gameMap.startGame();
					}
			
				});
		MenuItem endGame=new MenuItem("停止游戏");
		endGame.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						gameMap.endGame();
					}
			
				});
		gameControl.add(startGame);
		gameControl.add(endGame);
		
		Menu menu=new Menu("模式选择");
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						if(e.getActionCommand().equals("随机生成"))
						{
							gameMap.initMap();
							gameMap.cellProducedByRandomly(gameMap.getMap());
							gameMap.startGame();
						}
						else if(e.getActionCommand().equals("手点生成"))
						{
							gameMap.initMap();
							gameControl.setEnabled(true);
							gameMap.cellProducedByMouse();
						}
					}
		
				});
	
		MenuBar menuBar=new MenuBar();
		menuBar.add(menu);
		menuBar.add(gameControl);
		this.setMenuBar(menuBar);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
	}}

