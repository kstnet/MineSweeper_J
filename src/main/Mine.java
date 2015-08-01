package main;

import java.awt.*;
import java.awt.event.*;

public class Mine extends Frame {
	private static final long serialVersionUID = 1L;
	public Button btn[][];
	public int mine[][];
	// 爆弾数
	public int mine_num = 99;
	public int count=0;
	public int w2=0,h2=0;
	// 縦、横のボタン数
	public int h=16,w=30;
	public int start = 0;
	public boolean flag= false;
	public boolean num = false;
	public boolean gameover = false;

	public Mine() {
		WindowAdpt WAdapter = new WindowAdpt();
		this.addWindowListener(WAdapter);
		setTitle("Mine");
		setLayout(null);
		setSize(610,350);
		setLocation(100,100);
		setBackground(new Color(154,154,154));
		setVisible(true);
		mine = new int[h][w];
		btn = new Button[h][w];

		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				btn[i][j] = new Button();
				btn[i][j].setSize(20,20);
				btn[i][j].setLocation(j*20+5,i*20+25);
				btn[i][j].setVisible(true);
				btn[i][j].addActionListener(lsn);
				add(btn[i][j]);
			}
		}
//		for(int i=0; i<h; i++) {
//			for(int j=0; j<w; j++) {
//			    btn[i][j].setVisible(true);
//			}
//		}
	}

	public void paint(Graphics g) {
		for(int i=0; i<h; i++)
			g.drawLine(5, i*20+25, 605, i*20+25);
		for(int i=0; i<w; i++)
			g.drawLine(i*20+5, 25, i*20+5, 345);

		if(num) {
			/*
			if(gameover) {
				g.setFont(new Font(null,Font.BOLD,30));
				g.setColor(Color.RED);
				g.drawString("GAME OVER", 100, 400);
			}
			*/
			for(int i=0; i<h; i++)
				for(int j=0; j<w; j++) {
					if(mine[i][j] == 9) {
						g.setFont(new Font(null,0,30));
						g.setColor(Color.RED);
						g.drawString("*", j*20+9, i*20+54);
					} else {
						int ii, jj;
						count = 0;
						for(ii=i-1; ii<=i+1; ii++)
							for(jj=j-1; jj<=j+1; jj++) {
								if(0<=ii && 0<=jj)
									if(ii+1<=h && jj+1<=w)
										if(mine[ii][jj] == 9)
											count++;
							}
						String out = Integer.toString(count);
						if(count != 0) {
							g.setFont(new Font(null,Font.BOLD,15));
							if(count == 1)
								g.setColor(Color.BLUE);
							else if(count == 2)
								g.setColor(Color.GREEN);
							else if(count == 3)
								g.setColor(new Color(153,51,51));
							else if(count == 4)
								g.setColor(new Color(0,0,102));
							else if(count== 5)
								g.setColor(new Color(102,0,51));
							else if(count== 6)
								g.setColor(new Color(51,204,255));
							else if(count== 7)
								g.setColor(Color.BLACK);
							else if(count== 8)
								g.setColor(Color.GRAY);
							g.drawString(out, j*20+11, i*20+40);
						}
					}
				}
		}
	}

	ActionListener lsn = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++){
					if(e.getSource() == btn[i][j]) {
						btn[i][j].setVisible(false);
						if(start == 0)
							start++;
							h2 = i;
							w2 = j;
						flag = true;
						break;
					}
				}
				if(flag) {
					flag = false;
					break;
				}
			}
			if(start == 1) {
				setMine();
				start++;
			}

			if(mine[h2][w2] == 9) {
				System.out.println("GameOver\n");
				gameover = true;
				repaint();
			}
		}
	};

	public void setMine() {
		int x,y,set=0;
		while(set<mine_num ) {
			x = (int)(Math.random()*100)%w;
			y = (int)(Math.random()*100)%h;
			if(mine[y][x] != 9)
				if(x!=w2 || y!=h2) {
					mine[y][x] = 9;
					set++;
				}
		}
		String out ="";
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(mine[i][j] == 9)
					out+="●";
				else
					out+="□";
			}
			out+="\n";
		}
		System.out.println(out);

		num = true;
		repaint();
	}

	class WindowAdpt extends java.awt.event.WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent event) {
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new Mine();
	}

}
