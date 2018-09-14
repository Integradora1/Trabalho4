import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Huffman 
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Selecione uma das opções abaixo:");
		System.out.println("1 - Comprimir um arquivo .txt");
		System.out.println("2 - Descomprimir um arquivo .bin");
		System.out.println("Outro - Sair");
		String op = in.next();
		try
		{
			if(op.equals("1"))
			{
				Compressor c = new Compressor();
				System.out.println("Digite o nome do arquivo: ");
		        String filename = in.next();
		        if(!filename.split("\\.")[1].equals("txt"))
		        {
		        	throw new IllegalArgumentException("Somente arquivos de extensão .txt suportados");
		        }
		        HashMap<Character, String> encoding = c.encode(filename);
		        c.writeOutputFiles(filename, encoding);
			}
			else if(op.equals("2"))
			{
				Decompressor d = new Decompressor();
				System.out.println("Digite o nome do arquivo: ");
			    String filename = in.next();	     
			    if(!filename.split("\\.")[1].equals("bin"))
			    {
			    	throw new IllegalArgumentException("Somente arquivos de extensão .bin suportados");
			    }		
			    String msg = d.decompress(filename);
			    d.writeInFile(msg, filename);
			}
		}
		catch(IOException e)
		{
			System.out.println("Algo deu errado: " + e.getMessage());
		}
		
	}
}
