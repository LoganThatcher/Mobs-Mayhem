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


public class GameOverWindow extends Application {
	private static final int map_height = 600;
	private static final int map_width = 900;
	static final Group root = new Group();
	static MediaPlayer mp;
	boolean muted;	
	Image unmute_image, mute_image;
	ImageView unmute_iv, mute_iv;

	public static void main(String[] args) {
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
		Font font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 90);
		Font font2 = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 150);
		AudioClip click_player = new AudioClip(getClass().getResource("assets/click.mp3").toURI().toString());
		AudioClip click2_player = new AudioClip(getClass().getResource("assets/click2.mp3").toURI().toString());
	    Scene home = new Scene(root, map_width, map_height);
    	Button newGame, highScores;
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
    	
    	/*F = new Text(225, 500, "Godfather");
    	F.setFont(font);
    	F.setFill(Color.RED); */
    	
    	//Buttons
    	newGame = new Button("New Game");
    	newGame.setFont(font);
    	highScores = new Button("High Scores");
    	highScores.setFont(font);
    	
    	//instructions = new Button("Instructions");
    	
    	
    	/*//Mute and Unmute images
    	unmute_image = new Image(getClass().getResourceAsStream("assets/unmute.png"));
    	unmute_iv = new ImageView(unmute_image);
    	unmute_iv.setFitHeight(15);
    	unmute_iv.setFitWidth(15);
    	
    	mute_image = new Image(getClass().getResourceAsStream("assets/mute.png"));
    	mute_iv = new ImageView(mute_image);
    	mute_iv.setFitHeight(15);
    	mute_iv.setFitWidth(15);
    	
    	mute = new Button("",unmute_iv);*/
    	

    	//Music
    	m = new Media(getClass().getResource("assets/godfather_theme.mp3").toURI().toString());
	    mp = new MediaPlayer(m);
	    mp.setVolume(0.35);
	    mp.play(); 
	    muted = false;

   	
		img = new Image("assets/start_screen.jpg");
		
		pattern = new ImagePattern(img);
		home.setFill(pattern);
    	stage.setScene(home);
        stage.setTitle("Mobs & Mayhem");
        
        newGame.setLayoutX(250);
        newGame.setLayoutY(400);
        highScores.setLayoutX(375);
        highScores.setLayoutY(400);
        //instructions.setLayoutX(450);
        //instructions.setLayoutY(400);
        
        
        newGame.setPrefSize(150, 100);
       highScores.setPrefSize(150, 100);
        
        //instructions.setPrefSize(150, 100);
        
        
        /*instructions.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
        instructions.setOnMouseEntered(e -> {
        	instructions.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px");
        	click_player.play();
        });
        settings.setOnMouseExited(e -> settings.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));*/
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
        
        
        
        root.getChildren().addAll(newGame, highScores, t, G, E);
        stage.show();
        
        
    	newGame.setOnAction(e -> {
    		
    		LoadWindow load = new LoadWindow(home,stage);
    		stage.getScene().setRoot(load.getRootGroup());
    		click2_player.play();
    		
    	});
    	
    	highScores.setOnAction(e -> {
    		SettingsWindow set = new SettingsWindow(home);
    		stage.getScene().setRoot(set.getRootGroup());
    		click2_player.play();
    	});

    	

    	
    	/*mute.setOnAction(e -> {
    		if(muted) {
    			mp.play();
    			mute.setGraphic(unmute_iv);
    			muted = false;
    		}else {
    			mp.pause();
    			mute.setGraphic(mute_iv);
    			muted = true;
    		}
    	}); */
    	
    }

}
