package org.arthur.fractale.domain.complex;

public class ComplexUtils {

	public static double module(ComplexNumber z) {
		
		return Math.sqrt((z.getRe()*z.getRe() + z.getIm()*z.getIm()));
		
	}

}
