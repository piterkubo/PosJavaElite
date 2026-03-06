  "use client";

import {FC, useActionState, useState} from "react"
import { FormInput } from "./FormInput";
import { FormButton } from "./FormButton";
import { FormError } from "./FormError";


type FormRegisterProps = {
  action:(_: string, formData: FormData) => Promise<string>;
};


export const  FormRegister: FC<FormRegisterProps> = ({action}) => {
  
  // criando variaveis com as hooks
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
 
  const [errorMessage, formAction, isPending] = useActionState(action, "");


  
    return (
      <>
        {!isPending  && <FormError message = {errorMessage}/>}

        
        <form className="grid gap-y-6" action={formAction}>
            
            
            <FormInput 
            id="username" 
            label="Usuário" 
            value={username} 
            setValue={setUsername}/>

            <FormInput
            id="email" 
            label="E-mail" 
            value={email} 
            setValue={setEmail}/>
            
            <FormInput
            id="password" 
            label="Passsword" 
            value={password} 
            setValue={setPassword}
            type={"password"}/>
           
            <FormButton>Cadastrar</FormButton>

        </form>
      </>
      
    );
  }
