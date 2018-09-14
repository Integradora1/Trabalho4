import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;
class DecompressorTests 
{
	Decompressor d;
	
	@BeforeEach
	void setUp() throws Exception 
	{
		d = new Decompressor();
	}
	
	@Test
	void TesDecompression1() throws IOException
	{
		String filename = "teste1.bin";
		String expected = "Lorem ipsum quis primis himenaeos auctor ultrices semper cras augue convallis lobortis leo porta augue vivamus dolor commodo conubia mollis consequat praesent ac. cras euismod praesent massa aliquet scelerisque aenean lacinia dictumst senectus commodo taciti metus mauris diam ad senectus nibh. enim nisl amet eget hac nisi enim semper nunc suspendisse pellentesque pretium placerat ut enim turpis id ligula molestie. est rutrum magna bibendum habitasse phasellus rhoncus ad sapien placerat auctor cursus urna pharetra sollicitudin auctor aliquam cras posuere etiam ac nibh viverra blandit eros augue elit conubia sociosqu est quisque suscipit nisi pulvinar.\n" + 
				"e";
		String text = d.decompress(filename);
		assertEquals(expected, text);
	}
	
	@Test
	void TesDecompression2() throws IOException
	{
		String filename = "teste2.bin";
		String expected = "Mary had a little lamb\n" + 
				"It's fleece was white as snow\n" + 
				"Everywhere the child went\n" + 
				"The little lamb was sure to go\n" + 
				"";
		String text = d.decompress(filename);
		assertEquals(expected, text);
	}
	
	@Test
	void TesDecompression3() throws IOException
	{
		String filename = "teste3.bin";
		String expected = "\n" + 
				"abc  12\n" + 
				"aa\n" + 
				"\n" + 
				"5\n" + 
				"aaa";
		String text = d.decompress(filename);
		assertEquals(expected, text);
	}
	
	@Test
	void TesDecompression4() throws IOException
	{
		String filename = "teste4.bin";
		String expected = "'Lost and Found' was written in response to discussions about how the authors build tension in the novel Lord of the Flies and the poem Playground Blues.\n" + 
				"o";
		String text = d.decompress(filename);
		assertEquals(expected, text);
	}
	
	@Test
	void TesDecompression5() throws IOException
	{
		String filename = "teste5.bin";
		String expected = "ahhu lkaps\n" + 
				"\n" + 
				"    \n" + 
				"(*ÂÂÂ*&*&\n" + 
				"\n" + 
				"8967 8ash*\n" + 
				"\n" + 
				"*-s&Â5sH\n" + 
				"p";
		String text = d.decompress(filename);
		assertEquals(expected, text);
	}

}
