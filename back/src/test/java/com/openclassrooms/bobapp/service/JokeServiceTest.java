package com.openclassrooms.bobapp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.bobapp.data.JsonReader;
import com.openclassrooms.bobapp.model.Joke;

/**
 * Tests unitaires pour le service JokeService.
 */
@ExtendWith(MockitoExtension.class)
class JokeServiceTest
{
    @Mock
    private JsonReader jsonReader;

    private JokeService jokeService;

    @BeforeEach
    void setUp()
    {
        jokeService = new JokeService(jsonReader);
    }

    @Test
    @DisplayName("getRandomJoke() devrait retourner une blague de la liste")
    void getRandomJoke_ShouldReturnJokeFromList()
    {
        // Arrange
        List<Joke> jokes = Arrays.asList(
            new Joke("Blague 1", "Réponse 1"),
            new Joke("Blague 2", "Réponse 2"),
            new Joke("Blague 3", "Réponse 3")
        );
        when(jsonReader.getJokes()).thenReturn(jokes);

        // Act
        Joke result = jokeService.getRandomJoke();

        // Assert
        assertNotNull(result, "La blague retournée ne devrait pas être nulle");
        assertTrue(jokes.contains(result), "La blague devrait être dans la liste originale");
        verify(jsonReader).getJokes();
    }

    @Test
    @DisplayName("getRandomJoke() devrait appeler JsonReader.getJokes()")
    void getRandomJoke_ShouldCallJsonReaderGetJokes()
    {
        // Arrange
        List<Joke> jokes = Arrays.asList(new Joke("Test", "Response"));
        when(jsonReader.getJokes()).thenReturn(jokes);

        // Act
        jokeService.getRandomJoke();

        // Assert
        verify(jsonReader, times(1)).getJokes();
    }

    @Test
    @DisplayName("getRandomJoke() avec une seule blague devrait retourner cette blague")
    void getRandomJoke_WithSingleJoke_ShouldReturnThatJoke()
    {
        // Arrange
        Joke singleJoke = new Joke("Unique blague", "Unique réponse");
        List<Joke> jokes = Arrays.asList(singleJoke);
        when(jsonReader.getJokes()).thenReturn(jokes);

        // Act
        Joke result = jokeService.getRandomJoke();

        // Assert
        assertSame(singleJoke, result, "Devrait retourner l'unique blague disponible");
    }

    @Test
    @DisplayName("Le constructeur devrait initialiser jsonReader")
    void constructor_ShouldInitializeJsonReader()
    {
        // Arrange & Act
        JokeService service = new JokeService(jsonReader);
        List<Joke> jokes = Arrays.asList(new Joke("Test", "Response"));
        when(jsonReader.getJokes()).thenReturn(jokes);

        // Assert - vérifie que le service est fonctionnel après construction
        assertNotNull(service, "Le service ne devrait pas être null");
        Joke result = service.getRandomJoke();
        assertNotNull(result, "Le service devrait pouvoir retourner une blague");
        verify(jsonReader).getJokes();
    }

    @Test
    @DisplayName("getRandomJoke() devrait retourner des blagues différentes avec plusieurs appels")
    void getRandomJoke_MultipleCalls_ShouldReturnJokes()
    {
        // Arrange
        List<Joke> jokes = Arrays.asList(
            new Joke("Blague 1", "Réponse 1"),
            new Joke("Blague 2", "Réponse 2"),
            new Joke("Blague 3", "Réponse 3"),
            new Joke("Blague 4", "Réponse 4"),
            new Joke("Blague 5", "Réponse 5")
        );
        when(jsonReader.getJokes()).thenReturn(jokes);

        // Act - appeler plusieurs fois pour exercer le générateur aléatoire
        boolean foundDifferent = false;
        Joke firstJoke = jokeService.getRandomJoke();
        for (int i = 0; i < 20; i++)
        {
            Joke joke = jokeService.getRandomJoke();
            if (!joke.equals(firstJoke))
            {
                foundDifferent = true;
                break;
            }
        }

        // Assert
        assertTrue(foundDifferent || jokes.size() == 1, 
            "Devrait pouvoir retourner différentes blagues (ou liste à 1 élément)");
    }
}
