# Kibana Logging Guide

## Where logs are stored

The application writes logs under the project-level `logs/` directory:

- `logs/app.log`: main plain-text application log
- `logs/error.log`: error-only plain-text log
- `logs/access.log`: request/access plain-text log from `LoggingInterceptor`
- `logs/postgres.log`: Hibernate SQL log
- `logs/elk/app-json.log`: JSON log consumed by Filebeat and sent to Elasticsearch

`logs/` is relative to the Spring Boot process working directory. When you run the app from the project root, the full path is:

`/home/david/Apps/Rest/rest/logs`

## How logs look

Plain text example from `logs/app.log`:

```text
2026-03-18 14:05:33.127 INFO  [http-nio-8080-exec-1] com.train.rest.filter.LoggingInterceptor - Request: GET /api/trains status=200 durationMs=18
```

JSON example from `logs/elk/app-json.log`:

```json
{
  "@timestamp": "2026-03-18T14:05:33.127533+01:00",
  "service": "rest",
  "environment": "local",
  "level": "INFO",
  "logger": "com.train.rest.filter.LoggingInterceptor",
  "thread": "http-nio-8080-exec-1",
  "message": "Request: GET /api/trains status=200 durationMs=18",
  "exception": ""
}
```

When Filebeat ships these records to Elasticsearch, it now normalizes the fields to ECS-style names so Kibana is easier to use:

- `service` -> `service.name`
- `environment` -> `service.environment`
- `level` -> `log.level`
- `logger` -> `log.logger`
- `thread` -> `process.thread.name`
- `exception` -> `error.stack_trace`

It also adds `event.dataset=rest.application`, host metadata, and `data_stream.*` markers so local logs are closer to what you usually see in production stacks.

## How to send logs to Kibana

1. Start your Spring Boot application so it creates `logs/elk/app-json.log`.
2. Start the ELK stack:

```bash
bash src/main/java/com/train/rest/elk/start.sh
```

3. Open Kibana at `http://localhost:5601`.
4. Go to `Stack Management` -> `Data Views`.
5. Create a data view with index pattern `rest-logs-*`.
6. Choose `@timestamp` as the time field.
7. Open `Discover` and select the `rest-logs-*` data view.

## How this project is wired

- `src/main/resources/logback-spring.xml` writes both plain-text logs and JSON logs.
- `src/main/java/com/train/rest/elk/filebeat.yml` reads `logs/elk/app-json.log`, reshapes the fields to ECS-like names, and sends records to Elasticsearch.
- `src/main/java/com/train/rest/elk/docker-compose-elk.yml` runs Elasticsearch, Kibana, and Filebeat.
- `src/main/java/com/train/rest/elk/start.sh` computes the project log path and starts the stack.

## Notes

- Kibana does not store logs by itself. Logs are indexed in Elasticsearch, and Kibana reads them from there.
- Filebeat only ships the JSON log file. Your plain-text files stay local on disk.
- The Elasticsearch index is still `rest-logs-*`, but the stored document shape is now closer to production-friendly ECS conventions.
- If no logs appear in Kibana, first confirm the app has written `logs/elk/app-json.log`.
