package org.arthur.fractale.application.manager;

import java.util.HashMap;

import org.arthur.fractale.domain.complex.ComplexNumber;
import org.arthur.fractale.domain.fractalizer.Fractalizer;
import org.arthur.fractale.domain.fractalizer.JuliaFractalizer;
import org.arthur.fractale.domain.fractalizer.ModuleFractalizer;
import org.arthur.fractale.domain.fractalizer.RandomFractalizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * Gère les générateurs de fractal.
 * 
 * Les calculs s'effectuent sur un carré du plan complexe défini par son centre
 * et la longueur de ses cotés
 *
 */
public class FractalizerManager {

	/* l'instance du singleton */
	private static FractalizerManager _instance;
	/* amplitude de la zone de calcul du plan complexe */
	private double _amplitude;
	/* le centre */
	private ComplexNumber _center;

	/* La map des générateur de fractale */
	private HashMap<String, Fractalizer> _fractalizerMap;
	/* le générateur courant */
	private Fractalizer _fractalizer;

	private ObservableList<String> _fractalizerNameList;

	/**
	 * Le constructeur
	 */
	private FractalizerManager() {

		_amplitude = 5;
		_center = new ComplexNumber();

		_fractalizerMap = new HashMap<>();

		_fractalizerMap.put("Random", new RandomFractalizer());
		_fractalizerMap.put("Module", new ModuleFractalizer());
		_fractalizerMap.put("Julia", new JuliaFractalizer());

		_fractalizerNameList = FXCollections.observableArrayList();

		for (String key : _fractalizerMap.keySet()) {
			_fractalizerNameList.add(key);
		}

	}

	/**
	 * Retourne l'instance du singleton
	 * 
	 * @return
	 */
	public static FractalizerManager getInstance() {

		if (_instance == null) {
			_instance = new FractalizerManager();
		}

		return _instance;

	}

	public void launchFractalizer() {

		if (_fractalizer != null) {

			System.out.println("Calcul envoyé");

			byte[][] res = _fractalizer.generate(_center, _amplitude,
					ZoneDessinManager.getInstance().getBorderLength());

			System.out.println("Rendering en cours");

			ZoneDessinManager.getInstance().render(res);

			System.out.println("fin");
		}

	}

	/**
	 * Change le générateur de fractale courant
	 * 
	 * @param fractalizer
	 *            le nom du nouveau fractalizer
	 */
	public void setFractalizer(String fractalizer) {

		_fractalizer = _fractalizerMap.get(fractalizer);

	}

	/**
	 * Retourne l'amplitude de la zone de calcul
	 * 
	 * @return
	 */
	public double getAmplitude() {

		return _amplitude;
	}

	/**
	 * Retourne le centre de la zone de calcul
	 * 
	 * @return
	 */
	public ComplexNumber getCentre() {
		return _center;
	}

	public ObservableList<String> getAvailableFractalizer() {
		return _fractalizerNameList;
	}

	public void setAmplitude(double amp) {

		_amplitude = amp;

	}

	public void setCentre(ComplexNumber center) {
		_center = center;

	}

}
