package mx.infotec.dads.insight.controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreensFramework extends Application {

    public static final String SCREEN1ID = "main";
    public static final String SCREEN1FILE = "Screen1.fxml";
    public static final String SCREEN2ID = "screen2";
    public static final String SCREEN2FILE = "Screen2.fxml";
    public static final String SCREEN3ID = "screen3";
    public static final String SCREEN3FILE = "Screen3.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
	ScreensController mainContainer = new ScreensController();
	mainContainer.loadScreen(ScreensFramework.SCREEN1ID, ScreensFramework.SCREEN1FILE);
	mainContainer.loadScreen(ScreensFramework.SCREEN2ID, ScreensFramework.SCREEN2FILE);
	mainContainer.loadScreen(ScreensFramework.SCREEN3ID, ScreensFramework.SCREEN3FILE);

	mainContainer.setScreen(ScreensFramework.SCREEN1ID);

	Group root = new Group();
	root.getChildren().addAll(mainContainer);
	Scene scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.show();

    }

}
