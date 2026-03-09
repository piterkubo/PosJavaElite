import { FormLogin } from "@/components/FormLogin";
import { COOKIE } from "@/constants/constants";
import { checkInvalidateEmail, checkInvalidatePassword } from "@/lib/utils";
import { Metadata } from "next";
import { cookies } from "next/headers";
import Link from "next/link";
import { redirect } from "next/navigation";




const PAGE_TITLE = "Login";



export const metadata: Metadata = {
  title:PAGE_TITLE,
  
};

export default function Login() {

  const handleLogin = async (initialState_:string, formData: FormData) => {
      
      'use server';
  
     
  
      
      const email = formData.get('email')?.toString();
      const password = formData.get('password')?.toString();
  
      if(!email || !password){
        
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
          
            email:email,
            password: password,
          };
              
          const res = await fetch(`${process.env.BACKEND_URL}/auth/Login`,{
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
     
      <FormLogin action={handleLogin}/>
      <Link className="text-center underline" href="/register">Não tenho cadastro</Link>
    </>
  );
}
