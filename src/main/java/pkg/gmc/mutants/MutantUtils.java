/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.gmc.mutants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author gilberto
 */
public class MutantUtils {

    

    public static boolean isMutant(String[] _dna) {
        boolean ismutant = false;
        int mutante = 0;
        String[] dna = _dna;
        final String regexp = "[0-9|a-z|^BDEFHIJKLMNOPQRSUVWXYZ|^\\s]";
        char[][] genoma;
        int _secuencia = 0;
        int filas = dna.length;
        Pattern patronMutante = Pattern.compile(regexp);
        genoma = new char[filas][];

        if (filas < 4) {
            System.out.println("No se cumple con el tamaño minimo de 4 muestra de ADN");
            return ismutant;
        }
        for (String gen : dna) {
            Matcher comparador = patronMutante.matcher(gen);
            int _cromosomas = gen.length();
            if (_cromosomas == filas && !comparador.find()) {
                char[] cromosoma = gen.toCharArray();
                genoma[_secuencia] = new char[cromosoma.length];
                System.arraycopy(cromosoma, 0, genoma[_secuencia], 0, cromosoma.length);
                
            } else {
                System.out.print("La secuencia contiene un error: ");
                System.out.println(gen);
                return ismutant;
            }
            _secuencia++;
        }

        for (int i = 0; i < filas; i++) {

            for (int j = 0; j < genoma[i].length; j++) {
                if (j + 1 < genoma[i].length && i + 1 < filas) {

                    //Buscamos en horizontal los genes
                    ismutant  = buscaSecuencia(genoma[i][j], i, j + 1, 0, genoma);
                    if(ismutant)
                        mutante++;
                    //Buscamos en Vertical los genes
                    buscaSecuencia(genoma[i][j], i + 1, j, 0, genoma);
                     if(ismutant)
                        mutante++;
                    //Buscamos en Diagonal /oblicua los genes de izq. a der.
                    buscaSecuencia(genoma[i][j], i + 1, j + 1, 0, genoma);
                     if(ismutant)
                        mutante++;

                } //else {
//                    for (; j > 0; j--) {
//                        buscaSecuencia(genoma[i][j], i+1, j - 1, 0, genoma);
//                    }
//                }

            }
        }

        if (mutante >= 2) {
            ismutant = true;
            System.out.println("M");
        }else{
            System.out.println("H");
        }
        

        return ismutant;

    }

    public static boolean buscaSecuencia(char bNitrogenada, int inicio, int fin, int secuencia, char[][] genoma) {

        if ((genoma[inicio][fin] == bNitrogenada)) {

            secuencia++;

            if (inicio < fin) {
                inicio++;
            } else if (inicio > fin) {
                fin++;
            } else if (inicio == fin) {
                inicio++;
                fin++;
            }
            if (secuencia >= 3) {
                return true;
            }
            return buscaSecuencia(bNitrogenada, inicio, fin, secuencia, genoma);
        }

        return false;

    }

}
