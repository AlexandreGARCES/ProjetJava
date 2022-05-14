package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vue.Gestion3D;

public class ControleurConstruction implements Initializable {
	
	Stage window;
	Scene scene;
	
	//-----------------------------------------------------
	
	//lancerAffichage3D
	//gererInteractions3D (+ annuler et redo)
	//sauvegarderModele
	//changerScene (revenir au menu ou passer en visualisation ou en plein ecran)
	//rechercheMultiCrit (ici changer brique ou construction à rajouter à la construction actuelle)
	//prevenirQueBlocSelectionneEstModifié (le bloc a placer)
	//demanderSauvegardeAuModele
	
	
	public ControleurConstruction() {
		System.out.println("nouveau controleur construction");
	}
	
	//-----------------------------------------------------
	
	@FXML
    private ComboBox<CheckBox> CheckComboBox;
	
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
    private SubScene subScene3D;
    
    
    @FXML
    private CheckBox boxBlancBloc;

    @FXML
    private CheckBox boxBleuBloc;

    @FXML
    private CheckBox boxCarréBloc;

    @FXML
    private CheckBox boxCyanBloc;

    @FXML
    private CheckBox boxGrandBloc;

    @FXML
    private CheckBox boxGrisBloc;

    @FXML
    private CheckBox boxJauneBloc;

    @FXML
    private CheckBox boxMagentaBloc;

    @FXML
    private CheckBox boxMoyenBloc;

    @FXML
    private CheckBox boxNoirBloc;

    @FXML
    private CheckBox boxPetitBloc;

    @FXML
    private CheckBox boxRectBloc;

    @FXML
    private CheckBox boxRondBloc;

    @FXML
    private CheckBox boxRougeBloc;
    
    
    @FXML //oubliez pas de lui mettre truc que vous voulez ( a la place du string )
    private ListView<String> listeResultatRechercheBloc;
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Permet d'afficher la recherche
    	//listeResultatRechercheBloc.getItems().addAll(*une collection de bloc pour la recherche*);
    	//
    	//Listener pour obtenir le bloc sur lequel vous avez clicker
    	//listeResultatRechercheBloc.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
    	
    		//la m�thode correspondante.
			//@Override
			//public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
    		//
			//}
    		
    	//});
		
	}
    
    @FXML
    void SwitchFXMLMenu(ActionEvent event) throws IOException {
    	window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	Parent root = FXMLLoader.load((getClass().getResource("../vue/Menu.fxml")));
    	scene = new Scene(root);
	    
	    window.setTitle("LEGO");
	    window.setScene(scene);
	    window.show();
    }
    
    @FXML
    void SwitchFXMLVisualisation(ActionEvent event) throws IOException {
    	SubScene subScene3D=(SubScene) ((Node)event.getSource()).getParent().getScene().getRoot().getChildrenUnmodifiable().get(1);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	AnchorPane pane = new AnchorPane();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/Visualisation.fxml"));
	   	
	    pane = loader.load();

	   	//Gestion3D gestion3D=new Gestion3D(window);
	   	
	    pane.getChildren().add(subScene3D);
	    scene = new Scene(pane);
	    
	    //gestion3D.addTouches(scene);
	    
	    window.setScene(scene);
	    window.show();
    }
    
    @FXML
    void SauvegarderConstruction(ActionEvent event) {
    	// la methode qui va appel� sauvegarder du Mod�le
    }
    
    @FXML
    void rechercheMultiCritBloc(ActionEvent event) {
    	//Pour savoir si boxRougeBloc est activ�e:
    	//if(boxRougeBloc.isSelected()){ ---> renvoie un bool�en
    	//
    	//}
    	//(Pareil pour les autres box bien sur)
    	//
    	//Si tu veux pas avoir plus box selectionn�es en m�me temps:
    	//if(boxRougeBloc.isSelected()){
    	//	boxBleuBloc.setSelected(false);  ( d�selectionne la checkBox )
    	//}
    	//Apres si il vous manque des choses youtube est ton meilleur amis car je vois pas d'autre m�thodes utiles.
    	
    	
    	
    	
    	//Et juste pour morti car martin t'as pas le pb, la scroll pane te permet de retrecir le bouzin sans enlever l'interface tu peux ( en scrollant ) acceder aux boutons qui te posaient pb. 
    	//Bien sur la Subscene bug donc faudra faire avec
    }

}
