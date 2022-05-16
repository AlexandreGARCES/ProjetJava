package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

}
