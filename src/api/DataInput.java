package api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DataInput {
    private static final DataInput DATA_INPUT = new DataInput();

    private DataInput() {
    }

    public static DataInput getDataInput() {
        return DATA_INPUT;
    }

    public String getStringInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public Integer getIntInput() {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public Date getDateInput() throws ParseException {
        Scanner input = new Scanner(System.in);
        SimpleDateFormat sDF = new SimpleDateFormat("dd/MM/yyyy");
        return sDF.parse(input.nextLine());
    }
}
