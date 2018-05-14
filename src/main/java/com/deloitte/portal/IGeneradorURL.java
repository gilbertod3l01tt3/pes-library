package com.deloitte.portal;

import java.util.HashMap;

/**
 * Contract to generate the OBIEE dynamic URL.
 *
 */
public interface IGeneradorURL {

	/**
	 * Generate the static part of the URL.
	 * @param mapaDeVariables The HashMap that contains the variables for the static part of the URL.
	 * @return The static part of the URL.
	 */
    StringBuilder GeneradorURLEstatica(HashMap<String, Object> mapaDeVariables);

    /**
     * Generate the dynamic part of the URL.
     * @param mapaDeVariables The HashMap that contains the variables for the dynamic part of the URL.
     * @return The dynamic part of the URL.
     */
    String GeneradorURLDinamica(HashMap<String, Object> mapaDeVariables);

    /**
     * Used to concatenate the static and dynamic parts of the URL.
     * @param urlEstatica The static part of the URL.
     * @param urlDinamica The dynamic part of the URL.
     * @return The complete OBIEE URL.
     */
    String ConcatenadorEncoder(StringBuilder urlEstatica, String urlDinamica);

}
