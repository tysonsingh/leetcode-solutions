class Solution {
    public boolean backspaceCompare(String s, String t) {
        int sEnd = s.length() - 1;
        int tEnd = t.length() - 1;

        int sSkip = 0;
        int tSkip = 0;

        while(sEnd >= 0 || tEnd >= 0) {
            // char sChar = s.charAt(sEnd);
            // char tChar = t.charAt(tChar);

            while(sEnd >= 0) {
                if(s.charAt(sEnd) == '#') {
                    sSkip++;
                    sEnd--;
                }
                else if( sSkip > 0) {
                    sSkip--;
                    sEnd--;
                }
                else {
                    break;
                }
            }

            while( tEnd >= 0 ) {
                if(t.charAt(tEnd) == '#') {
                    tEnd--;
                    tSkip++;
                }
                else if(tSkip > 0) {
                    tEnd--;
                    tSkip--;
                }
                else {
                    break;
                }
            }

            if( sEnd >= 0 && tEnd >= 0) {
                if(s.charAt(sEnd) != t.charAt(tEnd)) {
                    return false;
                }
            }
            else if(sEnd >= 0 || tEnd >= 0) {
                return false;
            }

            sEnd--;
            tEnd--;
        }

        return true;
    }
}