package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.kaddem.EtudiantServiceImpl;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class IEtudiantServiceTest {
    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    @Mock
    private EtudiantRepository etudiantRepository;


    @Test
     void testAddEtudiant() {
        // Créez un étudiant fictif pour le test
        Etudiant etudiant = new Etudiant();
        etudiant.setNomE("Touati");
        etudiant.setPrenomE("Wissal");
        Mockito.when(etudiantRepository.save(etudiant)).thenReturn(etudiant);
        Etudiant result = etudiantService.addEtudiant(etudiant);
        // Vérifiez que la méthode etudiantRepository.save a été appelée une fois
        Mockito.verify(etudiantRepository, Mockito.times(1)).save(etudiant);

        Assertions.assertEquals(etudiant, result);
    }

    @Test
     void testRetrieveAllEtudiants() {
        // Créez une liste fictive d'étudiants pour le test
        List<Etudiant> etudiantsFictifs = new ArrayList<>();
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Touati");
        etudiant.setPrenomE("Ouissal");

        Etudiant etudiant1 = new Etudiant();
        etudiant1.setIdEtudiant(2);
        etudiant1.setNomE("Touati");
        etudiant1.setPrenomE("Wissal");

        etudiantsFictifs.add(etudiant);
        etudiantsFictifs.add(etudiant1);
        Mockito.when(etudiantRepository.findAll()).thenReturn(etudiantsFictifs);

        List<Etudiant> result = etudiantService.retrieveAllEtudiants();
        Mockito.verify(etudiantRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(etudiantsFictifs, result);
    }


    @Test
     void testUpdateEtudiant() {
        // Créez un étudiant fictif pour le test
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Touati");
        etudiant.setPrenomE("Wissal");

        // Appelez la méthode à tester
        Etudiant result = etudiantService.updateEtudiant(etudiant);

        // Vérifiez que la méthode etudiantRepository.save a été appelée une fois avec l'étudiant en argument
        Mockito.verify(etudiantRepository, Mockito.times(1)).save(etudiant);

        // Vérifiez que le résultat de la méthode est l'étudiant passé en argument
        Assertions.assertEquals(etudiant, result);
    }
    @Test
    void testRemoveEtudiant() {
        Integer idEtudiant = 1;
        etudiantService.removeEtudiant(idEtudiant);

        Mockito.verify(etudiantRepository, Mockito.times(1)).deleteById(idEtudiant);
    }
    @Test
     void testRetrieveEtudiant() {
        // Créez un étudiant fictif pour le test
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Touati");
        etudiant.setPrenomE("Wissal");
        Mockito.when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        Etudiant result = etudiantService.retrieveEtudiant(1);
        Mockito.verify(etudiantRepository, Mockito.times(1)).findById(1);

        Assertions.assertEquals(etudiant, result);
    }
}
