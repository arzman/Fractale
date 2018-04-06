package org.arthur.fractale.application.manager;

import org.arthur.fractale.application.Dessinable;
import org.arthur.fractale.presentation.pane.ZoneDessinPane;

import javafx.scene.paint.Color;

/**
 * Gestionnaire de la zone de dessin.
 * 
 * @author ARDUFLOT
 *
 */
public class ZoneDessinManager {

	/* L'instance du singleton */
	private static ZoneDessinManager _instance;

	/* La longueur en px des cotes du carré de dessin */
	private int _borderLength;

	/* La zone de dessin */
	private Dessinable _zoneDessin;

	/* palette de couleur */
	Color[] _palette;

	/**
	 * Constructeur
	 */
	private ZoneDessinManager() {

		_borderLength = 800;

		_palette = new Color[256];

		for (int i = 0; i < _palette.length; i++) {
			_palette[i] = new Color(i / 255.0, 0.1, 0.7, 1.0);
		}

	}

	/**
	 * Retourne l'unique instance du singleton
	 * 
	 * @return le singleton
	 */
	public static ZoneDessinManager getInstance() {

		if (_instance == null) {
			_instance = new ZoneDessinManager();
		}

		return _instance;
	}

	public void bindZoneDessin(ZoneDessinPane dessinZone) {
		_zoneDessin = dessinZone;

	}

	public void drawAt(int x, int y, byte colorbyte) {

		_zoneDessin.drawPointAt(x, y, _palette[colorbyte + 128]);
	}

	public void render(byte[][] res) {

		for (int i = 0; i < _borderLength; i++) {
			for (int j = 0; j < _borderLength; j++) {

				drawAt(i, j, res[i][j]);

			}
		}

	}

	public int getBorderLength() {

		return _borderLength;
	}

	public void setBorderLength(int length) {

		_borderLength = length;

	}

}
