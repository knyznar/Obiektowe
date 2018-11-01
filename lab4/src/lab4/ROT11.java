package lab4;

public class ROT11 implements Algorithm{
    @Override
    public String crypt(String wordToCrypt){
        String cryptedWord = "";
        int charInAscii;
        for(int i=0; i < wordToCrypt.length(); ++i){
            charInAscii = wordToCrypt.charAt(i);
            if(charInAscii >= 65 && charInAscii <= 90){
                if(charInAscii+11 <= 90){
                    cryptedWord += (char)(charInAscii+11);
                }
                else{
                    cryptedWord += (char)(charInAscii+11-26);
                }
            }
            else if(charInAscii >= 97 && charInAscii <= 122){
                if(charInAscii+11 <= 122){
                    cryptedWord += (char)(charInAscii+11);
                }
                else{
                    cryptedWord += (char)(charInAscii+11-26);
                }
            }
            else{
                cryptedWord += charInAscii;
            }
        }
        return cryptedWord;
    }

    @Override
    public String decrypt(String wordToDecrypt){
        String decryptedWord = "";
        int charInAscii;
        for(int i=0; i < wordToDecrypt.length(); ++i){
            charInAscii = (int)wordToDecrypt.charAt(i);
            if(charInAscii >= 65 && charInAscii <= 90){
                if(charInAscii-11 >= 65){
                    decryptedWord += (char)(charInAscii-11);
                }
                else{
                    decryptedWord += (char)(charInAscii-11+26);
                }
            }
            else if(charInAscii >= 97 && charInAscii <= 122){
                if(charInAscii-11 >= 97){
                    decryptedWord += (char)(charInAscii-11);
                }
                else{
                    decryptedWord += (char)(charInAscii-11+26);
                }
            }
            else{
                decryptedWord += (char)charInAscii;
            }
        }
        return decryptedWord;
    }
}
