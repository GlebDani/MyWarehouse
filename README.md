# MyWarehouse
Для запуска приложения:
1. Соберите war файл: maven clean compile
2. Запустите MyWarehouseApplication

Все эндпоинты представлены ниже, а также доступны по "/swagger-ui/index.html".

![image](https://github.com/GlebDani/MyWarehouse/assets/140164257/397cce81-ebc6-467b-abf4-29b291d1e0b5)

Чтобы добавить новый товар или обновить имеющийся необходимо передать в теле JSON объект типа:

    {
      "stockNum": "097796",
      "name": "string",
      "description": "string",
      "price": 0,
      "inStock": true
    }
