package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private SubScene subScene3D;
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		
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
    	
    }

}
