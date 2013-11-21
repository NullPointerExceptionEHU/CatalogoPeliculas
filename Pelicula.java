package org.eda.practica1basicsort;

public class Pelicula implements Comparable<Pelicula>{
	
	private String titulo;
	
	public Pelicula(String pTitulo){
		this.titulo = pTitulo;
	}
	
	public String getTitulo(){
		return this.titulo;
	}

	public void imprimir() {
		System.out.println(this.getTitulo());		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public int compareTo(Pelicula pGenT) {
		return this.getTitulo().toLowerCase().compareTo(pGenT.getTitulo().toLowerCase());
		//TODO tolowercase hay peliculas o actores con minuscula o mayuscula y no procesa bien el ordenamiento.
	}

}