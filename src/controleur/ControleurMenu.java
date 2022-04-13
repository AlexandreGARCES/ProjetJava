package controleur;

import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import modele.Modele;
import javafx.scene.Node;

public class ControleurMenu {
	
	private double anchorX, anchorY;
	private double anchorAngleX = 0;
	private double anchorAngleY = 0;
	private final DoubleProperty angleX = new SimpleDoubleProperty(21.0);
	private final DoubleProperty angleY = new SimpleDoubleProperty(46.0);
	
	private static final int WIDTH = 1400;
	private static final int HEIGHT = 800;
	Stage window;
	Scene scene;

    @FXML
    private Button boutonCatalgue;

    @FXML
    private Button boutonJouer;
    
    @FXML
    private ImageView imageFond;

    @FXML
    private ImageView imageLogo;

    @FXML
    public void afficherJeu(ActionEvent event) throws IOException {
    	
    	Modele mod = new Modele(); 	
		Group group = mod.getTerrain();
		AnchorPane pane = new AnchorPane();
		SubScene subScene = new SubScene(group, WIDTH, HEIGHT, true, null);
		
		
		Camera camera = new PerspectiveCamera();
	    camera.setTranslateZ(-30);
	    subScene.setCamera(camera);

	    
	    group.translateZProperty().set(3000);
		
	    initMouseControl(group,subScene);
	    
	   	FXMLLoader loader = new FXMLLoader(getClass().getResource("../vue/Jeu.fxml"));
	   	window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    pane = loader.load();
	    pane.getChildren().add(subScene);
	    scene = new Scene(pane);
	    	scene.addEventHandler(KeyEvent.KEY_PRESSED, e->{
    		switch(e.getCode())
    		{
    		case W:
    			mod.setCouleur(Color.WHITE);
    			break;
    		case B:
    			mod.setCouleur(Color.BLUE);
    			break;
    		
	    	case R:
				mod.setCouleur(Color.RED);
				break;
			
	    	case V:
				mod.setCouleur(Color.GREEN);
				break;
			default:
				break;
    
		}
    		
    	});
	    
	    window.setScene(scene);
	    window.show();
    }
    
    private void initMouseControl(Group group,SubScene  subScene) {
    	Rotate xRotate;
    	Rotate yRotate;
    	group.getTransforms().addAll(
    			xRotate = new Rotate(0, Rotate.X_AXIS),
    			yRotate = new Rotate(0, Rotate.Y_AXIS));
    	
    	xRotate.angleProperty().bind(angleX);
    	yRotate.angleProperty().bind(angleY);
    	
    	subScene.setOnMousePressed(event -> {
    		anchorX = event.getSceneX();
    		anchorY = event.getSceneY();
    		anchorAngleX = angleX.get();
    		anchorAngleY = angleY.get();
    	});
    	
    	subScene.setOnMouseDragged(event -> {
    		angleX.set(anchorAngleX-(anchorY- event.getSceneY()));
    		angleY.set(anchorAngleY-(anchorX- event.getSceneX()));
    		
    	});
    	
    	subScene.addEventHandler(ScrollEvent.SCROLL, event ->{
    		double delta = event.getDeltaY();
    		group.translateZProperty().set(group.getTranslateZ() - 10*delta);
    	});
		
	}
    
}
