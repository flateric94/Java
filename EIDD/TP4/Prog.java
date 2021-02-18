public class Prog {

    public static void main(String[] args ) {

        QuestionTexte q1 = new QuestionTexte("Quel est le nom du prof de JAVA ?", 1, "Charbit");
		QuestionNum q2 = new QuestionNum("Quel âge a le prof de JAVA", 2, 20);
		QuestionMulti q3 = new QuestionMulti("Citez une agglomération de plus de 20 millions d'habitants.", 3, "Pékin", "Lagos", "", "");

		Questionnaire quiz = new Questionnaire(3);
		quiz.tab[0] = q1;
		quiz.tab[1] = q2;
		quiz.tab[2] = q3;

		quiz.poser();

	}
}