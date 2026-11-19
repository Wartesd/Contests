package TBank072026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task5 {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        FastReader in = new FastReader();
        String firstToken = in.next();
        if (firstToken == null) return;
        int n = Integer.parseInt(firstToken);
        int q = in.nextInt();

        int[] maxR = new int[n + 2];

        for (int i = 0; i < q; i++) {
            int l = in.nextInt();
            int r = in.nextInt();
            if (r > maxR[l]) {
                maxR[l] = r;
            }
        }

        int towersCount = 0;
        int currEnd = 0;
        int nextEnd = 0;

        for (int i = 1; i <= n; i++) {
            nextEnd = Math.max(nextEnd, maxR[i]);

            if (i == currEnd + 1) {
                if (nextEnd < i) {
                    System.out.println("No");
                    return;
                }
                towersCount++;
                currEnd = nextEnd;
                if (currEnd >= n) {
                    break;
                }
            }
        }

        System.out.println("Yes");
        System.out.println(towersCount);
    }
}