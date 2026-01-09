package com.openclassrooms.bobapp.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests unitaires pour la classe Joke.
 */
class JokeTest
{
    private Joke joke;

    @BeforeEach
    void setUp()
    {
        joke = new Joke();
    }

    @Test
    @DisplayName("Le constructeur par défaut devrait créer une instance valide")
    void defaultConstructor_ShouldCreateValidInstance()
    {
        // Assert
        assertNotNull(joke, "L'instance ne devrait pas être nulle");
        assertNull(joke.getJoke(), "Le champ joke devrait être nul par défaut");
        assertNull(joke.getResponse(), "Le champ response devrait être nul par défaut");
    }

    @Test
    @DisplayName("Le constructeur avec paramètres devrait initialiser les champs correctement")
    void parameterizedConstructor_ShouldInitializeFieldsCorrectly()
    {
        // Arrange
        String expectedJoke = "Pourquoi les plongeurs plongent-ils toujours en arrière ?";
        String expectedResponse = "Parce que sinon ils tomberaient dans le bateau !";

        // Act
        Joke jokeWithParams = new Joke(expectedJoke, expectedResponse);

        // Assert
        assertEquals(expectedJoke, jokeWithParams.getJoke());
        assertEquals(expectedResponse, jokeWithParams.getResponse());
    }

    @Test
    @DisplayName("setJoke() devrait définir correctement la valeur")
    void setJoke_ShouldSetValueCorrectly()
    {
        // Arrange
        String expectedJoke = "C'est l'histoire d'un pingouin...";

        // Act
        joke.setJoke(expectedJoke);

        // Assert
        assertEquals(expectedJoke, joke.getJoke());
    }

    @Test
    @DisplayName("setResponse() devrait définir correctement la valeur")
    void setResponse_ShouldSetValueCorrectly()
    {
        // Arrange
        String expectedResponse = "Il glisse sur la banquise !";

        // Act
        joke.setResponse(expectedResponse);

        // Assert
        assertEquals(expectedResponse, joke.getResponse());
    }

    @Test
    @DisplayName("toString() devrait retourner une représentation correcte")
    void toString_ShouldReturnCorrectRepresentation()
    {
        // Arrange
        String jokeText = "Ma blague";
        String responseText = "Ma réponse";
        joke.setJoke(jokeText);
        joke.setResponse(responseText);

        // Act
        String result = joke.toString();

        // Assert
        assertTrue(result.contains(jokeText), "toString devrait contenir le texte de la blague");
        assertTrue(result.contains(responseText), "toString devrait contenir la réponse");
        assertEquals("Joke [joke=Ma blague, response=Ma réponse]", result);
    }
}
