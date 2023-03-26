package ScheduleClass;

/**课表数据
 */
enum Frequency{
    always,single,twin
} //课表时间（单双周或always）

enum  Week{
    one,two,three,four,five,six,seven
} //周一至周日

enum ClassTime{
    one,two,three,four,five
} //课程时间段选择

public class DataSchedule {
    DataSchedule() {
        //TO DO
    }

    //读课表信息
    void readSchedule() {
        //TO DO
    }

    //保存课程信息
    boolean saveSchedule() {
        //TO DO
        return true;
    }

    //添加课程
    boolean addClass() {
        //TO DO
        return true;
    }
}