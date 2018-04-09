package org.arthur.fractale.domain.fractalizer;

import java.util.HashMap;

import org.arthur.fractale.domain.complex.ComplexNumber;
import org.arthur.fractale.domain.complex.ComplexUtils;

public class JuliaFractalizer extends Fractalizer {

	private final String RE_C = "Re(c)";

	private final String IM_C = "Img(c)";

	private final String ITER = "N iteration";

	private ComplexNumber _c;

	private int _nIter;

	public JuliaFractalizer() {

		super();

		_properties.put(RE_C, "-0.772");
		_properties.put(IM_C, "0.124");
		_properties.put(ITER, "200");

	}

	@Override
	protected byte computeAt(ComplexNumber aff) {

		byte res;

		int count = 0;

		ComplexNumber u = new ComplexNumber(aff.getRe(), aff.getIm());

		while (ComplexUtils.module(u) < 2 && count < _nIter) {

			uNPlusUn(u);
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

	private void uNPlusUn(ComplexNumber un) {

		// U(n+1) = U(n)² + c

		double real = un.getRe() * un.getRe() - un.getIm() * un.getIm() + _c.getRe();
		double img = 2 * un.getRe() * un.getIm() + _c.getIm();

		un.setRe(real);
		un.setIm(img);

	}

	@Override
	public void applyProp(HashMap<String, String> propMap) {

		double re = Double.parseDouble(propMap.get(RE_C));
		double im = Double.parseDouble(propMap.get(IM_C));

		_c = new ComplexNumber(re, im);
		_nIter = Integer.parseInt(propMap.get(ITER));

	}

}
