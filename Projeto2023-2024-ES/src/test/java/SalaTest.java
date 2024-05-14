package test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import EngSoftPackage.data.Sala;
import EngSoftPackage.data.TipoSala;

import static org.junit.jupiter.api.Assertions.*;

class SalaTest {

    private Sala sala;

    @BeforeEach
    void setUp() {
    	
    	TipoSala ts = new TipoSala(System.getProperty("user.dir") + "/assets/CaracterizaçãoDasSalas.csv");  
        String[] s = {"Ala Autnoma (ISCTE-IUL)","Auditorio Afonso de Barros","80","39","4","x","","","","","","","","","","x","","","","","","","","","x","x","","","","x","",""};   
        
        // Inicializa o objeto Sala antes de cada teste
        sala = new Sala(s, ts); // Este construtor deve inicializar todas as variáveis internas de maneira adequada
    }

    @Test
    void testNameInitialized() {
        // Verifica se o nome está inicializado corretamente
        assertNotNull(sala.getName(), "O nome da sala não deve ser nulo.");
        assertFalse(sala.getName().isEmpty(), "O nome da sala não deve estar vazio.");
    }

    @Test
    void testBuildingInitialized() {
        // Verifica se o prédio está inicializado corretamente
        assertNotNull(sala.getBuilding(), "O prédio não deve ser nulo.");
        assertFalse(sala.getBuilding().isEmpty(), "O prédio não deve estar vazio.");
    }

    @Test
    void testCapacityInitialized() {
        // Verifica se a capacidade está definida corretamente
        assertTrue(sala.getCapacity() >= 0, "A capacidade deve ser não-negativa.");
    }

    @Test
    void testExamCapacityInitialized() {
        // Verifica se a capacidade de exame está definida corretamente
        assertTrue(sala.getExamCapacity() >= 0, "A capacidade de exame deve ser não-negativa.");
    }

    @Test
    void testNumberOfFeaturesInitialized() {
        // Verifica se o número de características está definido corretamente
        assertTrue(sala.getNumberOfFeatures() >= 0, "O número de características deve ser não-negativo.");
    }

    @Test
    void testFeaturesInitialized() {
        // Verifica se a lista de características está inicializada e pode estar vazia, mas não nula
        assertNotNull(sala.getFeature(), "A lista de características não deve ser nula.");
    }
}