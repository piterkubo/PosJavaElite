import {FormRegister} from "@/components/FormRegister";
import { redirect } from "next/navigation";
import Link from "next/link";
import { cookies } from "next/headers";


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
    if(!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)){
      return 'Email Invalido';
    }

    // senha min 6 caracteres

    if(password.length < 6){
      return 'A senha precisa ter min 6 caracteres';
    }


    try{
          const body = {
          username:name,
          email:email,
          password: password,
        };

          

        const res = await fetch('http://localhost:4000/auth/register',{
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

          cookiesStore.set('token', token, {
            httpOnly:true,
            secure:true,
            path:'/', 
            maxAge: 60 * 60 * 24,
          })
          
        }
        
     }

    catch{
        console.error("handleRegister failed");
        return "Erro no cadastro";
    }

    
    redirect("/tasks");


  };

  return (
    <div className="grid gap-y-4 px-8 min-w-100 py-12 bg-[#fcfcfc] rounded-3xl shadow-xl" >

     <h1 className="text-4xl text-center font-bold">Cadastro</h1>
     
     <FormRegister action={handleRegister}/>

     <Link className="text-center underline" href="/login">Já tenho cadastro</Link>
     
    </div>
  );
}
