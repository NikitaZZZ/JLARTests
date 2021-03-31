package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Controller {
    // Поля, что ссылаются на объекты внутри дизайна
    @FXML
    private ToggleGroup answers;

    @FXML
    private Text question_text;

    @FXML
    private RadioButton radio_btn_1;

    @FXML
    private RadioButton radio_btn_2;

    @FXML
    private RadioButton radio_btn_3;

    @FXML
    private RadioButton radio_btn_4;

    @FXML
    private Button answerBtn;

    @FXML
    private Button closeApp;

    @FXML
    private Button test_java_btn;

    @FXML
    private Button test_js_btn;

    @FXML
    private Button test_python_btn;

    @FXML
    private Text title_test;

    @FXML
    private Button correctAnswersBtn;

    @FXML
    private Text correctAnswerText;

    @FXML
    private Text userAnswerText;

    @FXML
    private Button nextCorrectAnswerBtn;

    // Массив на основе класса Questions. Каждый объект – вопрос с набором возможных ответов
    private final questions[] test_java = new questions[] {
            new questions("В каком из вариантов представлен корректный формат вывода информации на экран?", new String[] {"Console.Write()", "console.log()", "print()", "System.out.println()"}),
            new questions("Какой тип данных отвечает за целые числа?", new String[] {"String", "Float", "Boolean", "Integer"}),
            new questions("Где правильно присвоено новое значение к многомерному массиву?", new String[] {"a(0)(0) = 1;", "a[0 0] = 1;", "a{0}{0} = 1;", "a[0][0] = 1;"}),
            new questions("Какой метод позволяет запустить программу на Java?", new String[] {"Основного метода нет", "С класса, что был написан первым и с методов что есть внутри него", "Любой, его можно задавать в настройках проекта", "С метода main в любом из классов"}),
            new questions("Каждый файл должен называется...", new String[] {"по имени первой библиотеки в нём", "по имени названия пакета", "как вам захочется", "по имени класса в нём"}),
    };

    private final questions[] test_javascript = new questions[] {
            new questions("В чем разница между confirm и prompt?",
                    new String[] {"confirm и prompt нет в JavaScript",
                            "Они ничем не отличаются",
                            "confirm вызывает окно с полем для ввода, prompt - окно с подтверждением",
                            "prompt вызывает окно с полем для ввода, confirm - окно с подтверждением"}),
            new questions("Где верно указан вывод данных в консоль?",
                    new String[] {"document.write('Hello World!')",
                            "prompt('Hello World!')",
                            "print('Hello World!')",
                            "console.log('Hello World!')"}),
            new questions("Где можно использовать JavaScript?",
                    new String[] {"Веб-приложения",
                            "ПО",
                            "Серверные приложения",
                            "Можно во всех перечисленных"}),
            new questions(" Какие функции выполняет JS?\n" +
                    "* В вопросе не идет речь про платформу Node JS",
                    new String[] {"Создает стилевое оформление сайта",
                            "Отвечает за работу с базами данных",
                            "Выполняет работу с сервером",
                            "Отвечает за функции на стороне клиента"}),
            new questions("Какая переменная записана неверно?",
                    new String[] {"var isDone = 0;",
                            "var num = 'STRING';",
                            "var b = false;",
                            "var number = 12,5;"}),
    };

    private final questions[] test_python = new questions[] {
            new questions("Что выведет 'print(type(1 / 2))', при его исполнении? \n Используется Python 3.x.",
                    new String[] {"class 'int'",
                            "class 'number'",
                            "class 'double'",
                            "class 'float'"}),
            new questions(" Где правильно создана переменная?",
                    new String[] {"var num = 2",
                            "Нет ответа",
                            "$num = 2",
                            "num = float(2)"}),
            new questions("Сколько библиотек можно импортировать в один проект?",
                    new String[] {"Не более 10",
                            "Не более 23",
                            "Не более 3",
                            "Неограниченное количество"}),
            new questions("Как получить данные от пользователя?",
                    new String[] {"Использовать метод readLine()",
                            "Использовать метод get()",
                            "Использовать метод read()",
                            "Использовать метод input()"}),
            new questions("Какая функция выводит что-либо в консоль?",
                    new String[] {"out()",
                            "log()",
                            "write()",
                            "print()"}),
    };

    // Переменные для установки текущего номера вопроса и для подсчета количества верных ответов
    private int nowQuestionTestJava = 0,
            nowQuestionTestJS = 0,
            nowQuestionTestPython = 0,
            correctAnswers;

    // В эту переменную будет устанавливаться корректный ответ текущего вопроса
    private String nowCorrectAnswer;

    // Текущий тест
    private String currentTest = "test_java";

    @FXML
    public void initialize() {
        radio_btn_1.setVisible(false); // Скрываем все поля для выбора
        radio_btn_2.setVisible(false);
        radio_btn_3.setVisible(false);
        radio_btn_4.setVisible(false);
        answerBtn.setVisible(false); // Скрываем кнопку ответа

        // Массив правильных ответов
        String[] correctAnswersArray = new String[5];

        // Массив ответов пользователя
        String[] answersUser = new String[5];

        // Отслеживание нажатия на кнопку "Закрыть программу"
        closeApp.setOnAction(e -> System.exit(0));

        // Тест по языку JavaScript
        test_js_btn.setOnAction(e -> {
            title_test.setText("Тест по языку JavaScript");
            currentTest = "test_javascript";

            radio_btn_1.setDisable(false); // Включаем radio кнопки
            radio_btn_2.setDisable(false);
            radio_btn_3.setDisable(false);
            radio_btn_4.setDisable(false);

            // Очищаем номер вопроса
            nowQuestionTestJS = 0;

            // Очищаем массивы правильных ответов и ответов пользователя
            Arrays.fill(correctAnswersArray, null);
            Arrays.fill(answersUser, null);

            // Скрываем текст "Правильный ответ" и "Ваш ответ"
            correctAnswerText.setVisible(false);
            userAnswerText.setVisible(false);

            // Указываем новый текст верного ответа
            nowCorrectAnswer = test_javascript[nowQuestionTestJS].correctAnswer();

            // Меняем текст вопроса в программе
            question_text.setText(test_javascript[nowQuestionTestJS].getQuestion());

            // Показываем все поля для выбора
            radio_btn_1.setVisible(true);
            radio_btn_2.setVisible(true);
            radio_btn_3.setVisible(true);
            radio_btn_4.setVisible(true);
            answerBtn.setVisible(true); // Показываем кнопку ответа

            // Получаем массив ответов
            String[] answers = test_javascript[nowQuestionTestJS].getAnswers();

            // Преобразовываем в список (так удобнее сортировать элементы)
            List<String> intList = Arrays.asList(answers);

            // Сортируем в случайном порядке
            Collections.shuffle(intList);

            // Подставляем ответы в радио кнопки
            radio_btn_1.setText(intList.get(0));
            radio_btn_2.setText(intList.get(1));
            radio_btn_3.setText(intList.get(2));
            radio_btn_4.setText(intList.get(3));
        });

        // Тест по языку Python
        test_python_btn.setOnAction(e -> {
            title_test.setText("Тест по языку Python");
            currentTest = "test_python";

            radio_btn_1.setDisable(false); // Включаем radio кнопки
            radio_btn_2.setDisable(false);
            radio_btn_3.setDisable(false);
            radio_btn_4.setDisable(false);

            // Очищаем номер вопроса
            nowQuestionTestPython = 0;

            // Очищаем массивы правильных ответов и ответов пользователя
            Arrays.fill(correctAnswersArray, null);
            Arrays.fill(answersUser, null);

            // Скрываем текст "Правильный ответ" и "Ваш ответ"
            correctAnswerText.setVisible(false);
            userAnswerText.setVisible(false);

            // Указываем новый текст верного ответа
            nowCorrectAnswer = test_python[nowQuestionTestPython].correctAnswer();

            // Меняем текст вопроса в программе
            question_text.setText(test_python[nowQuestionTestPython].getQuestion());

            // Показываем все поля для выбора
            radio_btn_1.setVisible(true);
            radio_btn_2.setVisible(true);
            radio_btn_3.setVisible(true);
            radio_btn_4.setVisible(true);
            answerBtn.setVisible(true); // Показываем кнопку ответа

            // Получаем массив ответов
            String[] answers = test_python[nowQuestionTestPython].getAnswers();

            // Преобразовываем в список (так удобнее сортировать элементы)
            List<String> intList = Arrays.asList(answers);

            // Сортируем в случайном порядке
            Collections.shuffle(intList);

            // Подставляем ответы в радио кнопки
            radio_btn_1.setText(intList.get(0));
            radio_btn_2.setText(intList.get(1));
            radio_btn_3.setText(intList.get(2));
            radio_btn_4.setText(intList.get(3));
        });

        // Тест по языку Java
        test_java_btn.setOnAction(e -> {
            title_test.setText("Тест по языку Java");
            currentTest = "test_java";

            radio_btn_1.setDisable(false); // Включаем radio кнопки
            radio_btn_2.setDisable(false);
            radio_btn_3.setDisable(false);
            radio_btn_4.setDisable(false);

            // Очищаем номер вопроса
            nowQuestionTestJava = 0;

            // Очищаем массивы правильных ответов и ответов пользователя
            Arrays.fill(correctAnswersArray, null);
            Arrays.fill(answersUser, null);

            // Скрываем текст "Правильный ответ" и "Ваш ответ"
            correctAnswerText.setVisible(false);
            userAnswerText.setVisible(false);

            // Указываем новый текст верного ответа
            nowCorrectAnswer = test_java[nowQuestionTestJava].correctAnswer();

            // Меняем текст вопроса в программе
            question_text.setText(test_java[nowQuestionTestJava].getQuestion());

            // Показываем все поля для выбора
            radio_btn_1.setVisible(true);
            radio_btn_2.setVisible(true);
            radio_btn_3.setVisible(true);
            radio_btn_4.setVisible(true);
            answerBtn.setVisible(true); // Показываем кнопку ответа

            // Получаем массив ответов
            String[] answers = test_java[nowQuestionTestJava].getAnswers();

            // Преобразовываем в список (так удобнее сортировать элементы)
            List<String> intList = Arrays.asList(answers);

            // Сортируем в случайном порядке
            Collections.shuffle(intList);

            // Подставляем ответы в радио кнопки
            radio_btn_1.setText(intList.get(0));
            radio_btn_2.setText(intList.get(1));
            radio_btn_3.setText(intList.get(2));
            radio_btn_4.setText(intList.get(3));
        });

        // Результаты теста
        correctAnswersBtn.setOnAction(e -> {
            nowQuestionTestJava = 0;
            nowQuestionTestPython = 0;
            nowQuestionTestJS = 0;

            radio_btn_1.setDisable(true); // Выключаем radio кнопки
            radio_btn_2.setDisable(true);
            radio_btn_3.setDisable(true);
            radio_btn_4.setDisable(true);

            switch (currentTest) {
                case "test_java":
                    if (nowQuestionTestJava + 1 == test_java.length) {
                        System.out.println("The End!");
                    } else { // Если еще есть вопросы...
                        radio_btn_1.setVisible(true); // Показываем все поля для выбора
                        radio_btn_2.setVisible(true);
                        radio_btn_3.setVisible(true);
                        radio_btn_4.setVisible(true);

                        // Показываем кнопку "Далее"
                        nextCorrectAnswerBtn.setVisible(true);

                        // Скрываем кнопку "Показать ответы"
                        correctAnswersBtn.setVisible(false);

                        correctAnswerText.setVisible(true); // Показываем текст результа
                        userAnswerText.setVisible(true);

                        // Показываем правильный ответ и ответ пользователя
                        correctAnswerText.setText("Правильный ответ: " + correctAnswersArray[nowQuestionTestJava]);
                        userAnswerText.setText("Ваш ответ: " + answersUser[nowQuestionTestJava]);

                        System.out.println("Правильный ответ: " + correctAnswersArray[nowQuestionTestJava]);
                        System.out.println("Ваш ответ: " + answersUser[nowQuestionTestJava]);
                        System.out.println("Вопрос - " + nowQuestionTestJava);

                        // Меняем текст вопроса в программе
                        question_text.setText(test_java[nowQuestionTestJava].getQuestion());

                        // Получаем массив ответов
                        String[] answers = test_java[nowQuestionTestJava].getAnswers();

                        // Преобразовываем в список (так удобнее сортировать элементы)
                        List<String> intList = Arrays.asList(answers);


                        // Подставляем ответы в радио кнопки
                        radio_btn_1.setText(intList.get(0));
                        radio_btn_2.setText(intList.get(1));
                        radio_btn_3.setText(intList.get(2));
                        radio_btn_4.setText(intList.get(3));

                        // Увеличиваем номер текущего вопроса
                        nowQuestionTestJava++;
                    }
                    break;
                case "test_javascript":
                    if (nowQuestionTestJS + 1 == test_javascript.length) {
                        System.out.println("The End!");
                    } else { // Если еще есть вопросы...
                        radio_btn_1.setVisible(true); // Показываем все поля для выбора
                        radio_btn_2.setVisible(true);
                        radio_btn_3.setVisible(true);
                        radio_btn_4.setVisible(true);

                        // Показываем кнопку "Далее"
                        nextCorrectAnswerBtn.setVisible(true);

                        // Скрываем кнопку "Показать ответы"
                        correctAnswersBtn.setVisible(false);

                        correctAnswerText.setVisible(true); // Показываем текст результа
                        userAnswerText.setVisible(true);

                        // Показываем правильный ответ и ответ пользователя
                        correctAnswerText.setText("Правильный ответ: " + correctAnswersArray[nowQuestionTestJS]);
                        userAnswerText.setText("Ваш ответ: " + answersUser[nowQuestionTestJS]);

                        System.out.println("Правильный ответ: " + correctAnswersArray[nowQuestionTestJS]);
                        System.out.println("Ваш ответ: " + answersUser[nowQuestionTestJS]);
                        System.out.println("Вопрос - " + nowQuestionTestJS);

                        // Меняем текст вопроса в программе
                        question_text.setText(test_javascript[nowQuestionTestJS].getQuestion());

                        // Получаем массив ответов
                        String[] answers = test_javascript[nowQuestionTestJS].getAnswers();

                        // Преобразовываем в список (так удобнее сортировать элементы)
                        List<String> intList = Arrays.asList(answers);

                        // Подставляем ответы в радио кнопки
                        radio_btn_1.setText(intList.get(0));
                        radio_btn_2.setText(intList.get(1));
                        radio_btn_3.setText(intList.get(2));
                        radio_btn_4.setText(intList.get(3));

                        // Увеличиваем номер текущего вопроса
                        nowQuestionTestJS++;
                    }
                    break;
                case "test_python":
                    if (nowQuestionTestPython + 1 == test_python.length) {
                        System.out.println("The End!");
                    } else { // Если еще есть вопросы...
                        radio_btn_1.setVisible(true); // Показываем все поля для выбора
                        radio_btn_2.setVisible(true);
                        radio_btn_3.setVisible(true);
                        radio_btn_4.setVisible(true);

                        // Показываем кнопку "Далее"
                        nextCorrectAnswerBtn.setVisible(true);

                        // Скрываем кнопку "Показать ответы"
                        correctAnswersBtn.setVisible(false);

                        correctAnswerText.setVisible(true); // Показываем текст результа
                        userAnswerText.setVisible(true);

                        // Показываем правильный ответ и ответ пользователя
                        correctAnswerText.setText("Правильный ответ: " + correctAnswersArray[nowQuestionTestPython]);
                        userAnswerText.setText("Ваш ответ: " + answersUser[nowQuestionTestPython]);

                        System.out.println("Правильный ответ: " + correctAnswersArray[nowQuestionTestPython]);
                        System.out.println("Ваш ответ: " + answersUser[nowQuestionTestPython]);
                        System.out.println("Вопрос - " + nowQuestionTestPython);

                        // Меняем текст вопроса в программе
                        question_text.setText(test_python[nowQuestionTestPython].getQuestion());

                        // Получаем массив ответов
                        String[] answers = test_python[nowQuestionTestPython].getAnswers();

                        // Преобразовываем в список (так удобнее сортировать элементы)
                        List<String> intList = Arrays.asList(answers);

                        // Подставляем ответы в радио кнопки
                        radio_btn_1.setText(intList.get(0));
                        radio_btn_2.setText(intList.get(1));
                        radio_btn_3.setText(intList.get(2));
                        radio_btn_4.setText(intList.get(3));

                        // Увеличиваем номер текущего вопроса
                        nowQuestionTestPython++;
                    }
                    break;
            }
        });

        // Кнопка далее (Результаты теста)
        nextCorrectAnswerBtn.setOnAction(e -> {
            switch (currentTest) {
                case "test_java":
                    if (nowQuestionTestJava + 1 == test_java.length) {
                        radio_btn_1.setVisible(true); // Показываем все поля для выбора
                        radio_btn_2.setVisible(true);
                        radio_btn_3.setVisible(true);
                        radio_btn_4.setVisible(true);

                        // Скрываем кнопку "Далее"
                        nextCorrectAnswerBtn.setVisible(false);

                        correctAnswerText.setVisible(true); // Показываем текст результа
                        userAnswerText.setVisible(true);

                        // Меняем текст вопроса в программе
                        question_text.setText(test_java[nowQuestionTestJava].getQuestion());

                        // Получаем массив ответов
                        String[] answers = test_java[nowQuestionTestJava].getAnswers();

                        // Преобразовываем в список (так удобнее сортировать элементы)
                        List<String> intList = Arrays.asList(answers);

                        // Подставляем ответы в радио кнопки
                        radio_btn_1.setText(intList.get(0));
                        radio_btn_2.setText(intList.get(1));
                        radio_btn_3.setText(intList.get(2));
                        radio_btn_4.setText(intList.get(3));

                        // Показываем правильный ответ и ответ пользователя
                        correctAnswerText.setText("Правильный ответ: " + correctAnswersArray[nowQuestionTestJava]);
                        userAnswerText.setText("Ваш ответ: " + answersUser[nowQuestionTestJava]);

                        // Вывод в консоль ответов и номер вопроса
                        System.out.println("Правильный ответ: " + correctAnswersArray[nowQuestionTestJava]);
                        System.out.println("Ваш ответ: " + answersUser[nowQuestionTestJava]);
                        System.out.println("Номер вопроса - " + nowQuestionTestJava);
                    } else { // Если еще есть вопросы...
                        radio_btn_1.setVisible(true); // Показываем все поля для выбора
                        radio_btn_2.setVisible(true);
                        radio_btn_3.setVisible(true);
                        radio_btn_4.setVisible(true);

                        correctAnswerText.setVisible(true); // Показываем текст результа
                        userAnswerText.setVisible(true);

                        // Показываем кнопку ответа и меняем ее текст на "Далее"
                        correctAnswersBtn.setText("Далее");

                        // Показываем правильный ответ и ответ пользователя
                        correctAnswerText.setText("Правильный ответ: " + correctAnswersArray[nowQuestionTestJava]);
                        userAnswerText.setText("Ваш ответ: " + answersUser[nowQuestionTestJava]);

                        System.out.println("Правильный ответ: " + correctAnswersArray[nowQuestionTestJava]);
                        System.out.println("Ваш ответ: " + answersUser[nowQuestionTestJava]);
                        System.out.println("Вопрос - " + nowQuestionTestJava);

                        // Меняем текст вопроса в программе
                        question_text.setText(test_java[nowQuestionTestJava].getQuestion());

                        // Получаем массив ответов
                        String[] answers = test_java[nowQuestionTestJava].getAnswers();

                        // Преобразовываем в список (так удобнее сортировать элементы)
                        List<String> intList = Arrays.asList(answers);

                        // Подставляем ответы в радио кнопки
                        radio_btn_1.setText(intList.get(0));
                        radio_btn_2.setText(intList.get(1));
                        radio_btn_3.setText(intList.get(2));
                        radio_btn_4.setText(intList.get(3));

                        // Увеличиваем номер текущего вопроса
                        nowQuestionTestJava++;
                    }
                    break;
                case "test_javascript":
                    if (nowQuestionTestJS + 1 == test_javascript.length) {
                        radio_btn_1.setVisible(true); // Показываем все поля для выбора
                        radio_btn_2.setVisible(true);
                        radio_btn_3.setVisible(true);
                        radio_btn_4.setVisible(true);

                        // Скрываем кнопку "Далее"
                        nextCorrectAnswerBtn.setVisible(false);

                        correctAnswerText.setVisible(true); // Показываем текст результа
                        userAnswerText.setVisible(true);

                        // Меняем текст вопроса в программе
                        question_text.setText(test_javascript[nowQuestionTestJS].getQuestion());

                        // Получаем массив ответов
                        String[] answers = test_javascript[nowQuestionTestJS].getAnswers();

                        // Преобразовываем в список (так удобнее сортировать элементы)
                        List<String> intList = Arrays.asList(answers);

                        // Подставляем ответы в радио кнопки
                        radio_btn_1.setText(intList.get(0));
                        radio_btn_2.setText(intList.get(1));
                        radio_btn_3.setText(intList.get(2));
                        radio_btn_4.setText(intList.get(3));

                        // Показываем правильный ответ и ответ пользователя
                        correctAnswerText.setText("Правильный ответ: " + correctAnswersArray[nowQuestionTestJS]);
                        userAnswerText.setText("Ваш ответ: " + answersUser[nowQuestionTestJS]);

                        // Вывод в консоль ответов и номер вопроса
                        System.out.println("Правильный ответ: " + correctAnswersArray[nowQuestionTestJS]);
                        System.out.println("Ваш ответ: " + answersUser[nowQuestionTestJS]);
                        System.out.println("Номер вопроса - " + nowQuestionTestJS);
                    } else { // Если еще есть вопросы...
                        radio_btn_1.setVisible(true); // Показываем все поля для выбора
                        radio_btn_2.setVisible(true);
                        radio_btn_3.setVisible(true);
                        radio_btn_4.setVisible(true);

                        correctAnswerText.setVisible(true); // Показываем текст результа
                        userAnswerText.setVisible(true);

                        // Показываем кнопку ответа и меняем ее текст на "Далее"
                        correctAnswersBtn.setText("Далее");

                        // Показываем правильный ответ и ответ пользователя
                        correctAnswerText.setText("Правильный ответ: " + correctAnswersArray[nowQuestionTestJS]);
                        userAnswerText.setText("Ваш ответ: " + answersUser[nowQuestionTestJS]);

                        System.out.println("Правильный ответ: " + correctAnswersArray[nowQuestionTestJS]);
                        System.out.println("Ваш ответ: " + answersUser[nowQuestionTestJS]);
                        System.out.println("Вопрос - " + nowQuestionTestJS);

                        // Меняем текст вопроса в программе
                        question_text.setText(test_javascript[nowQuestionTestJS].getQuestion());

                        // Получаем массив ответов
                        String[] answers = test_javascript[nowQuestionTestJS].getAnswers();

                        // Преобразовываем в список (так удобнее сортировать элементы)
                        List<String> intList = Arrays.asList(answers);

                        // Подставляем ответы в радио кнопки
                        radio_btn_1.setText(intList.get(0));
                        radio_btn_2.setText(intList.get(1));
                        radio_btn_3.setText(intList.get(2));
                        radio_btn_4.setText(intList.get(3));

                        // Увеличиваем номер текущего вопроса
                        nowQuestionTestJS++;
                    }
                    break;
                case "test_python":
                    if (nowQuestionTestPython + 1 == test_python.length) {
                        radio_btn_1.setVisible(true); // Показываем все поля для выбора
                        radio_btn_2.setVisible(true);
                        radio_btn_3.setVisible(true);
                        radio_btn_4.setVisible(true);

                        // Скрываем кнопку "Далее"
                        nextCorrectAnswerBtn.setVisible(false);

                        correctAnswerText.setVisible(true); // Показываем текст результа
                        userAnswerText.setVisible(true);

                        // Меняем текст вопроса в программе
                        question_text.setText(test_python[nowQuestionTestPython].getQuestion());

                        // Получаем массив ответов
                        String[] answers = test_python[nowQuestionTestPython].getAnswers();

                        // Преобразовываем в список (так удобнее сортировать элементы)
                        List<String> intList = Arrays.asList(answers);

                        // Подставляем ответы в радио кнопки
                        radio_btn_1.setText(intList.get(0));
                        radio_btn_2.setText(intList.get(1));
                        radio_btn_3.setText(intList.get(2));
                        radio_btn_4.setText(intList.get(3));

                        // Показываем правильный ответ и ответ пользователя
                        correctAnswerText.setText("Правильный ответ: " + correctAnswersArray[nowQuestionTestPython]);
                        userAnswerText.setText("Ваш ответ: " + answersUser[nowQuestionTestPython]);

                        // Вывод в консоль ответов и номер вопроса
                        System.out.println("Правильный ответ: " + correctAnswersArray[nowQuestionTestPython]);
                        System.out.println("Ваш ответ: " + answersUser[nowQuestionTestPython]);
                        System.out.println("Номер вопроса - " + nowQuestionTestPython);
                    } else { // Если еще есть вопросы...
                        radio_btn_1.setVisible(true); // Показываем все поля для выбора
                        radio_btn_2.setVisible(true);
                        radio_btn_3.setVisible(true);
                        radio_btn_4.setVisible(true);

                        correctAnswerText.setVisible(true); // Показываем текст результа
                        userAnswerText.setVisible(true);

                        // Показываем кнопку ответа и меняем ее текст на "Далее"
                        correctAnswersBtn.setText("Далее");

                        // Показываем правильный ответ и ответ пользователя
                        correctAnswerText.setText("Правильный ответ: " + correctAnswersArray[nowQuestionTestPython]);
                        userAnswerText.setText("Ваш ответ: " + answersUser[nowQuestionTestPython]);

                        System.out.println("Правильный ответ: " + correctAnswersArray[nowQuestionTestPython]);
                        System.out.println("Ваш ответ: " + answersUser[nowQuestionTestPython]);
                        System.out.println("Вопрос - " + nowQuestionTestPython);

                        // Меняем текст вопроса в программе
                        question_text.setText(test_python[nowQuestionTestPython].getQuestion());

                        // Получаем массив ответов
                        String[] answers = test_python[nowQuestionTestPython].getAnswers();

                        // Преобразовываем в список (так удобнее сортировать элементы)
                        List<String> intList = Arrays.asList(answers);

                        // Подставляем ответы в радио кнопки
                        radio_btn_1.setText(intList.get(0));
                        radio_btn_2.setText(intList.get(1));
                        radio_btn_3.setText(intList.get(2));
                        radio_btn_4.setText(intList.get(3));

                        // Увеличиваем номер текущего вопроса
                        nowQuestionTestPython++;
                    }
                    break;
            }
        });

        // Отслеживание нажатия на кнопку "Ответить"
        answerBtn.setOnAction(e -> {
            // Получаем выбранную кнопку пользователем
            RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();

            // Код будет выполняться только если пользователь выбрал ответ
            if(selectedRadioButton != null) {
                // Получаем текст ответа
                String toogleGroupValue = selectedRadioButton.getText();

                // Сверяем ответ с корректным
                if (toogleGroupValue.equals(nowCorrectAnswer)) {
                    // Выводим информацию и увеличиваем количество верных ответов
                    System.out.println("Верный ответ");

                    // Увеличиваем число правильных ответов пользователя на один
                    correctAnswers++;
                } else
                    System.out.println("Не верный ответ");

                // Если сейчас был последний вопрос, то скрываем все поля
                switch (currentTest) {
                    case "test_java":
                        if (nowQuestionTestJava + 1 == test_java.length) {
                            // Добавляем правильный ответ на данный вопрос
                            correctAnswersArray[nowQuestionTestJava] = nowCorrectAnswer;
                            System.out.println(Arrays.toString(correctAnswersArray));

                            // Добавляем ответ пользователя в массив answersUser
                            answersUser[nowQuestionTestJava] = toogleGroupValue;
                            System.out.println(Arrays.toString(answersUser));

                            radio_btn_1.setVisible(false); // Скрываем все поля для выбора
                            radio_btn_2.setVisible(false);
                            radio_btn_3.setVisible(false);
                            radio_btn_4.setVisible(false);
                            answerBtn.setVisible(false); // Скрываем кнопку ответа

                            // Показываем текст в конце
                            question_text.setText("Вы ответили корректно на " + correctAnswers + " из " + test_java.length + " вопросов!");

                            // Показываем кнопку "Показать ответы" в конце
                            correctAnswersBtn.setVisible(true);
                        } else { // Если еще есть вопросы...
                            // Добавляем правильный ответ на данный вопрос
                            correctAnswersArray[nowQuestionTestJava] = nowCorrectAnswer;
                            System.out.println(Arrays.toString(correctAnswersArray));

                            // Добавляем ответ пользователя в массив answersUser
                            answersUser[nowQuestionTestJava] = toogleGroupValue;
                            System.out.println(Arrays.toString(answersUser));

                            // Увеличиваем номер текущего вопроса
                            nowQuestionTestJava++;

                            // Указываем новый текст верного ответа
                            nowCorrectAnswer = test_java[nowQuestionTestJava].correctAnswer();

                            // Меняем текст вопроса в программе
                            question_text.setText(test_java[nowQuestionTestJava].getQuestion());
                            // Получаем массив ответов
                            String[] answers = test_java[nowQuestionTestJava].getAnswers();

                            // Преобразовываем в список (так удобнее сортировать элементы)
                            List<String> intList = Arrays.asList(answers);

                            // Сортируем в случайном порядке
                            Collections.shuffle(intList);

                            // Подставляем ответы в радио кнопки
                            radio_btn_1.setText(intList.get(0));
                            radio_btn_2.setText(intList.get(1));
                            radio_btn_3.setText(intList.get(2));
                            radio_btn_4.setText(intList.get(3));

                            // Снимаем выделение, что указал пользователь ранее
                            selectedRadioButton.setSelected(false);
                        }

                        break;
                    case "test_javascript":
                        if (nowQuestionTestJS + 1 == test_javascript.length) {
                            // Добавляем правильный ответ на данный вопрос
                            correctAnswersArray[nowQuestionTestJS] = nowCorrectAnswer;
                            System.out.println(Arrays.toString(correctAnswersArray));

                            // Добавляем ответ пользователя в массив answersUser
                            answersUser[nowQuestionTestJS] = toogleGroupValue;
                            System.out.println(Arrays.toString(answersUser));

                            radio_btn_1.setVisible(false); // Скрываем все поля для выбора
                            radio_btn_2.setVisible(false);
                            radio_btn_3.setVisible(false);
                            radio_btn_4.setVisible(false);
                            answerBtn.setVisible(false); // Скрываем кнопку ответа

                            // Показываем текст в конце
                            question_text.setText("Вы ответили корректно на " + correctAnswers + " из " + test_javascript.length + " вопросов!");

                            // Показываем кнопку "Показать ответы" в конце
                            correctAnswersBtn.setVisible(true);
                        } else { // Если еще есть вопросы...
                            // Добавляем правильный ответ на данный вопрос
                            correctAnswersArray[nowQuestionTestJS] = nowCorrectAnswer;
                            System.out.println(Arrays.toString(correctAnswersArray));

                            // Добавляем ответ пользователя в массив answersUser
                            answersUser[nowQuestionTestJS] = toogleGroupValue;
                            System.out.println(Arrays.toString(answersUser));

                            // Увеличиваем номер текущего вопроса
                            nowQuestionTestJS++;

                            // Указываем новый текст верного ответа
                            nowCorrectAnswer = test_javascript[nowQuestionTestJS].correctAnswer();

                            // Меняем текст вопроса в программе
                            question_text.setText(test_javascript[nowQuestionTestJS].getQuestion());
                            // Получаем массив ответов
                            String[] answers = test_javascript[nowQuestionTestJS].getAnswers();

                            // Преобразовываем в список (так удобнее сортировать элементы)
                            List<String> intList = Arrays.asList(answers);

                            // Сортируем в случайном порядке
                            Collections.shuffle(intList);

                            // Подставляем ответы в радио кнопки
                            radio_btn_1.setText(intList.get(0));
                            radio_btn_2.setText(intList.get(1));
                            radio_btn_3.setText(intList.get(2));
                            radio_btn_4.setText(intList.get(3));

                            // Снимаем выделение, что указал пользователь ранее
                            selectedRadioButton.setSelected(false);
                        }

                        break;
                    case "test_python":
                        if (nowQuestionTestPython + 1 == test_python.length) {
                            // Добавляем правильный ответ на данный вопрос
                            correctAnswersArray[nowQuestionTestPython] = nowCorrectAnswer;
                            System.out.println(Arrays.toString(correctAnswersArray));

                            // Добавляем ответ пользователя в массив answersUser
                            answersUser[nowQuestionTestPython] = toogleGroupValue;
                            System.out.println(Arrays.toString(answersUser));

                            radio_btn_1.setVisible(false); // Скрываем все поля для выбора
                            radio_btn_2.setVisible(false);
                            radio_btn_3.setVisible(false);
                            radio_btn_4.setVisible(false);
                            answerBtn.setVisible(false); // Скрываем кнопку ответа

                            // Показываем текст в конце
                            question_text.setText("Вы ответили корректно на " + correctAnswers + " из " + test_python.length + " вопросов!");

                            // Показываем кнопку "Показать ответы" в конце
                            correctAnswersBtn.setVisible(true);
                        } else { // Если еще есть вопросы...
                            // Добавляем правильный ответ на данный вопрос
                            correctAnswersArray[nowQuestionTestPython] = nowCorrectAnswer;
                            System.out.println(Arrays.toString(correctAnswersArray));

                            // Добавляем ответ пользователя в массив answersUser
                            answersUser[nowQuestionTestPython] = toogleGroupValue;
                            System.out.println(Arrays.toString(answersUser));

                            // Увеличиваем номер текущего вопроса
                            nowQuestionTestPython++;

                            // Указываем новый текст верного ответа
                            nowCorrectAnswer = test_python[nowQuestionTestPython].correctAnswer();

                            // Меняем текст вопроса в программе
                            question_text.setText(test_python[nowQuestionTestPython].getQuestion());
                            // Получаем массив ответов
                            String[] answers = test_python[nowQuestionTestPython].getAnswers();

                            // Преобразовываем в список (так удобнее сортировать элементы)
                            List<String> intList = Arrays.asList(answers);

                            // Сортируем в случайном порядке
                            Collections.shuffle(intList);

                            // Подставляем ответы в радио кнопки
                            radio_btn_1.setText(intList.get(0));
                            radio_btn_2.setText(intList.get(1));
                            radio_btn_3.setText(intList.get(2));
                            radio_btn_4.setText(intList.get(3));

                            // Снимаем выделение, что указал пользователь ранее
                            selectedRadioButton.setSelected(false);
                        }

                        break;
                }
            }
        });
    }
}