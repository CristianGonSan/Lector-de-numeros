/**
 * 
 * @author Cristian Gonzalez Santos
 */
public class ReadNumber {
    private final String[][] unidades = {{"Cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"}, {"", "once", "doce", "trece", "catorce", "quince"}};
    private final String[][] decenas = {{"", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"}, {"", "diesi", "veinti", "treinta y ", "cuarenta y ", "cincuenta y ", "sesenta y ", "setenta y ", "ochenta y ", "noventa y "}};
    private final String[] centenas = {"", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"};
    private final String[] illones = {"", "m", "b", "tr", "cuatr", "quint", "sext", "sept", "oct", "non", "dec", "undec", "duoc", "tredec", "cuatordec", "quintedec", "sexdec", "septedec", "octodec", "novendec", "vigit"};

    /**
     * 
     * @param numberString String pero solo con caracteres numericos.
     * @return Devuelve un StringBuilder que contiene la pronunciación de la cifra ingresada.
     */
    public StringBuilder parseText(String numberString) {

        char[] numbersChar = numberString.toCharArray();
        int[] numbers = new int[numbersChar.length];

        for (int i = 0; i < numbersChar.length; i++) {
            numbers[i] = Character.getNumericValue(numbersChar[i]);
        }

        StringBuilder stringBuilder = new StringBuilder();

        if (numbers.length == 1) {
            stringBuilder.append(unidades[0][numbers[0]]);
        } else {
            int indice = numbers.length % 3;
            if (indice == 1) {
                indice = 2;
            } else if (indice == 2) {
                indice = 1;
            }
            int a = 0, b = 0, d = 0;

            for (int i = 0; i < numbers.length; i++) {
                int number = numbers[i];

                if (number != 0) {
                    a++;
                    b++;
                    d++;

                    switch (indice) {

                        case 0: //Centenas

                            if (number == 1 && numbers[i + 1] == 0 && numbers[i + 2] == 0) {
                                stringBuilder.append("cien ");
                            } else {
                                stringBuilder.append(centenas[number]);
                                stringBuilder.append(" ");
                            }

                            break;

                        case 1: //Decenas

                            if (numbers[i + 1] == 0) {
                                stringBuilder.append(decenas[0][number]);
                                stringBuilder.append(" ");
                            } else if (number == 1 && numbers[i + 1] < 6) {
                                stringBuilder.append(unidades[1][numbers[i + 1]]);
                                stringBuilder.append(" ");
                            } else {
                                stringBuilder.append(decenas[1][number]);
                            }

                            break;

                        case 2: //Unidades

                            if (number == 1) {
                                if (i == 0) {
                                    if ((((numbers.length - (i + 1)) % 6)) == 0) {
                                        stringBuilder.append("un ");

                                    }
                                } else if (numbers.length == (i + 1)) {
                                    stringBuilder.append(unidades[0][number]);

                                } else if (numbers[i - 1] > 1) {
                                    if (numbers[i - 1] == 2) {
                                        stringBuilder.append("un ");
                                    } else {
                                        stringBuilder.append("y un ");
                                    }
                                } else if (numbers[i - 1] == 0 && numbers[i - 2] > 0) {
                                    stringBuilder.append("un ");
                                }
                                
                            } else if (i > 0) {
                                if (!(numbers[i - 1] == 1 && number < 6)) {
                                    stringBuilder.append(unidades[0][number]);
                                    stringBuilder.append(" ");
                                }
                            } else {
                                stringBuilder.append(unidades[0][number]);
                                stringBuilder.append(" ");
                            }

                            break;

                    }

                }

                indice++;

                if (indice > 2) {
                    if ((((numbers.length - (i + 1)) % 6)) == 0 && numbers.length != (i + 1)) {
                        if (a != 0) {
                            stringBuilder.append(illones[(numbers.length - (i + 1)) / 6]);

                            if (a == 1 && number == 1) {
                                stringBuilder.append("illón ");

                            } else {
                                stringBuilder.append("illones ");

                            }
                            a = 0;//Reiniciamos la variable a
                        }
                    } else if (b > 0 && (i + 1) != numbers.length) {
                        stringBuilder.append("mil ");

                    }

                    b = 0;
                    indice = 0;
                }

            }
            if (d == 0) {
                stringBuilder.append("Cero");
            }
        }

        return stringBuilder;

    }
}
