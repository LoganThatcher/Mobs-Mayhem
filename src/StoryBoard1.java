import javafx.scene.paint.ImagePattern;
	import javafx.scene.image.Image;
	import javafx.scene.Group;
	import javafx.scene.Scene;
	import javafx.stage.Stage;
	import javafx.concurrent.Task;
	import javafx.concurrent.WorkerStateEvent;
	import javafx.event.EventHandler;
	
public class StoryBoard1 {
	
		private final Group rootGroup;	

	    
		public StoryBoard1(Scene game, Stage primaryStage){
			rootGroup = new Group();

			
			String url = "assets/sb1.png";
			Image img = new Image(url);
			
			
			ImagePattern pattern = new ImagePattern(img);
			game.setFill(pattern);
			
			Task<Void> sleeper = new Task<Void>() {
	            @Override
	            protected Void call() throws Exception {
	                try {
	                    Thread.sleep(3000);
	                } catch (InterruptedException e) {
	                }
	                return null;
	            }
	        };
	        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
	            @Override
	            public void handle(WorkerStateEvent event) {
	            	StoryBoard2 sb2 = new StoryBoard2(game,primaryStage);
	        		primaryStage.getScene().setRoot(sb2.getRootGroup());
	            }
	        });
	        new Thread(sleeper).start();
			
			
			
		
		}

	    public Group getRootGroup() {
	    	return rootGroup;
	    }
	    
	    

}



