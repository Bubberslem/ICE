

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {

    public static ArrayList<String> readPlayerData(String path) {
        ArrayList<String> data = new ArrayList();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header

            while (scan.hasNextLine()) {
                String line = scan.nextLine(); // "tess, 40000"
                data.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return data;
    }

    public static void saveData(List<String> items, String path, String header) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(header + "\n");
            for (String s : items) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("something went wrong when writing to file");
        }
    }

    public String[] readGameData(String path, int length) {
        String[] data = new String[length];
        File file = new File(path);
        int counter = 0;

        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                data[counter] = line;
                counter++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return data;

    }
    public static List<Weapon> loadWeapons(String filePath) {
        List<Weapon> weapons = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.nextLine(); // Skip the header row

            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                String name = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());
                int damage = Integer.parseInt(parts[2].trim());
                int durability = Integer.parseInt(parts[3].trim());

                weapons.add(new Weapon(name, value, damage, durability));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Weapons file not found: " + filePath);
        } catch (NumberFormatException e) {
            System.out.println("Invalid data format in weapons file.");
        }

        return weapons;
    }
    public static List<Armor> loadArmor(String filePath) {
        List<Armor> armors = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.nextLine(); // Skip the header row

            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                String name = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());
                int defense = Integer.parseInt(parts[2].trim());
                int durability = Integer.parseInt(parts[3].trim());

                armors.add(new Armor(name, value, defense, durability));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Armor file not found: " + filePath);
        } catch (NumberFormatException e) {
            System.out.println("Invalid data format in armor file.");
        }

        return armors;
    }

    public static List<Loot> loadLoot(String filePath) {
        List<Loot> lootItems = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filePath));
            scanner.nextLine(); // Skip the header row

            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length < 3) continue; // Skip incomplete lines

                String name = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());
                String description = parts[2].trim();

                lootItems.add(new Loot(name, value, description));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Loot file not found: " + filePath);
        } catch (NumberFormatException e) {
            System.out.println("Invalid data format in loot file.");
        }

        return lootItems;
    }
}
