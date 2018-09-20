import java.util.*;
import java.io.*;

public class Compressor
{
    public Compressor()
    {
    	
    }
    
    public HashMap<Character, String> encode(String filename) throws IOException
    {
    	InputStream file = new FileInputStream(filename); 
        Scanner in = new Scanner(new InputStreamReader(file)); 
        Hashtable <Character, Double> frequencia_total = new Hashtable <Character, Double>();
        String next;
        while(in.hasNextLine())
        {
        	next = in.nextLine();
        	if(in.hasNextLine() || next.isEmpty()) next = next + "\n";
            String[] caracteres = next.split("(?!^)");
            for(String c : caracteres){
                char carac = c.charAt(0);
                if(!frequencia_total.containsKey(carac)) frequencia_total.put(carac,1.0);
                else frequencia_total.put(carac, frequencia_total.get(carac) + 1);
            }
        }
        file.close();
        in.close();
        HuffmanEncoding he = new HuffmanEncoding(frequencia_total);
        HashMap<Character, String> encoding = he.returnEncoding();        
        return encoding;
    }
    
    public void writeOutputFiles(String filename, HashMap<Character, String> encoding) throws IOException
    {
    	InputStream file = new FileInputStream(filename); 
        Scanner in = new Scanner(new InputStreamReader(file)); 
        String next;
        BitSet code = new BitSet();
        int pos = 0;
        while(in.hasNextLine())
        {
        	next = in.nextLine();
        	if(in.hasNextLine() || next.isEmpty()) next = next + "\n";
            String[] caracteres = next.split("(?!^)");
            for(String c : caracteres){
                String representacao = encoding.get(c.charAt(0));
                for(Character d : representacao.toCharArray())
                {
                    if(d.equals('1')) 
                    {
                        code.set(pos);
                    }
                    pos++;
                }
            }
        }
        String outName = filename.split("\\.")[0] + ".bin";
        OutputStream file_out = new FileOutputStream(outName); 
        file_out.write(code.toByteArray());
        String dicFilename = "dicionario_" + filename.split("\\.")[0] + ".txt";
        PrintWriter writer = new PrintWriter(dicFilename, "UTF-8");
        writer.println(pos);
        for(Character c : encoding.keySet())
        {
            if(c == '\n') writer.println("barraN " + encoding.get(c));
            else if(c == ' ') writer.println("barra " + encoding.get(c));
            else writer.println(c + " " + encoding.get(c));
        }        
        file.close();
        in.close();
        file_out.close();
        writer.close();       
    }
}