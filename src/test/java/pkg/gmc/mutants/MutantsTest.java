/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pkg.gmc.mutants;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pkg.gmc.mutants.controller.ApiMutantController;

/**
 *
 * @author gilberto
 */
@SpringBootTest(classes = Mutants.class)
public class MutantsTest {
    
    @Autowired
    ApiMutantController apimutantcontroller;
    
    @Test
    public void contextLoads() throws Exception {
       assertThat(apimutantcontroller).isNotNull();
    }
    
    
    
    
}
