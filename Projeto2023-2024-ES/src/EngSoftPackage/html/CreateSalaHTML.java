package EngSoftPackage.html;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import EngSoftPackage.data.TipoSala;
import EngSoftPackage.data.Sala;

public class CreateSalaHTML {

	private List<Sala> salas;
	private String pathFile;
    private List<String> columnTitles = new ArrayList<>();
    private List<String> TipoSalas;


	public CreateSalaHTML(List<Sala> salas, String pathFile, TipoSala ts) {
		this.pathFile = pathFile.replace(".csv",".html");
		this.salas = salas;
        this.columnTitles.add("classroomtypedescription");
        this.columnTitles.add("amountofclassroomsofthistype");
        this.columnTitles.add("classroomsids");
        this.TipoSalas =ts.getSalas();
	}
	
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
		
		File f = new File(pathFile.replace("ã", "a").replace("ç", "c"));
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(html.toString());
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String getPathHtml() {
		return pathFile.replace("ã", "a").replace("ç", "c");
	}

}
