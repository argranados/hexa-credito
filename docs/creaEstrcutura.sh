#!/bin/bash
# Crea la estructura de carpetas hexagonal para hexa-credito
# Ejecutar desde la raíz del proyecto (donde está pom.xml)

set -e

BASE="src/main/java/com/ciberaccion/hexacredito"

mkdir -p "$BASE/domain/model"
mkdir -p "$BASE/domain/port/in"
mkdir -p "$BASE/domain/port/out"
mkdir -p "$BASE/application/usecase"
mkdir -p "$BASE/infrastructure/adapter/in/rest"
mkdir -p "$BASE/infrastructure/adapter/out/persistence"
mkdir -p "$BASE/infrastructure/config"

echo "Estructura hexagonal creada en $BASE"
find "$BASE" -type d | sort