import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
	
public class StoryBoard2 {
	
	private final Group rootGroup;	
	boolean change = true; 
	
	public StoryBoard2(Scene game, Stage primaryStage){
		rootGroup = new Group();
	
		
		String url = "assets/sb2.png";
		Image img = new Image(url);
		Button skip = new Button("SKIP");
		Button next = new Button("NEXT");
		
		skip.setLayoutX(845);
		skip.setLayoutY(560);
		next.setLayoutX(785);
		next.setLayoutY(560);
		
		ImagePattern pattern = new ImagePattern(img);
		game.setFill(pattern);
		
		Task<Void> sleeper = new Task<Void>() {
	        @Override
	        protected Void call() throws Exception {
	            try {
	                Thread.sleep(5000);
	            } catch (InterruptedException e) {
	            }
	            return null;
	        }
	    };
	    sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
	        @Override
	        public void handle(WorkerStateEvent event) {
	        		if(change) {
		            	StoryBoard3 sb3 = new StoryBoard3(game,primaryStage);
		        		primaryStage.getScene().setRoot(sb3.getRootGroup());
	        		}
	            }
	    });
	    new Thread(sleeper).start();
	    
	    skip.setOnAction(e -> {
    		
	    	LoadWindow load = new LoadWindow(game,primaryStage);
    		primaryStage.getScene().setRoot(load.getRootGroup());
    		change = false;
    		
    	});
	    next.setOnAction(e -> {
    		
	    	StoryBoard3 sb3 = new StoryBoard3(game,primaryStage);
    		primaryStage.getScene().setRoot(sb3.getRootGroup());
    		change = false;
    	});
	    
		
		rootGroup.getChildren().addAll(skip,next);
		
	
	}
	
	public Group getRootGroup() {
		return rootGroup;
	}
	
	

}

