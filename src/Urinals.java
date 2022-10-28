//author: Heli Vachhani

import java.io.File;
import java.util.Scanner;
import java.io.*;
//import static org.junit.jupiter.api.Assertions.assertTrue;


public class Urinals{
    static Boolean goodString(String str)
    {
        int len = str.length();
        for(int i=1; i<len; i++)
        {
            if(str.charAt(i)=='1' && str.charAt(i-1)=='1')
            {
                return false;
            }

        }
        return true;
    }
    int countUrinals(String str)
    {   int count=0;
        int len = str.length();
        if(!goodString(str))
            return -1;

        for(int i=0; i<len; i++)
        {
            if(str.charAt(i)=='0')
            {
                if((i-1<0 || str.charAt(i-1)=='0') && (i+1>=len || str.charAt(i+1)=='0'))
                {
                    count++;
                    i++;
                }
            }
        }
        return count;

    }

    String getOutputFilename()
    {

        String filename = "src/rule.txt";
        int count=0;
        File fl = new File(filename);

        while(true)
        {
            if(fl.exists())
            {
                count++;
                filename = filename.substring(0,8) + count + ".txt";
                fl = new File(filename);
            }
            else
            {
                break;
            }
        }
        return filename;
    }
    void writeToFile(int a, String filename)
    {
        try
        {

            FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            Integer y = a;
            bw.write(y.toString());
            bw.newLine();
            bw.close();

        }
        catch(IOException e)
        {
            System.out.println("I/O Exception!");
        }
    }
    void readFromFile(String filename)
    {
        String f = "src/" + filename;
        try {
            File fl = new File(f);
            Scanner scr = new Scanner(fl);
            String str = this.getOutputFilename();
            while (scr.hasNextLine()) {
                String line = scr.nextLine();
                int a = this.countUrinals(line);
                this.writeToFile(a,str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Urinals obj = new Urinals();
        System.out.println("Import the input from file (or) console ?");
        System.out.println("1. Import from Console");
        System.out.println("2. Import from File");
        System.out.print("Enter your choice here = ");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        switch(option)
        {
            case 1:
            {
                String s;
                char c='N';
                String str = obj.getOutputFilename();
                do {
                    System.out.println("Enter string: ");
                    s = sc.next();
                    int ans = obj.countUrinals(s);
                    obj.writeToFile(ans,str);
                    System.out.println("Do you wish to continue Input (Y/N) ?");
                    c = sc.next().charAt(0);
                }while(c=='Y');
            }

            case 2:
            {
                System.out.println("Enter name of Input file: ");
                String str = sc.next();
                obj.readFromFile(str);
                break;
            }

        }
    }
}