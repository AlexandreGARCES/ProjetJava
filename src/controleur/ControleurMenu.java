package controleur;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.stage.Stage;
import modele.Modele;
import vue.Gestion3D;

public class ControleurMenu {

	Stage window;
	Scene scene;
	
	
	//-----------------------------------------------------
	
	//PasserAUneAutreScene
	
	
	public ControleurMenu() {
		System.out.println("nouveau Controleur menu");
	}
	
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
    	//penser à set le mode du modèle
    	System.out.println(((Node)event.getSource()).getParent().getScene().getRoot());
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	AnchorPane pane = new AnchorPane();
    	
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/Construction.fxml"));
	   	
	    pane = loader.load();

	   	Gestion3D gestion3D=new Gestion3D(window);
	   	
	    pane.getChildren().add(gestion3D.subScene3D);
	    scene = new Scene(pane);
	    
	    gestion3D.addTouches(scene);
	    
	    Modele.setMode(true);
	    
	    window.setScene(scene);
	    window.show();
    }
    
    
    @FXML
    void switchFXMLVisualisation(ActionEvent event) throws IOException {
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	AnchorPane pane = new AnchorPane();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/Visualisation.fxml"));
	   	
	    pane = loader.load();
	    
	    
	   	Gestion3D gestion3D=new Gestion3D(window);
	   	
	    pane.getChildren().add(gestion3D.subScene3D);
	    scene = new Scene(pane);
	    
	    gestion3D.addTouches(scene);
	    
	    window.setScene(scene);
	    window.show();
    	
    }
}

