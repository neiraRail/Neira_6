package datos;

import contextoProblema.Libro;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControlJsonTest {

    @Before
    public void setUp() throws Exception {
        ControlJson ctrl = new ControlJson("libreriaTest.json");
        ctrl.escribirTexto("{\"libros\": [{\"Titulo\":\"Mercado\",\"Autor\":" +
                "\"Ariel Montecinos\",\"Precio\":\"5000\",\"Codigo\":\"0\"},\n" +
                "  {\"Titulo\":\"El Hacendado\",\"Autor\":\"Maria Trujillo\",\"Precio\":\"" +
                "5000\",\"Codigo\":\"1\"},\n" +
                "  {\"Titulo\":\"Atlas De Anatomia\",\"Autor\":\"Jose Pereira\",\"Precio\":" +
                "\"10000\",\"Codigo\":\"2\"}]}");

    }

    @Test
    public void agregarObjeto() {

        ControlJson ctrl = new ControlJson("libreriaTest.json");
        String expected="{\"libros\": [{\"Titulo\":\"Mercado\",\"Autor\":\"Ariel Montecinos\",\"Precio\":\"5000\",\"Codigo\":\"0\"},\n" +
                "  {\"Titulo\":\"El Hacendado\",\"Autor\":\"Maria Trujillo\",\"Precio\":\"5000\",\"Codigo\":\"1\"},\n" +
                "  {\"Titulo\":\"Atlas De Anatomia\",\"Autor\":\"Jose Pereira\",\"Precio\":\"10000\",\"Codigo\":\"2\"},\n" +
                "  {\"Titulo\":\"TituloTest\",\"Autor\":\"AutorTest\",\"Precio\":\"1000\",\"Codigo\":\"3\"}]}\n\n";


        Libro libro=new Libro("TituloTest","AutorTest",1000,3);
        ctrl.agregarObjeto(libro);
        assertEquals(expected,ctrl.leerArchivo());
    }


    @Test
    public void eliminarObjeto() {
        ControlJson ctrl = new ControlJson("libreriaTest.json");
        String expected="{\"libros\": [{\"Titulo\":\"Mercado\",\"Autor\":\"Ariel Montecinos\",\"Precio\":\"5000\",\"Codigo\":\"0\"},\n" +
                "  \n"+
                "  {\"Titulo\":\"Atlas De Anatomia\",\"Autor\":\"Jose Pereira\",\"Precio\":\"10000\",\"Codigo\":\"2\"}]}\n\n";
        ctrl.eliminarObjeto(1);
        assertEquals(expected,ctrl.leerArchivo());
    }



}