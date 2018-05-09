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

public class SettingsWindow {

	private final Group rootGroup;
	private final Text t, t2;
	boolean muted;

	public SettingsWindow(Scene set)
	{
		AudioClip click_player = new AudioClip(this.getClass().getResource("assets/click.mp3").toExternalForm());
		AudioClip click2_player = new AudioClip(this.getClass().getResource("assets/click2.mp3").toExternalForm());
		Font font = Font.loadFont(getClass().getResourceAsStream("assets/godfather.ttf"), 120);
		rootGroup = new Group();
		t = new Text();
		t2 = new Text();
		Button back = new Button("Back To Main Menu");
		Button mute;
		String url = "assets/blackscreen.png";
		Image img = new Image(url);
		ImagePattern pattern = new ImagePattern(img);
		set.setFill(pattern);
		Image unmute_image, mute_image;
		ImageView unmute_iv, mute_iv;
		
		unmute_image = new Image(getClass().getResourceAsStream("assets/unmute.png"));
    	unmute_iv = new ImageView(unmute_image);
    	unmute_iv.setFitHeight(15);
    	unmute_iv.setFitWidth(15);
    	
    	mute_image = new Image(getClass().getResourceAsStream("assets/mute.png"));
    	mute_iv = new ImageView(mute_image);
    	mute_iv.setFitHeight(15);
    	mute_iv.setFitWidth(15);
    	
    	mute = new Button("",unmute_iv);
    	
		t.setFont(font);
		t.setText("Mute:");
		t.setFill(Color.WHITE);
		t.setX(30.0);
		t.setY(300.0);
		t2.setFont(font);
		t2.setText("Settings");
		t2.setFill(Color.WHITE);
		t2.setX(320.0);
		t2.setY(100.0);
		
		mute.setLayoutX(240);
		mute.setLayoutY(210);
		mute.setPrefSize(200,100);
		back.setLayoutX(600);
		back.setLayoutY(500);
		back.setPrefSize(300, 100);
		back.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px");
		back.setOnMouseEntered(e -> {
        	back.setStyle("-fx-background-color: transparent; -fx-text-fill: #f8f8ff; -fx-font-size: 20px");
        	click_player.play();
        });
		back.setOnMouseExited(e -> back.setStyle("-fx-background-color: transparent; -fx-text-fill: #ff0000; -fx-font-size: 20px"));
		rootGroup.getChildren().addAll(back, t, mute, t2);
		
		back.setOnAction(e -> {
			click2_player.play();
			//go to StartWindow
		});  
			
		mute.setOnAction(e -> {
	    	if(muted) {
	    		StartWindow.mp.play();
	    		mute.setGraphic(unmute_iv);
	    		muted = false;
	    	}else {
	    		StartWindow.mp.pause();
	    		mute.setGraphic(mute_iv);
	    		muted = true;
	    	}
	    }); 

	}

	public Group getRootGroup() {
		return rootGroup;
	}  

}