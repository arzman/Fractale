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

		_canvas = new Canvas();
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

		int borderLength = ZoneDessinManager.getInstance().getBorderLength();

		_canvas.setHeight(borderLength);
		_canvas.setWidth(borderLength);

		_gc.setFill(Color.WHITE);
		_gc.fillRect(0, 0, _canvas.getWidth(), _canvas.getHeight());

	}

	public Canvas getCanva() {
		return _canvas;
	}

	public void drawRectAt(int xC,int yC,int amp) {
		
		_gc.setStroke(Color.RED);

        _gc.strokePolygon(new double[]{xC-amp/2.0, xC+amp/2.0, xC+amp/2.0, xC-amp/2.0},
                new double[]{yC-amp/2.0, yC-amp/2.0, yC+amp/2.0, yC+amp/2.0}, 4);
		
		
	}

}
