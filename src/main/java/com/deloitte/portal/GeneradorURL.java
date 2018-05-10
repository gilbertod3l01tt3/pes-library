package com.deloitte.portal;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

public class GeneradorURL implements IGeneradorURL {
    private Utils utils = new Utils();

    public StringBuilder GeneradorURLEstatica(HashMap<String, Object> mapaDeVariables) {
        if (utils.ValidadorMapaEstatico(mapaDeVariables)) {
            StringBuilder sb = new StringBuilder();
            sb.append("http://");
            sb.append(mapaDeVariables.get("url").toString());
            sb.append(":");
            sb.append(mapaDeVariables.get("puerto").toString());
            sb.append("/analytics/saw.dll?dashboard");
            sb.append("&NQUser=");
            sb.append(mapaDeVariables.get("usuarioOBIEE").toString());
            sb.append("&NQPassword=");
            sb.append(mapaDeVariables.get("passwordOBIEE").toString());
            sb.append("&Action=");
            sb.append(mapaDeVariables.get("accion").toString());
            if (mapaDeVariables.containsKey("disabledFilters")) {
                sb.append("&disabledFilters=");
                sb.append(mapaDeVariables.get("disabledFilters").toString());
            }
            return sb;
        } else {
            return null;
        }
    }

    public String GeneradorURLDinamica(HashMap<String, Object> mapaDeVariables) {

        if (utils.ValidadorMapaDinamico(mapaDeVariables)) {
            StringBuilder urlDin = new StringBuilder();
            urlDin.append("&PortalPath=");
            String pathplain = mapaDeVariables.get("Path").toString() + mapaDeVariables.get("Panel").toString();
            urlDin.append(pathplain);
            mapaDeVariables.remove("Path");
            mapaDeVariables.remove("Panel");

            if (mapaDeVariables.containsKey("Page")) {
                urlDin.append("&Page=");
                urlDin.append(mapaDeVariables.get("Page"));
                mapaDeVariables.remove("Page");
            }
            int cuentaParametros = mapaDeVariables.size();
            if (cuentaParametros >= 1) {
                urlDin.append("&P0=");
                urlDin.append(cuentaParametros);
                urlDin.append("&P1=eq");
                // Imprimimos el Map con un Iterador
                Iterator it = mapaDeVariables.keySet().iterator();
                int j = 2;
                while (it.hasNext()) {
                    String key = (String) it.next();
                    String value = (String) mapaDeVariables.get(key);
                    urlDin.append("&P" + j + "=");
                    urlDin.append(key);
                    urlDin.append("&P" + (j + 1) + "=");
                    String[] vectorCheck = value.split(",");
                    if (vectorCheck.length > 0) {
                        urlDin.append(vectorCheck.length + "+");
                        String my_new_str = value.replace(",", "+");
                        urlDin.append(my_new_str);
                        j = j + 2;
                    } else {
                        urlDin.append(value);
                        j = j + 2;
                    }
                }
            }
            return urlDin.toString();
        } else {
            return null;
        }
    }

    public String ConcatenadorEncoder(StringBuilder urlEstatica, String urlDinamica) {
        if (urlEstatica.length() > 0 && urlDinamica.length() > 0) {
            urlEstatica.append(urlDinamica);
            System.out.println("URL total en plano: " + urlEstatica.toString());
            try {
                URL url = new URL(urlEstatica.toString());
                String encodedString = "";
                URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                encodedString = uri.toASCIIString();
                System.out.println("URL total encodeada: " + encodedString);
                return encodedString;
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

}
