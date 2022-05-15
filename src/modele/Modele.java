package modele;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
	public Construction constructionActuelle;
	public static Construction constructionaAjouter;
	
	public Modele(){
		this.constructionActuelle= new Construction("terrain",1);//ça marche vraiment ça???
		this.setElement_a_ajouter(Element_a_ajouter.CUBE);////construction plus tard
		
		
		
		File fichier = new File("constructions.xml");
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
		Cube c0 = new Cube(50, 50, 50, null, 0);
		Cube c1 = new Cube(50, 50, 50, c0, 0);
		ArrayList<Element> ar = new ArrayList<Element>();
		ar.add(c0);
		Construction constr = new Construction(ar);
		constr.setNom("construction1");
	
		Modele.constructionaAjouter = constr;
		Modele.getConstructions().remove("construction1");
		Modele.getConstructions().put("construction1", constr);
		Modele.sauvegarderModele();
		ArrayList<Element> arr = new ArrayList<Element>();
		
		Cube c00 = new Cube(50, 50, 50, null, 0);
		Cube c11 = new Cube(50, 50, 50, c00, 0);
		ar.add(c00);
		Construction constrr = new Construction(arr);
		constr.setNom("construction1");
	
		Modele.constructionaAjouter = constr;
		Modele.getConstructions().remove("construction2");
		constrr.setNom("construction2");
		Modele.getConstructions().put("construction2", constrr);

		System.out.println(mode);
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
		System.out.println(selection);
		System.out.println(Modele.getConstructions().get(selection));
		System.out.println(Modele.getConstructions().get(selection).getNom());
		this.constructionActuelle=constructions.get(selection);
		
	}
	
	public ArrayList<String> getListeConstructions() {
		Set<String> nomconstructions = Modele.getConstructions().keySet();
		ArrayList<String> ar =new ArrayList<String>();
		for(String nom : nomconstructions) {
			ar.add(nom);			
		}
		return ar;
	}

	@SuppressWarnings("resource")
	public static void sauvegarderModele() {
		XMLEncoder encoder = null;
		try {
			FileOutputStream fos = new FileOutputStream("constructions.xml");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			encoder.writeObject(Modele.constructions);
			encoder.flush();
				}
		catch (Exception e){
			throw new RuntimeException("Ecriture des données impossible");
		}
		finally {
			if (encoder != null) encoder.close();
		}	
	}
	
	public void sauvegarder() {
		int i = Modele.constructions.size();
		this.constructionActuelle.setNom("construction" + i);
		Modele.constructions.put(this.constructionActuelle.getNom(), this.constructionActuelle);
	}

	@SuppressWarnings("unchecked")
	public void charger() {
		XMLDecoder decoder = null;
		try {
			FileInputStream fis = new FileInputStream("constructions.xml");
			System.out.println("oui");
			System.out.println("file");
			BufferedInputStream bis = new BufferedInputStream(fis);
			System.out.println("bis");
			decoder = new XMLDecoder(bis);
			System.out.println("decoder");
			Modele.constructions = (HashMap<String,Construction>) decoder.readObject();
			}
		catch (Exception e){
			throw new RuntimeException("Chargement des données impossible"+e);
		}
		finally {
			if (decoder != null) decoder.close();
		}	
	}

	public void setElement_a_ajouter(Element_a_ajouter element_a_ajouter) {
		Modele.element_a_ajouter = element_a_ajouter;
	}
	
	//pour la sérialisation on as pas besoin de sérialiser les variables static non?

	public static HashMap<String,Construction> getConstructions() {
		return constructions;
	}
	
	public static void setConstructions(HashMap<String,Construction> constructions) {
		Modele.constructions = constructions;
	}

}
