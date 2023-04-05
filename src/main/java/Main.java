import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static File saveFile = new File("basket.json");

    public static void main(String[] args) throws Exception {

        Basket basket = null;
        if (saveFile.exists()) {
            basket = Basket.loadFromJSONFile(saveFile);
            basket.printCart();
        } else {basket=new Basket(basket.products, basket.price);}

            basket.addToCart(1, 2);
            basket.addToCart(2, 1);
            basket.addToCart(3, 2);
            basket.addToCart(3,9);
            basket.saveJSON((saveFile));
            basket.printCart();

            ClientLog clientLog = new ClientLog();
            clientLog.log(3, 2);
            clientLog.log(3, 2);
            clientLog.log(1, 9);
            clientLog.exportAsCSV(new File("clientLog.csv"));

    }
}

