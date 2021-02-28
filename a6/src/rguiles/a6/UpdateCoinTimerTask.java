package rguiles.a6;

/**
 * Timer Class that continuously updates until cancelled
 */
public class UpdateCoinTimerTask implements Runnable {

    private final Coin coin;

    /**
     * Default Constructor
     * @param coin sets coin instance
     */
    public UpdateCoinTimerTask(Coin coin) {this.coin = coin;}

    /**
     * Function that updates current price of coin if it has changed
     */
    @Override
    public void run(){
        System.out.println("Checking for update on " + coin.getName());
        double curValue = this.coin.getCurrentPrice();
        CoinGecko.updateCurrentPrice(this.coin);
        if (curValue != this.coin.getCurrentPrice()){
            System.out.println("-------------------------------- Price Changed "
                    + this.coin.getName() + " " + curValue + "----> " + this.coin.getCurrentPrice());
        }
    }
}
