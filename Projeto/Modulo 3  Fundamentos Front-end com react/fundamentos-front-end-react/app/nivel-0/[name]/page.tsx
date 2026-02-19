import Link from "next/link";

// function
/*export default function Page() {
  return <div>Page</div>;
}*/



//ArrowFunction



// function
/*export default function Page() {
  return <div>Page</div>;
}*/


import { MeuNome } from "../../components/MeuNome";
import { Hobbis } from "../../components/Hobbis";
import {Imagem} from "../../components/Imagem";



//ArrowFunction

/*type PagesProps = {

  params: Promise<{name: string}>

}


const Page = async ({ params }: PagesProps) => {
  const  {name}  = await params;


  return (
    <div className="p-4 grid- gap-y-4">
      <MeuNome 
        name={name}
        age={40}
        birthDate={new Date(1985, 3, 18)}
      />
      <Hobbis />
      <div>
          <p>Gosto de: </p>
          <Imagem />
      </div>
      
       <Link href="/nivel-0">Voltar</Link>
    </div>
     
  );
};


export default Page;*/


type PagesProps = {
  params: { name: string }
}


const Page = async ({ params }: PagesProps) => {
  const { name } = await params;

  return (
    <div className="grid gap-y-4">
      <MeuNome 
        name={name}
        age={40}
        birthDate={new Date(1985, 3, 18)}
      />
      <Hobbis />
      <div>
        <p>Gosto de:</p>
        <Imagem />
      </div>
      <Link href="/nivel-0">Voltar</Link>
    </div>
  );
};

export default Page;
