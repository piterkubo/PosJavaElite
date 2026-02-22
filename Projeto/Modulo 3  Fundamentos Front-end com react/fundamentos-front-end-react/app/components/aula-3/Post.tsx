"use client";

import {use} from "react";
import {PostType} from "@/app/types";


type PostProps = {
    posts : Promise<PostType[]> ;
}


export default function Posts({
    posts,

}: PostProps){
    const allPosts = use (posts);

    return(
        <ul>
            {allPosts.map((post) =>(
                <li key={post.id}>{post.title}</li>
            ))}
        </ul>
    );
}