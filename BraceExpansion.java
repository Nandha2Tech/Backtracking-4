// Time Complexity : O(k^(N/k))  -> k is the average length of the group and N is the length of the input string
// Space Complexity : O(N/k)
// Did this code successfully run on Leetcode : Yes

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BraceExpansion {
    List<String> result;
    List<List<Character>> groups;
    public String[] expand(String s) {
        result = new ArrayList<>();
        groups = new ArrayList<>();
        int i=0;
        while(i < s.length()){
            List<Character> group = new ArrayList<>();
            char c = s.charAt(i);
            if(c == '{'){
                i++;
                while(s.charAt(i)!= '}'){
                    if(s.charAt(i) != ',')
                        group.add(s.charAt(i));
                    i++;
                }
            }else{
                group.add(c);
            }
            i++;
            Collections.sort(group);  // k log k
            groups.add(group);
        }

        helper(0, new StringBuilder());   // find the combinations

        String[] reArr = new String[result.size()];
        for(int j=0; j<reArr.length; j++){
            reArr[j] = result.get(j);
        }
        return reArr;
    }

    private void helper(int idx, StringBuilder sb){

        if(idx == groups.size()){
            result.add(sb.toString());
            return;
        }
        List<Character> li = groups.get(idx);

        for(int i=0; i<li.size(); i++){
            sb.append(li.get(i));   //action
            helper(idx+1,sb);   //recurse
            sb.setLength(sb.length()-1);  // backtrack;
        }

    }
}