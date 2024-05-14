package test.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import EngSoftPackage.data.Horario;

import static org.junit.jupiter.api.Assertions.*;

class HorarioTest {

    private Horario horario;

    @BeforeEach
    void setUp() {
    	String path = System.getProperty("user.dir") + "/assets/HorarioDeTeste.csv";
        // Inicializa o objeto Horario antes de cada teste
        horario = new Horario(path);  // A assumção é que o construtor inicializa ou faz alguma operação com columnTitles e data
    }

    @Test
    void testHorarioNotNull() {
        // Testa se o objeto Horario não é nulo
        assertNotNull(horario, "O objeto Horario não deve ser nulo.");
    }

    @Test
    void testColumnTitlesInitialized() {
        // Testa se columnTitles(titulos) foi inicializado e contém dados
        assertNotNull(Horario.getTitles(), "columnTitles não deve ser nulo.");
        assertFalse(Horario.getTitles().isEmpty(), "columnTitles não deve estar vazio.");
    }

    @Test
    void testDataInitialized() {
        // Testa se os campos foram inicializados e contém dados
        assertNotNull(Horario.getData(), "data não deve ser nulo.");
        assertFalse(Horario.getData().isEmpty(), "data não deve estar vazio.");
    }
}
