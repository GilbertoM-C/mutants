/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pkg.gmc.mutants.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pkg.gmc.mutants.model.Mutant;

/**
 *
 * @author gilberto
 */
public interface MutantsJpa extends JpaRepository<Mutant, Long>{

   

    public boolean existsByDNA(String toString);
    
}
