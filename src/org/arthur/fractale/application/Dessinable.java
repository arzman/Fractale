package org.arthur.fractale.application;

import javafx.scene.paint.Color;

public interface Dessinable {

	/**
	 * dessine un point de couleur à l'endroit indiqué
	 * 
	 * @param x
	 * @param y
	 * @param color
	 */
	public void drawPointAt(int x, int y, Color color);

	/**
	 * RaZ de la zone de dessin
	 */
	public void resetZone();


}
