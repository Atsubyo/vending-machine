package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			setScene(primaryStage);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setScene(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("./vendingUI/vendingDisplay.fxml"));
			Scene scene = new Scene(root,660,450);
			scene.getStylesheets().add(getClass().getResource("./vendingUI/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Vending Machine");
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("./images/vending_app_icon.png")));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
