package org.arthur.fractale.application.manager;

import java.util.HashMap;

import org.arthur.fractale.domain.complex.ComplexNumber;
import org.arthur.fractale.domain.fractalizer.Fractalizer;
import org.arthur.fractale.domain.fractalizer.JuliaFractalizer;
import org.arthur.fractale.domain.fractalizer.MandelbrotFractalizer;
import org.arthur.fractale.domain.fractalizer.ModuleFractalizer;
import org.arthur.fractale.domain.fractalizer.RandomFractalizer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * G�re les g�n�rateurs de fractal.
 * 
 * Les calculs s'effectuent sur un carr� du plan complexe d�fini par son centre
 * et la longueur de ses cot�s
 *
 */
public class FractalizerManager {

	/* l'instance du singleton */
	private static FractalizerManager _instance;
	/* amplitude de la zone de calcul du plan complexe */
	private double _amplitude;
	/* le centre */
	private ComplexNumber _center;

	/* La map des g�n�rateur de fractale */
	private HashMap<String, Fractalizer> _fractalizerMap;
	/* le g�n�rateur courant */
	private Fractalizer _fractalizer;

	private ObservableList<String> _fractalizerNameList;

	/**
	 * Le constructeur
	 */
	private FractalizerManager() {

		_amplitude = 5;
		_center = new ComplexNumber();

		_fractalizerMap = new HashMap<>();

		_fractalizerMap.put("Random (test)", new RandomFractalizer());
		_fractalizerMap.put("Module (test)", new ModuleFractalizer());
		_fractalizerMap.put("Julia (z�+c)", new JuliaFractalizer());
		_fractalizerMap.put("Mandelbrot (z�+c)", new MandelbrotFractalizer());

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

	/**
	 * Lance le calcul
	 * 
	 * @param propMap
	 *            les propri�t� du fractalizer
	 */
	public void launchFractalizer(HashMap<String, String> propMap) {

		if (_fractalizer != null) {

			// on envoie la saisie utilisateur
			_fractalizer.applyProp(propMap);
			// calcul de l'image
			byte[][] res = _fractalizer.generate(_center, _amplitude,
					ZoneDessinManager.getInstance().getBorderLength());
			// affichage
			ZoneDessinManager.getInstance().render(res);

		}

	}

	/**
	 * R�cup�rer la map des propri�t�s du fractalizer
	 * 
	 * @return
	 */
	public HashMap<String, String> getCurrentFractalizerPropMap() {

		HashMap<String, String> map = new HashMap<>();

		if (_fractalizer != null) {

			for (String key : _fractalizer.getProperties().keySet()) {

				map.put(key, _fractalizer.getProperties().get(key));

			}

		}

		return map;
	}

	/**
	 * Change le g�n�rateur de fractale courant
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
