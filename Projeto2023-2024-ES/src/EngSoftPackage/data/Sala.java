package EngSoftPackage.data;

import java.util.ArrayList;
import java.util.List;

public class Sala {

    private String name;
    private String building;
    private int capacity;
    private int examCapacity;
    private int numberOfFeatures;
    private List<String> feature = new ArrayList<>();;
    private String[] linhaFile;
    private TipoSala ts;

    //abrir e trabalhar o ficheiro CSV noutra função e criar uma lista de salas
    //a partir desta classe de SALA
    //ver se é preciso criar um Objeto ou enumerado para lidar com as caracteristicas das salas
    

    public Sala(String[] linhaFile, TipoSala ts){    
            this.linhaFile = linhaFile;
            this.ts=ts;
            Create();
	}

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
	
	public String getName(){    
        return this.name;
	}

    public String getBuilding(){    
        return this.building;
	}

    public int getCapacity(){    
        return this.capacity;
	}

    public int getExamCapacity(){    
        return this.examCapacity;
	}

    public List<String> getFeature(){    
        return this.feature;
	}

    public int getNumberOfFeatures(){    
        return this.numberOfFeatures;
	}


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

