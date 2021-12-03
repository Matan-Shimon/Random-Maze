import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // getting from the user the number of rows
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter num of rows: ");
        int m = scanner.nextInt();
        System.out.print("Enter num of columns: ");
        int n = scanner.nextInt();
        MazeGUI mazeGUI = new MazeGUI(m,n);
    }
}
