import java.io.*;
import java.util.Scanner;

public class Basket {
    public String products[];
    protected int price[];
    protected File file = new File("basket.txt");
    protected int basket[];

    public Basket() {
        this.products = new String[]{"Хлеб", "Масло", "Молоко"};
        this.price = new int[]{50, 200, 80};
        this.basket = new int[products.length];
    }
    public Basket(String[] products, int[] prices, int[] basket) {
        this.products = products;
        this.price = prices;
        this.basket = basket;
    }
    public void addToCart(int productNum, int amount) {
        basket[productNum - 1] += amount;
    }

    public void printCart() {
        int sumPurchase = 0;
        for (int i = 0; i < basket.length; i++) {
            if (basket[i] > 0) {
                int pricePurchase = price[i] * basket[i];
                sumPurchase += pricePurchase;
                System.out.println(products[i] + "-" + basket[i] + "шт" + " на сумму: " + pricePurchase
                );
            }
        }
        System.out.println("Сумма вашей корзины : " + sumPurchase);
    }

    public File getFile() {
        return file;
    }

    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(textFile)) {
            for (String product : products) {
                writer.print(product + " ");
            }
            writer.print("\n");
            for (int prices : price) {
                writer.print(prices + " ");
            }
            writer.print("\n");
            for (int i : basket) {
                writer.print(i + " ");
            }
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws Exception {
        try (InputStream ins = new FileInputStream(textFile)) {
            Scanner scanner = new Scanner(ins);
            String[] products = scanner.nextLine().trim().split(" ");
            String[] pricesI = scanner.nextLine().trim().split(" ");
            int[] price = new int[pricesI.length];
            for (int i = 0; i < pricesI.length; i++) {
                price[i] = Integer.parseInt(pricesI[i]);
            }
            String[] basketI = scanner.nextLine().trim().split(" ");
            int[] basket = new int[basketI.length];
            for (int i = 0; i < basketI.length; i++)
                basket[i] = Integer.parseInt(basketI[i]);
            return new Basket(products, price, basket);
        }
    }

    @Override
    public String toString() {
        System.out.println("Список товаров для покупки: ");
        for (int i = 0; i < products.length; i++) {
            System.out.println(" Товар: " + " " + products[i] + price[i] + " рублей.");
        }
        return " ";
    }


}

