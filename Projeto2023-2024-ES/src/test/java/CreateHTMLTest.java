package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import EngSoftPackage.html.*;
import EngSoftPackage.data.*;
import java.io.File;


class CreateHTMLTest {

    @Test
    void testGenerateHTMLPage() {
        // Configuração do teste
        Horario horario = new Horario(System.getProperty("user.dir") + "/assets/HorarioDeExemplo.csv"); 
        CreateHTML creator = new CreateHTML(horario, System.getProperty("user.dir")+ "/assets/testOutput.csv");

        // Execução
        creator.generateHTMLPage();

        // Verificação
        File file = new File(System.getProperty("user.dir")+ "/assets/testOutput.html");
        assertTrue(file.exists(), "O arquivo HTML deve ser criado");
        assertTrue(file.length() > 0, "O arquivo HTML não deve estar vazio");

        // Cleanup 
        file.delete();
    }
}
