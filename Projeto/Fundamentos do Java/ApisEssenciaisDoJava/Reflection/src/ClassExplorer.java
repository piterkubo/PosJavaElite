import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class ClassExplorer {

    public static void exploreMetadata(Object o) throws Exception{

        IO.println("---> Extraindo os astributos");

        for(Field f: o.getClass().getDeclaredFields()){
            IO.println(f.getName() + ":" +f.getType().getName()); // pega o nome e o tipo
        }


        IO.println("----> Extraindo os metodos");

        for (Method m: o.getClass().getDeclaredMethods()){
            IO.println(m.getName()+ ":" +m.getReturnType().getName());
        }



        IO.println("----> Extraindo dados do objetos");


        for(Field g: o.getClass().getDeclaredFields()){

            g.setAccessible(true); // tira o produto do private
            IO.println(g.get(o)); // imprime na tela
            g.setAccessible(false); // volta colocar o produto no private
        }


        IO.println("----> Extraindo por execucao de metodo");

        for(Method m: o.getClass().getDeclaredMethods()){
            if(m.getName().startsWith("get")){
                IO.println(m.getName()+" - valor: " + m.invoke(o, null));
            }
        }

    }
}
