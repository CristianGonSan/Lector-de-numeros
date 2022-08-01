/**
 * 
 * @author Cristian Gonzalez Santos
 */
public class Pronunciar{
    
    /**
     * 
     * @param cifra Resive un String, pero solo con caracteres numericos.
     * @return Devuelve un String que contiene la pronunciación de la cifra ingresada.
     */
    public static StringBuilder pronun(String cifra){
        
        String unidades[][]= {{"Cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve"}, {"","once","doce","trece","catorce","quince"}};     
        String decenas[][] = {{"","diez","veinte","treinta","cuarenta","cincuenta","sesenta","setenta","ochenta","noventa"}, {"","diesi","veinti","treinta y ","cuarenta y ","cincuenta y ","sesenta y ","setenta y ","ochenta y ","noventa y "}};
        String centenas[]  =  {"","ciento","doscientos","trescientos","cuatrocientos","quinientos","seiscientos","setecientos","ochocientos","novecientos"};
        String illones[]   =  {"","m","b","tr","cuatr","quint","sext","sept","oct","non","dec","undec","duoc","tredec","cuatordec","quintedec", "sexdec","septedec","octodec","novendec","vigit"};

        StringBuilder cifraFinal = new StringBuilder(); //Se crea un objeto de la clase StringBuilder, es un string mutable.

        if (cifra.length()==1){
            cifraFinal.append(unidades[0][Integer.parseInt(String.valueOf(cifra.charAt(0)))]);          
        }
        else {
            int lugar = cifra.length()%3;
                if (lugar == 1){
                    lugar = 2;
                }
                else if (lugar == 2){
                    lugar = 1;
                }
                int a=0,b=0,d=0;
                
            for (int i=0;i<cifra.length();i++){
                int numero = Integer.parseInt(String.valueOf(cifra.charAt(i)));
                
                if(numero != 0){
                    a++; b++; d++;
                    
                    switch (lugar) {
                        
                        case 0: //Centenas
                            
                            if (numero == 1 && 
                                Integer.parseInt(String.valueOf(cifra.charAt(i+1)))==0 && 
                                Integer.parseInt(String.valueOf(cifra.charAt(i+2)))==0){
                                cifraFinal.append("cien ");
                            
                            }
                            else {
                                cifraFinal.append(centenas[numero]);
                                cifraFinal.append(" ");
                            }
                            
                            break;
                        
                        case 1: //Decenas
                            
                            if (numero == 1 && Integer.parseInt(String.valueOf(cifra.charAt(i+1))) < 6 && 
                                               Integer.parseInt(String.valueOf(cifra.charAt(i+1))) > 0){
                            cifraFinal.append(unidades[1][Integer.parseInt(String.valueOf(cifra.charAt(i+1)))]);
                                cifraFinal.append(" ");
                            }
                            else if (Integer.parseInt(String.valueOf(cifra.charAt(i+1))) == 0){
                                cifraFinal.append(decenas[0][numero]);
                                cifraFinal.append(" ");
                            }
                            else if ((i+2)!= cifra.length() && Integer.parseInt(String.valueOf(cifra.charAt(i+1))) == 1){ 
                                if (numero == 2){
                                    cifraFinal.append(decenas[1][2]);  
                                }
                                else {
                                    cifraFinal.append(decenas[0][numero]);
                                    cifraFinal.append(" ");
                                }
                            }
                            else {
                                cifraFinal.append(decenas[1][numero]);
                            }
                            
                            break;
                        
                        case 2: //Unidades
                          
                            if (numero == 1){  
                                if (i == 0){
                                    if ((((cifra.length()-(i+1))%6))==0){
                                        cifraFinal.append("un ");
                                       
                                    }
                                    else {
                                    }     
                                }
                                else if (Integer.parseInt(String.valueOf(cifra.charAt(i-1)))==1 && numero < 6){
                                }
                                else if ((i+1) == cifra.length()){
                                        cifraFinal.append(unidades[0][numero]);
                                }
                                else if (Integer.parseInt(String.valueOf(cifra.charAt(i-1)))>1){   
                                     if (Integer.parseInt(String.valueOf(cifra.charAt(i-1)))==2){
                                        cifraFinal.append("un ");//Solo esta decena lleva "un" en elugar de "y un"
                                       
                                    }  
                                    else {
                                        cifraFinal.append("y un ");
                                        
                                    } 
                                }
                                else if (Integer.parseInt(String.valueOf(cifra.charAt(i-1))) > 1 ||
                                        (Integer.parseInt(String.valueOf(cifra.charAt(i-1))) == 0 &&
                                         Integer.parseInt(String.valueOf(cifra.charAt(i-2))) > 0)){
                                         cifraFinal.append("un "); 
                                }
                            }   
                            else if (i > 0){
                                if (Integer.parseInt(String.valueOf(cifra.charAt(i-1)))==1 && numero < 6){
                                }
                                else {
                                cifraFinal.append(unidades[0][numero]);
                                cifraFinal.append(" ");
                                }
                            }
                            else {
                                cifraFinal.append(unidades[0][numero]);
                                cifraFinal.append(" ");
                            }
                               
                            break;
  
                    }//Fin del switch 
                    
                }//Fin de la condicional

                lugar ++;

                if (lugar > 2){
                    if ((((cifra.length()-(i+1))%6))==0 && (i+1) != cifra.length()){ 
                        if (a != 0){
                            cifraFinal.append(illones[(cifra.length()-(i+1)) / 6]);
                            
                            if (a==1 && numero == 1){
                                cifraFinal.append("illón ");//Este pone "millón"
                            
                            }
                            else {
                                cifraFinal.append("illones ");//Este pone "millones"
                          
                            }
                            a = 0;//Reiniciamos la variable a
                        }
                    }
    
                    else if (b > 0 && (i+1)!= cifra.length()){
                        cifraFinal.append("mil ");
                       
                    }
                    
                    b = 0;
                    lugar = 0;
                }
  
            }//Fin de bucle principal             
            if(d == 0){
            cifraFinal.append("Cero");
            }         
        }//Fin del else principal
        
        return cifraFinal;
        
    }

}
