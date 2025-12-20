
void main() throws Exception{

    String texto = "Hello World";
    IO.println("Criptografando...");

    // gerar a chave
    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(128);

    SecretKey secretKey = keyGen.generateKey();



   String textoCriptografado = criptografar(texto,secretKey);
   IO.println(textoCriptografado);

   String textoDecriptografado = descriptografar(textoCriptografado, secretKey);
   IO.println(textoDecriptografado);




}


public static String criptografar(String textoOriginal, SecretKey chave) throws Exception
{

    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE,chave);
    byte[] textoByte = cipher.doFinal(textoOriginal.getBytes());
    return Base64.getEncoder().encodeToString(textoByte);







}


public static String descriptografar(String textoCripto, SecretKey chave) throws Exception
{

    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, chave);
    byte[] cryptoByte = Base64.getDecoder().decode(textoCripto.getBytes());
    byte[] textByte = cipher.doFinal(cryptoByte);

    return  new String(textByte);


}