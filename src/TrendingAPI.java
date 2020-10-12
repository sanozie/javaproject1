import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.*;
import java.util.*;

public class TrendingAPI {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter either day or time: ");
        String time = scan.nextLine();

        TrendingRes trendingRes = null;
        try {
            trendingRes = getTrendingData(time);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methods

    /**
     * Returns data from API cal as TrendingRes class.
     * @param time time for which data is called for
     * @return TrendingRes
     * @throws IOException
     */
    public static TrendingRes getTrendingData(String time) throws IOException {
        final String API_KEY = "cc279df4edbe8668c4742d8271c03ee4";
        // Generate link
        String queryString = "https://api.themoviedb.org/3/trending/person/" + time
                + "?api_key=" + API_KEY;

        StringBuffer content = APIWrapper.callAPI(queryString);

        return new Gson().fromJson(content.toString(), TrendingRes.class);
    }

    public static String printDataAsString(TrendingRes data) {
        StringBuilder builder = new StringBuilder();
        for (Result res : data.results) {
            builder.append(res.title).append(",  ");
        }
        return builder.toString();
    }

    public class Result {

        @SerializedName("adult")
        @Expose
        public boolean adult;
        @SerializedName("backdrop_path")
        @Expose
        public String backdropPath;
        @SerializedName("genre_ids")
        @Expose
        public List<Integer> genreIds = null;
        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("original_language")
        @Expose
        public String originalLanguage;
        @SerializedName("original_title")
        @Expose
        public String originalTitle;
        @SerializedName("overview")
        @Expose
        public String overview;
        @SerializedName("poster_path")
        @Expose
        public String posterPath;
        @SerializedName("release_date")
        @Expose
        public String releaseDate;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("video")
        @Expose
        public boolean video;
        @SerializedName("vote_average")
        @Expose
        public double voteAverage;
        @SerializedName("vote_count")
        @Expose
        public int voteCount;
        @SerializedName("popularity")
        @Expose
        public double popularity;

    }

    public class TrendingRes {

        @SerializedName("page")
        @Expose
        public int page;
        @SerializedName("results")
        @Expose
        public List<Result> results = null;

    }
}
