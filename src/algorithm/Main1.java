package algorithm;


import entity.Teacher;

import java.util.*;

/**
 * @Description
 * @Tips
 * @Author yikai.wang
 * @Date 2024/9/21 13:48
 */
public class Main1 {

    public static String sort(String str) {
        // 先将英文字母收集起来
        List<Character> letters = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                letters.add(ch);
            }
        }
        // 将英文字母先排序好
        letters.sort(new Comparator<Character>() {
            public int compare(Character o1, Character o2) {
                return Character.toLowerCase(o1) - Character.toLowerCase(o2);
            }
        });
        // 若是非英文字母则直接添加
        StringBuilder result = new StringBuilder();
        for (int i = 0, j = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                result.append(letters.get(j++));
            }
            else {
                result.append(str.charAt(i));
            }
        }
        return result.toString();
    }

    //分披萨

    static int n;
    static int[] a;
    static int[][] dp;
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        dp = new int[n][n];
        for (int[] rows : dp){
            Arrays.fill(rows,-1);
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            total = Math.max(total,allocation((i + 1)%n, (i + n - 1)%n) + a[i]);
        }

        System.out.println(total);


    }

    private static int allocation(int l, int r) {
        if (dp[l][r] != -1){
            return dp[l][r];
        }

        if (a[l] > a[r]){
            l = (l+1)%n;
        }else {
            r = (r+n-1)%n;
        }

        if (l == r){
            dp[l][r] = a[l];
        }else {
            dp[l][r] = Math.max(a[l] + allocation((l + 1) % n, r), a[r] + allocation(l, (r + n - 1) % n));
        }
        return dp[l][r];

    }


}
