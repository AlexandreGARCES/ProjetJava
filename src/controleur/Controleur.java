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
    @FXML
	protected ListView<String> listeResultatRechercheBloc;
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
    	ArrayList<String> recherche = new ArrayList<String>();
    	ArrayList<String> taille=new ArrayList<String>();
    	ArrayList<String> couleur=new ArrayList<String>();
    	ArrayList<String> direction=new ArrayList<String>();
    	taille.add("moyen");
    	taille.add("petit"); 
    	taille.add("grand");
    	
    	if(boxGrandBloc.isSelected()) {if(taille.size() == 3) {taille = new ArrayList<String>();} taille.add("grand"); }
    	if(boxPetitBloc.isSelected()) {if(taille.size() == 3) {taille = new ArrayList<String>();} taille.add("petit"); }
    	if(boxMoyenBloc.isSelected()) {if(taille.size() == 3) {taille = new ArrayList<String>();} taille.add("moyen"); }
    	
    	couleur.add("blanc");
    	couleur.add("bleu");
    	couleur.add("cyan");
    	couleur.add("gris");
    	couleur.add("orange");
    	couleur.add("noir");
    	couleur.add("rouge");
    	couleur.add("vert");
    	couleur.add("jaune");
    	
    	if (boxBlanc.isSelected()) {if(couleur.size() == 9) {couleur = new ArrayList<String>();} couleur.add("blanc");}
    	if (boxBleu.isSelected())  {if(couleur.size() == 9) {couleur = new ArrayList<String>();} couleur.add("bleu");}
    	if (boxCyan.isSelected())  {if(couleur.size() == 9) {couleur = new ArrayList<String>();} couleur.add("cyan");}
    	if (boxGris.isSelected())  {if(couleur.size() == 9) {couleur = new ArrayList<String>();} couleur.add("gris"); }
    	if (boxOrange.isSelected()){if(couleur.size() == 9) {couleur = new ArrayList<String>();} couleur.add("orange");}
    	if (boxNoir.isSelected())  {if(couleur.size() == 9) {couleur = new ArrayList<String>();} couleur.add("noir");}
    	if (boxRouge.isSelected()) {if(couleur.size() == 9) {couleur = new ArrayList<String>();} couleur.add("rouge");}
    	if (boxVert.isSelected())  {if(couleur.size() == 9) {couleur = new ArrayList<String>();} couleur.add("vert");}
    	if (boxJaune.isSelected()) {if(couleur.size() == 9) {couleur = new ArrayList<String>();} couleur.add("jaune");}
    	
    	direction.add("horizontalX");
    	direction.add("horizontalY");
    	direction.add("vertical");
    	if(boxCarreBloc.isSelected()) {if(direction.size() == 3) {direction = new ArrayList<String>();}  direction.add("horizontalX");}
    	if(boxRectBloc.isSelected()) {if(direction.size() == 3) {direction = new ArrayList<String>();}  direction.add("vertical"); }
    	if(boxRondBloc.isSelected()) {if(direction.size() == 3) {direction = new ArrayList<String>();} direction.add("horizontalY");}
    	for(String taiille: taille) {
    		for(String couuleur: couleur) {
    			if(taiille == "petit") {
    				recherche.add(String.format("%s %s", taiille, couuleur));
				}
    			else {
        			for(String diirection:direction) {
        				
        				System.out.println("marche");
        				recherche.add(String.format("%s %s %s", taiille, couuleur, diirection));
        				
        			}
    				
    			}

    		}
    	}
    	

    	System.out.println(recherche);

    	this.listeBlocs=this.mod.rechercherElement(recherche);
    	this.initialize(this.url, this.rbundle);
    }
    
	public void initialize(URL arg0, ResourceBundle arg1) {
    	this.url=arg0;
    	this.rbundle=arg1;
    	
    	
    	if (this.listeBlocs!=null) {
    		this.listeResultatRechercheBloc.getItems().clear();
    	listeResultatRechercheBloc.getItems().addAll(this.listeBlocs);
		this.listeBlocs=null;
    	listeResultatRechercheBloc.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
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
		this.listeResultatRecherche.getItems().clear();
    		listeResultatRecherche.getItems().addAll(this.listeConstructions);
    		this.listeConstructions=null;
        	listeResultatRecherche.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    			@Override
    			public void changed(ObservableValue<? extends String> arg0, String ancienSelect, String selection) {
    				if (selection!=null) {
    					if (Controleur.type) {
        					Gestion3D.mod.changerConstructionaAjouter(selection);
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
