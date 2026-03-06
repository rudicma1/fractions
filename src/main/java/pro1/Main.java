package pro1;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.*;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Path inputFolder = Paths.get("input");
        Path outputFolder = Paths.get("output");

        try {

            if (!Files.exists(outputFolder)) {
                Files.createDirectories(outputFolder);
            }

            Stream<Path> files = Files.list(inputFolder);

            files.forEach(file -> {

                if (file.toString().endsWith(".csv")) {

                    Path outFile = outputFolder.resolve(file.getFileName());
                    processCsv(file, outFile);

                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void processCsv(Path inputFile, Path outputFile) {

        try {

            BufferedReader reader = Files.newBufferedReader(inputFile);
            BufferedWriter writer = Files.newBufferedWriter(outputFile);

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("[;:=]");

                if (parts.length < 2) continue;

                String name = parts[0].trim();
                String expression = parts[1].trim();

                Fraction result = calculate(expression);

                writer.write(name + "," + result.toString());
                writer.newLine();
            }

            reader.close();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Fraction calculate(String expr) {

        String[] pieces = expr.split("\\+");

        Fraction total = new Fraction(BigInteger.ZERO, BigInteger.ONE);

        for (String part : pieces) {

            part = part.trim();

            Fraction f;

            if (part.endsWith("%")) {

                String number = part.replace("%", "").trim();
                BigInteger value = new BigInteger(number);

                f = new Fraction(value, BigInteger.valueOf(100));

            } else {

                String[] frac = part.split("/");

                BigInteger num = new BigInteger(frac[0].trim());
                BigInteger den = new BigInteger(frac[1].trim());

                f = new Fraction(num, den);
            }

            total = total.add(f);
        }

        return total.simplify();
    }

    static class Fraction {

        BigInteger numerator;
        BigInteger denominator;

        Fraction(BigInteger n, BigInteger d) {
            numerator = n;
            denominator = d;
        }

        Fraction add(Fraction other) {

            BigInteger newNum =
                    numerator.multiply(other.denominator)
                            .add(other.numerator.multiply(denominator));

            BigInteger newDen =
                    denominator.multiply(other.denominator);

            return new Fraction(newNum, newDen);
        }

        Fraction simplify() {

            BigInteger gcd = numerator.gcd(denominator);

            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);

            return this;
        }

        public String toString() {
            return numerator + "/" + denominator;
        }
    }
}