// Time Complexity : k^(n/k)
// Space Complexity : k*(n-k)

class Solution {
    List<String> res;
    public String[] expand(String s) {
        res = new ArrayList<>();
        List<List<Character>> processedString = new ArrayList<>();
        int n = s.length();
        int i=0;
        while(i<n) {
            List<Character> li = new ArrayList<>();
            if(s.charAt(i) == '{') {
                i++;
                while(s.charAt(i) != '}') {
                    if(s.charAt(i) != ',') {
                        li.add(s.charAt(i));
                    }
                    i++;
                }
            } else {
                li.add(s.charAt(i));
            }
            Collections.sort(li);
            processedString.add(li);
            i++;
        }
        backtrack(processedString, 0, new StringBuilder());
        String[] finalRes = new String[res.size()];
        for(int j=0;j<res.size();j++) {
            finalRes[j] = res.get(j);
        }
        return finalRes;
    }
    private void backtrack(List<List<Character>> processedString, int pivot, StringBuilder path) {
        if(pivot == processedString.size()) {
            res.add(path.toString());
            return;
        }
        List<Character> block = processedString.get(pivot);
        for(int i=0;i<block.size();i++) {
            //action
            int initLength = path.length();
            path.append(block.get(i));
            //recurse
            backtrack(processedString, pivot+1, path);
            //backtrack
            path.setLength(initLength);
        }
    }
} 