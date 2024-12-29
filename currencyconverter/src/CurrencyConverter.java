import java.util.Scanner;
import java.util.HashMap;

public class CurrencyConverter {

    public static void main(String[] args) {
        HashMap<String, Double> currencies = loadCurrencyRates();
        showCurrencyOptions(currencies);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Choose the source currency by entering its corresponding number: ");
        int sourceChoice = scanner.nextInt();
        String sourceCurrency = getCurrencyByChoice(sourceChoice, currencies);

        if (sourceCurrency == null) {
            System.out.println("Invalid source currency choice. Exiting...");
            scanner.close();
            return;
        }

        System.out.print("Choose the target currency by entering its corresponding number: ");
        int targetChoice = scanner.nextInt();
        String targetCurrency = getCurrencyByChoice(targetChoice, currencies);

        if (targetCurrency == null) {
            System.out.println("Invalid target currency choice. Exiting...");
            scanner.close();
            return;
        }

        System.out.print("Enter the amount you want to convert: ");
        double amountToConvert = scanner.nextDouble();

        if (amountToConvert <= 0) {
            System.out.println("Please enter a valid positive amount.");
            scanner.close();
            return;
        }

        double convertedAmount = calculateExchangeRate(sourceCurrency, targetCurrency, amountToConvert, currencies);
        System.out.printf("Conversion result: %.2f %s\n", convertedAmount, targetCurrency);

        scanner.close();
    }

    private static HashMap<String, Double> loadCurrencyRates() {
        HashMap<String, Double> exchangeRates = new HashMap<>();
        exchangeRates.put("AUD", 1.36);
        exchangeRates.put("CAD", 1.27);
        exchangeRates.put("JPY", 131.2);
        exchangeRates.put("GBP", 0.74);
        exchangeRates.put("INR", 82.5);
        exchangeRates.put("EUR", 0.92);
        exchangeRates.put("USD", 1.0);
        return exchangeRates;
    }

    private static void showCurrencyOptions(HashMap<String, Double> currencies) {
        System.out.println("Select a currency from the list below:");
        int index = 1;
        for (String currency : currencies.keySet()) {
            System.out.println(index + ". " + currency);
            index++;
        }
    }

    private static String getCurrencyByChoice(int choice, HashMap<String, Double> currencies) {
        if (choice < 1 || choice > currencies.size()) {
            return null;
        }
        int counter = 1;
        for (String currency : currencies.keySet()) {
            if (counter == choice) {
                return currency;
            }
            counter++;
        }
        return null;
    }

    private static double calculateExchangeRate(String source, String target, double amount, HashMap<String, Double> currencies) {
        double sourceRate = currencies.get(source);
        double targetRate = currencies.get(target);
        return (amount * targetRate) / sourceRate;
    }
}
