package com.openclassrooms.bobapp.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.openclassrooms.bobapp.data.JsonReader;
import com.openclassrooms.bobapp.model.Joke;

@Service
public class JokeService
{
    private static final Random RANDOM = new Random();
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
