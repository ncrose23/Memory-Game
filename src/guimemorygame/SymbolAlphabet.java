package guimemorygame;
import java.io.*;
public class SymbolAlphabet implements Alphabet {
    public SymbolAlphabet() {
    }
	
    
    //non-static, so has to be used on an object
	public char[] toCharArray()
    {
        char[] SymbolAlphabet = new char[28];
        SymbolAlphabet[0] = 0x018B;
        SymbolAlphabet[1] = 0x018D;
        SymbolAlphabet[2] = 0x0189;
        SymbolAlphabet[3] = 0x0194;
        SymbolAlphabet[4] = 0x019B;
        SymbolAlphabet[5] = '!';
        SymbolAlphabet[6] = '@';
        SymbolAlphabet[7] = '#';
        SymbolAlphabet[8] = '$';
        SymbolAlphabet[9] = '%';
        SymbolAlphabet[10] = 0x019C;
        SymbolAlphabet[11] = '0';
        SymbolAlphabet[12] = '1';
        SymbolAlphabet[13] = '2';
        SymbolAlphabet[14] = '3';
        SymbolAlphabet[15] = '4';
        SymbolAlphabet[16] = '5';
        SymbolAlphabet[17] = '6';
        SymbolAlphabet[18] = '7';
        SymbolAlphabet[19] = '8';
        SymbolAlphabet[20] = '9';
        SymbolAlphabet[21] = 0x8361;
        SymbolAlphabet[22] = '+';
        SymbolAlphabet[23] = '&';
        SymbolAlphabet[24] = 0x8555;
        SymbolAlphabet[25] = '*';
        SymbolAlphabet[26] = '-';
        SymbolAlphabet[27] = '+';
        return SymbolAlphabet;
    }
	
	
}