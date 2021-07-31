package by.epam.jonline.mod02;

import java.util.Scanner;

/*
 * 7. Пусть даны две неубывающие последовательности действительных чисел a1 <= a2 <= ... <= a(n) и 
 * b1 <= b2 <= ... <= b(m). Требуется указать те места, на которые нужно вставлять элементы 
 * последовательности b1 <= b2 <= ... <= b(m) в первую последовательность так, чтобы новая 
 * последовательность оставалась возрастающей.
 */

public class SortingTask07 {

	public static void main(String[] args) {

		int[] mas1;
		int[] mas2;
		int[] mas3;

		int n1;
		int n2;

		do {
			n1 = inputInt("Введите размер массива n1: ");
		} while (n1 < 1);

		do {
			n2 = inputInt("Введите размер массива n2: ");
		} while (n2 < 1);

		mas1 = new int[n1];
		mas2 = new int[n2];
		mas3 = new int[n1 + n2];

		// создание неубывающего массива a
		System.out.println("Введите элементы массива неубывающего ряда:");
		for (int i = 0; i < n1; i++) {

			if (i == 0) {
				mas1[i] = inputInt("a(" + (i + 1) + "): ");
				continue;
			}

			do {
				mas1[i] = inputInt("a(" + (i + 1) + "): ");
			} while (mas1[i] < mas1[i - 1]);
		}
	

		// создание неубывающего массива b
		System.out.println("Введите элементы массива неубывающего ряда:");
		for (int i = 0; i < n2; i++) {

			if (i == 0) {
				mas2[i] = inputInt("b(" + (i + 1) + "): ");
				continue;
			}

			do {
				mas2[i] = inputInt("b(" + (i + 1) + "): ");
			} while (mas2[i] < mas2[i - 1]);
		}
	
		print(mas1, "Массив a: ");
		print(mas2, "Массив b: ");

		mas3 = insertSortPosition(mas1, mas2, mas3);
		print(mas3, "Результирующий массив: ");
		
		printSortPosition(mas1, mas2);
		
	}

	// нахождение позиций вставки и формирование новой неубывающей последовательности
	public static int[] insertSortPosition(int[] mas1, int[] mas2, int[] mas3) {

		// переписываем отсортированную последовательность "a" в общий массив
		for (int i = 0; i < mas1.length; i++) {
			mas3[i] = mas1[i];
		}

		for (int i = 0, position = 0; i < mas2.length; i++) {

			// находим позицию вставки элемента из массива b в массив а
			// так как две последовательности отсортированы по возрастанию, позиция вставки
			// всегда будет впереди

			while (mas2[i] > mas3[position] && position < mas1.length + i) {
				position++;
			}

			// сдвиг массива
			for (int j = mas3.length - 1; j > position; j--) {
				mas3[j] = mas3[j - 1];
			}

			mas3[position] = mas2[i]; // вставляем элемент
		}

		return mas3;
	}

	// только указание позиций вставки элементов массивов а и b(по условию задачи), без формирования новой последовательности
	public static void printSortPosition(int[] mas1, int[] mas2) {

		boolean check1;
		boolean check2;
		int i;
		int j;

		check1 = false;
		check2 = false;
		i = 0;
		j = 0;

		while (!check1 | !check2) {

			while ((mas1[i] <= mas2[j] | check2) & !check1) {
				System.out.println("a[" + i + "]");
				i++;

				if (i == mas1.length) {
					check1 = true;
					i--;
					break;
				}
			}

			while ((mas2[j] <= mas1[i] | check1) & !check2) {
				System.out.println("b[" + j + "]");
				j++;

				if (j == mas2.length) {
					check2 = true;
					j--;
					break;

				}
			}
		}
	}

	public static int inputInt(String a) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println(a);
		while (!sc.hasNextInt()) {
			System.out.println(">>");
			sc.nextLine();
		}

		return sc.nextInt();
	}

	public static void print(int[] mas, String text) {

		System.out.print(text);
		for (int i = 0; i < mas.length; i++) {
			System.out.print(mas[i] + " ");

		}
		System.out.println();
	}
}