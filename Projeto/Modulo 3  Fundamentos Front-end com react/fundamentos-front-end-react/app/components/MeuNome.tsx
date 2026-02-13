import { FC } from "react";


//implentando obj
type MeunomeProps = {name: string; age:number; birthDate:Date};


export const MeuNome: FC<MeunomeProps> = ({name,age,birthDate}) =>
(    
 
    <p>
        Eu sou o {name} e tenho  {age} anos
        e nasci do dia {""} {birthDate.toLocaleDateString("pt-BR")}
    </p>
       
);

