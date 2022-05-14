package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ControleurVisualisation implements Initializable {
	
	Stage window;
	Scene scene;
	
	String[] construction = {"Construction1", "Construction2"};

	
	
	//-----------------------------------------------------
	
	//lancerAffichage3D
	//gererInteractions3D
	//changerScene (revenir au menu ou passer en construction ou en plein ecran)
	//rechercheMultiCrit (ici changer construction à afficher dans le modele)
	
	//-----------------------------------------------------
	
	
	@FXML
    private ListView<String> listeMode;
	
    @FXML
    private Button boutonMenu;

    @FXML
    private Button boutonModifier;

    @FXML
    private Button boutonPleinEcran;
    
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	listeMode.getItems().addAll(construction);
    	
    	listeMode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

			}
    		
    	});
		
	}
    
    @FXML
    void SwitchFXMLConstruction(ActionEvent event) throws IOException {

    	System.out.println(((Node)event.getSource()).getParent().getScene().getRoot());
    	
    	SubScene subScene3D=(SubScene) ((Node)event.getSource()).getParent().getScene().getRoot().getChildrenUnmodifiable().get(1);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	AnchorPane pane = new AnchorPane();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/Construction.fxml"));
	   	
	    pane = loader.load();

	   	//Gestion3D gestion3D=new Gestion3D(window);
	   	
	    pane.getChildren().add(subScene3D);
	    scene = new Scene(pane);
	    
	    //gestion3D.addTouches(scene);
	    
	    window.setScene(scene);
	    window.show();
    }
    
    @FXML
    void SwitchFXMLMenu(ActionEvent event) throws IOException {
    	
    	System.out.println("aaaa");
    	System.out.println(   ((Node)event.getSource()).getParent().getScene().getRoot().getChildrenUnmodifiable());//.get(1).getScene()    );
    	System.out.println("bbbbb");
    	
    	
    	
    	window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	Parent root = FXMLLoader.load((getClass().getResource("../vue/Menu.fxml")));
	    scene = new Scene(root);
	    
	    window.setTitle("LEGO");
	    window.setScene(scene);
	    window.show();
    }
    
    @FXML
    void SwitchFXMLPleinEcran(ActionEvent event) throws IOException {
    	SubScene subScene3D=(SubScene) ((Node)event.getSource()).getParent().getScene().getRoot().getChildrenUnmodifiable().get(1);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	AnchorPane pane = new AnchorPane();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/PleinEcran.fxml"));

	    pane = loader.load();

	   	//Gestion3D gestion3D=new Gestion3D(window);

	    pane.getChildren().add(subScene3D);
	    scene = new Scene(pane);
	    
	    subScene3D.heightProperty().bind(pane.heightProperty());
	    subScene3D.widthProperty().bind(pane.widthProperty());
	    
	    //gestion3D.addTouches(scene);
	    window.setScene(scene);
	    window.show();
    }
    
    
}
