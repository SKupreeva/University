package Logic;

import java.rmi.RemoteException;

public class TwoVowelsChanging implements TwoVowelsInterface {

    @Override
    public String changeString(String input) throws RemoteException {
        var result = new StringBuilder();
        var noVowels = "No double vowels in string!\n";
        var vowels = "aeiouyAEIOUYауоыиэяюеёАУОЫИЭЯЮЕЁ".toCharArray();
        var flag = 0;
        for(var i = 0; i < input.length()-1; i++){
            flag = 0;
            for (char c : vowels) {
                if (input.charAt(i) == c) {
                    for (char vowel : vowels) {
                        if (input.charAt(i + 1) == vowel){
                            result.append(input.charAt(i)).append(" ");
                            flag++;
                        }
                    }
                }
            }
            if(flag == 0){
                result.append(input.charAt(i));
            }
        }
        result.append(input.charAt(input.length()-1));
        if(result.toString().equals(input)) return noVowels + result.toString();
        else return result.toString();
    }
}
