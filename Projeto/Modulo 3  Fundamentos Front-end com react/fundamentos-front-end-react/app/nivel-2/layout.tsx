import ContadorProvider, { ContadorContext } from "../context/ContatorContext";

export default function Layout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (

    // chamando a função provider que esta dentro do arquivo contadorContext

   <ContadorProvider>
        <div className="p-4">{children}</div>
   </ContadorProvider>
   
   
  );
}
