package com.way361.other;

import java.util.*;
import java.text.DecimalFormat;

/**
 *
 * @author lulu6602
 */
public class Assignment3 {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		int botton = 0;
		double cost, Value, TotalDepreciation, temp = 0, AmtDepreciated, year, life, startValue;

		Scanner scanner = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("0.00");

		do {
			System.out.println("Please enter a number between 0 to 100000:");
			cost = scanner.nextDouble();
			if (cost <= 0) {
				System.out.println("Please enter a positive value for cost");
				botton = 1;
			} else if (cost > 100000) {
				System.out.println("Please enter a value for cost < 100000");
				botton = 1;
			} else {
				break;
			}

		} while (botton == 1);
		System.out.println("Enter estimated life ");

		year = scanner.nextDouble();

		System.out.printf("%-20s%-20s%-20s%-20s", "Year", "Startvalue", "Amt Depreciated", "Total Depreciation");
		System.out.println();
		startValue = cost;

		for (life = 1; year >= life; life++) {
			Value = cost;
			AmtDepreciated = Value * (2 / year);
			TotalDepreciation = temp + AmtDepreciated;
			cost = Value - AmtDepreciated;
			temp = TotalDepreciation;
			if (year == life) {
				AmtDepreciated = Value;
				TotalDepreciation = startValue;
			}
			String a = df.format(Value);
			String b = df.format(AmtDepreciated);
			String c = df.format(TotalDepreciation);

			System.out.printf("%-20s%-20s%-20s%-20s", life+"", "$"+a, "$"+b, "$"+c);
			System.out.println();
//			System.out.println((int) life + "\t\t$" + a + "\t\t$" + b + "\t\t$" + c);
		}

	}

}
