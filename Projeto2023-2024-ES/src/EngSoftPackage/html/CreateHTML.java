package EngSoftPackage.html;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;

import EngSoftPackage.data.Horario;


/**
 * Classe que constroi a página HTML com o Horario
 * Esta classe possui informações sobre o Horario, o caminho para o ficheiro de Horario(utilizado para criar o caminho do HTML)
 * Implementa os métodos formatDataForHtml, buildColumnsForTable, generateHTMLPage e buildToogleButtons que vão
 * manipular a info para string e criar o corpo do html;
 * o método getPathHtml que gera o caminho HTML do ficheiro
 * e os metodos semanaDoAno e semanaDoSemestre que manipulam a data da aula para fazer o respectivo campo do número da semana
 */
public class CreateHTML {

	private List<String> columnFields;
	private List<String> userOrderTitles = new ArrayList<>();
	private List<List<String>> data;
	private Horario horario;
	private String pathFile;


	/**
     * Construtor da classe CreateSalaHTML.
     *
     * @param h objeto com o Horario
     * @param pathFile caminho para o ficheiro 
     */
	public CreateHTML(Horario h, String pathFile) {
		this.pathFile = pathFile.replace(".csv",".html");
		this.horario = h;
		this.data = horario.getData();
		this.columnFields = new ArrayList<>();
		userOrderTitles = horario.getTitles();
		userOrderTitles.add("Semana do ano");
		userOrderTitles.add("Semana do semestre");
		for (String titles : userOrderTitles) {
			String titlesTrimmed = titles.replace(" ", "");
			columnFields.add(titlesTrimmed + "description");
		}
	
	}

	/**
     * metodo que calcula a semana do ano de acordo a uma data passada
     * @param data um campo do ficheiro Horario no formato List<string> com varias infos sobre o mesmo
     * @return a semana do ano, caso seja passada uma data inválida retorna -1
     */

	public int semanaDoAno(List<String> data) {
		//verifica a String passada para ver se existe sequer uma data
		if(data.size()<8)
			return -1;
		String dateString =data.get(8);
		 // Definir o formato da data
		if (dateString.equals("N/A")) 
			return -1;
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Converter a string de data em um objeto LocalDate
        LocalDate date = LocalDate.parse(dateString, formatter);

		return date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
	}
	
	/**
     * metodo que calcula a semana do Semestre de acordo a uma data passada
     * @param data um campo do ficheiro Horario no formato List<string> com varias infos sobre o mesmo
     * @return a semana do ano, caso seja passada uma data inválida retorna -1
     */
	public int semanaDoSemestre(List<String> data) {

		//verifica a String passada para ver se existe sequer uma data
		if(data.size()<8)
			return -1;
		String dateString =data.get(8);
		 // Definir o formato da data
		if (dateString.equals("N/A")) 
			return -1;
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Converter a string de data em um objeto LocalDate
        LocalDate date = LocalDate.parse(dateString, formatter);

		// Definindo as datas de início dos semestres
        LocalDate semesterStart1 = LocalDate.of(date.getYear(), 9, 1);  // 1 de Setembro
        LocalDate semesterEnd1 = LocalDate.of(date.getYear(), 12, 15);  // 15 de Dezembro
        LocalDate semesterStart2 = LocalDate.of(date.getYear(), 2, 1);  // 1 de Fevereiro
        LocalDate semesterEnd2 = LocalDate.of(date.getYear(), 5, 15);   // 15 de Maio

        // Corrigindo o início do semestre para começar na segunda-feira (conforme a norma ISO)
        if (semesterStart1.getDayOfWeek().getValue() != 1) {
            semesterStart1 = semesterStart1.with(java.time.temporal.TemporalAdjusters.next(java.time.DayOfWeek.MONDAY));
        }
        if (semesterStart2.getDayOfWeek().getValue() != 1) {
            semesterStart2 = semesterStart2.with(java.time.temporal.TemporalAdjusters.next(java.time.DayOfWeek.MONDAY));
        }

        // Calcula a semana do semestre
        if (!date.isBefore(semesterStart1) && !date.isAfter(semesterEnd1)) {
            // Semestre de Setembro a Dezembro
            return (int) ChronoUnit.WEEKS.between(semesterStart1, date) + 1;
        } else if (!date.isBefore(semesterStart2) && !date.isAfter(semesterEnd2)) {
            // Semestre de Fevereiro a Maio
            return (int) ChronoUnit.WEEKS.between(semesterStart2, date) + 1;
        }

        // Se a data não está em nenhum semestre
        return -1;
	}
	
	/**
	 * este metodo vai aceder ao horario e suas infos e cria a tabela 
	 * a ser representada pelo tabulator 
	 * @return sb string com os dados da tabela
	 */
	
	public String formatDataForHtml(){
		StringBuilder sb = new StringBuilder();
		sb.append("var tableData = [");
		
		for (List<String> string : data) {
			sb.append("{ ");
			for(int i = 0; i < columnFields.size()-2; i++){
				StringBuilder novaString = new StringBuilder();
		    	String str = string.get(i);
		    	for (int j = 0; j < str.length(); j++) {
		    	    char caractere = str.charAt(j);
		    	    if (caractere == '\'') {
		    	        novaString.append("");
		    	    } else {
		    	        novaString.append(caractere);
		    	    }
		    	}
				sb.append(columnFields.get(i)+ ": " + "'" + novaString + "', ");
			}
			int semanaDoAno = semanaDoAno(string);
			sb.append(columnFields.get(columnFields.size()-2)+ ": " + "'");

			if (semanaDoAno > 0) {
				sb.append( semanaDoAno +  "',");
			}else{
				sb.append( "N/A',");
			}

			int semanaDoSemestre = semanaDoSemestre(string);
			sb.append(columnFields.get(columnFields.size()-1)+ ": " + "'");

			if (semanaDoSemestre > 0) {
				sb.append( semanaDoSemestre +  "'");
			}else{
				sb.append( "Semana s/ aulas'");
			}

		    sb.append("},\n ");
		}
		    sb.append("];\n");
		    
		    return sb.toString();
	}
	

	/**
	 * este metodo vai criar a estrutura das colunas da tabela
	 * @return JsCode string com os dados das colunas
	 */
	private String buildColumnsForTable() {
		StringBuilder jsCode = new StringBuilder();
		jsCode.append("\t" + "\t").append("columns: [");

		for (int i = 0; i < columnFields.size(); i++) {
			jsCode.append("\t" + "\t").append("{title: '").append(userOrderTitles.get(i)).append("', field: '")
					.append(columnFields.get(i)).append("', headerFilter:'input'},").append("\n");
		}
		jsCode.delete(jsCode.length() - 1, jsCode.length());
		jsCode.append("],");
		return jsCode.toString();
	}

	/**
	 * este metodo vai criar os botões que controlam o script que mostra e esconde as colunas da tabela
	 * @return Jscode string com os botões 
	 */
	private String buildToogleButtons() {
		StringBuilder jsCode = new StringBuilder();
		for (int i = 0; i < columnFields.size(); i++) {
			//adiciona o primeiro marcador do botão e passa o Id da coluna a mostrar ou esconder
			jsCode.append("<button onclick=\"toogleColuna(").append(i).append(")\">");
			//adiciona o nome do botão, ou seja, a coluna a esconder
			jsCode.append(userOrderTitles.get(i));
			//adiciona o final do marcador do botão
			jsCode.append("</button> -");
		}
		jsCode.delete(jsCode.length() - 1, jsCode.length());

		return jsCode.toString();
	}

	/**
	 * este metodo vai criar e estruturar o corpo do nosso ficheiro HTML 
	 * 
	 * @throws IOException Se ocorrer um erro durante a criação do ficheiro.
     */
	 
	public void generateHTMLPage() {
		StringBuilder html = new StringBuilder();

		// Adiciona o início do documento HTML
		html.append("<!DOCTYPE html>\n");
		html.append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n");

		// Adiciona a tag head com o título
		html.append("<head>\n");
		html.append("<meta charset=\"utf-8\" />\n");

		// Adiciona o link para o arquivo CSS do tabulator
		html.append(
				"<link rel=\"stylesheet\" href=\"https://unpkg.com/tabulator-tables@4.8.4/dist/css/tabulator.min.css\">\n");
		html.append(
				"<script type=\"text/javascript\" src=\"https://unpkg.com/tabulator-tables@4.8.4/dist/js/tabulator.min.js\"></script>\n");
		html.append("</head>\n");

		// Adiciona o corpo do documento HTML
		html.append("<body>\n");
		html.append("<h1>Horário de Aulas 2023-2024</h1>\n");
		
		html.append("<div>\n");
		html.append("<button id=\"download-csv\">Download CSV</button>\n");
		html.append("<button id=\"download-json\">Download JSON</button>\n");
		html.append("<p></p></div>");
	 	
		// Adiciona o elemento div com o id "horario"
		html.append("<div id=\"horario\"></div>\n");
		
		//adiciona o elemento div com os botões para mostrar e esconder as colunas "toogle"
		html.append("<div>");
		html.append("<p>Toogle Colunas:</p>");
		html.append(buildToogleButtons());
		html.append("</div>");


		// Adiciona o script com o link para o arquivo JavaScript do tabulator
		html.append("<script type=\"text/javascript\">\n");

		String javaScriptTable1 = "var table = new Tabulator('#horario', { " + "data: tableData, "
				+ "pagination:\"local\", " + "layout: 'fitDatafill', " + "paginationSize:10, " + "movableColumns:true, "
				+ "paginationCounter:\"rows\", " + "paginationSizeSelector:[10, 20, 30, 40], "
				+ "initialSort:[{column:\"building\",dir:\"asc\"}], " + "";
		String javaScriptTable2 = javaScriptTable1 + buildColumnsForTable() + "});;";

		String javascriptCode = formatDataForHtml() + "\n" + javaScriptTable2;
		html.append(javascriptCode);

		html.append("\n");

		//cria 2 funções que respondem aos 2 botões para export de CSV e de JSON
		html.append("document.getElementById(\"download-csv\").addEventListener(\"click\", function(){\n");
		html.append("table.download(\"csv\", \"Horario.csv\");\n");
		html.append("});\n");
		html.append("document.getElementById(\"download-json\").addEventListener(\"click\", function(){\n");
		html.append("table.download(\"json\", \"Horario.json\");\n");
		html.append("});\n");

		html.append("\n");

		//função para esconder e mostrar colunas
		html.append("function toogleColuna(columnIndex) {\n");
		html.append("var column = table.getColumns()[columnIndex];\n");
		html.append("if (column.getVisibility()) {\n");
		html.append("table.hideColumn(column);\n");
		html.append("} else {\n");
		html.append("table.showColumn(column);\n");
		html.append("}\n}\n");
		
		// Fecha o corpo e o documento HTML
		html.append("</script>\n");
		html.append("</body>\n");
		html.append("</html>");

		//criação do ficheiro HTML onde vamos transcrever a nossa string HTML que tem o conteudo da pagina
		File f = new File(pathFile);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(html.toString());
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método get
     * Obtém a o caminho para o ficheiro html
     * @return pathFile 
     */
	public String getPathHtml() {
		return pathFile;
	}

}