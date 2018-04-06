package org.arthur.fractale;

import org.arthur.fractale.presentation.scene.MainScene;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.stage.Stage;

public class FractaleMain extends Application {

	public static void main(String[] args) {

		// lancement de l'ihm
		LauncherImpl.launchApplication(FractaleMain.class, args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// mise en place du titre de la fenêtre
		primaryStage.setTitle("Fractale 1.0");

		// remplissage de la fenetre
		MainScene sc = new MainScene();
		primaryStage.setScene(sc);
		// on prend toute la place
		primaryStage.setMaximized(true);

		setUserAgentStylesheet(STYLESHEET_MODENA);

		// ouverture de la fenetre
		primaryStage.show();

	}

}
