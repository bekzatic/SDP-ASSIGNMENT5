# Калькулятор на паттерне Strategy

Задание №5, курс ShP-2216 Software Design Patterns (Astana IT University), вариант B.

## Идея в двух словах

Калькулятор (`Calculator`) сам не умеет ни складывать, ни делить. Он хранит у себя одну
«стратегию» — объект, который знает одну операцию, — и просто передаёт ей числа.
Хотите другую операцию? Дайте калькулятору другую стратегию, и для этого не нужно
перезапускать программу. Поэтому в калькуляторе нет ни `if`, ни `switch` по типу операции.

## Кто есть кто

| Роль в паттерне | Класс | Что делает |
| --- | --- | --- |
| Strategy (интерфейс) | `OperationStrategy` | Обещает метод `calculate(left, right)` |
| Concrete Strategies | `AddStrategy`, `SubtractStrategy`, `MultiplyStrategy`, `DivideStrategy` | Каждая умеет ровно одну операцию |
| Context | `Calculator` | Хранит текущую стратегию, отдаёт ей числа, умеет менять стратегию |
| Client | `CalculatorConsole` | Читает ввод, выбирает стратегию и вставляет её в калькулятор |

Вспомогательные классы клиента:
- `StrategyCatalog` — таблица «символ → стратегия» (`"+"` → `AddStrategy`). Стратегия выбирается поиском в таблице, а не цепочкой `if`.
- `ExpressionParser` — превращает строку `12 * 4` в объект `Expression`.
- `ResultFormatter` — печатает результат красиво: `3`, а не `3.0`.
- `Main` — создаёт все объекты и соединяет их между собой.

## Структура папок

```
src/main/java/kz/aitu/calculator/
├── Main.java
├── strategy/   OperationStrategy, AddStrategy, SubtractStrategy, MultiplyStrategy, DivideStrategy
├── context/    Calculator
└── client/     CalculatorConsole, StrategyCatalog, ExpressionParser, Expression, ResultFormatter
src/test/java/kz/aitu/calculator/   тесты JUnit 5
docs/REPORT.md                      отчёт
```

## Как запустить

Нужны JDK 17 и Maven (Maven уже встроен в IntelliJ IDEA).

- В IntelliJ откройте папку как Maven-проект и запустите `Main`.
- Из терминала используйте `mvn test` (тесты) и `mvn compile exec:java` (программа).

## Пример работы

```
== Same numbers, every strategy ==
[AddStrategy] 12 + 4 = 16
[SubtractStrategy] 12 - 4 = 8
[MultiplyStrategy] 12 * 4 = 48
[DivideStrategy] 12 / 4 = 3

Type an expression like 12 * 4, or 'quit' to exit.
> 7 - 10
[SubtractStrategy] 7 - 10 = -3
> 9 / 0
[DivideStrategy] Error: cannot divide by zero
> 5 % 2
Unknown operator '%'. Available: [+, -, *, /]
> quit
```

Имя в квадратных скобках показывает, какая стратегия сейчас стоит в калькуляторе.
Так видно, что она меняется прямо во время работы программы.

## Как добавить новую операцию

Например, возведение в степень:

1. Создайте класс `PowerStrategy implements OperationStrategy` с `return Math.pow(left, right);`.
2. Добавьте в `Main` одну строку: `.register("^", new PowerStrategy())`.

Больше ничего менять не нужно: ни `Calculator`, ни остальные стратегии, ни их тесты.
