import java.util.Objects;

public class Produto {

    private int id;
    private String nome;
    private double preco;


    public Produto(int id, String nome, double preco){
        super();

        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId(){
        return id;
    }


    @Override
    public String toString(){
        return "Produto [id=" + id + ", nome = " + nome + ", Preço=" + preco + "]";
    }


    //Set
    @Override
    public int hashCode(){
        return Objects.hash(id,nome,preco);
    }


    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;

        if(obj == null)
            return false;

        if(getClass() != obj.getClass())
            return false;

        Produto other = (Produto) obj;
        return id == other.id && this.nome.equals(other.nome) &&
                this.preco == other.preco;
    }


}
