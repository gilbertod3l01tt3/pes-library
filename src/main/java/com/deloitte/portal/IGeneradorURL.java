package com.deloitte.portal;

import java.util.HashMap;

public interface IGeneradorURL {

    StringBuilder GeneradorURLEstatica(HashMap<String, Object> mapaDeVariables);

    String GeneradorURLDinamica(HashMap<String, Object> mapaDeVariables);

    String ConcatenadorEncoder(StringBuilder urlEstatica, String urlDinamica);

}
