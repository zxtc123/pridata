package leetcode.solution;

import java.util.ArrayList;
import java.util.List;

public class Partition {
    int len;

    public List<List<String>> partition(String s) {
        List<List<String>> parts = new ArrayList<>();
        len = s.length();
        for (int i = 0; i < len; i++) {
            parts.add(part(s, i));
        }
        return parts;
    }

    private List<String> part(String s, int i) {
        List<String> list = new ArrayList<>();
        if (s.length() == 1) {
            list.add(s);
            return list;
        }
        int j = s.length() - 1;
        while (j > i && !isPart(s, i, j)) j--;
        list.add(s.substring(i, ++j));
        list.addAll(part(s.substring(j), j));
        return list;
    }

    private boolean isPart(String s, int l, int r) {
        if (l < 0 || r > s.length()) return false;
        if (s.charAt(l) == s.charAt(r)) {
            if (l + 1 >= r) return true;
            isPart(s, l++, r--);
        }
        return false;
    }

    public static void main(String[] args) {
        Partition p = new Partition();
        System.out.println(p.partition("aab"));
    }
}
