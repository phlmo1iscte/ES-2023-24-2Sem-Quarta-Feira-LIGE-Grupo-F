package EngSoftPackage.data;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
			// TODO: handle exception
		}
		return aux;
	}
	
	// Preenche valores ausentes

	private List<String> formataData(List<String> s) {
		while (s.size() < getTitles().size()) {
			s.add("N/A");
		}
		return s;
	}

	public List<String> readTitles(Scanner s){
		List<String> titles = new ArrayList<>();
		String str = s.nextLine();
		titles.addAll(Arrays.asList(str.split(";")));
		return titles;
	}

	public List<String> getTitles() {
		return columnTitles;
	}

	public List<List<String>> getData() {
		return data;
	}


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
