/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import pkg.gmc.mutants.model.Mutant;
import pkg.gmc.mutants.repository.MutantsJpa;

/**
 *
 * @author gilberto
 */
@RestController
public class ApiMutants {

    @Autowired
    private MutantsJpa mutantsjpa;

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
        
        isMutant = MutantUtils.isMutant(mutant.getDna());
        existeMutant = mutantsjpa.existsByDNA(Arrays.toString(mutant.getDna()));

        //Validamos si est mutante o no
        if (isMutant) {
            //Sino existe registramos el nuevo mutante
            if (!existeMutant) {
                Mutant mutante = new Mutant();
                mutante.setDNA(Arrays.toString(mutant.getDna()));
                mutantsjpa.save(mutante);
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @GetMapping("/stat")
    public StatsDTO getStats() {

        return null;
    }

}
