#Instructions fot API test project

POJO класс - модель данных, не несет никакой логики
используем над POJO классом аннотацию JsonIgnoreProperties (ignoreUnknown=true), если не хотим десериализовать поля
1) генерить pojoExampleWithLombok можно с помощью плагина мавен_конфигурации в пом + прописать код
2) использовать плагин в идее RoboPomGenerator
   - ПКМ на пустую директорию, куда хотим сгенерить pojoExampleWithLombok класс(ы)
   - Generate POJO from JSON
   - вставляем json
   - даем имя основному POJO классу
   - выбираем библиотеку и параметры генерации
3) использовать сайт https://www.jsonschema2pojo.org/
   -генерим,скачиваем,разархивируем,копируем классы в нужный пекэдж
4) написать классы руками используя lombok

При генерации POJO попадаются в json ключи вида last_name.
Однако java переименует поля в соотв. с конвенцией, и поле будет называться lastName.
чтобы явно указать, что данное поле соответствует ключу last_name мы исп.аннотацию
@JsonProperty("last_name")
private String lastName;
Теперь мы можем как угодно назвать саму переменную.
Если при генерации мы бы выбрали Jackson, то такие аннотации 
бы добавились ко всем переменным.

lombok - помогает избавиться от кучи кода

Принцип JWT авторизации
1. Register the user with the end point
2. Authenticate and generate the token
3. Extract the token using Json path
4. Send the request with jwt token
