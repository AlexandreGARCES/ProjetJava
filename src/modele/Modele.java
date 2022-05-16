package modele;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Set;


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

		this.constructionActuelle= new Construction("terrain",4);//ça marche vraiment ça???

		File fichier = new File("constructions.dat");
		try {
			fichier.createNewFile();
			System.out.println("création du fichier");
		}
		catch (Exception e) {
			 System.out.println("le fichier n'est pas créé");
		}
		this.charger();
		if (Modele.constructions == null) {
			Modele.constructions = new HashMap<String,Construction>();
		}
		if (Modele.elements == null) {
			Modele.elements = new HashMap<String,Construction>();
		}
		Cube c0 = new Cube(50, 50, 50, null, 0);
		Cube c1 = new Cube(50, 50, 50, c0, 0);
		ArrayList<Element> ar = new ArrayList<Element>();
		ar.add(c0);
		Construction constr = new Construction(ar);
		Modele.constructionaAjouter = constr;
		//Modele.constructionaAjouter = Modele.getConstructions().get("construction16").copie(null);

		
	}
	
	//-----------------------------------------------------
	
	//changeConstructionAAfficher
	//rendreConstructionModifiable
	//rendreConstructionNonModifiable
	//enregisterEnXML(ou FXML)
	//chargerXML(ou FXML)
	//changerLaCouleurBriqueAPlacer (ou texture)
	//gererLaRechercheMultiCritere
	//doit contenir la liste de toutes les constructions existantes
	//-----------------------------------------------------
	
	// Methode renvoyant la contruction à afficher(visualisation)
	// modifier (bouton): passer en mode construction (conserver la même construcction)
	//construction -> visualisation : demander la sauvegarde
	
	public static void setMode(boolean mode) {
		if (mode) {
			Modele.mode=Modes.CONSTRUCTION;///mettre sur bonton (controleurs)
		}else {
			Modele.mode=Modes.VISUALISATION;
		}
	}
	
	public void changerConstructionActuelle(String selection) {
		this.constructionActuelle=constructions.get(selection).copie(null);
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
	
	public void raz() {
		//demander couleur
		this.constructionActuelle= new Construction("terrain",4);
		//Modele.constructionaAjouter = Modele.getConstructions().get("cube 1x1").copie(null);
		//doit aussi raz la construction à ajouter!!
		this.setChanged();
        this.notifyObservers();
	}
	
	public void changerBlocaAjouter(String selection) {
		Modele.constructionaAjouter=Modele.elements.get(selection);
		
	}

	public void changerConstructionaAjouter(String selection) {
		Modele.constructionaAjouter=Modele.elements.get(selection);		
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
		//dois enregistrer la construction actuelle dans une variable
		//quand on quitte on fait appel à une autre fonction qui va faire ce qu'on fait là mais avec la variable
		this.sauvegarde=this.constructionActuelle;
	}
	
	public void sauvegarderSous() {
		if (sauvegarde!=null) {
			int i = Modele.constructions.size();
			this.sauvegarde.setNom("construction" + i);
			Modele.constructions.put(this.sauvegarde.getNom(), this.sauvegarde.copie(null));
			sauvegarde=null;
		}	
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

}
