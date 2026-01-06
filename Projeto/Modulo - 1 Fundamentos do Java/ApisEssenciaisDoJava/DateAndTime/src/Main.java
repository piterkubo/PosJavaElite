//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    LocalDate dataAtual = LocalDate.now();
    LocalTime horaAtual = LocalTime.now();
    LocalDateTime dataHoraAtual = LocalDateTime.now();


    IO.println(dataAtual);
    IO.println(horaAtual);
    IO.println(dataHoraAtual);

    // criando um padrao para dateTime

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    IO.println(dataAtual.format(format));


    // Recebendo a entrada

    String dataUsuario =  "01/01/2026";

    LocalDate data = LocalDate.parse(dataUsuario, format);

    IO.println(data);

    // Outro modelo

    DateTimeFormatter outroFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    IO.println(data.format(outroFormato));
}
