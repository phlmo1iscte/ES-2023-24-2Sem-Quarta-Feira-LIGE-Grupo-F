
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A classe Reader e responsavel por ler dados de um arquivo CSV, processar e
 * armazenar as informacoes em estruturas de dados apropriadas.
 */
public class readCSV {

	private File ficheiroCSV;
	private List<String> columnTitles;
	private static List<List<String>> tableData;

	/**
	 * Construtor da classe Reader. Inicializa o objeto Reader com o caminho do
	 * arquivo CSV e realiza a leitura do arquivo para preencher as estruturas de
	 * dados.
	 *
	 * @param ficheiroLocal O caminho local do arquivo CSV a ser lido.
	 */
	public readCSV(String ficheiroLocal) {
		ficheiroCSV = new File(ficheiroLocal);
		tableData = dividirPorColuna(ficheiroCSV);
	}

	/**
	 * Metodo privado que realiza a leitura do arquivo CSV, divide as linhas em
	 * colunas e formata os dados conforme necessario.
	 *
	 * @param csv O arquivo CSV a ser lido.
	 * @return Uma lista de listas de strings representando os dados da tabela CSV.
	 */
	private List<List<String>> dividirPorColuna(File csv) {
		List<List<String>> data = new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(csv));
			columnTitles = readColumnTitles(br);
			String line;
			while ((line = br.readLine()) != null) {
				String[] splitLine = line.split(";");
				List<String> linha = new ArrayList<>();
				linha.addAll(Arrays.asList(splitLine));
				data.add(formatDataFromFile(linha));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * Metodo privado que formata os dados lidos do arquivo, preenchendo valores
	 * ausentes.
	 * 
	 * @return Lista de strings formatada.
	 */
	private List<String> formatDataFromFile(List<String> linha) {
		while (linha.size() < getColumnTitles().size()) {
			linha.add("N/A");
		}
		return linha;
	}

	/**
	 * Metodo que le os titulos das colunas do arquivo CSV.
	 *
	 * @param br BufferedReader para leitura do arquivo CSV.
	 * @return Lista de strings contendo os titulos das colunas.
	 * @throws IOException
	 */
	public List<String> readColumnTitles(BufferedReader br) throws IOException {
		List<String> titles = new ArrayList<>();
		String line = br.readLine();
		if (line != null) {
			titles = Arrays.asList(line.split(";"));
		}
		return titles;
	}

	/**
	 * Obtem os titulos das colunas do arquivo CSV.
	 *
	 * @return Lista de strings contendo os titulos das colunas.
	 */
	public List<String> getColumnTitles() {
		return columnTitles;
	}

	/**
	 * Obtem os dados da tabela do arquivo CSV.
	 *
	 * @return Lista de listas de strings representando os dados da tabela.
	 */
	public List<List<String>> getTableData() {
		return tableData;
	}

	/**
	 * Obtem o objeto File associado ao arquivo CSV.
	 *
	 * @return Objeto File representando o arquivo CSV.
	 */
	public File getFicheiroCSV() {
		return ficheiroCSV;
	}

	public static void main(String[] args) {
		readCSV h = new readCSV("HorarioDeExemplo.csv");
		for (List<String> row : tableData) {
			for (String value : row) {
				System.out.println(value);
			}
		}
	}

}
