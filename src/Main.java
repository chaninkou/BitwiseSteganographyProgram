import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
/*   
Author: Chan In Kou
Purpose: The Display will show a GUI with different function.
*/
public class Main extends Application {
	protected File fileSelected = null;
	Scene scene1, grayScene, sepiaScene, negativeScene;
	protected String message;

	@Override
	public void start(Stage primaryStage) {
		try {
			// import image to display it
			uploadImage();

			// calling the subclass
			PPMImageSubclass ppm = new PPMImageSubclass(fileSelected);

			// making a borderpane
			BorderPane mainPane = new BorderPane();
			BorderPane subPane1 = new BorderPane();
			BorderPane subPane2 = new BorderPane();
			BorderPane subPane3 = new BorderPane();

			// adding the button
			Button grayscaleButton = new Button("Grayscale");

			// set on action
			grayscaleButton.setOnAction(e -> {
				primaryStage.setScene(grayScene);
				ppm.grayscale();
			});

			// adding the button
			Button sepiaButton = new Button("Sepia");

			// set on action
			sepiaButton.setOnAction(e -> {
				primaryStage.setScene(sepiaScene);
				ppm.sepia();
			});

			// adding the button
			Button negativeButton = new Button("Negative");
			
			negativeButton.setOnAction(e -> {
				primaryStage.setScene(negativeScene);
				ppm.negative();
			});
			
			Button recover = new Button("Hidden message");
			
			recover.setOnAction(e -> {
					uploadImage();
					PPMImageSubclass ppm2 = new PPMImageSubclass(fileSelected);
					System.out.println(ppm2.recoverMessage());
			});

			// adding home button
			Button homeButton1 = new Button("home button");
			homeButton1.setOnAction(e -> primaryStage.setScene(scene1));

			// adding home button
			Button homeButton2 = new Button("home button");
			homeButton2.setOnAction(e -> primaryStage.setScene(scene1));

			// adding home button
			Button homeButton3 = new Button("home button");
			homeButton3.setOnAction(e -> primaryStage.setScene(scene1));

			// making a hbox
			HBox boxForHome1 = new HBox(10);
			boxForHome1.getChildren().addAll(homeButton1);
			boxForHome1.setAlignment(Pos.CENTER);

			// making a hbox
			HBox boxForHome2 = new HBox(10);
			boxForHome2.getChildren().addAll(homeButton2);
			boxForHome2.setAlignment(Pos.CENTER);

			// making a hbox
			HBox boxForHome3 = new HBox(10);
			boxForHome3.getChildren().addAll(homeButton3);
			boxForHome3.setAlignment(Pos.CENTER);
			
			Label question = new Label("Enter your hidden message");
			
			TextField textbox = new TextField();
			textbox.setPrefColumnCount(5); 
			textbox.setAlignment(Pos.BASELINE_LEFT); 
			textbox.setOnAction(e -> ppm.hideMessage(textbox.getText()));
			
			// making a hbox
			HBox boxForKey = new HBox(10);
			boxForKey.getChildren().addAll(question, textbox, recover, grayscaleButton, sepiaButton, negativeButton);
			boxForKey.setAlignment(Pos.CENTER);
			mainPane.setBottom(boxForKey);

			// setting the box to the bottom of the pane
			subPane1.setBottom(boxForHome1);
			subPane2.setBottom(boxForHome2);
			subPane3.setBottom(boxForHome3);

			// creating a pane
			StackPane root = new StackPane();
			root.getChildren().add(mainPane);

			// creating second pane
			StackPane root2 = new StackPane();
			root2.getChildren().add(subPane1);

			// creating third pane
			StackPane root3 = new StackPane();
			root3.getChildren().add(subPane2);

			// creating fourth pane
			StackPane root4 = new StackPane();
			root4.getChildren().add(subPane3);

			// use two methods to convert a PPM image into something that JavaFX
			// can display
			Image image = SwingFXUtils.toFXImage(ImageIO.read(fileSelected), null);

			// creating a image view
			ImageView view = new ImageView(image);
			

			// making it look nice
			view.setPreserveRatio(true);
			view.setFitWidth(300);

			// add view to root
			root.getChildren().add(view);

			// setting the scene 1
			scene1 = new Scene(root, 800, 800);

			// setting the grayscene
			grayScene = new Scene(root2, 800, 800);

			// setting the Sepiascene
			sepiaScene = new Scene(root3, 800, 800);

			// setting Negativescene
			negativeScene = new Scene(root4, 800, 800);

			// setting the title
			primaryStage.setTitle("HW:6 Bitwise Operators");

			// setting the stage
			primaryStage.setScene(scene1);

			// Display
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// using import
	public File uploadImage() {
		JFileChooser chooseFile = new JFileChooser();
		int retVal = chooseFile.showOpenDialog(null);
		if (retVal == JFileChooser.APPROVE_OPTION) {
			fileSelected = chooseFile.getSelectedFile();
			return fileSelected;
		}
		return fileSelected;
	}

	public static void main(String[] args) {
		launch(args);
	}
}