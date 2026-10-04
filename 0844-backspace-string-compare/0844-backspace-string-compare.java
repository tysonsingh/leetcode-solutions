// class Solution {
//     public boolean backspaceCompare(String s, String t) {
//         int sEnd = s.length() - 1;
//         int tEnd = t.length() - 1;

//         int sSkip = 0;
//         int tSkip = 0;

//         while(sEnd >= 0 || tEnd >= 0) {

//             while(sEnd >= 0) {
//                 if(s.charAt(sEnd) == '#') {
//                     sSkip++;
//                     sEnd--;
//                 }
//                 else if( sSkip > 0) {
//                     sSkip--;
//                     sEnd--;
//                 }
//                 else {
//                     break;
//                 }
//             }

//             while( tEnd >= 0 ) { 
//                 if(t.charAt(tEnd) == '#') {
//                     tEnd--;
//                     tSkip++;
//                 }
//                 else if(tSkip > 0) {
//                     tEnd--;
//                     tSkip--;
//                 }
//                 else {
//                     break;
//                 }
//             }

//             if( sEnd >= 0 && tEnd >= 0) {
//                 if(s.charAt(sEnd) != t.charAt(tEnd)) {
//                     return false;
//                 }
//             }
//             else if(sEnd >= 0 || tEnd >= 0) {
//                 return false;
//             }

//             sEnd--;
//             tEnd--;
//         }

//         return true;
//     }
// }


class Solution {
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stT = new Stack<>();
        Stack<Character> stS = new Stack<>();

        for(char c : s.toCharArray()) {
            if(c != '#') {
                stS.push(c);
            }
            else {
                if(!stS.isEmpty()) {
                    stS.pop();
                }
            }
        }

        for(char c : t.toCharArray()) {
            if( c != '#') {
                stT.push(c);
            }
            else {
                if(!stT.isEmpty()) {
                    stT.pop();
                }
            }
        }

        return stS.equals(stT);
    }
}