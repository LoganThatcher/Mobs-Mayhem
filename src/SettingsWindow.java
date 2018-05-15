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

public class SettingsWindow {

	private final Group rootGroup;
	private final Text t, t2;
	boolean muted;

	public SettingsWindow(Scene set, Stage primaryStage)
	{
		//Upload music and fonts
		AudioClip click_player = new AudioClip(this.getClass().getResource("assets/click.mp3").toExternalForm());
		AudioClip click2_player = new AudioClip(this.getClass().getResource("assets/click2.mp3").toExternalForm());
		Font font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 120);
		
		//Declarations
		rootGroup = new Group();
		t = new Text();
		t2 = new Text();
		Button back = new Button("Back To Main Menu");
		Button minus, plus;
		String url = "assets/blackscreen.png";
		Image img = new Image(url);
		ImagePattern pattern = new ImagePattern(img);
		set.setFill(pattern);
    
    	minus = new Button("-");
    	plus = new Button("+");
    	
    	//Settings Title and Mute Title
		t2.setFont(font);
		t2.setText("Settings");
		t2.setFill(Color.WHITE);
		t2.setX(320.0);
		t2.setY(100.0);
		font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 90);
		t.setFont(font);
		t.setText("Volume:");
		t.setFill(Color.WHITE);
		t.setX(30.0);
		t.setY(300.0);
		
		//Button Positioning and Size
		minus.setLayoutX(225);
		minus.setLayoutY(260);
		minus.setPrefSize(25,25);
		plus.setLayoutX(260);
		plus.setLayoutY(260);
		plus.setPrefSize(25,25);
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
		rootGroup.getChildren().addAll(back, t, minus, t2, plus);
		
		//Button Actions
		back.setOnAction(e -> {
			click2_player.play();
			MainMenuWindow main = new MainMenuWindow(set, primaryStage);
			primaryStage.getScene().setRoot(main.getRootGroup());
		});  
			
		minus.setOnAction(e -> {
			if(StartWindow.mp.getVolume() > 0)
			{
	    		StartWindow.mp.setVolume((StartWindow.mp.getVolume()-0.1));
	    	}
	    }); 
		
		plus.setOnAction(e -> {
			if(StartWindow.mp.getVolume() < 1)
			{
	    		StartWindow.mp.setVolume((StartWindow.mp.getVolume()+0.1));
	    	}
	    }); 

	}

	public Group getRootGroup() {
		return rootGroup;
	}  

}