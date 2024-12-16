import java.util.Scanner;

public class Path {

    private Scanner scanner;

    public Path() {
        scanner = new Scanner(System.in);
    }

    public void startPath(String saveSlot) {
        displayWelcomeMessage();

        System.out.println("=== Your Adventure Begins! ===");
        System.out.println("You are in a destroyed city filled with rubble, fire and the smell of concrete. A billboard lies before you, written on it states Welcome to City 17. three paths lie ahead: ");
        System.out.println("1. Take the path through the abandoned parking lot (Strange sounds are heard from within).");
        System.out.println("2. Follow along the destroyed roads of the city (The roads are very open a visible from a far).");
        System.out.println("3. Walk towards the Combine base (It looks destroyed).");
        System.out.println("Choose a path: ");

        String choice = scanner.nextLine();
        hanndleChoice(choice, saveSlot);

    }
    private void displayWelcomeMessage() {
        System.out.println("Welcome to City 17");
        System.out.println("You're a rebellious fighter known throughout planet earth, however humanity failed and you lost the war for the world!");
        System.out.println("Your name is Tess Freeman, known for you wisdom and athletic abilities. You're equipped with an standard glock 18 and a crowbar");
        System.out.println("Follow your heart and save the world from the evil combine slavers.");
        System.out.println();
    }

    private void hanndleChoice(String choice, String saveSlot) {
        switch (choice) {
            case "1":
                System.out.println("You head ");
        }
    }

}
