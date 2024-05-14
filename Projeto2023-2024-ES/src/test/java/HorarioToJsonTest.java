package test.java;

import org.junit.jupiter.api.Test;

import EngSoftPackage.data.Horario;
import EngSoftPackage.export.HorarioToJson;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

class HorarioToJsonTest {

    @Test
    void testSomeMethod() {
        // Instancie a classe HorarioToCsv
    	String path = System.getProperty("user.dir") + "/assets/HorarioDeTeste.csv";
        Horario h = new Horario(path); 
        
        
        HorarioToJson horarioToJson = new HorarioToJson(h, path);
        File file = new File(path.replace(".csv", "Exported.json"));
        
        //verificação
        assertTrue(file.exists(), "O arquivo json deve ser criado");
        assertTrue(file.length() > 0, "O arquivo json não deve estar vazio");

        // Cleanup 
        file.delete();

        
    }

   
}
