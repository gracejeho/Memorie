package pack;

public class Tableau {    // tableau d'entiers qui gere les differentes interactions pendant le jeu

	private int[][] tab;
	private int form;

	public Tableau(int a, int form) {
		tab = new int[5][5];

		if (a > 0) {
			tab = new int[a][a];

		}
		this.form = form;

	}

	public int[][] getTab() {
		return tab;
	}

	public void setTab(int i, int j, int value) {
		tab[i][j] = value;
	}

	public void affec(int n) {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				tab[i][j] = n;
			}
		}
	}

	public void view() {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				System.out.print(tab[i][j] + "   ");
			}
			System.out.println();
		}
	}

	public boolean present(int n, int a)

	{
		int comp = 0;
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				if (tab[i][j] == a)
					comp++;
			}
		}

		if (comp == n) {
			return true;
		}
		return false;
	}

	public int[][] alea() {                     // donne des valeurs aleatoires au tableau et deux a deux 
		int nombre_carte = 0;
		forme();
		if (form == 0) {
			nombre_carte = 8;
		}
		if (form == 1) {
			nombre_carte = 10;
		}
		if (form == 2) {
			nombre_carte = 12;
		}

		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				int m = 0;
				if (tab[i][j] == 0) {
					do {
						m = (int) (Math.random() * nombre_carte + 1);
					} while (present(2, m));

					tab[i][j] = m;
				}
			}
		}

		return tab;
	}

	public void rect(int x, int y, int l, int h, int value) {     // dessiner un rectangle de valeur pour modeliser la forme des cartes sur le plateau du jeu
		for (int i = x; i < x + l; i++) {
			for (int j = y; j < y + h; j++) {
				tab[i][j] = value;
			}
		}
	}

	public void forme() {

		if (form == 0) {
			affec(0);
			rect(2, 0, 3, 3, -1);
		}

		if (form == 1) {
			affec(0);
			for (int i = 0; i < 5; i++) {
				tab[i][i] = -1;
			}
		}

		if (form == 2) {
			affec(0);
			tab[2][2] = -1;
		}

	}

	public static void ini(int[] tau) {
		for (int i = 0; i < tau.length; i++) {             // initialiser un tableau a -1
			tau[i] = -1;
		}
	}

	public static int ajouter(int x, int y, int[] tau) {
		if (tau[0] == -1) {
			tau[0] = x;
			tau[1] = y;
			return 0;

		}

		if (tau[0] != -1)

		{
			tau[2] = x;
			tau[3] = y;
		}

		return 1;

	}

	public void voir() {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++) {
				System.out.print(tab[i][j] + " ");            // voir le tableau
			}

			System.out.println(" ");

		}

	}

	public void preopption(int a, int b) {
		for (int i = 0; i < tab.length; i++) {
			tab[a][i] = -1;
		}

		tab[a][b] = b;
	}

	

}
