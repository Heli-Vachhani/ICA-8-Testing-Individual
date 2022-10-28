import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class UrinalsTest {

    String author = " ---> HELI VACHHANI <--- ";
    String input_flname = "src/urinals.dat";

    @Test
    void goodString() {
        assertAll(
                () -> assertEquals(false, Urinals.goodString("1110001")),
                () -> assertEquals(true, Urinals.goodString("1010101"))
        );
        System.out.println(author + "GoodString >> Test Cases >> TEST SUCCESS");
    }

    @Test
    void countUrinals() {
        assertAll(
                () -> assertEquals(1, Urinals.countUrinals("10001")),
                () -> assertEquals(0, Urinals.countUrinals("1001")),
                () -> assertEquals(3, Urinals.countUrinals("00000")),
                () -> assertEquals(2, Urinals.countUrinals("0000")),
                () -> assertEquals(1, Urinals.countUrinals("01000")),
                () -> assertEquals(-1, Urinals.countUrinals("011"))
        );
        System.out.println(author + "CountUrinals Test >> Test Cases >> TEST SUCCESS");
    }

    @Test
    void getOutputFilename() {
        String g_flname = Urinals.getOutputFilename();
        String name_init = g_flname.substring(4, 8);
        String name_last = g_flname.substring(g_flname.lastIndexOf('.'), g_flname.length());
        String name_digit = g_flname.substring(8, g_flname.lastIndexOf('.'));
        int n = Integer.parseInt(name_digit);

        assertAll(
                () -> assertEquals("rule",name_init),
                () -> assertEquals(".txt",name_last)
        );
        System.out.println(author + "Write File Tests >> Bad File Name >> TEST SUCCESS");
    }

    @Test
    void writeToFile() {
        File dir = new File("src/");
        boolean flag = true;
        String[] files = dir.list();
        for(int i=0; i<files.length; i++) {
            for(int j=i+1; j<files.length; j++)
            {
                if (files[i].equals(files[j]) && files[i].equals("urinals.dat")) {
                    flag = false;
                    break;
                }
            }
        }
        assertTrue(flag);
        System.out.println(author + "Write File Tests >> Duplicate File >> TEST SUCCESS");
    }

    @Test
    void readFromFile() {
        File file = new File(input_flname);
        assertTrue(file.exists());
        System.out.println(author + "Read File Tests >> File Exists >> TEST SUCCESS");

        assertTrue(file.length() != 0);
        System.out.println(author + "Read File Tests >> File Empty >> TEST SUCCESS");
    }
}