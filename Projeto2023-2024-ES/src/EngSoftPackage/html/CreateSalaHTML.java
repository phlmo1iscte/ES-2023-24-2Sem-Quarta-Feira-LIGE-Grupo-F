package EngSoftPackage.html;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import EngSoftPackage.data.TipoSala;
import EngSoftPackage.data.Sala;

/**
 * Classe que constroi a página HTML com a Caracterização das salas
 * Esta classe possui informações sobre as salas, o caminho para o ficheiro das salas(utilizado para criar o caminho do HTML),
 * os Tipos de salas(caracteristicas), e os titulos das tabelas a serem criadas no HTML
 * Implementa os métodos formatDataForHtml, buildColumnsForTable, generateHTMLPage que vão
 * manipular a info para string e criar o corpo do html e o método getPathHtml que gera o caminho HTML do ficheiro
 * 
 */
public class CreateSalaHTML {

	private List<Sala> salas;
	private String pathFile;
    private List<String> columnTitles = new ArrayList<>();
    private List<String> TipoSalas;


	/**
     * Construtor da classe CreateSalaHTML.
     *
     * @param salas lista com todas as salas
     * @param pathFile caminho para o ficheiro 
     * @param ts tipos de sala (caracteristicas)
     */
	public CreateSalaHTML(List<Sala> salas, String pathFile, TipoSala ts) {
		this.pathFile = pathFile.replace(".csv",".html");
		this.salas = salas;
        this.columnTitles.add("classroomtypedescription");
        this.columnTitles.add("amountofclassroomsofthistype");
        this.columnTitles.add("classroomsids");
        this.TipoSalas =ts.getSalas();
	}
	
	/**
	 * este metodo vai aceder a lista das salas e suas infos e cria a tabela 
	 * a ser representada pelo tabulator 
	 * @return sb string com os dados da tabela
	 */
	public String formatDataForHtml(){
		StringBuilder sb = new StringBuilder();
		sb.append("var tableData = [");
      
		for (String string : TipoSalas) {
            sb.append("{ ");
            sb.append(columnTitles.get(0)+ ": \'");
		    sb.append(string + "\',");
            sb.append(columnTitles.get(1)+ ": \'");
            int numberOfClasses = 0;
		    for (int i = 0; i < salas.size(); i++) {
                List<String> features = salas.get(i).getFeature();
                for (int j = 0; j<features.size();j++){
                    if (features.get(j).equals(string)) {
                        numberOfClasses++;
                    }
                }
            }
            sb.append(numberOfClasses + "\',");
            sb.append(columnTitles.get(2)+ ": \'");
            List<String> idSala = new ArrayList<>();
            for (Sala s: salas) {
                List<String> features = s.getFeature();
                for (int i = 0; i<features.size();i++){
                    if (features.get(i).equals(string)) {
                        idSala.add(s.getName());
                    }
                }
            }
            sb.append(idSala + "\'");
            sb.append("},\n ");
		}
    	sb.delete(sb.length() - 2, sb.length()); // Remove a vírgula e o espaço extra
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

		jsCode.append("\t" + "\t").append("{title: '").append("Descrição do Tipo de Sala").append("', field: '")
            .append(columnTitles.get(0)).append("', headerFilter:'input'},").append("\n");
		
        jsCode.append("\t" + "\t").append("{title: '").append("Descrição do Tipo de Sala").append("', field: '")
            .append(columnTitles.get(1)).append("', headerFilter:'input'},").append("\n");
        
        jsCode.append("\t" + "\t").append("{title: '").append("Descrição do Tipo de Sala").append("', field: '")
            .append(columnTitles.get(2)).append("', headerFilter:'input'},").append("\n");
		
            jsCode.append("],");
		return jsCode.toString();
	}
	
	/**
	 * este metodo vai criar e estruturar o corpo do nosso ficheiro HTML e chama os dois 
	 * outros 2 metodos anteriores de apoio ao preenchimento do HTML
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
	
		// Adiciona o elemento div com o id "horario"
		html.append("<div id=\"salas\"></div>\n");
			

		// Adiciona o script com o link para o arquivo JavaScript do tabulator
		html.append("<script type=\"text/javascript\">\n");

		String javaScriptTable1 = "var table = new Tabulator('#salas', { " + "data: tableData, "
				+ "pagination:\"local\", " + "layout: 'fitDatafill', " + "paginationSize:10, " + "movableColumns:true, "
				+ "paginationCounter:\"rows\", " + "paginationSizeSelector:[10, 20, 30, 40], "
				+ "initialSort:[{column:\"building\",dir:\"asc\"}], " + "\n";
		String javaScriptTable2 = javaScriptTable1 + buildColumnsForTable() + "});;";

		String javascriptCode = formatDataForHtml() + "\n" + javaScriptTable2;
		html.append(javascriptCode);

		html.append("\n");
		
		// Fecha o corpo e o documento HTML
		html.append("</script>\n");
		html.append("</body>\n");
		html.append("</html>");
		
		//criação do ficheiro HTML onde vamos transcrever a nossa string HTML que tem o conteudo da pagina
		File f = new File(pathFile.replace("ã", "a").replace("ç", "c"));
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
		return pathFile.replace("ã", "a").replace("ç", "c");
	}

}
