import {Children, createContext, ReactNode} from 'react';

export const ContadorContext = createContext({
    contador:0,
    setContador:() => {},
});

// provider irar dar suporte para ler os valores
export default function ContadorProvider ({children} : {children:ReactNode})
{
    return <ContadorContext.Provider value={{contador:0, setContador:() =>{}}}>
        {children}
        </ContadorContext.Provider>
};