![Clojure test](https://github.com/GoddessEyes/HealthSamuraiDemo/workflows/Clojure%20test/badge.svg)
![Clojure build and style guide](https://github.com/GoddessEyes/HealthSamuraiDemo/workflows/Clojure%20build%20and%20style%20guide/badge.svg)

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

