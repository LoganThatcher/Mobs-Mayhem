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

public class SettingsWindow {

	private static final int map_height = 600;
	private static final int map_width = 900;
	private final Group rootGroup;
	private final Text t;

	public SettingsWindow(Scene set)
	{

		rootGroup = new Group();
		t = new Text();
		Button back = new Button("Back To Main Menu");
		String url = "https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/m8eSsjS/chroma-key-black-screen_hthm4fwfx_thumbnail-full01.png";
		Image img = new Image(url);
		ImagePattern pattern = new ImagePattern(img);
		set.setFill(pattern);
		t.setText("LOL you have no settings options");
		t.setFill(Color.WHITE);
		t.setX(30.0);
		t.setY(300.0);
		t.setFont(Font.font("Verdana", 50));
		back.setLayoutX(0);
		back.setLayoutY(0);
		rootGroup.getChildren().addAll(back, t);
		
		back.setOnAction(e -> {
			//go back to StartWindow
		});  

	}

	public Group getRootGroup() {
		return rootGroup;
	}  

}