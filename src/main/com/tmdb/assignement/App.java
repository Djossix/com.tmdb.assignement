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
		setUpDB();
		
		while (true) {
			try {
				Scanner in = new Scanner(System.in);

				System.out.println("Welcome to WarcraftRental - choose from a variety of movies.\nMake your choice in the menu below.\n\n"
						+ "[M]ovies \n[Q]uit \n");
				String menu = in.nextLine();

				// Choice M, list genres menu.
				if (menu.equalsIgnoreCase("M")) {
					System.out.print("\nChoose genre.\n\n[T]hriller \n[C]omedy \n[D]rama \n[S]cifi \n");
					String genre = in.nextLine();
					
					System.out.println("Choose movie.\n\n");
					String choice;
					switch (genre.toLowerCase()) {
					case "t":
						for (int i = 0; i < thriller.size(); i++) {
							System.out.println(thriller.get(i).getId() + ": " + 
						thriller.get(i).getTitle());
						}
						break;
					case "c":
						for (int i = 0; i < comedy.size(); i++) {
							System.out.println(comedy.get(i).getId() + ": " + 
						comedy.get(i).getTitle());
						}
						break;
					case "d":
						for (int i = 0; i < drama.size(); i++) {
							System.out.println(drama.get(i).getId() + ": " + 
						drama.get(i).getTitle());
						}
						break;
					case "s":
						for (int i = 0; i < scifi.size(); i++) {
							System.out.println(scifi.get(i).getId() + ": " + 
						scifi.get(i).getTitle());
						}
						break;
					}

					choice = in.nextLine();
					System.out.println("Thank you for renting from WarcraftRental!");
					
					// Choice Q, quit menu and close connection.
				} else if (menu.equalsIgnoreCase("Q")) {
					dbLogic.closeDatabase();
					break;
				}

				dbLogic.closeDatabase();

			} catch (Exception e) {
				System.err.println("ERROR: " + e.getMessage());
				break;
			}
		}
	}

	/**
	 * Connect to our database and run the setup.
	 * Fetch the data and put it into lists,
	 * so we don't have to do a database-request each time.
	 */
	public static void setUpDB() {

		try {
			dbLogic = new Database("jdbc:postgresql://localhost:5432/postgres",
			        "postgres",
			        "Lx0e1utY");
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
