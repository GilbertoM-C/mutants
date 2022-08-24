package pkg.gmc.mutants.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pkg.gmc.mutants.MutantUtils;
import pkg.gmc.mutants.controller.dtos.MutantDTO;
import pkg.gmc.mutants.controller.dtos.StatsDTO;
import pkg.gmc.mutants.model.LogHistoryAPI;
import pkg.gmc.mutants.model.Mutant;
import pkg.gmc.mutants.repository.MutantsJpa;
import pkg.gmc.mutants.repository.StatsJpa;

/**
 *
 * @author gilberto
 */
@RestController
public class ApiMutants {

    @Autowired
    private MutantsJpa mutantsjpa;
    @Autowired
    private StatsJpa statsjpa;

    /**
     *
     * @param mutant
     * @param http
     * @return
     */
    @PostMapping(path = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity mutant(@RequestBody MutantDTO mutant, HttpServletRequest http) {

        boolean isMutant = false;
        boolean existeMutant = false;
        LogHistoryAPI tester = new LogHistoryAPI();

        isMutant = MutantUtils.isMutant(mutant.getDna());
        existeMutant = mutantsjpa.existsByDNA(Arrays.toString(mutant.getDna()));

        //Validamos si est mutante o no
        if (isMutant) {
            //Sino existe registramos el nuevo mutante
            if (!existeMutant) {
                Mutant mutante = new Mutant();
                tester.setDna(Arrays.toString(mutant.getDna()));
                tester.setIp(http.getRemoteAddr());
                tester.setTipo('M');
                mutante.setDNA(Arrays.toString(mutant.getDna()));
                mutantsjpa.save(mutante);
                statsjpa.save(tester);

            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            tester.setDna(Arrays.toString(mutant.getDna()));
            tester.setIp(http.getRemoteAddr());
            tester.setTipo('H');
            statsjpa.save(tester);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatsDTO getStats() {
        long numMutantes;
        long numHumanos;
        long numPruebas;
        double ratio;
        StatsDTO stats = new StatsDTO();

        numPruebas = statsjpa.count();
        numMutantes = statsjpa.countByTipo('M');
        numHumanos = numPruebas - numMutantes;
        ratio = (double)numMutantes / (double)numHumanos;

        stats.setCount_human_dna(numHumanos);
        stats.setCount_mutant_dna(numMutantes);
        stats.setRatio(ratio);

        return stats;
    }

}
