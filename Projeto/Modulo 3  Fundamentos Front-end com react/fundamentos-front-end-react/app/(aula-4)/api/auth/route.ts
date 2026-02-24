// app/api/auth/route.ts
import { NextResponse } from 'next/server'
import jwt from 'jsonwebtoken'

const SECRET = 'segreto-bem-seguro'

export async function POST(req: Request) {
  const { email, password } = await req.json()

  // Simulação simples
  if (email === 'admin@example.com' && password === '123456') {
    const user = { email, role: 'admin' }
    const token = jwt.sign(user, process.env.JWT_SECRET!, { expiresIn: '1h' })

    return NextResponse.json({ token, user })
  }

  return NextResponse.json({ message: 'Credenciais inválidas' }, { status: 401 })
}