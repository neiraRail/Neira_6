package datos;

import contextoProblema.Libro;

import java.io.*;

public class ControlJson {
	private String ruta="libreria.json";

	public ControlJson(String ruta){
		this.ruta=ruta;
	}
	public ControlJson(){

	}

	/**
	 * Agrega al final del Array Json el objeto libro
	 * @param libro
	 */
	public void agregarObjeto(Libro libro) {
		String texto=leerArchivo();
		String target="]}";
		texto = texto.replaceAll(target,","+"\n  "+libro.toJson()+"]}");
		this.escribirTexto(texto);
	}

	/**
	 * Caso especial cuando se agrega el primer libro del Array.
	 * Noagrega la coma de separacion al comienzo del objeto ya que no hay objetos anteriores.
	 * @param libro
	 */
	public void agregarPrimero(Libro libro) {
		String texto=leerArchivo();
		String target="]}";
		texto = texto.replaceAll(target,libro.toJson()+"]}");
		this.escribirTexto(texto);
	}

	/**
	 * Quita del Array Json el elemento en la posicion indice
	 * @param indice
	 */
	public void eliminarObjeto(int indice){
		String texto=leerArchivo();
		String target = "\\{"+separarObjetos()[indice]+"},?";
		texto = texto.replaceAll(target,"");
		escribirTexto(texto);
	}

	/**
	 * Caso especial cuando se elimina el ultimo Objeto cuando quedan 2 en total.
	 * Debe quitarse la coma de separacion ya que solo quedara un objeto en el Array
	 */
	public void eliminarUltimo() {
		String texto=leerArchivo();
		String target = "\\{"+separarObjetos()[1]+"}]}";
		texto = texto.replaceAll(target,"");
		target="},";
		texto = texto.replaceAll(target,"}]}");
		escribirTexto(texto);
	}

	/**
	 * Guarda el contenido del archivo en una variable String
	 * @return String con el texto.
	 */
	String leerArchivo() {

		String texto="";
		try {
			texto=intentarleer_Archivo();
		}
		catch (IOException io) {
			System.out.println("No existe la ruta especificada");
		}
		return texto;
	}

	/**
	 * Condicion inicial del programa
	 * Garantiza la existencia de un archivo con un array vacio al inicio del programa.
	 */
	public void crearArchivo(){
		File file = new File(ruta);
		try {
			if (file.exists()) {
				file.delete();
				file.createNewFile();
				escribirTexto("{\"libros\": []}");
			} else {
				file.createNewFile();
				escribirTexto("{\"libros\": []}");
			}
		}
		catch (IOException ioe){
			System.out.println("No existe");
		}
	}


	private String intentarleer_Archivo() throws IOException {


		StringBuilder texto= new StringBuilder();
		String linea;
		FileReader fr = new FileReader(ruta);
		BufferedReader br = new BufferedReader(fr);

		while((linea = br.readLine())!=null) {
			texto.append(linea).append("\n");
		}
		br.close();
		return texto.toString();
	}

	/**
	 * Escribe el contenido de mensaje en una nueva linea del archivo
	 * @param mensaje
	 */
	void escribirTexto(String mensaje) {
		try {
			intentarEscribir(mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("El archivo no fue encontrado en este directorio!!");
		}

	}

	private void intentarEscribir(String mensaje)throws IOException {
		FileWriter fichero = new FileWriter(ruta);
		PrintWriter pw = new PrintWriter(fichero);
		pw.println(mensaje);
		fichero.close();
	}

	/**
	 * Separa los objetos del Array Json en un Arreglo de String
	 * @return Arreglo de String con los obbjetos del Json
	 */
	private String[] separarObjetos() {
		String texto=separarArray();
		String[] parte = texto.split("[{}]");
		String[] objetos = new String[parte.length/2];
		for(int i=0;i<(parte.length/2);i++){
			objetos[i]=parte[(2*i)+1];
		}
		return objetos;

	}

	private String separarArray() {
		String texto=leerArchivo();
		String[] parte = texto.split("[\\[\\]]");
		return parte[1];
	}
}