package com.tmdb.assignement;

import java.io.IOException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class API_parser {
	public API_parser() {
	}

	/**
	 * Retrieve list of movies based on genre
	 * 
	 * @param genre
	 *            genre
	 * @return list containing movie titles
	 */
	public List<String> getListOfMovies(String genre) {

		List<String> movies = new ArrayList<>();

		Handler getMovies = new Handler();
		OkHttpClient client = new OkHttpClient();
		String API_Key = "376a9fffdeddc83cbb3811bcc4f3fc8b";

		Request request = new Request.Builder().url("https://api.themoviedb.org/3/discover/movie?api_key=" + API_Key
				+ "&with_genres=" + genre + "&language=en-US&page=1").get().build();

		try {
			Response response = client.newCall(request).execute();

			String responseData = response.body().string();
			JSONObject jsonObject = new JSONObject(responseData);

			JSONArray moviesArray = jsonObject.getJSONArray("results");

			movies = getMovies.get_Movie_Titles(moviesArray);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return movies;
	}
}
