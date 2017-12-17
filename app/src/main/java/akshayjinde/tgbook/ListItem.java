package akshayjinde.tgbook;

/**
 * Created by Akshay Jinde on 23-09-2017.
 */

public class ListItem {

    private String student_name;
    private String student_year;
    private String student_div;
    private String student_roll_no;

    public ListItem(String student_name, String student_year, String student_div, String student_roll_no) {
        this.student_name = student_name;
        this.student_year = student_year;
        this.student_div = student_div;
        this.student_roll_no = student_roll_no;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getStudent_year() {
        return student_year;
    }

    public String getStudent_div() {
        return student_div;
    }

    public String getStudent_roll_no() {
        return student_roll_no;
    }
}
