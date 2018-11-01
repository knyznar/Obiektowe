package lab4;

public class Polibiusz implements Algorithm{
    final char tab[][] = {{'a','b','c','d','e'},
                        {'f','g','h','i','k'},  //j=i
                        {'l','m','n','o','p'},
                        {'q','r','s','t','u'},
                        {'v','w','x','y','z'}};

    @Override
    public String crypt(String wordToCrypt) {
        String cryptedWord="";
        wordToCrypt = wordToCrypt.toLowerCase();
        char temp;
        for(int i=0; i<wordToCrypt.length(); i++){
            temp = wordToCrypt.charAt(i);
            if(temp =='j')  cryptedWord += "24";
            else {
                for (int j = 0; j < 5; ++j) {
                    for (int k = 0; k < 5; k++) {
                        if (temp == tab[j][k]) {
                            cryptedWord += j + 1;
                            cryptedWord += k + 1;
                            break;
                        }
                    }
                }
            }
        }
        return cryptedWord;
    }

    @Override
    public String decrypt(String wordToDecrypt) {
        String cryptedWord="";
        for (int i = 0; i < wordToDecrypt.length(); i+=2) {
            cryptedWord += tab[Character.getNumericValue(wordToDecrypt.charAt(i))-1][Character.getNumericValue(wordToDecrypt.charAt(i+1))-1];

        }
        return cryptedWord;
    }
}
