package modele;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Observable;


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
	
	private ArrayList<Construction> constructions; /// les constructions disponibles, à organiser en bibliothèque
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
		if (this.constructions == null) {
			this.constructions = new ArrayList<Construction>();
		}
		Cube c0 = new Cube(50, 50, 50, null, 0);
		Cube c1 = new Cube(50, 50, 50, c0, 0);
		ArrayList<Element> ar = new ArrayList<Element>();
		ar.add(c0);
		Construction constr = new Construction(ar);
		
		
	
		Modele.constructionaAjouter = constr;
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

	@SuppressWarnings("resource")
	public void sauvegarderModele() {
		XMLEncoder encoder = null;
		try {
			FileOutputStream fos = new FileOutputStream("constructions.xml");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);
			encoder.writeObject(this.constructions);
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
		int i = this.constructions.size();
		this.constructionActuelle.setNom("construction" + i);
		this.constructions.add(this.constructionActuelle);
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
			this.constructions = (ArrayList<Construction>) decoder.readObject();
			}
		catch (Exception e){
			throw new RuntimeException("Chargement des données impossible");
		}
		finally {
			if (decoder != null) decoder.close();
		}	
	}

	public void setElement_a_ajouter(Element_a_ajouter element_a_ajouter) {
		Modele.element_a_ajouter = element_a_ajouter;
	}
	
	//pour la sérialisation on as pas besoin de sérialiser les variables static non?

	public ArrayList<Construction> getConstructions() {
		return constructions;
	}
	
	public void setConstructions(ArrayList<Construction> constructions) {
		this.constructions = constructions;
	}

}
