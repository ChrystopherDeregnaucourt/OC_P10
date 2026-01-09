import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { JokesService } from './jokes.service';
import { Joke } from '../model/joke.model';

describe('JokesService', () =>
{
  let service: JokesService;
  let httpMock: HttpTestingController;

  const mockJoke: Joke = {
    joke: 'Pourquoi les plongeurs plongent-ils toujours en arrière ?',
    response: 'Parce que sinon ils tomberaient dans le bateau !'
  };

  beforeEach(() =>
  {
    TestBed.configureTestingModule(
      {
        imports: [HttpClientTestingModule],
        providers: [JokesService]
      });
    service = TestBed.inject(JokesService);
    httpMock = TestBed.inject(HttpTestingController);

    // Handle the initial HTTP request made in constructor
    const req = httpMock.expectOne('api/joke');
    req.flush(mockJoke);
  });

  afterEach(() =>
  {
    httpMock.verify();
  });

  it('should be created', () =>
  {
    expect(service).toBeTruthy();
  });

  it('should call getRandomJoke on construction and update subject', () =>
  {
    service.joke$().subscribe(joke =>
    {
      expect(joke).toEqual(mockJoke);
    });
  });

  it('getRandomJoke() should fetch a joke from the API', () =>
  {
    const newJoke: Joke = {
      joke: 'Nouvelle blague',
      response: 'Nouvelle réponse'
    };

    service.getRandomJoke();

    const req = httpMock.expectOne('api/joke');
    expect(req.request.method).toBe('GET');
    req.flush(newJoke);

    service.joke$().subscribe(joke =>
    {
      expect(joke).toEqual(newJoke);
    });
  });

  it('joke$() should return an observable', () =>
  {
    const observable = service.joke$();
    expect(observable).toBeTruthy();
    expect(typeof observable.subscribe).toBe('function');
  });
});
