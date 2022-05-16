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
import javafx.scene.control.TextInputDialog;
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
    void SwitchFXMLMenu(ActionEvent event) throws IOException {
    	FXMLLoader loaderBox1 = new FXMLLoader();
    	loaderBox1.setLocation(getClass().getResource("../vue/DialogBoxConstructionQuitter.fxml"));
    	Pane PopUpConstructionQuitter = loaderBox1.load();
    	
    	Dialog<ButtonType> dialogBox1 = new Dialog<>();
    	dialogBox1.setDialogPane((DialogPane) PopUpConstructionQuitter);
    	
    	Optional<ButtonType> boutonClicker = dialogBox1.showAndWait();
    	if (boutonClicker.get() == ButtonType.YES) {
    		String nomSauv = null;
        	TextInputDialog text = new TextInputDialog();
        	text.getDialogPane().setContentText("Quel nom voulez-vous utiliser ?");
        	Optional<String> resultat = text.showAndWait();
        	TextField input = text.getEditor();
        	if (input.getText() != null && input.getText().toString().length() != 0) {
        		nomSauv = input.getText().toString();
        		this.mod.sauvegarderSous(nomSauv);
        		this.mod.raz();
        		this.mod.sauvegarde=null;
        		this.retourMenu(event);
        	} else { System.out.println("entrez un nom !"); }
    	} else if (boutonClicker.get() == ButtonType.NO) {
    		this.mod.raz();
    		this.mod.sauvegarde=null;
    		this.retourMenu(event);
    	}
    }
    
    @FXML
    void SwitchFXMLVisualisation(ActionEvent event) throws IOException {
    	FXMLLoader loaderBox1 = new FXMLLoader();
    	loaderBox1.setLocation(getClass().getResource("../vue/DialogBoxConstructionQuitter.fxml"));
    	Pane PopUpConstructionQuitter = loaderBox1.load();
    	
    	Dialog<ButtonType> dialogBox1 = new Dialog<>();
    	dialogBox1.setDialogPane((DialogPane) PopUpConstructionQuitter);
    	
    	Optional<ButtonType> boutonClicker = dialogBox1.showAndWait();
    	if (boutonClicker.get() == ButtonType.YES) {
    		String nomSauv = null;
        	TextInputDialog text = new TextInputDialog();
        	text.getDialogPane().setContentText("Quel nom voulez-vous utiliser ?");
        	Optional<String> resultat = text.showAndWait();
        	TextField input = text.getEditor();
        	if (input.getText() != null && input.getText().toString().length() != 0) {
        		nomSauv = input.getText().toString();
        		this.mod.sauvegarderSous(nomSauv);
        		this.mod.sauvegarde=null;
        		this.changerFenetre("Visualisation", event);
        	} else { System.out.println("entrez un nom !"); }
    	} else if (boutonClicker.get() == ButtonType.NO) {
    		this.mod.sauvegarde=null;
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
