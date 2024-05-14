package EngSoftPackage.data;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * Classe que representa a estrutura de um Horário  
 * Esta classe possui informações sobre a unidade curricular, turno, turma, número de inscritos,
 * data da aula, sala, ect.
 * Implementa os métodos getData, formataData, readTitles, getTitles, getData e faz um @override do toString 
 * Para manipular os dados do horario, maioritariamente para ler e guardar de um ficheiro
 * aceder a campos de informação e definir valores para eses campos
 */

public class Horario {

	private String nameFile;
	private static File file;
	private static List<String> columnTitles;
	private static List<List<String>> data;


	/**
     * Construtor da classe Horario.
     *
     * @param name path para o ficheiro a abrir para extrair a informação
     */

	public Horario(String name) {
		this.nameFile = name;
		file = new File(nameFile);
		data = getData(file);
	}


	/**
	 *Método get
     * Obtém os dados para os campos do Horário, como o preenchimento desta classe
	 * depende do ficheiro de input temos esta Lista que guarda os campos de informação
	 * @param f ficheiro com os dados a extrair para o Horario
     * @return Unidade curricular da aula
	 * 
	 * 
	 * @throws IOException Se ocorrer um erro durante a leitura do arquivo.
     */
	public List<List<String>> getData(File f){
		List<List<String>> aux = new ArrayList<>();
		try {
			Scanner sc = new Scanner(f);
			columnTitles = readTitles(sc);
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				List<String> str = new ArrayList<>();
				str.addAll(Arrays.asList(line.split(";")));
				aux.add(formataData(str));
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aux;
	}
	
	

	/**
     * Sempre que tivermos um campo vazio para não visualizarmos nada na nossa tabela 
	 * chamamos este metodo para preencher o campo com 'N/A'
	 * @param s Lista de dados do horario
     * @return s devolve a mesma lista depois de adicionar informação
     */
	private List<String> formataData(List<String> s) {
		while (s.size() < getTitles().size()) {
			s.add("N/A");
		}
		return s;
	}

	/**
     * metodo chamado para atribuir valor aos titulos dos campos do horario
	 * @param s recebe o scanner que já abriu o File com a informação do Horário
     * @return titles retorna a lista com os campos de titulos, ou ordem de titulos dentro do nosso Horario
     */
	public List<String> readTitles(Scanner s){
		List<String> titles = new ArrayList<>();
		String str = s.nextLine();
		titles.addAll(Arrays.asList(str.split(";")));
		return titles;
	}

	/**
	 * Método get
     * Obtém os titulos dos campos dos horários
     * @return columnTitles titulos dos campos dos horários
     */
	public static List<String> getTitles() {
		return columnTitles;
	}

	/**
	 *Método get
     * Obtém os dados do Horario, ou seja aulas marcadas
     * @return data dados do Horario
     */
	public static List<List<String>> getData() {
		return data;
	}


	/**
     * @override do metodo toString para visualizar o horario 
	 * no formato: titulos depois aulas marcadas(campos)
     * @return sb string com o horario
     */

	@Override
	public String toString(){

		StringBuilder sb = new StringBuilder();
        
        // Adiciona os títulos das colunas separados por ";"
        sb.append(String.join(";", columnTitles));
        sb.append("\n");
        
        // Adiciona os dados separados por ";"
        for (List<String> rowData : data) {
            sb.append(String.join(";", rowData));
            sb.append("\n");
        }
        
        return sb.toString();
	}
}
