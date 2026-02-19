const hobbis = ['Musculação', 'Video Game', 'Skate'];



export const Hobbis = () => {
  
    const  [hobbisPreferido, ...outrosHobbis] = hobbis;

    const novosHobbis = ['Festa', 'Baladas'];

    const myhobbis = [...outrosHobbis, ...novosHobbis] 

    return (
    <div>
        <p>Este são meus hobbis</p>
        <ul className= "list-disc">
            <li className= "list-bold">{hobbisPreferido}</li>
            {myhobbis.map((myhobbis, i) => (
            
            <li key= {`hob-${i}`}>{myhobbis}</li>)) } 
                  

        </ul>  

    </div>  
);


}

