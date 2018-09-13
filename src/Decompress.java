import java.util.*;
import java.io.*;
import java.nio.file.*;


public class Decompress{
	
	public static void main(String args[]) throws IOException{
		
		HashMap<String,Character> code = new HashMap<>();

		Path path1 = Paths.get("saida.bin");
		byte[] byts =  Files.readAllBytes(path1);
		int tamanho = byts.length * 8;
		BitSet bits = new BitSet(tamanho);
		bits = BitSet.valueOf(byts);
		for(int i = 0; i < tamanho; i++){
            //System.out.print(bits.get(i) ? '1' : '0');
        }

        InputStream file = new FileInputStream("dicionario.txt"); 
        BufferedReader br = new BufferedReader(new InputStreamReader(file)); 
        
        
        String next;
        while((next = br.readLine()) != null){
        	
            String[] elem = next.split(" ");
            
            if (elem[0].equals("barraN")) {
            	code.put( elem[1], '\n' );
            }else if(elem[0].equals("barra")) {
            	code.put( elem[1], ' ' );
            }
            else {
            	code.put(elem[1], elem[0].charAt(0));
            }
        }
        
        System.out.println();
        String message = "";
        String word = "";
        for(int i = 0; i < tamanho; i++) {
        	word = word + (bits.get(i) ? '1' : '0');
        	if (code.containsKey(word)) {
        		message= message + code.get(word);
        		word = "";
        	}
        }
		
        System.out.println();
        System.out.println(message);

        PrintWriter writer = new PrintWriter("decodificado", "UTF-8");
        writer.println(message);
        
        //System.out.println(code);
	}
}