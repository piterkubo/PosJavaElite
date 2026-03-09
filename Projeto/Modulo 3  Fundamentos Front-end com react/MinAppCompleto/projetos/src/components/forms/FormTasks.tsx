 "use client";

import { FC, useActionState, useRef } from "react";
import { FormError } from "../FormError";

type FormTasksProps = {
  action: (_: string, formData: FormData) => Promise<string>;
};

export const FormTasks: FC<FormTasksProps> = ({ action }) => {

  const formRef = useRef<HTMLFormElement>(null);

  const [errorMessage, formAction, isPending] = useActionState(
    async (state: string, formData: FormData) => {
      const result = await action(state, formData);

      if (!result) {
        formRef.current?.reset();
      }

      return result;
    },
    ""
  );

  return (
    <>
      {!isPending && <FormError message={errorMessage} />}

      <form
        ref={formRef}
        className="relative shadow-lg rounded-lg"
        action={formAction}
      >
        <input
          name="task"
          className="w-full pl-2 pr-10 py-1 text-[#7b7c7b] border border-[#e8e9e9] hover:border-[#b1b2b2] focus:border-[#b1b2b2] outline-none rounded-lg"
          placeholder="Informe o titulo da task"
          type="text"
        />

        <button className="absolute px-3 top-0 right-0 bottom-0 bg-[#141516] text-white rounded-r-lg cursor-pointer">
          +
        </button>
      </form>
    </>
  );
};
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 /* "use client";

import {FC, useActionState, useEffect, useState} from "react"

import { FormError } from "../FormError";


type FormTasksProps = {
  action:(_: string, formData: FormData) => Promise<string>;
};


export const  FormTasks: FC<FormTasksProps> = ({action}) => {
  
  // criando variaveis com as hooks

  const [task, setTask] = useState("");
  
 
  const [errorMessage, formAction, isPending] = useActionState(action, "");


  useEffect(() => {
    if (!isPending && !errorMessage) {
      setTask("");
    }
  }, [isPending, errorMessage]);



  
    return (
      <>
        {!isPending  && <FormError message = {errorMessage}/>}

        
        <form className="relative shadow-lg rounded-lg"  action={formAction}>
            
            <input name="task" value={task} onChange={(e) =>
              setTask(e.target.value)}
              className="w-full pl-2 pr-10 py-1  text-[#7b7c7b] border border-[#e8e9e9] hover:border-[#b1b2b2] focus:border-[#b1b2b2] outline-none rounded-lg"
              placeholder="Informe o titulo da task"
              type="text"               
            />


            <button className="absolute px-3 top-0 right-0 bottom-0 bg-[#141516] text-white rounded-r-lg cursor-pointer">+</button>
           

        </form>
      </>
      
    );
  };
*/
  
