
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
 
public class StartWindow extends Application 
{
	private static final int map_height = 600;
	private static final int map_width = 900;
	static final Group root = new Group();
	MediaPlayer mp;
	boolean muted;	
	Image unmute_image, mute_image;
	ImageView unmute_iv, mute_iv;
    
	public static void main(String[] args) 
    {
        launch(args);
    }
	
	
	@Override
    public void start(Stage stage) throws Exception
    {	
		/*
	    Screen screen = Screen.getPrimary();
	    Rectangle2D bounds = screen.getVisualBounds();
	    stage.setX(bounds.getMinX());
	    stage.setY(bounds.getMinY());
	    stage.setWidth(bounds.getWidth());
	    stage.setHeight(bounds.getHeight());
	    */
		
		//Declarations
		Font font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 150);		
	    Scene home = new Scene(root, map_width, map_height);
    	Button startGame;
    	Button howToPlay;
    	Button settings;
    	Button mute;
    	
    	//Main Title
    	Text t = new Text(225,160,"Mob Mayhem");
    	t.setFont(font);
    	t.setFill(Color.WHITE);
    	
    	//Buttons
    	startGame = new Button("Start Game");
    	startGame.setFont(font);
    	howToPlay = new Button("How To Play");
    	settings = new Button("Settings");
    	
    	
    	//Mute and Unmute images
    	unmute_image = new Image(getClass().getResourceAsStream("assets/unmute.png"));
    	unmute_iv = new ImageView(unmute_image);
    	unmute_iv.setFitHeight(15);
    	unmute_iv.setFitWidth(15);
    	
    	mute_image = new Image(getClass().getResourceAsStream("assets/mute.png"));
    	mute_iv = new ImageView(mute_image);
    	mute_iv.setFitHeight(15);
    	mute_iv.setFitWidth(15);
    	
    	mute = new Button("",unmute_iv);
    	

    	//Music
    	Media m = new Media(getClass().getResource("assets/godfather_theme.mp3").toURI().toString());
	    mp = new MediaPlayer(m);
	    mp.setVolume(0.5);
	    mp.play(); 
	    muted = false;

   	
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
        root.getChildren().addAll(startGame, howToPlay, settings, t, mute);
        stage.show();
        
        
    	startGame.setOnAction(e -> {
    		GameWindow game = new GameWindow(home,stage);
    		stage.getScene().setRoot(game.getRootGroup());
    	});
    	
    	settings.setOnAction(e -> {
    		SettingsWindow set = new SettingsWindow(home);
    		stage.getScene().setRoot(set.getRootGroup());
    	});   
    	
    	mute.setOnAction(e -> {
    		if(muted) {
    			mp.play();
    			mute.setGraphic(unmute_iv);
    			muted = false;
    		}else {
    			mp.pause();
    			mute.setGraphic(mute_iv);
    			muted = true;
    		}
    	}); 
    	
    }
   
}