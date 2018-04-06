package org.arthur.fractale.domain.fractalizer;

import org.arthur.fractale.domain.complex.ComplexNumber;
import org.arthur.fractale.domain.complex.ComplexUtils;

public class ModuleFractalizer extends Fractalizer {

	public ModuleFractalizer() {

	}

	@Override
	protected byte computeAt(ComplexNumber aff) {

		byte res = 127;
		
		double seuil = 2.0;

		double module = ComplexUtils.module(aff);

		if (module < seuil ) {

			res = (byte) (module / seuil * 255 - 128);

		}

		return res;
	}

}
