package EngSoftPackage.data;

import java.util.List;

public class Sala {

    private String name;
    private String building;
    private int capacity;
    private int examCapacity;
    private int numberOfFeatures;
    private List<String> feature;

    //abrir e trabalhar o ficheiro CSV noutra função e criar uma lista de salas
    //a partir desta classe de SALA
    //ver se é preciso criar um Objeto ou enumerado para lidar com as caracteristicas das salas
    

    public Sala(String name, String building, int capacity, int examCapacity,  int numberOfFeatures, List<String> feature){    
            this.name = name;
            this.building = building;
            this.capacity = capacity;
            this.examCapacity = examCapacity;
            this.numberOfFeatures = numberOfFeatures;
            this.feature=feature;
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
        //to do
        return sb.toString();
	}
}

