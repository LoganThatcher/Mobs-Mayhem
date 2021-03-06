package logic;

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
	Scene home = new Scene(root, map_width, map_height);
	Image img;
	ImagePattern pattern;
	static Stage stage1;
	
	public static void main(String[] args) 
    {
        launch(args);
    }
		
	@Override
    public void start(Stage stage) throws Exception
    {	   
		
		//Background Image
		img = new Image("logic/assets/start_screen.jpg");
		
		pattern = new ImagePattern(img);
		home.setFill(pattern);
        stage.setTitle("Mob Mayhem");
	
        //Declarations
		Font font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 150);		
		AudioClip click_player = new AudioClip(getClass().getResource("assets/click.mp3").toURI().toString());
		AudioClip click2_player = new AudioClip(getClass().getResource("assets/click2.mp3").toURI().toString());	    	
		Button startGame, howToPlay, settings;
	    Text t;
	   	Media m;
		 	
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
        
        
        //Button Actions
    	startGame.setOnAction(e -> {
    		
    		StoryBoard1 sb1 = new StoryBoard1(home,stage);
    		stage.getScene().setRoot(sb1.getRootGroup());
    		click2_player.play();
    		
    	});
    	
    	settings.setOnAction(e -> {
    		SettingsWindow set = new SettingsWindow(home, stage);
    		stage.getScene().setRoot(set.getRootGroup());
    		click2_player.play();
    	});

    	howToPlay.setOnAction( e -> {
    		InstructionsWindow instruct = new InstructionsWindow(home, stage);
    		stage.getScene().setRoot(instruct.getRootGroup());
    		click2_player.play();
    	});
    	
    	stage.setScene(home);
    	stage.show();
    	
    	stage1 = stage;

    }
	
	public Group getRootGroup() {
		return root;
	}
	
	public static Stage getStage() {
		return stage1;
	}
}