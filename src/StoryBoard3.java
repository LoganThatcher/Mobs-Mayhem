import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
	
public class StoryBoard3 {
	
		private final Group rootGroup;	

	    
		public StoryBoard3(Scene game, Stage primaryStage){
			rootGroup = new Group();

			
			String url = "assets/sb3.png";
			Image img = new Image(url);
			
			
			ImagePattern pattern = new ImagePattern(img);
			game.setFill(pattern);
			
			Task<Void> sleeper = new Task<Void>() {
	            @Override
	            protected Void call() throws Exception {
	                try {
	                    Thread.sleep(500);
	                } catch (InterruptedException e) {
	                }
	                return null;
	            }
	        };
	        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
	            @Override
	            public void handle(WorkerStateEvent event) {
	            	StoryBoard4 sb4 = new StoryBoard4(game,primaryStage);
	        		primaryStage.getScene().setRoot(sb4.getRootGroup());
	            }
	        });
	        new Thread(sleeper).start();
			
			
			
		
		}

	    public Group getRootGroup() {
	    	return rootGroup;
	    }
	    
	    

}