package EngSoftPackage.export;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import EngSoftPackage.data.Horario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


/**
 * Classe que que transforma um Horario num ficheiro Json
 * Esta classe possui informações sobre o horario dividido em duas listas com os titulos e os respectivos campos
 * Implementa o métodos convertToJson
 */
public class HorarioToJson {
	private static List<String> columnTitles;
	private static List<List<String>> data;


    /**
     * Construtor da classe HorarioToCsv.
     *
     * @param horario Horario com informação a exportar
     * @param path Caminho do ficheiro a guardar
     */
	public HorarioToJson(Horario horario, String path) {
		columnTitles = horario.getTitles();
        data = horario.getData();
        convertToJson(path.replace(".csv", "Exported.json"));

	}

	/**
	 Este metodo vai pegar num objecto da classe horario e transformar num ficheiro Json
     * @param filePath Caminho do ficheiro a guardar
	 * @throws IOException Se ocorrer um erro durante a criação do ficheiro.
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