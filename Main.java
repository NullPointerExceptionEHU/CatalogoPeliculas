package org.eda.practica1basicsort;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		while (true) {
			cargarMenu();
		}
	}

	private static void cargarMenu() {
		System.out.println("***** Bienvenido *****");
		System.out.println("**********************");
		System.out.println("1. Cargar los datos desde fichero");
		System.out.println("2. Buscar actor/actriz por nombre y apellidos.");
		System.out.println("3. Añadir un actor");
		System.out.println("X. Añadir una pelicula"); // TODO?
		System.out.println("4. Borrar un actor/actriz");
		System.out.println("5. Guardar la lista en un fichero");
		System.out.println("6. Obtener la lista de actores ordenada");
		System.out.println("7. Obtener la lista de peliculas ordenada");
		System.out.println("0. Salir");
		System.out.println("");
		System.out.print(">> ");
		Scanner sc;
		sc = new Scanner(System.in);
		String resp = sc.next();
		switch (resp) {
		case "1":
			System.out.println("Ruta del fichero (ruta/al/fichero/): ");
			sc.nextLine(); // Parar ejecucion
			String ruta = sc.nextLine();
			System.out.print("Nombre del fichero a cargar (nombre.txt): ");
			String nomF = sc.nextLine();
			cargarFichero(ruta,nomF);
			break;
		case "2":
			try {
				ListaActores.getListaActores().buscarActorPorNombre().imprimir();
			} catch (NullPointerException e) {
				System.out.println("Lista vacia o actor no encontrado");
			}
			break;
		case "3":
			System.out.print("Introduce un actor [Apellido, nombre]: ");
			sc.nextLine(); // Parar ejecucion
			resp = sc.nextLine();
			Actor unActor = new Actor(resp);
			ListaActores.getListaActores().anadirActor(unActor);
			do {
				System.out.print("Pelicula: ");
				resp = sc.nextLine();
				unActor.anadirPelicula(new Pelicula(resp));
				ListaPeliculas.getListaPeliculas().anadirPelicula(new Pelicula(resp));
				System.out.print("Introducir otra pelicula (s/n): ");
				resp = sc.next();
			} while (resp == "s");
			break;
		case "4":
			ListaActores.getListaActores().borrarActor(resp);
			break;
		case "5":
			guardarListaEnFichero();
			break;
		case "6":
			ListaActores.getListaActores().ordenar();
			ListaActores.getListaActores().imprimir();
			break;
		case "7":
			ListaPeliculas.getListaPeliculas().ordenar();
			ListaPeliculas.getListaPeliculas().imprimir();
			break;
		case "0":
			System.exit(0);
			break;
		default:
			System.out.println("Opcion desconocida");
			break;
		}
	}

	private static void cargarFichero(String ruta, String nomF) {
		try {
			Scanner entrada = new Scanner(
					new FileReader(ruta + nomF));
			String linea, nomActor = null;
			Actor unActor = null;
			Pelicula unaPelicula = null;
			String[] datos;
			while (entrada.hasNext()) {
				linea = entrada.nextLine();
				// Si se encuentra el ENTER
				if (!linea.isEmpty()) {
					datos = linea.split("\t+"); // Separadas en 2, NomAct - Peli
					nomActor = datos[0]; // Meto el nombre del actor
					datos = datos[1].split("[ ][(][0-9]\\d+[)]");
					datos[0] = datos[0].replaceAll("\"", ""); // Un string es
																// inmutable hay
																// que
																// sobreescribir
					if ("".equals(nomActor)) { // Si no hay Act (Solo peli)
						// Quito lo que no nos interesa del titulo Peli
						unaPelicula = new Pelicula(datos[0]);
						unActor.anadirPelicula(unaPelicula);
						ListaPeliculas.getListaPeliculas().anadirPelicula(
								unaPelicula);
					} else { // Hay Nombre de Actor. CREO ACTOR.
						unActor = new Actor(nomActor);
						ListaActores.getListaActores().anadirActor(unActor);
						unaPelicula = new Pelicula(datos[0]);
						unActor.anadirPelicula(unaPelicula);
						ListaPeliculas.getListaPeliculas().anadirPelicula(
								unaPelicula);
					}
				}
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void guardarListaEnFichero() {
		FileWriter fichero = null;
		PrintWriter pw = null;
		Scanner sc;
		sc = new Scanner(System.in);
		System.out.println("Ruta del fichero (ruta/al/fichero/): ");
		String ruta = sc.nextLine();
		System.out.print("Nombre del fichero a guardar (nombre.txt): ");
		String nomF = sc.nextLine();
		try {
			fichero = new FileWriter(ruta + nomF);
			pw = new PrintWriter(fichero);

			Iterator<Actor> itrAct = ListaActores.getListaActores().getLista()
					.iterator();
			Iterator<Pelicula> itrPel = null;
			Actor unActor = null;
			Pelicula unaPelicula = null;
			int i; // Para comprobar si es la primera pelicula y no añadir
						// tabuladores
			while (itrAct.hasNext()) {
				unActor = itrAct.next();
				pw.print(unActor.getNombre());
				itrPel = unActor.getPelIterator();
				i = 0;
				while (itrPel.hasNext()) {
					unaPelicula = itrPel.next();
					//No hay patron para nombres largos?
					if (i == 0){
						pw.println("\t" + unaPelicula.getTitulo());
					}else{
						pw.println("\t\t\t" + unaPelicula.getTitulo());
					}
					i++;
				}
				pw.println("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}