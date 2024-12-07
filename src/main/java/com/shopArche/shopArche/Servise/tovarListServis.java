package com.shopArche.shopArche.Servise;

import com.shopArche.shopArche.Repository.TovarRep;
import com.shopArche.shopArche.model.TovarPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tovarListServis {
    private TovarRep tovarRep;
    public List<TovarPost> getAllTovars() {
        return tovarRep.findAll(); // Проверить, что этот метод возвращает данные.
    }
}

