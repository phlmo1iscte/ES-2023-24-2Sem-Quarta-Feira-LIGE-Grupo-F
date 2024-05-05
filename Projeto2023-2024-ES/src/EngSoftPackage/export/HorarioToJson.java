package EngSoftPackage.export;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import EngSoftPackage.html.Horario;

public class HorarioToJson {
	private static List<String> columnTitles;
	private static List<List<String>> data;

	public HorarioToJson(Horario horario, String path) {
		columnTitles = horario.getTitles();
        data = horario.getData();
        convertToJson(path.replace(".csv", "Exported.json"));

	}

	/**
	 Este metodo vai pegar num objecto da classe horario e transformar num ficheiro Json
	 */

	 public void convertToJson(String filePath) {
        System.out.println(this);
        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonTitles = gson.toJson(columnTitles);
            String jsonData = gson.toJson(data);
            writer.write("[\n");
            writer.write(jsonTitles);
            writer.write(",\n");
            writer.write(jsonData);
            writer.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}