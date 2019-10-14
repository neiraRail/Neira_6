package contextoProblema;
import datos.ControlJson;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Libreria {
	private ArrayList<Libro> stock = new ArrayList<>();
	private int librosTotal = 0;

	public Libreria(){
	}

	public void agregarLibro() {
		ControlJson ctrl = new ControlJson();
		String titulo= recibirTitulo();
		String autor=recibirAutor();
		int precio = recibirPrecio();
		int codigo = librosTotal;

		Libro libro = new Libro(titulo,autor,precio,codigo);
		stock.add(libro);

		if(stock.size()==1){
			ctrl.agregarPrimero(libro);
		}
		else {
			ctrl.agregarObjeto(libro);
		}
		librosTotal++;
	}

	public void mostrarListado() {
		if(stock.size()==0){
			System.out.println("No hay Libros!");
		}
		else {
			System.out.println("Listado---------");
			for (int i = 0; i < stock.size(); i++) {
				System.out.println("[" + (i + 1) + "] " + stock.get(i));
			}
		}
	}

	public void venderLibro() {
		if (stock.size()==0){
			System.out.println("No hay libros!");
		}
		else {
			ControlJson ctrl = new ControlJson();
			System.out.println("Que libro desea vender.");
			mostrarListado();
			try {
				int indice= elegirOpcion()-1;
				stock.remove(indice);
				if(stock.size()==1&&indice==1)
					ctrl.eliminarUltimo();
				else
					ctrl.eliminarObjeto(indice);
			} catch (IndexOutOfBoundsException iob) {
				System.out.println("Debe elegir una de las opciones!");
			}
		}
	}

	private String recibirTitulo(){
		System.out.println("Titulo");
		//Una o dos palabras con Mayusculas
		return recibir("^[A-Z][a-z]+(\\s[A-Z][a-z]+)*?$");
	}

	private String recibirAutor(){
		System.out.println("Nombre y apellido del Autor");
		return recibir("^[A-Z][a-z]+\\s[A-Z][a-z]+$");
	}

	private int recibirPrecio(){
		System.out.println("Precio");
		return Integer.parseInt(recibir("^[0-9]{4,6}$"));

	}

	private String recibir(String regex){
		String valor;
		Scanner scan = new Scanner(System.in);
		Pattern patron = Pattern.compile(regex);
		String texto = scan.nextLine();
		Matcher matcher = patron.matcher(texto);
		if(matcher.matches()) {
			valor = texto;
			return valor;
		}
		else {
			System.out.println(texto+" no es valido. Ingrese nuevamente");
			return recibir(regex);
		}

	}

	private int elegirOpcion() {
		int opcion=0;
		try {
			opcion= intentarElegir();
		}
		catch (InputMismatchException e) {
			System.out.println("Debes insertar un número");
			elegirOpcion();
		}
		return opcion;
	}

	private int intentarElegir() {
		Scanner sn = new Scanner(System.in);
		return sn.nextInt();
	}
}