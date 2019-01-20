package com.tmdb.assignement;

import java.sql.SQLException;
import java.util.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class App {
	
	static Database dbLogic;
	@Resource(name = "jdbc/tmdb-sql")
	static DataSource dataSource;
	private static List<Handler> thriller = new ArrayList<>(), comedy = new ArrayList<>(), drama = new ArrayList<>(), scifi = new ArrayList<>();
	
	public static void main(String[] args) {
		try {
			dbLogic = new Database("jdbc:postgresql://localhost:5432/postgres",
			        "postgres",
			        "Lx0e1utY");
			dbLogic.setUp();
		} catch (SQLException e) {
			System.err.println("Connection to database failed: " + e.getMessage());
		}
		while (true) {
			try {
				Scanner in = new Scanner(System.in);

				System.out.println("Welcome to WarcraftRental - rent movies and TV-series.\n Make your choice in the menu below.\n\n"
						+ "[M]ovies \n[T]V-Series \n[Q]uit \n");
				String menu = in.nextLine();

				// Choice M, list genres menu.
				if (menu.equalsIgnoreCase("M")) {
					System.out.print("\nChoose genre.\n\n[T]hriller \n[C]omedy \n[D]rama \n[S]cifi \n");
					String genre = in.nextLine();
					
					System.out.println("Choose movie.\n\n");
					String choice;
					switch (genre.toLowerCase()) {
					case "t":
						for (Handler movie : thriller) {
							System.out.println(movie.toString() + "\n");
							choice = in.nextLine();
						}
					case "c":
						for (Handler movie : comedy) {
							System.out.println(movie.toString() + "\n");
							choice = in.nextLine();
						}
					case "d":
						for (Handler movie : drama) {
							System.out.println(movie.toString() + "\n");
							choice = in.nextLine();
						}
					case "s":
						for (Handler movie : scifi) {
							System.out.println(movie.toString() + "\n");
							choice = in.nextLine();
						}
					}

					System.out.println("Thank you for renting from WarcraftRental!");

					// Choice T, list TV-series.
				} else if (menu.equalsIgnoreCase("T")) {

					// Choice Q, quit menu and close connection.
				} else if (menu.equalsIgnoreCase("Q")) {
					dbLogic.closeDatabase();
					break;
				}

				in.close();
				dbLogic.closeDatabase();

			} catch (Exception e) {
				System.err.println("ERROR: " + e.getMessage());
				break;
			}
		}
	}

	public static void setUpDB() {

		try {
			//dbLogic = new Database(dataSource);
			//dbLogic.setUp();
			
			dbLogic = new Database("jdbc:postgresql://localhost:5432/postgres",
			        "postgres",
			        "postgres");
			dbLogic.setUp();
		} catch (SQLException e) {
			System.err.println("Connection to database failed: " + e.getMessage());
		}
		
		thriller = dbLogic.getMovies("thriller");
		comedy = dbLogic.getMovies("comedy");
		drama = dbLogic.getMovies("drama");
		scifi = dbLogic.getMovies("scifi");
	}

}
