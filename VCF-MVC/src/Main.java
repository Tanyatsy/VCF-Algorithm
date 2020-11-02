import org.json.JSONException;

public class Main {

    public static void main(String[] args) throws JSONException {

        BoothsGenerator boothsGenerator = new BoothsGenerator();
        boothsGenerator.createVCF();

        System.out.println(boothsGenerator.jsonArray.toString());

    }
}