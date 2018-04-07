package org.arthur.fractale.domain.fractalizer;

import java.util.HashMap;

import org.arthur.fractale.domain.complex.ComplexNumber;

public abstract class Fractalizer {

	/* Propri�t� �ditable du g�n�rateur */
	protected HashMap<String, String> _properties;

	/**
	 * Constructeur par d�faut
	 */
	public Fractalizer() {

		_properties = new HashMap<>();

	}

	/**
	 * Lance le calcul pour toute la zone de calcul
	 * 
	 * @param center
	 *            le centre de la zone
	 * @param amplitude
	 *            l'amplitude de la zone ( cote du carr�)
	 * @param borderLength
	 *            le nombre de point
	 * @return
	 */
	public byte[][] generate(ComplexNumber center, double amplitude, int borderLength) {

		byte[][] res = new byte[borderLength][borderLength];

		double pas = amplitude / borderLength;

		for (int i = 0; i < borderLength; i++) {
			for (int j = 0; j < borderLength; j++) {

				ComplexNumber aff = new ComplexNumber(center.getRe() - amplitude / 2 + i * pas,
						center.getIm() + amplitude / 2 - j * pas);
				res[i][j] = computeAt(aff);

			}
		}

		return res;
	}

	/**
	 * Retourne la map des propri�t�s du fractalizer
	 * 
	 * @return
	 */
	public HashMap<String, String> getProperties() {
		return _properties;
	}

	/**
	 * Lance le calcul pour le point d'affixe donn�
	 * 
	 * @param aff
	 * @return
	 */
	protected abstract byte computeAt(ComplexNumber aff);

	/**
	 * Prend en compte les propri�t�s de calcul
	 * 
	 * @param propMap
	 */
	public abstract void applyProp(HashMap<String, String> propMap);

};
