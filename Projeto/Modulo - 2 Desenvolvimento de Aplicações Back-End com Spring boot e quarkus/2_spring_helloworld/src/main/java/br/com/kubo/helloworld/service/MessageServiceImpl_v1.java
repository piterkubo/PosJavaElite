package br.com.kubo.helloworld.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("V1")
public class MessageServiceImpl_v1 implements IMessageService {

    @Override
    public String sayCustomMessage(String original) {
        return original.toUpperCase();
    }
}
