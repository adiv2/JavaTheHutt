public class ExamQuestion {
    String question[][];
    String answers[][];
    ExamQuestion()
    {
        question=new String[10][5];
        answers=new String[10][8];
        for(int i=0;i<10;i++)
        {
            question[i][0]="select one choice from the following";
            question[i][1]="option 1";
            question[i][2]="option 2";
            question[i][3]="option 3";
            question[i][4]="option 4";
            for(int j=0;j<4;j++){
               answers[i][j]="";
               answers[i][j+4]="0";
            }
        }
    }
}
