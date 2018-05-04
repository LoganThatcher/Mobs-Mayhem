import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class GameWindow
{
	private static final int KEYBOARD_MOVEMENT_DELTA = 5;
	private static final int map_height = 600;
	private static final int map_width = 900;
	private final Group rootGroup;	
    
	public GameWindow(Scene game, Stage primaryStage) {
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
        
        moveCircleOnKeyPress(game, circle,primaryStage);
        
	}

	
    
    private void moveCircleOnKeyPress(Scene scene, final Circle circle,Stage primaryStage) {
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
            	case ESCAPE: 
            		rootGroup.setEffect(new GaussianBlur());
                  	
                	  VBox pauseRoot = new VBox(5);
                	  pauseRoot.getChildren().add(new Label("Paused"));
                    pauseRoot.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
                    pauseRoot.setAlignment(Pos.CENTER);
                    pauseRoot.setPadding(new Insets(20));
                  
                    
                    Button resume = new Button("RESUME");
                    pauseRoot.getChildren().add(resume);
                    
                    Stage popupStage = new Stage(StageStyle.TRANSPARENT);
                    popupStage.initOwner(primaryStage);
                    popupStage.initModality(Modality.APPLICATION_MODAL);
                    popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));
                    
                    resume.setOnAction(e ->{
                    	rootGroup.setEffect(null);
                    	popupStage.hide();
                    });
                    
                    popupStage.show();
            		
          
            }
          }
        });
      }
    
    public Group getRootGroup() {
    	return rootGroup;
    }

}