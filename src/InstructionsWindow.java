import javafx.scene.layout.BackgroundImage;
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

public class InstructionsWindow {

	private static final int map_height = 600;
	private static final int map_width = 900;
	private final Group rootGroup;
	private final Text t;
	
	public InstructionsWindow(Scene instructions) {
		rootGroup = new Group();
		t = new Text();
		Button back = new Button("Back To Main Menu");
		String url = "assets/blackscreen.png";
		Image img = new Image(url);
		ImagePattern pattern = new ImagePattern(img);
		instructions.setFill(pattern);
		
	    t.setFont(Font.font("Verdana", 50));
		t.setText("Instructions coming soooooon!");
		t.setFill(Color.WHITE);
		t.setX(30.0);
		t.setY(300.0);
		back.setLayoutX(600);
		back.setLayoutY(500);
		back.setPrefSize(300, 100);
		back.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
		back.setOnMouseEntered(e -> back.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px"));
		back.setOnMouseExited(e -> back.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
		rootGroup.getChildren().addAll(back, t);
		
		back.setOnAction(e -> {
			//go to StartWindow
		}); 
	}
	
	public Group getRootGroup() {
		return rootGroup;
	}
	
}







