package mx.florinda.cardapio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.time.Instant;

public class SerializadorPix {

    static void main(String[] args) throws Exception {


        var pix = new Pix(1L, new BigDecimal("10.99"), "piterkubo@email.com", Instant.now(),
                "$$$$");



        try(var fos = new FileOutputStream("pix.ser");

            var  oos = new ObjectOutputStream(fos)){

                oos.writeObject(pix);


            };

    }

}
