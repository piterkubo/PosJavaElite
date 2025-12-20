//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
// Optional

    ProdutoRepo repo = new ProdutoRepo();

    Produto p = repo.findById(10).
            //orElse(new Produto(-1, "Produto Inexistente",0));
            orElseThrow(() -> new RuntimeException("Produto Inexistente"));
    IO.println(p);


}
