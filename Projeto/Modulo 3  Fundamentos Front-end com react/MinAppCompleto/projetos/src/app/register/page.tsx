import Link from "next/link";

export default function Cadastro() {
  return (
    <div className="grid gap-y-4 px-8 min-w-100 py-12 bg-[#fcfcfc] rounded-3xl shadow-xl" >

     <h1 className="text-4xl text-center font-bold">Cadastro</h1>
     
     <form className="grid gap-y-6">
        <fieldset className="grid">      

          <label className="text-[#7b7c7b]" htmlFor="username">
            Usuário
          </label>

          <input className="px-4 py-1 text-[#7b7c7b] border border-[#e8e9e9] hover:border-[#b1b2b2] focus:border-[#b1b2b2] outline-none shadow-md rounded-lg" name="username" id="username" placeholder="" type="text" />   
        </fieldset>


        <fieldset className="grid">

          <label className="text-[#7b7c7b]" htmlFor="email">
            E-mail
          </label>

          <input className="px-4 py-1 text-[#7b7c7b] border border-[#e8e9e9] hover:border-[#b1b2b2] focus:border-[#b1b2b2] outline-none shadow-md rounded-lg" name="email" id="email" placeholder="" type="text" />  
        </fieldset>


        <fieldset className="grid"> 
          <label className="text-[#7b7c7b]" htmlFor="username">
            Password
          </label>

          <input className="px-4 py-1 text-[#7b7c7b] border border-[#e8e9e9] hover:border-[#b1b2b2] focus:border-[#b1b2b2] outline-none shadow-md rounded-lg" name="password" id="password" placeholder="" type="text" />  
        </fieldset>

        <button className="py-2 bg-[#141516] text-white shadow-md rounded-lg cursor-pointer hover:shadow-none">Cadastrar</button>

     </form>

     <Link className="text-center underline" href="/login">Já tenho cadastro</Link>
     
    </div>
  );
}
