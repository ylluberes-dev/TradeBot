import Indicators.EMA;
import Indicators.RSI;
import Indicators.SMA;
import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApiException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws BinanceApiException {
        try {
            Currency currency = new Currency("BTC");
            RSI rsi = new RSI(currency.getCandles(1000), 14);
            System.out.println("RSI: " + rsi.getTemp(currency.getPrice()));
            EMA ema = new EMA(currency.getCandles(1000), 7);
            System.out.println("EMA: " + ema.getTemp(currency.getPrice()));
            SMA sma = new SMA(currency.getCandles(15), 7);
            System.out.println("SMA: " + sma.getTemp(currency.getPrice()));
        } catch (
                BinanceApiException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        //Account toomas = new Account("Investor Toomas", 1000);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your API Key: ");
        CurrentAPI.get().setApiKey(sc.nextLine());
        System.out.println("Enter your Secret Key:");
        CurrentAPI.get().setSecretKey(sc.nextLine());
        JsonObject account = CurrentAPI.get().account();
        //Connection with Binance API and sout-ing some info.
        System.out.println("Maker Commission: " + account.get("makerCommission").getAsBigDecimal());
        System.out.println("Taker Commission: " + account.get("takerCommission").getAsBigDecimal());
        System.out.println("Buyer Commission: " + account.get("buyerCommission").getAsBigDecimal());
        System.out.println("Seller Commission: " + account.get("sellerCommission").getAsBigDecimal());
        System.out.println("Can Trade: " + account.get("canTrade").getAsBoolean());
        System.out.println("Can Withdraw: " + account.get("canWithdraw").getAsBoolean());
        System.out.println("Can Deposit: " + account.get("canDeposit").getAsBoolean());
    }
}
