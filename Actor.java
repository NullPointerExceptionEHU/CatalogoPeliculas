package org.eda.practica1basicsort;

import java.util.ArrayList;
import java.util.Iterator;

public class Actor implements Comparable<Actor>{
	private String nombre;
	private ArrayList<Pelicula> peliculas;

	public Actor(String pNombre) {
		this.nombre = pNombre;
		this.peliculas = new ArrayList<Pelicula>(); // Lo crea vacio, lo
													// llenamos con
													// anadirPelicula()
	}

	public String getNombre() {
		return this.nombre;
	}

	private ArrayList<Pelicula> getPeliculas() {
		return this.peliculas;
	}

	public Iterator<Pelicula> getPelIterator() {
		return this.getPeliculas().iterator();
	}

	private boolean esta(Pelicula pPelicula) {
		return this.getPeliculas().contains(pPelicula);
	}

	public void anadirPelicula(Pelicula pPelicula) {
		if (!this.esta(pPelicula)) {
			this.getPeliculas().add(pPelicula);
		} else {
			System.out.println("La pelicula ya está catalogada");
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public void imprimir() {
		System.out.println("********** Actor **********");
		System.out.println(this.getNombre());
		Iterator<Pelicula> itr = this.getPeliculas().iterator();
		Pelicula unaPelicula = null;
		System.out.println("********** Peliculas **********");
		while (itr.hasNext()) {
			unaPelicula = itr.next();
			unaPelicula.imprimir();

		}
	}

	@Override
	public int compareTo(Actor pGenT) {
		return this.getNombre().toLowerCase().compareTo(pGenT.getNombre().toLowerCase());
	}

}
