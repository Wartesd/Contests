package TBank072026;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNext()) return;

        String s = scanner.next();
        long totalCount = 0;
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int charIdx = s.charAt(i) - 'a';

            for (int j = charIdx + 1; j < 26; j++) {
                totalCount += freq[j];
            }

            freq[charIdx]++;
        }

        System.out.println(totalCount);
    }
}
