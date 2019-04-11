import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Principal {

	public static void main(String[] args) {
		String nombre;
		Integer votos, escanyos, totalVotos = 0, limite;
		
		TreeMap<String, Partido> grupoPartidos = new TreeMap<>();
		TreeMap<String , ArrayList<Integer>> repartoEsc = new TreeMap<String, ArrayList<Integer>>();
		//int numPartidos = Leer.pedirEntero("¿Cuántos partidos quieres crear?");
		escanyos = Leer.pedirEntero("¿Cuántos escaños se van a repartir?");
		rellenaPartidos (grupoPartidos, repartoEsc); //rellenamos el mapa de partidos y el de escaños con los datos de nombre y votos.
		
		
		Leer.mostrarEnPantalla( "----- PARTIDOS-----\n" + grupoPartidos.toString());
		for (Map.Entry<String, Partido> datos:  grupoPartidos.entrySet()) { // sumamos los votos de cada partido 
			Partido unPartido;
			 Integer votosPart;
			 unPartido = datos.getValue(); //metemos en esta variable un partido en cada vuelta del bucle 
			 votosPart = unPartido.getVotos();
			 totalVotos = totalVotos + votosPart;
			
		}
		
		System.out.println( " El TOTAL DE LOS VOTOS ES: " + totalVotos);
		limite = (totalVotos*5)/100;
		System.out.println(" EL 5% del total de votos es :" + limite);
		
		calculoCocientes(repartoEsc, grupoPartidos, escanyos, totalVotos); //calculamos y añadimos los cocientes al arrayList de cada partido
		Leer.mostrarEnPantalla("-----------COCIENTES DE LOS PARTIDOS-------------\n");
		Leer.mostrarEnPantalla(repartoEsc.toString()); //mostramos los cocientes de los partidos
		
		reparticion(grupoPartidos, repartoEsc, escanyos); // repartimos los escaños segun los cocientes
		Leer.mostrarEnPantalla(" -----------RESULTADOS REPARTO DE ESCAÑOS -------------");
		Leer.mostrarEnPantalla(grupoPartidos.toString()); //mostramos el resultado final de los escaños
		

	}//main
	
	public static  void  rellenaPartidos( TreeMap<String, Partido> grupoPartidos, TreeMap<String, ArrayList<Integer>> RepartoEsc) {
		String nombre;
		Partido unPartido;
		Integer votos, totalVotos = 0;
		ArrayList<Integer> listaCocientes;
		
	
		Fichero fichero;
		
		fichero = new Fichero ("D:\\PROGRAMACIÓN\\Expresiones Regulares\\wks_reg_exp\\Partidos\\src\\ficheroPartidos.dat", "I");
		leeFichero(grupoPartidos, fichero);
		fichero.cerrar("I");//cerrar fichero de lectura 
		
		
		for (Map.Entry<String,Partido> recorrePartidos: grupoPartidos.entrySet()) {
			votos = (int) (Math.random ()*10000+3000); // ponemos el número de votos al azar
			unPartido= recorrePartidos.getValue(); //guardamos el valor del objeto aquí
			nombre= unPartido.getNombre();
			unPartido.setVotos(votos); //guardamos los votos en el objeto partido
			listaCocientes = new ArrayList<Integer>(); //creamos el objeto array list, uno para cada partido,a quí guardaremos los cocientes.
			RepartoEsc.put(nombre, listaCocientes); //le añadimos al mapa de escaños la información
			grupoPartidos.put(nombre, unPartido);
			
		}//for
		
	} //fin del metodo rellenar
	

	public static void calculoCocientes (TreeMap <String, ArrayList<Integer>> repartoEsc, TreeMap<String, Partido> grupoPartidos, int escanyos, int totalVotos) {
		ArrayList<Integer> listaCocientes;
		Partido unPartido;
		String nombreP = ""; // hay que inicializarlo para que no de error dentro del for 
		
		for (Map.Entry<String,Partido> recorrePartidos: grupoPartidos.entrySet()) {
			if(recorrePartidos.getValue().getVotos() > totalVotos * 0.05) {
		nombreP=recorrePartidos.getKey(); // sacamos la clave del mapa de partidos, que es el nombre del partido 
			listaCocientes= repartoEsc.get(nombreP) ; //así extraemos el arrayList del mapa de repartir escaños.
			for (int i = 1 ; i <= escanyos; i ++) { // recorremos el numero de escaños para sacar todos los cocientes
				listaCocientes.add( recorrePartidos.getValue().getVotos()/i); // añadimos al array de cocientes cada resultado de dividir 
																			// votos por i
			} //segundo for
			repartoEsc.put(nombreP, listaCocientes); // actualizamos la nueva información del mapa
			}
		} // for each
			
	} // fin del calculo de cocientes
	
	

	
	
	public static void reparticion ( TreeMap <String, Partido> grupoPartidos, TreeMap<String, ArrayList<Integer>> repartoEsc, int escanyos) {
		String nombreP, partidoElegido= "";
		Partido unPartido;
		ArrayList<Integer> listaCocientes;
		int cociente;
		for (int i = 0; i < escanyos; i++) { //recorremos los escaños existentes
			cociente = 0; // inicializamos el cociente a 0 en cada vuelta de escaño
			for (Map.Entry <String, ArrayList<Integer>> mapaEscanyos: repartoEsc.entrySet()) {
				listaCocientes= mapaEscanyos.getValue(); // sacamos el array de los cocientes de un partdo
				nombreP=mapaEscanyos.getKey(); //sacamos el nombre que necesitaremos después
				if (listaCocientes.size() > 0) {
					
					if (cociente < listaCocientes.get(0)) {
						partidoElegido = nombreP; //estes erá el partido con un mayor cociente y lo guardamos
						cociente = listaCocientes.get(0);		
					} // if
				}
			}//for each
			listaCocientes = repartoEsc.get(partidoElegido);
			listaCocientes.remove(0); //borramos el cociente utilizado para que no se vuelva a elegir
			repartoEsc.put(partidoElegido, listaCocientes); //actualizamos el mapa porque hemos quitado un cociente
			unPartido = grupoPartidos.get(partidoElegido); //guardamos el objeto partido en la variable
			unPartido.setEscanyos(unPartido.getEscanyos()+1); // añadimos el escaño al partido seleccionado
			grupoPartidos.put(partidoElegido, unPartido); //actualizamos el mapa de partidos
		}//for de los escaños
		
		
	} // fin del método de repartición de escaños

	private static void leeFichero(TreeMap <String,Partido> grupoPartidos, Fichero fichero) {
		Partido partido;
		partido = fichero.leer();
		String nombre;
		while (partido != null) {
			nombre= partido.getNombre();
			grupoPartidos.put(nombre,partido);//lo añadimos a la lista
			partido = fichero.leer();//leemos siguiente
		}
		
	}
}//class
