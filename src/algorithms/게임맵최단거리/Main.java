package algorithms.게임맵최단거리;

import java.util.LinkedList;

import static java.lang.Math.min;


class Data {
    int y;
    int x;
    int count;

    public Data(int y, int x, int count) {
        this.y = y;
        this.x = x;
        this.count = count;
    }
}

public class Main {

    static int[][] dp = new int[100][100];


    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, -1, 0, 0};

    static int INF = 987654321;

    public static void main(String[] args) {
        boolean[][] visit = new boolean[100][100];
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                dp[i][j] = INF;
            }
        }

    }

    public int dfs(int[][] maps, boolean[][] visit, int y, int x) {
        if (y == maps.length - 1 && x == maps[0].length - 1) {
            return 0;
        }

        if (dp[y][x] == INF) {
            return dp[y][x];
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= maps.length || nx < 0 || nx >= maps[0].length) continue;

            if (maps[ny][nx] == 0) continue;

            if (visit[ny][nx]) continue;

            visit[ny][nx] = true;
            dp[y][x] = min(dp[y][x], dfs(maps, visit, ny, nx) + 1);
            visit[ny][nx] = false;
        }

        return dp[y][x];
    }

    public int bfs(int[][] maps) {
        LinkedList<Data> que = new LinkedList<>();
        boolean[][] visit = new boolean[maps.length][maps.length];
        int count = INF;

        que.offer(new Data(0, 0, 0));
        visit[0][0] = true;

        while (!que.isEmpty()) {
            Data cur = que.poll();

            if (cur.y == maps.length - 1 && cur.x == maps.length - 1) {
                count = min(count, cur.count);
                continue;
            }

            visit[cur.y][cur.x] = true;

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= maps.length || nx < 0 || nx >= maps[0].length) continue;

                if (maps[ny][nx] == 0) continue;

                if (visit[ny][nx]) continue;

                que.offer(new Data(ny, nx, cur.count + 1));
            }
        }

        return count;
    }
}
