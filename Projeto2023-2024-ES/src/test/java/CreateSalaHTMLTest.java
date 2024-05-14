package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import EngSoftPackage.html.*;
import EngSoftPackage.data.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


class CreateSalaHTMLTest {

    private final String testFilePath = System.getProperty("user.dir") + "/assets/testSala.html";
    private CreateSalaHTML createSalaHTML;
    private List<Sala> salas;

    @BeforeEach
    void setUp() throws Exception {
        // Configuração inicial do teste
        salas = new ArrayList<>();
        TipoSala ts = new TipoSala(System.getProperty("user.dir") + "/assets/CaracterizaçãoDasSalas.csv");  
        String[] s = {"Ala Autnoma (ISCTE-IUL)","Auditorio Afonso de Barros","80","39","4","x","","","","","","","","","","x","","","","","","","","","x","x","","","","x","",""};   
        salas.add(new Sala(s,ts)); // 
        createSalaHTML = new CreateSalaHTML(salas, testFilePath, ts);
    }

    @Test
    void testGenerateHTMLPage() throws Exception {
        // Ação
        createSalaHTML.generateHTMLPage();

        // Verificação
        File file = new File(testFilePath);
        assertTrue(file.exists(), "O arquivo HTML deve ser criado");
        assertNotEquals(0, file.length(), "O arquivo HTML não deve estar vazio");

        String content = new String(Files.readAllBytes(Paths.get(testFilePath)));
        assertTrue(content.contains("Auditorio Afonso de Barros"), "O conteúdo deve incluir o nome da sala");
        assertTrue(content.contains("Anfiteatro aulas"), "O conteúdo deve incluir o tipo da sala");
    }

    @AfterEach
    void tearDown() throws Exception {
    	
        // Limpeza após o teste
        Files.deleteIfExists(Paths.get(testFilePath));
    }
}
