import java.util.*;
import java.io.*;
import java.nio.file.*;


public class Decompressor
{
	
	public Decompressor()
	{
		
	}
	
	public String decompress(String filename) throws IOException
	{
		HashMap<String,Character> code = new HashMap<>();		
		Path path1 = Paths.get(filename);
		byte[] byts =  Files.readAllBytes(path1);
		BitSet bits = new BitSet();
		bits = BitSet.valueOf(byts);
		String dicFilename = "dicionario_" + filename.split("\\.")[0] + ".txt";
        InputStream file = new FileInputStream(dicFilename); 
        Scanner in = new Scanner(new InputStreamReader(file));       
        String next;
        int tam = 0;
        if(in.hasNext()) tam = Integer.parseInt(in.nextLine());
        while(in.hasNext())
        {    
        	next = in.nextLine();
            String[] elem = next.split(" ");            
            if (elem[0].equals("barraN")) 
            {
            	code.put( elem[1], '\n' );
            }
            else if(elem[0].equals("barra")) 
            {
            	code.put( elem[1], ' ' );
            }
            else
            {
            	code.put(elem[1], elem[0].charAt(0));
            }
        }
        String message = "";
        String word = "";
        for(int i = 0; i < tam; i++) 
        {
        	word = word + (bits.get(i) ? '1' : '0');
        	if (code.containsKey(word)) 
        	{
        		message = message + code.get(word);
        		word = "";
        	}
        } 
        return message;
	}
	
	public void writeInFile(String message, String filename) throws FileNotFoundException
	{
		String outFilename = "decodificado_" + filename.split("\\.")[0] + ".txt";
        PrintWriter writer = new PrintWriter(outFilename);
        writer.print(message);
        writer.close();
	}
}