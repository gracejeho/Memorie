package pack;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Menu {

	private Button[] b;
	private static int a;
	private Tableau t;

	public Menu(int a) {              // menu du jeu
		this.b = new Button[a];
		b[0] = new Button("image/fondjeu.jpg");
		b[1] = new Button("image/fondmenu.jpg");
		b[2] = new Button("image/m1.png");
		b[3] = new Button("image/m2.png");
		b[4] = new Button("image/m3.png");
		b[5] = new Button("image/retour.png");
		b[6] = new Button("image/fondfleurs.jpg");
		b[7] = new Button("image/p1.png");
		b[8] = new Button("image/p2.png");
		b[9] = new Button("image/p3.png");
		b[10] = new Button("image/p4.png");
		b[11] = new Button("image/p6.png");
		b[12] = new Button("image/p7.png");
		b[13] = new Button("image/p9.png");
		b[14] = new Button("image/p10.png");
		b[15] = new Button("image/p11.png");
		b[16] = new Button("image/p12.png");
		b[17] = new Button("image/p7.png");
		b[18] = new Button("image/fondanimaux");
		t = new Tableau(3, 3);
		t.rect(0, 0, 3, 3, -1);
		t.rect(0, 0, 3, 1, 0);
		t.getTab()[0][0]=-1;

	}

	public Tableau getT() {
		return t;
	}

	public void fondmenu(int n, Graphics g) throws SlickException {

		if (n == 0) {
			b[0].draw(0, 0, g);
		}

		if (n == 1) {
			b[1].draw(0, 0, g);

		}

	}

	public void mainmenu(int x, int y, Graphics g) throws SlickException {
		for (int i = 0; i < 3; i++) {
			b[i + 2].draw(x, y + 200 * i, g);
		}
	}

	public void drawparametre(Graphics g) {
		g.setColor(Color.blue);
		for (int i = 1; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (t.getTab()[i][j] != -1) {
					g.fillRect(333 * j, 350 + 100 * i, 200, 20);
				}
			}
		}
	}

	public void option(boolean c, int x, int y) {
		if (c) {
			
			if (b[10].appartenir(x, y, 250, 50)) {
				t.preopption(1, 0);
			}
			if (b[12].appartenir(x, y, 250, 50)) {
				t.preopption(2, 1);
			}
			if (b[11].appartenir(x, y, 250, 50)) {
				t.preopption(2, 0);
			}
			if (b[17].appartenir(x, y, 250, 50)) {
				t.preopption(1, 1);
			}
			if (b[13].appartenir(x, y, 250, 50)) {
				t.preopption(2, 2);
			}

		}

	}

	public void parametre(Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.fillRect(0, 0, 1000, 1000);

		g.setColor(Color.blue);
		g.fillRect(0, 50, 1000, 150);
		b[7].draw(350, 100, g);
		
		b[10].draw(0, 400, g);
		b[11].draw(0, 500, g);
		b[14].draw(0, 600, g);

		
		b[17].draw(333, 400, g);
		b[12].draw(333, 500, g);
		b[15].draw(333, 600, g);

		b[13].draw(666, 500, g);

		b[16].draw(666, 600, g);

	}

	public Button[] getB() {
		return b;
	}

	public void setB(Button[] b) {
		this.b = b;
	}

	public static int getA() {
		return a;
	}

	public static void setA(int a) {
		Menu.a = a;
	}

}
