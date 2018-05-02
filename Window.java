import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;
import javafx.scene.input.*;
 
public class Window extends Application 
{
	private static final int      KEYBOARD_MOVEMENT_DELTA = 5;
	private static final int map_height = 600;
	private static final int map_width = 900;
    
	public static void main(String[] args) 
    {
        launch(args);
    }
 
    public void start(Stage stage) 
    {
    	Group root = new Group();
    	Scene scene = new Scene(root, map_width, map_height);
    	
    	//create circle
    	Circle circle = new Circle();
        circle.setCenterX(40);
        circle.setCenterY(40);
        circle.setRadius(10);
        root.getChildren().add(circle);
        
        moveCircleOnKeyPress(scene, circle);
        
        //display window
    	stage.setScene(scene);
        stage.setTitle("Mobs & Mayhem");
        stage.show();
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

}
