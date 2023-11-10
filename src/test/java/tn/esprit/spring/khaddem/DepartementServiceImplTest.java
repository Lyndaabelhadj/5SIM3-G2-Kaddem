package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.DepartementServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DepartementServiceImplTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllDepartements() {
        // Créez une liste factice de départements
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement());
        departements.add(new Departement());

        when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> result = departementService.retrieveAllDepartements();

        assertEquals(2, result.size());
    }

    @Test
    public void testAddDepartement() {
        Departement departement = new Departement();

        when(departementRepository.save(departement)).thenReturn(departement);

        Departement addedDepartement = departementService.addDepartement(departement);

        assertNotNull(addedDepartement);
    }

    @Test
    public void testUpdateDepartement() {
        Departement departement = new Departement();

        when(departementRepository.save(departement)).thenReturn(departement);

        Departement updatedDepartement = departementService.updateDepartement(departement);

        assertNotNull(updatedDepartement);
    }

    @Test
    public void testRetrieveDepartement() {
        Departement expectedDepartement = new Departement();
        when(departementRepository.findById(1)).thenReturn(Optional.of(expectedDepartement));

        Departement result = departementService.retrieveDepartement(1);

        assertNotNull(result);
        assertEquals(expectedDepartement, result);
    }
/*
    @Test
    public void testRetrieveDepartementsByUniversite() {
        Universite universite = new Universite();
        Departement departement1 = new Departement();
        Departement departement2 = new Departement();
        universite.getDepartements().add(departement1);
        universite.getDepartements().add(departement2);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        List<Departement> result = departementService.retrieveDepartementsByUniversite(1);

        assertNotNull(result);
        assertEquals(2, result.size());
    } */
}
