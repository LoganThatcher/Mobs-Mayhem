import javafx.scene.paint.ImagePattern;
	import javafx.scene.image.Image;
	import javafx.scene.Group;
	import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
	import javafx.concurrent.Task;
	import javafx.concurrent.WorkerStateEvent;
	import javafx.event.EventHandler;
	
public class StoryBoard1 {
	
		private final Group rootGroup;	
		boolean change = true;
	    
		public StoryBoard1(Scene game, Stage primaryStage){
			rootGroup = new Group();

			
			String url = "assets/sb1.png";
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
		            	StoryBoard2 sb2 = new StoryBoard2(game,primaryStage);
		        		primaryStage.getScene().setRoot(sb2.getRootGroup());
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
	    		
		    	StoryBoard2 sb2 = new StoryBoard2(game,primaryStage);
	    		primaryStage.getScene().setRoot(sb2.getRootGroup());
	    		change = false;
	    		
	    	});
		
		    
		    rootGroup.getChildren().addAll(skip,next);
		}

	    public Group getRootGroup() {
	    	return rootGroup;
	    }
	    
	    

}



