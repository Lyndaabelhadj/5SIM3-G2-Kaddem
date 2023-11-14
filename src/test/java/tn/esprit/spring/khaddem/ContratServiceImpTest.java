package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.ContratServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContratServiceImpTest {

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @Test
    void retrieveAllContrats() {
        when(contratRepository.findAll()).thenReturn(new ArrayList<>());

        List<Contrat> result = contratService.retrieveAllContrats();

        verify(contratRepository, times(1)).findAll();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void addContrat() {
        when(contratRepository.save(any(Contrat.class))).thenReturn(new Contrat());

        Contrat result = contratService.addContrat(new Contrat());

        verify(contratRepository, times(1)).save(any(Contrat.class));

        // Assert the result
        assertNotNull(result);
    }

    /*   @Test
       void addAndAffectContratToEtudiant() {
           // Mocking the behavior of etudiantRepository.findByNomEAndPrenomE()
           when(etudiantRepository.findByNomEAndPrenomE(anyString(), anyString())).thenReturn(new Etudiant());

           // Mocking the behavior of contratRepository.save()
           when(contratRepository.save(any(Contrat.class))).thenReturn(new Contrat());

           // Call the method from the service
           Contrat result = contratService.addAndAffectContratToEtudiant(new Contrat(), "nom", "prenom");

           // Verify that etudiantRepository.findByNomEAndPrenomE() was called once
           verify(etudiantRepository, times(1)).findByNomEAndPrenomE(anyString(), anyString());

           // Verify that contratRepository.save() was called once
           verify(contratRepository, times(1)).save(any(Contrat.class));

           // Assert the result
           assertNotNull(result);
       }
   */
    @Test
    void retrieveContrat() {
        when(contratRepository.findById(anyInt())).thenReturn(Optional.of(new Contrat()));

        Contrat result = contratService.retrieveContrat(1);

        verify(contratRepository, times(1)).findById(anyInt());

        assertNotNull(result);
    }

    @Test
    void removeContrat() {
        assertDoesNotThrow(() -> contratService.removeContrat(1));

        verify(contratRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void updateContrat() {
        when(contratRepository.save(any(Contrat.class))).thenReturn(new Contrat());

        Contrat result = contratService.updateContrat(new Contrat());

        verify(contratRepository, times(1)).save(any(Contrat.class));

        assertNotNull(result);
    }

    @Test
    void nbContratsValides() {
        when(contratRepository.getnbContratsValides(any(Date.class), any(Date.class))).thenReturn(5);

        int result = contratService.nbContratsValides(new Date(), new Date());

        verify(contratRepository, times(1)).getnbContratsValides(any(Date.class), any(Date.class));

        assertEquals(5, result);
    }


}
