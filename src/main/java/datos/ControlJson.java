package datos;

import contextoProblema.Libro;

import java.io.*;

public class ControlJson {
	private String ruta="libreria.json";

	public ControlJson(){

	}
	public void agregarObjeto(Libro libro) {
		String texto=leerArchivo();
		String target="]}";
		texto = texto.replaceAll(target,","+"\n  "+libro.toJson()+"]}");
		this.escribirTexto(texto);
	}

	public void agregarPrimero(Libro libro) {
		String texto=leerArchivo();
		String target="]}";
		texto = texto.replaceAll(target,libro.toJson()+"]}");
		this.escribirTexto(texto);
	}

	public void eliminarObjeto(int indice){
		String texto=leerArchivo();
		String target = "\\{"+separarObjetos()[indice]+"},?";
		texto = texto.replaceAll(target,"");
		escribirTexto(texto);
	}

	public void eliminarUltimo() {
		String texto=leerArchivo();
		String target = "\\{"+separarObjetos()[1]+"}]}";
		texto = texto.replaceAll(target,"");
		target="},";
		texto = texto.replaceAll(target,"}]}");
		escribirTexto(texto);
	}

	private String leerArchivo() {

		String texto="";
		try {
			texto=intentarleer_Archivo();
		}
		catch (IOException io) {
			System.out.println("No existe la ruta especificada");
		}
		return texto;
	}
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

	private void escribirTexto(String mensaje) {
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