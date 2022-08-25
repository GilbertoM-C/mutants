/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.gmc.mutants.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import pkg.gmc.mutants.controller.dtos.StatsDTO;
import pkg.gmc.mutants.services.ApiMutantService;

/**
 *
 * @author gilberto
 */
@WebMvcTest(ApiMutantController.class)
public class ApiMutantControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ApiMutantService service;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getStats()
            throws Exception {

        StatsDTO stats =  createStat(15L,8L);

        given(service.generateStats()).willReturn(stats);
       
        
        MvcResult getStats = mockMvc.perform(
                get("/stats"))
                .andExpect(status().isOk())
                .andReturn();
        
        StatsDTO b = objectMapper.readValue(getStats.getResponse().getContentAsString(), StatsDTO.class);
        assert b.getRatio().equals(8L/(double)15L);
    }

    private StatsDTO createStat(Long human, Long mutant) {
        StatsDTO stats = new StatsDTO();
        stats.setCount_human_dna(human);
        stats.setCount_mutant_dna(mutant);
        
        Double _ratio = mutant/(double)human;
        double ratio = Math.round(_ratio * 10) / 10d;
        stats.setRatio(ratio);

        return stats;
    }

}
