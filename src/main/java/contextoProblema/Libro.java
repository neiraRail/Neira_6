package contextoProblema;

public class Libro {
	private String titulo;
	private String autor;
	private int precio;
	private int codigo;

	public String toString(){
		return "Titulo: "+titulo+".\n \tAutor: "+autor+".\n\tPrecio: $"+precio+"\n\tCodigo: "+codigo+"\n";
	}
	public String toJson(){
		return "{\"Titulo\":\"" + titulo + '\"' +
				",\"Autor\":\"" + autor + '\"' +
				",\"Precio\":\"" + precio + '\"' +
				",\"Codigo\":\"" + codigo + '\"' +
				'}';
	}

	Libro(String titulo, String autor, int precio, int codigo) {
		this.titulo=titulo;
		this.autor=autor;
		this.precio=precio;
		this.codigo=codigo;
	}
}