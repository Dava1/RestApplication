#!/bin/bash

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
COMPOSE_FILE="${SCRIPT_DIR}/docker-compose-elk.yml"

if docker compose version >/dev/null 2>&1; then
  COMPOSE_CMD=(docker compose)
elif command -v docker-compose >/dev/null 2>&1; then
  COMPOSE_CMD=(docker-compose)
else
  echo "Error: neither 'docker compose' nor 'docker-compose' is installed."
  exit 1
fi

echo "Starting ELK stack using ${COMPOSE_FILE}..."
"${COMPOSE_CMD[@]}" -f "${COMPOSE_FILE}" up -d

echo "Elasticsearch: http://localhost:9200"
echo "Kibana: http://localhost:5601"
