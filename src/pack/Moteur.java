package pack;

import java.awt.Font;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;

// partie ou on assemble les differentes objets
public class Moteur extends BasicGame {

	Font f;
	TrueTypeFont ttf;
	Font fa;
	TrueTypeFont ttfa;
	int testsouris;
	int test; // indique a quel niveau du jeu on se trouve
	int time; // chronometre d affichage des cartes
	int timer; // chronometre d'affichage de la page d'acceuil du jeu
	Partie pa;
	Menu m;
	int[] ta; // contient les indices des cartes retournées
	int form; // niveau de difficulte de la partie , 0-->facile , 1--->moyen 2---->difficile
	int positionx; // abcisisse a la quelle le plateau de jeu est dessiné
	int positiony;

	int comp;
	int nombrecoups; // nombre de coups maximum pendant la partie
	int tempsjeu; // chonometre du jeu
	Music mmenu;
	Music mpartie; // musique du jeu
	Sound select;
	Sound trouve; // effet sonore lorsque deux cartes sont identiques

	boolean flags; // indique si le jeu continue ou pas

	public Moteur(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		form = 0;
		pa = new Partie(100, 5, form);
		ta = new int[4];
		Tableau.ini(ta);
		m = new Menu(100);
		test = 0;
		time = 0;
		testsouris = 0;
		comp = 0;
		timer = 0;
		positionx = 0;
		positiony = 0;
		f = new Font("Algerian", Font.BOLD, 32);
		ttf = new TrueTypeFont(f, true);
		fa = new Font("Algerian", Font.BOLD, 500);
		ttfa = new TrueTypeFont(f, true);
		nombrecoups = 20;
		tempsjeu = 200000;
		mmenu = new Music("sounds/smenu.ogg");
		mmenu.setVolume(0.5f);
		mmenu.loop();
		select = new Sound("sounds/select.ogg");
		trouve = new Sound("sounds/trouve.wav");
		flags = true;

		mmenu.play();

	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub

		if (test == 0) {
			m.fondmenu(0, g);
		}

		if (test == 1) {
			m.fondmenu(1, g);
			m.mainmenu(100, 100, g);
		}

		if (test == 11) {
			pa.draw(positionx, positiony, 160, g);
			g.setColor(Color.white);
			g.fillRect(0, 850, 1000, 150);
			g.setColor(Color.black);
			g.fillRect(0, 880, 1000, 120);
			ttf.drawString(0, 900,
					pa.viewLevel(m.getT().getTab()[2][0] + m.getT().getTab()[2][1] + m.getT().getTab()[2][2] + 2,
							m.getT().getTab()[1][0] + m.getT().getTab()[1][1] + m.getT().getTab()[1][2] + 2));
			ttf.drawString(0, 950, pa.viewreste(nombrecoups, tempsjeu,
					m.getT().getTab()[1][0] + m.getT().getTab()[1][1] + m.getT().getTab()[1][2] + 2));

			if (!flags)

			{
				pa.messagefinal(pa.gagner(), g);
			}
		}

		if (test == 12) {
			m.parametre(g);
			m.drawparametre(g);

		}

		if (test == 13) {
			g.setColor(Color.white);
			ttf.drawString(500, 0, "AIDE");
			ttf.drawString(100, 100, " Le jeu consiste a retouner des cartes deux");
			ttf.drawString(0, 200, "à deux jusqu'à ce qu'elles soient identiques et dans ce ");
			ttf.drawString(0, 300, "cas, elles disparaissent. en fonction du mode , un temps ou un nombre  ");
			ttf.drawString(0, 400, "limite est imparti.  ");

		}

		if (test > 1) {
			m.getB()[5].draw(750, 900, g);
		}
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input inp = gc.getInput();
		boolean click = inp.isMousePressed(Input.MOUSE_LEFT_BUTTON);
		int xm = inp.getMouseX();
		int ym = inp.getMouseY();

		if (test == 0) {
			time++;
			if (time > 25) {
				test = 1;
			}
		}
		time++;
		if (test == 1)

		{

			if (click && m.getB()[2].appartenir(xm, ym, 750, 100)) {
				select.play();
				xm = 900;
				ym = 900;
				form = m.getT().getTab()[2][0] + m.getT().getTab()[2][1] + m.getT().getTab()[2][2] + 2;
         
				
				
				pa = new Partie(100, 5, form);

				test = 11;
				tempsjeu = 200000;
				flags = true;
				nombrecoups = 20;

			} else if (click && m.getB()[3].appartenir(xm, ym, 750, 100)) {
				select.play();
				test = 12;
			} else if (click && m.getB()[4].appartenir(xm, ym, 750, 100)) {
				select.play();
				test = 13;
			}

		}

		if (test == 11) {

			positionx++;

			if (positionx > 200) {
				positionx = -800;
			}
			flags = !pa.arretjeu(m.getT().getTab()[1][0] + m.getT().getTab()[1][1] + m.getT().getTab()[1][2] + 2,
					tempsjeu, nombrecoups);

			if (click && xm > 750 && ym > 900) {
				select.play();
				test = test / 10;

			}
			System.out.println(m.getT().getTab()[1][0] + m.getT().getTab()[1][1] + m.getT().getTab()[1][2] + 2 + "   "
					+ nombrecoups);

			if (flags)

			{
				tempsjeu = tempsjeu - 20;

				int indx = (xm - positionx) / 160;
				int indy = (ym) / 160;

				if (click && comp <= 1 && indx < 5 && indx >= 0 && indy < 5 && indy >= 0
						&& pa.getT().getTab()[indx][indy] != -1 && pa.getT().getTab()[indx][indy] < 100) {
					select.play();
					pa.getT().setTab(indx, indy, pa.getT().getTab()[indx][indy] * 100);

					comp++;
					Tableau.ajouter(indx, indy, ta);

				}
				if (comp == 2) // on a tourné DEUX CARTES

				{
					timer++;
				}
				if (timer > 20 && comp == 2)

				{
					nombrecoups--;
					if (pa.getT().getTab()[ta[0]][ta[1]] != pa.getT().getTab()[ta[2]][ta[3]]) {
						pa.getT().setTab(ta[0], ta[1], pa.getT().getTab()[ta[0]][ta[1]] / 100); // LES CARTES SE
																								// RETOUNENT
						pa.getT().setTab(ta[2], ta[3], pa.getT().getTab()[ta[2]][ta[3]] / 100);
					} else {
						pa.getT().setTab(ta[0], ta[1], -1);
						pa.getT().setTab(ta[2], ta[3], -1); // LES CARTES DISPARAISSENT
						trouve.play();
					}

					Tableau.ini(ta); // reiniitaliser le tableau qui enregistre la position des cartes retournées
					comp = 0;
					timer = 0;

				}
			}

		}

		if (test == 12) {
			m.option(click, xm, ym);
			if (click && xm > 750 && ym > 900) {
				test = test / 10;

			}

		}

		if (test == 13) {
			if (click && xm > 750 && ym > 900) {
				test = test / 10;
				select.play();

			}

		}

	}

}
