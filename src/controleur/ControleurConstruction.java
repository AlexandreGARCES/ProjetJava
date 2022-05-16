package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Modele;
import vue.Gestion3D;

public class ControleurConstruction extends Controleur implements Initializable {
	
	//-----------------------------------------------------
	
	//sauvegarderModele quand on quitte
	//rechercheMultiCrit (pour avoir la liste des briques ou constructions qui nous intéressent pour construire)
	//lorsque click sur un élément de la liste: change Modele.constructionAAjouter

	//-----------------------------------------------------
	
	@FXML
    private TextField barreRecherche;
	
	@FXML
    private SubScene subScene3D;
	
	@FXML
    private Button boutonMenu;
    @FXML
    private Button boutonQuitter;
    @FXML
    private Button boutonRedo;
    @FXML
    private Button boutonSauvegarder;
    @FXML
    private Button boutonUndo;
    
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
    private ListView<String> listeResultatRecherche;
    private ArrayList<String> listeBlocs;
    private ArrayList<String> listeConstructions;
    
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
    
    @Override
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
    				Gestion3D.mod.changerBlocaAjouter(selection);
    			}
        	});
        	
    	}else if (this.listeConstructions!=null) {
    		listeResultatRecherche.getItems().addAll(this.listeConstructions);
    		this.listeConstructions=null;
        	listeResultatRecherche.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    			@Override
    			public void changed(ObservableValue<? extends String> arg0, String ancienSelect, String selection) {
    				Gestion3D.mod.changerConstructionaAjouter(selection);
    			}
        	});
    	}
	}
    
    @FXML
    void SwitchFXMLMenu(ActionEvent event) throws IOException {
    	this.mod.sauvegarderSous();
    	this.mod.raz();
    	this.retourMenu(event);
    }
    
    @FXML
    void SwitchFXMLVisualisation(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("../vue/DialogBoxConstructionQuitter.fxml"));
    	Pane PopUpConstructionQuitter = loader.load();
    	
    	Dialog<ButtonType> dialog = new Dialog<>();
    	dialog.setDialogPane((DialogPane) PopUpConstructionQuitter);
    	
    	Optional<ButtonType> boutonClicker = dialog.showAndWait();
    	if (boutonClicker.get() == ButtonType.YES) {
    		this.mod.sauvegarderSous();
    		this.changerFenetre("Visualisation", event);
    	} else if (boutonClicker.get() == ButtonType.NO) {
    		this.changerFenetre("Visualisation", event);
    	}
    	
    	
    	//demander si on veux sauvegarder et si oui appeler this.mod.sauvegarder()
    
    }
    
    @FXML
    void SauvegarderConstruction(ActionEvent event) {
    	//demander le nom et modifier pour que this.mod.sauvegarder(String nom);
    	this.mod.sauvegarder();
    }

}
