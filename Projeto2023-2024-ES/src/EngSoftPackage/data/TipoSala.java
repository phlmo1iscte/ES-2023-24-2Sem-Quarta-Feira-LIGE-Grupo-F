package EngSoftPackage.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa os tipos ou caracteristicas das salas
 * Esta classe possui informações sobre as caracteristicas das salas numa lista.
 * Implementa os métodos lerCampos - para criação da lista com as  caracteristicas salas a partir de uma linha do ficheiro CSV que recebe,
 * metodo get salas e  um @override para visualização das caracteristicas das salas 
 */

public class TipoSala {

    private List<String> salas;

    /**
     * Construtor da classe TipoSala.
     *
     * @param filePath caminho para o ficheiro csv com informação sobre as salas 
     */
    public TipoSala(String filePath){    
            salas = lerCampos(filePath);
            
	}

    /**
     * metodo que Lê do ficheiro csv os tipos de salas
     * @param arquivoCSV caminho para o ficheiro csv com informação sobre as salas 
     * @return campos - lista com as caracterisicas de salas lida do ficheiro
	 * @throws IOException Se ocorrer um erro durante a leitura do ficheiro.
     */
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
    
    /**
     * Método get
     * Obtém a lista com as caracteristicas das salas
     * @return salas
     */
    public List<String> getSalas(){    
        return this.salas;
	}

    /**
     * @override do metodo toString para visualizar as caracteristicas das salas 
     * @return salas string com todos os tipos de salas
     */
    @Override
	public String toString(){    
        return salas.toString();
	}

}
	
