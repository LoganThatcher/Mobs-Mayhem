import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainMenuWindow {

	private final Group rootGroup;
	private final Text t;
	boolean muted;
	private static final int map_height = 600;
	private static final int map_width = 900;
	static MediaPlayer mp;
	Image unmute_image, mute_image;
	ImageView unmute_iv, mute_iv;
	Image img;
	ImagePattern pattern;

	public MainMenuWindow(Scene main, Stage primaryStage)
	{
		//Background Image		
		img = new Image("assets/start_screen.jpg");
				
		pattern = new ImagePattern(img);
		main.setFill(pattern);
			
		//Declarations
	    Font font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 150);		
	    AudioClip click_player = new AudioClip(this.getClass().getResource("assets/click.mp3").toExternalForm());
		AudioClip click2_player = new AudioClip(this.getClass().getResource("assets/click2.mp3").toExternalForm());  	
		Button startGame, howToPlay, settings;
	    Media m;
				 	
	    //Main Title
	    rootGroup = new Group();
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
				        
				        
	    rootGroup.getChildren().addAll(startGame, howToPlay, settings, t);
				        
				        
		//Button Actions
		startGame.setOnAction(e -> {	    		
			StoryBoard1 sb1 = new StoryBoard1(main,primaryStage);
    		primaryStage.getScene().setRoot(sb1.getRootGroup());
    		click2_player.play();	    		
		});
				    	
		settings.setOnAction(e -> {
		    SettingsWindow set = new SettingsWindow(main,primaryStage);
			primaryStage.getScene().setRoot(set.getRootGroup());
			click2_player.play();
		});

		howToPlay.setOnAction( e -> {
			InstructionsWindow instruct = new InstructionsWindow(main, primaryStage);
			primaryStage.getScene().setRoot(instruct.getRootGroup());
			click2_player.play();
		});

	}

	public Group getRootGroup() {
		return rootGroup;
	} 
}


