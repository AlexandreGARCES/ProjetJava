package controleur;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Modele;
import vue.Gestion3D;

public class Controleur {
	public static Gestion3D vue3D=new Gestion3D();
	
	public Stage window;
	public Scene scene;
	public Modele mod=Gestion3D.mod;
	
	public Controleur () {
	}
	
	public void changerFenetre(String fenetre, ActionEvent event) throws IOException {
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	AnchorPane pane = new AnchorPane();

    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/"+fenetre+".fxml"));
	    pane = loader.load();

	   	pane.getChildren().add(Controleur.vue3D.subScene3D);
	    this.scene = new Scene(pane);
	    Controleur.vue3D.addTouches(this.scene);
	    
	    Modele.setMode(fenetre=="Construction");
	    
	    window.setScene(this.scene);
	    window.show();
	}

}
