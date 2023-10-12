package tn.esprit.spring.kaddem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.khaddem.KhaddemApplication;
import tn.esprit.spring.khaddem.controllers.EtudiantRestController;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Option;
import tn.esprit.spring.khaddem.services.IDepartementService;
import tn.esprit.spring.khaddem.services.IEtudiantService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = KhaddemApplication.class)
public class EtudiantRestControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private IEtudiantService etudiantService;


    @MockBean
    private IDepartementService departementService;
    @Autowired
    private EtudiantRestController etudiantController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(etudiantController).build();
    }

    @Test
    public void testAddEtudiant() throws Exception {
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(1);
        etudiant.setNomE("Wissal");
        etudiant.setPrenomE("Touati");
        etudiant.setOp(Option.GAMIX);

        when(etudiantService.addEtudiant(any(Etudiant.class))).thenReturn(etudiant);

        mockMvc.perform(post("/etudiant/add-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idEtudiant\":1,\"nomE\":\"John\",\"prenomE\":\"Doe\"}")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.idEtudiant").value(1)) // Updated attribute name
                .andExpect(jsonPath("$.nomE").value("Wissal")) // Updated attribute name
                .andExpect(jsonPath("$.prenomE").value("Touati"));
    }
    @Test
    public void testGetEtudiants() throws Exception {
        // Create a list of sample Etudiant objects
        List<Etudiant> etudiantList = new ArrayList<>();
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setIdEtudiant(1);
        etudiant1.setNomE("Touati");
        etudiant1.setPrenomE("Wissal");
        Etudiant etudiant2 = new Etudiant();
        etudiant2.setIdEtudiant(2);
        etudiant2.setNomE("BelHaj");
        etudiant2.setPrenomE("Linda");
        etudiantList.add(etudiant1);
        etudiantList.add(etudiant2);

        // Define the behavior of your mocked service
        when(etudiantService.retrieveAllEtudiants()).thenReturn(etudiantList);

        // Perform an HTTP GET request to your endpoint
        mockMvc.perform(get("/etudiant/retrieve-all-etudiants"))
                .andExpect(status().isOk()) // Expect a 200 OK status
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))) // Expect a JSON array with 2 elements
                .andExpect(jsonPath("$[0].idEtudiant", is(1)))
                .andExpect(jsonPath("$[0].nomE", is("Touati")))
                .andExpect(jsonPath("$[0].prenomE", is("Wissal")))
                .andExpect(jsonPath("$[1].idEtudiant", is(2)))
                .andExpect(jsonPath("$[1].nomE", is("BelHaj")))
                .andExpect(jsonPath("$[1].prenomE", is("Linda")));
    }

    @Test
    public void testRemoveEtudiant() throws Exception {
        // L'ID de l'étudiant à supprimer
        Integer idEtudiant = 1;

        doNothing().when(etudiantService).removeEtudiant(idEtudiant);

        // Effectuez une requête HTTP de type DELETE vers l'endpoint /removeEtudiant/{idEtudiant}
        mockMvc.perform(delete("/etudiant/removeEtudiant/{id}", idEtudiant))
                .andExpect(status().isOk()).andDo(print()); // Nous attendons une réponse HTTP 200 OK

        // Vérifiez que la méthode removeEtudiant du service a été appelée avec le bon argument
        verify(etudiantService, times(1)).removeEtudiant(idEtudiant);
    }

    @Test
    public void testAssignEtudiantToDepartement() throws Exception {
        Integer etudiantId = 1;
        Integer departementId = 2;

        // Prepare mock Etudiant and Departement instances
        Etudiant etudiant = new Etudiant(); // Create an instance with the expected ID
        etudiant.setIdEtudiant(etudiantId);
        Departement departement = new Departement(); // Create an instance with the expected ID
        departement.setIdDepartement(departementId);

        // Mock the service methods to retrieve Etudiant and Departement by ID
        when(etudiantService.retrieveEtudiant(etudiantId)).thenReturn(etudiant);
        when(departementService.retrieveDepartement(departementId)).thenReturn(departement);

        // Mock the behavior of the service method
        doNothing().when(etudiantService).assignEtudiantToDepartement(etudiant.getIdEtudiant(), departement.getIdDepartement());

        mockMvc.perform(put("/etudiant/assignEtudiantToDepartement/{etudiantId}/{departementId}", etudiantId, departementId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());

        // Verify that the service method was called with the correct arguments
        verify(etudiantService, times(1)).assignEtudiantToDepartement(etudiantId, departementId);
    }
}
