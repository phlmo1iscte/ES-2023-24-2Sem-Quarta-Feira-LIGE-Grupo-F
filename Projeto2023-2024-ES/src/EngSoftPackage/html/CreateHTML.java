package EngSoftPackage.html;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import EngSoftPackage.data.Horario;

public class CreateHTML {

	private List<String> columnFields;
	private List<String> userOrderTitles = new ArrayList<>();
	private List<List<String>> data;
	private Horario horario;
	private String pathFile;

	public CreateHTML(Horario h, String pathFile) {
		this.pathFile = pathFile.replace(".csv",".html");
		this.horario = h;
		this.data = horario.getData();
		this.columnFields = new ArrayList<>();
		userOrderTitles = horario.getTitles();
		for (String titles : userOrderTitles) {
			String titlesTrimmed = titles.replace(" ", "");
			columnFields.add(titlesTrimmed + "description");
		}
	}
	
	public String formatDataForHtml(){
		StringBuilder sb = new StringBuilder();
		sb.append("var tableData = [");
		
		for (List<String> string : data) {
			sb.append("{ ");
			for(int i = 0; i < columnFields.size(); i++){
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
			 sb.delete(sb.length() - 2, sb.length()); // Remove a vírgula e o espaço extra
		     sb.append("},\n ");
		}
		    sb.append("];\n");
		    
		    return sb.toString();
	}
	
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
		
		

		// Adiciona o elemento div com o id "horario"
		html.append("<div id=\"horario\"></div>\n");
		

		//adiciona o elemento div com os botões para mostrar e esconder as colunas "toogle"
		html.append("<div>Toogle Colunas:");
		html.append(buildToogleButtons());
		html.append("</div>");

		//Cria 2 botões para exportar os ficheiros CSV e JSON
		html.append("<div>\n");
		html.append("<button id=\"download-csv\">Download CSV</button>\n");
		html.append("<button id=\"download-json\">Download JSON</button>\n");
		html.append("</div>\n");
	
		

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
		
		File f = new File(pathFile);
//		pathHtml = System.getProperty("user.dir") + "/" + "Horario.html";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(html.toString());
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String getPathHtml() {
		return pathFile;
	}

}