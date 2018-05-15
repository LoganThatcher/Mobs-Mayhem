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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.net.URISyntaxException;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;

public class GameWindow
{
	private static final int KEYBOARD_MOVEMENT_DELTA = 10;
	private static final int map_height = 600;
	private static final int map_width = 900;
	private static final Color cop_color = Color.BLUE;
	private static final Color player_color = Color.BLACK;
	private static final Color cit_color = Color.ANTIQUEWHITE;
	private final Group rootGroup;	
	AudioClip click_player;
	
	Random ran = new Random();

	public GameWindow(Scene game, Stage primaryStage){
		String url = "assets/cobblestone.jpg";
		Image img = new Image(url);
		ImagePattern pattern = new ImagePattern(img);
		Timeline circMover;
		Circle cop = new Circle();
		Circle cit = new Circle();
		Circle player = new Circle();
		
		game.setFill(pattern);
		rootGroup = new Group();
			
		circMover = new Timeline(new KeyFrame(Duration.millis(100), new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        moveCircleRandomly(cop);
		        moveCircleRandomly(cit);
		        checkCollisions(cop, player);
		        checkCollisions(cit,player);
		    }
		}));
		circMover.setCycleCount(Timeline.INDEFINITE);
		circMover.play();

		
		// Character placements and settings	
        player.setCenterX(map_width/2);
        player.setCenterY(map_height/2);
        player.setRadius(10);
        player.setFill(player_color);
        
        cop.setCenterX(140);
        cop.setCenterY(140);
        cop.setRadius(10);
        cop.setFill(cop_color);
        
        cit.setCenterX(40);
        cit.setCenterY(40);
        cit.setRadius(10);
        cit.setFill(cit_color);
        
        rootGroup.getChildren().addAll(player, cop,cit);
        moveCircleOnKeyPress(game, player,primaryStage);
	}

	private void checkCollisions(Circle a, Circle b) {
		if(a.getBoundsInParent().intersects(b.getBoundsInParent())) {
			if(a.getFill() == cop_color) {
				System.out.println("You're arrested");	
			}
			else {
				System.out.println("+1 Homies");	
			}
		}
			
	}
	
	private void moveCircleRandomly(final Circle cop) {
		int direc = 1;
		Random rand = new Random();
		direc = rand.nextInt(4)+1;	
		
		switch (direc) {
	    	case 1:
	    		if(!(cop.getCenterY() == 0))
	    			cop.setCenterY(cop.getCenterY() - KEYBOARD_MOVEMENT_DELTA); 
	    		break;
	    	case 2: 
	    		if(!(cop.getCenterX() == map_width))
	    			cop.setCenterX(cop.getCenterX() + KEYBOARD_MOVEMENT_DELTA); break;
	    	case 3: 
	    		if(!(cop.getCenterY() == map_height))
	    			cop.setCenterY(cop.getCenterY() + KEYBOARD_MOVEMENT_DELTA); break;
	    	case 4:  
	    		if(!(cop.getCenterX() == 0))
	    			cop.setCenterX(cop.getCenterX() - KEYBOARD_MOVEMENT_DELTA); break;
	    	default:
	    		break;
		}
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
    		SettingsWindow set = new SettingsWindow(scene, primaryStage);
    		primaryStage.getScene().setRoot(set.getRootGroup());
    		rootGroup.setEffect(null);
    		popupStage.hide();
    	});
	    instructions.setOnAction(e -> {
    		InstructionsWindow instruct = new InstructionsWindow(scene, primaryStage);
    		primaryStage.getScene().setRoot(instruct.getRootGroup());
    		rootGroup.setEffect(null);
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