"use client";

import { useContext } from "react";
import { ContadorContext } from "../../context/ContatorContext";




export const ValorContadorGlobal = () => {
       
// pegando a hook do contadorContext
const {contador} = useContext(ContadorContext);

    return (

        <div className="grid gap-y-4">
             
            <p>Valor do meu Contador Global: {contador}</p>     
                      
                         
        </div>



    );



}