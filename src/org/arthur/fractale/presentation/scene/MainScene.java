package org.arthur.fractale.presentation.scene;

import org.arthur.fractale.application.manager.ZoneDessinManager;
import org.arthur.fractale.presentation.listener.ZoneDessinMouseListener;
import org.arthur.fractale.presentation.pane.GeneralConfigPane;
import org.arthur.fractale.presentation.pane.ZoneDessinPane;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class MainScene extends Scene {

	public MainScene() {
		// appel du constructeur parent
		super(new BorderPane());

		GeneralConfigPane configPane = new GeneralConfigPane();
		ZoneDessinPane zoneDessin = new ZoneDessinPane();

		ZoneDessinManager.getInstance().bindZoneDessin(zoneDessin);

		((BorderPane) getRoot()).setRight(configPane);
		((BorderPane) getRoot()).setCenter(zoneDessin);

		ZoneDessinMouseListener zdml = new ZoneDessinMouseListener(zoneDessin, configPane);

	}

}
