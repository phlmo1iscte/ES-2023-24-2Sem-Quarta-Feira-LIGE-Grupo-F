package test.java;

import org.junit.jupiter.api.Test;

import EngSoftPackage.data.Horario;
import EngSoftPackage.export.HorarioToCsv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

class HorarioToCsvTest {

    @Test
    void testSomeMethod() {
        // Instancie a classe HorarioToCsv
    	String path = System.getProperty("user.dir") + "/assets/HorarioDeTeste.csv";
        Horario h = new Horario(path); 
        
        
        HorarioToCsv horarioToCsv = new HorarioToCsv(h, path);
        File file = new File(path.replace(".csv", "Exported.csv"));
        
        //verificação
        assertTrue(file.exists(), "O arquivo csv deve ser criado");
        assertTrue(file.length() > 0, "O arquivo csv não deve estar vazio");

        // Cleanup 
        file.delete();

        
    }

   
}
