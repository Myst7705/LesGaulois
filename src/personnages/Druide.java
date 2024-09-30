package personnages;
import java.util.Random;

public class Druide {
	private String nom;
	private int effetPotionMin;
	private int effetPotionMax;
	private int attributForcePotion = 1;

	public Druide(String nom, int effetPotionMin, int effetPotionMax) {
		this.nom = nom;
		this.effetPotionMin = effetPotionMin;
		this.effetPotionMax = effetPotionMax;
		parler("Bonjour, je suis le druide " + nom
				+ " et ma potion peut aller d'une force " + effetPotionMin + " à "
				+ effetPotionMax + ".");
	}

	public String getNom() {
		return nom;
	}

	public void parler(String texte) {
		System.out.println(prendreParole() + "« " + texte + "»");
	}

	private String prendreParole() {
		return "Le druide " + nom + " : ";
	}
	
	public int preparerPotion() {
		Random generator = new Random();
		int valeurPot;
		
		do {
			valeurPot = generator.nextInt(effetPotionMax);
		} while (valeurPot < effetPotionMin || valeurPot > effetPotionMax);
		
		if (valeurPot > 7) {
			System.out.println("J'ai prepare une super potion de force: " + valeurPot);
			return valeurPot;
		} else {
			System.out.println("Je n'ai pas trouve tous les ingredients, ma potion est seulement de force: " + valeurPot);
			return valeurPot;
		}
	}
	
	public void booster(Gaulois gaulois) {
		if (gaulois.getNom() == "Obelix") {
			System.out.println("Non Obelix!... tu n'auras pas de potion magique!");
			return;
		} else {
			gaulois.boirePotion(preparerPotion());
		}
		
	}
	
	public static void main(String[] args) {
		Druide panoramix = new Druide("Panoramix", 5, 10);
		panoramix.preparerPotion();
	}
	
	

}
