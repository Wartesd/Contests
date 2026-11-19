package TBank072026;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        int[] counts = new int[1001];

        for (int i = 0; i < n; i++) {
            counts[scanner.nextInt()]++;
        }

        int oddCount = 0;
        for (int count : counts) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }

        if (oddCount <= 1) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}