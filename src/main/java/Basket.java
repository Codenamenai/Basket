import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String products[];
    protected int price[];

    protected int quantities[];

    public Basket() {
    }

    ;

    public Basket(String[] products, int[] price) {
        this.products = new String[]{"Хлеб", "Масло", "Молоко"};
        this.price = new int[]{50, 200, 80};
        this.quantities = new int[this.products.length];
    }

    public Basket(String[] products, int[] price, int[] quantities) {
        this.products = products;
        this.price = price;
        this.quantities = quantities;
    }

    public void addToCart(int productNum, int amount) {
        quantities[productNum] += amount;
    }

    public void printCart() {
        int sumPurchase = 0;
        for (int i = 0; i < products.length; i++) {
            if (quantities[i] > 0) {
                int pricePurchase = price[i] * quantities[i];
                sumPurchase += pricePurchase;
                System.out.println(products[i] + "-" + quantities[i] + "шт" + " на сумму: " + pricePurchase
                );
            }
        }
        System.out.println("Сумма вашей корзины : " + sumPurchase);
    }

   /* public File getFile() {
        return file;
    }*/

    public void saveTxt(File textFile) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(textFile)) {
            for (String product : products) {
                writer.print(product + " ");
            }
            writer.print("\n");
            for (int prices : price) {
                writer.print(prices + " ");
            }
            writer.print("\n");
            for (int quantity : quantities) {
                writer.print(quantity + " ");
            }
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws FileNotFoundException {
        Basket basket = new Basket();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {
            String productsStr = bufferedReader.readLine();
            String priceStr = bufferedReader.readLine();
            String quantitiesStr = bufferedReader.readLine();
            basket.products = productsStr.split(" ");
            basket.price = Arrays.stream(priceStr.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
            basket.quantities = Arrays.stream(quantitiesStr.split(" "))
                    .map(Integer::parseInt)
                    .mapToInt(Integer::intValue)
                    .toArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }

    public void saveBin(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromBinFile(File file) {
        Basket basket = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }

    /*
      public String toString() {
          System.out.println("Список товаров для покупки: ");
          for (int i = 0; i < products.length; i++) {
              System.out.println(" Товар: " + " " + products[i] + price[i] + " рублей.");
          }
          return " ";
      }*/
    public void saveJSON(File file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(this);
            writer.print(json);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Basket loadFromJSONFile(File file) {
        Basket basket ;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            Gson gson = new Gson();
            basket = gson.fromJson(builder.toString(), Basket.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }


}

