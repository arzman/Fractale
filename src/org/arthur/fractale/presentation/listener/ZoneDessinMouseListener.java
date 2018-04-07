package org.arthur.fractale.presentation.listener;

import org.arthur.fractale.presentation.pane.GeneralConfigPane;
import org.arthur.fractale.presentation.pane.ZoneDessinPane;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Ecoute la souris sur la zone de dessin.
 * 
 * Permet d'ajouter un zoom
 * 
 * @author Arthur
 *
 */
public class ZoneDessinMouseListener {

	/* La zone de dessin */
	private ZoneDessinPane _zoneDessin;
	/* Le panneau de config */
	private GeneralConfigPane _genConfigPane;

	/* variable de capture de la zoom de zoom */
	private int debX;
	private int debY;
	private int finX;
	private int finY;
	private boolean dragged = false;

	public ZoneDessinMouseListener(ZoneDessinPane dessin, GeneralConfigPane config) {

		_zoneDessin = dessin;
		_genConfigPane = config;

		hooklistener();

	}

	private void hooklistener() {

		_zoneDessin.getCanva().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				dragged = false;

				debX = (int) (event.getSceneX() - _zoneDessin.getCanva().getLayoutX()
						- _zoneDessin.getCanva().getTranslateX());
				debY = (int) (event.getSceneY() - _zoneDessin.getCanva().getLayoutY()
						- _zoneDessin.getCanva().getTranslateY());

			}
		});

		_zoneDessin.getCanva().addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				dragged = true;
			}
		});

		_zoneDessin.getCanva().addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				finX = (int) (event.getSceneX() - _zoneDessin.getCanva().getLayoutX()
						- _zoneDessin.getCanva().getTranslateX());
				finY = (int) (event.getSceneY() - _zoneDessin.getCanva().getLayoutY()
						- _zoneDessin.getCanva().getTranslateY());

				if (dragged) {

					int xC = debX + (finX - debX) / 2;
					int yC = debY + (finY - debY) / 2;

					int amp = (int) ((Math.abs(finX - debX) + Math.abs(finY - debY)) / 2.0);

					_zoneDessin.drawRectAt(xC, yC, amp);
					_genConfigPane.adjustCadre(xC, yC, amp);

				}

			}
		});

	}

}
