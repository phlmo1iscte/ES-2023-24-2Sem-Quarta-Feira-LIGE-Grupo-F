package EngSoftPackage.export;

import java.io.FileWriter;
import java.io.IOException;

import EngSoftPackage.data.Horario;

public class HorarioToCsv {
    private Horario horario;
    private String fileName;

    public HorarioToCsv(Horario horario, String path) {
		this.horario = horario;
        fileName = path.replace(".csv", "Exported.csv");
        convertToCsv();
	}

	/**
	 Este metodo vai pegar num objecto da classe horario e transformar num ficheiro csv
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

