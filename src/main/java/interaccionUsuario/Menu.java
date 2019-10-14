package interaccionUsuario;

import contextoProblema.Libreria;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	public Menu() {

	}

	public void desplegarMenu() {
		Libreria libreria = new Libreria();
		boolean salir = false;
		int opcion;
		while(!salir) {
			System.out.println("------Menu------");
			System.out.println("1. Mostrar Listado");
			System.out.println("2. Agregar Libro");
			System.out.println("3. Vender Libro");
			System.out.println("4. Salir");

			opcion= elegirOpcion();
			switch (opcion) {
				case 1:
					libreria.mostrarListado();
					break;
				case 2:
					libreria.agregarLibro();
					break;
				case 3:
					libreria.venderLibro();
					break;
				case 4:
					System.out.println("Salir");
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 4");
			}


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