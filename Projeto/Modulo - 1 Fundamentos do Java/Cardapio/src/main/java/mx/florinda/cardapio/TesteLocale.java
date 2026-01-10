package mx.florinda.cardapio;

import java.text.NumberFormat;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;


public class TesteLocale {

    public static void main(String[] args) {

        Locale.availableLocales().forEach(System.out::println);

        // imprime o valor da localização da regiao da maquina atual
        System.out.println("Default locale:" + Locale.getDefault());


        // declarando uma liguagem de locale
        Locale localePtBr = Locale.of("pt", "BR");
        Locale localeUS = Locale.US;



        // Ajustando uma data de locale
       DateTimeFormatter formatterDataHora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);

       // SHORT
            //09/01/2026 18:37
            //1/9/26, 6:37?PM
            //09/01/2026 18:37


        // MEDIUM
            //9 de jan. de 2026 18:37:41
            //Jan 9, 2026, 6:37:41?PM
            //9 de jan. de 2026 18:37:41


        // LONG
            // 9 de janeiro de 2026 18:36:12 BRT
            //January 9, 2026, 6:36:12?PM GMT-03:00
            //9 de janeiro de 2026 18:36:12 BRT

        // FULL

            //sexta-feira, 9 de janeiro de 2026 18:39:14 Hor�rio Padr�o de Bras�lia
           //Friday, January 9, 2026, 6:39:15?PM Brasilia Standard Time
          //sexta-feira, 9 de janeiro de 2026 18:39:15 Hor�rio Padr�o de Bras�lia


        System.out.println(formatterDataHora.format(ZonedDateTime.now()));
        System.out.println(formatterDataHora.withLocale(localeUS).format(ZonedDateTime.now()));
        System.out.println(formatterDataHora.withLocale(localePtBr).format(ZonedDateTime.now()));


        DateTimeFormatter formatterMesAno = DateTimeFormatter.ofPattern("MMMM/yyyy");
        System.out.println(formatterMesAno.format(YearMonth.now()));
        System.out.println(formatterMesAno.withLocale(localeUS).format(YearMonth.now()));
        System.out.println(formatterMesAno.withLocale(localePtBr).format(YearMonth.now()));



        // Formatação de moedas

        System.out.println(NumberFormat.getCurrencyInstance().format(2.99));
        System.out.println(NumberFormat.getCurrencyInstance(localeUS).format(2.99));
        System.out.println(NumberFormat.getCurrencyInstance(localePtBr).format(2.99));



        // Pegando a tradução do enum nos arquivos resouces Bundle

        ResourceBundle mensagens = ResourceBundle.getBundle("mensagens");
        ResourceBundle mensagensUS = ResourceBundle.getBundle("mensagens", localeUS);
        ResourceBundle mensagensPTBR = ResourceBundle.getBundle("mensagens", localePtBr);

        System.out.println(mensagens.getString("categoria.cardapio.pratos_principais"));
        System.out.println(mensagensUS.getString("categoria.cardapio.pratos_principais"));
        System.out.println(mensagensPTBR.getString("categoria.cardapio.pratos_principais"));
    }
}
