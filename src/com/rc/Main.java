package com.rc;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Set<String> permutations = Permutations.getPermutations("ABCDEFGHIJ");
        Set<String> permutations = Permutations.getPermutationsNonRecursive("ABCDEFGHIJ");
        System.out.println(permutations);
        System.out.println("No of permutations - " + permutations.size());
    }
}
