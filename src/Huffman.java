import java.util.*;
import java.io.*;

public class Huffman{


    public static void main(String args[]) throws IOException{
        Scanner in = new Scanner(System.in);
		System.out.println("Digite o nome do arquivo: ");
        String nome_arquivo = in.next();
        InputStream file = new FileInputStream(nome_arquivo); 
        BufferedReader br = new BufferedReader(new InputStreamReader(file)); 

        Hashtable <Character, Double> frequencia_total = new Hashtable <Character, Double>();

        String next;
        while((next = br.readLine()) != null){
            next = next + "\n";
            String[] caracteres = next.split("(?!^)");
            for(String c : caracteres){
                char carac = c.charAt(0);
                if(!frequencia_total.containsKey(carac)) frequencia_total.put(carac,1.0);
                else frequencia_total.put(carac, frequencia_total.get(carac) + 1);
            }
        }


        HuffmanEncoding he = new HuffmanEncoding(frequencia_total);
        HashMap<Character, String> encoding = he.returnEncoding();


        InputStream file2 = new FileInputStream(nome_arquivo); 
        BufferedReader br2 = new BufferedReader(new InputStreamReader(file2)); 

        BitSet code = new BitSet();
        int pos = 0;

        while((next = br2.readLine()) != null){
            next = next + "\n";
            String[] caracteres = next.split("(?!^)");
            for(String c : caracteres){
                String representacao = encoding.get(c.charAt(0));

                for(Character d : representacao.toCharArray()){
                    if(d.equals('1')) {
                        code.set(pos);
                    }
                    pos++;
                }
            }
        }



        // for(Character quei : encoding.keySet()){
        //     System.out.println(quei + ": " + encoding.get(quei));
        // }

        // for(int i = 0; i < pos - encoding.get('\n').length(); i++){
        //     System.out.print(code.get(i) ? '1' : '0');
        // }
        // System.out.println();

        OutputStream file_out = new FileOutputStream("saida.bin"); 
        file_out.write(code.toByteArray());

        PrintWriter writer = new PrintWriter("dicionario.txt", "UTF-8");
        for(Character c : encoding.keySet()){
            if(c == '\n') writer.println("barraN " + encoding.get(c));
            else if(c == ' ') writer.println("barra " + encoding.get(c));
            else writer.println(c + " " + encoding.get(c));
        }



        file.close();
        br.close();
        file2.close();
        br2.close();
        file_out.close();
        writer.close();
    }


}

