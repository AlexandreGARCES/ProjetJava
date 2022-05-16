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
    private TextField barreRecherche;
	
    @FXML
    private Button boutonRechercherBloc;
    @FXML
    private Button boutonRechercherConstruction;

    @FXML
    private CheckBox boxBlancBloc;
    @FXML
    private CheckBox boxBleuBloc;
    @FXML
    private CheckBox boxCyanBloc;
    @FXML
    private CheckBox boxGrisBloc;
    @FXML
    private CheckBox boxJauneBloc;
    @FXML
    private CheckBox boxOrangeBloc;
    @FXML
    private CheckBox boxNoirBloc;
    @FXML
    private CheckBox boxRougeBloc;
    @FXML
    private CheckBox boxVertBloc;
    
    @FXML
    private CheckBox boxCarreBloc;
    @FXML
    private CheckBox boxRectBloc;
    @FXML
    private CheckBox boxRondBloc;
    
    @FXML
    private CheckBox boxGrandBloc;
    @FXML
    private CheckBox boxMoyenBloc;
    @FXML
    private CheckBox boxPetitBloc;

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
	    
	    this.mod.raz();
	    
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
    	//réunir plusieurs checkbox ensemble nous simplifierais la vie
    	
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
    	
    	ArrayList<String> formes=new ArrayList<String>();//on rajoute les triangles les gars?
    	cpt=0;
    	if(boxCarreBloc.isSelected()) { formes.add("carre"); }else { cpt++; }
    	if(boxRectBloc.isSelected()) { formes.add("rect"); }else { cpt++; }
    	if(boxRondBloc.isSelected()) { formes.add("rond"); }else { cpt++; }
    	if(cpt==3) {
    		formes.clear();
    		formes.add("carre");
    		formes.add("rect");
    		formes.add("rond");
    	}
    	
    	ArrayList<String> types=(ArrayList<String>) formes.clone();
    	cpt=0;
    	if(boxGrandBloc.isSelected()) {
    		for (int i=0;i<types.size();i++) {
    			types.set(i, types.get(i)+" grand");
    		} }else { cpt++; }
    	
    	if(boxPetitBloc.isSelected()) {
    		for (int i=0;i<types.size();i++) {
    			types.set(i, types.get(i)+" petit");
    		} }else { cpt++; }
    	
    	if(boxMoyenBloc.isSelected()) {
    		for (int i=0;i<types.size();i++) {
    			types.set(i, types.get(i)+" moyen");
    		} }else { cpt++; }
    	
    	if (cpt==3) {
    		types=(ArrayList<String>) formes.clone();
    		for (int i=0;i<types.size();i++) { types.set(i, types.get(i)+" grand"); }
    		for (int i=0;i<types.size();i++) { types.set(i, types.get(i)+" petit"); }
    		for (int i=0;i<types.size();i++) { types.set(i, types.get(i)+" moyen"); }
    	}
    	this.listeBlocs=this.mod.rechercherElement(couleurs,types);
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
