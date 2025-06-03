Zasady gry w pokera (Texas Hold'em – skrót)
Na początku każdy gracz dostaje 2 własne karty , których inni nie widzą oraz każdy gracz ma swoje żetony (stack)
Gra przebiega w 4 fazach:
Pre-flop – decyzje graczy przed wyłożeniem kart wspólnych.
Flop – na stole pojawiają się 3 wspólne karty.
Turn – dokładana jest 4. karta.
River – dokładana jest 5. ostatnia karta.
W każdej fazie gracz może:
check – przeczekać (jeśli nikt nie podbił),
call – sprawdzić (wyrównać stawkę),
raise – podbić,
fold – spasować (odpaść z rozdania).
Po ostatniej fazie, jeśli zostało więcej niż 1 gracz, następuje showdown – porównanie kart.
Każdy gracz tworzy najlepszy możliwy układ 5 kart z własnych dwóch oraz pięciu wspólnych.
Wygrywa gracz z najsilniejszym układem.

ActionHandler – odpowiada za obsługę wszystkich możliwych akcji gracza w grze w pokera (np. bet, raise, fold, call), zarządzanie fazami gry oraz aktualizowanie stanu gry, puli i stacków graczy.

GamePhase — definiuje kolejne fazy rozgrywki w pokerze

GamePlay — odpowiada za główną logikę rozgrywki pokera heads-up, zarządza przebiegiem rozdania, przekazuje akcje między graczami i kontroluje fazy gry.

GamePlayUtils — zawiera pomocnicze metody statyczne do wyświetlania informacji o stanie gry pokerowej oraz do obliczeń związanych z pulą i wysokością zakładów.

GameState — przechowuje bieżący stan gry pokerowej, w tym fazę gry, wysokość puli oraz wartości blindów, i udostępnia metody do modyfikowania puli.

HandleActionResult — reprezentuje wynik akcji gracza, informując czy doszło do zagrania typu all-in lub fold.

HandsAndBoard - przechowuje i udostępnia dwie ręce kart graczy oraz wspólne karty na stole w grze pokerowej.

Player - reprezentuje gracza w pokerze, przechowując jego stan finansowy, rękę kart, nazwy oraz informacje o zakładach i udziale w puli.

PossibleAction - definiuje możliwe akcje, które gracz może wykonać podczas rundy w grze pokerowej.

StartGamePlay - inicjalizuje i uruchamia pełną rozgrywkę pokerową, tworząc talię, graczy, stan gry oraz obsługę akcji i testy.

Card - reprezentuje pojedynczą kartę do gry, przechowując jej wartość i kolor oraz umożliwiając wygodne formatowanie na tekstową reprezentację.

Deck - tworzy i zarządza pełną talią kart, umożliwiając tasowanie oraz generowanie rąk i kart na stole do gry pokerowej.

EquityEvaluator - oblicza i przechowuje szanse wygranej dwóch rąk pokerowych na różnych etapach gry oraz zawiera metody sprawdzające i zwracające wartości pokerowych układów takich jak kareta, full, kolor itp.
na podstawie kart gracza i wspólnych kart.

EquityEvaluatorTest - to klasa służąca do testowania wybranych rozdań pokerowych, pozwalająca samodzielnie zdefiniować ręce graczy i karty wspólne, aby przeanalizować i porównać ich siłę oraz obliczyć equity.

GameResult - odpowiada za przechowanie i aktualizacje wygranych obu graczy oraz remisów w rozgrywce pokerowej.

PokerTest- to klasa do ręcznego testowania rozpoznawania różnych układów pokerowych przez EquityEvaluator na wybranych rozdaniach.

ResultHandOut - to klasa przechowująca wynik oceny układu pokerowego

pokerTest (Package) to część projektu innego użytkownika, który pobrałem i dołączyłem do swojego kodu, zawiera klasy do reprezentacji kart, układów, kalkulacji equity oraz rankingów rąk pokerowych,
z którego korzystałem w swojej klasie EquityEvaluatorTest do testowania i poprawienia działania swojej aplikacji.

tests (Package) zawiera wszystko, co potrzebne do kontrolowanych testów — umożliwia ręczne ustalanie kart graczy i boarda oraz pełną kontrolę nad przebiegiem rozgrywki przez określanie decyzji graczy
(bet, call, raise) wraz z wartościami zakładów. Zawiera także mechanizmy sprawdzające, czy po rozdaniu stacki graczy mają odpowiednią wartość.
