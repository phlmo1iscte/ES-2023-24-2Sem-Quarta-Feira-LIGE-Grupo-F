package EngSoftPackage.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TipoSala {


    private String filePath;
    private List<String> salas;

    //abrir e trabalhar o ficheiro CSV noutra função e criar uma lista de salas
    //a partir desta classe de SALA
    //ver se é preciso criar um Objeto ou enumerado para lidar com as caracteristicas das salas
    

    public TipoSala(String filePath){    
            this.filePath = filePath;
            salas = lerCampos(filePath);
            
	}

    public static List<String> lerCampos(String arquivoCSV) {
        List<String> campos = new ArrayList<>();
        BufferedReader leitor = null;
        
        try {
            leitor = new BufferedReader(new FileReader(arquivoCSV));
            String linha = leitor.readLine(); // Lê a primeira linha do arquivo
            if (linha != null) {
                // Divide a linha em campos utilizando o delimitador ";"
                String[] todosCampos = linha.split(";");
                // Adiciona os campos a partir do quinto até o último
                for (int i = 5; i < todosCampos.length; i++) {
                    campos.add(todosCampos[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (leitor != null) {
                try {
                    leitor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return campos;
    }
    
    public List<String> getSalas(){    
        return this.salas;
	}

    @Override
	public String toString(){    
        return salas.toString();
	}

}
	
