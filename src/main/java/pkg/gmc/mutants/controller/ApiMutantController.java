package pkg.gmc.mutants.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pkg.gmc.mutants.controller.dtos.MutantDTO;
import pkg.gmc.mutants.controller.dtos.StatsDTO;
import pkg.gmc.mutants.services.ApiMutantService;

/**
 *
 * @author gilberto
 */
@RestController
public class ApiMutantController {

    @Autowired
    private ApiMutantService mutantservice;
    

    /**
     *
     * @param mutant
     * @param http
     * @return
     */
    @PostMapping(path = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity mutant(@RequestBody MutantDTO mutant, HttpServletRequest http) {

        boolean isMutant = false;
        
        isMutant = mutantservice.validateMutant(mutant, http);
        
        if (isMutant) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatsDTO getStats() {
        return mutantservice.generateStats();
    }

}
