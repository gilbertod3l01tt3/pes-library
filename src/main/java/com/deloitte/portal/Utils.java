package com.deloitte.portal;

import java.util.HashMap;

public class Utils {

    boolean ValidadorMapaEstatico(HashMap<String, Object> MapaDeVariablesEstatico) {
        boolean flag = false;
        if (!MapaDeVariablesEstatico.isEmpty()
                && MapaDeVariablesEstatico.containsKey("url")
                && MapaDeVariablesEstatico.containsKey("puerto")
                && MapaDeVariablesEstatico.containsKey("usuarioOBIEE")
                && MapaDeVariablesEstatico.containsKey("passwordOBIEE")
                && MapaDeVariablesEstatico.containsKey("accion")
                ) {
            flag = true;
        }
        return flag;
    }

    boolean ValidadorMapaDinamico(HashMap<String, Object> MapaDeVariablesDinamico) {
        boolean flag = false;
        if (!MapaDeVariablesDinamico.isEmpty()
                && MapaDeVariablesDinamico.containsKey("Path")
                && MapaDeVariablesDinamico.containsKey("Panel")
                ) {
            flag = true;
        }
        return flag;
    }
}
