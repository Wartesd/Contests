package TBank072026;

import java.util.Arrays;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        String s = scanner.next();

        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += (s.charAt(i) == 'R') ? 1 : -1;
        }

        if (totalSum == 0) {
            System.out.println(0);
            return;
        }

        int[] lastSeen = new int[2 * n + 1];
        Arrays.fill(lastSeen, -1);

        int currSum = 0;
        lastSeen[currSum + n] = 0;
        int minLen = n;

        for (int i = 1; i <= n; i++) {
            currSum += (s.charAt(i - 1) == 'R') ? 1 : -1;
            int target = currSum - totalSum;

            if (target + n >= 0 && target + n <= 2 * n) {
                if (lastSeen[target + n] != -1) {
                    minLen = Math.min(minLen, i - lastSeen[target + n]);
                }
            }
            lastSeen[currSum + n] = i;
        }

        if (minLen == n) {
            System.out.println(-1);
        } else {
            System.out.println(minLen);
        }
    }
}