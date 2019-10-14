package contextoProblema;

public class Libro {
	private String titulo;
	private String autor;
	private int precio;
	private int codigo;

	/**
	 * toString de Libro
	 * @return String cno formato listado por consola. Cada dato en una linea diferente.
	 */
	public String toString(){
		return "Titulo: "+titulo+".\n \tAutor: "+autor+".\n\tPrecio: $"+precio+"\n\tCodigo: "+codigo+"\n";
	}

	/**
	 * Similar a toString, pero orientado al formato Json
	 * @return	String del objeto en formato Json.
	 */
	public String toJson(){
		return "{\"Titulo\":\"" + titulo + '\"' +
				",\"Autor\":\"" + autor + '\"' +
				",\"Precio\":\"" + precio + '\"' +
				",\"Codigo\":\"" + codigo + '\"' +
				'}';
	}

	public Libro(String titulo, String autor, int precio, int codigo) {
		this.titulo=titulo;
		this.autor=autor;
		this.precio=precio;
		this.codigo=codigo;
	}
}