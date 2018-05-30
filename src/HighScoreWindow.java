import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HighScoreWindow {

	private final Group rootGroup;
	private final Text t, t2, t3, t4, t5, t6;
	boolean muted;

	public HighScoreWindow(Scene hs, Stage primaryStage)
	{
		//Upload music and fonts
		AudioClip click_player = new AudioClip(this.getClass().getResource("assets/click.mp3").toExternalForm());
		AudioClip click2_player = new AudioClip(this.getClass().getResource("assets/click2.mp3").toExternalForm());
		Font font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 120);
		
		//Declarations
		rootGroup = new Group();
		t = new Text();
		t2 = new Text();
		t3 = new Text();
		t4 = new Text();
		t5 = new Text();
		t6 = new Text();
		Button back = new Button("Back To Main Menu");
		String url = "assets/blackscreen.png";
		Image img = new Image(url);
		ImagePattern pattern = new ImagePattern(img);
		hs.setFill(pattern);

    	//Settings Title and Mute Title
		t2.setFont(font);
		t2.setText("High Scores");
		t2.setFill(Color.WHITE);
		t2.setX(300.0);
		t2.setY(100.0);
		font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 40);
		t.setFont(font);
		t.setText("Jason        300");
		t.setFill(Color.WHITE);
		t.setX(375.0);
		t.setY(200.0);
		t3.setFont(font);
		t3.setText("Danica       280");
		t3.setFill(Color.WHITE);
		t3.setX(375.0);
		t3.setY(245.0);
		t4.setFont(font);
		t4.setText("Anshula      270");
		t4.setFill(Color.WHITE);
		t4.setX(375.0);
		t4.setY(290.0);
		t5.setFont(font);
		t5.setText("Logan        250");
		t5.setFill(Color.WHITE);
		t5.setX(375.0);
		t5.setY(335.0);
		t6.setFont(font);
		t6.setText("Lindsey      200");
		t6.setFill(Color.WHITE);
		t6.setX(375.0);
		t6.setY(380.0);
		
		//Button Positioning and Size
		back.setLayoutX(600);
		back.setLayoutY(500);
		back.setPrefSize(300, 100);
		
		//Mouse Actions for Back Button
		back.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
		back.setOnMouseEntered(e -> {
        	back.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px");
        	click_player.play();
        });
		back.setOnMouseExited(e -> back.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
		rootGroup.getChildren().addAll(back, t, t2, t3, t4, t5, t6);
		
		//Button Actions
		back.setOnAction(e -> {
			click2_player.play();
			MainMenuWindow main = new MainMenuWindow(hs, primaryStage);
			primaryStage.getScene().setRoot(main.getRootGroup());
		});  
	}

	public Group getRootGroup() {
		return rootGroup;
	}  

}
