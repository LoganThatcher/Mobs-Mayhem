package logic;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.ArrayList;
import java.util.Iterator;
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
	private static final int KEYBOARD_MOVEMENT_DELTA = 20;
	private static final int map_height = 600;
	private static final int map_width = 900;

	
	// Setting image patterns to load images on top of the circles
    private static final Image imCop = new Image("logic/assets/cophead.png");
    private static final ImagePattern ip_cop = new ImagePattern(imCop);        
    private static final Image imCit = new Image("logic/assets/civilianhead.png");
    private static final ImagePattern ip_cit = new ImagePattern(imCit);
	
	ArrayList<Circle> moneys = new ArrayList<Circle>();
	ArrayList<Circle> cops = new ArrayList<Circle>();
	Iterator<Circle> iter = moneys.iterator();
	private final Group rootGroup;	
	private int lives;
	private int score;
	AudioClip click_player;
	Text life_counter;
	Text score_counter;
	
	Random ran = new Random();

	public GameWindow(Scene game, Stage primaryStage){
		String url = "logic/assets/cobblestone.jpg";
		Image img = new Image(url);
		ImagePattern pattern = new ImagePattern(img);
		Timeline circMover;
		
		
		Circle cop = new Circle();
		Circle cop2 = new Circle();
		Circle cop3 = new Circle();
		Circle cop4 = new Circle();
		cops.add(cop);
		cops.add(cop2);
		cops.add(cop3);
		cops.add(cop4);
		Circle cit = new Circle();
		Circle player = new Circle();
		
		//mobSize = 10;
		lives = 3;
		life_counter = new Text(10,20,"Lives: " + lives);
		life_counter.setFill(Color.RED);
		score = 0;
		score_counter = new Text(830,20,"Score: " + score);
		score_counter.setFill(Color.RED);
		
		game.setFill(pattern);
		rootGroup = new Group();

	    //Background
	    GridPane grid = new GridPane();
	    grid.setAlignment(Pos.TOP_LEFT);
	    grid.setHgap(25);
	    grid.setVgap(25);
	    grid.setPadding(new Insets(25, 25, 25, 25));
	     
	    String roofurl = "logic/assets/halloween-house.png";
	    Image roofimg = new Image(roofurl);
	    ImageView roof;
	      
	    for (int i = 0; i < 5; i++) {
	       for (int j = 0; j < 8; j++) {
	          roof = new ImageView(roofimg);
	          roof.setFitHeight(90);
	          roof.setFitWidth(85);
	          grid.add(roof, j, i);
	       }
	    }
	      
	    rootGroup.getChildren().add(grid);
			
		circMover = new Timeline(new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	
		    	for(Circle c: cops) {
		    		moveCircleTowardPlayer(c,player);
		    		checkCollisions(c, player, game, primaryStage);
		    	}
		        moveCircleRandomly(cit);
		        checkCollisions(cit, player, game, primaryStage);
		        
		        if(!moneys.isEmpty()) {
		        	Circle curr_money = moneys.get(0);
		        	checkCollisions(curr_money, cop, game, primaryStage);
		        }
		    }
		}));
		circMover.setCycleCount(Timeline.INDEFINITE);
		circMover.play();	
        
        
        setCharacterPlacement(cops,cit,player);
        rootGroup.getChildren().addAll(player, cop, cit, life_counter, score_counter);
        moveCircleOnKeyPress(game, player,primaryStage,circMover);
	}
	
	// Character placements and settings
	private void setCharacterPlacement(ArrayList<Circle> cops, Circle cit, Circle player) {
		Random rand = new Random();
		int pos = rand.nextInt(map_width);
        player.setCenterX(map_width/2);
        player.setCenterY(map_height/2);
        player.setRadius(15);
        Image imPlayer = new Image("logic/assets/mobbosshead.png");
        player.setFill(new ImagePattern(imPlayer));
        
		for(Circle c : cops) {
			c.setCenterX(140);
	        c.setCenterY(140);
	        c.setRadius(0);
	        c.setFill(ip_cop);
		}
		cops.get(0).setRadius(10);
		cit.setCenterX(pos);
        pos = rand.nextInt(map_height);
        cit.setCenterY(pos);
        cit.setRadius(10);
        cit.setFill(ip_cit);
	
	}

	private void checkCollisions(Circle a, Circle b, Scene game, Stage primaryStage) {
		if(a.getBoundsInParent().intersects(b.getBoundsInParent())) {
			if (a.getFill() == ip_cop) {
				if(lives == 1) {
					GameOverWindow go = new GameOverWindow(game,primaryStage);
		    		primaryStage.getScene().setRoot(go.getRootGroup());
				}
				lives -= 1;
				life_counter.setText("Lives: " + lives);
				a.setCenterX(140);
				a.setCenterY(140);
				b.setRadius(15);
				b.setCenterX(map_width/2);
		        b.setCenterY(map_height/2);
			}
			else if(a.getFill() == Color.GREEN) {
				a.setFill(Color.TRANSPARENT);
				moneys.remove(a);
			}
			else{
				a.setRadius(0);
				rootGroup.getChildren().remove(a);
				score += 10;
				if(score == 20) {
					cops.get(1).setRadius(20);
					rootGroup.getChildren().addAll(cops.get(1));
				}
				score_counter.setText("Score: " + score);
				Random rand = new Random();
				int pos = rand.nextInt(map_width);
				a.setCenterX(pos);
		        pos = rand.nextInt(map_height);
		        a.setCenterY(pos);
		        a.setRadius(10);
		        a.setFill(ip_cit);
		        rootGroup.getChildren().add(a);
			}
		}
			
	}
	
	
	
	private void moveCircleRandomly(final Circle cop) {
		int direc = 1;
		Random rand = new Random();
		direc = rand.nextInt(4)+1;	
		
		switch (direc) {
	    	case 1:
	    		if(!(cop.getCenterY() - KEYBOARD_MOVEMENT_DELTA <= 0))
	    			cop.setCenterY(cop.getCenterY() - KEYBOARD_MOVEMENT_DELTA); break;
	    	case 2: 
	    		if(!(cop.getCenterX() + KEYBOARD_MOVEMENT_DELTA >= map_width))
	    			cop.setCenterX(cop.getCenterX() + KEYBOARD_MOVEMENT_DELTA); break;
	    	case 3: 
	    		if(!(cop.getCenterY() + KEYBOARD_MOVEMENT_DELTA >= map_height))
	    			cop.setCenterY(cop.getCenterY() + KEYBOARD_MOVEMENT_DELTA); break;
	    	case 4:  
	    		if(!(cop.getCenterX() - KEYBOARD_MOVEMENT_DELTA <= 0))
	    			cop.setCenterX(cop.getCenterX() - KEYBOARD_MOVEMENT_DELTA); break;
	    	default:
	    		break;
		}
	}

	private void moveCircleTowardPlayer(final Circle cop,final Circle player) {
		double player_x = player.getCenterX();
		double player_y = player.getCenterY();
		double cop_x = cop.getCenterX();
		double cop_y = cop.getCenterY();
		
		if(!moneys.isEmpty()){
			player_x = moneys.get(0).getCenterX();
			player_y = moneys.get(0).getCenterY();
		}
		
		if(Math.abs(player_x - cop_x) > Math.abs(player_y - cop_y)){
			//Move horizontally
			if(player_x > cop_x) {
				cop.setCenterX(cop.getCenterX() + KEYBOARD_MOVEMENT_DELTA);
			}else {
				cop.setCenterX(cop.getCenterX() - KEYBOARD_MOVEMENT_DELTA);
			}
		}
		else {
			//Move vertically
			if(player_y > cop_y) {
				cop.setCenterY(cop.getCenterY() + KEYBOARD_MOVEMENT_DELTA);
			}else {
				cop.setCenterY(cop.getCenterY() - KEYBOARD_MOVEMENT_DELTA);
			}
		}
	}
    
    private void moveCircleOnKeyPress(Scene scene, final Circle circle, Stage primaryStage, Timeline circMover) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
          @Override public void handle(KeyEvent event) {
            switch (event.getCode()) {
            	case UP:
            		if(!(circle.getCenterY() - KEYBOARD_MOVEMENT_DELTA <= 0))
            			circle.setCenterY(circle.getCenterY() - KEYBOARD_MOVEMENT_DELTA); break;
            	case RIGHT: 
            		if(!(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA >= map_width))
            			circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA); break;
            	case DOWN: 
            		if(!(circle.getCenterY() + KEYBOARD_MOVEMENT_DELTA >= map_height))
            			circle.setCenterY(circle.getCenterY() + KEYBOARD_MOVEMENT_DELTA); break;
            	case LEFT:  
            		if(!(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA <= 0))
            			circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA); break;
            	case ESCAPE: 
            		pause(primaryStage, scene,circMover);
            	case SPACE: 
            		if(score >= 10) {
            			score = score - 10;
            			score_counter.setText("Score: " + Integer.toString(score));
	            		Circle money = new Circle();
	            		money.setCenterY(circle.getCenterY());
	            	    money.setCenterX(circle.getCenterX());
	            	    money.setRadius(5);
	            	    money.setFill(Color.GREEN);
	            	    rootGroup.getChildren().add(money);
	            	    moneys.add(money);
            		}
            	    break;
            	default:
            		break;
          
            }
          }
        });
      }

    
      private void pause(Stage primaryStage, Scene scene, Timeline circMover){
    	circMover.pause();
    
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
	    //Button settings = new Button("SETTINGS");
	    //Button instructions = new Button("INSTRUCTIONS");
	    Button quit = new Button("QUIT");
	    pauseRoot.getChildren().addAll(resume,restart,quit);

	    
	    Stage popupStage = new Stage(StageStyle.TRANSPARENT);
	    popupStage.initOwner(primaryStage);
	    popupStage.initModality(Modality.APPLICATION_MODAL);
	    popupStage.setScene(new Scene(pauseRoot, Color.BLACK));
	    
	    
	    resume.setOnAction(e ->{
	    	rootGroup.setEffect(null);
	      	popupStage.hide();
	      	circMover.play();
	    });
	    /*settings.setOnAction(e -> {
    		PauseSettings pset = new PauseSettings(scene, primaryStage);
    		primaryStage.getScene().setRoot(pset.getRootGroup());
    		popupStage.hide();
    	});
	    instructions.setOnAction(e -> {
    		InstructionsWindow instruct = new InstructionsWindow(scene, primaryStage);
    		primaryStage.getScene().setRoot(instruct.getRootGroup());
    		popupStage.hide();
    	});*/
	    quit.setOnAction(e -> {
			MainMenuWindow main = new MainMenuWindow(scene, primaryStage);
			primaryStage.getScene().setRoot(main.getRootGroup());
			popupStage.hide();
		});  
	    restart.setOnAction(e -> {
			LoadWindow load = new LoadWindow(scene, primaryStage);
			primaryStage.getScene().setRoot(load.getRootGroup());
			popupStage.hide();
		});  
	    pauseRoot.setOnKeyPressed(new EventHandler<KeyEvent>() {
	          @Override public void handle(KeyEvent event) {
	            switch (event.getCode()) {
	            	case ESCAPE: 
	                 	click_player.play();
	                 	circMover.play();
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