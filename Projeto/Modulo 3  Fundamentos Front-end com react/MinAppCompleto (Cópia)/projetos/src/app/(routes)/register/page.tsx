import {FormRegister} from "@/components/FormRegister";
import { redirect } from "next/navigation";
import Link from "next/link";
import { cookies } from "next/headers";
import { Metadata } from "next";
import { checkInvalidateEmail, checkInvalidatePassword } from "@/lib/utils";
import { COOKIE } from "@/constants/constants";



const PAGE_TITLE = "Cadastro";



export const metadata: Metadata = {
  title:PAGE_TITLE,
  
};



export default function Cadastro() {
 
  const handleRegister = async (initialState_:string, formData: FormData) => {
    
    'use server';

   

    const name = formData.get('username')?.toString();
    const email = formData.get('email')?.toString();
    const password = formData.get('password')?.toString();

    if(!name || !email || !password){
      
      return "Preencha todos os campos";

    }

    //validando email
   if(checkInvalidateEmail(email)){
           return 'Email Invalido';
         }

    // senha min 6 caracteres

    if(checkInvalidatePassword(password)){
        return 'A senha precisa ter min 6 caracteres';
      }


    try{
          const body = {
          username:name,
          email:email,
          password: password,
        };

          

        const res = await fetch(`${process.env.BACKEND_URL}/auth/auth/register`,{
          method:'POST',
          body: JSON.stringify(body),
          headers:{
            'Content-Type':'application/json'
          }

        });

        const {token, message} = await res.json();

        if(!token){
          return message;
          
        }

        else{
           
          const cookiesStore = await cookies();

          cookiesStore.set('token', token, COOKIE)
          
        }
        
     }

    catch{
        console.error("handleRegister failed");
        return "Erro no cadastro";
    }

    
    redirect("/tasks");


  };

  return (

    <>
      <h1 className="text-4xl text-center font-bold">{PAGE_TITLE}</h1>      
      <FormRegister action={handleRegister}/>
      <Link className="text-center underline" href="/login">Já tenho cadastro</Link>
    </> 
   
  );
}
