package org.example;

import java.util.*;

public class Quiz {

    Scanner sc = new Scanner(System.in);

    List<Map<String, Object>> quizData = new ArrayList<>();
    List<Integer> answerList = new ArrayList<>();

    public void initQuiz() {
        quizData.add(createQuestionData(2, 10, "1. Python에서 변수를 선언하는 방법은?",
                List.of("var name", "name = value", "set name", "name == value")));
        quizData.add(createQuestionData(1, 15, "2. Python에서 리스트(List)의 특징은 무엇인가요?",
                List.of("순서가 있고 변경 가능하다", "중복된 값을 가질 수 없다", "원소를 추가하거나 삭제할 수 없다", "정렬된 상태로 유지된다")));
        quizData.add(createQuestionData(1, 10, "3. Python에서 조건문을 작성하는 방법은?",
                List.of("if-else", "for-in", "while", "def")));
        quizData.add(createQuestionData(2, 5, "4. Python에서 함수를 정의하는 방법은?",
                List.of("class", "def", "import", "return")));
    }

    private Map<String, Object> createQuestionData(int answer, int score, String question, List<String> options) {
        Map<String, Object> data = new HashMap<>();
        data.put("answer", answer);
        data.put("score", score);
        data.put("question", question);
        data.put("options", options);
        return data;
    }

    public void askQuestion(Map<String, Object> questionData, int questionNumber) {
        System.out.println(questionData.get("question") + " (점수: " + questionData.get("score") + "점)");
        List<String> options = (List<String>) questionData.get("options");

        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ") " + options.get(i));
        }

        System.out.print("-정답 : ");
        int num = sc.nextInt();
        answerList.add(num);
    }

    public void result() {
        int totalScore = 0;
        System.out.println("----- 결과 -----");
        System.out.print("응답한 내용 : ");
        totalScore = getTotalScore();
        System.out.println();
        System.out.println("당신 응답 합계: " + totalScore);
    }

    private int getTotalScore() {
        int totalScore = 0;

        for (int i = 0; i < answerList.size(); i++) {
            int userAnswer = answerList.get(i);
            Map<String, Object> questionData = quizData.get(i);
            int correctAnswer = (int) questionData.get("answer");
            int score = (int) questionData.get("score");
            System.out.print((i + 1) + "번 " + userAnswer + ", ");

            if (userAnswer == correctAnswer) {
                totalScore += score;
            }
        }
        return totalScore;
    }

    public void runQuiz() {
        initQuiz();
        int questionNumber = 1;
        for (Map<String, Object> questionData : quizData) {
            askQuestion(questionData, questionNumber++);
        }
        result();
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.runQuiz();
    }

}
