import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // getting from the user the number of rows

        // Project creators: Matan Yarin Shimon - 314669342 & Netanel Levine - 312512619
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter num of rows: ");
        int m = scanner.nextInt();
        System.out.print("Enter num of columns: ");
        int n = scanner.nextInt();
        MazeGUI mazeGUI = new MazeGUI(m,n);
    }
}
