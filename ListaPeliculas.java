package org.eda.practica1basicsort;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaPeliculas {
	private static ListaPeliculas miListaPeliculas;
	private ArrayList<Pelicula> lista;

	private ListaPeliculas() {
		this.lista = new ArrayList<Pelicula>();
	}

	public static ListaPeliculas getListaPeliculas() {
		if (miListaPeliculas == null) {
			miListaPeliculas = new ListaPeliculas();
		}
		return miListaPeliculas;
	}

	public ArrayList<Pelicula> getLista() {
		return this.lista;
	}
	
	private boolean esta(Pelicula pPelicula) {
		return this.getLista().contains(pPelicula);
	}

	public void anadirPelicula(Pelicula pPelicula) {
		if (!this.esta(pPelicula)) {
			this.getLista().add(pPelicula);
		}
	}
	
	public void imprimir() {
		System.out.println("Peliculas *************");
		Iterator<Pelicula> itr = this.getLista().iterator();
		Pelicula unaPelicula = null;
		while(itr.hasNext()){
			unaPelicula = itr.next();
			unaPelicula.imprimir();
		}
	}

	public void ordenar() {
		SortAndSearch<Pelicula> ordenPel = new SortAndSearch<Pelicula>();
		ordenPel.quickSort(ListaPeliculas.getListaPeliculas().getLista());	
	}
}
