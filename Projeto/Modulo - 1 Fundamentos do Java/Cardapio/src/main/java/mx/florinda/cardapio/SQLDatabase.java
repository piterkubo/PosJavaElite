package mx.florinda.cardapio;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLDatabase implements Database{

    @Override
    public List<ItemCardapio> listaItensCardapio() {

        // criando um array de list
        List <ItemCardapio> itens = new ArrayList<>();
        
        // enviando uma consulta no banco de dados 
        String sql  = "SELECT id, nome, descricao, categoria, preco, preco_promocional From item_cardapio";

        // criando uma string de conexão do banco

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardapio", "root", "GTT188#");
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            while(rs.next()){
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                BigDecimal preco = rs.getBigDecimal("preco");
                BigDecimal preco_promocional = rs.getBigDecimal("preco_promocional");
                String categoria_Str = rs.getString("categoria");

                //convertendo a string para uma enum
                ItemCardapio.CategoriaCardapio categoria = ItemCardapio.CategoriaCardapio.valueOf(categoria_Str);

                var itemCardapio = new ItemCardapio(id, nome, descricao, categoria, preco, preco_promocional);

                // adicionando itemcardapio a listagem
                itens.add(itemCardapio);
            }

            return itens;

        } 
        
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        

        

    }


    @Override
    public int totalItensCardapio() {
        
                
        // enviando uma consulta no banco de dados 
        String sql  = "SELECT COUNT(*) FROM item_cardapio;";

        // criando uma string de conexão do banco

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardapio", "root", "GTT188#");
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){

            
            int total = 0;

            if(rs.next()){
                total = rs.getInt(1);
                

                
            }

            return total;
         

        } 
        
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void adicionaItemCardapio(ItemCardapio itemCardapio) {
        // enviando uma consulta no banco de dados 
        String sql  = "INSERT INTO item_cardapio (ID, NOME, DESCRICAO, CATEGORIA, PRECO, PRECO_PROMOCIONAL)" + 
        "VALUES(?,?,?,?,?,?)";    



        // criando uma string de conexão do banco

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cardapio", "root", "GTT188#");
            PreparedStatement ps = connection.prepareStatement(sql);){


            ps.setLong(1, itemCardapio.id());
            ps.setString(2, itemCardapio.nome());
            ps.setString(3, itemCardapio.descricao());
            ps.setString(4, itemCardapio.categoria().name());
            ps.setBigDecimal(5, itemCardapio.preco());
            ps.setBigDecimal(6, itemCardapio.precoComDescricao());

            ps.execute();
            
         

        } 
        
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public Optional<ItemCardapio> itemCardapioPorId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionaItemCardapio'");
    }

 
    @Override
    public boolean removerItemCardapio(Long itemId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removerItemCardapio'");
    }

    @Override
    public boolean alterarPrecoItemCardapio(long id, BigDecimal novoPreco) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alterarPrecoItemCardapio'");
    }

    
    



    
    

    
}
