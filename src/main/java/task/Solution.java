package task;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<String>> partition(String s) {
        int length = s.trim().length();
        validateInput(s, length);

        return new ArrayList<List<String>>();
    }

    private static void validateInput(String s, int length) {
        if (s == null ) throw new IllegalStateException("Given string is out of scope");

        if(length < 1 || length > 20){
            throw new IllegalStateException("Given string is out of scope");
        }
    }
}


