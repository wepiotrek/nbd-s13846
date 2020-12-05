#!/bin/bash
for f in *.js ; do fString="${f%.js}.csv"; mongo nbd "$f" >> "${fString/zapytanie/wyniki}" ; done