import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Leer {

	/**
	 * Método para imprimir mensaje que se repetía
	 */
	static public void mostrarEnPantalla(String mensaje) {
		System.out.println(mensaje);
	}

	/**
	 * @param texto
	 * @return
	 */
	static public String pedirCadena(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		String dato = "";
		boolean error = true;
		while (error) {
			try {
				dato = "";
				mostrarEnPantalla(texto);
				dato = dataIn.readLine();
				error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;

	}

	/**
	 * @param texto
	 * @return
	 */
	static public int pedirEntero(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		int dato = 0;
		boolean error = true;
		while (error) {
			try {
				mostrarEnPantalla(texto);
				dato = Integer.parseInt(dataIn.readLine());
				error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor");
				//error = true;
			} catch (NumberFormatException e) {
				mostrarEnPantalla("El dato introducido no es entero");
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;

	}

	/**
	 * @param texto
	 * @return
	 */
	static public int pedirEntero(final String[] texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		int dato = -1;
		boolean error = true;
		while (error) {
			try {
				while (dato < 0 || dato > texto.length - 1) {
					for (int i = 1; i < texto.length; i++) {
						mostrarEnPantalla(i + " " + texto[i]);
					}
					mostrarEnPantalla(0 + " " + texto[0]);

					dato = Integer.parseInt(dataIn.readLine());
					error = false;
				}
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor");
				//error = true;
			} catch (NumberFormatException e) {
				mostrarEnPantalla("El dato introducido no es entero");
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;

	}

	/**
	 * @param texto
	 * @return
	 */
	static public double pedirDouble(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		double dato = 0;
		boolean error = true;
		while (error) {
			try {
				mostrarEnPantalla(texto);
				dato = Double.parseDouble(dataIn.readLine());
				error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			} catch (NumberFormatException e) {
				mostrarEnPantalla("El dato introducido no es decimal");
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;

	}

	/**
	 * @param texto
	 * @return
	 */
	static public float pedirFloat(final String texto) {
		BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		float dato = 0;
		boolean error = true;
		while (error) {
			try {
				mostrarEnPantalla(texto);
				dato = Float.parseFloat(dataIn.readLine());
				error = false;
			} catch (IOException e) {
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			} catch (NumberFormatException e) {
				mostrarEnPantalla("El dato introducido no es decimal");
				mostrarEnPantalla("Vuelve a introducir el dato, por favor: ");
				//error = true;
			}
		}
		return dato;

	}
}
