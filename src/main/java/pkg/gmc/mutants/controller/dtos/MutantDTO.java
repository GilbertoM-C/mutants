/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.gmc.mutants.controller.dtos;

import java.io.Serializable;

/**
 *
 * @author gilberto
 */
public class MutantDTO implements Serializable{
    
    private String[] dna;

    public MutantDTO() {
    }
    
    public MutantDTO(String[] dna) {
        this.dna = dna;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }    
}
