package EngSoftPackage.data;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa a estrutura de uma sala  
 * Esta classe possui informações sobre a nome, edificio, capacidade, capacidade para exame,
 * caracteristicas da sala e nº de caracteristicas.
 * Implementa os métodos Create - para criação das salas a partir de uma linha do ficheiro CSV que recebe,
 * e um conjunto de metodos do tipo get para aceder a informação: getName, getBuilding, size, getCapacity
 * getNumberOfFeatures, getFeatures e getExamCapacity e finalmente um @override para visualização da sala e a sua info
 */
public class Sala {

    private String name;
    private String building;
    private int capacity;
    private int examCapacity;
    private int numberOfFeatures;
    private List<String> feature = new ArrayList<>();;
    private String[] linhaFile;
    private TipoSala ts;


    /**
     * Construtor da classe Sala.
     *
     * @param linhaFile linha do ficheiro csv com a informação referente a sala
     * @param ts tipos de salas ou seja as caracteristicas possiveis para as salas 
     * para ver quais serão atribuidas a esta sala
     */
    public Sala(String[] linhaFile, TipoSala ts){    
            this.linhaFile = linhaFile;
            this.ts=ts;
            Create();
	}


    /**
     * metodo que separa os campos do string[] recebida e preenche oas variaveis da classe
     */
    public  void Create() {
        this.building = linhaFile[0];
        this.name = linhaFile[1];
        this.capacity = Integer.parseInt(linhaFile[2]);
        this.examCapacity = Integer.parseInt(linhaFile[3]);
        this.numberOfFeatures = Integer.parseInt(linhaFile[4]);
        
        for (int i = 5; i < linhaFile.length; i++) {
            if (linhaFile[i].equalsIgnoreCase("x")) {
                feature.add(ts.getSalas().get(i-5));
            }
        }
        
        
    }

	/**
     * Método get
     * Obtém o nome da sala
     * @return name
     */
	public String getName(){    
        return this.name;
	}

    /**
     * Método get
     * Obtém o nome do edificio que esta localizada a sala
     * @return building
     */
    public String getBuilding(){    
        return this.building;
	}

    /**
     * Método get
     * Obtém o capacidade normal da sala
     * @return capacity
     */
    public int getCapacity(){    
        return this.capacity;
	}

    /**
     * Método get
     * Obtém o capacidade de exame da sala
     * @return examCapacity
     */
    public int getExamCapacity(){    
        return this.examCapacity;
	}

    /**
     * Método get
     * Obtém uma lista com as caracteristicas da sala
     * @return feature
     */
    public List<String> getFeature(){    
        return this.feature;
	}

    /**
     * Método get
     * Obtém o nº de caracteristicas da sala
     * @return numberOfFeatures
     */
    public int getNumberOfFeatures(){    
        return this.numberOfFeatures;
	}


	/**
     * @override do metodo toString para visualizar a sala 
     * @return sb string com a info da sala
     */

	@Override
	public String toString(){    
        StringBuilder sb = new StringBuilder();
        sb.append("Edíficio: " + getBuilding() + "\n");
        sb.append("Nome da Sala: " + getName() + "\n");
        sb.append("Capacidade Normal: " + getCapacity() + "\n");
        sb.append("Capacidade Exame: " + getExamCapacity() + "\n");
        sb.append("Número caracteristicas da sala: " + getNumberOfFeatures() + "\n");
        sb.append("Caracteristicas: " + feature.toString() + "\n");
        return sb.toString();
	}
}

