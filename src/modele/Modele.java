package modele;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.stream.Collectors;


public class Modele extends Observable{
	
	public static int couleurChoisie=4;//défini à blanc au démarrage de l'appli
	
	public static Modes mode;
	public enum Modes{
		VISUALISATION, CONSTRUCTION
	}
	
	public enum Element_a_ajouter{////construction plus tard
		CUBE, CONSTRUCTION
	}
	public static Element_a_ajouter element_a_ajouter;////construction plus tard
	
	private static HashMap<String,Construction> constructions;
	private static HashMap<String,Construction> elements;
	public static Construction constructionaAjouter;
	
	
	public Construction constructionActuelle;
	public Construction sauvegarde;
	
	
	public Modele(){

		this.raz(4);

		File fichier = new File("constructions.dat");
		try {
			fichier.createNewFile();
			System.out.println("création du fichier");
		}
		catch (Exception e) {
			 System.out.println("le fichier n'est pas créé");
		}
		this.charger();
		this.creer_blocs();
		this.chargerBlocs();
		System.out.println(Modele.getElements().keySet());
		if (Modele.constructions == null) {
			Modele.constructions = new HashMap<String,Construction>();
		}
		if (Modele.elements == null) {
			Modele.elements = new HashMap<String,Construction>();
		}
		Cube c0 = new Cube(50, 50, 50, null, 6);
		Cube c1 = new Cube(50, 50, 50, c0, 0);
		int [] tab = {0, -50, 0};
		Cube c3 = new Cube(50, 50, 50, c0, tab, 0);
		tab[0] = 0;
		tab[1] -= 50;
		Cube c4 = new Cube(50, 50, 50, null, tab, 0);
		ArrayList<Element> ar = new ArrayList<Element>();
		ar.add(c0);
		Construction constr = new Construction(ar);
		Modele.constructions.put("petit moyen",  constr);
		Modele.constructionaAjouter = constr.copie(null);
		//Modele.constructionaAjouter = Modele.getConstructions().get("construction16").copie(null);
		

		
	}

	@SuppressWarnings("unchecked")
	private void chargerBlocs() {
		File fiichier = new File("blocs.dat");


		try {
			FileInputStream fiis = new FileInputStream(fiichier);
			ObjectInputStream oiis = new ObjectInputStream(fiis);

			Modele.elements = (HashMap<String,Construction>)oiis.readObject();
			oiis.close();
			fiis.close();
			}
		catch (Exception e){
			throw new RuntimeException("Chargement des données impossible"+e);
		}	
		
	}
	public static void setMode(boolean mode) {
		if (mode) {
			Modele.mode=Modes.CONSTRUCTION;///mettre sur bonton (controleurs)
		}else {
			Modele.mode=Modes.VISUALISATION;
		}
	}
	
	public void changerConstructionActuelle(String selection) {
		Construction copie;
		if (constructions.get(selection)==null) {
			copie=elements.get(selection).copie(null);
			copie.setNom(elements.get(selection).getNom());
		}else {
			copie=constructions.get(selection).copie(null);
			copie.setNom(constructions.get(selection).getNom());
		}

		this.constructionActuelle=copie;
		this.setChanged();
        this.notifyObservers();
	}
	
	public ArrayList<String> getListeConstructions() {
		Set<String> nomconstructions = Modele.getConstructions().keySet();
		ArrayList<String> ar =new ArrayList<String>();
		for(String nom : nomconstructions) {
			ar.add(nom);
		}
		return ar;
	}
	
	public ArrayList<String> getListeElements() {
		Set<String> nomElements = Modele.getElements().keySet();
		ArrayList<String> ar =new ArrayList<String>();
		for(String nom : nomElements) {
			ar.add(nom);
		}
		return ar;
	}
	
	private static HashMap<String, Construction> getElements() {
		return Modele.elements;
	}

	public void raz(int couleur) {
		//demander couleur
		this.constructionActuelle= new Construction("terrain",couleur);
		//Modele.constructionaAjouter = Modele.getConstructions().get("cube 1x1").copie(null);
		this.setChanged();
        this.notifyObservers();
	}
	
	public void changerBlocaAjouter(String selection) {
		Modele.constructionaAjouter=Modele.elements.get(selection).copie(null);
		
	}

	public void changerConstructionaAjouter(String selection) {
		Modele.constructionaAjouter=Modele.constructions.get(selection).copie(null);		
	}

	public static void sauvegarderModele() {
		try {
			File fichier = new File("constructions.dat");
			FileOutputStream fos = new FileOutputStream(fichier);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Modele.getConstructions());
			oos.close();
			fos.close();
				}
		catch (Exception e){
			throw new RuntimeException("Ecriture des données impossible");
		}
	}
	
	public void sauvegarder() {
		this.sauvegarde=this.constructionActuelle;
	}
	
	public void sauvegarderSous(String nom) {
		this.sauvegarder();
		int j=0;
		while (Modele.constructions.containsKey(nom)) {
			nom=nom+" "+j;
			j++;
		}
		Construction copie=this.sauvegarde.copie(null);
		copie.setNom(nom);
		Modele.constructions.put(copie.getNom(), copie);
		sauvegarde=null;
	}

	@SuppressWarnings("unchecked")
	public void charger() {
		File fichier = new File("constructions.dat");


		try {
			FileInputStream fis = new FileInputStream(fichier);
			ObjectInputStream ois = new ObjectInputStream(fis);

			Modele.constructions = (HashMap<String,Construction>)ois.readObject();
			ois.close();
			fis.close();
			}
		catch (Exception e){
			throw new RuntimeException("Chargement des données impossible"+e);
		}	
	}

	public void setElement_a_ajouter(Element_a_ajouter element_a_ajouter) {
		Modele.element_a_ajouter = element_a_ajouter;
	}
	
	//pour la sérialisation on as pas besoin de sérialiser les variables static non?

	public static HashMap<String,Construction> getConstructions() {
		return Modele.constructions;
	}
	
	public static void setConstructions(HashMap<String,Construction> constructions) {
		Modele.constructions = constructions;
	}

	public ArrayList<String> rechercherElement(ArrayList<String> nomsBloc) {
		//penser à enlever la ligne au dessus
		ArrayList<String> resultat=new ArrayList<String>();
		ArrayList<String> elems=this.getListeElements();
		
		for (int i=0;i<nomsBloc.size();i++) {
			List<String> recherche=Arrays.asList(nomsBloc.get(i).trim().split(" "));
	    	List<String> noms= elems.stream().filter(input -> {
	    		return recherche.stream().allMatch(mot -> 
	    		input.toLowerCase().contains(mot.toLowerCase()));
	    	}).collect(Collectors.toList());
			
	    	for (int k=0;k<noms.size();k++) {
	    		resultat.add(noms.get(k));
	    	}
		}
		if (resultat.size()==0) {
			resultat=this.getListeElements();
		}
		return resultat;
	}

	public ArrayList<String> rechercherConstruction(ArrayList<Integer> couleurs, List<String> noms) {
		ArrayList<String> resultat=new ArrayList<String>();
		for (String nom : noms) {
			Construction cstr=Modele.constructions.get(nom);
			if (cstr!=null && couleurs.contains(cstr.getCouleurBase())) {
				resultat.add(nom);
			}
		}
		return resultat;
	}
	
public void bloc_simple(int couleur, HashMap<String, Construction> elements) {
	String[] Couleurs = {"rouge", "vert", "bleu", "blanc", "gris", "noir", "cyan", "jaune", "orange"};
		
		
		Cube c0 = new Cube(50, 50, 50, null, 3);
		Cube c1 = new Cube(50, 50, 50, c0, couleur);
		/*
		int [] tab = {50, 0, 0};
		Cube c3 = new Cube(50, 50, 50, c0, tab, couleur);
		tab[0] = -50;
		Cube c4 = new Cube(50, 50, 50, c0, tab, couleur);
		 */
		ArrayList<Element> ar = new ArrayList<Element>();

		ar.add(c0);
		Construction constr = new Construction(ar);
		elements.put(String.format("petit %s",  Couleurs[couleur]), constr);
		
	}
	public void bloc_double_horizontalX(int couleur, HashMap<String, Construction> elements) {
		String[] Couleurs = {"rouge", "vert", "bleu", "blanc", "gris", "noir", "cyan", "jaune", "orange"};
		Cube c0 = new Cube(50, 50, 50, null, 3);
		Cube c1 = new Cube(50, 50, 50, c0, couleur);
		int [] tab = {50, 0, 0};
		Cube c3 = new Cube(50, 50, 50, c0, tab, couleur);
		/*
		tab[0] = -50;
		Cube c4 = new Cube(50, 50, 50, c0, tab, couleur);
		 */
		ArrayList<Element> ar = new ArrayList<Element>();

		ar.add(c0);
		Construction constr = new Construction(ar);
		elements.put(String.format("moyen %s horizontalX",  Couleurs[couleur]), constr);
		
	}
	public void bloc_double_vertical(int couleur, HashMap<String, Construction> elements) {
		String[] Couleurs = {"rouge", "vert", "bleu", "blanc", "gris", "noir", "cyan", "jaune", "orange"};
		Cube c0 = new Cube(50, 50, 50, null, 3);
		Cube c1 = new Cube(50, 50, 50, c0, couleur);
		int [] tab = {0, -50, 0};
		Cube c3 = new Cube(50, 50, 50, c0, tab, couleur);
		/*
		tab[0] = -50;
		Cube c4 = new Cube(50, 50, 50, c0, tab, couleur);
		 */
		ArrayList<Element> ar = new ArrayList<Element>();

		ar.add(c0);
		Construction constr = new Construction(ar);
		elements.put(String.format("moyen %s vertical",  Couleurs[couleur]), constr);
		
	}
	public void bloc_double_horiziontalY(int couleur, HashMap<String, Construction> elements) {
		String[] Couleurs = {"rouge", "vert", "bleu", "blanc", "gris", "noir", "cyan", "jaune", "orange"};
		Cube c0 = new Cube(50, 50, 50, null, 3);
		Cube c1 = new Cube(50, 50, 50, c0, couleur);
		int [] tab = {0, 0, 50};
		Cube c3 = new Cube(50, 50, 50, c0, tab, couleur);
		/*
		tab[0] = -50;
		Cube c4 = new Cube(50, 50, 50, c0, tab, couleur);
		 */
		ArrayList<Element> ar = new ArrayList<Element>();

		ar.add(c0);
		Construction constr = new Construction(ar);
		elements.put(String.format("moyen %s horiziontalY",  Couleurs[couleur]), constr);
		
	}
	
	public void bloc_triple_vertial(int couleur, HashMap<String, Construction> elements) {
		String[] Couleurs = {"rouge", "vert", "bleu", "blanc", "gris", "noir", "cyan", "jaune", "orange"};
		Cube c0 = new Cube(50, 50, 50, null, 3);
		Cube c1 = new Cube(50, 50, 50, c0, couleur);
		int [] tab = {0, -50, -0};
		Cube c3 = new Cube(50, 50, 50, c0, tab, couleur);
		tab[0] -=50;
		Cube c4 = new Cube(50, 50, 50, c0, tab, couleur);
		ArrayList<Element> ar = new ArrayList<Element>();

		ar.add(c0);
		Construction constr = new Construction(ar);
		elements.put(String.format("grand %s horiziontalY",  Couleurs[couleur]), constr);
		
	}
	public void bloc_triple_horiziontalY(int couleur, HashMap<String, Construction> elements) {
		String[] Couleurs = {"rouge", "vert", "bleu", "blanc", "gris", "noir", "cyan", "jaune", "orange"};
		Cube c0 = new Cube(50, 50, 50, null, 3);
		Cube c1 = new Cube(50, 50, 50, c0, couleur);
		int [] tab = {0, 0, 50};
		Cube c3 = new Cube(50, 50, 50, c0, tab, couleur);

		tab[2] +=50;
		Cube c4 = new Cube(50, 50, 50, c0, tab, couleur);
		ArrayList<Element> ar = new ArrayList<Element>();

		ar.add(c0);
		Construction constr = new Construction(ar);
		elements.put(String.format("grand %s horiziontalY",  Couleurs[couleur]), constr);
	}
	public void bloc_triple_horizontalX(int couleur, HashMap<String, Construction> elements) {
		String[] Couleurs = {"rouge", "vert", "bleu", "blanc", "gris", "noir", "cyan", "jaune", "orange"};
		Cube c0 = new Cube(50, 50, 50, null, 3);
		Cube c1 = new Cube(50, 50, 50, c0, couleur);
		int [] tab = {50, 0, 0};
		Cube c3 = new Cube(50, 50, 50, c0, tab, couleur);
		tab[0] +=50;
		Cube c4 = new Cube(50, 50, 50, c0, tab, couleur);
		ArrayList<Element> ar = new ArrayList<Element>();

		ar.add(c0);
		Construction constr = new Construction(ar);
		elements.put(String.format("moyen %s horizontalX",  Couleurs[couleur]), constr);
		
	}
	public void creer_blocs() {
		
	String[] Couleurs = {"rouge", "vert", "bleu", "blanc", "gris", "noir", "cyan", "jaune", "orange"};
		
	
	
	File fiiichier = new File("blocs.dat");
	try {
		fiiichier.createNewFile();
		System.out.println("création du fichier");
	}
	catch (Exception e) {
		 System.out.println("le fichier n'est pas créé");
	}
	
	HashMap<String, Construction> dico = new HashMap<String, Construction>();
	for(int i = 0; i < Couleurs.length; i++) {
		this.bloc_simple(i, dico);
		this.bloc_double_horiziontalY(i,  dico);
		this.bloc_double_horizontalX(i,  dico);
		this.bloc_double_vertical(i,  dico);
		this.bloc_triple_horiziontalY(i,  dico);
		this.bloc_triple_horizontalX(i,  dico);
		this.bloc_triple_vertial(i,  dico);
		
	}
	try {
		FileOutputStream fos = new FileOutputStream(fiiichier);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(dico);
		oos.close();
		fos.close();
			}
	catch (Exception e){
		throw new RuntimeException("Ecriture des données impossible");
	
	}
	
}

}
