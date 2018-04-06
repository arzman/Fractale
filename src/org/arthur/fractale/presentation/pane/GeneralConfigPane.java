package org.arthur.fractale.presentation.pane;

import org.arthur.fractale.application.manager.FractalizerManager;
import org.arthur.fractale.application.manager.ZoneDessinManager;
import org.arthur.fractale.domain.complex.ComplexNumber;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

/**
 * Zone de saisie de la configuration "générale" de l'application
 *
 */
public class GeneralConfigPane extends GridPane {

	/* Champ de configuration de la zone de dessin */
	private TextField _amptxt;
	private TextField _resolutionTxt;
	private TextField _centerTxt;
	private ComboBox<String> _fractalizerCmb;
	private Button _applyBtn;

	public GeneralConfigPane() {

		setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(2.0), new BorderWidths(1.0))));

		ColumnConstraints colConsLbl = new ColumnConstraints();
		colConsLbl.setFillWidth(true);
		colConsLbl.setHgrow(Priority.ALWAYS);
		getColumnConstraints().add(colConsLbl);

		ColumnConstraints colConsTxt = new ColumnConstraints();
		colConsTxt.setFillWidth(true);
		colConsTxt.setHgrow(Priority.ALWAYS);
		getColumnConstraints().add(colConsTxt);

		setVgap(2);
		setPadding(new Insets(2, 2, 2, 2));

		Label minRealLbl = new Label("Amplitude :");
		add(minRealLbl, 0, 0);

		_amptxt = new TextField();
		add(_amptxt, 1, 0);

		Label resolutionLbl = new Label("Résolution:");
		add(resolutionLbl, 0, 1);

		_resolutionTxt = new TextField();
		add(_resolutionTxt, 1, 1);

		Label centerLbl = new Label("Centre :");
		add(centerLbl, 0, 2);

		_centerTxt = new TextField();
		add(_centerTxt, 1, 2);

		_fractalizerCmb = new ComboBox<String>();
		_fractalizerCmb.setItems(FractalizerManager.getInstance().getAvailableFractalizer());

		add(_fractalizerCmb, 0, 3, 1, 2);

		_applyBtn = new Button("Calculer");
		add(_applyBtn, 0, 5, 1, 2);

		// init des champs
		getFromManager();

		// bind des listeners
		hookListeners();

	}

	/**
	 * Met à jour l'IHM avec les valeurs de configuration contenue dans
	 * l'application
	 */
	private void getFromManager() {

		// config de la zone de dessin
		_resolutionTxt.setText(String.valueOf(ZoneDessinManager.getInstance().getBorderLength()));

		// config de la partie calcul
		_amptxt.setText(String.valueOf(FractalizerManager.getInstance().getAmplitude()));
		_centerTxt.setText(formatComplexNumber(FractalizerManager.getInstance().getCentre()));

	}

	private void hookListeners() {

		_fractalizerCmb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				FractalizerManager.getInstance().setFractalizer(newValue);

			}
		});

		_applyBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				FractalizerManager.getInstance().setAmplitude(Double.parseDouble(_amptxt.getText()));
				FractalizerManager.getInstance().setCentre(parseComplexeNumber(_centerTxt.getText()));

				ZoneDessinManager.getInstance().setBorderLength(Integer.parseInt(_resolutionTxt.getText()));

				FractalizerManager.getInstance().launchFractalizer();

			}

		});

	}

	/**
	 * Transforme un nombre complexe en chaine de caractère
	 * 
	 * @param complex
	 * @return
	 */
	private String formatComplexNumber(ComplexNumber complex) {

		return String.valueOf(complex.getRe()) + " + " + String.valueOf(complex.getIm()) + "i ";
	}
	
	private ComplexNumber parseComplexeNumber(String text) {
		
		
		String[] splited = text.split("\\+");
		
		double real;
		double imag;
		
		if(splited[0].contains("i")) {
			
			real = Double.parseDouble(splited[1].trim());
			imag = Double.parseDouble(splited[0].replaceAll("i", "").trim());
			
		}else {
			real = Double.parseDouble(splited[0].trim());
			imag = Double.parseDouble(splited[1].replaceAll("i", "").trim());
		}
		
		
		ComplexNumber res = new ComplexNumber(real, imag);
		
		return res;
	}

}
