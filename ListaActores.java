package org.eda.practica1basicsort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ListaActores {
	private ArrayList<Actor> lista;
	private static ListaActores miListaActores;

	private ListaActores() {
		this.lista = new ArrayList<Actor>();
	}

	public static ListaActores getListaActores() {
		if (miListaActores == null) {
			miListaActores = new ListaActores();
		}
		return miListaActores;
	}

	public ArrayList<Actor> getLista() {
		return lista;
	}

	private boolean esta(Actor pActor) {
		return this.getLista().contains(pActor);
	}

	public void anadirActor(Actor pActor) {
		if (!this.esta(pActor)) {
			this.getLista().add(pActor);
		} else {
			System.out.println("El actor ya está catalogado");
		}
	}
	
	public Actor buscarActorPorNombre() {
		//si lo escriben completamente bien (si hay alguno con lo introducido exactamente igual), devolvemos ese actor y lo imprimimos con sus peliculas
		//sino, comprobamos si lo introducido es parte del nombre
		String resp;
		ArrayList<Actor> encontrados = new ArrayList<Actor>();
		System.out.println("Introduce el nombre completo de la actriz/actor (Apellido, Nombre) o parte de el:\n");
		Scanner sc = new Scanner(System.in);
		resp = sc.nextLine();
		Iterator<Actor> itr = this.getLista().iterator();
		Actor unActor = null;
		while (itr.hasNext()) {
			unActor = itr.next();
			if (unActor.getNombre().indexOf (resp) != -1) { //Esto compara si es substring
				encontrados.add(unActor);
			}
		}
		if (encontrados.size()==0) {return null;}
		else if (encontrados.size() == 1) {return encontrados.get(0);}
		else
		{
			System.out.println("\n\n\n****ACTRICES/ACTORES ENCONTRADAS****\n\n");
			Iterator<Actor> itr2 = encontrados.iterator();
			int i=1;
			while(itr2.hasNext())
			{
				unActor=itr2.next();
				System.out.println("\n\nActriz/Actor numero "+i);
				i++;
				System.out.println(unActor.getNombre());
			}
			int numActriz=-1;
			while (numActriz<=0 || numActriz>encontrados.size())
			{
				System.out.println("Introduce el numero de actriz/actor: ");
				Scanner sc2 = new Scanner(System.in);
				numActriz = Integer.parseInt(sc2.next());
			}
			return encontrados.get(numActriz-1);
		}
	}


	public void borrarActor(String pNomActor) {
		if (this.getLista().remove(this.buscarActorPorNombre())){
			System.out.println("Borrado completado con exito");
		}else{
			System.out.println("Actor/Actriz no disponible en la base de datos");
		}
	}
	

	public void imprimir() {
		System.out.println("Actores y peliculas *************");
		Iterator<Actor> itr = this.getLista().iterator();
		Actor unActor = null;
		while (itr.hasNext()) {
			unActor = itr.next();
			unActor.imprimir();
		}
	}

	public void ordenar() {
		SortAndSearch<Actor> ordenAct = new SortAndSearch<Actor>();
		ordenAct.quickSort(ListaActores.getListaActores().getLista());
		
	}
}
