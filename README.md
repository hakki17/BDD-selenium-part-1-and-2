# BDD Part 1 - Automatización con Cucumber y Selenium

> Proyecto de laboratorio para aprender Behavior-Driven Development (BDD) usando Java, Cucumber y Selenium WebDriver.

---

## ¿Qué hace este proyecto?

> Este proyecto automatiza pruebas de comportamiento sobre un navegador web real.
> En concreto, simula un usuario que abre Google, busca un término, y verifica que los resultados aparezcan correctamente en pantalla.

---

## ¿Cómo funciona BDD aquí?

> Las pruebas se escriben primero en lenguaje natural (archivos `.feature`), y luego se implementa el código que las ejecuta paso a paso.

El escenario de prueba es:

```gherkin
Feature: Google Search

  Scenario: Search for a term
    Given I am on the Google search page
    When I search for "GitHub"
    Then I should see "GitHub" in the results
```

> Cada línea del escenario (`Given`, `When`, `Then`) tiene una función Java que la ejecuta automáticamente.

---

## Resultado de la ejecución

> Al correr `mvn test`, Cucumber ejecuta el escenario en un navegador Chrome en modo headless (sin ventana visible) y genera un reporte HTML con los resultados.

![Reporte HTML generado por Cucumber](img/image.png)

> El reporte muestra cada paso del escenario con una marca verde si pasó correctamente.

---

## Tecnologías usadas

> El proyecto combina varias herramientas del ecosistema de pruebas en Java:

- **Java** - Lenguaje principal
- **Maven** - Gestión de dependencias y ejecución de pruebas
- **Cucumber** - Framework BDD que interpreta los archivos `.feature`
- **Selenium WebDriver** - Controla el navegador automáticamente
- **Google Chrome + ChromeDriver** - Navegador usado para las pruebas
- **JUnit 4** - Runner que lanza los tests

---

## Estructura del proyecto

```
src/
 test/
  java/
   edu/eci/bddproject/
    features/
     google_search.feature   <- Escenario en lenguaje natural
    runners/
     TestRunner.java          <- Configura y lanza Cucumber
    steps/
     SearchSteps.java         <- Implementación de cada paso
```

---

## ¿Cómo correr las pruebas?

> Asegúrate de tener Java, Maven y ChromeDriver instalados antes de ejecutar.

```bash
mvn test
```

> El reporte HTML se genera en `target/HtmlReports/report.html`.

---

## Autora

> María Paula Sánchez Macías
