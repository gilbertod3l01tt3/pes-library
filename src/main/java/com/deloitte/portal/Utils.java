package com.deloitte.portal;

import java.util.HashMap;

/**
 * Class that contains helper methods to generate the dynamic URL.
 */
public class Utils {

	/**
	 * Constant that represent the Key of the Host to the HashMap of variables.
	 */
	public static final String KEY_HOST = "host";
	/**
	 * Constant that represent the Key of the Port to the HashMap of variables.
	 */
	public static final String KEY_PORT = "puerto";
	/**
	 * Constant that represent the Key of the OBIEE user to the HashMap of variables.
	 */
	public static final String KEY_USER = "usuarioOBIEE";
	/**
	 * Constant that represent the Key of the OBIIE password to the HashMap of variables.
	 */
	public static final String KEY_PASS = "passwordOBIEE";
	/**
	 * Constant that represent the Key of the action to the HashMap of variables.
	 */
	public static final String KEY_ACTION = "accion";
	/**
	 * Constant that represent the Key of the disabledFilters to the HashMap of variables.
	 */
	public static final String KEY_DISABLED_FILTERS = "disabledFilters";
	
	/**
	 * Constant that represent the Key of the Path to the HashMap of variables.
	 */
	public static final String KEY_PATH = "Path";
	/**
	 * Constant that represent the Key of the Panel to the HashMap of variables.
	 */
	public static final String KEY_PANEL = "Panel";
	/**
	 * Constant that represent the Key of the Page to the HashMap of variables.
	 */
	public static final String KEY_PAGE = "Page";
	
	/**
	 * Validate the parameters that are static in the URL.
	 * @param MapaDeVariablesEstatico The map that has the name and the values of the parameters for the URL
	 * @return True if the map contains all the required static parameters, false otherwise.
	 */
    boolean ValidadorMapaEstatico(HashMap<String, Object> MapaDeVariablesEstatico) {
        boolean flag = false;
        if (!MapaDeVariablesEstatico.isEmpty()
                && MapaDeVariablesEstatico.containsKey(KEY_HOST)
                && MapaDeVariablesEstatico.containsKey(KEY_PORT)
                && MapaDeVariablesEstatico.containsKey(KEY_USER)
                && MapaDeVariablesEstatico.containsKey(KEY_PASS)
                && MapaDeVariablesEstatico.containsKey(KEY_ACTION)
                ) {
            flag = true;
        }
        return flag;
    }

    /**
     * Validate the parameters that will be dynamic in the URL.
     * @param MapaDeVariablesDinamico The map that has the name and the values of the parameters for the URL.
     * @return True if the map contains all the required dynamic parameters, false otherwise.
     */
    boolean ValidadorMapaDinamico(HashMap<String, Object> MapaDeVariablesDinamico) {
        boolean flag = false;
        if (!MapaDeVariablesDinamico.isEmpty()
                && MapaDeVariablesDinamico.containsKey(KEY_PATH)
                && MapaDeVariablesDinamico.containsKey(KEY_PANEL)
                ) {
            flag = true;
        }
        return flag;
    }
}
