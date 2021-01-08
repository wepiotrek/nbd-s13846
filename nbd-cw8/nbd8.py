#!/usr/bin/python3
import sys, getopt
import requests
import json
import tkinter as tk
from tkinter import filedialog
_version = '1.0a'

class NBD8:
    def __init__(self, arx, serverHOST='localhost', bucket='s13846', serverPort=8098, docFile='dokument.json', komFile='komunikaty.txt', mod='autor|s13846',verbose=False):
        self.arx = arx
        self.serverHOST = serverHOST
        self.bucket = bucket
        self.serverPort = serverPort
        self.docFile = docFile
        self.komFile = komFile
        self.mod = mod
        self.verbose = False
        self.kom = []
    #wrzuci do bazy dokument, pobierze go i wypisze, zmodyfikuje go, następnie pobierze i wypisze, a na końcu usunie go i spróbuje pobrać z bazy

    def usage(self): #instrukcje użycia
        print('Użycie: \n\tpy nbd8.py w celu uruchomienia kreatora/skorzystania z domyślnych\n\tpy nbd8.py ([-h (Host/IP)] [-p (port)] [-b (bucket)] [-i (dokument.json)] [-o (komunikaty.txt)] [-m (klucz:wartosc)] [-v]) / [-h] / [--version]')
        print('Argumenty:\n\t-h, --help \tpokazanie tej informacji i wyjście\n\t-s, --server\tHost/IP serwera na którym jest serwer riak (domyślnie: localhost)\n\t-p, --port\tPort serwera (domyślnie: 8098)'+
        '\n\t-b, --bucket\tbucket na którym mają być operacje (domyślnie: s13846)\n\t-i, --dokument\tplik dokumentu na którym mają być przeprowadzone operacje (domyślnie: dokument.json)\n\t'+
        '-o, --kom\tplik z komunikatami (domyślnie: komunikaty.txt)\n\t-m, --mod\tzmiany (nadpisanie/dodatnie) w formacie KLUCZ|WARTOŚĆ (domyślnie: autor|"s13846")\n\t-v, --verbose\ttryb gadatliwy - więcej komunikatów podczas realiacji programu'+
        '\n\t--version\twersja programu i informacje o autorze')

    def selectEx(self):
        print("1) Wprowadź ścieżkę do pliku z dokumentem\n2) Przejdź do dialogu wyboru pliku\n3) Błąd i zakończ program")
        menu = input("Twój wybór [1-3]:")
        if menu == "1":
            print("wybór")
            newLoc=input("Podaj ścieżkę do pliku z dokumentem:")
            self.docFile = newLoc
            self.loadDoc()
        elif menu == "2":
            root = tk.Tk()
            root.withdraw()
            self.docFile = filedialog.askopenfilename()
            self.loadDoc()
        elif menu == "3":
            sys.exit(3)
        else:
            print("Błędna odpowiedź!")
            self.selectEx()

    def loadDoc(self): #otwieranie pliku z dokumentem
        try:
            f = open(self.docFile)
        except IOError:
            print(f'Uwaga!: Nie można otworzyć pliku "{self.docFile}"!')
            self.selectEx()
        else:
            with f:
                docData = json.load(f)
                return docData
                
    def wgrywanie(self, dane, url=''):
        if url == '':
            url = f'http://{self.serverHOST}:{self.serverPort}/buckets/{self.bucket}/keys/'
        if(self.verbose):
            print(f'Wgrywanie {dane}')
            print(f'URL:{url}')
        try:
            self.kom.append(f'WGRYWANIE danych {dane} pod URL: {url}')
            r = requests.post(url, json = dane)
            self.kom.append(f'WYNIK: kod odpowiedzi {r.status_code}, nagłówki: {r.headers}')
            if r.status_code==201: #201 -Created (powinno się stworzyć)
                print(r.headers['Location'])
                return f'http://{self.serverHOST}:{self.serverPort}{r.headers["Location"]}'
            elif r.status_code==204: #204 - No Content (powinnno się zmodyfikować)
                return url
            else:
                print(f'Nie zwrócono spodziewanego kodu odpowiedzi tylko {r.status_code}!')
        except requests.exceptions.RequestException: #ConnectionRefusedError:
            print(f'Nie można połączyć się z serwerem {url}!')

    def pobranie(self, klucz):
        #print(f'Pobieranie {klucz}')
        try:
            self.kom.append(f'POBIERANIE danych z URL: {klucz}')
            r = requests.get(klucz)
            self.kom.append(f'WYNIK: kod odpowiedzi {r.status_code}, nagłówki: {r.headers}, odpowiedź: {r.text}')
            if r.status_code==200: #200 - OK (powinno być OK)
                if(self.verbose):
                    print(r.json())
                return r.json()
            else:
                print(f'Nie zwrócono spodziewanego kodu odpowiedzi tylko {r.status_code}!')
        except requests.exceptions.RequestException: #ConnectionRefusedError:
            print(f'Nie można połączyć się z serwerem {klucz}!')

    def modyfikacja(self, dane):
        arr=self.mod.split('|')
        if len(arr)!=2:
            print("Niespodziewny format modyfikacji. Wybierz akceptowalny klucz|wartość.")
            sys.exit(3)
        dane[arr[0]]=arr[1]
        return dane

    def usun(self, klucz):
        print(f'Usuwanie {klucz}')
        try:
            self.kom.append(f'USÓWANIE danych z URL: {klucz}')
            r = requests.delete(klucz)
            self.kom.append(f'WYNIK: kod odpowiedzi {r.status_code}, nagłówki: {r.headers}')
            if r.status_code==204: #204 - No Content (powinno być OK)
                if(self.verbose):
                    print("Usunięto")
            else:
                if(self.verbose):
                    print(f'Nie zwrócono spodziewanego kodu, tylko {r.status_code}!')
        except requests.exceptions.RequestException: #ConnectionRefusedError:
            print(f'Nie można połączyć się z serwerem {klucz}!')

    def main(self):
        try:
            opts, args = getopt.getopt(self.arx,"hs:p:b:i:o:m:v",["help","version","server","port","bucket","dokument","kom","mod", "verbose"])
        except getopt.GetoptError as err:
            print(f'{err}\n')  # błąd
            self.usage()
            sys.exit(2)
        for opt, arg in opts:
            if opt in ('-h', "--help"):
                self.usage()
                sys.exit()
            elif opt == '--version':
                print("NBD zadanie 8 CLI dla Python'a 3\nWersja: "+_version+"\nAutor:S13846 (Piotr Wezgraj)")
                sys.exit()
            elif opt in ("-s", "--server"):
                self.serverHOST = arg
            elif opt in ("-p", "--port"):
                self.serverPort = arg
            elif opt in ("-b", "--bucket"):
                self.bucket = arg
            elif opt in ("-i", "--dokument"):
                self.docFile = arg
            elif opt in ("-o", "-kom"):
                self.komFile = arg
            elif opt in ("-m", "--mod"):
                self.mod = arg
            elif opt in ("-v", '--verbose'):
                self.verbose = True

        dokument = self.loadDoc() #odczyt dokumentu z pliku
        kluczDok = self.wgrywanie(dokument) #wgrywanie
        odczytane = self.pobranie(kluczDok) #odczyt wgranego pliku
        nowe = self.modyfikacja(odczytane) #modyfikacja
        nowyOdczytaneKl = self.wgrywanie(nowe, kluczDok) #wgranie zmodyfikowanego
        odczytane2 = self.pobranie(nowyOdczytaneKl) #odczyt zmodyfikowanego
        self.usun(nowyOdczytaneKl) #kasownaie
        odczytaneBlad = self.pobranie(nowyOdczytaneKl) #odczytanie skasowanego
        #print(self.kom)
        with open(self.komFile, 'w') as kf:
            kf.writelines("%s\n" % l for l in self.kom)

if __name__ == "__main__":
   NBD8(sys.argv[1:]).main()