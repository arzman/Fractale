package org.arthur.fractale.domain.fractalizer;

import org.arthur.fractale.domain.complex.ComplexNumber;

public abstract class Fractalizer {

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

	protected abstract byte computeAt(ComplexNumber aff);

};
