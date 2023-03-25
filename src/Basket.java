import java.io.*;
import java.util.Scanner;

public class Basket {
    public String products[];
    protected int price[];
    protected File file = new File("basket.bin");
    protected int basket[];

    public Basket() {
        this.products = new String[]{"Хлеб", "Масло", "Молоко"};
        this.price = new int[]{50, 200, 80};
        this.basket = new int[products.length];
    }

    public Basket(String[] products, int[] price, int[] basket) {
        this.products = products;
        this.price = price;
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

    public void saveBin(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(new Basket(products, price, basket));
        }
    }

    public static Basket loadFromBinFile(File file) throws Exception {
        Basket basket = null;
        try (ObjectInputStream ins = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) ins.readObject();
            return basket;
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

