# MyWarehouse
Для запуска приложения:
1. Соберите war файл: maven clean compile
2. Запустите MyWarehouseApplication

Все эндпоинты можно посмотреть ниже или на "/swagger-ui.html"

![image](https://github.com/GlebDani/MyWarehouse/assets/140164257/397cce81-ebc6-467b-abf4-29b291d1e0b5)

Чтобы добавить новый товар необходимо передать в теле JSON объект типа:

    {
      "stockNum": "097796",
      "name": "string",
      "description": "string",
      "price": 0,
      "inStock": true
    }
