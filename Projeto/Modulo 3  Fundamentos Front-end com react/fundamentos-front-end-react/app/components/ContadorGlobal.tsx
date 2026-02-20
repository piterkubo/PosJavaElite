"use client";

import { useContext } from "react";
import { ContadorContext } from "../context/ContatorContext";
import { Button } from "./Button";



export const ContadorGlobal = () => {
       
// pegando a hook do contadorContext
const {contador, setContador} = useContext(ContadorContext);

    return (

        <div className="grid gap-y-4">
                <h2 className="text-2xl">Contador Global</h2>
                <p>Numuro atual: {contador}</p>       
               
        
                <div className="flex gap-x-2">
                        <Button
                        onClick={()=> {
                            setContador((c) => (c ?? 0) - 1);
                        } }>-1</Button>
        
                        <Button 
                        onClick={()=> {
                            setContador((c) => (c ?? 0) - 3);            
                        } }>-3</Button>
        
        
                        <Button 
                        onClick={()=> {
                            setContador((c) => (c ?? 0) + 1);
                        } }>+1</Button>
        
                        <Button 
                        onClick={()=> {
                            setContador((c) => (c ?? 0)+ 3);            
                        } }>+3</Button>
                </div>       
                         
        </div>



    );



}