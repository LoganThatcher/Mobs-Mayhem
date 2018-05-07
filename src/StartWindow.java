import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
 
public class StartWindow extends Application 
{
	private static final int map_height = 600;
	private static final int map_width = 900;
	static final Group root = new Group();
	MediaPlayer mp;
	
    
	public static void main(String[] args) 
    {
        launch(args);
    }
	
	
	@Override
    public void start(Stage stage) throws Exception
    {	
	    Scene home = new Scene(root, map_width, map_height);
    	Button startGame;
    	Button howToPlay;
    	Button settings;
    	Text t = new Text(250, 100, "Mobs & Mayhem");
    	t.setFill(Color.RED);
    	//t.setFont(Font.loadFont("assets/Corleone.ttf", 120));
    	t.setFont(Font.font("Verdana", 50));
    	startGame = new Button("Start Game");
    	howToPlay = new Button("How To Play");
    	settings = new Button("Settings");

    	/*
    	Media m = new Media(getClass().getResource("assets/godfather_theme.mp3").toURI().toString());
	    mp = new MediaPlayer(m);
	    mp.setVolume(0.5);
	    mp.play(); //Comment this out if you don't want music to play
*/
   	
		String url = "assets/start_screen.jpg";
		Image img = new Image(url);
		
		ImagePattern pattern = new ImagePattern(img);
		home.setFill(pattern);
    	stage.setScene(home);
        stage.setTitle("Mobs & Mayhem");
        
        howToPlay.setLayoutX(225);
        howToPlay.setLayoutY(400);
        settings.setLayoutX(350);
        settings.setLayoutY(400);
        startGame.setLayoutX(475);
        startGame.setLayoutY(400);
        
        startGame.setPrefSize(150, 100);
        howToPlay.setPrefSize(150, 100);
        settings.setPrefSize(150, 100);
        
        settings.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
        settings.setOnMouseEntered(e -> settings.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px"));
        settings.setOnMouseExited(e -> settings.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
        
        startGame.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
        startGame.setOnMouseEntered(e -> startGame.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px"));
        startGame.setOnMouseExited(e -> startGame.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
        
        howToPlay.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
        howToPlay.setOnMouseEntered(e -> howToPlay.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px"));
        howToPlay.setOnMouseExited(e -> howToPlay.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
        root.getChildren().addAll(startGame, howToPlay, settings, t);
        stage.show();
        
        
    	startGame.setOnAction(e -> {
    		GameWindow game = new GameWindow(home,stage);
    		stage.getScene().setRoot(game.getRootGroup());
    	});
    	
    	settings.setOnAction(e -> {
    		SettingsWindow set = new SettingsWindow(home);
    		stage.getScene().setRoot(set.getRootGroup());
    	});   
    	
    }
   
}