package org.arthur.fractale.presentation.pane;

import org.arthur.fractale.application.Dessinable;
import org.arthur.fractale.application.manager.ZoneDessinManager;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class ZoneDessinPane extends BorderPane implements Dessinable {

	private GraphicsContext _gc;
	private Canvas _canvas;

	public ZoneDessinPane() {

		int borderLength = ZoneDessinManager.getInstance().getBorderLength();

		_canvas = new Canvas(borderLength, borderLength);
		_gc = _canvas.getGraphicsContext2D();

		setCenter(_canvas);

		resetZone();

	}

	@Override
	public void drawPointAt(int x, int y, Color color) {

		_gc.setFill(color);
		_gc.fillRect(x, y, 1, 1);

	}

	@Override
	public void resetZone() {

		_gc.setFill(Color.WHITE);
		_gc.fillRect(0, 0, _canvas.getWidth(), _canvas.getHeight());

	}

}
