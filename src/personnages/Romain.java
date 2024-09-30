package personnages;

public class Romain {
	private String nom;
	private int force;
	private Equipements[] equipements;
	private int nbEquipements = 0;

	public Romain(String nom, int force) {
		this.nom = nom;
		this.force = force;
		this.equipements = new Equipements[2];
		assert this.force > 0 : "La force du romain est positive";
	}

	public String getNom() {
		return nom;
	}

	public void parler(String texte) {
		System.out.println(prendreParole() + "\"" + texte + "\"");
	}

	private String prendreParole() {
		return "Le Romain " + nom + ": ";
	}

	/*
	 * public void recevoirCoup(int forceCoup) { assert this.force > 0 :
	 * "Pre-condition recevoirCoup"; int forceDebut = this.force;
	 * 
	 * force -= forceCoup; if (force > 0) { parler("Aie"); } else {
	 * parler("J'abandonne..."); }
	 * 
	 * assert this.force < forceDebut : "Post-condition recevoirCoup"; }
	 */

	public void sEquiper(Equipements equipement) {
		String l = "Le soldat";
		switch (nbEquipements) {
		case 0:
			equipements[0] = equipement;
			System.out.println(l + nom + " s'equipe avec un " + equipement.getNom());
			nbEquipements++;
			break;
		case 1:
			if (equipements[0] == equipement) {
				System.out.println(l + nom + " possede deja un " + equipement.getNom() + "!");
			} else {
				equipements[1] = equipement;
				System.out.println(l + nom + " s'equipe avec un " + equipement.getNom());
				nbEquipements++;
			}
			break;
		case 2:
			System.out.println("Le soldat " + nom + " est deja bien protege!");
			break;
		}
	}

	public Equipements[] recevoirCoup(int forceCoup) {
		Equipements[] equipementEjecte = null;
		// précondition
		assert force > 0;
		int oldForce = force;
		forceCoup = calculResistanceEquipement(forceCoup);
		force -= forceCoup;
		// if (force > 0) {
		// parler("Aïe");
		// } else {
		// equipementEjecte = ejecterEquipement();
		// parler("J'abandonne...");
		// }
		if (force == 0) {
			parler("Aïe");
		} else {
			equipementEjecte = ejecterEquipement();
			parler("J'abandonne...");
		}
		// post condition la force a diminuée
		assert force < oldForce;
		return equipementEjecte;
	}

	private int calculResistanceEquipement(int forceCoup) {
		String message;
		message = "Ma force est de " + this.force + ", et la force du coup est de " + forceCoup;
		int resistanceEquipement = 0;
		if (nbEquipements != 0) {
			message += "\nMais heureusement, grace à mon équipement sa force est diminué de ";
			for (int i = 0; i < nbEquipements; i++) {
				if ((equipements[i] != null && equipements[i].equals(Equipements.BOUCLIER)) == true) {
					resistanceEquipement += 8;
				} else {
					System.out.println("Equipement casque");
					resistanceEquipement += 5;
				}
			}
			message = +resistanceEquipement + "!";
		}
		parler(message);
		forceCoup -= resistanceEquipement;
		return forceCoup;
	}

	private Equipements[] ejecterEquipement() {
		Equipements[] equipementEjecte = new Equipements[nbEquipements];
		System.out.println("L'équipement de " + nom + "s'envole sous la force du coup.");
		// TODO
		int nbEquipementEjecte = 0;
		for (int i = 0; i < nbEquipements; i++) {
			if (equipements[i] != null) {
				equipementEjecte[nbEquipementEjecte] = equipements[i];
				nbEquipementEjecte++;
				equipements[i] = null;
			}
		}
		return equipementEjecte;
	}

	public static void main(String[] args) {
		Romain auguste = new Romain("Auguste", 10);

		/*
		 * Auguste.parler("Suis-je le premier ou bien le dernier empereurs?");
		 * Auguste.recevoirCoup(8); Auguste.recevoirCoup(3);
		 * 
		 * System.out.println(Equipements.CASQUE);
		 */

		auguste.sEquiper(Equipements.CASQUE);
		auguste.sEquiper(Equipements.CASQUE);
		auguste.sEquiper(Equipements.BOUCLIER);
		auguste.sEquiper(Equipements.CASQUE);
	}
}
