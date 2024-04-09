// TC: n*2^n where n is the length of the string (all substrings)
// SC: n*2^n

// Approach: BFS by removing one character at a time. If a valid string
// is found, stop BFS after processing that level.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class RemoveParanthesis {
    public List<String> removeInvalidParentheses(String s) {
        // to avoid repeated calculations
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        boolean validStringFound = false;
        List<String> res = new ArrayList<>();

        while (!queue.isEmpty() && !validStringFound) {
            int size = queue.size();

            while (size != 0) {
                String current = queue.poll();

                if (isValid(current)) {
                    res.add(current);
                    validStringFound = true;
                }

                for (int i = 0; i < current.length(); i++) {
                    if (Character.isAlphabetic(current.charAt(i))) {
                        continue;
                    }

                    StringBuilder sb = new StringBuilder(current);
                    sb.deleteCharAt(i);

                    if (!visited.contains(sb.toString())) {
                        queue.add(sb.toString());
                        visited.add(sb.toString());
                    }
                }

                size--;
            }
        }

        return res;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                continue;
            }
            if (c == '(') {
                count++;
            } else {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }

        return count == 0;
    }
}