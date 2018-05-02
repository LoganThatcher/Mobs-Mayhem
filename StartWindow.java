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
 
public class StartWindow extends Application 
{
	private static final int KEYBOARD_MOVEMENT_DELTA = 5;
	private static final int map_height = 600;
	private static final int map_width = 900;
	private static final Group root = new Group();
	private static final Group game_root = new Group();
	
    
	public static void main(String[] args) 
    {
        launch(args);
    }
 
	@Override
    public void start(Stage stage) throws Exception
    {	
    	Scene home = new Scene(root, map_width, map_height);
    	Button startGame;
    	startGame = new Button("Start Game");
    	
    	
    	stage.setScene(home);
        stage.setTitle("Mobs & Mayhem");
        root.getChildren().add(startGame);
        stage.show();
        
        
    	startGame.setOnAction(e -> {
    		GameWindow game = new GameWindow(home);
    		stage.getScene().setRoot(game.getRootGroup());
    	});
    	
    	

     
    	
    }
   

}