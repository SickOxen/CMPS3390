package rguiles.a6;

/**
 * Main Driver Class for a6
 * @author Richard Guiles
 * @version 1.1
 */
public class Main {

    /**
     *  Calls in this function are used to run REST Debugger
     * @param args Arguments from the cmdline
     */
    public static void main(String[] args) {
        Coin bitcoin = new Coin("bitcoin");
	    Coin ethereum = new Coin("ethereum");

	    CoinGecko.updateCurrentPrice(bitcoin);
	    CoinGecko.updatePriceHistory(bitcoin, 7);

        CoinGecko.updateCurrentPrice(ethereum);
        CoinGecko.updatePriceHistory(ethereum, 7);

        System.out.println(bitcoin);
        System.out.println(ethereum);
    }
}
