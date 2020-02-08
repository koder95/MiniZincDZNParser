# Jądro biblioteki
Jądro definiuje podstawowe założenia dla projektu.
*	Interfejs **Data** definiuje sposób przechowywania danych. Jest on bardzo prosty, aby dostęp do określonych wartości był prosty.
*	Interfejs **IParser** definiuje jakie metody ma posiadać parser.
*	Klasa **DataBuilder** dostarcza budowniczego instancji interfejsu *Data*.

## Używanie jądra
Jądro może być używane do wczytywania i zapisywania danych. Sposób w jaki będzie to wykonane określa implementacja interfejsu **IParser**.

### Wczytywanie
````Java
IParser parser;
InputStream input;
String key;
	
// inicjalizowanie powyższych zmiennych
	
Object desiredValue = null;
try {
    Data data = parser.load(input);
    if (data.containKey(key)) desiredValue = key;
} catch (IOException ex) {
    // obsługa wyjątku
}
````
### Zapisywanie
````Java
Data data = new DataBuilder().
	.put("count", 5) // - przykładowe dane
	.build();
IParser parser;
OutputStream output;

// inicjalizowanie zmiennych parser i output

try {
    parser.save(output, data);
} catch (IOException ex) {
    // obsługa wyjątku
}
`````
