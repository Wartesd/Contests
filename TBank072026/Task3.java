package TBank072026;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        String s = scanner.next();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int[][][] dp = new int[n][n][k + 1];

        for (int i = 0; i < n; i++) {
            for (int m = 0; m <= k; m++) {
                dp[i][i][m] = Math.max(0, a[i]);
            }
        }

        for (int len = 2; len <= n; len++) {
            for (int l = 0; l <= n - len; l++) {
                int r = l + len - 1;
                for (int m = 0; m <= k; m++) {
                    int res = Math.max(dp[l + 1][r][m], dp[l][r - 1][m]);

                    int inner = (l + 1 <= r - 1) ? dp[l + 1][r - 1][m] : 0;
                    if (s.charAt(l) == s.charAt(r)) {
                        res = Math.max(res, a[l] + a[r] + inner);
                    } else if (m > 0) {
                        int innerMismatch = (l + 1 <= r - 1) ? dp[l + 1][r - 1][m - 1] : 0;
                        res = Math.max(res, a[l] + a[r] + innerMismatch);
                    }

                    dp[l][r][m] = res;
                }
            }
        }

        System.out.println(dp[0][n - 1][k]);
    }
}