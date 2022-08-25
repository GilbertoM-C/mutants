/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.gmc.mutants.services;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
public class ApiMutantService {

    @Autowired
    private MutantsJpa mutantsjpa;
    @Autowired
    private StatsJpa statsjpa;

    public boolean validateMutant(MutantDTO mutant, HttpServletRequest http) {
        boolean isMutant = false;
        boolean existMutant = false;
        LogHistoryAPI tester = new LogHistoryAPI();

        isMutant = MutantService.isMutant(mutant.getDna());
        existMutant = mutantsjpa.existsByDNA(Arrays.toString(mutant.getDna()));

        //Validamos si est mutante o no
        if (isMutant) {
            //Sino existe registramos el nuevo mutante
            if (!existMutant) {
                Mutant mutante = new Mutant();
                tester.setDna(Arrays.toString(mutant.getDna()));
                tester.setIp(http.getRemoteAddr());
                tester.setTipo('M');
                mutante.setDNA(Arrays.toString(mutant.getDna()));
                mutantsjpa.save(mutante);
                statsjpa.save(tester);

            }
            return isMutant;
        } else {
            tester.setDna(Arrays.toString(mutant.getDna()));
            tester.setIp(http.getRemoteAddr());
            tester.setTipo('H');
            statsjpa.save(tester);
            return isMutant;
        }

    }

    public StatsDTO generateStats() {
        long numMutantes;
        long numHumanos;
        long numPruebas;
        double ratio;
        StatsDTO stats = new StatsDTO();

        numPruebas = statsjpa.count();
        numMutantes = statsjpa.countByTipo('M');
        numHumanos = numPruebas - numMutantes;
        ratio = (double) numMutantes / (double) numHumanos;

        stats.setCount_human_dna(numHumanos);
        stats.setCount_mutant_dna(numMutantes);
        stats.setRatio(ratio);
        
        return stats;

    }
}
