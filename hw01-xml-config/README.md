Домашнее задание №1 (hw01-xml-config)

Описание задания:

В ресурсах хранятся вопросы и различные ответы к ним в виде CSV файла (5 вопросов).
Вопросы могут быть с выбором из нескольких вариантов или со свободным ответом - на Ваше желание и усмотрение.
Приложение должна просто вывести вопросы теста из CSV-файла с возможными вариантами ответа (если имеются).


Требования:
1. В приложении должна присутствовать объектная модель (отдаём предпочтение объектам и классам, а не строчкам и массивам/спискам строчек).

2. Все классы в приложении должны решать строго определённую задачу (см. п. 18-19 "Правила оформления кода.pdf", прикреплённые к материалам занятия).
3. Контекст описывается XML-файлом.
4. Все зависимости должны быть настроены в IoC контейнере.
5. Имя ресурса с вопросами (CSV-файла) необходимо захардкодить строчкой в XML-файле с контекстом.
6. CSV с вопросами читается именно как ресурс, а не как файл.
7. Scanner, PrintStream и другие стандартные типы в контекст класть не нужно!
8. Весь ввод-вывод осуществляется на английском языке.
9. Крайне желательно написать юнит-тест какого-нибудь сервиса (оцениваться будет только попытка написать тест).
10. Помним - "без фанатизма".