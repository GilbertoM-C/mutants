/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.gmc.mutants.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import pkg.gmc.mutants.model.Mutant;

/**
 *
 * @author gilberto
 */
@DataJpaTest
public class MutantJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MutantsJpa mutantsjpa;

    @Test
    public void saveUniqueDna_returnSuccesOrError() {
        // given
        Mutant mutant = createMutant();
        entityManager.persist(mutant);
        entityManager.flush();
        
        boolean m = mutantsjpa.existsByDNA("['ATGCGA','CAGTGC','TTATGT','AGAAGG','CCCCTA','TCACTG']");
        
        assertThat(m).isEqualTo(true);
    }

    private Mutant createMutant() {
        Mutant mutant = new Mutant();
        mutant.setDNA("['ATGCGA','CAGTGC','TTATGT','AGAAGG','CCCCTA','TCACTG']");
        return mutant;
    }
}
