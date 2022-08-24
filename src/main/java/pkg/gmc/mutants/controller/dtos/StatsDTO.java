/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.gmc.mutants.controller.dtos;

/**
 *
 * @author gilberto
 */
public class StatsDTO {
    private Long count_mutant_dna ;
    private Long count_human_dna;
    private Float ratio;

    public StatsDTO() {
    }

    public Long getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public void setCount_mutant_dna(Long count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    public Long getCount_human_dna() {
        return count_human_dna;
    }

    public void setCount_human_dna(Long count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }
    
    
}
