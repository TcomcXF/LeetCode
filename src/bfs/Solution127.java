package bfs;

import java.util.*;

public class Solution127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        Map<String, List<String>> newWordList = trans(wordList);
        if (newWordList.get(endWord) == null) {
            return 0;
        }
        Map<String, Boolean> visited = new HashMap<>();
        for (String s : wordList) {
            visited.put(s, false);
        }
        LinkedList<String> toBeProcessed = new LinkedList<>();
        Map<String, String> prev = new HashMap<>();
        toBeProcessed.add(beginWord);
        visited.put(beginWord, true);
        while (toBeProcessed.size() > 0) {
            String currentWord = toBeProcessed.removeFirst();
            List<String> currentWordNeighbor = newWordList.get(currentWord);
            for (String s : currentWordNeighbor) {
                if (!visited.get(s)) {
                    toBeProcessed.addLast(s);
                    visited.put(s, true);
                    prev.put(s, currentWord);
                }
            }
        }
        if (prev.get(endWord) == null) {
            return 0;
        }
        String word = endWord;
        int result = 1;
        while (!word.equals(beginWord)) {
            result++;
            word = prev.get(word);
        }
        return result;
    }

    public Map<String, List<String>> trans(List<String> wordList) {
        Map<String, List<String>> newWordList = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            List<String> neighbourWordList = new ArrayList<>();
            for (int j = 0; j < wordList.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (isNeighborWord(wordList.get(i), wordList.get(j))) {
                    neighbourWordList.add(wordList.get(j));
                }
            }
            newWordList.put(wordList.get(i), neighbourWordList);
        }
        return newWordList;
    }

    public boolean isNeighborWord(String word1, String word2) {
        int flag = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                flag++;
            }
            if (flag == 2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> wordList1 = new ArrayList<>();
//        wordList1.add("hot");
//        wordList1.add("dot");
//        wordList1.add("dog");
//        wordList1.add("lot");
//        wordList1.add("log");
//        wordList1.add("cog");
//        System.out.println(new Solution127().ladderLength("hit", "cog", wordList1));
        wordList1.add("a");
        wordList1.add("b");
        wordList1.add("c");
        wordList1.add("d");
        wordList1.add("e");
        System.out.println(new Solution127().ladderLength("a", "e", wordList1));
    }
}
