package EngSoftPackage.export;

import java.io.FileWriter;
import java.io.IOException;

import EngSoftPackage.data.Horario;

 
/**
 * Classe que que transforma um Horario num ficheiro CSV
 * Esta classe possui informações sobre o horario e o path para o ficheiro destino
 * Implementa o métodos convertToCsv
 */

public class HorarioToCsv {
    private Horario horario;
    private String fileName;


    /**
     * Construtor da classe HorarioToCsv.
     *
     * @param horario Horario com informação a exportar
     * @param path Caminho do ficheiro a guardar
     */
    public HorarioToCsv(Horario horario, String path) {
		this.horario = horario;
        fileName = path.replace(".csv", "Exported.csv");
        convertToCsv();
	}

	/**
	 *Este metodo vai pegar num objecto da classe horario e transformar num ficheiro cs
	 * @throws IOException Se ocorrer um erro durante a criação do ficheiro.
     */
	 
	 public void convertToCsv() {
        String data = horario.toString();
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

