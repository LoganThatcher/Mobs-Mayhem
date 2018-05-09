import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
 
public class StartWindow extends Application 
{
	private static final int map_height = 600;
	private static final int map_width = 900;
	static final Group root = new Group();
	static MediaPlayer mp;
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
		AudioClip click_player = new AudioClip(getClass().getResource("assets/click.mp3").toURI().toString());
		AudioClip click2_player = new AudioClip(getClass().getResource("assets/click2.mp3").toURI().toString());
	    Scene home = new Scene(root, map_width, map_height);
    	Button startGame, howToPlay, settings;
    	Text t;
    	Media m;
    	Image img;
    	ImagePattern pattern;
 	
    	//Main Title
    	t = new Text(225,160,"Mob Mayhem");
    	t.setFont(font);
    	t.setFill(Color.RED);
    	
    	//Buttons
    	startGame = new Button("Start Game");
    	startGame.setFont(font);
    	howToPlay = new Button("How To Play");
    	howToPlay.setFont(font);
    	settings = new Button("Settings");
    	settings.setFont(font);
    	
    	//Music
    	m = new Media(getClass().getResource("assets/godfather_theme.mp3").toURI().toString());
	    mp = new MediaPlayer(m);
	    mp.setVolume(0.4);
	    mp.play(); 
	    muted = false;
   	
	    //Background Image
		img = new Image("assets/start_screen.jpg");
		
		pattern = new ImagePattern(img);
		home.setFill(pattern);
    	stage.setScene(home);
        stage.setTitle("Mobs & Mayhem");
        
        //Button Positioning and Sizing
        howToPlay.setLayoutX(250);
        howToPlay.setLayoutY(400);
        settings.setLayoutX(375);
        settings.setLayoutY(400);
        startGame.setLayoutX(500);
        startGame.setLayoutY(400);
        
        startGame.setPrefSize(150, 100);
        howToPlay.setPrefSize(150, 100);
        settings.setPrefSize(150, 100);
        
        //Mouse on Button Actions
        settings.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
        settings.setOnMouseEntered(e -> {
        	settings.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px");
        	click_player.play();
        });
        settings.setOnMouseExited(e -> settings.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
        
        startGame.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
        startGame.setOnMouseEntered(e -> {
        	startGame.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px");
        	click_player.play();
        });
        startGame.setOnMouseExited(e -> startGame.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
        
        howToPlay.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
        howToPlay.setOnMouseEntered(e -> {
        	howToPlay.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px");
        	click_player.play();
        });
        howToPlay.setOnMouseExited(e -> howToPlay.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
        
        
        root.getChildren().addAll(startGame, howToPlay, settings, t);
        stage.show();
        
        
        //Button Actions
    	startGame.setOnAction(e -> {
    		
    		LoadWindow load = new LoadWindow(home,stage);
    		stage.getScene().setRoot(load.getRootGroup());
    		click2_player.play();
    		
    	});
    	
    	settings.setOnAction(e -> {
    		SettingsWindow set = new SettingsWindow(home);
    		stage.getScene().setRoot(set.getRootGroup());
    		click2_player.play();
    	});

    	howToPlay.setOnAction( e -> {
    		InstructionsWindow instruct = new InstructionsWindow(home);
    		stage.getScene().setRoot(instruct.getRootGroup());
    		click2_player.play();
    	});
    	
    }
   
}