package rguiles.a6;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class that controls REST features from CoinGecko website
 */
public class CoinGecko {

    /**
     * Builds HTTP client
     */
    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    /**
     * Builds HTTP Request and updates an Array with coin price history
     * @param coin Instance of passed coin
     * @param days Number of requested days
     */
    public static void updatePriceHistory(Coin coin, int days) {
        coin.getHistoricalValues().getData().clear();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.coingecko.com/api/v3/coins/"
                        + coin.getName() + "/market_chart?vs_currency=usd&days="
                        + days + "&interval=daily"))
                .setHeader("User-Agent", "Java 11 HttpClient rguiles.a6")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            JSONArray priceArray = jsonObject.getJSONArray("prices");

            for(int loop = 0; loop < priceArray.length(); loop++){
                JSONArray tempArray = priceArray.getJSONArray(loop);
                double tempValue = tempArray.getDouble(1);
                coin.addHistoricalValue(loop - priceArray.length() + 1, tempValue);
            }

        } catch (IOException | InterruptedException e) {e.printStackTrace();}

    }

    /**
     * Builds HTTP Request and stores current price of coin
     * @param coin Instance of passed coin
     */
    public static void updateCurrentPrice(Coin coin){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.coingecko.com/api/v3/simple/price?ids=" + coin.getName() + "&vs_currencies=usd"))
                .setHeader("User-Agent", "Java 11 HttpClient rguiles.a6")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            double value = jsonObject.getJSONObject(coin.getName()).getDouble("usd");
            coin.setCurrentPrice(value);

        } catch (IOException | InterruptedException e) {e.printStackTrace();}
    }
}
