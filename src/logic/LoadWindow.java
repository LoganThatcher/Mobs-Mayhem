package logic;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LoadWindow
{
	private final Group rootGroup;	
	int i = 0;
	double end_line = 25;
    
	public LoadWindow(Scene game, Stage primaryStage){
				
		String url = "logic/assets/blackscreen.png";
		Image img = new Image(url);
		ImagePattern pattern = new ImagePattern(img);
		Font font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 150);		
		Text t, progress, tip;
		Line l;
		
		rootGroup = new Group();
		
		l = new Line(25,535, end_line, 535);
		l.setStroke(Color.RED);
		l.setStrokeWidth(5);
		
		
		
		t = new Text(30,565,"Loading");
    	t.setFont(font);
    	t.setFill(Color.WHITE);
    	
    	progress = new Text(300,565, "0");
    	progress.setFont(font);
    	progress.setFill(Color.WHITE);
    	progress.setStyle("-fx-font-size: 75");
    	
    	tip = new Text(275,275,"Tip: Avoid police to avoid going to jail");
    	tip.setFont(font);
    	tip.setFill(Color.WHITE);
    	tip.setStyle("-fx-font-size: 50");
    
		game.setFill(pattern);
		
		
		
		
		
		Timeline loading = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		       progress.setText(Integer.toString(i));
		       l.setEndX(end_line);
		       i++;
		       end_line += 2.65;
		    }
		}));
		loading.setCycleCount(101);
		loading.play();
		
		
		
		
		
		Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                	//Sleep for 100 * timeline length
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
            	GameWindow game_screen = new GameWindow(game,primaryStage);
        		primaryStage.getScene().setRoot(game_screen.getRootGroup());
            }
        });
        new Thread(sleeper).start();
        
        
        rootGroup.getChildren().addAll(t,progress,tip,l);
		
		
	
	}

    public Group getRootGroup() {
    	return rootGroup;
    }
    
    

}

