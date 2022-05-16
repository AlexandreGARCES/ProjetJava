package controleur;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;

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
    	String[] couleur = {"rouge","bleu","vert","cyan","jaune","orange","gris","blanc","noir"}; 
    	String couleurPlateau = null;
    	TextInputDialog text = new TextInputDialog();
    	text.getDialogPane().setContentText("Quelle couleur de terrain voulez-vous choisir ?\nRouge,Bleu,Vert,Cyan,Jaune,Orange,Gris,Blanc,Noir");
    	Optional<String> resultat = text.showAndWait();
    	TextField input = text.getEditor();
    	if (input.getText() != null && input.getText().toString().length() != 0) {
    		couleurPlateau = input.getText().toString();
    		for(int i=0; i<9; i++) {
    			if (couleurPlateau.toLowerCase().equals(couleur[i].toLowerCase())) {
    				System.out.println(couleurPlateau);
    				//utilise couleurPlateau
    				
    				this.mod.raz();
    	        	this.changerFenetre("Construction", event);
    				break;
    			}
    		}
    	} else { System.out.println("entrez une couleur !"); }
    }
    
    
    @FXML
    void switchFXMLVisualisation(ActionEvent event) throws IOException {
    	this.mod.raz();
    	this.changerFenetre("Visualisation", event);
    }
}

