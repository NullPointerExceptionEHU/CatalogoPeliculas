package org.eda.practica1basicsort;

import java.util.ArrayList;
import java.util.Random;

public class SortAndSearch<T extends Comparable<T>> {

	public SortAndSearch() {
	}

	public void quickSort(ArrayList<T> pArrayList) {
		quickSort(pArrayList, 0, pArrayList.size()-1);
	}

	private void quickSort(ArrayList<T> pArrayList, int inicio, int fin) {
		if (fin - inicio >= 1) { // hay más de un elemento en la tabla
			int indiceParticion = particion(pArrayList, inicio, fin);
			quickSort(pArrayList, inicio, indiceParticion - 1);
			quickSort(pArrayList, indiceParticion + 1, fin);
		}
	}

	private int particion(ArrayList<T> pArrayList, int i, int f) {
		 // MUY IMPORTANTE POR SI HAY LISTAS ORDENADAS (ELEMENTO MEDIO COMO PIVOTE)
		int izq = i;
		int der = f;
		swap(pArrayList, (i+(f-i)/2),i);
		T pivote = pArrayList.get(i);
		while (izq < der) {
			while (pArrayList.get(izq).compareTo(pivote) <= 0 && izq < der)
				izq++;
			while (pArrayList.get(der).compareTo(pivote) > 0 && der >= i && der >= izq)
				der--;
			if (izq < der)
				swap(pArrayList, izq, der);
		}
		pArrayList.set(i, pArrayList.get(der));
		pArrayList.set(der, pivote);
		return der;
	}

	private void swap(ArrayList<T> pArrayList, int one, int two) {
		T temp = pArrayList.get(one);
		pArrayList.set(one, pArrayList.get(two));
		pArrayList.set(two, temp);
	}
}
