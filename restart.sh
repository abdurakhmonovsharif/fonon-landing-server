#!/usr/bin/env bash
set -euo pipefail

if ! command -v docker >/dev/null 2>&1; then
  echo "docker command is required but not found in PATH" >&2
  exit 1
fi

COMPOSE_FILE="${COMPOSE_FILE:-docker-compose.yml}"
PROJECT_NAME="${COMPOSE_PROJECT_NAME:-fonon-landing}"
COMPOSE_CMD=(${COMPOSE_COMMAND:-docker compose})

echo "Restarting services using ${COMPOSE_FILE}..."
"${COMPOSE_CMD[@]}" -f "${COMPOSE_FILE}" --project-name "${PROJECT_NAME}" down
"${COMPOSE_CMD[@]}" -f "${COMPOSE_FILE}" --project-name "${PROJECT_NAME}" up -d --build "$@"
"${COMPOSE_CMD[@]}" -f "${COMPOSE_FILE}" --project-name "${PROJECT_NAME}" ps
