package org.arthur.fractale.presentation.scene;

import org.arthur.fractale.application.manager.ZoneDessinManager;
import org.arthur.fractale.presentation.pane.GeneralConfigPane;
import org.arthur.fractale.presentation.pane.ZoneDessinPane;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainScene extends Scene {

	public MainScene() {
		// appel du constructeur parent
		super(new BorderPane());

		GeneralConfigPane configPane = new GeneralConfigPane();
		ZoneDessinPane dessinZone = new ZoneDessinPane();

		ZoneDessinManager.getInstance().bindZoneDessin(dessinZone);

		((BorderPane) getRoot()).setRight(configPane);
		((BorderPane) getRoot()).setCenter(dessinZone);

	}

}
