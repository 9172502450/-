    import java.util.Scanner;

    public class Main {
        public static Integer romanToArab(String romanInput){               // Переводим римский ввод в арабский
            int result = 0;
            int[] arab = {10, 9, 5, 4, 1};
            String[] roman = {"X", "IX", "V", "IV", "I"};
            for (int i = 0; i < arab.length; i++ ) {
                while (romanInput.indexOf(roman[i]) == 0) {
                    result += arab[i];
                    romanInput = romanInput.substring(roman[i].length());   // Исключаем посчитанные числа
                }
            }

            return result;
        }
        public static String arabToRoman(int arabInput){                    // Перевод араб в рим
            String result = "";
            int value = 0;
            int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
            String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            for (int i = 0; i < arab.length; i++){
                value = arabInput / arab[i];
                for (int j = 0; j < value; j++){
                    result = result.concat(roman[i]);
                }
                arabInput = arabInput % arab[i];
            }
            return result;
        }

        public static String calc(String input) throws Exception{
            boolean romanOrArab = false;                            // Для понимания какое число на выходе (рим или араб)
            int result = 0;                                         // Считает выражение
            input=input.replaceAll(" ", "");                        // Удаляем пробелы
            String[] inputSplit = input.split("[+-/*]");
            if (inputSplit.length != 2){
                throw new Exception("Sign Exception");             // Исключение, если не 2 элемента
            }
            Integer firstNumber = 0;
            Integer secondNumber = 0;
            try {
                firstNumber = Integer.valueOf(inputSplit[0]);
                secondNumber = Integer.valueOf(inputSplit[1]);
            } catch (NumberFormatException e) {                     // Ловим, если не арабское
                try {
                    firstNumber = romanToArab(inputSplit[0]);
                    secondNumber = romanToArab(inputSplit[1]);
                    romanOrArab = true;
                } catch (NumberFormatException ex) {                // Ловим, если не римское
                    throw new Exception("Number Format Exception"); // Исключение, если не арабское и не римское!
                }
            }

            if ((firstNumber < 1) || (firstNumber > 10) || (secondNumber < 1) || (secondNumber > 10)){
                throw new Exception("Range Exception");             //Исключение, если не диапазоне значений
            }

            char sign = input.charAt(inputSplit[0].length());
            switch (sign) {
                case '+' -> result = firstNumber + secondNumber;
                case '-' -> result = firstNumber - secondNumber;
                case '*' -> result = firstNumber * secondNumber;
                case '/' -> result = firstNumber / secondNumber;
            }

            String output;// Наш вывод
            if (romanOrArab){
                if(result < 1){
                    throw new Exception("Result is less than 1");
                } else {
                    output = arabToRoman(result);
                }
            } else {
                output = Integer.toString(result);
            }
            return output;
        }

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            System.out.println("Введите выражение [2 + 2] или два римских числа [V + V], от 1 до 10: + Enter");// Запрос ввода
            String expression = input.nextLine();           // Ввод выражения
            try {
                String answer = calc(expression);           // Метод calc
                System.out.println("Ответ:\n" + answer);    // Выводим ответ
            }
            catch (Exception e) {                           // Ловим исключение
                System.out.println(e);
            }
        }
    }

