import type { Metadata } from "next";


import "./globals.css";
import Link from "next/link";



export const metadata: Metadata = {
  title: "Task App",
  
};


export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
  
}>)

{
  return (
    <html lang="pt-BR">
      <body className="">

        <header className="fixed top:-0 right-0 left-0 py-2 border-b text-center shadow-xl">
          <Link className="font-bold" href="/">Task App</Link>
        </header>

        <main className="pt-25 mb-14 flex justify-center">
          {children}
        </main>


          <footer className="text-center">
            <p className="text-sm">Projeto desenvolvido durante o curso de Fundamentos de Front-End com React</p>
            <p className="text-xs">(2026)</p>
          </footer>
        
      
      </body>
    </html>
  );
}
