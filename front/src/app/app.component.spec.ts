import { TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { JokesService } from './services/jokes.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Joke } from './model/joke.model';

describe('AppComponent', () =>
{
  let httpMock: HttpTestingController;
  const mockJoke: Joke = {
    joke: 'Test joke',
    response: 'Test response'
  };

  beforeEach(async () =>
  {
    await TestBed.configureTestingModule(
      {
        declarations: [AppComponent],
        imports: [HttpClientTestingModule],
        providers: [JokesService]
      }).compileComponents();

    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() =>
  {
    httpMock.verify();
  });

  it('should create the app', () =>
  {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;

    // Handle HTTP requests from JokesService constructor and ngOnInit
    const requests = httpMock.match('api/joke');
    requests.forEach(req => req.flush(mockJoke));

    expect(app).toBeTruthy();
  });

  it('should call getRandomJoke on ngOnInit', () =>
  {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    const jokesService = TestBed.inject(JokesService);

    spyOn(jokesService, 'getRandomJoke');

    // Handle initial HTTP request from JokesService constructor
    const req = httpMock.expectOne('api/joke');
    req.flush(mockJoke);

    app.ngOnInit();

    expect(jokesService.getRandomJoke).toHaveBeenCalled();
  });

  it('should call jokesService.getRandomJoke when getRandomJoke is called', () =>
  {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    const jokesService = TestBed.inject(JokesService);

    spyOn(jokesService, 'getRandomJoke');

    // Handle initial HTTP request from JokesService constructor
    const req = httpMock.expectOne('api/joke');
    req.flush(mockJoke);

    app.getRandomJoke();

    expect(jokesService.getRandomJoke).toHaveBeenCalled();
  });

  it('should have joke$ observable from jokesService', () =>
  {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;

    // Handle HTTP requests
    const requests = httpMock.match('api/joke');
    requests.forEach(req => req.flush(mockJoke));

    expect(app.joke$).toBeTruthy();
  });

  it('constructor should initialize jokesService', () =>
  {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    
    // Handle HTTP requests
    const requests = httpMock.match('api/joke');
    requests.forEach(req => req.flush(mockJoke));

    expect(app['jokesService']).toBeTruthy();
  });

  it('ngOnInit should trigger a new joke fetch during initialization', () =>
  {
    const fixture = TestBed.createComponent(AppComponent);
    const jokesService = TestBed.inject(JokesService);

    spyOn(jokesService, 'getRandomJoke').and.callThrough();

    // Flush initial request from JokesService constructor
    const initReq = httpMock.expectOne('api/joke');
    initReq.flush(mockJoke);

    // Trigger lifecycle hooks (ngOnInit)
    fixture.detectChanges();

    // ngOnInit should have called jokesService.getRandomJoke
    expect(jokesService.getRandomJoke).toHaveBeenCalled();

    // Flush request triggered by ngOnInit
    const ngOnInitReq = httpMock.expectOne('api/joke');
    ngOnInitReq.flush(mockJoke);
  });
});
