##Zadanie 8
##Wymagania
* Python 3 (testowano na 3.9.1)
* requests (testowano na 2.25.1)
  * instalacja np. przez "pip install requests"

dodatkowo to wymaganie pojawia się w requirements.txt.

##Użycie

        py nbd8.py w celu uruchomienia kreatora/skorzystania z domyślnych
        py nbd8.py ([-h (Host/IP)] [-p (port)] [-b (bucket)] [-i (dokument.json)] [-o (komunikaty.txt)] [-m (klucz:wartosc)] [-v]) / [-h] / [--version]
 
        -h, --help      pokazanie tej informacji i wyjście
        -s, --server    Host/IP serwera na którym jest serwer riak (domyślnie: localhost)
        -p, --port      Port serwera (domyślnie: 8098)
        -b, --bucket    bucket na którym mają być operacje (domyślnie: s13846)
        -i, --dokument  plik dokumentu na którym mają być przeprowadzone operacje (domyślnie: dokument.json)
        -o, --kom       plik z komunikatami (domyślnie: komunikaty.txt)
        -m, --mod       zmiany (nadpisanie/dodatnie) w formacie KLUCZ|WARTOŚĆ (domyślnie: autor|"s13846")
        -v, --verbose   tryb gadatliwy - więcej komunikatów podczas realiacji programu
        --version       wersja programu i informacje o autorze