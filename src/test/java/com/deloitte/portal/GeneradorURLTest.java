package com.deloitte.portal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.assertTrue;

public class GeneradorURLTest {

    public GeneradorURL generadorURL = new GeneradorURL();
    private HashMap<String, Object> mapaEstaticoBase;
    private HashMap<String, Object> mapaDinamicoBase;

    private void FillMaps() {
        mapaEstaticoBase = new HashMap<String, Object>();
        mapaDinamicoBase = new HashMap<String, Object>();

        mapaEstaticoBase.put(Utils.KEY_HOST, "172.31.10.150");
        mapaEstaticoBase.put(Utils.KEY_PORT, "9502");
        mapaEstaticoBase.put(Utils.KEY_USER, "usr_Estado");
        mapaEstaticoBase.put(Utils.KEY_PASS, "usr_3st4d0");
        mapaEstaticoBase.put(Utils.KEY_ACTION, "Navigate");

        mapaDinamicoBase.put(Utils.KEY_PATH, "/shared/Carpeta de Pruebas/_portal/");
        mapaDinamicoBase.put(Utils.KEY_PANEL, "PanelPruebaIntegracion");
        mapaDinamicoBase.put(Utils.KEY_PAGE, "Principal");
    }

    @Before
    public void beforeEachTest() {
        System.out.println("Llenando los mapas de pruebas");
        FillMaps();
    }

    @Test
    public void GeneradorURLEstaticaMapaIncompletoUrl() {
        mapaEstaticoBase.remove("url");
        assertTrue(null == generadorURL.GeneradorURLEstatica(mapaEstaticoBase));
    }

    @Test
    public void GeneradorURLEstaticaMapaIncompletoPuerto() {
        mapaEstaticoBase.remove("puerto");
        assertTrue(null == generadorURL.GeneradorURLEstatica(mapaEstaticoBase));
    }

    @Test
    public void GeneradorURLEstaticaMapaIncompletousuarioOBIEE() {
        mapaEstaticoBase.remove("usuarioOBIEE");
        assertTrue(null == generadorURL.GeneradorURLEstatica(mapaEstaticoBase));
    }

    @Test
    public void GeneradorURLEstaticaMapaIncompletopasswordOBIEE() {
        mapaEstaticoBase.remove("passwordOBIEE");
        assertTrue(null == generadorURL.GeneradorURLEstatica(mapaEstaticoBase));
    }

    @Test
    public void GeneradorURLEstaticaMapaIncompletoaccion() {
        mapaEstaticoBase.remove("accion");
        assertTrue(null == generadorURL.GeneradorURLEstatica(mapaEstaticoBase));
    }

    @Test
    public void GeneradorURLDinamicaMapaIncompletoPath() {
        mapaDinamicoBase.remove("Path");
        assertTrue(null == generadorURL.GeneradorURLDinamica(mapaDinamicoBase));
    }

    @Test
    public void GeneradorURLDinamicaMapaIncompletoPanel() {
        mapaDinamicoBase.remove("Panel");
        assertTrue(null == generadorURL.GeneradorURLDinamica(mapaDinamicoBase));
    }


    @Test
    public void GeneradorURLEstaticaDisabledFilters() {
        mapaEstaticoBase.put("disabledFilters", "1,2,3");
        StringBuilder resultado = generadorURL.GeneradorURLEstatica(mapaEstaticoBase);
        System.out.println(resultado);
        assertTrue(resultado.toString().equals("http://172.31.10.150:9502/analytics/saw.dll?dashboard&NQUser=usr_Estado&NQPassword=usr_3st4d0&Action=Navigate&disabledFilters=1,2,3"));
    }

    @Test
    public void GeneradorURDinamicaDisabledFilters() {
        mapaDinamicoBase.put("\"Ubicacion\".\"Entidad federativa\"", "PUEBLA,TLAXCALA");
        String resultado = generadorURL.GeneradorURLDinamica(mapaDinamicoBase);
        System.out.println(resultado);
        assertTrue(resultado.equals("&PortalPath=/shared/Carpeta de Pruebas/_portal/PanelPruebaIntegracion&Page=Principal&P0=1&P1=eq&P2=\"Ubicacion\".\"Entidad federativa\"&P3=2+PUEBLA+TLAXCALA"));
    }

    @Test
    public void GeneradorCompleto(){
        mapaEstaticoBase.put("disabledFilters", "1,2,3");
        mapaDinamicoBase.put("\"Ubicacion\".\"Entidad federativa\"", "PUEBLA,TLAXCALA");
        StringBuilder resultadoEstatico = generadorURL.GeneradorURLEstatica(mapaEstaticoBase);
        String resultadoDinamico = generadorURL.GeneradorURLDinamica(mapaDinamicoBase);
        String resultadoFinal = generadorURL.ConcatenadorEncoder(resultadoEstatico, resultadoDinamico);
        System.out.println("Url final: "+resultadoFinal);
    }

    @Test
    public void GeneradorCompletoBadUri(){
        mapaEstaticoBase.put("disabledFilters", "1,2,3");
        mapaEstaticoBase.remove("url");
        mapaEstaticoBase.put("url", "172.31.10.15a");
        mapaDinamicoBase.put("\"Ubicacion\".\"Entidad federativa\"", "PUEBLA,TLAXCALA");
        StringBuilder resultadoEstatico = generadorURL.GeneradorURLEstatica(mapaEstaticoBase);
        String resultadoDinamico = generadorURL.GeneradorURLDinamica(mapaDinamicoBase);
        String resultadoFinal = generadorURL.ConcatenadorEncoder(resultadoEstatico, resultadoDinamico);
        System.out.println("Url final: "+resultadoFinal);
        Assert.assertTrue(null==resultadoFinal);
    }

}
