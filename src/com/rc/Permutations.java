package com.rc;

import java.util.*;

public class Permutations {

    /**
     * Find the set of all permutations of a string
     *
     * @param input Input string
     *
     * @return Set of string permutations
     */
    public static Set<String> getPermutations(String input) {
        List<Character> allCharacters = new ArrayList<>();
        for (Character c: input.toCharArray()) {
            allCharacters.add(c);
        }

        return getPermutationsInternal(allCharacters, "");
    }

    private static Set<String> getPermutationsInternal(List<Character> allCharacters, String prefix) {
        Set<String> result = new HashSet<>();
        if (allCharacters.size() == 1) {
            result.add(prefix + allCharacters.get(0).toString());
            return result;
        }

        for (int i = 0; i < allCharacters.size(); i++) {
            char prefixChar = allCharacters.get(i);
            List<Character> characters = new ArrayList<>();
            for (int j = 0; j < allCharacters.size(); j++) {
                if (j != i) {
                    characters.add(allCharacters.get(j));
                }
            }
            result.addAll(getPermutationsInternal(characters, prefix + prefixChar));
        }

        return result;
    }

    /**
     * This method also returns the set of all permutations of a string, but avoids using recursion by
     * emulating it through managing call stack frames explicitly. Done purely as an academic exercise - don't
     * see any apparent benefit. Uses the same amount of memory as a standard recursive solution.
     */
    public static Set<String> getPermutationsNonRecursive(String input) {
        List<Character> allCharacters = new ArrayList<>();
        for (Character c: input.toCharArray()) {
            allCharacters.add(c);
        }

        Set<String> result = new HashSet<>();
        Stack<StackFrame> callStack = new Stack<>();

        callStack.push(new StackFrame(allCharacters, ""));

        while (!callStack.empty()) {
            StackFrame sf = callStack.pop();
            result.addAll(getPermutationsInternal(sf, callStack));
        }

        return result;
    }

    private static Set<String> getPermutationsInternal(StackFrame sf, Stack<StackFrame> callStack) {
        List<Character> allCharacters = sf.allCharacters;
        String prefix = sf.prefix;
        Set<String> result = new HashSet<>();

        if (allCharacters.size() == 1) {
            result.add(prefix + allCharacters.get(0).toString());
            return result;
        }

        for (int i = 0; i < allCharacters.size(); i++) {
            char prefixChar = allCharacters.get(i);
            List<Character> characters = new ArrayList<>();
            for (int j = 0; j < allCharacters.size(); j++) {
                if (j != i) {
                    characters.add(allCharacters.get(j));
                }
            }
            callStack.push(new StackFrame(characters, prefix + prefixChar));
        }

        return result;
    }

    private static class StackFrame {
        final List<Character> allCharacters;
        final String prefix;

        public StackFrame(List<Character> allCharacters, String prefix) {
            this.allCharacters = allCharacters;
            this.prefix = prefix;
        }
    }
}
