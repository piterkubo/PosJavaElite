"use client";

import { ButtonHTMLAttributes, FC, } from "react";




export const Button: FC <ButtonHTMLAttributes<HTMLElement>> = ({ onClick, children, ...props}) => (  
  
   <button className="border border-blue-500 p-2 rounded cursor-pointer  bg-blue-500  hover:bg-blue-900 text-white font-bold" 
   {...props} 
   
   onClick={onClick}>{children}</button>


       
            
        

  
)