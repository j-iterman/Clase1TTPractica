package radix;

import java.util.ArrayList;
import java.util.HashMap;

public class RadixSort {

    private static String[] transformarAStrings(int[] numeros){
        String[] numerosConvertido = new String[numeros.length];

        for (int i = 0; i < numeros.length; i++){
            numerosConvertido[i] = String.valueOf(numeros[i]);
        }

        return numerosConvertido;
    }

    private static int[] transformarAEnteros(String[] numeros) {
        int[] numerosConvertido = new int[numeros.length];

        for (int i = 0; i < numeros.length; i++){
            numerosConvertido[i] = Integer.parseInt(numeros[i]);
        }

        return numerosConvertido;
    }

    private static int encontrarMayor(String[] numeros) {
        int mayorLargo = 0;

        for (String numero : numeros) {
            int largo = numero.length();
            if (largo > mayorLargo) mayorLargo = largo;
        }

        return mayorLargo;
    }

    private static String completarConCeros(String numero, int diferencia){
        return "0".repeat(Math.max(0, diferencia)) + numero;
    }

    private static String[] completarConCerosArray(String[] numeros, int mayorLargo){
        for (int i = 0; i < numeros.length; i++){
            numeros[i] = completarConCeros(numeros[i], mayorLargo - numeros[i].length());
        }

        return numeros;
    }

    private static HashMap<String, ArrayList<String>> generarArrays(){
        HashMap<String, ArrayList<String>> listasVacias = new HashMap<>();

        for (int i = 0; i < 10; i++){
            ArrayList<String> listaVacia = new ArrayList<>();
            listasVacias.put(String.valueOf(i), listaVacia);
        }

        return listasVacias;
    }

    private static void ubicarSegunDigito(String[] numeros, int i, HashMap<String, ArrayList<String>> listasPorDigito){
        for (String numero : numeros){
            String digito = String.valueOf(numero.charAt(i));
            listasPorDigito.get(digito).add(numero);
        }
    }

    private static void reordenarArray(String[] numeros, HashMap<String, ArrayList<String>> listasPorDigito) {
        int iArray = 0;
        for (int i = 0; i < 10; i++){
            for (String numero: listasPorDigito.get(String.valueOf(i))){
                numeros[iArray] = numero;
                iArray += 1;
            }
        }
    }

    public static int[] ordenarArray(int[] numeros){
        String[] numerosConvertido = transformarAStrings(numeros);
        int mayorLargo = encontrarMayor(numerosConvertido);
        String[] numerosConCeros = completarConCerosArray(numerosConvertido, mayorLargo);

        HashMap<String, ArrayList<String>> listasPorDigito = generarArrays();

        for (int i = mayorLargo-1; i >= 0; i--){
            ubicarSegunDigito(numerosConCeros, i, listasPorDigito);
            reordenarArray(numerosConCeros, listasPorDigito);
            listasPorDigito = generarArrays();
        }

        return transformarAEnteros(numerosConCeros);
    }

}
