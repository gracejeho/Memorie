package pack;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Partie {

	private Button bu[];

	private int mode;
	private int niveau;
	private int theme;
	private static int n;
	private Tableau t;
	private int form;

	public Partie(int nombreimages, int taille, int form) {   //partie de jeu
		this.form = form;

		this.bu = new Button[nombreimages];

		if (form == 0) {
			for (int i = 0; i < 16; i++) {
				bu[i] = new Button("image/cartecachee.png");
			}
			bu[26] = new Button("image/f2.jpg");
			bu[27] = new Button("image/f3.jpg");
			bu[28] = new Button("image/f10.jpg");
			bu[29] = new Button("image/f5.jpg");
			bu[30] = new Button("image/f6.jpg");
			bu[31] = new Button("image/f7.jpg");
			bu[32] = new Button("image/f8.jpg");
			bu[33] = new Button("image/f1.jpg");

			bu[40] = new Button("image/fondfleurs.jpg");
		}

		if (form == 1) {
			for (int i = 0; i < 20; i++) {
				bu[i] = new Button("image/cartecacheee.png");
			}

			bu[40] = new Button("image/fondanimaux.jpg");
			bu[26] = new Button("image/a1.jpg");
			bu[27] = new Button("image/a2.jpg");
			bu[28] = new Button("image/a3.jpg");
			bu[29] = new Button("image/a4.jpg");
			bu[30] = new Button("image/a5.jpg");
			bu[31] = new Button("image/a6.jpg");
			bu[32] = new Button("image/a7.jpg");
			bu[33] = new Button("image/a8.jpg");
			bu[34] = new Button("image/a9.jpg");
			bu[35] = new Button("image/a10.jpg");

		}
		if (form == 2) {
			for (int i = 0; i < 24; i++) {
				bu[i] = new Button("image/cartecachee.png");
			}

			bu[40] = new Button("image/fondpaysage.jpg");
			bu[26] = new Button("image/pa1.jpg");
			bu[27] = new Button("image/pa2.jpg");
			bu[28] = new Button("image/pa3.jpg");
			bu[29] = new Button("image/pa4.jpg");
			bu[30] = new Button("image/pa5.jpeg");
			bu[31] = new Button("image/pa6.jpg");
			bu[32] = new Button("image/pa7.jpg");
			bu[33] = new Button("image/pa8.jpg");
			bu[34] = new Button("image/pa9.jpg");
			bu[35] = new Button("image/pa10.jpg");
			bu[36] = new Button("image/pa11.jpg");
			bu[37] = new Button("image/pa12.jpg");

		}
		bu[38] = new Button("image/ga.png");
		bu[39] = new Button("image/gane.png");

		t = new Tableau(taille, form);

		t.alea();
	}

	public Tableau getT() {
		return t;
	}

	public Button[] getBu() {
		return bu;
	}

	public void setBu(Button[] bu) {
		this.bu = bu;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public int getTheme() {
		return theme;
	}

	public void setTheme(int theme) {
		this.theme = theme;
	}

	public static int getN() {
		return n;
	}

	public static void setN(int n) {
		Partie.n = n;
	}

	public String viewLevel(int niveau, int mode) {
		if (mode == 0) {
			return "mode challenge";
		}

		if (mode == 1) {
			if (niveau == 0) {
				return "Niveau facile";
			}
			if (niveau == 1) {
				return "Niveau moyen";
			}
			if (niveau == 2) {
				return "Niveau difficile";
			}
		}
		return "mode challenge";
	}

	public String viewreste(int nombrecoups, int temps, int mode) {
		if (mode == 0) {
			int a = temps / 1000;
			return "Temps restant   " + a / 60 + " : " + a % 60;
		}

		if (mode == 1) {
			return "Coups restants " + nombrecoups;
		}

		return " ";
	}

	public boolean arretjeu(int mode, int temps, int coups) {
		if (mode == 0 && temps <= 0) {
			return temps <= 0;
		}

		if (mode == 1 && coups <= 0) {
			return coups <= 0;
		}
		if (gagner()) {
			return true;
		}

		return false;

	}

	public boolean gagner() {
		boolean flags = true;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (t.getTab()[i][j] != -1)
					flags = false;
			}
		}
		return flags;
	}

	public void draw(int x, int y, int ecart, Graphics g) throws SlickException {
		g.drawImage(bu[40].init(), 0, 0);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (t.getTab()[i][j] < 100 && t.getTab()[i][j] != -1) {
					g.drawImage(bu[i].init(), x + ecart * i, y + ecart * j);
				}

				if (t.getTab()[i][j] >= 100 && t.getTab()[i][j] != -1 && bu[25 + t.getTab()[i][j] / 100] != null) {
					g.drawImage(bu[25 + t.getTab()[i][j] / 100].init(), x + ecart * i, y + ecart * j);
				}
			}
		}
	}

	public void messagefinal(boolean c, Graphics g) throws SlickException {
		int n = (int) (Math.random() * 2);
		if (n == 0)
			g.setColor(Color.blue);
		if (n == 1)
			g.setColor(Color.cyan);
		if (n == 2)
			g.setColor(Color.green);
		if (n == 3)
			g.setColor(Color.red);
		if (n == 4)
			g.setColor(Color.white);

		if (c) {
			g.fillRect(0, 200, 1000, 300);
			bu[39].draw(60, 310, g);
		} else {
			bu[38].draw(60, 310, g);
		}

	}

}
