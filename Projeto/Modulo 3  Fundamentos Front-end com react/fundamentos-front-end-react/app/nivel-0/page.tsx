

// function
/*export default function Page() {
  return <div>Page</div>;
}*/


import { MeuNome } from "../components/MeuNome";
import { Hobbis } from "../components/Hobbis";
import {Imagem} from "../components/Imagem";


//ArrowFunction

const Page = () => (
  <div>
    <MeuNome name={"PITER"} 
    age = {40} 
    birthDate = {new Date(1985, 3, 18)}/>  
    <Hobbis/>
    <Imagem />

  </div>
);


export default Page;