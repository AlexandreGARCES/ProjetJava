package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Construction;
import modele.Cube;
import modele.Element;
import modele.Modele;
import vue.Gestion3D;

public class Controleur {
	public static Gestion3D vue3D=new Gestion3D();
	
	public Stage window;
	public Scene scene;
	public Modele mod=Gestion3D.mod;
	
	public URL url;
	public ResourceBundle rbundle;
	
	@FXML
	protected TextField barreRecherche;
	
    @FXML
    protected Button boutonRechercherBloc;
    @FXML
    protected Button boutonRechercherConstruction;

    @FXML
    protected CheckBox boxBlanc;
    @FXML
    protected CheckBox boxBleu;
    @FXML
    protected CheckBox boxCyan;
    @FXML
    protected CheckBox boxGris;
    @FXML
    protected CheckBox boxJaune;
    @FXML
    protected CheckBox boxOrange;
    @FXML
    protected CheckBox boxNoir;
    @FXML
    protected CheckBox boxRouge;
    @FXML
    protected CheckBox boxVert;
    @FXML
    protected CheckBox boxBlancBloc;
    @FXML
    protected CheckBox boxBleuBloc;
    @FXML
    protected CheckBox boxCyanBloc;
    @FXML
    protected CheckBox boxGrisBloc;
    @FXML
    protected CheckBox boxJauneBloc;
    @FXML
    protected CheckBox boxOrangeBloc;
    @FXML
    protected CheckBox boxNoirBloc;
    @FXML
    protected CheckBox boxRougeBloc;
    @FXML
    protected CheckBox boxVertBloc;
    
    @FXML
    private CheckBox boxCarreBloc;
    @FXML
    protected CheckBox boxRectBloc;
    @FXML
    protected CheckBox boxRondBloc;
    
    @FXML
    protected CheckBox boxGrandBloc;
    @FXML
    protected CheckBox boxMoyenBloc;
    @FXML
    protected CheckBox boxPetitBloc;

    @FXML
	protected ListView<String> listeResultatRecherche;
    protected ArrayList<String> listeBlocs;
    protected ArrayList<String> listeConstructions;
    
    public static boolean type;
    
	
	public Controleur () {
	}
	
	public void changerFenetre(String fenetre, ActionEvent event) throws IOException {
		Controleur.type=fenetre=="Construction";
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	AnchorPane pane = new AnchorPane();

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/"+fenetre+".fxml"));
	    pane = loader.load();

	   	pane.getChildren().add(Controleur.vue3D.subScene3D);
	    this.scene = new Scene(pane);
	    Controleur.vue3D.addTouches(this.scene);
	    
	    Modele.setMode(fenetre=="Construction");
	    
	    
	    //A SUPPRIMER APRES devrait être dans raz!!!!
	    Cube c0 = new Cube(50, 50, 50, null, 0);
		Cube c1 = new Cube(50, 50, 50, c0, 0);
		ArrayList<Element> ar = new ArrayList<Element>();
		ar.add(c0);
		Construction constr = new Construction(ar);
		Modele.constructionaAjouter = constr;
		//A SUPPRIMER APRES
	    
	    window.setScene(this.scene);
	    window.show();
	}
	
	public void retourMenu(ActionEvent event) throws IOException {
    	window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	Parent root = FXMLLoader.load((getClass().getResource("../vue/Menu.fxml")));
	    scene = new Scene(root);
	    
	    this.mod.raz(4);
	    
	    //A SUPPRIMER APRES devrait être dans raz!!!!
	    Cube c0 = new Cube(50, 50, 50, null, 0);
		Cube c1 = new Cube(50, 50, 50, c0, 0);
		ArrayList<Element> ar = new ArrayList<Element>();
		ar.add(c0);
		Construction constr = new Construction(ar);
		Modele.constructionaAjouter = constr;
		//A SUPPRIMER APRES
		
	    window.setTitle("LEGO");
	    window.setScene(scene);
	    window.show();
	}
	
    @FXML
    void rechercheMultiCritBloc(ActionEvent event) {
    	System.out.println(boxJaune.isSelected());
    	ArrayList<String> taille=new ArrayList<String>();
    	int cpt=0;
    	if(boxGrandBloc.isSelected()) { taille.add("grand"); }else { cpt++; }
    	if(boxPetitBloc.isSelected()) { taille.add("petit"); }else { cpt++; }
    	if(boxMoyenBloc.isSelected()) { taille.add("moyen"); }else { cpt++; }
    	if (cpt==3) { taille.add("grand"); taille.add("moyen"); taille.add("petit");}
    	
    	String[] tabCouleurs= {" blanc"," bleu"," cyan"," gris"," orange"," noir"," rouge"," vert"," jaune"};
    	
    	ArrayList<String> couleurs=new ArrayList<String>();
    	cpt=0;
    	if (boxBlanc.isSelected()) { 
    		for (int i=0;i<taille.size();i++) {
    			couleurs.add(taille.get(i)+" blanc");
    		} }else { cpt++; }
    	if (boxBleu.isSelected())  {
    		for (int i=0;i<taille.size();i++) {
    			couleurs.add(taille.get(i)+" bleu");
    		} }else { cpt++; }
    	if (boxCyan.isSelected())  { 
    		for (int i=0;i<taille.size();i++) {
    			couleurs.add(taille.get(i)+" cyan");
    		} }else { cpt++; }
    	if (boxGris.isSelected())  { 
    		for (int i=0;i<taille.size();i++) {
    			couleurs.add(taille.get(i)+" gris");
    		} }else { cpt++; }
    	if (boxOrange.isSelected()){ 
    		for (int i=0;i<taille.size();i++) {
    			couleurs.add(taille.get(i)+" orange");
    		} }else { cpt++; }
    	if (boxNoir.isSelected())  { 
    		for (int i=0;i<taille.size();i++) {
    			couleurs.add(taille.get(i)+" noir");
    		} }else { cpt++; }
    	if (boxRouge.isSelected()) { 
    		for (int i=0;i<taille.size();i++) {
    			couleurs.add(taille.get(i)+" rouge");
    		} }else { cpt++; }
    	if (boxVert.isSelected())  { 
    		for (int i=0;i<taille.size();i++) {
    			couleurs.add(taille.get(i)+" vert");
    		} }else { cpt++; }
    	if (boxJaune.isSelected()) { 
    		for (int i=0;i<taille.size();i++) {
    			couleurs.add(taille.get(i)+" jaune");
    		} }else { cpt++; }
    	if (cpt==9) {
    		couleurs.clear();
    		for (int i=0;i<tabCouleurs.length;i++) {
    			for (int j=0;j<taille.size();j++) {
    				couleurs.add(taille.get(j)+tabCouleurs[i]);
    			}
    		}
    	}
    	
    	String[] tabOrientation= {" horizontalX"," horizontalX"," vertical"};
    	ArrayList<String> orientations=new ArrayList<String>();
    	cpt=0;
    	if(boxCarreBloc.isSelected()) { 
    		for (int i=0;i<taille.size();i++) {
    			orientations.add(taille.get(i)+" horizontalX");
    		} }else { cpt++; }
    	if(boxRectBloc.isSelected()) { 
    		for (int i=0;i<taille.size();i++) {
    			orientations.add(taille.get(i)+" vertical");
    		} }else { cpt++; }
    	if(boxRondBloc.isSelected()) { 
    		for (int i=0;i<taille.size();i++) {
    			orientations.add(taille.get(i)+" horizontalY");
    		} }else { cpt++; }
    	if(cpt==3) {
    		orientations.clear();
    		for (int i=0;i<tabOrientation.length;i++) {
    			for (int j=0;j<couleurs.size();j++) {
    				orientations.add(couleurs.get(j)+tabOrientation[i]);
    			}
    		}	
    	}
    	
    	
    	
    	
    	this.listeBlocs=this.mod.rechercherElement(orientations);
    	this.initialize(this.url, this.rbundle);
    }
    
	public void initialize(URL arg0, ResourceBundle arg1) {
    	this.url=arg0;
    	this.rbundle=arg1;
    	this.listeResultatRecherche.getItems().clear();
    	
    	if (this.listeBlocs!=null) {
        	listeResultatRecherche.getItems().addAll(this.listeBlocs);
    		this.listeBlocs=null;
        	listeResultatRecherche.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    			@Override
    			public void changed(ObservableValue<? extends String> arg0, String ancienSelect, String selection) {
    				if (selection!=null) {
    					if (Controleur.type) {
        					Gestion3D.mod.changerBlocaAjouter(selection);
        				}else {
        					Gestion3D.mod.changerConstructionActuelle(selection);
        				}
    				}
    			}
        	});
        	
    	}else if (this.listeConstructions!=null) {
    		listeResultatRecherche.getItems().addAll(this.listeConstructions);
    		this.listeConstructions=null;
        	listeResultatRecherche.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    			@Override
    			public void changed(ObservableValue<? extends String> arg0, String ancienSelect, String selection) {
    				if (selection!=null) {
    					if (Controleur.type) {
        					Gestion3D.mod.changerBlocaAjouter(selection);
        				}else {
        					Gestion3D.mod.changerConstructionActuelle(selection);
        				}
    				}
    			}
        	});
    	}
	}

	@FXML
    void rechercheMultiCritConstruc(ActionEvent event) {
    	List<String> recherche=Arrays.asList(barreRecherche.getText().trim().split(" "));
    	ArrayList<String> constructions=this.mod.getListeConstructions();
    	
    	List<String> noms= constructions.stream().filter(input -> {
    		return recherche.stream().allMatch(mot -> 
    		input.toLowerCase().contains(mot.toLowerCase()));
    	}).collect(Collectors.toList());
    	
    	ArrayList<Integer> couleurs=new ArrayList<Integer>();
    	int cpt=0;
    	if (boxBlancBloc.isSelected()) { couleurs.add(3); }else { cpt++; }
    	if (boxBleuBloc.isSelected())  { couleurs.add(2); }else { cpt++; }
    	if (boxCyanBloc.isSelected())  { couleurs.add(6); }else { cpt++; }
    	if (boxGrisBloc.isSelected())  { couleurs.add(4); }else { cpt++; }
    	if (boxOrangeBloc.isSelected()){ couleurs.add(8); }else { cpt++; }
    	if (boxNoirBloc.isSelected())  { couleurs.add(5); }else { cpt++; }
    	if (boxRougeBloc.isSelected()) { couleurs.add(0); }else { cpt++; }
    	if (boxVertBloc.isSelected())  { couleurs.add(1); }else { cpt++; }
    	if (boxJauneBloc.isSelected()) { couleurs.add(7); }else { cpt++; }
    	if (cpt==9) {//si aucun n'est sélectionné, tout mettre
    		couleurs.clear();
    		for (int i=0;i<9;i++) { couleurs.add(i); }
    	}
    	this.listeConstructions=this.mod.rechercherConstruction(couleurs,noms);
    	this.initialize(this.url, this.rbundle);
    }

}
