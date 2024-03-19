import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Horario {

	private String nameFile;
	private File file;
	private static List<String> columnTitles;
	private static List<List<String>> data;

	public Horario(String name) {
		this.nameFile = name;
		file = new File(nameFile);
		data = getData(file);
	}

	public List<List<String>> getData(File csv) {
		List<List<String>> aux = new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(csv));
			columnTitles = readColumnTitles(br);
			String line;
			while ((line = br.readLine()) != null) {
				String[] splitLine = line.split(";");
				List<String> linha = new ArrayList<>();
				linha.addAll(Arrays.asList(splitLine));
				aux.add(formatDataFromFile(linha));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aux;
	}

	private List<String> formatDataFromFile(List<String> linha) {
		while (linha.size() < getColumnTitles().size()) {
			linha.add("N/A");
		}
		return linha;
	}

	public List<String> readColumnTitles(BufferedReader br) throws IOException {
		List<String> titles = new ArrayList<>();
		String line = br.readLine();
		if (line != null) {
			titles = Arrays.asList(line.split(";"));
		}
		return titles;
	}

	public List<String> getColumnTitles() {
		return columnTitles;
	}

	public List<List<String>> getData() {
		return data;
	}

//	public static void main(String[] args){
//		Horario h = new Horario("HorarioDeExemplo.csv");
//		for (List<String> row : data) {
//		    for (String value : row) {
//		        System.out.println(value);
//		    }
//		}
//	}

}
