import { Suspense } from 'react'

import Posts from '@/app/ui/posts'

export default function Page(){
    const posts = getPosts()


    return (
       <Suspense fallback = {<div>Loading...</div>}>

            <Posts posts={posts}/>

       </Suspense>
    )
}
