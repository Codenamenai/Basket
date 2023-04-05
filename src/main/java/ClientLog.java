import au.com.bytecode.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {

    private List<String[]> prodNumAmountList = new ArrayList<>();


    public void log(int productNum, int amount) {

        prodNumAmountList.add(new String[]{String.valueOf(productNum), String.valueOf(amount)});

    }

    public void exportAsCSV(File txtFile) {
        prodNumAmountList.add(0, new String[]{"productNum" + " " + "amount"});
        try (CSVWriter writer = new CSVWriter(new FileWriter(txtFile, true))) {
            writer.writeAll(prodNumAmountList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String[]> getProdNumAmountList() {
        return prodNumAmountList;
    }
}

