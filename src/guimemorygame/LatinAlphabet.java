package guimemorygame;
import java.io.*;
public class LatinAlphabet implements Alphabet {
    public char[] toCharArray() {
        char[] LatinAlphabet = new char[28];
        LatinAlphabet[0] = 'A';
        LatinAlphabet[1] = 'B';
        LatinAlphabet[2] = 'C';
        LatinAlphabet[3] = 'D';
        LatinAlphabet[4] = 'E';
        LatinAlphabet[5] = 'F';
        LatinAlphabet[6] = 'G';
        LatinAlphabet[7] = 'H';
        LatinAlphabet[8] = 'I';
        LatinAlphabet[9] = 'J';
        LatinAlphabet[10] = 'K';
        LatinAlphabet[11] = 'L';
        LatinAlphabet[12] = 'M';
        LatinAlphabet[13] = 'N';
        LatinAlphabet[14] = 'O';
        LatinAlphabet[15] = 'P';
        LatinAlphabet[16] = 'Q';
        LatinAlphabet[17] = 'R';
        LatinAlphabet[18] = 'S';
        LatinAlphabet[19] = 'T';
        LatinAlphabet[20] = 'U';
        LatinAlphabet[21] = 'V';
        LatinAlphabet[22] = 'W';
        LatinAlphabet[23] = 'X';
        LatinAlphabet[24] = 'Y';
        LatinAlphabet[25] = 'Z';
        LatinAlphabet[26] = '>';
        LatinAlphabet[27] = '<';
        return LatinAlphabet;
    }
}