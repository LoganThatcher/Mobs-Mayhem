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


public class GameOverWindow {
	private static final int map_height = 600;
	private static final int map_width = 900;
	static final Group root = new Group();
	static MediaPlayer mp;
	boolean muted;	
	Image unmute_image, mute_image;
	ImageView unmute_iv, mute_iv;

	public GameOverWindow (Scene go, Stage primaryStage)
    {	
		//Declarations
		Font font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 90);
		Font font2 = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 150);
		AudioClip click_player = new AudioClip(this.getClass().getResource("assets/click.mp3").toExternalForm());
		AudioClip click2_player = new AudioClip(this.getClass().getResource("assets/click2.mp3").toExternalForm());
    	Button newGame, highScores, menu;
    	Text t;
    	Text G;
    	Text E;
    	Text F;
    	Media m;
    	Image img;
    	ImagePattern pattern;

    	
    	//Main Title
    	t = new Text(225,160,"Game Over");
    	t.setFont(font2);
    	t.setFill(Color.RED);
    	
    	G = new Text(225,300,"You Have Failed To");
    	G.setFont(font);
    	G.setFill(Color.WHITE);
    	
    	E = new Text(225, 400, "Become The Godfather");
    	E.setFont(font);
    	E.setFill(Color.WHITE);
    	
  
    	//Buttons
    	newGame = new Button("New Game");
    	newGame.setFont(font);
    	highScores = new Button("High Scores");
    	highScores.setFont(font);
    	menu = new Button("Main Menu");
    	menu.setFont(font);
    	
   	
		img = new Image("assets/blackscreen.png");
		
		pattern = new ImagePattern(img);
		go.setFill(pattern);
        
        newGame.setLayoutX(215);
        newGame.setLayoutY(450);
        highScores.setLayoutX(360);
        highScores.setLayoutY(450);
        menu.setLayoutX(515);
        menu.setLayoutY(450);
        
        newGame.setPrefSize(150, 100);
        highScores.setPrefSize(150, 100);
        menu.setPrefSize(150, 100);
        
        
        newGame.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
        newGame.setOnMouseEntered(e -> {
        	newGame.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px");
        	click_player.play();
        });
        newGame.setOnMouseExited(e -> newGame.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
        
               
        highScores.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
        highScores.setOnMouseEntered(e -> {
        	highScores.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px");
        	click_player.play();
        });
        highScores.setOnMouseExited(e -> highScores.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
        
         
        menu.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
		menu.setOnMouseEntered(e -> {
			menu.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px");
			click_player.play();
		});
		menu.setOnMouseExited(e -> menu.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
				        
			
        root.getChildren().addAll(newGame, highScores, menu, t, G, E);
        
        
    	newGame.setOnAction(e -> {
    		
    		LoadWindow load = new LoadWindow(go, primaryStage);
    		primaryStage.getScene().setRoot(load.getRootGroup());
    		click2_player.play();
    		
    	});
    	
    	highScores.setOnAction(e -> {
    		HighScoreWindow hs = new HighScoreWindow(go, primaryStage);
    		primaryStage.getScene().setRoot(hs.getRootGroup());
    		click2_player.play();
    	});
    	
    	menu.setOnAction(e -> {
    		MainMenuWindow main = new MainMenuWindow(go, primaryStage);
    		primaryStage.getScene().setRoot(main.getRootGroup());
    		click2_player.play();
    	});
    	
    }
	
	public Group getRootGroup() {
		return root;
	}  


}
