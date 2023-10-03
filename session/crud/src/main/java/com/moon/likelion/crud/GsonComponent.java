package com.moon.likelion.crud;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class GsonComponent {
    public final Gson gson;

    public GsonComponent() {
        this.gson = new Gson();
    }

    public Gson getGson() {
        return this.gson;
    }
}
