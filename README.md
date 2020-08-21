![Clojure CI](https://github.com/GoddessEyes/HealthSamuraiDemo/workflows/Clojure%20CI/badge.svg)

# health_samurai_demo

### Запуск "production" сборки

1 Создать `.env` на основе `.env.example`

2 Запустить контейнеры app/nginx/postgres:
```bash 
docker-compose up --build -d
```
3 Провести миграцию schema.sql в DB:
Пример:
```postgres-sql
psql -h localhost -U demo -f schema.sql
```

