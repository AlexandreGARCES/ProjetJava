package vue;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import modele.Modele;

public class Gestion3D {

    private double anchorX, anchorY;
	private double anchorAngleX = 0;
	private double anchorAngleY = 0;
	private final DoubleProperty angleX = new SimpleDoubleProperty(21.0);
	private final DoubleProperty angleY = new SimpleDoubleProperty(46.0);
	private static final int WIDTH = 1400;
	private static final int HEIGHT = 600;
	
	public Modele mod;
	public Group group;
	public Camera camera;
	public SubScene subScene3D;
	
	
	public Gestion3D(Stage window) {
		this.mod = new Modele(); 	
		this.group = mod.getTerrain();
		this.subScene3D = new SubScene(group, WIDTH, HEIGHT, true, null);
		
		
		this.camera = new PerspectiveCamera();
	    camera.setTranslateZ(-30);
	    subScene3D.setCamera(camera);

	    group.translateXProperty().set(WIDTH/2);
	    group.translateYProperty().set(HEIGHT/2);
	    group.translateZProperty().set(3000);
		
	    initMouseControl(group,subScene3D);
	    
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
    		if(group.getTranslateZ()>-1000 || delta<=0) {
    			group.translateZProperty().set(group.getTranslateZ() - 10*delta);
    		}
    		
    	});
		
	}



	public void addTouches(Scene scene) {
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
	}

}
