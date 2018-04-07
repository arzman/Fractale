package org.arthur.fractale.domain.fractalizer;

import org.arthur.fractale.domain.complex.ComplexNumber;
import org.arthur.fractale.domain.complex.ComplexUtils;

public class JuliaFractalizer extends Fractalizer {

	private ComplexNumber _c;

	public JuliaFractalizer() {
		// -0.0958 + 0.735i
		_c = new ComplexNumber(-0.0958, 0.735);

	}

	@Override
	protected byte computeAt(ComplexNumber aff) {

		byte res;

		int count = 0;

		ComplexNumber u = new ComplexNumber(aff.getRe(), aff.getIm());

		while (ComplexUtils.module(u) < 2 && count < 100) {

			uNPlusUn(u);
			count = count + 1;

		}

		if (ComplexUtils.module(u) < 2) {

			res = -128;

		} else {

			res = 127;
		}

		return res;
	}

	private void uNPlusUn(ComplexNumber un) {

		double real = un.getRe()*un.getRe() - un.getIm() * un.getIm() + _c.getRe();
		double img = 2 * un.getRe() * un.getIm() + _c.getIm();

		un.setRe(real);
		un.setIm(img);

	}

}
