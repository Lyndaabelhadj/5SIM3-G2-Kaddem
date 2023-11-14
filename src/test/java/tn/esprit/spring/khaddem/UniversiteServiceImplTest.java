package tn.esprit.spring.khaddem;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.services.UniversiteServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    public void setUp() {
        Universite universite = new Universite();
        universite.setIdUniversite(1);
        universite.setNomUniv("Test University");

        Mockito.lenient().when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        Mockito.lenient().when(universiteRepository.findAll()).thenReturn(Arrays.asList(universite));
        Mockito.lenient().when(universiteRepository.save(any(Universite.class))).thenReturn(universite);
    }

    @Test
    public void testRetrieveAllUniversites() {
        assertEquals(1, universiteService.retrieveAllUniversites().size());
    }

    @Test
    public void testAddUniversite() {
        Universite newUniversite = new Universite();
        newUniversite.setNomUniv("New University");
        Universite returnedUniversite = universiteService.addUniversite(newUniversite);
        assertEquals("New University", returnedUniversite.getNomUniv());
    }

    @Test
    public void testUpdateUniversite() {
        Universite updatedUniversite = new Universite();
        updatedUniversite.setNomUniv("Updated University");
        Universite returnedUniversite = universiteService.updateUniversite(updatedUniversite);
        assertEquals("Updated University", returnedUniversite.getNomUniv());
    }

    @Test
    public void testRetrieveUniversite() {
        Universite universite = universiteService.retrieveUniversite(1);
        assertNotNull(universite);
        assertEquals("Test University", universite.getNomUniv());
    }

    /*@Test
    public void testAssignUniversiteToDepartement() {
        Departement departement = new Departement(); // Assuming Departement class has a default constructor
        departement.setIdDepartement(1); // And setters

        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(1, 1);
        verify(departementRepository, times(1)).findById(1);
        verify(universiteRepository, times(1)).findById(1);
    }*/
}
