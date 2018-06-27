

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class AnalyzerTest {
    /*
     * =========================================
     * Primera parte
     * =========================================
     */

    /**
     * Prueba si el archivo existe.
     */
	@Test
	public void readFile_FileExist_True() {
		List<Sentence> listSentences = Analyzer.readFile("file.txt");
		assertTrue(!listSentences.isEmpty());
	}

    /**
     * Prueba si lee todas las lineas del archivo texto.
     */
    @Test
    public void readFile_NumberSentences_CorrectNumber() {
        List<Sentence> listSentences = Analyzer.readFile("file.txt");
        assertEquals(6, listSentences.size());
    }

    /**
     * Prueba si el archivo no existe.
     */
	@Test
	public void readFile_Nofile_True() {
		List<Sentence> listSentences = Analyzer.readFile("no_exist_file.txt");
		assertTrue(listSentences.isEmpty());
	}

    /**
     * Prueba si el archivo esta vacio.
     */
	@Test
	public void readFile_FileEmpty_Empty() {
		List<Sentence> listSentences = Analyzer.readFile("file_empty.txt");
		assertEquals("", 0, listSentences.size());
	}

    /**
     * Prueba si el archivo tiene formato incorrecto.
     */
	@Test
	public void readFile_WrongFormat_NumSentences() {
		List<Sentence> listSentences = Analyzer.readFile("file_format_wrong.txt");
		assertEquals(3, listSentences.size());
	}

    /**
     * Prueba si los límites de puntuación son correctos.
     */
	@Test
	public void readFile_WorgScoreBoundaries_NumSentences() {
		List<Sentence> listSentences =  Analyzer.readFile("file_format_limits.txt");
		assertEquals(2, listSentences.size());
	}



    /*
     * =========================================
     * Segunda Parte
     * =========================================
     */

    /**
     *Prueba la cantidad total correcta de palabras contadas leídas del archivo.
     */
	@Test
	public void allWords_RightTotalNumWords_NumWords() {
		List<Sentence> lista = Analyzer.readFile("file.txt");
        Set<Word> listaPalabras = Analyzer.allWords(lista);
		assertEquals(40, listaPalabras.size());
	}


    /**
     * Prueba la cantidad correcta de palabras contadas. Ej. "java"
     */
	@Test
	public void allWords_RightNumberWords_NumberWords() {
		List<Sentence> listSentences = Analyzer.readFile("file.txt");
        Set<Word> listWords = Analyzer.allWords(listSentences);

		int count = 0;
		for (Word word : listWords) {
			if("java".equals(word.getText())){
				count = word.getCount();
			}
		}

		assertEquals(2, count);
	}

    /**
     * Prueba el número correcto de número cumulativo de una palabra. Ej. "java".
     */
	@Test
	public void allWords_RightCummulativeNumber_NumberWords() {
		List<Sentence> lista = Analyzer.readFile("file.txt");
        Set<Word> listSentences = Analyzer.allWords(lista);

		int total = 0;
		for (Word word: listSentences) {
			if("java".equals(word.getText())){
				total = word.getTotal();
			}
		}
		assertEquals(4, total);
	}


	/*
	 * =========================================
	 * Tercera Parte
	 * =========================================
	 */

    /**
     *Prueba si la palabra del puntaje es correcta. Ej, "fun".
     */
	@Test
	public void calculateScores_RightScore_WordScore() {
		List<Sentence> listSentences = Analyzer.readFile("file.txt");
        Set<Word> listaPalabras = Analyzer.allWords(listSentences);
		Map<String, Double> mapWords = Analyzer.calculateScores(listaPalabras);

		double score = 0;
		for (Map.Entry<String, Double> entry : mapWords.entrySet()) {
			if ("fun".equals(entry.getKey())) {
				score = entry.getValue();
			}
		}
		assertEquals("0.8", String.valueOf(score));
	}

    /**
     * Prueba si el mapa está vacío si se proporciona un archivo vacío.
     */
	@Test
	public void calculateScores_EmptyMapEmptyFile_MapWords() {
		List<Sentence> listSentences = Analyzer.readFile("file_empty.txt");
        Set<Word> listWords = Analyzer.allWords(listSentences);

		Map<String, Double> mapWords = Analyzer.calculateScores(listWords);

		assertEquals(0, mapWords.size());
	}
	/*
	 * =========================================
	 * Cuarta Parte
	 * =========================================
	 */
}
