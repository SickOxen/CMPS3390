package rguiles.a6;

public class Main {

    public static void main(String[] args) {
        Coin bitcoin = new Coin("bitcoin");
	    Coin ethereum = new Coin("ethereum");
	    Coin dogecoin = new Coin("dogecoin");

	    CoinGecko.updateCurrentPrice(bitcoin);
	    CoinGecko.updatePriceHistory(bitcoin, 7);

        CoinGecko.updateCurrentPrice(ethereum);
        CoinGecko.updatePriceHistory(ethereum, 7);

        CoinGecko.updateCurrentPrice(dogecoin);
        CoinGecko.updatePriceHistory(dogecoin, 7);

        System.out.println(bitcoin);
        System.out.println(ethereum);
        System.out.println(dogecoin);
    }
}
