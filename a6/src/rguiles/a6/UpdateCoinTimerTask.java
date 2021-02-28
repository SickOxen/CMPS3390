package rguiles.a6;

public class UpdateCoinTimerTask implements Runnable {

    private final Coin coin;
    public UpdateCoinTimerTask(Coin coin) {this.coin = coin;}

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
