package pkg.gmc.mutants.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author gilberto
 */
@Entity
public class Mutant implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    
    @Column(name = "dna", length = 512,unique = true)
    private String DNA;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getDNA() {
        return DNA;
    }

    public void setDNA(String DNA) {
        this.DNA = DNA;
    }
    
    
    
}
