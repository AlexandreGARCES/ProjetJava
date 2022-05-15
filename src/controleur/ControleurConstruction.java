package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
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

    @FXML //oubliez pas de lui mettre truc que vous voulez ( a la place du string )
    private ListView<String> listeResultatRechercheBloc;
    
    @FXML
    void rechercheMultiCritBloc(ActionEvent event) {
    	//réunir plusieurs checkbox ensemble nous simplifierais la vie
    	
    	
    	if (boxBlancBloc.isSelected()) {
    		System.out.println("blanc");
    	}
    	if (boxBleuBloc.isSelected()) {
    		System.out.println("bleu");
    	}
    	if(boxCyanBloc.isSelected()) {
    		System.out.println("cyan");
    	}
    	if(boxGrisBloc.isSelected()) {
    		System.out.println("gris");
    	}
    	if(boxOrangeBloc.isSelected()) {
    		System.out.println("Orange");
    	}
    	if(boxNoirBloc.isSelected()) {
    		System.out.println("noir");
    	}
    	if(boxRougeBloc.isSelected()) {
    		System.out.println("rouge");
    	}
    	if(boxVertBloc.isSelected()) {
    		System.out.println("vert");
    	}
    	if(boxJauneBloc.isSelected()){
    		System.out.println("jaune");
    	}
    	
    	
    	if(boxCarreBloc.isSelected()) {
    		System.out.println("carré");
    	}
    	if(boxRectBloc.isSelected()) {
    		System.out.println("rectangle");
    	}
    	if(boxRondBloc.isSelected()) {
    		System.out.println("rond");
    	}
    	
    	
    	if(boxGrandBloc.isSelected()) {
    		System.out.println("grand");
    	}
    	if(boxPetitBloc.isSelected()) {
    		System.out.println("petit");
    	}
    	if(boxMoyenBloc.isSelected()) {
    		System.out.println("moyen");
    	}
    	
    	
    	
    	//Si tu veux pas avoir plus box selectionn�es en m�me temps:
    	//if(boxRougeBloc.isSelected()){
    	//	boxBleuBloc.setSelected(false);  ( d�selectionne la checkBox )
    	//}
    	
    }
    
    @FXML
    void rechercheMultiCritConstruc(ActionEvent event) {
    	System.out.println("recherche dans Modele.constructions");
    	//par nom: dans modèle: pour chaque String dans constructions.setKey, if ma String est contenue dans la key: rajouter aux résultats recherche
    	//par couleur de la base
    	//par couleurs présentes (ou exclure une couleur présente?)
    	//par formes présentes?
    	//par tailles présentes?
    	
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	//this.mod.listeResultatRecherche (à transformer en listView)
    	
		//Permet d'afficher la recherche
    	//listeResultatRechercheBloc.getItems().addAll(*une collection de bloc pour la recherche*);
    	//
    	//Listener pour obtenir le bloc sur lequel vous avez clicker
    	//listeResultatRechercheBloc.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    	
    		//la m�thode correspondante.
			//@Override
			//public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
    		//Gestion3D.mod.changerConstructionActuelle(selection);
    		//penser à trouver quel argument fait quoi
			//}
    		
    	//});
		
	}
    
    @FXML
    void SwitchFXMLMenu(ActionEvent event) throws IOException {
    	this.retourMenu(event);
    }
    
    @FXML
    void SwitchFXMLVisualisation(ActionEvent event) throws IOException {
    	//demander si on veux sauvegarder et si oui appeler this.mod.sauvegarder()
    	this.changerFenetre("Visualisation", event);
    }
    
    @FXML
    void SauvegarderConstruction(ActionEvent event) {
    	//demander le nom et modifier pour que this.mod.sauvegarder(String nom);
    	this.mod.sauvegarder();
    }

}
