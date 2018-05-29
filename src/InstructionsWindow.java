import javafx.scene.layout.BackgroundImage;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.event.EventHandler;
import javafx.scene.input.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InstructionsWindow {

	private static final int map_height = 600;
	private static final int map_width = 900;
	private final Group rootGroup;
	private final Text t;
	
	public InstructionsWindow(Scene instruct, Stage primaryStage)
	{
		// Upload music and fonts
		Font font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 100);	
		AudioClip click_player = new AudioClip(this.getClass().getResource("assets/click.mp3").toExternalForm());
		AudioClip click2_player = new AudioClip(this.getClass().getResource("assets/click2.mp3").toExternalForm());
		
		// Declarations
		rootGroup = new Group();
		t = new Text();
		Button back = new Button("Back To Main Menu");
		String url = "assets/blackscreen.png";
		Image img = new Image(url);
		ImagePattern pattern = new ImagePattern(img);
		instruct.setFill(pattern);
		
		// Instructions Image
		String instructions = "assets/instructions.png";
		Image im_instructions = new Image(instructions);
		ImageView imageView = new ImageView(im_instructions);
		imageView.setX(115);
		imageView.setY(125);
		imageView.setFitHeight(400);
		imageView.setPreserveRatio(true);
		
		// Setting Title
	    t.setFont(font);
		t.setText("Instructions");
		t.setFill(Color.WHITE);
		t.setX(320.0);
		t.setY(100.0);
		
		// Back Button
		back.setLayoutX(600);
		back.setLayoutY(500);
		back.setPrefSize(300, 100);
		back.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
		back.setOnMouseEntered(e -> {
        	back.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px");
        	click_player.play();
        });
		back.setOnMouseExited(e -> back.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
		rootGroup.getChildren().addAll(back, t, imageView);
		
		back.setOnAction(e -> {
			click2_player.play();
			MainMenuWindow main = new MainMenuWindow(instruct, primaryStage);
			primaryStage.getScene().setRoot(main.getRootGroup());
		}); 
	}
	
	public Group getRootGroup() {
		return rootGroup;
	}
	
}





