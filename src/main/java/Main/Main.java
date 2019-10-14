package Main;

import datos.ControlJson;
import interaccionUsuario.Menu;

public class Main {

	public static void main(String[] args) {
		ControlJson ctr = new ControlJson();
		ctr.crearArchivo();
		Menu menu = new Menu();
		menu.desplegarMenu();
	}
}