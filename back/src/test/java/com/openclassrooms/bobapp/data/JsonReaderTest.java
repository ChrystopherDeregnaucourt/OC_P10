package com.openclassrooms.bobapp.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.openclassrooms.bobapp.model.Joke;

/**
 * Tests unitaires pour la classe JsonReader.
 * Vérifie le bon fonctionnement du singleton et de la lecture des blagues depuis le fichier JSON.
 */
class JsonReaderTest
{
    @Test
    @DisplayName("getInstance() devrait retourner une instance non nulle")
    void getInstance_ShouldReturnNonNullInstance()
    {
        // Act
        JsonReader instance = JsonReader.getInstance();

        // Assert
        assertNotNull(instance, "L'instance de JsonReader ne devrait pas être nulle");
    }

    @Test
    @DisplayName("getInstance() devrait toujours retourner la même instance (Singleton)")
    void getInstance_ShouldAlwaysReturnSameInstance()
    {
        // Act
        JsonReader instance1 = JsonReader.getInstance();
        JsonReader instance2 = JsonReader.getInstance();

        // Assert
        assertSame(instance1, instance2, "Les deux appels devraient retourner la même instance");
    }

    @Test
    @DisplayName("getJokes() devrait retourner une liste non vide de blagues")
    void getJokes_ShouldReturnNonEmptyList()
    {
        // Arrange
        JsonReader reader = JsonReader.getInstance();

        // Act
        List<Joke> jokes = reader.getJokes();

        // Assert
        assertNotNull(jokes, "La liste de blagues ne devrait pas être nulle");
        assertFalse(jokes.isEmpty(), "La liste de blagues ne devrait pas être vide");
    }

    @Test
    @DisplayName("getJokes() devrait retourner des blagues avec un contenu valide")
    void getJokes_ShouldReturnJokesWithValidContent()
    {
        // Arrange
        JsonReader reader = JsonReader.getInstance();

        // Act
        List<Joke> jokes = reader.getJokes();

        // Assert
        for (Joke joke : jokes)
        {
            assertNotNull(joke.getJoke(), "Chaque blague devrait avoir un contenu non nul");
            assertFalse(joke.getJoke().isEmpty(), "Chaque blague devrait avoir un contenu non vide");
        }
    }
}
