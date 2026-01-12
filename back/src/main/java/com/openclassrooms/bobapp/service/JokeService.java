package com.openclassrooms.bobapp.service;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.bobapp.data.JsonReader;
import com.openclassrooms.bobapp.model.Joke;

@Service
public class JokeService
{
    private static final SecureRandom RANDOM = new SecureRandom();
    private final JsonReader jsonReader;

    JokeService(JsonReader jsonReader)
    {
        this.jsonReader = jsonReader;
    }

    public Joke getRandomJoke()
    {
        List<Joke> jokes = this.jsonReader.getJokes();
        int randomIndex = RANDOM.nextInt(jokes.size());
        return jokes.get(randomIndex);
    }
}
