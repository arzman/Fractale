package org.arthur.fractale.domain.fractalizer;

import java.util.HashMap;

import org.arthur.fractale.domain.complex.ComplexNumber;
import org.arthur.fractale.domain.complex.ComplexUtils;

public class ModuleFractalizer extends Fractalizer {

	private final String SEUIL = "Seuil";

	/* seuil en dessous duquel on colorie */
	private double seuil;

	public ModuleFractalizer() {

		_properties.put(SEUIL, "2.0");
		seuil = 2.0;

	}

	@Override
	protected byte computeAt(ComplexNumber aff) {

		byte res = 127;

		double module = ComplexUtils.module(aff);

		if (module < seuil) {

			res = (byte) (module / seuil * 255 - 128);

		}

		return res;
	}

	@Override
	public void applyProp(HashMap<String, String> propMap) {

		seuil = Double.parseDouble(propMap.get(SEUIL));

	}

}
