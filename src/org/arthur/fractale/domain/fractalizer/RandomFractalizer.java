package org.arthur.fractale.domain.fractalizer;

import java.util.Random;

import org.arthur.fractale.domain.complex.ComplexNumber;

public class RandomFractalizer extends Fractalizer {

	private Random rnd;

	public RandomFractalizer() {

		rnd = new Random();

	}

	@Override
	protected byte computeAt(ComplexNumber aff) {

		byte[] res = new byte[1];
		rnd.nextBytes(res);

		return res[0];
	}

}
