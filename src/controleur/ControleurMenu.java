package controleur;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.stage.Stage;
import modele.Modele;
import vue.Gestion3D;

public class ControleurMenu extends Controleur{
	
	//-----------------------------------------------------
	
	//sauvegarderModele quand on quitte
	
	//-----------------------------------------------------
	

    @FXML
    private Button boutonConstruction;

    @FXML
    private Button boutonVisualisation;

    @FXML
    private ImageView imageFond;

    @FXML
    private ImageView imageLogo;
    

    @FXML
    void switchFXMLConstruction(ActionEvent event) throws IOException {
    	String couleurPlateau = null;
    	TextInputDialog text = new TextInputDialog();
    	text.getDialogPane().setContentText("Quelle couleur de terrain voulez-vous choisir ?");
    	Optional<String> resultat = text.showAndWait();
    	TextField input = text.getEditor();
    	if (input.getText() != null && input.getText().toString().length() != 0) {
    		couleurPlateau = input.getText().toString();
    		System.out.println(couleurPlateau);
    		this.mod.raz();
        	this.changerFenetre("Construction", event);
    	} else { System.out.println("entrez une couleur !"); }
    }
    
    
    @FXML
    void switchFXMLVisualisation(ActionEvent event) throws IOException {
    	this.mod.raz();
    	this.changerFenetre("Visualisation", event);
    }
}

