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
		this.setTitle("������Ϸ");
		this.add(gameMap);
		MenuItem menuItem1=new MenuItem("�������");
		
		MenuItem menuItem2=new MenuItem("�ֵ�����");
		
		Menu gameControl=new Menu("��Ϸ");
		gameControl.setEnabled(false);
		MenuItem startGame=new MenuItem("��ʼ��Ϸ");
		startGame.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						gameMap.startGame();
					}
			
				});
		MenuItem endGame=new MenuItem("ֹͣ��Ϸ");
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
		
		Menu menu=new Menu("ģʽѡ��");
		menu.add(menuItem1);
		menu.add(menuItem2);
		menu.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						if(e.getActionCommand().equals("�������"))
						{
							gameMap.initMap();
							gameMap.cellProducedByRandomly(gameMap.getMap());
							gameMap.startGame();
						}
						else if(e.getActionCommand().equals("�ֵ�����"))
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

