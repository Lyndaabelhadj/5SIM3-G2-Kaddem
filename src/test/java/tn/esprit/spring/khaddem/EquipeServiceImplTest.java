package tn.esprit.spring.khaddem;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.services.EquipeServiceImpl;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class EquipeServiceImplTest {

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Test
    void testRetrieveAllEquipes() {
        List<Equipe> mockedList = Arrays.asList(new Equipe(), new Equipe());
        when(equipeRepository.findAll()).thenReturn(mockedList);

        List<Equipe> result = equipeService.retrieveAllEquipes();

        assertEquals(mockedList.size(), result.size());
        verify(equipeRepository).findAll();
    }

    @Test
    void testAddEquipe() {
        Equipe equipe = new Equipe();
        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipe);

        Equipe result = equipeService.addEquipe(equipe);

        assertNotNull(result);
        verify(equipeRepository).save(equipe);
    }

    @Test
    void testUpdateEquipe() {
        Equipe equipe = new Equipe();
        when(equipeRepository.save(any(Equipe.class))).thenReturn(equipe);

        Equipe result = equipeService.updateEquipe(equipe);

        assertNotNull(result);
        verify(equipeRepository).save(equipe);
    }

    @Test
    void testRetrieveEquipe() {
        Integer id = 1;
        Equipe equipe = new Equipe();
        when(equipeRepository.findById(id)).thenReturn(Optional.of(equipe));

        Equipe result = equipeService.retrieveEquipe(id);

        assertNotNull(result);
        verify(equipeRepository).findById(id);
    }

    @Test
    void testEvoluerEquipes() {
        List<Equipe> equipes = Arrays.asList(new Equipe(), new Equipe());
        when(equipeRepository.findAll()).thenReturn(equipes);

        equipeService.evoluerEquipes();
    }

}
