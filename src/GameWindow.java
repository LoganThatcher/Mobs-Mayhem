import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.net.URISyntaxException;
import java.util.Random;

import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;

public class GameWindow
{
	private static final int KEYBOARD_MOVEMENT_DELTA = 5;
	private static final int map_height = 600;
	private static final int map_width = 900;
	private final Group rootGroup;	
	AudioClip click_player;
	
	Random ran = new Random();

	public GameWindow(Scene game, Stage primaryStage){
		rootGroup = new Group();
		
		
		
		String url = "assets/cobblestone.jpg";
		Image img = new Image(url);
		
		
		ImagePattern pattern = new ImagePattern(img);
		game.setFill(pattern);
		
		Circle player = new Circle();
        Polygon police = new Polygon();
        police.getPoints().addAll(new Double[]{
            0.0, 0.0,
            20.0, 10.0,
            10.0, 20.0 });
        
        // Random movements
        PathTransition pt = new PathTransition();
        pt.setNode(police);
        pt.setDuration(Duration.seconds(3));
        pt.setPath(createPath());
        pt.setCycleCount(PathTransition.INDEFINITE);
        pt.play();
			
        player.setCenterX(40);
        player.setCenterY(40);
        player.setRadius(10);
        rootGroup.getChildren().addAll(player, police);
        
        moveCircleOnKeyPress(game, player,primaryStage);
        
	}
	
	
	private Path createPath() {
        int loc = ran.nextInt(600 - 300 + 1) + 300; // min=300 , max=600

        Path path = new Path();

        path.getElements().add(new MoveTo(20, 20));
        path.getElements().add(new LineTo(loc, 600));

        return path;
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
            		pause(primaryStage, scene);
            	default:
            		break;
          
            }
          }
        });
      }
    
    private void pause(Stage primaryStage, Scene scene){
    
		try {
			click_player = new AudioClip(getClass().getResource("assets/click2.mp3").toURI().toString());
			click_player.play();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
    	
     	rootGroup.setEffect(new GaussianBlur());
    	Font font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 150);	
      	
	  	VBox pauseRoot = new VBox(5);
	  	pauseRoot.prefWidthProperty().bind(primaryStage.widthProperty());
	  	pauseRoot.prefHeightProperty().bind(primaryStage.heightProperty());

	  	
	  	Text pause_title = new Text("Paused");
	  	pause_title.setFont(font);
	  	pause_title.setFill(Color.WHITE);
	  	
	  	pauseRoot.getChildren().add(pause_title);
	    pauseRoot.setStyle("-fx-background-color: rgba(255, 255, 255, 0);");
	    pauseRoot.setAlignment(Pos.CENTER);
	    pauseRoot.setPadding(new Insets(20));
	    
	      
	    Button resume = new Button("RESUME");
	    Button restart = new Button("RESTART");
	    Button settings = new Button("SETTINGS");
	    Button instructions = new Button("INSTRUCTIONS");
	    Button quit = new Button("QUIT");
	    pauseRoot.getChildren().addAll(resume,restart,settings,instructions,quit);

	    
	    Stage popupStage = new Stage(StageStyle.TRANSPARENT);
	    popupStage.initOwner(primaryStage);
	    popupStage.initModality(Modality.APPLICATION_MODAL);
	    popupStage.setScene(new Scene(pauseRoot, Color.BLACK));
	    
	    
	    resume.setOnAction(e ->{
	    	rootGroup.setEffect(null);
	      	popupStage.hide();
	    });
	    settings.setOnAction(e -> {
    		SettingsWindow set = new SettingsWindow(scene);
    		primaryStage.getScene().setRoot(set.getRootGroup());
    		rootGroup.setEffect(null);;
    		popupStage.hide();
    	});
	    instructions.setOnAction(e -> {
    		InstructionsWindow instruct = new InstructionsWindow(scene);
    		primaryStage.getScene().setRoot(instruct.getRootGroup());
    		rootGroup.setEffect(null);;
    		popupStage.hide();
    	});
	    pauseRoot.setOnKeyPressed(new EventHandler<KeyEvent>() {
	          @Override public void handle(KeyEvent event) {
	            switch (event.getCode()) {
	            	case ESCAPE: 
	                 	click_player.play();
	            		rootGroup.setEffect(null);
	        	      	popupStage.hide();
	            	default:
	            		break;
	            }
	          }
	    });
	      
	    popupStage.show();
	
    
    }
    
    public Group getRootGroup() {
    	return rootGroup;
    }

}