import java.io.Serializable;

public class Partido implements Comparable<Partido>, Serializable {

	String nombre;
	Integer votos;
	Integer escanyos;
	
	

	public Partido(String nombre, Integer votos, Integer escanyos) {
		
		this.nombre = nombre;
		this.votos = votos;
		this.escanyos = escanyos;
	}

	
	public String getNombre() {
		return nombre;
	}


	public Integer getVotos() {
		return votos;
	}



	public void setVotos(Integer votos) {
		this.votos = votos;
	}



	public Integer getEscanyos() {
		return escanyos;
	}



	public void setEscanyos(Integer escanyos) {
		this.escanyos = escanyos;
	}



	@Override
	public String toString() {
		return "Partido [nombre=" + nombre + ", votos=" + votos + ", escanyos=" + escanyos + "]";
	}


	@Override
	public int compareTo(Partido otropartido) {
		
		return this.nombre.compareTo(otropartido.nombre);
	}
	
	
	
	
	
}
