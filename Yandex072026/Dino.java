package Yandex072026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dino {
    private static final int[] OBS_LENGTHS = {0, 1, 2, 4};
    private static final int[] OBS_REWARDS = {0, 1, 3, 5};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int obsCount = Integer.parseInt(reader.readLine().trim());
        long[] obsStarts = new long[obsCount];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < obsCount; i++) {
            obsStarts[i] = Long.parseLong(st.nextToken());
        }

        int[] obsTypes = new int[obsCount];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < obsCount; i++) {
            obsTypes[i] = Integer.parseInt(st.nextToken());
        }

        long[] obsEnds = new long[obsCount];
        for (int i = 0; i < obsCount; i++) {
            int type = obsTypes[i];
            obsEnds[i] = obsStarts[i] + OBS_LENGTHS[type];
            if (i > 0 && obsEnds[i - 1] >= obsStarts[i]) {
                System.out.println(0);
                return;
            }
        }

        int jumpCount = Integer.parseInt(reader.readLine().trim());
        long[] jumpStarts = new long[jumpCount];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < jumpCount; i++) {
            jumpStarts[i] = Long.parseLong(st.nextToken());
        }

        long[] jumpDist = new long[jumpCount];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < jumpCount; i++) {
            jumpDist[i] = Long.parseLong(st.nextToken());
        }

        long finalScore = 0;
        int currentJump = 0;

        for (int i = 0; i < obsCount; i++) {
            while (currentJump + 1 < jumpCount && jumpStarts[currentJump + 1] <= obsStarts[i]) {
                currentJump++;
            }

            long jumpStart = jumpStarts[currentJump];
            long jumpEnd = jumpStart + jumpDist[currentJump];

            if (jumpStart <= obsStarts[i] && jumpEnd >= obsEnds[i]) {
                finalScore += OBS_REWARDS[obsTypes[i]];
            } else {
                finalScore--;
            }
        }

        System.out.println(Math.max(0L, finalScore));
    }
}

