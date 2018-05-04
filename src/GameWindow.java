import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;

import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern; 
import javafx.scene.image.Image;
 
public class GameWindow
{
	private static final int KEYBOARD_MOVEMENT_DELTA = 5;
	private static final int map_height = 600;
	private static final int map_width = 900;
	private final Group rootGroup;	
    
	public GameWindow(Scene game) {
		rootGroup = new Group();
		
		
		String url = "assets/cobblestone.jpg";
		Image img = new Image(url);
		
		ImagePattern pattern = new ImagePattern(img);
		game.setFill(pattern);
		
		
    	Circle circle = new Circle();
    	
        circle.setCenterX(40);
        circle.setCenterY(40);
        circle.setRadius(10);
        rootGroup.getChildren().add(circle);
        
        moveCircleOnKeyPress(game, circle);
	}

	
    
    private void moveCircleOnKeyPress(Scene scene, final Circle circle) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
          @Override public void handle(KeyEvent event) {
            switch (event.getCode()) {
            case UP:
          	  if(!(circle.getCenterY() == 0))
          		  circle.setCenterY(circle.getCenterY() - KEYBOARD_MOVEMENT_DELTA); 
          	  break;
            case RIGHT: 
          	  if(!(circle.getCenterX() == map_width))
          		  circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA); break;
            case DOWN: 
          	  if(!(circle.getCenterY() == map_height))
          		  circle.setCenterY(circle.getCenterY() + KEYBOARD_MOVEMENT_DELTA); break;
            case LEFT:  
          	  if(!(circle.getCenterX() == 0))
          	  circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA); break;
            }
          }
        });
      }
    
    public Group getRootGroup() {
    	return rootGroup;
    }

}