package org.arthur.fractale.domain.complex;

/**
 * Nombre complexe
 * 
 * @author ARDUFLOT
 *
 */
public class ComplexNumber {

	/**
	 * partie reelle
	 */
	private double _real;
	/**
	 * partie imaginaire
	 */
	private double _imag;

	/**
	 * Constructeur par defaut
	 */
	public ComplexNumber() {
		this(0, 0);
	}

	/**
	 * Constructeur
	 * 
	 * @param real
	 *            partie reelle
	 * @param imag
	 *            partie imaginaire
	 */

	public ComplexNumber(double real, double imag) {
		_real = real;
		_imag = imag;
	}

	public double getRe() {
		return _real;
	}

	public double getIm() {
		return _imag;
	}

	@Override
	public final boolean equals(Object z) {
		if (!(z instanceof ComplexNumber))
			return false;
		ComplexNumber a = (ComplexNumber) z;
		return (_real == a._real) && (_imag == a._imag);
	}

	public void setRe(double real) {
		_real = real;

	}

	public void setIm(double img) {

		_imag = img;

	}

}