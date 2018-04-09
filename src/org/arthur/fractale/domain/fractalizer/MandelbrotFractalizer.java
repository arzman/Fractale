package org.arthur.fractale.domain.fractalizer;

import java.util.HashMap;

import org.arthur.fractale.domain.complex.ComplexNumber;
import org.arthur.fractale.domain.complex.ComplexUtils;

public class MandelbrotFractalizer extends Fractalizer {

	private final String RE_Z0 = "Re(Z0)";

	private final String IM_Z0 = "Img(Z0)";

	private final String ITER = "N iteration";

	private ComplexNumber _z0;

	private int _nIter;

	public MandelbrotFractalizer() {

		super();
		// -0.0958 + 0.735i
		_z0 = new ComplexNumber(-0.0958, 0.735);

		_properties.put(RE_Z0, "-0.0958");
		_properties.put(IM_Z0, "0.735");

		_nIter = 200;
		_properties.put(ITER, "200");

	}

	@Override
	protected byte computeAt(ComplexNumber aff) {

		byte res;

		int count = 0;

		ComplexNumber u = new ComplexNumber(_z0.getRe(), _z0.getIm());

		while (ComplexUtils.module(u) < 2 && count < _nIter) {

			uNPlusUn(u, aff);
			count = count + 1;

		}

		if (ComplexUtils.module(u) < 2) {
			// convergeance
			res = -128;

		} else {
			//
			res = (byte) (count - 100);
		}

		return res;
	}

	private void uNPlusUn(ComplexNumber un, ComplexNumber c) {

		// U(n+1) = U(n)² + c

		double real = un.getRe() * un.getRe() - un.getIm() * un.getIm() + c.getRe();
		double img = 2 * un.getRe() * un.getIm() + c.getIm();

		un.setRe(real);
		un.setIm(img);

	}

	@Override
	public void applyProp(HashMap<String, String> propMap) {

		double re = Double.parseDouble(propMap.get(RE_Z0));
		double im = Double.parseDouble(propMap.get(IM_Z0));

		_z0 = new ComplexNumber(re, im);
		_nIter = Integer.parseInt(propMap.get(ITER));

	}

}
