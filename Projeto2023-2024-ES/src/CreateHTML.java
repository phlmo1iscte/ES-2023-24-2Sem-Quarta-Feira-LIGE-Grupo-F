import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

class CreateHTML {

	private List<String> columnFields;
	private List<String> userOrderTitles = new ArrayList<>();
	private List<List<String>> data;
	private boolean isMetricsSchedule = false;
	private Horario horario;
	private List<String> columnTitlesForQualitySchedule;
	private String pathHtml;

	public CreateHTML(Horario h, List<List<String>> str, List<String> columTitles) {
		this.horario = h;
		this.data = str;
		this.columnFields = new ArrayList<>();
		userOrderTitles = columTitles;
		this.columnTitlesForQualitySchedule = columTitles;
		for (String titles : userOrderTitles) {
			String titlesTrimmed = titles.replace(" ", "");
			columnFields.add(titlesTrimmed + "_field");
		}
	}

	public List<String> tiltesPosition() {
		List<String> titlesPosition = new ArrayList<>();
		for (String title : userOrderTitles) {
			if (!isMetricsSchedule) {
				titlesPosition.add(String.valueOf(horario.getColumnTitles().indexOf(title)));
			} else {
				titlesPosition.add(String.valueOf(columnTitlesForQualitySchedule.indexOf(title)));
			}
		}
		return titlesPosition;
	}

	private String formatDataForHtml() {
		StringBuilder jsCode = new StringBuilder();
		jsCode.append("var tableData = [");
		List<String> titlesPosition = tiltesPosition();

		for (List<String> row : data) {

			jsCode.append("{ ");
			for (int i = 0; i < columnFields.size(); i++) {
				jsCode.append(columnFields.get(i) + ": ");
				String columnValue = row.get(Integer.parseInt(titlesPosition.get(i))).replace("'", "");
				String javaScriptFormatValue = "'" + columnValue + "', ";
				jsCode.append(javaScriptFormatValue);
			}

			jsCode.delete(jsCode.length() - 2, jsCode.length());
			jsCode.append(" }, ");

		}
		return jsCode.substring(0, jsCode.length() - 2) + "];";
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

	public void generateHTMLPage() {
		StringBuilder html = new StringBuilder();

		// Adiciona o início do documento HTML
		html.append("<!DOCTYPE html>\n");
		html.append("html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n");

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
		html.append("<div id=\"horario\"></div>\n");

		// Adiciona o script com o link para o arquivo JavaScript do tabulator
		html.append("<script type=\"text/javascript\">\n");

		// Fecha o corpo e o documento HTML
//        html.append("</body>\n");
//        html.append("</html>");

		String javaScriptTable1 = "var table = new Tabulator('#horario', { " + "data: tableData, "
				+ "pagination:\"local\", " + "layout: 'fitDatafill', " + "paginationSize:10, " + "movableColumns:true, "
				+ "paginationCounter:\"rows\", " + "paginationSizeSelector:[5, 10, 20, 40], "
				+ "initialSort:[{column:\"building\",dir:\"asc\"}], " + "";
		String javaScriptTable2 = javaScriptTable1 + buildColumnsForTable() + "});;";

		String javascriptCode = formatDataForHtml() + "\n" + javaScriptTable2;
		html.append(javascriptCode);
		html.append("</script>\n");
		html.append("</body>\n");
		html.append("</html>");
//		String code = "<script type=\"text/javascript\">"+javascriptCode+"</script>";
//		String name = "html";
//		String code = "<h1>Teste"+name+"</h1>";
		File f = new File(System.getProperty("user.dir") + "/" + "Horario.html");
		pathHtml = System.getProperty("user.dir") + "/" + "Horario.html";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(f));
			bw.write(html.toString());
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public String getPathHtml() {
		return pathHtml;
	}

}