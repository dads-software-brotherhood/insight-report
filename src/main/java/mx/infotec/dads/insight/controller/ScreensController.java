package mx.infotec.dads.insight.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ScreensController extends StackPane {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScreensController.class);

    // Holds the screens to be displayed
    private HashMap<String, Node> screens = new HashMap<>();

    public ScreensController() {
	super();
    }

    // Add the screen to the collection
    public void addScreen(String name, Node screen) {
	screens.put(name, screen);
    }

    // Returns the Node with the appropriate name
    public Node getScreen(String name) {
	return screens.get(name);
    }

    public boolean loadScreen(String name, String resource) {
	try {
	    FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
	    Parent loadScreen = (Parent) myLoader.load();
	    ControlledScreen myScreenControler = (ControlledScreen) myLoader.getController();
	    myScreenControler.setScreenParent(this);
	    addScreen(name, loadScreen);
	    return true;
	} catch (Exception e) {
	    LOGGER.info("loadScreen", e);
	    return false;
	}
    }

    public boolean setScreen(final String name) {
	if (screens.get(name) != null) { // screen loaded
	    final DoubleProperty opacity = opacityProperty();

	    if (!getChildren().isEmpty()) { // if there is more than one screen
		Timeline fade = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
			new KeyFrame(new Duration(500), (ActionEvent t) -> {
			    getChildren().remove(0); // remove the displayed //
						     // screen
			    getChildren().add(0, screens.get(name)); // add the
								     // screen
			    Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
				    new KeyFrame(new Duration(500), new KeyValue(opacity, 1.0)));
			    fadeIn.play();
			} , new KeyValue(opacity, 0.0)));
		fade.play();

	    } else {
		setOpacity(0.0);
		getChildren().add(screens.get(name)); // no one else been
						      // displayed, then just
						      // show
		Timeline fadeIn = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
			new KeyFrame(new Duration(500), new KeyValue(opacity, 1.0)));
		fadeIn.play();
	    }
	    return true;
	} else {
	    LOGGER.info("No se logró cargar la pantalla\n");
	    return false;
	}
    }

    public boolean unloadScreen(String name) {
	if (screens.remove(name) == null) {
	    LOGGER.info("La pantalla no existe");
	    return false;
	} else {
	    return true;
	}
    }
}
