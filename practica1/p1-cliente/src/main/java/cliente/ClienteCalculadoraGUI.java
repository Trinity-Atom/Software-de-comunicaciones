package cliente;

import CalculadoraGUI.CalculadoraGUI;

public class ClienteCalculadoraGUI {

	public static void main(String[] args) {
		
		new CalculadoraGUI(new AdaptadorOperacionesCalculadoraGUI());
	}

}
