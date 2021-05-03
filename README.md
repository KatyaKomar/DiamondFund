# XML/XSD & Parsing

Cоздан xml-файл, хранящий информацию об алмазном фоне. Для валидации полученного xml-файла разработана соответствующая ему схема xsd. Выполнен парсинг xml-документа с использованием DOM, SAX, StAX парсеров.

В задании:
* использованы для атрибутов required & optional
* использованы перечисления
* использованы шаблоны и предельные значения
* использован тип ID
* заданы значений атрибутов по умолчанию
* расширены типов (имитация наследования)
* использованы дата-время с помощью пакета java.time
* созданы в xml-документе 16 сущностей
* парсеры организованы с помощью шаблона Builder
* для записи логов использовался Log4J2
* код покрыт тестами

# Алмазный фонд
Драгоценные и полудрагоценные камни:
Name – название камня.
Preciousness – может быть драгоценным либо полудрагоценным.
Origin – место добывания.
Visual parameters – цвет, прозрачность (измеряется в процентах 0-100%), способы огранки (количество граней 4-15).
Value – вес камня.
Корневой элемент назван Gems.